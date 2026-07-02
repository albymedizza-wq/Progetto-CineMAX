package cinemax.model;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Rappresenta una proiezione cinematografica.
 * <p>
 * Contiene il film proiettato, la data, l'orario, il costo del
 * biglietto e la disponibilità dei posti in sala.
 *
 * @author Davide Gallorini
 * @author Guidi Lorenzo
 * @author Alberto Medizza
 */
public class Proiezione {

    /**
 * Film associato alla proiezione.
 */
    private Film film;

    /**
 * Data della proiezione.
 */
    private LocalDate data;

    /**
 * Orario di inizio della proiezione.
 */
    private LocalTime ora;

    /**
 * Prezzo del biglietto.
 */
    private double costo;

    /**
 * Numero di posti ancora disponibili.
 */
    private int postiDisponibili;

   /**
 * Stato dei posti della sala.
 * true indica un posto occupato,
 * false indica un posto libero.
 */
    // MATRICE SALA CINEMA
    // false = libero
    // true = occupato
    private boolean[][] posti;

    /**
 * Crea una nuova proiezione.
 *
 * @param film film proiettato
 * @param data data della proiezione
 * @param ora orario della proiezione
 * @param costo costo del biglietto
 * @param postiDisponibili numero iniziale di posti disponibili
 */
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

    /**
 * Restituisce il film della proiezione.
 *
 * @return film associato
 */
    public Film getFilm() {
        return film;
    }

    /**
 * Restituisce la data della proiezione.
 *
 * @return data della proiezione
 */
    public LocalDate getData() {
        return data;
    }

    /**
 * Restituisce l'orario della proiezione.
 *
 * @return orario della proiezione
 */
    public LocalTime getOra() {
        return ora;
    }

    /**
 * Restituisce il prezzo dei biglietti.
 *
 * @return prezzo del biglietto
 */
    public double getCosto() {
        return costo;
    }

    /**
 * Restituisce il numero di posti ancora disponibili.
 *
 * @return numero di posti disponibili
 */
    public int getPostiDisponibili() {
        return postiDisponibili;
    }

    /**
 * Visualizza sul terminale la disposizione dei posti della sala,
 * indicando quelli liberi e quelli già occupati.
 */
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

    /**
 * Prenota un posto della sala se disponibile.
 *
 * @param riga riga del posto
 * @param colonna colonna del posto
 * @return true se la prenotazione è andata a buon fine,
 *         false se il posto era già occupato
 */
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

    /**
 * Libera un posto precedentemente prenotato.
 *
 * @param riga riga del posto
 * @param colonna colonna del posto
 */
    // LIBERA POSTO
    public void liberaPosto(
            int riga,
            int colonna) {

        if (posti[riga][colonna]) {

            posti[riga][colonna] = false;

            postiDisponibili++;
        }
    }

    /**
 * Restituisce una descrizione della proiezione.
 *
 * @return informazioni principali della proiezione
 */
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