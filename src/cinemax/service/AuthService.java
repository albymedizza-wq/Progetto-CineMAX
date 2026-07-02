package cinemax.service;

import cinemax.model.Cliente;
import cinemax.model.Utente;
import java.util.Scanner;

/**
 * Gestisce le operazioni di autenticazione degli utenti.
 * <p>
 * La classe permette di effettuare il login e la registrazione
 * dei clienti, verificando le credenziali e salvando i dati
 * nei file del sistema.
 *
 * @author Davide Gallorini
 * @author Guidi Lorenzo
 * @author Alberto Medizza
 */
public class AuthService {

        /**
 * Percorso del file contenente gli utenti registrati.
 */
    private static final String UTENTI_FILE = "data/utenti.txt";

    /**
 * Gestisce la lettura e la scrittura dei dati su file.
 */
    private final FileManager fileManager = new FileManager();

    /**
 * Effettua il login di un utente verificando le credenziali
 * inserite.
 *
 * @param scanner scanner utilizzato per leggere l'input
 * @return l'utente autenticato, oppure {@code null} se il login
 *         non è andato a buon fine
 */
    public Utente login(Scanner scanner) {

        System.out.print("Username: ");

        String username =
                scanner.nextLine();

        System.out.print("Password: ");

        String password =
                scanner.nextLine();

        if (fileManager.verificaCredenziali(
                UTENTI_FILE,
                username,
                password)) {

            Utente utente = fileManager.caricaUtente(
                    UTENTI_FILE,
                    username
            );

            if (utente != null) {
                System.out.println(
                        "Login effettuato."
                );
                return utente;
            }
        }

        System.out.println(
                "Credenziali non valide."
        );
        return null;
    }

    /**
 * Registra un nuovo cliente nel sistema.
 * <p>
 * I dati inseriti vengono salvati nel file degli utenti
 * e viene creato il relativo oggetto Cliente.
 *
 * @param scanner scanner utilizzato per leggere l'input
 * @return il cliente registrato oppure {@code null}
 *         se la registrazione non è stata completata
 */
    public Utente registraCliente(
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

        if (fileManager.esisteUsername(
                UTENTI_FILE,
                username)) {

            System.out.println(
                    "Username già presente. Scegli un altro username."
            );
            return null;
        }

        System.out.print("Password: ");

        String password =
                scanner.nextLine();

        System.out.print("Data di nascita (dd/MM/yyyy): ");

        String dataNascita =
                scanner.nextLine();

        System.out.print("Luogo di nascita: ");

        String luogoNascita =
                scanner.nextLine();

        Cliente cliente =
                new Cliente(
                        nome,
                        cognome,
                        username,
                        password,
                        dataNascita,
                        luogoNascita
                );

        boolean salvato = fileManager.salvaUtente(
                UTENTI_FILE,
                nome,
                cognome,
                username,
                password,
                dataNascita,
                luogoNascita,
                cliente.getRuolo()
        );

        if (salvato) {
            System.out.println(
                    "Registrazione completata."
            );
            return cliente;
        }

        System.out.println(
                "Errore durante la registrazione. Riprova."
        );
        return null;
    }
}