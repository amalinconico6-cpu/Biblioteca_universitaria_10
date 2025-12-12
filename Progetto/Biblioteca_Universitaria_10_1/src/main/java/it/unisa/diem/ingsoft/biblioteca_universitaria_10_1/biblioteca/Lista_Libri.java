/**
 * @file Lista_libri
 * @brief classe per la gestione della lista dei libri
 * "fornisce tutti i metodi per la gestione della lista dei libri"
 * @author Francesco sabia
 * @version 1.0
 */


package it.unisa.diem.ingsoft.biblioteca_universitaria_10_1.biblioteca;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Lista_Libri {
    private List<Libro> libri=new ArrayList<>();

/**
 * @brief "inserire qui descrizione breve"
 * "inserire qui descrizione specifica del metodo"
 * @pre
 * @post
 * @param[in]
 * @param[out]
 * @author
 */
    public void aggiungiLibro(Libro libro){
        if(this.libri.contains(libro)){
            for(Libro l: this.libri){
                if(l.equals(libro)){
                    l.setCopie(l.getCopie()+1);
                }
            }
        }    
        else{
            this.libri.add(libro);
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
    public void rimuoviLibro(Libro libro){
        if(!(this.libri.contains(libro))){
            //errore
        }
        else{
            this.libri.remove(libro);
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
    public void modificaLibro(Libro libro, Libro l_modificato){
        for(Libro l: this.libri){
            if(l.equals(libro)){
                    l=l_modificato;
            }
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
    public void modificaCopie(String ISBN, int n){
        for(Libro l: this.libri){
                if(l.getISBN()==ISBN){
                    l.setCopie(l.getCopie()+n);
                }
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
    public List<Libro> cercaPerTitolo(String str){
        List<Libro> temp_list=new ArrayList<>();
        for(Libro l: this.libri){
            if(l.getTitolo()==str){
                temp_list.add(l);
            }
        }
        if(temp_list.isEmpty())
            return null;//errore
        else
            return temp_list;
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
    public List<Libro> cercaPerISBN(String str){
        List<Libro> temp_list=new ArrayList<>();
        for(Libro l: this.libri){
            if(l.getISBN().equals(str)){
                temp_list.add(l);
            }
        }
        if(temp_list.isEmpty())
            return null;//errore
        else
            return temp_list;
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
    public List<Libro> cercaPerAutore(String str){
        List<Libro> temp_list=new ArrayList<>();
        for(Libro l: this.libri){
            for(Autore a : l.getAutori())
                if(a.getCognome().equals(str)||a.getNome().equals(str))
                    temp_list.add(l);
            }
        if(temp_list.isEmpty())
            return null;//errore
        else
            return temp_list;
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
  public boolean checkISBN(String ISBN){
        for(Libro l: this.libri){
            if(l.getISBN().equals(ISBN))
                return true;
            else 
                return false;
        }
        return false;
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
    public void ordina(Comparator<Libro> cmp){
        libri.sort(cmp);
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
    public void salvataggioLibri(String nomefile)throws IOException{
        FileOutputStream fileOut = new FileOutputStream(nomefile);
        try(ObjectOutputStream objin=new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(nomefile)))){
            objin.writeObject(this); 
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
    public void letturaLibri(String nomefile)throws IOException{
        try(ObjectInputStream objout=new ObjectInputStream(new BufferedInputStream(new FileInputStream(nomefile)))){
            List<Libro> lista_e= (List<Libro>)objout.readObject(); 
            this.libri=lista_e;
        }catch(IOException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        
    }
}
