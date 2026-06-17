package cinemax.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Proiezione {

    private Film film;

    private LocalDate data;

    private LocalTime ora;

    private double costo;

    private int postiDisponibili;

    // MATRICE SALA CINEMA
    // false = libero
    // true = occupato
    private boolean[][] posti;

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

        // 10 righe x 20 colonne = 200 posti
        posti = new boolean[10][20];
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

    // MOSTRA MAPPA SALA
    public void mostraSala() {

        System.out.println(
                "\n===== SALA CINEMA ====="
        );

        for (int i = 0; i < posti.length; i++) {

            char letteraRiga =
                    (char) ('A' + i);

            System.out.print(
                    letteraRiga + " "
            );

            for (int j = 0;
                 j < posti[i].length;
                 j++) {

                if (posti[i][j]) {

                    System.out.print("[x]");

                } else {

                    System.out.print("[ ]");
                }
            }

            System.out.println();
        }

        System.out.println();

        System.out.print("   ");

        for (int i = 1; i <= 20; i++) {

            if (i < 10) {

                System.out.print(" " + i + " ");

            } else {

                System.out.print(i + " ");
            }
        }

        System.out.println();
    }

    // PRENOTA POSTO
    public boolean prenotaPosto(
            int riga,
            int colonna) {

        if (!posti[riga][colonna]) {

            posti[riga][colonna] = true;

            postiDisponibili--;

            return true;
        }

        return false;
    }

    // LIBERA POSTO
    public void liberaPosto(
            int riga,
            int colonna) {

        if (posti[riga][colonna]) {

            posti[riga][colonna] = false;

            postiDisponibili++;
        }
    }

    @Override
    public String toString() {

        return "\nFilm: " +
               film.getTitolo() +

               "\nGenere: " +
               film.getGenere() +

               "\nRegista: " +
               film.getRegista() +

               "\nData: " +
               data +

               "\nOra: " +
               ora +

               "\nCosto: Euro " +
               costo +

               "\nPosti disponibili: " +
               postiDisponibili;
    }
}