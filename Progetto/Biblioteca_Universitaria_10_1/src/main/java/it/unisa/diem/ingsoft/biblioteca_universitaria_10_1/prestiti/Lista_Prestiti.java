/**
 * @file 
 * @brief
 * "Inserire qui descrizione specifica della classe"
 * @author
 * @date
 * @version
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
 * @brief "inserire qui descrizione breve"
 * "inserire qui descrizione specifica del metodo"
 * @pre
 * @post
 * @param[in]
 * @param[out]
 * @return 
 * @author
 */
    public List<Prestito> getPrestiti(){
        return this.prestiti;
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
    public String aggiungiPrestito(Prestito p){
        if(checkUtente(p))
            return "Ci sono già 3 prestiti associati a questo utente";
        else if(checkMatricola(p.getMatricola())){ 
                if(checkISBN(p.getISBN())){
                    if(checkCopie(p.getISBN())){
                        prestiti.add(p);
                        OrdinaPerScadenza(p.getComp());
                        modificaCopie(p.getISBN(),-1);
                        return "Prestito inserito";
                    }else
                        return "Non ci sono copie disponibili";
            
                }else
                    return "ISBN non presente nella Lista Libri";
            }else
                return "Utente non presente";
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
    public String rimuoviPrestito(Prestito p){
        if(prestiti.contains(p)){
            prestiti.remove(p);
            modificaCopie(p.getISBN(),+1);
            return "Prestito rimosso correttamente";
        } 
        else{
            return "Prestito non presente in lista";
        }
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
 * @brief "inserire qui descrizione breve"
 * "inserire qui descrizione specifica del metodo"
 * @pre
 * @post
 * @param[in]
 * @param[out]
 * @author
 */
    public void setPrestiti(List<Prestito> prestiti){
        this.prestiti=prestiti;
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
    public List<Prestito> cercaPerISBN(String str){
        List<Prestito> filter=new ArrayList<>();
        for(Prestito p:prestiti){
            if(p.getISBN().contains(str))
                filter.add(p);
        }
        return filter;
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
    public List<Prestito> cercaPerID(int ID){
        List<Prestito> filter=new ArrayList<>();
        for(Prestito p:prestiti){
            if(p.getID()==ID)
                filter.add(p);
        }
        return filter;
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
    public List<Prestito> cercaPerMatricola(int Matricola){
        List<Prestito> filter=new ArrayList<>();
        for(Prestito p:prestiti){
            if(p.getMatricola()==Matricola)
                filter.add(p);
        }
        return filter;
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
    public List<Prestito> cercaPerData(LocalDate dataScadenza){
        List<Prestito> filter=new ArrayList<>();
        for(Prestito p:prestiti){
            if(p.getDataScadenza().equals(dataScadenza))
                filter.add(p);
        }
        return filter;
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
    public void OrdinaPerScadenza(Comparator<Prestito> cmp){
        prestiti.sort(cmp);
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
    public void salvataggioPrestiti(String nomefile)throws IOException{
        try(ObjectOutputStream o=new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(nomefile)))){
            o.writeObject(prestiti);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
/**
 * @brief "inserire qui descrizione breve"
 * "inserire qui descrizione specifica del metodo"
 * @pre
 * @post
 * @param[in]
 * @param[out]
 * @return 
 * @author
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
 * @brief "inserire qui descrizione breve"
 * "inserire qui descrizione specifica del metodo"
 * @pre
 * @post
 * @param[in]
 * @param[out]
 * @return 
 * @author
 */
    public boolean checkUtente(Prestito p){
        List<Prestito> check=cercaPerMatricola(p.getMatricola());
        if(check.size()==3)
            return true;
        else{
           return false;
        }
    }
}
