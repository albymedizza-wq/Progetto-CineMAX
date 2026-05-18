package cinemax.model;

public class Bigliettaio extends Utente {

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