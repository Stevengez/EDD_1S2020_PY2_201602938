/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.Estructuras;

import biblioteca.Libro;

/**
 *
 * @author Steven
 */
public class Clave {
    Libro Data;
    private NodoB Menores, Mayores;
    
    public Clave(Libro Data){
        this.Data = Data;
        this.Menores = null;
        this.Mayores = null;
    }
    
    public void convertTo(Clave clave){
        this.Data = clave.getData();
        this.Menores = clave.getMenores();
        this.Mayores = clave.getMayores();
    }
    
    public Libro getData(){
        return this.Data;
    }
    
    public int getClave(){
        return this.Data.getISBN();
    }
    
    public void setMenores(NodoB Menores){
        this.Menores = Menores;
    }
    
    public void setMayores(NodoB Mayores){
        this.Mayores = Mayores;
    }
    
    public NodoB getMenores(){
        return this.Menores;
    }
    
    public NodoB getMayores(){
        return this.Mayores;
    }
    
}
