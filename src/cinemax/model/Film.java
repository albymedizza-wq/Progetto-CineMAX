package cinemax.model;

/**
 * Rappresenta un film disponibile nel catalogo del cinema.
 * <p>
 * Ogni film è identificato dal titolo, dal genere, dal regista,
 * dall'anno di uscita, dalla durata e dall'età minima consigliata.
 *
 * @author Davide Gallorini
 * @author Guidi Lorenzo
 * @author Alberto Medizza
 */
public class Film {

    /**
     * Titolo del film.
     */
    private String titolo;

         /**
     * Genere cinematografico del film.
     */
    private String genere;

     /**
     * Regista del film.
     */
    private String regista;

     /**
     * Anno di uscita del film.
     */
    private int anno;

     /**
     * Durata del film espressa in minuti.
     */
    private int durata;

      /**
     * Età minima consigliata per la visione.
     */
    private int etaMinima;

     /**
     * Crea un nuovo film.
     *
     * @param titolo titolo del film
     * @param genere genere del film
     * @param regista regista del film
     * @param anno anno di uscita
     * @param durata durata in minuti
     * @param etaMinima età minima consigliata
     */
    public Film(String titolo,
                String genere,
                String regista,
                int anno,
                int durata,
                int etaMinima) {

        this.titolo = titolo;
        this.genere = genere;
        this.regista = regista;
        this.anno = anno;
        this.durata = durata;
        this.etaMinima = etaMinima;
    }

    /**
     * Restituisce il titolo del film.
     *
     * @return titolo del film
     */
    public String getTitolo() {
        return titolo;
    }

    /**
     * Restituisce il genere del film.
     *
     * @return genere del film
     */
    public String getGenere() {
        return genere;
    }

/**
     * Restituisce il regista del film.
     *
     * @return regista del film
     */
    public String getRegista() {
        return regista;
    }

     /**
     * Restituisce l'anno di uscita.
     *
     * @return anno di uscita
     */
    public int getAnno() {
        return anno;
    }

     /**
     * Restituisce la durata del film.
     *
     * @return durata in minuti
     */
    public int getDurata() {
        return durata;
    }


     /**
     * Restituisce l'età minima consigliata.
     *
     * @return età minima
     */
    public int getEtaMinima() {
        return etaMinima;
    }

     /**
     * Restituisce una rappresentazione testuale del film.
     *
     * @return informazioni principali del film
     */
    @Override
    public String toString() {

        return titolo + " - " +
               genere + " - " +
               regista;
    }
}