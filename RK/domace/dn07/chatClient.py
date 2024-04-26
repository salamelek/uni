import json
import socket
import struct
import sys
import threading

PORT = 1234
HEADER_LENGTH = 3


def receive_fixed_length_msg(sock, msglen):
    message = b''
    while len(message) < msglen:
        chunk = sock.recv(msglen - len(message))
        if chunk == b'':
            raise RuntimeError("socket connection broken")
        message = message + chunk

    return message


def receive_message(sock):
    header = receive_fixed_length_msg(sock, HEADER_LENGTH)
    headerTuple = struct.unpack("!HB", header)

    msgLen = headerTuple[0]

    message = None
    if msgLen > 0:
        message = receive_fixed_length_msg(sock, msgLen)
        message = message.decode("utf-8")

    else:
        print("Message length is 0!")

    return headerTuple, message


def send_message(sock, msg, user=None, to=None, msgType=2):
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


# message_receiver funkcija tece v loceni niti
def message_receiver():
    while True:
        headerTuple, msg_received = receive_message(sock)
        msgType = headerTuple[1]
        jsonMsg = json.loads(msg_received)

        # server response
        if msgType == 0:
            jsonResp = json.loads(msg_received)

            if not jsonResp["status"]:
                # print errors
                print(f"[Server]: status: {jsonResp['status']} | msg: {jsonResp['msg']}")

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
    send_message(sock, None, user, None, 1)

    thread = threading.Thread(target=message_receiver)
    thread.daemon = True
    thread.start()

    while True:
        try:
            msg = input("")
            msgList = msg.split(" ")

            to = None
            msgType = 2     # public

            if msgList[0] == "msg":
                msgList.pop(0)
                to = msgList.pop(0)
                msgType = 3

            elif msgList[0] == "username":
                msgList.pop(0)
                user = msgList.pop(0)
                send_message(sock, None, user, None, 1)
                continue

            msgSend = " ".join(msgList)

            if msgSend == "":
                continue

            send_message(sock, msgSend, user, to, msgType)
        except KeyboardInterrupt:
            sys.exit()
