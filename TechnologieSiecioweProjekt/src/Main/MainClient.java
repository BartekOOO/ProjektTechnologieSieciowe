package Main;
import Network.Client;
import Services.ConsoleService.ConsoleService;


public class MainClient {
    public static void main(String[] args) {
        String serverAddress = "localhost";
        int port = 5001;

        ConsoleService service = new ConsoleService(serverAddress,port);
        service.Start();
    }
}
