import sys
import ssl
import socket
import struct
import threading

from server import PORT, receiveMessage


def send_message(sock, msgType, message):
    """
    MsgType:
        0:

    :param sock:
    :param msgType:
    :param message:
    :return:
    """

    encoded_message = message.encode("utf-8")

    # create msg header (msg len and msg type)
    header = struct.pack("!HB", len(encoded_message), msgType)

    msg = header + encoded_message
    sock.sendall(msg)


def message_receiver():
    while True:
        msg_received = receiveMessage(sock)

        if not len(msg_received):
            continue

        print("[RKchat] " + msg_received)


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
    sock = connectToServer(mySslCtx)
    startReceiver()

    print("Done!")

    while True:
        try:
            usrInput = input("")

            if not len(usrInput):
                continue

            # commands
            if usrInput[0] == "/":
                pass

            # normal messages
            else:
                send_message(sock, 0, usrInput)

        except KeyboardInterrupt:
            sys.exit()