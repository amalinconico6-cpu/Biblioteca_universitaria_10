
/**
 * @file ControllerListaLibri.java
 * @brief Controller JavaFX per la gestione e visualizzazione della lista dei libri
 * @author SAMUELE TORTORA
 */


package it.unisa.bibliotecajfx.controller;

import it.unisa.bibliotecajfx.model.biblioteca.Libro;
import it.unisa.bibliotecajfx.model.biblioteca.Lista_Libri;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class ControllerListaLibri {
     private Lista_Libri listalibri;
      final String FILE_LIBRI = "FileEsternoLibri.bin";
      
      
/*
 * @brief Imposta la lista dei libri da visualizzare
 * @param listalibri 
 * @pre listalibri!=NULL
 * @post La tabella contiene tutti i libri della lista
 */
     public void setListaLibri(Lista_Libri listalibri) {
    this.listalibri = listalibri;
    tabellaLibri.getItems().setAll(listalibri.getLibri());
}

/**
 * @brief Tabella per la visualizzazione dei libri  
 */
     @FXML
    private TableView<Libro> tabellaLibri;
     
/**
 * @brief Pulsante per la modifica del libro selezionato
 */
 @FXML
 private Button btnModificaLibro; 
 
/**
 * @brief Pulsante per la rimozione del libro selezionato
 */
 @FXML
 private Button btnRimuoviLibro; 
 
/**
 * @brief Pulsante per la chiusura della finestra
 */ 
  @FXML
  private Button btnChiudi; 
  
/**
 * @brief Colonna del titolo del libro
 */ 
  @FXML
private TableColumn<Libro, String> colTitolo;
  
/**
 * @brief Colonna degli autori del libro
 */
   @FXML
private TableColumn<Libro, String> colAutore;
   
/**
 * @brief Colonna dell'ISBN del libro
 */
@FXML
private TableColumn<Libro, String> colISBN;

/**
 * @brief Colonna dell'anno di pubblicazione
 */
@FXML
private TableColumn<Libro, Integer> colAnno;

/**
 * @brief Colonna del numero di copie disponibili
 */
@FXML
private TableColumn<Libro, Integer> colCopie;


 /**
  * @brief Metodo di inizializzazione del controller
  * Collega le colonne della TableView agli
  * attributi della classe Libro.
  * @post La tabella è pronta 
  */
@FXML
private void initialize() {
    // Collego le colonne ai campi di Libro (titolo, ISBN, anno, copie)
    colTitolo.setCellValueFactory(new PropertyValueFactory<>("titolo"));
    
    colAutore.setCellValueFactory(cd -> {
        Libro libro = cd.getValue();

        if (libro == null || libro.getAutori() == null || libro.getAutori().isEmpty()) {
            return new javafx.beans.property.SimpleStringProperty("");
        }

        String autoriString = libro.getAutori().stream()
            .filter(a -> a != null)
            .map(a -> {
                String nome = a.getNome() != null ? a.getNome() : "";
                String cognome = a.getCognome() != null ? a.getCognome() : "";
                return (nome + " " + cognome).trim();
            })
            .filter(s -> !s.isEmpty())
            .collect(java.util.stream.Collectors.joining(", "));

        return new javafx.beans.property.SimpleStringProperty(autoriString);
    });
    
    colISBN.setCellValueFactory(new PropertyValueFactory<>("ISBN"));
    colAnno.setCellValueFactory(new PropertyValueFactory<>("anno"));
    colCopie.setCellValueFactory(new PropertyValueFactory<>("copie"));
  
   
}


 /**
     * @brief Gestisce la modifica di un libro
     * Apre una nuova finestra che consente
     * la modifica del libro selezionato.
     * @param event
     * @pre Un libro deve essere selezionato
     * @post I dati del libro possono essere aggiornati
     */
   @FXML
private void onModificaLibro(ActionEvent event) {
    Libro selezionato = tabellaLibri.getSelectionModel().getSelectedItem();
    if (selezionato == null) {
        return;
    }

    try {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/ModificaLibro.fxml")
        );
        Parent root = loader.load();

        ControllerModificaLibro controller = loader.getController();
        controller.setListaLibri(listalibri);
        controller.setLibro(selezionato);   // <-- PASSO IL LIBRO

        Stage stage = new Stage();
        stage.setTitle("Modifica il libro");
        stage.setScene(new Scene(root));
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(btnModificaLibro.getScene().getWindow());
        stage.showAndWait();  // aspetto che chiuda

        // dopo la modifica, aggiorno la tabella
        tabellaLibri.refresh();

    } catch (IOException e) {
        e.printStackTrace();
    }
}

 /**
     * @brief Gestisce la rimozione di un libro
     * Rimuove il libro selezionato dalla lista,
     * aggiorna il file di salvataggio e la tabella.
     * @param event 
     * @throws IOException in caso di errore durante il salvataggio
     * @pre Un libro deve essere selezionato
     * @post Il libro viene rimosso dalla lista
     */
    @FXML
private void onRimuoviLibro(ActionEvent event) throws IOException {
    Libro selezionato = tabellaLibri.getSelectionModel().getSelectedItem();
    if (selezionato == null) {
         ControllerPopup.showError(tabellaLibri.getScene().getWindow(), "Seleziona un libro.");
        return;
    }
    
     Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Conferma eliminazione");
    alert.setHeaderText(null);
    alert.setContentText(
    "Sei sicuro di voler rimuovere il libro: " + selezionato.getTitolo() + " ?"
);


    Optional<ButtonType> res = alert.showAndWait();
    if (!res.isPresent() || res.get() != ButtonType.OK) return;

    listalibri.rimuoviLibro(selezionato);
    listalibri.salvataggioLibri(FILE_LIBRI);
    tabellaLibri.getItems().remove(selezionato);
}

 /**
     * @brief Mostra una lista di libri nella tabella
     * @param libri 
     * @pre libri != null
     * @post La tabella mostra i libri 
     */    
    public void mostraLibri(List<Libro> libri) {
    tabellaLibri.getItems().setAll(libri);
}
    
/**
 * @brief chiude la finestra corrente
 */ 
    @FXML
    private void handleChiudi() {
        Stage stage = (Stage) tabellaLibri.getScene().getWindow();
        stage.close();
    }
}
