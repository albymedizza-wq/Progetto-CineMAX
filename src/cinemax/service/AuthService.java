package cinemax.service;

import java.util.Scanner;

public class AuthService {

    public void login(Scanner scanner) {

        System.out.print("Username: ");

        String username =
                scanner.nextLine();

        System.out.print("Password: ");

        String password =
                scanner.nextLine();

        System.out.println(
                "Login effettuato."
        );
    }

    public void registraCliente(
            Scanner scanner) {

        System.out.print("Nome: ");

        String nome =
                scanner.nextLine();

        System.out.print("Cognome: ");

        String cognome =
                scanner.nextLine();

        System.out.print("Username: ");

        String username =
                scanner.nextLine();

        System.out.print("Password: ");

        String password =
                scanner.nextLine();

        System.out.println(
                "Registrazione completata."
        );
    }
}