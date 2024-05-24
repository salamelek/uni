import signal
import socket
import struct
import threading

signal.signal(signal.SIGINT, signal.SIG_DFL)

PORT = 1234
HEADER_LENGTH = 3


def receiveFixedLengthMsg(sock, msgLen):
    message = b''

    while len(message) < msgLen:
        chunk = sock.recv(msgLen - len(message))

        if chunk == b'':
            raise RuntimeError("socket connection broken")

        message = message + chunk

    return message


def receiveMessage(sock):
    header = receiveFixedLengthMsg(sock, HEADER_LENGTH)
    headerTuple = struct.unpack("!HB", header)

    msgLen = headerTuple[0]
    msgType = headerTuple[1]

    message = None

    if msgLen:
        message = receiveFixedLengthMsg(sock, msgLen)
        message = message.decode("utf-8")

    else:
        print("Message len is 0!")

    return message


def sendMessage(sock, msgType, message):
    encoded_message = message.encode("utf-8")

    header = struct.pack("!HB", len(encoded_message), msgType)

    msg = header + encoded_message
    sock.sendall(msg)


def clientThread(client_sock, client_addr):
    global clients

    print(f"[system] connected with {client_addr[0]}:{str(client_addr[1])}")
    print("[system] we now have " + str(len(clients)) + " clients")

    try:
        while True:
            msg_received = receiveMessage(client_sock)

            if not msg_received:
                break

            print(f"[RKchat] [{client_addr[0]}:{str(client_addr[1])}]: {msg_received}")

            for client in clients:
                sendMessage(client, 0, msg_received)

    except Exception as e:
        # tule bi lahko bolj elegantno reagirali, npr. na posamezne izjeme. Trenutno kar pozremo izjemo
        pass

    # prisli smo iz neskoncne zanke
    with clients_lock:
        clients.remove(client_sock)
    print("[system] we now have " + str(len(clients)) + " clients")
    client_sock.close()


if __name__ == "__main__":
    # kreiraj socket
    server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    server_socket.bind(("localhost", PORT))
    server_socket.listen(1)

    # cakaj na nove odjemalce
    print("[system] listening ...")
    clients = set()
    clients_lock = threading.Lock()
    while True:
        try:
            # pocakaj na novo povezavo - blokirajoc klic
            client_sock, client_addr = server_socket.accept()
            with clients_lock:
                clients.add(client_sock)

            thread = threading.Thread(target=clientThread, args=(client_sock, client_addr))
            thread.daemon = True
            thread.start()

        except KeyboardInterrupt:
            break

    print("[system] closing server socket ...")
    server_socket.close()
