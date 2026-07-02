package cinemax.model;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Rappresenta una prenotazione effettuata da un cliente.
 * <p>
 * Ogni prenotazione contiene il codice identificativo,
 * il cliente che l'ha effettuata, la proiezione scelta,
 * i posti prenotati, il costo totale e la data di acquisto.
 *
 * @author Davide Gallorini
 * @author Guidi Lorenzo
 * @author Alberto Medizza
 */
public class Prenotazione {

     /**
     * Codice identificativo della prenotazione.
     */

    private String codice;

     /**
     * Username del cliente che ha effettuato la prenotazione.
     */
    private String usernameCliente;

     /**
     * Proiezione associata alla prenotazione.
     */
    private Proiezione proiezione;

     /**
     * Elenco dei posti prenotati.
     */
    private ArrayList<Posto> postiPrenotati;

    /**
     * Costo totale della prenotazione.
     */
    private double costoTotale;

     /**
     * Data e ora di acquisto della prenotazione.
     */
    private LocalDateTime dataAcquisto;

     /**
     * Crea una nuova prenotazione.
     *
     * @param codice codice identificativo
     * @param usernameCliente username del cliente
     * @param proiezione proiezione prenotata
     */
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

     /**
     * Crea una prenotazione utilizzando dati già esistenti.
     *
     * @param codice codice identificativo
     * @param usernameCliente username del cliente
     * @param proiezione proiezione prenotata
     * @param dataAcquisto data di acquisto
     * @param costoTotale costo totale della prenotazione
     */
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
 /**
     * Restituisce il codice della prenotazione.
     *
     * @return codice identificativo
     */
    public String getCodice() {
        return codice;
    }

     /**
     * Restituisce lo username del cliente.
     *
     * @return username del cliente
     */
    public String getUsernameCliente() {
        return usernameCliente;
    }

     /**
     * Restituisce la proiezione prenotata.
     *
     * @return proiezione associata
     */
    public Proiezione getProiezione() {
        return proiezione;
    }

     /**
     * Restituisce l'elenco dei posti prenotati.
     *
     * @return lista dei posti prenotati
     */
    public ArrayList<Posto>
    getPostiPrenotati() {

        return postiPrenotati;
    }

     /**
     * Aggiunge un posto alla prenotazione.
     *
     * @param posto posto da aggiungere
     */
    public void aggiungiPosto(
            Posto posto) {

        postiPrenotati.add(posto);
    }

 /**
     * Restituisce il costo totale della prenotazione.
     *
     * @return costo totale
     */
    public double getCostoTotale() {
        return costoTotale;
    }

     /**
     * Imposta il costo totale della prenotazione.
     *
     * @param costoTotale nuovo costo totale
     */
    public void setCostoTotale(
            double costoTotale) {

        this.costoTotale = costoTotale;
    }

     /**
     * Restituisce la data di acquisto della prenotazione.
     *
     * @return data e ora di acquisto
     */
    public LocalDateTime getDataAcquisto() {
        return dataAcquisto;
    }

        /**
     * Restituisce una rappresentazione testuale della prenotazione.
     *
     * @return informazioni principali della prenotazione
     */

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