package cinemax;

import java.util.Scanner;

import cinemax.service.AuthService;
import cinemax.service.ProiezioneService;

public class CineMax {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        AuthService authService = new AuthService();
        ProiezioneService proiezioneService =
                new ProiezioneService();

        int scelta;

        do {

            System.out.println("\n===== CINEMAX =====");
            System.out.println("1. Login");
            System.out.println("2. Registrazione");
            System.out.println("3. Cerca proiezioni");
            System.out.println("0. Esci");

            System.out.print("Scelta: ");

            scelta = scanner.nextInt();
            scanner.nextLine();

            switch (scelta) {

                case 1:
                    authService.login(scanner);
                    break;

                case 2:
                    authService.registraCliente(scanner);
                    break;

                case 3:
                    proiezioneService.cercaProiezioni(scanner);
                    break;

                case 0:
                    System.out.println("Uscita...");
                    break;

                default:
                    System.out.println("Scelta non valida.");
            }

        } while (scelta != 0);

        scanner.close();
    }
}