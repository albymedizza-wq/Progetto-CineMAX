package cinemax.service;

import cinemax.model.Proiezione;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Gestisce le operazioni relative alle proiezioni.
 * <p>
 * Consente di visualizzare le proiezioni disponibili,
 * effettuare ricerche e selezionare una proiezione.
 *
 * @author Davide Gallorini
 * @author Guidi Lorenzo
 * @author Alberto Medizza
 */
public class ProiezioneService {

/**
 * Elenco delle proiezioni disponibili.
 */
    private ArrayList<Proiezione>
            listaProiezioni;

/**
 * Carica le proiezioni disponibili dal file.
 */
    public ProiezioneService() {

        FileManager fileManager =
                new FileManager();

        listaProiezioni =
                fileManager.caricaProiezioni(
                        "data/proiezioni.csv"
                );
    }

/**
 * Mostra tutte le proiezioni disponibili.
 */
    // MOSTRA TUTTE LE PROIEZIONI
    public void mostraProiezioni() {

        System.out.println(
                "\n===== PROIEZIONI ====="
        );

        for (int i = 0;
             i < listaProiezioni.size();
             i++) {

            System.out.println(
                    "\n[" + i + "]"
            );

            System.out.println(
                    listaProiezioni.get(i)
            );
        }
    }

    /**
 * Cerca una proiezione in base al titolo del film.
 *
 * @param scanner scanner utilizzato per leggere l'input
 */
    // CERCA FILM
    public void cercaProiezioni(
            Scanner scanner) {

        System.out.print(
                "Titolo film: "
        );

        String ricerca =
                scanner.nextLine();

        boolean trovato = false;

        for (Proiezione p
                : listaProiezioni) {

            if (p.getFilm()
                    .getTitolo()
                    .toLowerCase()
                    .contains(
                            ricerca.toLowerCase()
                    )) {

                System.out.println(p);

                trovato = true;
            }
        }

        if (!trovato) {

            System.out.println(
                    "Nessun film trovato."
            );
        }
    }

    /**
 * Permette all'utente di selezionare una proiezione.
 *
 * @param scanner scanner utilizzato per leggere l'input
 * @return la proiezione selezionata oppure {@code null}
 *         se la scelta non è valida
 */
    // SELEZIONA PROIEZIONE
    public Proiezione selezionaProiezione(
            Scanner scanner) {

        mostraProiezioni();

        int scelta =
                leggiIntero(
                        scanner,
                        "\nSeleziona proiezione: ",
                        0,
                        listaProiezioni.size() - 1
                );

        if (scelta >= 0 &&
                scelta < listaProiezioni.size()) {

            return listaProiezioni.get(
                    scelta
            );
        }

        System.out.println(
                "Scelta non valida."
        );

        return null;
    }

    /**
 * Restituisce l'elenco delle proiezioni.
 *
 * @return lista delle proiezioni
 */
    public ArrayList<Proiezione> getListaProiezioni() {
        return listaProiezioni;
    }

    /**
 * Legge un numero intero verificando che appartenga
 * all'intervallo specificato.
 *
 * @param scanner scanner utilizzato per leggere l'input
 * @param prompt messaggio mostrato all'utente
 * @param min valore minimo consentito
 * @param max valore massimo consentito
 * @return numero intero valido
 */
    private int leggiIntero(
            Scanner scanner,
            String prompt,
            int min,
            int max) {

        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();
            try {
                int value = Integer.parseInt(line);
                if (value >= min && value <= max) {
                    return value;
                }
                System.out.println(
                        "Inserisci un numero tra " +
                                min + " e " + max + "."
                );
            } catch (NumberFormatException e) {
                System.out.println(
                        "Inserisci un numero valido."
                );
            }
        }
    }
}
