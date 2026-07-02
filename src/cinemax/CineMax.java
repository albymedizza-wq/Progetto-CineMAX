package cinemax;

import cinemax.model.Proiezione;
import cinemax.model.Utente;
import cinemax.service.AuthService;
import cinemax.service.PrenotazioneService;
import cinemax.service.ProiezioneService;
import java.util.Scanner;

/**
 * Classe principale dell'applicazione CineMAX.
 * <p>
 * Contiene il punto di accesso del programma e gestisce
 * il menu principale dell'applicazione.
 *
 * @author Davide Gallorini
 * @author Guidi Lorenzo
 * @author Alberto Medizza
 */
public class CineMax {

  /**
 * Avvia l'applicazione CineMAX.
 * <p>
 * Inizializza i servizi principali e gestisce il menu
 * dell'applicazione fino alla chiusura del programma.
 *
 * @param args argomenti passati da riga di comando
 */
    public static void main(String[] args) {

        Scanner scanner =
                new Scanner(System.in);

        AuthService authService =
                new AuthService();

        ProiezioneService
                proiezioneService =
                new ProiezioneService();

        PrenotazioneService
                prenotazioneService =
                new PrenotazioneService(
                        proiezioneService
                );

        Utente utenteLoggato = null;
        int scelta;

        do {

            System.out.println(
                    "\n===== CINEMAX ====="
            );

            System.out.println(
                    "1. Login"
            );

            System.out.println(
                    "2. Registrazione"
            );

            System.out.println(
                    "3. Cerca film"
            );

            System.out.println(
                    "4. Mostra proiezioni"
            );

            System.out.println(
                    "5. Prenota posti"
            );

            System.out.println(
                    "6. Visualizza prenotazioni"
            );

            System.out.println(
                    "7. Elimina prenotazione"
            );

            System.out.println(
                    "8. Profilo"
            );

            System.out.println(
                    "9. Logout"
            );

            System.out.println(
                    "0. Esci"
            );

            scelta =
                    leggiIntero(
                            scanner,
                            "Scelta: ",
                            0,
                            9
                    );

            switch (scelta) {

                case 1:
                    if (utenteLoggato != null) {
                        System.out.println(
                                "Sei già loggato come " +
                                        utenteLoggato.getUsername()
                        );
                    } else {
                        utenteLoggato = authService.login(
                                scanner
                        );
                    }
                    break;

                case 2:
                    Utente registrato =
                            authService.registraCliente(
                                    scanner
                            );
                    if (registrato != null) {
                        utenteLoggato = registrato;
                    }
                    break;

                case 3:
                    proiezioneService
                            .cercaProiezioni(
                                    scanner
                            );
                    break;

                case 4:
                    proiezioneService
                            .mostraProiezioni();
                    break;

                case 5:
                    if (utenteLoggato == null) {
                        System.out.println(
                                "Devi accedere per prenotare."
                        );
                        break;
                    }
                    Proiezione proiezione =
                            proiezioneService
                                    .selezionaProiezione(
                                            scanner
                                    );
                    if (proiezione != null) {
                        proiezione.mostraSala();
                        prenotazioneService
                                .creaPrenotazione(
                                        scanner,
                                        utenteLoggato,
                                        proiezione
                                );
                    }
                    break;

                case 6:
                    prenotazioneService
                            .visualizzaPrenotazioni(
                                    utenteLoggato
                            );
                    break;

                case 7:
                    prenotazioneService
                            .eliminaPrenotazione(
                                    scanner,
                                    utenteLoggato
                            );
                    break;

                case 8:
                    if (utenteLoggato == null) {
                        System.out.println(
                                "Devi effettuare il login per vedere il profilo."
                        );
                    } else {
                        System.out.println(
                                "\n===== PROFILO UTENTE ====="
                        );
                        System.out.println(
                                "Nome: " +
                                        utenteLoggato.getNome()
                        );
                        System.out.println(
                                "Cognome: " +
                                        utenteLoggato.getCognome()
                        );
                        System.out.println(
                                "Username: " +
                                        utenteLoggato.getUsername()
                        );
                        System.out.println(
                                "Ruolo: " +
                                        utenteLoggato.getRuolo()
                        );
                        System.out.println(
                                "Data di nascita: " +
                                        utenteLoggato.getDataNascita()
                     );
                        System.out.println(
                                "Luogo di nascita: " +
                                        utenteLoggato.getLuogoNascita()
                        );
                    }
                    break;

                case 9:
                    if (utenteLoggato != null) {
                        System.out.println(
                                "Logout eseguito per " +
                                        utenteLoggato.getUsername()
                        );
                        utenteLoggato = null;
                    } else {
                        System.out.println(
                                "Nessun utente loggato."
                        );
                    }
                    break;

                case 0:
                    System.out.println(
                            "Uscita..."
                    );
                    break;

                default:
                    System.out.println(
                            "Scelta non valida."
                    );
            }

        } while (scelta != 0);

        scanner.close();
    }

    /**
 * Legge un numero intero verificando che appartenga
 * all'intervallo indicato.
 *
 * @param scanner scanner utilizzato per leggere l'input
 * @param prompt messaggio mostrato all'utente
 * @param min valore minimo consentito
 * @param max valore massimo consentito
 * @return numero intero valido
 */
    private static int leggiIntero(
            Scanner scanner,
            String prompt,
            int min,
            int max) {

        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();
            try {
                int value = Integer.parseInt(line);
                if (value >= min && value <= max) {
                    return value;
                }
                System.out.println(
                        "Inserisci un numero tra " +
                                min + " e " + max + "."
                );
            } catch (NumberFormatException e) {
                System.out.println(
                        "Inserisci un numero valido."
                );
            }
        }
    }
}