package Network;

import Models.RequestData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    private String _serverAddress;
    private int _port;

    public Client(String serverAddress, int port) {
        this._serverAddress = serverAddress;
        this._port = port;
    }

    public void sendMessage(RequestData requestData) {
        try (
                Socket socket = new Socket(_serverAddress, _port);

                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        ) {
            out.println(requestData.ToJSONBody());
            System.out.println("Wysłano do serwera: " + requestData.ToJSONBody());

            String response = in.readLine();
            System.out.println("Odpowiedź od serwera: " + response);

        } catch (IOException e) {
            System.err.println("Błąd w komunikacji z serwerem: " + e.getMessage());
        }
    }
}
