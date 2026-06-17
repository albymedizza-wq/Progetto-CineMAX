package cinemax.service;

import cinemax.model.Proiezione;
import java.util.ArrayList;
import java.util.Scanner;

public class ProiezioneService {

    private ArrayList<Proiezione>
            listaProiezioni;

    public ProiezioneService() {

        FileManager fileManager =
                new FileManager();

        listaProiezioni =
                fileManager.caricaProiezioni(
                        "data/proiezioni.csv"
                );
    }

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

    public ArrayList<Proiezione> getListaProiezioni() {
        return listaProiezioni;
    }

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
