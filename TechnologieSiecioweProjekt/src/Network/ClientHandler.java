package Network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable{
    private Socket _clientSocket;

    public ClientHandler(Socket socket) {
        this._clientSocket = socket;
    }
    @Override
    public void run() {
        try (
                BufferedReader in = new BufferedReader(new InputStreamReader(_clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(_clientSocket.getOutputStream(), true);
        ) {
            String message;
            while ((message = in.readLine()) != null) {
                System.out.println("Otrzymano od klienta: " + message);
                out.println("Serwer: " + message);
            }
        } catch (IOException e) {
            System.err.println("Błąd w komunikacji z klientem: " + e.getMessage());
        } finally {
            try {
                _clientSocket.close();
            } catch (IOException e) {
                System.err.println("Błąd przy zamykaniu połączenia: " + e.getMessage());
            }
        }
    }
}
