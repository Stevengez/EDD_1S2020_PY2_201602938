/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.Estructuras;

import JSONCreator.JSONCreator;

/**
 *
 * @author Steven
 */
public class NodoHash {
    
    private int size;
    private SubNodoHash Inicio, Final, Temp;
    private HashTable Padre;
    public NodoHash(HashTable Padre) {
        this.Padre = Padre;
        this.size = 0;
        this.Inicio = null;
        this.Final = null;
    }

    public SubNodoHash addCarnet(int Carnet, String Nombre, String Apellido, String Carrera, String Password) {
        SubNodoHash nuevo = new SubNodoHash(Carnet, Nombre, Apellido, Carrera, Password);
        if (Inicio == null) {
            Inicio = nuevo;
            Final = nuevo;
            this.size++;
            this.Padre.addToSize();
            return nuevo;
        } else {
            if (existID(Carnet)==null) {
                Final.setSiguiente(nuevo);
                nuevo.setAnterior(Final);
                Final = nuevo;
                this.size++;
                this.Padre.addToSize();
                return nuevo;
            } else {
                System.out.println("Ya existe este usuario.");
                return null;
            }
        }
    }
    
    public SubNodoHash getHead(){
        return this.Inicio;
    }

    private SubNodoHash existID(int Carnet) {
        SubNodoHash aux = Inicio;
        while (aux != null) {
            if (aux.getCarnet() == Carnet) {
                return aux;
            }
            aux = aux.getSiguiente();
        }
        return null;
    }
    
    public SubNodoHash removeCarnet(int Carnet){
        SubNodoHash aux = existID(Carnet);
        if(aux !=null){
            if(aux == Inicio){
                if(aux.getSiguiente()!=null){
                    Inicio.getSiguiente().setAnterior(null);
                    Inicio = Inicio.getSiguiente();
                }else{
                    Inicio = null;
                }
                this.size--;
                this.Padre.resToSize();
                return aux;
            }else if(aux == Final){
                if(Final.getAnterior()!=null){
                    Final.getAnterior().setSiguiente(null);
                    Final = Final.getAnterior();
                }else{
                    Inicio = null;
                    Final = null;
                }
                this.size--;
                this.Padre.resToSize();
                return aux;
            }else{
                aux.getAnterior().setSiguiente(aux.getSiguiente());
                aux.getSiguiente().setAnterior(aux.getAnterior());
                this.size--;
                this.Padre.resToSize();
                return aux;
            }
        }else{
            System.out.println("No existia ese usuario.");
            return null;
        }
    }
    
    public boolean moveTo(int Carnet){
        SubNodoHash aux = existID(Carnet);
        if(aux!=null){
            Temp = aux;
            return true;
        }else{
            Temp = null;
            return false;
        }
    }
    
    public SubNodoHash doWith(){
        return this.Temp;
    }
    
    public int getSize(){
        return this.size;
    }
    
}
