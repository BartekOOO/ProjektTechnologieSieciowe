package Network;

import Network.ClientHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket _serverSocker;

    public Server(int port) throws IOException {
        _serverSocker = new ServerSocket(port);
        System.out.println("Serwer TCP uruchomiony na porcie: " + port);
    }

    public void start() {
        while (true) {
            try {
                Socket clientSocket = _serverSocker.accept();
                System.out.println("Połączono z klientem: " + clientSocket.getInetAddress().getHostAddress());
                ClientHandler clientHandler = new ClientHandler(clientSocket);
                new Thread(clientHandler).start();
            } catch (IOException e) {
                System.err.println("Błąd przy akceptowaniu połączenia: " + e.getMessage());
            }
        }
    }
}
