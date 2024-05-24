import socket
import struct
import sys
import threading

from server import PORT, receiveMessage


def send_message(sock, msgType, message):
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


def connectToServer():
    print("Connecting to server...")

    sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    sock.connect(("localhost", PORT))

    print("Done!")

    return sock


def secureConnection():
    pass


def startReceiver():
    thread = threading.Thread(target=message_receiver)
    thread.daemon = True
    thread.start()


if __name__ == '__main__':
    sock = connectToServer()

    secureConnection()

    startReceiver()

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
