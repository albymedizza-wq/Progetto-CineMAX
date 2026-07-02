package cinemax.model;

/**
 * Rappresenta un proiezionista del cinema.
 * <p>
 * Il proiezionista è un utente del sistema che viene
 * identificato dal ruolo "proiezionista".
 *
 * @author Davide Gallorini
 * @author Guidi Lorenzo
 * @author Alberto Medizza
 */
public class Proiezionista extends Utente {

     /**
     * Crea un nuovo proiezionista utilizzando i dati principali.
     *
     * @param nome nome del proiezionista
     * @param cognome cognome del proiezionista
     * @param username username dell'account
     * @param password password dell'account
     */
    public Proiezionista(String nome,
                         String cognome,
                         String username,
                         String password) {

        super(nome,
              cognome,
              username,
              password,
              "proiezionista");
    }

     /**
     * Crea un nuovo proiezionista con tutti i dati anagrafici.
     *
     * @param nome nome del proiezionista
     * @param cognome cognome del proiezionista
     * @param username username dell'account
     * @param password password dell'account
     * @param dataNascita data di nascita
     * @param luogoNascita luogo di nascita
     */
    public Proiezionista(String nome,
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
              "proiezionista");
    }
}