package cinemax.model;

public class Proiezionista extends Utente {

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
}