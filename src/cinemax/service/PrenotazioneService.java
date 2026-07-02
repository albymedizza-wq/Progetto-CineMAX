package cinemax.service;

import cinemax.model.Posto;
import cinemax.model.Prenotazione;
import cinemax.model.Proiezione;
import cinemax.model.Utente;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

/**
 * Gestisce le operazioni relative alle prenotazioni.
 * <p>
 * Permette ai clienti di creare, visualizzare ed eliminare
 * le proprie prenotazioni, aggiornando i dati memorizzati
 * nel sistema.
 *
 * @author Davide Gallorini
 * @author Guidi Lorenzo
 * @author Alberto Medizza
 */
public class PrenotazioneService {

        /**
 * Percorso del file contenente le prenotazioni.
 */
    private static final String PRENOTAZIONI_FILE =
            "data/prenotazioni.txt";

/**
 * Gestisce la lettura e la scrittura delle prenotazioni su file.
 */
    private final FileManager fileManager =
            new FileManager();

            /**
 * Elenco delle prenotazioni presenti nel sistema.
 */
    private final ArrayList<Prenotazione> prenotazioni;

    /**
 * Inizializza il servizio caricando le prenotazioni
 * presenti nel file.
 *
 * @param proiezioneService servizio utilizzato per
 *                          recuperare le proiezioni
 */
    public PrenotazioneService(
            ProiezioneService proiezioneService) {
        prenotazioni =
                fileManager.caricaPrenotazioni(
                        PRENOTAZIONI_FILE,
                        proiezioneService
                                .getListaProiezioni()
                );
    }

    /**
 * Crea una nuova prenotazione per una proiezione.
 * <p>
 * L'utente può scegliere i posti desiderati e,
 * al termine dell'operazione, la prenotazione
 * viene salvata nel file.
 *
 * @param scanner scanner utilizzato per leggere l'input
 * @param utente utente che effettua la prenotazione
 * @param proiezione proiezione selezionata
 */
    public void creaPrenotazione(
            Scanner scanner,
            Utente utente,
            Proiezione proiezione) {
        if (utente == null) {
            System.out.println(
                    "Devi effettuare il login prima di prenotare."
            );
            return;
        }

        if (!"cliente".equalsIgnoreCase(
                utente.getRuolo())) {
            System.out.println(
                    "Solo i clienti possono effettuare prenotazioni."
            );
            return;
        }

        int numeroPosti =
                leggiIntero(
                        scanner,
                        "Quanti posti vuoi prenotare? ",
                        1,
                        200
                );

        String codice =
                UUID.randomUUID()
                        .toString();
        Prenotazione prenotazione =
                new Prenotazione(
                        codice,
                        utente.getUsername(),
                        proiezione
                );

        for (int i = 0;
             i < numeroPosti;
             i++) {

            proiezione.mostraSala();

            System.out.println(
                    "\nPOSTO " + (i + 1)
            );

            int riga =
                    leggiIntero(
                            scanner,
                            "Riga (0-9): ",
                            0,
                            9
                    );

            int colonna =
                    leggiIntero(
                            scanner,
                            "Colonna (0-19): ",
                            0,
                            19
                    );

            boolean successo =
                    proiezione.prenotaPosto(
                            riga,
                            colonna
                    );

            if (successo) {
                Posto posto =
                        new Posto(
                                riga,
                                colonna
                        );
                prenotazione
                        .aggiungiPosto(
                                posto
                        );
                System.out.println(
                        "Posto prenotato!"
                );
            } else {
                System.out.println(
                        "Posto già occupato!"
                );
                i--;
            }
        }

        double totale =
                prenotazione
                        .getPostiPrenotati()
                        .size() *
                        proiezione.getCosto();
        prenotazione.setCostoTotale(totale);
        prenotazioni.add(prenotazione);

        boolean salvato =
                fileManager.salvaPrenotazione(
                        PRENOTAZIONI_FILE,
                        prenotazione
                );

        if (!salvato) {
            System.out.println(
                    "Errore durante il salvataggio della prenotazione."
            );
        }

        System.out.println(
                "\nPrenotazione completata!"
        );

        System.out.println(
                "Spesa totale: Euro " +
                        String.format("%.2f", totale)
        );

        System.out.println(
                prenotazione
        );
    }

    /**
 * Visualizza tutte le prenotazioni appartenenti
 * all'utente autenticato.
 *
 * @param utente utente di cui mostrare le prenotazioni
 */
    public void visualizzaPrenotazioni(
            Utente utente) {
        if (utente == null) {
            System.out.println(
                    "Devi effettuare il login per vedere le tue prenotazioni."
            );
            return;
        }

        boolean trovato = false;

        for (Prenotazione p : prenotazioni) {
            if (p.getUsernameCliente()
                    .equals(utente.getUsername())) {
                System.out.println(p);
                trovato = true;
            }
        }

        if (!trovato) {
            System.out.println(
                    "Non hai ancora prenotazioni."
            );
        }
    }

    /**
 * Elimina una prenotazione dell'utente autenticato.
 * <p>
 * I posti prenotati vengono nuovamente resi disponibili
 * e il file delle prenotazioni viene aggiornato.
 *
 * @param scanner scanner utilizzato per leggere l'input
 * @param utente utente che richiede l'eliminazione
 */
    public void eliminaPrenotazione(
            Scanner scanner,
            Utente utente) {
        if (utente == null) {
            System.out.println(
                    "Devi effettuare il login prima di eliminare una prenotazione."
            );
            return;
        }

        System.out.print(
                "Inserisci codice prenotazione: "
        );

        String codice =
                scanner.nextLine();

        Prenotazione daEliminare = null;

        for (Prenotazione p : prenotazioni) {
            if (p.getCodice().equals(codice) &&
                    p.getUsernameCliente()
                            .equals(utente.getUsername())) {
                daEliminare = p;
                break;
            }
        }

        if (daEliminare != null) {
            for (Posto posto :
                    daEliminare
                            .getPostiPrenotati()) {
                daEliminare
                        .getProiezione()
                        .liberaPosto(
                                posto.getRiga(),
                                posto.getColonna()
                        );
            }
            prenotazioni.remove(daEliminare);
            boolean riscritto =
                    fileManager.riscriviPrenotazioni(
                            PRENOTAZIONI_FILE,
                            prenotazioni
                    );

            if (riscritto) {
                System.out.println(
                        "Prenotazione eliminata."
                );
            } else {
                System.out.println(
                        "Errore durante l'eliminazione della prenotazione."
                );
            }
        } else {
            System.out.println(
                    "Prenotazione non trovata o non appartiene all'utente."
            );
        }
    }

    /**
 * Legge un numero intero controllando che appartenga
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
