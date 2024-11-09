package Main;
import Network.Client;


public class MainClient {
    public static void main(String[] args) {
        String serverAddress = "localhost";
        int port = 5001;
        String message = "Witaj, serwerze!";

        Client client = new Client(serverAddress, port);
        client.sendMessage(message);
    }
}
