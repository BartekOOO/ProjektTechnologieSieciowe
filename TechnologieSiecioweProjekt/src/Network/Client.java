package Network;

import Models.RequestData;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client {
    private String serverAddress;
    private int port;

    public Client(String serverAddress, int port) {
        this.serverAddress = serverAddress;
        this.port = port;
    }

    public void sendMessage(RequestData requestData) {
        try (
                Socket socket = new Socket(serverAddress, port);
                OutputStream out = socket.getOutputStream();
                InputStream in = socket.getInputStream();
        ) {
            byte[] data = (requestData.ToJSONBody() + "\n").getBytes("UTF-8");
            out.write(data);
            out.flush();
            System.out.println("Wysłano do serwera: " + requestData.ToJSONBody());

            byte[] responseBuffer = new byte[1024];
            int bytesRead = in.read(responseBuffer);
            String response = new String(responseBuffer, 0, bytesRead, "UTF-8");
            System.out.println("Odpowiedź od serwera: " + response);

        } catch (IOException e) {
            System.err.println("Błąd w komunikacji z serwerem: " + e.getMessage());
        }
    }

}
