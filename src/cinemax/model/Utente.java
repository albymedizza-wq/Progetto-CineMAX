package cinemax.model;

import java.util.ArrayList;

/**
 * Classe astratta che rappresenta un utente del sistema CineMAX.
 * <p>
 * Contiene le informazioni comuni a tutte le tipologie di utenti,
 * come dati anagrafici, credenziali di accesso, ruolo e gestione
 * delle prenotazioni e dello storico delle proiezioni.
 *
 * @author Davide Gallorini
 * @author Guidi Lorenzo
 * @author Alberto Medizza
 */

public abstract class Utente {

     /**
     * Nome dell'utente.
     */
    protected String nome;

     /**
     * Cognome dell'utente.
     */
    protected String cognome;
    
     /**
     * Username utilizzato per l'accesso.
     */
    protected String username;

     /**
     * Password associata all'account.
     */
    protected String password;

      /**
     * Data di nascita dell'utente.
     */
    protected String dataNascita;

     /**
     * Luogo di nascita dell'utente.
     */
    protected String luogoNascita;

     /**
     * Ruolo ricoperto dall'utente all'interno del sistema.
     */
    protected String ruolo;

     /**
     * Elenco delle prenotazioni effettuate dall'utente.
     */
    // Prenotazioni attive utente
    protected ArrayList<Prenotazione> prenotazioni;

     /**
     * Storico delle proiezioni visualizzate dall'utente.
     */
    // Storico proiezioni viste
    protected ArrayList<Proiezione> storicoVisioni;

    /**
     * Crea un nuovo utente utilizzando solamente i dati principali.
     *
     * @param nome nome dell'utente
     * @param cognome cognome dell'utente
     * @param username username dell'account
     * @param password password dell'account
     * @param ruolo ruolo assegnato all'utente
     */
    public Utente(String nome,
                  String cognome,
                  String username,
                  String password,
                  String ruolo) {

        this(nome,
             cognome,
             username,
             password,
             "",
             "",
             ruolo);
    }

     /**
     * Crea un nuovo utente con tutti i dati anagrafici.
     *
     * @param nome nome dell'utente
     * @param cognome cognome dell'utente
     * @param username username dell'account
     * @param password password dell'account
     * @param dataNascita data di nascita
     * @param luogoNascita luogo di nascita
     * @param ruolo ruolo assegnato all'utente
     */
    public Utente(String nome,
                  String cognome,
                  String username,
                  String password,
                  String dataNascita,
                  String luogoNascita,
                  String ruolo) {

        this.nome = nome;
        this.cognome = cognome;
        this.username = username;
        this.password = password;
        this.dataNascita = dataNascita;
        this.luogoNascita = luogoNascita;
        this.ruolo = ruolo;

        prenotazioni = new ArrayList<>();
        storicoVisioni = new ArrayList<>();
    }

     /**
     * Restituisce lo username dell'utente.
     *
     * @return username dell'utente
     */
    public String getUsername() {
        return username;
    }

     /**
     * Restituisce la password dell'utente.
     *
     * @return password dell'utente
     */
    public String getPassword() {
        return password;
    }

     /**
     * Restituisce il nome dell'utente.
     *
     * @return nome dell'utente
     */
    public String getNome() {
        return nome;
    }

      /**
     * Restituisce il cognome dell'utente.
     *
     * @return cognome dell'utente
     */
    public String getCognome() {
        return cognome;
    }

     /**
     * Restituisce la data di nascita.
     *
     * @return data di nascita dell'utente
     */
    public String getDataNascita() {
        return dataNascita;
    }

    /**
     * Restituisce il luogo di nascita.
     *
     * @return luogo di nascita dell'utente
     */
    public String getLuogoNascita() {
        return luogoNascita;
    }

    /**
     * Restituisce il ruolo dell'utente.
     *
     * @return ruolo dell'utente
     */
    public String getRuolo() {
        return ruolo;
    }

     /**
     * Aggiunge una prenotazione all'elenco dell'utente.
     *
     * @param prenotazione prenotazione da aggiungere
     */
    public void aggiungiPrenotazione(
            Prenotazione prenotazione) {

        prenotazioni.add(prenotazione);
    }

     /**
     * Rimuove una prenotazione dall'elenco dell'utente.
     *
     * @param prenotazione prenotazione da rimuovere
     */
    public void rimuoviPrenotazione(
            Prenotazione prenotazione) {

        prenotazioni.remove(prenotazione);
    }

      /**
     * Restituisce l'elenco delle prenotazioni dell'utente.
     *
     * @return lista delle prenotazioni
     */
    public ArrayList<Prenotazione>
    getPrenotazioni() {

        return prenotazioni;
    }

     /**
     * Aggiunge una proiezione allo storico delle visioni.
     *
     * @param proiezione proiezione da aggiungere allo storico
     */
    public void aggiungiStorico(
            Proiezione proiezione) {

        storicoVisioni.add(proiezione);
    }

     /**
     * Restituisce lo storico delle proiezioni visualizzate.
     *
     * @return lista delle proiezioni visualizzate
     */
    public ArrayList<Proiezione>
    getStoricoVisioni() {

        return storicoVisioni;
    }
}