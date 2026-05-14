package cinemax.model;

public abstract class Utente {

    protected String nome;
    protected String cognome;
    protected String username;
    protected String password;
    protected String ruolo;

    public Utente(String nome,
                  String cognome,
                  String username,
                  String password,
                  String ruolo) {

        this.nome = nome;
        this.cognome = cognome;
        this.username = username;
        this.password = password;
        this.ruolo = ruolo;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRuolo() {
        return ruolo;
    }
}