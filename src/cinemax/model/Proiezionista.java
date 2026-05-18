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