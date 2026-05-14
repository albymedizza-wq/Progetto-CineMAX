package cinemax.service;

import java.util.ArrayList;
import java.util.UUID;

import cinemax.model.Prenotazione;
import cinemax.model.Proiezione;

public class PrenotazioneService {

    private ArrayList<Prenotazione>
            prenotazioni;

    public PrenotazioneService() {

        prenotazioni =
                new ArrayList<>();
    }

    public void creaPrenotazione(
            String username,
            Proiezione proiezione,
            int biglietti) {

        String codice =
                UUID.randomUUID()
                        .toString();

        Prenotazione prenotazione =
                new Prenotazione(
                        codice,
                        username,
                        proiezione,
                        biglietti
                );

        prenotazioni.add(prenotazione);

        System.out.println(
                "Prenotazione effettuata."
        );

        System.out.println(
                "Codice: " + codice
        );
    }
}