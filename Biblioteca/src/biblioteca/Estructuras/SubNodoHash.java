/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.Estructuras;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Steven
 */
public class SubNodoHash {
    private int Carnet;
    private String Nombre, Apellido, Carrera, Password;
    private SubNodoHash siguiente,anterior;
    
    public SubNodoHash(int Carnet, String Nombre, String Apellido, String Carrera, String Password){
        this.Carnet = Carnet;
        this.Nombre = Nombre;
        this.Apellido = Apellido;
        this.Carrera  = Carrera;
        this.Password = Password;
        
    }
    
    public String getMD5From(String decoded){
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(decoded.getBytes());
            byte[] digest = md.digest();
            return digest.toString();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(SubNodoHash.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public int getCarnet(){
        return this.Carnet;
    }
    
    public String getNombre(){
        return this.Nombre;
    }
    
    public String getApellido(){
        return this.Apellido;
    }
    
    public String getFullName(){
        return this.Nombre + " " + this.Apellido;
    }
    
    public String getCarrera(){
        return this.Carrera;
    }
    
    public String getPassword(){
        return this.Password;
    }
    
    public SubNodoHash getSiguiente(){
        return this.siguiente;
    }
    
    public SubNodoHash getAnterior(){
        return this.anterior;
    }
    
    public void setSiguiente(SubNodoHash siguiente){
        this.siguiente = siguiente;
    }
    
    public void setAnterior(SubNodoHash anterior){
        this.anterior = anterior;
    }
    
}
