/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.Estructuras;

import JSONCreator.Constantes;
import JSONCreator.JSONCreator;
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
    //private Biblioteca VirtualLibrary;
    
    public HashTable(){
        //this.VirtualLibrary = VirtualLibrary;
        Casilleros = new NodoHash[Constantes.HASH_TABLE_MAXSIZE];    
    }
    
    public SubNodoHash newUser(int Carnet, String Nombre, String Apellido, String Carrera, String Password, boolean LocalJSON, boolean FromPeer ){
        if (Casilleros[getLockerID(Carnet)] == null) Casilleros[getLockerID(Carnet)] = new NodoHash(this);
        SubNodoHash nuevo;
        if(LocalJSON || FromPeer){
            nuevo = Casilleros[getLockerID(Carnet)].addCarnet(Carnet, Nombre, Apellido, Carrera, Password);
        }else{
            nuevo = Casilleros[getLockerID(Carnet)].addCarnet(Carnet, Nombre, Apellido, Carrera, getMD5From(Password));
        }
        
        if(!LocalJSON){
            if(nuevo != null){
                /* Agregar Operacion al Bloque */
                JSONCreator.addUserOperation(JSONCreator.getCurrentBlock(), nuevo);
            }
        }
        return nuevo;
    }
    
    public void delUser(int Carnet, boolean LocalJSON){
        SubNodoHash eliminar = Casilleros[getLockerID(Carnet)].removeCarnet(Carnet);
        if(eliminar != null){
            if(Casilleros[getLockerID(Carnet)].getSize()<1){
                Casilleros[getLockerID(Carnet)] = null;
            }
            if(!LocalJSON){
                /* Agregar Operacion al Bloque */
                JSONCreator.delUserOperation(JSONCreator.getCurrentBlock(), eliminar);
            }
        }
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
    
    public SubNodoHash logIn(int Carnet, String Password){
        SubNodoHash Usuario = getUser(Carnet);
        if(Usuario == null){ System.out.println("Usuarios:: No Existe"); return null; }
        
        if(getMD5From(Password).equals(Usuario.getPassword())){
            return Usuario;
        }else{
            return null;
        }
    }
    
    public NodoHash[] getCasilleros(){
        return this.Casilleros;
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
