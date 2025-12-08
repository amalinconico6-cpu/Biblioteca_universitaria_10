/**
 * @file 
 * @brief
 * "Inserire qui descrizione specifica della classe"
 * @author
 * @date
 * @version
 */

package it.unisa.diem.ingsoft.biblioteca_universitaria_10.userinterface;

import it.unisa.diem.ingsoft.biblioteca_universitaria_10.prestiti.Prestito;
import it.unisa.diem.ingsoft.biblioteca_universitaria_10.utenti.Utente;

import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.time.LocalDate;

public class ControllerSchermataVisualizzaPrestito {

    private TableView<Prestito> tabellaPrestito;
    private TableColumn<Prestito, String> colonnaISBN;
    private TableColumn<Prestito, Utente> colonnaUtente;
    private TableColumn<Prestito, Integer> colonnaID;
    private TableColumn<Prestito, LocalDate> colonnaScadenza;
    private TableColumn<Prestito, Void> colonnaElimina;
    private TableColumn<Prestito, Void> colonnaModifica;

    private Button btnIndietro;
    private Button btnRicerca;
/**
 * @brief "inserire qui descrizione breve"
 * "inserire qui descrizione specifica del metodo"
 * @pre
 * @post
 * @param[in]
 * @param[out]
 * @author
 */
    public void init() {
        // vuoto
    }
/**
 * @brief "inserire qui descrizione breve"
 * "inserire qui descrizione specifica del metodo"
 * @pre
 * @post
 * @param[in]
 * @param[out]
 * @author
 */
    public void aggiungiBtnModifica() {
        // vuoto
    }
/**
 * @brief "inserire qui descrizione breve"
 * "inserire qui descrizione specifica del metodo"
 * @pre
 * @post
 * @param[in]
 * @param[out]
 * @author
 */
    public void aggiungiBtnElimina() {
        // vuoto
    }
/**
 * @brief "inserire qui descrizione breve"
 * "inserire qui descrizione specifica del metodo"
 * @pre
 * @post
 * @param[in]
 * @param[out]
 * @author
 */
    public void cerca() {
        // vuoto
    }
/**
 * @brief "inserire qui descrizione breve"
 * "inserire qui descrizione specifica del metodo"
 * @pre
 * @post
 * @param[in]
 * @param[out]
 * @author
 */
    public void tornaIndietro() {
        // vuoto
    }
}
