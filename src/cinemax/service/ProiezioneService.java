package cinemax.service;

import java.util.ArrayList;
import java.util.Scanner;

import cinemax.model.Proiezione;

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

    public void cercaProiezioni(
            Scanner scanner) {

        System.out.print(
                "Inserisci titolo film: ");

        String ricerca =
                scanner.nextLine();

        boolean trovato = false;

        for (Proiezione p : listaProiezioni) {

            if (p.getFilm()
                    .getTitolo()
                    .toLowerCase()
                    .contains(
                            ricerca.toLowerCase())) {

                System.out.println(p);

                trovato = true;
            }
        }

        if (!trovato) {

            System.out.println(
                    "Nessuna proiezione trovata."
            );
        }
    }
}