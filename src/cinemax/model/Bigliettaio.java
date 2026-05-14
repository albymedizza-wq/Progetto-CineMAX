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
}