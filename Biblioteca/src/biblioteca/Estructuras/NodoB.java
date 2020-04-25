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
public class NodoB {
    private NodoB Padre, HermanoMayor, HermanoMenor;
    private Clave Clave1, Clave2, Clave3, Clave4, Aux;
    private int NumeroHijos,NumeroClaves, SubNiveles;

    public NodoB() {
        this.Padre = null;
        this.NumeroHijos = 0;
        this.NumeroClaves = 0;
        this.SubNiveles = 0;
        this.Clave1 = null;
        this.Clave2 = null;
        this.Clave3 = null;
        this.Clave4 = null;
        this.Aux = null;
        this.HermanoMayor = null;
        this.HermanoMenor = null;

    }
    
    public void setBB(NodoB Hermano){
        this.HermanoMayor = Hermano;
    }
    
    public void setYB(NodoB Hermano){
        this.HermanoMenor = Hermano;
    }
    
    public NodoB getBB(){
        return this.HermanoMayor;
    }
    
    public NodoB getYB(){
        return this.HermanoMenor;
    }
    
    public NodoB getPadre(){
        return this.Padre;
    }
    
    public void setPadre(NodoB Padre){
        this.Padre = Padre;
    }
    
    public void InsertNew(Clave Nuevo) {
        if (Clave1 == null) {
            Clave1 = Nuevo;
            this.NumeroClaves = 1;
        } else {
            switch (getKeySize()) {
                case 1:
                    if(Nuevo.getClave()>Clave1.getClave()){
                        Clave2 = Nuevo;
                    }else if(Nuevo.getClave()<Clave1.getClave()){
                        Clave2 = Clave1;
                        Clave1 = Nuevo;
                        
                        /* Reconectar */
                        Clave2.setMenores(Clave1.getMayores());
                    }else{
                        System.out.println("Esa clave ya existe.");
                    }
                    this.NumeroClaves = 2;
                    break;
                case 2:
                    if(Nuevo.getClave()>Clave2.getClave()){
                        Clave3 = Nuevo;
                    }else if(Nuevo.getClave()>Clave1.getClave()){
                        Clave3 = Clave2;
                        Clave2 = Nuevo;
                        
                        /* Reconectar */
                        Clave3.setMenores(Clave2.getMayores());
                    }else{
                        Clave3 = Clave2;
                        Clave2 = Clave1;
                        Clave1 = Nuevo;
                        
                        /* Reconectar */
                        Clave2.setMenores(Clave1.getMayores());
                    }
                    this.NumeroClaves = 3;
                    break;
                case 3:
                    if(Nuevo.getClave()>Clave3.getClave()){
                        Clave4 = Nuevo;
                    }else if(Nuevo.getClave()>Clave2.getClave()){
                        Clave4 = Clave3;
                        Clave3 = Nuevo;
                        
                        /* Reconectar */
                        Clave4.setMenores(Clave3.getMayores());                        
                    }else if(Nuevo.getClave()>Clave1.getClave()){
                        Clave4 = Clave3;
                        Clave3 = Clave2;
                        Clave2 = Nuevo;
                        
                        /* Reconectar */
                        Clave3.setMenores(Clave2.getMayores());
                    }else{
                        Clave4 = Clave3;
                        Clave3 = Clave2;
                        Clave2 = Clave1;
                        Clave1 = Nuevo;   
                        
                        /* Reconectar */
                        Clave2.setMenores(Clave1.getMayores());
                    }
                    this.NumeroClaves = 4;
                    break;
                case 4:
                    if(Nuevo.getClave()>Clave4.getClave()){
                        Aux = Nuevo;
                    }else if(Nuevo.getClave()>Clave3.getClave()){
                        Aux = Clave4;
                        Clave4 = Nuevo;
                        
                        /* Reconectar */
                        Aux.setMenores(Clave4.getMayores());                        
                    }else if(Nuevo.getClave()>Clave2.getClave()){
                        Aux = Clave4;
                        Clave4 = Clave3;
                        Clave3 = Nuevo;
                        
                        /* Reconectar */
                        Clave4.setMenores(Clave3.getMayores());
                    }else if(Nuevo.getClave()>Clave1.getClave()){
                        Aux = Clave4;
                        Clave4 = Clave3;
                        Clave3 = Clave2;
                        Clave2 = Nuevo;
                        
                        /* Reconectar */
                        Clave3.setMenores(Clave2.getMayores());
                    }else{
                        Aux = Clave4;
                        Clave4 = Clave3;
                        Clave3 = Clave2;
                        Clave2 = Clave1;
                        Clave1 = Nuevo;
                        
                        /* Reconectar */
                        Clave2.setMenores(Clave1.getMayores());
                    }
                    this.NumeroClaves = 5;
                    break;
                default:
                    System.out.println("La cantidad de claves no coinciden.");
                    break;
            }

        }
    }
    
    public void deleteKey(Clave clave){
        if(Clave1==clave){
            System.out.println("Borre la clave 1");
            Clave1 = Clave2;
            Clave2 = Clave3;
            Clave3 = Clave4;
            Clave4 = null;
        }else if(Clave2 == clave){
            System.out.println("Borre la clave 2");
            Clave2 = Clave3;
            Clave3 = Clave4;
            Clave4 = null;
        }else if(Clave3 == clave){
            System.out.println("Borre la clave 3");
            Clave3 = Clave4;
            Clave4 = null;
        }else{
            System.out.println("Borre la clave 4");
            Clave4 = null;
        }
    }
        
    public void setSubNiveles(int SubNiveles){
        this.SubNiveles = SubNiveles;
    }
    
    public int SubNiveles(){
        return this.SubNiveles;
    }
    
    public void addSubLevel(){
        this.SubNiveles++;
    }
    
    public void removeSubLevel(){
        this.SubNiveles--;
    }
    
    public Clave getMiddleKey(NodoB A, NodoB B){
        if(Clave1!=null && (Clave1.getMenores()==A && Clave1.getMayores()==B)){
            Clave2.setMenores(Clave1.getMenores());
            return Clave1;
        }else if(Clave2!=null && (Clave2.getMenores()==A && Clave2.getMayores()==B)){
            return Clave2;
        }else if(Clave3!=null && (Clave3.getMenores()==A && Clave3.getMayores()==B)){
            return Clave3;
        }else if(Clave4 !=null && (Clave4.getMenores()==A && Clave4.getMayores()==B)){
            return Clave4;
        }
        return null;
    }
    
    public void postDivision(){
        Clave3 = null;
        Clave4 = null;
        Aux = null;
        this.NumeroClaves = 2;
    }
    
    public void postDivisionRaiz(){
        Clave1 = Clave3;
        Clave2 = null;
        Clave3 = null;
        Clave4 = null;
        Aux = null;
        this.NumeroClaves = 1;
    }

    public int getKeySize() {
        int keysize = 0;
        if (Clave1 != null) {
            keysize++;
        }
        if (Clave2 != null) {
            keysize++;
        }
        if (Clave3 != null) {
            keysize++;
        }
        if (Clave4 != null) {
            keysize++;
        }
        if (Aux != null) {
            keysize++;
        }
        return keysize;
    }
    
    public Clave minClave(){
        if(Clave1!=null) return Clave1;
        return null;
    }
    
    public Clave maxClave(){
        if(Aux!=null) return Aux;
        if(Clave4!=null) return Clave4;
        if(Clave3!=null) return Clave3;
        if(Clave2!=null) return Clave2;
        if(Clave1!=null) return Clave1;
        return null;
    }
    
    public int minKey(){
        if(Clave1!=null) return Clave1.getClave();
        return 0;
    }
    
    public int maxKey(){
        if(Aux!=null) return Aux.getClave();
        if(Clave4!=null) return Clave4.getClave();
        if(Clave3!=null) return Clave3.getClave();
        if(Clave2!=null) return Clave2.getClave();
        if(Clave1!=null) return Clave1.getClave();
        return 0;
    }
    
    public Clave getKey1(){
        return Clave1;
    }
    
    public Clave getKey2(){
        return Clave2;
    }
    
    public Clave getKey3(){
        return this.Clave3;
    }
    
    public Clave getKey4(){
        return Clave4;
    }
    
    public Clave getKey5(){
        return Aux;
    }
}
