/**
 * @file 
 * @brief
 * "Inserire qui descrizione specifica della classe"
 * @author
 * @date
 * @version
 */


package it.unisa.diem.ingsoft.biblioteca_universitaria_10_1.prestiti;

import java.time.LocalDate;
import java.util.Comparator;

public class Prestito {

    private String ISBN;
    private int Matricola;
    private LocalDate dataScadenza;
    private final int ID;
    private static int counter=0;
    public static final Comparator<Prestito> SCADENZA = Comparator.comparing(Prestito::getDataScadenza);
    
    public Prestito(String ISBN,int Matricola,LocalDate dataScadenza){
        this.ISBN=ISBN;
        this.Matricola=Matricola;
        this.dataScadenza=dataScadenza;
        this.ID=counter++;
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
    public Comparator<Prestito> getComp(){
        return this.SCADENZA;
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
    public String getISBN(){
        return this.ISBN;
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
    public void setISBN(String ISBN){
        this.ISBN=ISBN;
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
   public int getMatricola(){
        return this.Matricola;
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
   public void setMatricola(int Matricola){
       this.Matricola=Matricola;
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
    public int getID(){
       return this.ID;
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
    public LocalDate getDataScadenza(){
        return this.dataScadenza;
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
    @Override
    public boolean equals(Object o){
        if(this==o) return true;
        if(o==null || this.getClass()!=o.getClass()) return false;
        Prestito p=(Prestito) o;
        return this.getID()==p.getID();
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
    public void setDataScadenza(LocalDate dataScadenza){
        this.dataScadenza=dataScadenza;
    }
}
