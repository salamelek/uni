import json
import signal
import socket
import struct
import threading

PORT = 1234
HEADER_LENGTH = 3
signal.signal(signal.SIGINT, signal.SIG_DFL)


def receive_fixed_length_msg(sock, msglen):
    message = b''
    while len(message) < msglen:
        chunk = sock.recv(msglen - len(message))  # preberi nekaj bajtov
        if chunk == b'':
            raise RuntimeError("socket connection broken")
        message = message + chunk  # pripni prebrane bajte sporocilu

    return message


def receive_message(sock):
    header = receive_fixed_length_msg(sock, HEADER_LENGTH)
    headerTuple = struct.unpack("!HB", header)

    msgLen = headerTuple[0]

    message = None
    if msgLen:
        message = receive_fixed_length_msg(sock, msgLen)  # preberi sporocilo
        message = message.decode("utf-8")

    else:
        print("Message length is 0!")

    return headerTuple, message


def send_message(sock, message, msgType):
    encoded_message = message.encode("utf-8")

    # metoda pack "!H" : !=network byte order, H=unsigned short
    header = struct.pack("!HB", len(encoded_message), msgType)

    message = header + encoded_message  # najprj posljemo dolzino sporocilo, slee nato sporocilo samo
    sock.sendall(message)


# funkcija za komunikacijo z odjemalcem (tece v loceni niti za vsakega odjemalca)
def client_thread(client_sock, client_addr):
    global clients

    print(f"[system] connected with {client_addr[0]}:{str(client_addr[1])}")
    print(f"[system] we now have {len(clients)} clients")

    try:
        while True:
            header, msg_received = receive_message(client_sock)

            if msg_received == "":
                print("Message was empty!")
                continue

            jsonMsg = json.loads(msg_received)
            msgType = header[1]

            respMsg = json.dumps({"status": 1, "msg": ""})

            # server response
            if msgType == 0:
                # ignore
                continue

            # send username
            elif msgType == 1:
                username = jsonMsg["sender"]

                if username in usedNames:
                    respMsg = json.dumps({
                        "status": 0,
                        "msg": "Name already in use! (use \"username <your username>\" to set a new one."
                    })

                else:
                    usedNames.append(username)
                    clients[client_sock] = username
                    respMsg = json.dumps({
                        "status": 1,
                        "msg": "Name accepted!"
                    })

                send_message(client_sock, respMsg, 0)

            # public message
            elif msgType == 2:
                # check if the user has a username
                if clients[client_sock] is None:
                    respMsg = json.dumps({
                        "status": 0,
                        "msg": "Name not set!"
                    })

                else:
                    if jsonMsg["message"] == "":
                        respMsg = json.dumps({
                            "status": 0,
                            "msg": "Don't send empty messages!"
                        })

                    else:
                        for client in clients.keys():
                            # Don't send to the sending client
                            if client is client_sock:
                                continue

                            send_message(client, msg_received, 2)

                send_message(client_sock, respMsg, 0)

            # private message
            elif msgType == 3:
                # check if the user has a username
                if clients[client_sock] is None:
                    respMsg = json.dumps({
                        "status": 0,
                        "msg": "Name not set!"
                    })

                else:
                    if jsonMsg["message"] == "":
                        respMsg = json.dumps({
                            "status": 0,
                            "msg": "Don't send empty messages!"
                        })

                    else:
                        found = False
                        for client, name in clients.items():
                            if name == jsonMsg["to"]:
                                found = True
                                send_message(client, msg_received, 2)
                                break

                        if not found:
                            respMsg = json.dumps({
                                "status": 0,
                                "msg": "Name not found"
                            })

                send_message(client_sock, respMsg, 0)

            else:
                raise Exception("Unknown message type")

            print("[RKchat] [" + client_addr[0] + ":" + str(client_addr[1]) + "] : " + msg_received)

    except Exception as e:
        print(f"An exception occurred: {e}")

    with clients_lock:
        try:
            usedNames.remove(clients[client_sock])
        except ValueError:
            pass

        clients.pop(client_sock)

    print(f"[system] we now have {len(clients)} clients")
    client_sock.close()


if __name__ == '__main__':
    server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    server_socket.bind(("localhost", PORT))
    server_socket.listen(1)

    print("[system] listening ...")
    clients = {}
    usedNames = []
    clients_lock = threading.Lock()

    while True:
        try:
            client_sock, client_addr = server_socket.accept()

            with clients_lock:
                clients[client_sock] = None

            thread = threading.Thread(target=client_thread, args=(client_sock, client_addr))
            thread.daemon = True
            thread.start()

        except KeyboardInterrupt:
            break

    print("[system] closing server socket ...")
    server_socket.close()
