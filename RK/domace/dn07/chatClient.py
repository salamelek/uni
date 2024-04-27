import json
import socket
import struct
import sys
import threading

from chatServer import PORT, receiveMessage


def sendMessage(sock, msg, user=None, to=None, msgType=2):
    if not user:
        raise ValueError("User is required")

    jsonMsg = {
        "sender": user,
        "message": msg,
        "to": to
    }

    jsonString = json.dumps(jsonMsg)
    encoded_message = jsonString.encode("utf-8")

    header = struct.pack("!HB", len(encoded_message), msgType)

    message = header + encoded_message
    sock.sendall(message)


def messageReceiver():
    while True:
        headerTuple, msg_received = receiveMessage(sock)
        msgType = headerTuple[1]
        jsonMsg = json.loads(msg_received)

        # server response
        if msgType == 0:
            jsonResp = json.loads(msg_received)

            # print errors
            if not jsonResp["status"]:
                print(f"[Server]: {jsonResp['msg']}")

            continue

        elif msgType in [2, 3]:
            private = ""
            if jsonMsg['to'] is not None:
                private = "|private"

            print(f"[RKchat{private}] {jsonMsg['sender']}: {jsonMsg['message']}")


if __name__ == '__main__':
    """
    Types:
    0: server response
    1: send username
    2: send public message
    3: send private message
    """

    print("[system] connecting to chat server ...")
    user = input("Input your username: ")

    sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    sock.connect(("localhost", PORT))

    # send username
    sendMessage(sock, None, user, None, 1)

    thread = threading.Thread(target=messageReceiver)
    thread.daemon = True
    thread.start()

    while True:
        try:
            msg = input("")
            msgList = msg.split(" ")

            if msgList[0] == "msg":
                msgList.pop(0)
                to = msgList.pop(0)
                msgType = 3

            elif msgList[0] == "username":
                msgList.pop(0)
                user = msgList.pop(0)
                sendMessage(sock, None, user, None, 1)
                continue

            else:
                msgType = 2
                to = None

            msgSend = " ".join(msgList)
            sendMessage(sock, msgSend, user, to, msgType)

        except KeyboardInterrupt:
            sys.exit()
