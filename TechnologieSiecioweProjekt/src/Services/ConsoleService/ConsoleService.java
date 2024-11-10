package Services.ConsoleService;
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

    }

    public void Rejestracja(){
        System.out.println("Podaj nazwę użytkownika:");
        String userName = scanner.nextLine();

        System.out.println("Podaj hasło:");
        String password = scanner.nextLine();

        System.out.println("Podaj adres email:");
        String email = scanner.nextLine();


    }


}

