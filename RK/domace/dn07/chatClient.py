import json
import socket
import struct
import sys
import threading

PORT = 1234
HEADER_LENGTH = 2


def receive_fixed_length_msg(sock, msglen):
    message = b''
    while len(message) < msglen:
        chunk = sock.recv(msglen - len(message))  # preberi nekaj bajtov
        if chunk == b'':
            raise RuntimeError("socket connection broken")
        message = message + chunk  # pripni prebrane bajte sporocilu

    return message


def receive_message(sock):
    header = receive_fixed_length_msg(sock, HEADER_LENGTH)  # preberi glavo sporocila (v prvih 2 bytih je dolzina sporocila)
    message_length = struct.unpack("!H", header)[0]  # pretvori dolzino sporocila v int

    message = None
    if message_length > 0:  # ce je vse OK
        message = receive_fixed_length_msg(sock, message_length)  # preberi sporocilo
        message = message.decode("utf-8")

    return message


def send_message(sock, msg, user=None, to=None):
    if not user:
        raise ValueError("user is required")

    jsonMsg = {
        "sender": user,
        "message": msg,
        "to": to
    }

    jsonString = json.dumps(jsonMsg)
    encoded_message = jsonString.encode("utf-8")  # pretvori sporocilo v niz bajtov, uporabi UTF-8 kodno tabelo

    # ustvari glavo v prvih 2 bytih je dolzina sporocila (HEADER_LENGTH)
    # metoda pack "!H" : !=network byte order, H=unsigned short
    header = struct.pack("!H", len(encoded_message))

    message = header + encoded_message  # najprj posljemo dolzino sporocilo, slee nato sporocilo samo
    sock.sendall(message)


# message_receiver funkcija tece v loceni niti
def message_receiver():
    while True:
        msg_received = receive_message(sock)

        if not msg_received:
            print("Message received is empty")

        jsonMsg = json.loads(msg_received)

        if (jsonMsg["to"] == user or jsonMsg["to"] is None) and jsonMsg["sender"] != user:
            private = ""
            if jsonMsg['to'] is not None:
                private = "|private"

            print(f"[RKchat{private}] {jsonMsg['sender']}: {jsonMsg['message']}")


if __name__ == '__main__':
    user = input("Input your username: ")

    print("[system] connecting to chat server ...")
    sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    sock.connect(("localhost", PORT))
    print("[system] connected!")

    thread = threading.Thread(target=message_receiver)
    thread.daemon = True
    thread.start()

    while True:
        try:
            msg = input("")

            msgList = msg.split(" ")

            to = None
            if msgList[0] == "msg":
                msgList.pop(0)
                to = msgList.pop(0)

            msgSend = " ".join(msgList)

            send_message(sock, msgSend, user, to)
        except KeyboardInterrupt:
            sys.exit()
