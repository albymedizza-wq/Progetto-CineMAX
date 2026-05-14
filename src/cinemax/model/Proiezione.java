package cinemax.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Proiezione {

    private Film film;

    private LocalDate data;
    private LocalTime ora;

    private double costo;

    private int postiDisponibili;

    public Proiezione(Film film,
                      LocalDate data,
                      LocalTime ora,
                      double costo,
                      int postiDisponibili) {

        this.film = film;
        this.data = data;
        this.ora = ora;
        this.costo = costo;
        this.postiDisponibili = postiDisponibili;
    }

    public Film getFilm() {
        return film;
    }

    public LocalDate getData() {
        return data;
    }

    public LocalTime getOra() {
        return ora;
    }

    public double getCosto() {
        return costo;
    }

    public int getPostiDisponibili() {
        return postiDisponibili;
    }

    public void setPostiDisponibili(int postiDisponibili) {

        this.postiDisponibili = postiDisponibili;
    }

    @Override
    public String toString() {

        return "\nFilm: " + film.getTitolo() +
               "\nGenere: " + film.getGenere() +
               "\nRegista: " + film.getRegista() +
               "\nData: " + data +
               "\nOra: " + ora +
               "\nCosto: €" + costo +
               "\nPosti disponibili: " +
               postiDisponibili;
    }
}