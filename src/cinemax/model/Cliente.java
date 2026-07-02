package cinemax.model;

/**
 * Rappresenta un cliente registrato nel sistema CineMAX.
 * <p>
 * Il cliente può effettuare il login, prenotare posti,
 * visualizzare le proprie prenotazioni e gestire il proprio profilo.
 *
 * @author Davide Gallorini
 * @author Guidi Lorenzo
 * @author Alberto Medizza
 */
public class Cliente extends Utente {

      /**
     * Crea un nuovo cliente utilizzando i dati principali.
     *
     * @param nome nome del cliente
     * @param cognome cognome del cliente
     * @param username username scelto dal cliente
     * @param password password dell'account
     */
    public Cliente(String nome,
                   String cognome,
                   String username,
                   String password) {

        super(nome,
              cognome,
              username,
              password,
              "cliente");
    }

    /**
     * Crea un nuovo cliente con tutti i dati anagrafici.
     *
     * @param nome nome del cliente
     * @param cognome cognome del cliente
     * @param username username scelto dal cliente
     * @param password password dell'account
     * @param dataNascita data di nascita del cliente
     * @param luogoNascita luogo di nascita del cliente
     */
    public Cliente(String nome,
                   String cognome,
                   String username,
                   String password,
                   String dataNascita,
                   String luogoNascita) {

        super(nome,
              cognome,
              username,
              password,
              dataNascita,
              luogoNascita,
              "cliente");
    }
}