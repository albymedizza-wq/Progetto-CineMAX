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
}