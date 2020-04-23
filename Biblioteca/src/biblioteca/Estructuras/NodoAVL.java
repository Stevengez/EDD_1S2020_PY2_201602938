/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.Estructuras;

import biblioteca.*;

/**
 *
 * @author Steven
 */
public class NodoAVL {
    private int bFact, SubLevels;
    private NodoAVL Izquierda, Derecha, Padre;
    private Categoria Data;
    private boolean isLevelCreator, isLevelDestructor, Eliminado;
    
    public NodoAVL(Categoria Data){
        this.bFact = 0;
        this.SubLevels = 0;
        this.Padre = null;
        this.Izquierda = null;
        this.Derecha = null;
        this.Data = Data;
        this.isLevelCreator = false;
        this.isLevelDestructor = false;
        this.Eliminado = false;
    }
    
    public void setPadre(NodoAVL Padre){
        this.Padre = Padre;
    }
    
    public NodoAVL getPadre(){
        return this.Padre;
    }
    
    public void setEliminado(boolean Eliminado){
        this.Eliminado = Eliminado;
    }
    
    public boolean isDeleted(){
        return this.Eliminado;
    }
    
    public void setData(Categoria Data){
        this.Data = Data;
    }
    
    public void setIzquierda(NodoAVL Izquierda){
        this.Izquierda = Izquierda;
    }
    
    public void setDerecha(NodoAVL Derecha){
        this.Derecha = Derecha;
    }
    
    public NodoAVL getIzquierda(){
        return this.Izquierda;
    }
    
    public NodoAVL getDerecha(){
        return this.Derecha;
    }
    
    public Categoria getData(){
        return this.Data;
    }
    
    public void setbFact(int bFact){
        this.bFact = bFact;
    }
    
    public void setSubLevels(int Sublevels){
        this.SubLevels = Sublevels;
    }
    
    public void AddSubLevel(){
        this.SubLevels++;
    }
    
    public void removeSubLevel(){
        this.SubLevels--;
    }
    
    public int getBFact(){
        return this.bFact;
    }
    
    public int getSubLevels(){
        return this.SubLevels;
    }
    
    public void setLevelCreator(boolean LC){
        this.isLevelCreator = LC;
    }
    
    public void setLevelDestructor(boolean LC){
        this.isLevelDestructor = LC;
    }
    
    public boolean isLevelCreator(){
        return this.isLevelCreator;
    }
    
    public boolean isLevelDestructor(){
        return this.isLevelDestructor;
    }
}
