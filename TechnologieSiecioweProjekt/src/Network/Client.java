package Network;

import Models.RequestData;
import Models.ResponseData;
import com.sun.source.tree.BreakTree;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client {
    private String serverAddress;
    private int port;
    private ResponseData responseData;

    public Client(String serverAddress, int port) {
        this.serverAddress = serverAddress;
        this.port = port;
    }

    public String GetResponseString(){
        return  responseData.ToJSONBody();
    }

    public void SendData(RequestData requestData) {
        ResponseData result = new ResponseData();
        try (
                Socket socket = new Socket(serverAddress, port);
                OutputStream out = socket.getOutputStream();
                InputStream in = socket.getInputStream();
        ) {
            byte[] data = (requestData.ToJSONBody() + "\n").getBytes("UTF-8");
            out.write(data);
            out.flush();


            byte[] responseBuffer = new byte[1024];
            int bytesRead = in.read(responseBuffer);
            String response = new String(responseBuffer, 0, bytesRead, "UTF-8");
            result.ReadDataFromJSON(response);
            responseData = result;

        } catch (IOException e) {
            System.err.println("Błąd w komunikacji z serwerem: " + e.getMessage());
        }

    }

}
