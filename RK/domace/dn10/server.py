import json
import ssl
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

    return msgType, message


def sendMessage(sock, msgType: int, message: str):
    encoded_message = message.encode("utf-8")

    header = struct.pack("!HB", len(encoded_message), msgType)

    msg = header + encoded_message
    sock.sendall(msg)


def sendError(receiverSocket, errorMsg=""):
    err = json.dumps({
        "msg": errorMsg,
        "receiver": clientsSN[receiverSocket],
        "sender": None
    })

    sendMessage(receiverSocket, 2, err)


def clientThread(clientSocket, clientAddr, clientName):
    global clientsSN, clientsNS

    print(f"[system] connected with {clientAddr[0]}:{str(clientAddr[1])} - {clientName}")
    print(f"[system] we now have {len(clientsSN)} clients")

    try:
        while True:
            msgType, msgStr = receiveMessage(clientSocket)

            if not msgStr:
                print("The message was empty!")
                continue

            # add sender
            jsonMsg = json.loads(msgStr)
            jsonMsg["sender"] = clientsSN[clientSocket]
            msgStr = json.dumps(jsonMsg)

            print(f"[RKchat] [{clientAddr[0]}:{str(clientAddr[1])} - {clientName}]: {msgStr}")

            # public messages
            if msgType == 0:
                for client in clientsSN.keys():
                    # dont send back to sending client
                    if client == clientSocket:
                        continue

                    sendMessage(client, 0, msgStr)

            # private message
            elif msgType == 1:
                receiver = jsonMsg["receiver"]
                try:
                    receiverSocket = clientsNS[receiver]
                except KeyError:
                    print("Username not found, sending error...")
                    sendError(clientSocket, "Username not found!")
                    continue

                print(f"Sending private message to {receiver}: {jsonMsg['msg']}")

                sendMessage(receiverSocket, 1, msgStr)

            # other types of messages
            else:
                print("Other type of msg!")
                continue

    except Exception as e:
        print(f"{clientName} disconnected because of: {e}")

    with clientsLock:
        clientsSN.pop(clientSocket)
        clientsNS.pop(clientName)

    print(f"[system] we now have {len(clientsSN)} clients")
    clientSocket.close()


def setupSslContext():
    context = ssl.SSLContext(ssl.PROTOCOL_TLSv1_2)
    context.verify_mode = ssl.CERT_REQUIRED

    context.load_cert_chain(certfile="server.crt", keyfile="server.key")
    context.load_verify_locations(cafile="clients.pem")

    context.set_ciphers('ECDHE-RSA-AES128-GCM-SHA256')

    return context


def createSocket():
    serverSocket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

    serverSocket.bind(("localhost", PORT))
    serverSocket.listen(1)

    return serverSocket


if __name__ == "__main__":
    mySslCtx = setupSslContext()
    serverSocket = createSocket()

    print("[system] listening ...")

    # clientSocker: clientName
    clientsSN = {}
    # clientName: clientSocket
    clientsNS = {}
    clientsLock = threading.Lock()

    while True:
        try:
            clientSocket, clientAddr = serverSocket.accept()
            clientSocket = mySslCtx.wrap_socket(clientSocket, server_side=True)

            clientName = clientSocket.getpeercert()["subject"][3][0][1]

            with clientsLock:
                clientsSN[clientSocket] = clientName
                clientsNS[clientName] = clientSocket

            thread = threading.Thread(target=clientThread, args=(clientSocket, clientAddr, clientName))
            thread.daemon = True
            thread.start()

        except KeyboardInterrupt:
            break

        except ssl.SSLCertVerificationError:
            print("Client tried to connect but certificate verification failed")

        except ssl.SSLError as e:
            print("Something happened with SSL.. maybe a client?")
            print(f"Error: {e}")

    print("[system] closing server socket ...")
    serverSocket.close()
