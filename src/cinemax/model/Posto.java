package cinemax.model;

public class Posto {

    private int riga;
    private int colonna;

    public Posto(int riga,
                 int colonna) {

        this.riga = riga;
        this.colonna = colonna;
    }

    public int getRiga() {
        return riga;
    }

    public int getColonna() {
        return colonna;
    }

    @Override
    public String toString() {

        char letteraRiga =
                (char) ('A' + riga);

        return letteraRiga +
               String.valueOf(colonna + 1);
    }
}