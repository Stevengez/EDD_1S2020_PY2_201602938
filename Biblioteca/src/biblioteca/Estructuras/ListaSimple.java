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

public class ListaSimple {

    private int size;
    private NodoSimple Inicio, Final, Temp;

    public ListaSimple() {
        this.size = 0;
        this.Inicio = null;
        this.Final = null;
    }

    public void AddNetNode(String IP, int ServerPort) {
        NodoSimple nuevo = new NodoSimple(IP, ServerPort);
        if (Inicio == null) {
            Inicio = nuevo;
            Final = nuevo;
            this.size++;
        } else {
            if (existNetNode(IP)==null) {
                Final.setSiguiente(nuevo);
                nuevo.setAnterior(Final);
                Final = nuevo;
                System.out.println("Sincronice un Nodo: "+IP+":"+ServerPort);
            } else {
                System.out.println("Ya existe este nodo de red.");
            }
        }
    }
    
    public NodoSimple getHead(){
        return this.Inicio;
    }
    
    public void replaceHead(String IP, int Port){
        if(Inicio !=null){
            Inicio.setIP(IP);
            Inicio.setPort(Port);
        }else{
            AddNetNode(IP,Port);
        }
    }

    private NodoSimple existNetNode(String IP) {
        NodoSimple aux = Inicio;
        while (aux != null) {
            if (aux.getAddress().equals(IP)) {
                return aux;
            }
            aux = aux.getSiguiente();
        }
        return null;
    }
    
    public void deleteNetNode(String IP){
        NodoSimple aux = existNetNode(IP);
        if(aux !=null){
            if(aux == Inicio){
                if(aux.getSiguiente()!=null){
                    Inicio.getSiguiente().setAnterior(null);
                    Inicio = Inicio.getSiguiente();
                }else{
                    Inicio = null;
                }
                this.size--;
            }else if(aux == Final){
                if(Final.getAnterior()!=null){
                    Final.getAnterior().setSiguiente(null);
                    Final = Final.getAnterior();
                }else{
                    Inicio = null;
                    Final = null;
                }
                this.size--;
            }else{
                aux.getAnterior().setSiguiente(aux.getSiguiente());
                aux.getSiguiente().setAnterior(aux.getAnterior());
                this.size--;
            }
        }else{
            System.out.println("No existia ese nodo, no se elimino nada");
            
        }
    }
    
    public boolean moveTo(String IP){
        NodoSimple aux = existNetNode(IP);
        if(aux!=null){
            Temp = aux;
            return true;
        }else{
            Temp = null;
            return false;
        }
    }
    
    public NodoSimple doWith(){
        return this.Temp;
    }
    
    public int getNodeSize(){
        return this.size;
    }
}
