package cinemax.service;

import cinemax.model.Bigliettaio;
import cinemax.model.Cliente;
import cinemax.model.Film;
import cinemax.model.Posto;
import cinemax.model.Prenotazione;
import cinemax.model.Proiezione;
import cinemax.model.Proiezionista;
import cinemax.model.Utente;
import cinemax.utils.PasswordUtils;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class FileManager {

    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    public ArrayList<Proiezione>
    caricaProiezioni(String nomeFile) {

        ArrayList<Proiezione> lista =
                new ArrayList<>();

        try (BufferedReader reader =
                     new BufferedReader(
                             new FileReader(nomeFile))) {

            String riga;

            // Salta intestazione
            reader.readLine();

            while ((riga = reader.readLine()) != null) {

                if (riga.trim().isEmpty()) {
                    continue;
                }

                String[] dati = parseCsvLine(riga);

                if (dati.length < 8) {
                    continue;
                }

                try {
                    String dataOraString =
                            stripQuotes(dati[0].trim());
                    String titolo =
                            stripQuotes(dati[1].trim());
                    String genere =
                            stripQuotes(dati[2].trim());
                    String regista =
                            stripQuotes(dati[3].trim());
                    int anno =
                            Integer.parseInt(
                                    stripQuotes(dati[4].trim())
                            );
                    int durata =
                            Integer.parseInt(
                                    stripQuotes(dati[5].trim())
                            );
                    int etaMinima =
                            Integer.parseInt(
                                    stripQuotes(dati[6].trim())
                            );
                    double costo =
                            Double.parseDouble(
                                    stripQuotes(dati[7].trim())
                            );

                    LocalDateTime dateTime =
                            LocalDateTime.parse(
                                    dataOraString,
                                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                            );
                    LocalDate data = dateTime.toLocalDate();
                    LocalTime ora = dateTime.toLocalTime();

                    Film film =
                            new Film(
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
                } catch (Exception e) {
                    System.out.println(
                            "Errore conversione proiezione: "
                                    + e.getMessage()
                    );
                }
            }

        } catch (IOException e) {
            System.out.println(
                    "Errore lettura file: "
                            + e.getMessage()
            );
        }

        return lista;
    }

    public Utente caricaUtente(
            String nomeFile,
            String username) {

        try (BufferedReader reader =
                     new BufferedReader(
                             new FileReader(nomeFile))) {

            String riga;

            while ((riga = reader.readLine()) != null) {
                if (riga.trim().isEmpty()) {
                    continue;
                }

                String[] dati = riga.split(";");
                if (dati.length < 4) {
                    continue;
                }

                String storedUsername =
                        stripQuotes(dati[2].trim());
                if (!storedUsername.equals(username)) {
                    continue;
                }

                String nome =
                        stripQuotes(dati[0].trim());
                String cognome =
                        stripQuotes(dati[1].trim());
                String password =
                        stripQuotes(dati[3].trim());
                String dataNascita =
                        dati.length > 4 ?
                                stripQuotes(dati[4].trim()) : "";
                String luogoNascita =
                        dati.length > 5 ?
                                stripQuotes(dati[5].trim()) : "";
                String ruolo =
                        dati.length > 6 ?
                                stripQuotes(dati[6].trim()) : "cliente";

                return createUtente(
                        nome,
                        cognome,
                        storedUsername,
                        password,
                        dataNascita,
                        luogoNascita,
                        ruolo
                );
            }

        } catch (IOException e) {
            System.out.println(
                    "Errore lettura file utenti: "
                            + e.getMessage()
            );
        }

        return null;
    }

    public boolean salvaUtente(
            String nomeFile,
            String nome,
            String cognome,
            String username,
            String password,
            String dataNascita,
            String luogoNascita,
            String ruolo) {

        String passwordHash =
                PasswordUtils.hashPassword(password);
        if (passwordHash == null) {
            System.out.println(
                    "Errore durante la cifratura della password."
            );
            return false;
        }

        try {
            File file = new File(nomeFile);
            File parent = file.getParentFile();

            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }

            if (file.exists() && file.length() > 0) {
                try (RandomAccessFile raf =
                             new RandomAccessFile(file, "r")) {
                    raf.seek(file.length() - 1);
                    int lastByte = raf.read();
                    if (lastByte != '\n' && lastByte != '\r') {
                        try (FileWriter fw = new FileWriter(file, true);
                             BufferedWriter bw =
                                     new BufferedWriter(fw);
                             PrintWriter pw = new PrintWriter(bw)) {
                            pw.println();
                        }
                    }
                }
            }

            try (FileWriter fw = new FileWriter(file, true);
                 BufferedWriter bw = new BufferedWriter(fw);
                 PrintWriter pw = new PrintWriter(bw)) {

                pw.println(
                        nome + ";" +
                                cognome + ";" +
                                username + ";" +
                                passwordHash + ";" +
                                dataNascita + ";" +
                                luogoNascita + ";" +
                                ruolo
                );
            }

            return true;

        } catch (IOException e) {
            System.out.println(
                    "Errore salvataggio utente: "
                            + e.getMessage()
            );
            return false;
        }
    }

    public boolean esisteUsername(
            String nomeFile,
            String username) {

        try (BufferedReader reader =
                     new BufferedReader(
                             new FileReader(nomeFile))) {

            String riga;
            while ((riga = reader.readLine()) != null) {
                if (riga.trim().isEmpty()) {
                    continue;
                }
                String[] dati = riga.split(";");
                if (dati.length >= 3 &&
                        stripQuotes(dati[2].trim())
                                .equals(username)) {
                    return true;
                }
            }

        } catch (IOException e) {
            System.out.println(
                    "Errore lettura file utenti: "
                            + e.getMessage()
            );
        }

        return false;
    }

    public boolean verificaCredenziali(
            String nomeFile,
            String username,
            String password) {

        String passwordHash =
                PasswordUtils.hashPassword(password);
        try (BufferedReader reader =
                     new BufferedReader(
                             new FileReader(nomeFile))) {

            String riga;
            while ((riga = reader.readLine()) != null) {
                if (riga.trim().isEmpty()) {
                    continue;
                }
                String[] dati = riga.split(";");
                if (dati.length >= 4 &&
                        stripQuotes(dati[2].trim())
                                .equals(username)) {
                    String storedPassword =
                            stripQuotes(dati[3].trim());
                    if (storedPassword.equals(password) ||
                            (passwordHash != null &&
                                    storedPassword.equals(passwordHash))) {
                        return true;
                    }
                }
            }

        } catch (IOException e) {
            System.out.println(
                    "Errore lettura file utenti: "
                            + e.getMessage()
            );
        }

        return false;
    }

    public ArrayList<Prenotazione>
    caricaPrenotazioni(
            String nomeFile,
            ArrayList<Proiezione> proiezioni) {

        ArrayList<Prenotazione> prenotazioni =
                new ArrayList<>();

        try (BufferedReader reader =
                     new BufferedReader(
                             new FileReader(nomeFile))) {

            String riga;
            while ((riga = reader.readLine()) != null) {
                if (riga.trim().isEmpty()) {
                    continue;
                }

                String[] dati = riga.split(";");
                if (dati.length < 7) {
                    continue;
                }

                try {
                    String codice =
                            stripQuotes(dati[0].trim());
                    String username =
                            stripQuotes(dati[1].trim());
                    String titolo =
                            stripQuotes(dati[2].trim());
                    LocalDate data =
                            LocalDate.parse(
                                    stripQuotes(dati[3].trim()),
                                    DATE_FORMATTER
                            );
                    LocalTime ora =
                            LocalTime.parse(
                                    stripQuotes(dati[4].trim())
                            );
                    String postiString =
                            stripQuotes(dati[5].trim());
                    double totale =
                            Double.parseDouble(
                                    stripQuotes(dati[6].trim())
                            );
                    LocalDateTime dataAcquisto =
                            dati.length > 7 ?
                                    LocalDateTime.parse(
                                            stripQuotes(dati[7].trim()),
                                            DATE_TIME_FORMATTER
                                    ) : LocalDateTime.now();

                    Proiezione proiezione =
                            trovaProiezione(
                                    proiezioni,
                                    titolo,
                                    data,
                                    ora
                            );

                    if (proiezione == null) {
                        continue;
                    }

                    Prenotazione prenotazione =
                            new Prenotazione(
                                    codice,
                                    username,
                                    proiezione,
                                    dataAcquisto,
                                    totale
                            );

                    if (!postiString.isBlank()) {
                        String[] posti =
                                postiString.split("\\|");
                        for (String seat : posti) {
                            if (seat.trim().isEmpty()) {
                                continue;
                            }
                            int rigaPosto =
                                    Character.toUpperCase(
                                                    seat.charAt(0)) -
                                            'A';
                            int colonnaPosto =
                                    Integer.parseInt(
                                                    seat.substring(1)) -
                                            1;
                            if (rigaPosto >= 0 &&
                                    rigaPosto < 10 &&
                                    colonnaPosto >= 0 &&
                                    colonnaPosto < 20) {

                                if (proiezione.prenotaPosto(
                                        rigaPosto,
                                        colonnaPosto)) {
                                    prenotazione
                                            .aggiungiPosto(
                                                    new Posto(
                                                            rigaPosto,
                                                            colonnaPosto
                                                    )
                                            );
                                }
                            }
                        }
                    }

                    prenotazioni.add(prenotazione);
                } catch (Exception e) {
                    System.out.println(
                            "Errore lettura prenotazione: "
                                    + e.getMessage()
                    );
                }
            }

        } catch (IOException e) {
            System.out.println(
                    "Errore caricamento prenotazioni: "
                            + e.getMessage()
            );
        }

        return prenotazioni;
    }

    public boolean salvaPrenotazione(
            String nomeFile,
            Prenotazione prenotazione) {

        try {
            File file = new File(nomeFile);
            File parent = file.getParentFile();
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }

            if (file.exists() && file.length() > 0) {
                try (RandomAccessFile raf =
                             new RandomAccessFile(file, "r")) {
                    raf.seek(file.length() - 1);
                    int lastByte = raf.read();
                    if (lastByte != '\n' && lastByte != '\r') {
                        try (FileWriter fw = new FileWriter(file, true);
                             BufferedWriter bw =
                                     new BufferedWriter(fw);
                             PrintWriter pw = new PrintWriter(bw)) {
                            pw.println();
                        }
                    }
                }
            }

            try (FileWriter fw = new FileWriter(file, true);
                 BufferedWriter bw = new BufferedWriter(fw);
                 PrintWriter pw = new PrintWriter(bw)) {

                pw.println(
                        prenotazione.getCodice() + ";" +
                                prenotazione.getUsernameCliente() + ";" +
                                prenotazione.getProiezione()
                                        .getFilm()
                                        .getTitolo() + ";" +
                                prenotazione.getProiezione()
                                        .getData()
                                        .format(DATE_FORMATTER) + ";" +
                                prenotazione.getProiezione()
                                        .getOra() + ";" +
                                stringifyPosti(
                                        prenotazione
                                                .getPostiPrenotati()
                                ) + ";" +
                                String.format(
                                        "%.2f",
                                        prenotazione.getCostoTotale()
                                ) + ";" +
                                prenotazione.getDataAcquisto()
                                        .format(DATE_TIME_FORMATTER)
                );
            }

            return true;
        } catch (IOException e) {
            System.out.println(
                    "Errore salvataggio prenotazione: "
                            + e.getMessage()
            );
            return false;
        }
    }

    public boolean riscriviPrenotazioni(
            String nomeFile,
            ArrayList<Prenotazione> prenotazioni) {

        try {
            File file = new File(nomeFile);
            File parent = file.getParentFile();
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }

            try (FileWriter fw = new FileWriter(file, false);
                 BufferedWriter bw = new BufferedWriter(fw);
                 PrintWriter pw = new PrintWriter(bw)) {
                for (Prenotazione prenotazione : prenotazioni) {
                    pw.println(
                            prenotazione.getCodice() + ";" +
                                    prenotazione.getUsernameCliente() + ";" +
                                    prenotazione.getProiezione()
                                            .getFilm()
                                            .getTitolo() + ";" +
                                    prenotazione.getProiezione()
                                            .getData()
                                            .format(DATE_FORMATTER) + ";" +
                                    prenotazione.getProiezione()
                                            .getOra() + ";" +
                                    stringifyPosti(
                                            prenotazione
                                                    .getPostiPrenotati()
                                    ) + ";" +
                                    String.format(
                                            "%.2f",
                                            prenotazione.getCostoTotale()
                                    ) + ";" +
                                    prenotazione.getDataAcquisto()
                                            .format(DATE_TIME_FORMATTER)
                    );
                }
            }

            return true;
        } catch (IOException e) {
            System.out.println(
                    "Errore riscrittura prenotazioni: "
                            + e.getMessage()
            );
            return false;
        }
    }

    private Utente createUtente(
            String nome,
            String cognome,
            String username,
            String password,
            String dataNascita,
            String luogoNascita,
            String ruolo) {

        switch (ruolo.toLowerCase()) {
            case "cliente":
                return new Cliente(
                        nome,
                        cognome,
                        username,
                        password,
                        dataNascita,
                        luogoNascita
                );
            case "proiezionista":
                return new Proiezionista(
                        nome,
                        cognome,
                        username,
                        password,
                        dataNascita,
                        luogoNascita
                );
            case "bigliettaio":
                return new Bigliettaio(
                        nome,
                        cognome,
                        username,
                        password,
                        dataNascita,
                        luogoNascita
                );
            default:
                return new Utente(
                        nome,
                        cognome,
                        username,
                        password,
                        dataNascita,
                        luogoNascita,
                        ruolo
                ) {
                };
        }
    }

    private Proiezione trovaProiezione(
            ArrayList<Proiezione> proiezioni,
            String titolo,
            LocalDate data,
            LocalTime ora) {

        for (Proiezione p : proiezioni) {
            if (p.getFilm().getTitolo().equalsIgnoreCase(titolo) &&
                    p.getData().equals(data) &&
                    p.getOra().equals(ora)) {
                return p;
            }
        }

        return null;
    }

    private String[] parseCsvLine(String line) {
        ArrayList<String> fields = new ArrayList<>();
        StringBuilder current = new StringBuilder();
        boolean inQuotes = false;

        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (c == '"') {
                inQuotes = !inQuotes;
                continue;
            }
            if (c == ',' && !inQuotes) {
                fields.add(current.toString());
                current.setLength(0);
            } else {
                current.append(c);
            }
        }

        fields.add(current.toString());
        return fields.toArray(new String[0]);
    }

    private String stringifyPosti(
            ArrayList<Posto> posti) {

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < posti.size(); i++) {
            sb.append(posti.get(i).toString());
            if (i < posti.size() - 1) {
                sb.append("|");
            }
        }
        return sb.toString();
    }

    private String stripQuotes(String value) {
        if (value.startsWith("\"") && value.endsWith("\"")) {
            return value.substring(1, value.length() - 1);
        }
        return value;
    }
}

