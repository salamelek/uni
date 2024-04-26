import json
import signal
import socket
import struct
import threading

PORT = 12345
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

    messageLength = headerTuple[0]
    messageType = headerTuple[1]

    if not messageLength:
        print("Message length is 0!")

    message = receive_fixed_length_msg(sock, messageLength)
    message = message.decode("utf-8")
    jsonMsg = json.loads(message)

    # public message
    if messageType == 0:
        return message

    # private message
    elif messageType == 1:
        pass

    # username request
    elif messageType == 2:
        # Only the server asks for usernames, so ignore
        pass

    # username response
    elif messageType == 3:
        # The client has responded with a username, so we can link it
        username = jsonMsg["sender"]

        if username not in existingUsernames:
            clients[sock] = username
            existingUsernames.append(username)
            return None

        else:
            pass

    # error
    elif messageType == 4:
        pass

    else:
        raise Exception("Unknown message type code")

    return None


def send_message(sock, message, msgType):
    encoded_message = message.encode("utf-8")

    # ustvari glavo v prvih 2 bytih je dolzina sporocila (HEADER_LENGTH)
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
            msg_received = receive_message(client_sock)

            if not msg_received:
                break

            print("[RKchat] [" + client_addr[0] + ":" + str(client_addr[1]) + "] : " + msg_received)

            for client in clients.keys():
                send_message(client, msg_received, 0)

    except Exception as e:
        print("An exception occurred: " + str(e))
        pass

    with clients_lock:
        clients.pop(client_sock)

    print(f"[system] we now have {len(clients)} clients")
    client_sock.close()


def requestUsername(sock):
    send_message(sock, "", 2)


if __name__ == '__main__':
    server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    server_socket.bind(("localhost", PORT))
    server_socket.listen(1)

    print("[system] listening ...")
    clients = {}
    existingUsernames = []
    clients_lock = threading.Lock()

    while True:
        try:
            client_sock, client_addr = server_socket.accept()

            requestUsername(client_sock)

            with clients_lock:
                clients[client_sock] = None

            thread = threading.Thread(target=client_thread, args=(client_sock, client_addr))
            thread.daemon = True
            thread.start()

        except KeyboardInterrupt:
            break

    print("[system] closing server socket ...")
    server_socket.close()
