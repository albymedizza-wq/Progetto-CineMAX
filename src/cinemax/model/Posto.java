package cinemax.model;

/**
 * Rappresenta un posto all'interno della sala cinematografica.
 * <p>
 * Ogni posto è identificato da una riga e da una colonna.
 *
 * @author Davide Gallorini
 * @author Guidi Lorenzo
 * @author Alberto Medizza
 */
public class Posto {

    /**
     * Riga del posto.
     */
    private int riga;

    /**
     * Colonna del posto.
     */
    private int colonna;

    /**
     * Crea un nuovo posto.
     *
     * @param riga riga del posto
     * @param colonna colonna del posto
     */
    public Posto(int riga,
                 int colonna) {

        this.riga = riga;
        this.colonna = colonna;
    }

      /**
     * Restituisce la riga del posto.
     *
     * @return numero della riga
     */
    public int getRiga() {
        return riga;
    }

     /**
     * Restituisce la colonna del posto.
     *
     * @return numero della colonna
     */
    public int getColonna() {
        return colonna;
    }

     /**
     * Restituisce il posto nel formato utilizzato dal cinema
     * (ad esempio A1, B10, C5).
     *
     * @return posizione del posto
     */
    @Override
    public String toString() {

        char letteraRiga =
                (char) ('A' + riga);

        return letteraRiga +
               String.valueOf(colonna + 1);
    }
}