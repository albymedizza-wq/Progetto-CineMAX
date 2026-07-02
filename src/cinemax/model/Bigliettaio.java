package cinemax.model;

/**
 * Rappresenta un bigliettaio del cinema.
 * <p>
 * Il bigliettaio è un utente del sistema identificato
 * dal ruolo "bigliettaio".
 *
 * @author Davide Gallorini
 * @author Guidi Lorenzo
 * @author Alberto Medizza
 */
public class Bigliettaio extends Utente {

     /**
     * Crea un nuovo bigliettaio utilizzando i dati principali.
     *
     * @param nome nome del bigliettaio
     * @param cognome cognome del bigliettaio
     * @param username username dell'account
     * @param password password dell'account
     */
    public Bigliettaio(String nome,
                       String cognome,
                       String username,
                       String password) {

        super(nome,
              cognome,
              username,
              password,
              "bigliettaio");
    }

     /**
     * Crea un nuovo bigliettaio con tutti i dati anagrafici.
     *
     * @param nome nome del bigliettaio
     * @param cognome cognome del bigliettaio
     * @param username username dell'account
     * @param password password dell'account
     * @param dataNascita data di nascita
     * @param luogoNascita luogo di nascita
     */
    public Bigliettaio(String nome,
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
              "bigliettaio");
    }
}