package cinemax.model;

public class Cliente extends Utente {

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