package Main;
import Network.Server;
import java.io.IOException;

public class MainServer {
    public static void main(String[] args) {

        try {
            Server serwer = new Server(5001);
            serwer.start();
        }
        catch (IOException ex)
        {

        }
        
    }
}