package Network;

import Models.RequestData;
import Models.ResponseCode;
import Models.ResponseData;
import Services.JSONService.ServerDataService;

import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private Socket clientSocket;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }
    @Override
    public void run() {
        try (
                InputStream in = clientSocket.getInputStream();
                OutputStream out = clientSocket.getOutputStream();
        ) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            StringBuilder messageBuilder = new StringBuilder();

            while ((bytesRead = in.read(buffer)) != -1) {
                String data = new String(buffer, 0, bytesRead, "UTF-8");
                messageBuilder.append(data);

                if (data.contains("\n")) {
                    break;
                }
            }

            String message = messageBuilder.toString().replace("\n", "");

            RequestData requestData = new RequestData();
            requestData.ReadDataFromJSON(message);
            System.out.println(requestData.ToJSONBody());

            ResponseData response = ServerDataService.ProcessData(requestData);

            out.write((response.ToJSONBody()).getBytes("UTF-8"));
            out.flush();

        } catch (IOException e) {
            System.err.println("Błąd w komunikacji z klientem: " + e.getMessage());
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                System.err.println("Błąd przy zamykaniu połączenia: " + e.getMessage());
            }
        }
    }
}
