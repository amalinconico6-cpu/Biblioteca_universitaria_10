/**
 * @file ControllerModificaPrestito.java
 * @brief Controller JavaFX per la modifica di un prestito.
 *
 * Questa classe gestisce la finestra "Modifica Prestito".
 * carica nei campi della form i dati del prestito selezionato (ISBN, matricola, data scadenza),
 * valida i nuovi valori inseriti dall'utente,
 * aggiorna il prestito nella ListaPrestiti,
 * gestisce correttamente le copie dei libri quando cambia l'ISBN del prestito:
 * restituisce 1 copia al vecchio libro,
 * scala 1 copia dal nuovo libro (solo se disponibile),
 * salva i prestiti su file (sempre) e i libri su file (solo se le copie sono state modificate),
 * esegue rollback delle copie in caso di fallimento della modifica,
 * chiude la finestra (annulla o dopo salvataggio).

 * @author SAMUELE TORTORA
 */

package it.unisa.bibliotecajfx.controller;

import it.unisa.bibliotecajfx.model.utenti.Lista_Utenti;
import it.unisa.bibliotecajfx.model.prestiti.Prestito;
import it.unisa.bibliotecajfx.model.prestiti.Lista_Prestiti;
import it.unisa.bibliotecajfx.model.biblioteca.Lista_Libri;
import it.unisa.bibliotecajfx.model.biblioteca.Libro;
import it.unisa.bibliotecajfx.model.*;
import java.io.IOException;
import java.time.LocalDate;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class ControllerModificaPrestito {

    /**
     * @brief Nome del file per la persistenza dei prestiti
     */
    final String FILE_PRESTITI = "FileEsternoPrestiti.bin";

    /**
     * @brief Nome del file per la persistenza dei libri (copie)
     */
    final String FILE_LIBRI = "FileEsternoLibri.bin";

    /**
     * @brief Campo testo per l'ISBN del prestito
     */
    @FXML
    private TextField txtISBN;

    /**
     * @brief Campo testo per la matricola dell'utente
     */
    @FXML
    private TextField txtMatricola;

    /**
     * @brief DatePicker per selezionare la data di scadenza del prestito
     */
    @FXML
    private DatePicker dateScadenza;

    /**
     * @brief Modello: lista prestiti
     */
    private Lista_Prestiti listaPrestiti;

    /**
     * @brief Modello lista libri (necessaria per aggiornare le copie in caso di cambio ISBN)
     */
    private Lista_Libri listaLibri;

    /**
     * @brief Modello lista utenti (necessaria per verificare l'esistenza della matricola)
     */
    private Lista_Utenti listaUtenti;

    /**
     * @brief Prestito selezionato originario da modificare
     */
    private Prestito prestitoOriginale;

    /**
     * @brief Imposta i riferimenti alle liste usate dal controller
     *
     * @param lp lista prestiti
     * @param lu lista utenti
     * @param ll lista libri
     * @pre lp != null && lu != null && ll != null
     * @post Il controller può operare su prestiti, utenti e libri
     */
    public void setListe(Lista_Prestiti lp, Lista_Utenti lu, Lista_Libri ll) {
        this.listaPrestiti = lp;
        this.listaUtenti = lu;
        this.listaLibri = ll;
    }

    /**
     * @brief Imposta il prestito da modificare e popola i campi della form
     *
     * @param p prestito selezionato
     * @pre p != null
     * @post I campi txtISBN, txtMatricola e dateScadenza mostrano i dati correnti del prestito
     */
    public void setPrestitoDaModificare(Prestito p) {
        this.prestitoOriginale = p;
        txtISBN.setText(p.getISBN());
        txtMatricola.setText(String.valueOf(p.getMatricola()));
        dateScadenza.setValue(p.getDataScadenza());
    }

    /**
     * @brief Salva la modifica del prestito 
     *
     * Validazioni effettuate:
     * liste e prestito originale inizializzati
     * campi non vuoti
     * matricola solo numeri
     * utente esistente in ListaUtenti
     * se cambia ISBN: nuovo ISBN esistente in ListaLibri e con copie disponibili
     * se dati uguali all'originale: nessuna modifica (errore)
     *
     * Se cambia ISBN:
     * incrementa le copie del vecchio libro (+1)
     * decrementa le copie del nuovo libro (-1)
     *
     * Poi crea un nuovo oggetto Prestito e invoca la modifica nel modello.
     * Se la modifica va a buon fine:
     * salva prestiti su file
     * salva libri su file solo se le copie sono state modificate
     * Se la modifica fallisce:
     * esegue rollback delle copie (se modificate)
     * mostra popup di errore
     *
     * @throws IOException errore durante operazioni di I/O (salvataggio)
     * @pre prestitoOriginale != null && listaPrestiti != null && listaUtenti != null && listaLibri != null
     * @post Prestito aggiornato e salvato; copie aggiornate coerentemente se ISBN cambiato
     */
    @FXML
    private void onModificaPrestito() throws IOException {

        if (listaUtenti == null || listaLibri == null || listaPrestiti == null || prestitoOriginale == null) {
            ControllerPopup.showError(txtMatricola.getScene().getWindow(),
                    "Errore interno: liste non inizializzate");
            return;
        }

        String nuovoISBN = txtISBN.getText().trim();
        String nuovaMatricola = txtMatricola.getText().trim();
        LocalDate nuovaScadenza = dateScadenza.getValue();

        if (nuovoISBN.isEmpty() || nuovaMatricola.isEmpty() || nuovaScadenza == null) {
            ControllerPopup.showError(txtMatricola.getScene().getWindow(),
                    "Compila tutti i campi.");
            return;
        }

        if (!nuovaMatricola.matches("\\d+")) {
            ControllerPopup.showError(txtMatricola.getScene().getWindow(),
                    "Matricola non valida.");
            return;
        }

        // Dati identici (nessuna modifica)
        if (nuovoISBN.equals(prestitoOriginale.getISBN()) &&
                nuovaMatricola == prestitoOriginale.getMatricola() &&
                nuovaScadenza.equals(prestitoOriginale.getDataScadenza())) {

            ControllerPopup.showError(txtMatricola.getScene().getWindow(),
                    "Nessuna modifica effettuata.");
            return;
        }

        if (!listaUtenti.checkMatricola(String.valueOf(nuovaMatricola))) {
            ControllerPopup.showError(txtMatricola.getScene().getWindow(),
                    "Utente non esistente.");
            return;
        }

        String vecchioISBN = prestitoOriginale.getISBN();
        boolean copieModificate = false;

        // Se cambia ISBN -> gestisco copie
        if (!nuovoISBN.equals(vecchioISBN)) {

            if (!listaLibri.checkISBN(nuovoISBN)) {
                ControllerPopup.showError(txtMatricola.getScene().getWindow(),
                        "ISBN non trovato");
                return;
            }

            Libro libroNuovo = null;
            for (Libro l : listaLibri.getLibri()) {
                if (l != null && l.getISBN() != null && l.getISBN().equals(nuovoISBN)) {
                    libroNuovo = l;
                    break;
                }
            }

            if (libroNuovo == null) {
                ControllerPopup.showError(txtMatricola.getScene().getWindow(),
                        "ISBN non trovato");
                return;
            }

            if (libroNuovo.getCopie() <= 0) {
                ControllerPopup.showError(txtMatricola.getScene().getWindow(),
                        "Nessuna copia disponibile.");
                return;
            }

            Libro libroVecchio = null;
            for (Libro l : listaLibri.getLibri()) {
                if (l != null && l.getISBN() != null && l.getISBN().equals(vecchioISBN)) {
                    libroVecchio = l;
                    break;
                }
            }

            // aggiorno copie: restituisco al vecchio e tolgo al nuovo
            if (libroVecchio != null) {
                libroVecchio.setCopie(libroVecchio.getCopie() + 1);
            }

            libroNuovo.setCopie(libroNuovo.getCopie() - 1);
            copieModificate = true;
        }

        // Creo il nuovo prestito con i dati modificati
        Prestito nuovo = new Prestito(nuovoISBN, nuovaMatricola, nuovaScadenza);

        boolean ok = listaPrestiti.ModificaPrestito(prestitoOriginale, nuovo);

        if (ok) {
            listaPrestiti.OrdinaPerScadenza(prestitoOriginale.getComp());

            // salvo sempre i prestiti
            listaPrestiti.salvataggioPrestiti(FILE_PRESTITI);

            // salvo i libri solo se ho toccato copie
            if (copieModificate) {
                listaLibri.salvataggioLibri(FILE_LIBRI);
            }

            ControllerPopup.showSuccess(txtMatricola.getScene().getWindow(),
                    "Prestito modificato correttamente.");
            chiudi();

        } else {
            // rollback copie (non salvo)
            if (copieModificate) {

                Libro libroVecchio = null;
                for (Libro l : listaLibri.getLibri()) {
                    if (l != null && l.getISBN() != null && l.getISBN().equals(vecchioISBN)) {
                        libroVecchio = l;
                        break;
                    }
                }

                Libro libroNuovo = null;
                for (Libro l : listaLibri.getLibri()) {
                    if (l != null && l.getISBN() != null && l.getISBN().equals(nuovoISBN)) {
                        libroNuovo = l;
                        break;
                    }
                }

                if (libroVecchio != null) libroVecchio.setCopie(libroVecchio.getCopie() - 1);
                if (libroNuovo != null) libroNuovo.setCopie(libroNuovo.getCopie() + 1);
            }

            ControllerPopup.showError(txtMatricola.getScene().getWindow(),
                    "L'utente ha già 3 prestiti.");
        }
    }

    /**
     * @brief Annulla l'operazione e chiude la finestra
     */
    @FXML
    private void onCancella() {
        chiudi();
    }

    /**
     * @brief Chiude la finestra corrente
     */
    private void chiudi() {
        Stage stage = (Stage) txtISBN.getScene().getWindow();
        stage.close();
    }
}