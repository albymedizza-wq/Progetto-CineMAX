package cinemax.model;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Prenotazione {

    private String codice;

    private String usernameCliente;

    private Proiezione proiezione;

    private ArrayList<Posto> postiPrenotati;

    private double costoTotale;

    private LocalDateTime dataAcquisto;

    public Prenotazione(String codice,
                         String usernameCliente,
                         Proiezione proiezione) {

        this.codice = codice;
        this.usernameCliente = usernameCliente;
        this.proiezione = proiezione;
        this.dataAcquisto = LocalDateTime.now();

        postiPrenotati =
                new ArrayList<>();
        costoTotale = 0.0;
    }

    public Prenotazione(String codice,
                         String usernameCliente,
                         Proiezione proiezione,
                         LocalDateTime dataAcquisto,
                         double costoTotale) {

        this.codice = codice;
        this.usernameCliente = usernameCliente;
        this.proiezione = proiezione;
        this.dataAcquisto = dataAcquisto;
        this.costoTotale = costoTotale;

        postiPrenotati =
                new ArrayList<>();
    }

    public String getCodice() {
        return codice;
    }

    public String getUsernameCliente() {
        return usernameCliente;
    }

    public Proiezione getProiezione() {
        return proiezione;
    }

    public ArrayList<Posto>
    getPostiPrenotati() {

        return postiPrenotati;
    }

    public void aggiungiPosto(
            Posto posto) {

        postiPrenotati.add(posto);
    }

    public double getCostoTotale() {
        return costoTotale;
    }

    public void setCostoTotale(
            double costoTotale) {

        this.costoTotale = costoTotale;
    }

    public LocalDateTime getDataAcquisto() {
        return dataAcquisto;
    }

    @Override
    public String toString() {

        return "\n===== PRENOTAZIONE =====" +

               "\nCodice: " +
               codice +

               "\nCliente: " +
               usernameCliente +

               "\nFilm: " +
               proiezione.getFilm()
                       .getTitolo() +

               "\nData: " +
               proiezione.getData() +

               "\nOra: " +
               proiezione.getOra() +

               "\nPosti: " +
               postiPrenotati +

               "\nCosto unitario: Euro " +
               String.format("%.2f", proiezione.getCosto()) +

               "\nTotale acquisto: Euro " +
               String.format("%.2f", costoTotale) +

               "\nData acquisto: " +
               dataAcquisto;
    }
}