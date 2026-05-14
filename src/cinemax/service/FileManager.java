package cinemax.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;

import cinemax.model.Film;
import cinemax.model.Proiezione;

public class FileManager {

    public ArrayList<Proiezione> caricaProiezioni(
            String nomeFile) {

        ArrayList<Proiezione> lista =
                new ArrayList<>();

        try {

            BufferedReader reader =
                    new BufferedReader(
                            new FileReader(nomeFile));

            String riga;

            reader.readLine();

            DateTimeFormatter formatter =
                    DateTimeFormatter.ofPattern(
                            "dd/MM/yyyy");

            while ((riga = reader.readLine()) != null) {

                String[] dati = riga.split(";");

                String titolo = dati[0];
                String genere = dati[1];
                String regista = dati[2];

                int anno =
                        Integer.parseInt(dati[3]);

                int durata =
                        Integer.parseInt(dati[4]);

                int etaMinima =
                        Integer.parseInt(dati[5]);

                LocalDate data =
                        LocalDate.parse(
                                dati[6],
                                formatter);

                LocalTime ora =
                        LocalTime.parse(dati[7]);

                double costo =
                        Double.parseDouble(dati[8]);

                Film film = new Film(
                        titolo,
                        genere,
                        regista,
                        anno,
                        durata,
                        etaMinima
                );

                Proiezione proiezione =
                        new Proiezione(
                                film,
                                data,
                                ora,
                                costo,
                                200
                        );

                lista.add(proiezione);
            }

            reader.close();

        } catch (IOException e) {

            System.out.println(
                    "Errore file: " +
                    e.getMessage()
            );
        }

        return lista;
    }
}