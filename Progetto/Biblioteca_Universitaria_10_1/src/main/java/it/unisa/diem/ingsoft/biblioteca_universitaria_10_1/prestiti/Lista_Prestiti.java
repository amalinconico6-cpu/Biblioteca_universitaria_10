/**
 * @file Lista_Prestiti
 * @brief Gestisce una list di prestiti
 * "Contiene funzioni di aggiunta,rimozione,modifica,ricerca e ordinamento di una lista di oggetti della classe Prestito"
 * @author ALESSANDRO VISCIANO
 */

package it.unisa.diem.ingsoft.biblioteca_universitaria_10_1.prestiti;
import it.unisa.diem.ingsoft.biblioteca_universitaria_10_1.biblioteca.*;
import it.unisa.diem.ingsoft.biblioteca_universitaria_10_1.utenti.*;

import java.util.Comparator;
import java.util.*;
import java.io.*;
import java.time.LocalDate;


public class Lista_Prestiti {

    private List<Prestito> prestiti=new ArrayList<>();
/**
 * @brief "get di prestiti"
 * "restituisce la lista prestiti"
 * @post Viene restuito List<Prestito> prestiti
 * @return List<Prestito> prestiti
 * @author ALESSANDRO VISCIANO
 */
    public List<Prestito> getPrestiti(){
        return this.prestiti;
    }
/**
 * @brief "Aggiunta di un prestito"
 * "esegue la add su List<Prestito> prestiti aggiungendo un nuovo elemento"
 * @pre Il parametro p deve superare i vari controlli sul formato dei suoi attributi e verificare che l'ISBN e la Matricola inseriti siano registrati nelle apposite liste
 * @post Se la matricola inserita nel parametro p compare già 3 volte nella List<Prestito> prestiti, ci si aspetta un return false, altrimenti eseue la add, riordina la lista e restituisce true
 * @param[in] Prestito p
 * @return boolean true, boolean false
 * @author ALESSANDRO VISCIANO
 */
    public boolean aggiungiPrestito(Prestito p){
        if(checkUtente(p))
            return false;
        else {
               prestiti.add(p);
               OrdinaPerScadenza(p.getComp());
               return true;
            }
    }
/**
 * @brief "Rimozione di un prestito (restituzione di un libro)"
 * "inserire qui descrizione specifica del metodo"
 * @post il prestito passato come parametro viene rimosso da List<Prestito> prestiti se contenuto nella lista, in tal caso ci si aspetta un return true, altrimenti false
 * @param[in] Prestito p
 * @return boolean true, boolean false
 * @author ALESSANDRO VISCIANO
 */
    public boolean rimuoviPrestito(Prestito p){
        if(prestiti.contains(p)){
            prestiti.remove(p);
            return true;
        } 
        else{
            return false;
        }
    }
/**
 * @brief "Registrazione dei ritardi"
 * "Scorrendo List<Prestito> prestiti, viene inserito in List<Prestito> ritardi ogni elemento il quale l'attributo dataScadenza risulta "maggiore" rispetto alla data odierna"
 * @pre Il metodo viene chiamato solo quando si vuole visualizzare la lista ritardi per un corretto e coerente funzionamento 
 * @post Ci si aspetta di ricevere una lista i quali elementi possiedono l'attributo dataScadenza "maggiore" rispetto alla data odierna
 * @return List<Prestito> ritardi
 * @author
 */
    public List<Prestito> aggiornaRitardi(){
        List<Prestito> ritardi=new ArrayList<>();
        LocalDate oggi=LocalDate.now();
        for(Prestito p:prestiti){
            if(p.getDataScadenza().isBefore(oggi))
                ritardi.add(p);
        }
        return ritardi;
    }
/**
 * @brief "set di prestiti"
 * "List<Prestito> prestiti attuale viene sostituito con List<Prestito> prestiti passato come parametro"
 * @post List<Prestito> prestiti attuale viene sostituito con List<Prestito> prestiti passato come parametro
 * @param[in] List<Prestito> prestiti
 * @author ALESSANDRO VISCIANO
 */
    public void setPrestiti(List<Prestito> prestiti){
        this.prestiti=prestiti;
    }
/**
 * @brief "Ricerca dei prestiti per ISBN"
 * "Scorrendo tutta la lista prestiti, vengono inseriti in List<Prestito> filter solo quei prestiti i quali l'attributo ISBN corrisponde con parametro String str"
 * @pre Il parametro String str deve rispettare il formato
 * @post Ci si apetta una lista che contenga solo i prestiti i quali l'attributo ISBN corrisponde con parametro String str
 * @param[in] String str
 * @return List<Prestito> filter
 * @author ALESSANDRO VISCIANO
 */
    public List<Prestito> cercaPerISBN(String str){
        List<Prestito> filter=new ArrayList<>();
        for(Prestito p:prestiti){
            if(p.getISBN().contains(str))
                filter.add(p);
        }
        return filter;
    }
/**
 * @brief "Ricerca dei prestiti per ID"
 * "Scorrendo tutta la lista prestiti, vengono inseriti in List<Prestito> filter solo quei prestiti i quali l'attributo ID corrisponde con parametro int ID"
 * @post Ci si apetta una lista che contenga solo i prestiti i quali l'attributo ID corrisponde con parametro int ID
 * @param[in] int ID
 * @return List<Prestito> filter
 * @author ALESSANDRO VISCIANO
 */
    public List<Prestito> cercaPerID(int ID){
        List<Prestito> filter=new ArrayList<>();
        for(Prestito p:prestiti){
            if(p.getID()==ID)
                filter.add(p);
        }
        return filter;
    }
/**
 * @brief "Ricerca dei prestiti per Matricola"
 * "Scorrendo tutta la lista prestiti, vengono inseriti in List<Prestito> filter solo quei prestiti i quali l'attributo matricola corrisponde con parametro String Matricola"
 * @pre Il parametro String Matricola deve rispettare il formato
 * @post Ci si apetta una lista che contenga solo i prestiti i quali l'attributo Matricola corrisponde con parametro String Matricola
 * @param[in] String matricola
 * @return List<Prestito> filter
 * @author ALESSANDRO VISCIANO
 */
    public List<Prestito> cercaPrestitoPerMatricola(String Matricola){
        List<Prestito> filter=new ArrayList<>();
        for(Prestito p:prestiti){
            if(p.getMatricola().equals(Matricola))
                filter.add(p);
        }
        return filter;
    }
/**
 * @brief "Ricerca dei prestiti per dataScadenza"
 * "Scorrendo tutta la lista prestiti, vengono inseriti in List<Prestito> filter solo quei prestiti i quali l'attributo dataScadenza corrisponde con parametro LocalDate dataScadenza"
 * @pre Il parametro LocalDate dataScadenza deve rispettare il formato
 * @post Ci si apetta una lista che contenga solo i prestiti i quali l'attributo dataScadenza corrisponde con parametro LocalDate dataScadenza
 * @param[in] LocalDate dataScadenza
 * @return List<Prestito> filter
 * @author ALESSANDRO VISCIANO
 */
    public List<Prestito> cercaPerData(LocalDate dataScadenza){
        List<Prestito> filter=new ArrayList<>();
        for(Prestito p:prestiti){
            if(p.getDataScadenza().equals(dataScadenza))
                filter.add(p);
        }
        return filter;
    }
/**
 * @brief "riordinamento per dataScadenza"
 * "viene eseguita la sort su List<Prestito> prestiti passando come parametro della sort il parametro Comparator<Prestito> cmp"
 * @pre Il comparatore deve essere inizializzato in Prestito
 * @post La lista viene riordinata
 * @param[in] Comparator<Prestito> cmp
 * @author ALESSANDRO VISCIANO
 */ 
    public void OrdinaPerScadenza(Comparator<Prestito> cmp){
        prestiti.sort(cmp);
    }
/**
 * @brief "Salvataggio su file esterno"
 * "il riferimento alla lista viene salvato su un file binario esterno"
 * @pre si deve passare sempre lo stesso parametro String nomefile (stesso parametro di letturaPrestiti)
 * @post Il riferimento a List<Prestito> prestiti viene salvato sul file esterno
 * @param[in] String nomefile
 * @author ALESSANDRO VISCIANO
 */
    public void salvataggioPrestiti(String nomefile)throws IOException{
        try(ObjectOutputStream o=new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(nomefile)))){
            o.writeObject(prestiti);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
/**
 * @brief "Lettura da file esterno"
 * "Viene letto il riferimento salvato su file esterno e List<Prestito> prestiti viene sovrascritto"
 * @pre  si deve passare sempre lo stesso parametro String nomefile (stesso parametro di salvataggioPrestiti)
 * @post List<Prestito> prestiti viene sovrascritto
 * @param[in] String nomefile
 * @author ALESSANDRO VISCIANO
 */
    public void letturaPrestiti(String nomefile)throws IOException{
        try(ObjectInputStream o=new ObjectInputStream(new BufferedInputStream(new FileInputStream(nomefile)))){
            List<Prestito> readed= (List<Prestito>)o.readObject(); 
            this.prestiti=readed;
        }catch(IOException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
 /**
 * @brief "Modifica di un prestito"
 * "Superati i vari controlli, gli attributi del prestito old presente in List<Prestito> prestiti vengono sostituiti con quelli del prestito newer, tranne per l'ID che resta lo stesso di old"
 * @pre gli attributi di newer devono superare i vari controlli e rispettare i formati, inoltre il metodo viene chiamato solo se almeno un campo viene modificato
 * @post Il prestito nella lista che corrisponde ad old viene modificato
 * @param[in] Prestito old, Prestito newer
 * @return boolean true, boolean false
 * @author ALESSANDRO VISCIANO
 */
    public boolean ModificaPrestito(Prestito old, Prestito newer){
        for(Prestito pr:prestiti){
            if(pr.equals(old))
                if(!checkUtente(newer)){
                    pr.setISBN(newer.getISBN()); 
                    pr.setMatricola(newer.getMatricola());
                    pr.setDataScadenza(newer.getDataScadenza());
                    return true;
                }else 
                    return false;
        }
        return false;
    }
    
/**
 * @brief "Controllo dell'Utente"
 * "Viene controllato de l'attributo Matricola del parametro Prestito p compare giò 3 volte nella lista"
 * @pre gli attributi del parametro devono rispettare il formato
 * @post Ci si aspetta true se l'attributo Matricola del parametro compare già 3 volte in lista, altrimenti false
 * @param[in] Prestito p
 * @return  boolean true, boolean false
 * @author ALESSANDRO VISCIANO
 */
    public boolean checkUtente(Prestito p){
        List<Prestito> check=cercaPrestitoPerMatricola(p.getMatricola());
        if(check.size()==3)
            return true;
        else{
           return false;
        }
    }
}
