/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.Estructuras;

import JSONCreator.Constantes;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Steven
 */
public class HashTable {
    private NodoHash[] Casilleros;
    private int size;
    
    public HashTable(){
        Casilleros = new NodoHash[Constantes.HASH_TABLE_MAXSIZE];    
    }
    
    public void newUser(int Carnet, String Nombre, String Apellido, String Carrera, String Password){
        if (Casilleros[getLockerID(Carnet)] == null) Casilleros[getLockerID(Carnet)] = new NodoHash(this);
        Casilleros[getLockerID(Carnet)].addCarnet(Carnet, Nombre, Apellido, Carrera, getMD5From(Password));
    }
    
    public String getMD5From(String Password){
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(Password.getBytes());
            byte[] encoded = md.digest();
            return toHex(encoded);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(HashTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    private String toHex(byte[] encoded){
        StringBuilder hexcoded = new StringBuilder();
        for(int i=0;i<encoded.length;i++){
            String hex = Integer.toHexString(0xff & encoded[i]);
            if(hex.length() == 1) hexcoded.append('0');
            hexcoded.append(hex);
        }
        return hexcoded.toString();
    }
    
    public SubNodoHash getUser(int Carnet){
        if(Casilleros[getLockerID(Carnet)] == null) return null;
        
        if(Casilleros[getLockerID(Carnet)].getSize()<0){
            return null;
        }else{
            Casilleros[getLockerID(Carnet)].moveTo(Carnet);
            return Casilleros[getLockerID(Carnet)].doWith();
        }
    }
    
    public void addToSize(){
        this.size++;
    }
    
    public void resToSize(){
        this.size--;
    }
    
    private int getLockerID(int Carnet){
        return Carnet % Constantes.HASH_TABLE_MAXSIZE;
    }
    
}
