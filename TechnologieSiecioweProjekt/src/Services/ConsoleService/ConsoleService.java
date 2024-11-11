package Services.ConsoleService;
import Models.Method;
import Models.RequestData;
import Models.User;
import Network.Client;

import java.io.IOException;
import java.io.Console;
import java.util.Scanner;

public class ConsoleService {
    public Scanner scanner = new Scanner(System.in);
    public Client client;

    public ConsoleService(String serverAddress,int port){
        client = new Client(serverAddress,port);
    }
    public void Start(){
        System.out.println("===Internetowy chat===");
        System.out.println("1.Zaloguj się");
        System.out.println("2.Zarejestruj się");
        String wybor = scanner.nextLine();

        switch (wybor){
            case "1":
                Logowanie();
                break;
            case "2":
                Rejestracja();
                break;
            default:
                System.out.println("Nierozpoznana opcja");
                Start();
                break;
        }
    }
    public void Logowanie(){
        System.out.println("Podaj nazwę użytkownika:");
        String userName = scanner.nextLine();

        System.out.println("Podaj hasło:");
        String password = scanner.nextLine();

        User user = new User(0,userName,password,"");
        RequestData requestData = new RequestData(Method.LogIn,"",user);
        client.SendData(requestData);
        System.out.println(client.GetResponseString());

        Start();
    }

    public void Rejestracja(){
        System.out.println("Podaj nazwę użytkownika:");
        String userName = scanner.nextLine();

        System.out.println("Podaj hasło:");
        String password = scanner.nextLine();

        System.out.println("Podaj adres email:");
        String email = scanner.nextLine();

        User user = new User(0,userName,password,email);
        RequestData requestData = new RequestData(Method.Post,"",user);

        client.SendData(requestData);

        System.out.println(client.GetResponseString());

        Start();

    }


}

