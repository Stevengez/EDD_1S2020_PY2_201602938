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
public class NodoSimple {
    String IP;
    int Puerto;
    private NodoSimple siguiente, anterior;
    
    public NodoSimple(String IP, int Puerto){
        siguiente = null;
        anterior = null;
        this.IP = IP;
        this.Puerto = Puerto;
    }
    
    public void setSiguiente(NodoSimple siguiente){
        this.siguiente = siguiente;
    }
    
    public void setAnterior(NodoSimple anterior){
        this.anterior = anterior;
    }
    
    public NodoSimple getSiguiente(){
        return this.siguiente;
    }
    
    public NodoSimple getAnterior(){
        return this.anterior;
    }
    
    public String getAddress(){
        return this.IP;
    }
    
    public int getServerPort(){
        return this.Puerto;
    }
    
    public String getIP(){
        return this.IP;
    }    
    
    public void setIP(String IP){
        this.IP = IP;
    }
    
    public void setPort(int Port){
        this.Puerto = Port;
    }
}
