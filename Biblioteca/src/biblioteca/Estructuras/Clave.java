/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.Estructuras;

/**
 *
 * @author Steven
 */
public class Clave {
    private NodoB Menores, Mayores;
    
    public Clave(){
        this.Menores = null;
        this.Mayores = null;
    }
    
    public NodoB getMenores(){
        return this.Menores;
    }
    
    public NodoB getMayores(){
        return this.Mayores;
    }
    
}
