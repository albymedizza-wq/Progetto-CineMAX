package cinemax.model;

import java.util.ArrayList;

public abstract class Utente {

    protected String nome;
    protected String cognome;
    protected String username;
    protected String password;
    protected String dataNascita;
    protected String luogoNascita;
    protected String ruolo;

    // Prenotazioni attive utente
    protected ArrayList<Prenotazione> prenotazioni;

    // Storico proiezioni viste
    protected ArrayList<Proiezione> storicoVisioni;

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

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String getDataNascita() {
        return dataNascita;
    }

    public String getLuogoNascita() {
        return luogoNascita;
    }

    public String getRuolo() {
        return ruolo;
    }

    public void aggiungiPrenotazione(
            Prenotazione prenotazione) {

        prenotazioni.add(prenotazione);
    }

    public void rimuoviPrenotazione(
            Prenotazione prenotazione) {

        prenotazioni.remove(prenotazione);
    }

    public ArrayList<Prenotazione>
    getPrenotazioni() {

        return prenotazioni;
    }

    public void aggiungiStorico(
            Proiezione proiezione) {

        storicoVisioni.add(proiezione);
    }

    public ArrayList<Proiezione>
    getStoricoVisioni() {

        return storicoVisioni;
    }
}