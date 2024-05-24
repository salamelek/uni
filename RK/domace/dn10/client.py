import json
import sys
import ssl
import socket
import struct
import threading

from server import PORT, receiveMessage


def send_message(sock, msgType, message, receiver=None):
    """
    MsgType:
        0: public message
        1: private message
        2: errors

    :param receiver:
    :param sock:
    :param msgType:
    :param message:
    :return:
    """

    jsonStr = json.dumps({
        "msg": message,
        "receiver": receiver
    })

    encoded_message = jsonStr.encode("utf-8")

    # create msg header (msg len and msg type)
    header = struct.pack("!HB", len(encoded_message), msgType)

    msg = header + encoded_message
    sock.sendall(msg)


def message_receiver():
    while True:
        msgType, msgStr = receiveMessage(clientSocket)

        if not len(msgStr):
            continue

        print("[RKchat] " + msgStr)


def connectToServer(sslCtx):
    sock = sslCtx.wrap_socket(socket.socket(socket.AF_INET, socket.SOCK_STREAM))
    sock.connect(("localhost", PORT))

    return sock


def setupSslContext():
    context = ssl.SSLContext(ssl.PROTOCOL_TLSv1_2)
    context.verify_mode = ssl.CERT_REQUIRED

    context.load_cert_chain(certfile="janez.crt", keyfile="janez.key")
    context.load_verify_locations("server.crt")

    context.set_ciphers("ECDHE-RSA-AES128-GCM-SHA256")

    return context


def startReceiver():
    thread = threading.Thread(target=message_receiver)
    thread.daemon = True
    thread.start()


if __name__ == '__main__':
    print("Connecting to server...")

    mySslCtx = setupSslContext()
    clientSocket = connectToServer(mySslCtx)
    startReceiver()

    print("Done!")

    while True:
        try:
            usrInput = input("")

            if not len(usrInput):
                continue

            # commands
            if usrInput[0] == "/":
                usrList = usrInput.split(" ")

                if usrList[0] == "/msg":
                    msgTo = usrList[1]
                    msgContent = " ".join(usrList[2:])

                    send_message(clientSocket, 1, msgContent, msgTo)

            # normal messages
            else:
                send_message(clientSocket, 0, usrInput)

        except KeyboardInterrupt:
            sys.exit()
