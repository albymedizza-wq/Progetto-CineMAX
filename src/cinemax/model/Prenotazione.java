package cinemax.model;

public class Prenotazione {

    private String codice;

    private String usernameCliente;

    private Proiezione proiezione;

    private int numeroBiglietti;

    public Prenotazione(String codice,
                         String usernameCliente,
                         Proiezione proiezione,
                         int numeroBiglietti) {

        this.codice = codice;
        this.usernameCliente = usernameCliente;
        this.proiezione = proiezione;
        this.numeroBiglietti = numeroBiglietti;
    }

    @Override
    public String toString() {

        return "Codice: " + codice +
               "\nCliente: " + usernameCliente +
               "\nBiglietti: " + numeroBiglietti +
               "\nFilm: " +
               proiezione.getFilm().getTitolo();
    }
}