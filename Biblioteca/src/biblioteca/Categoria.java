/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca;

import biblioteca.Estructuras.ArbolB;

/**
 *
 * @author Steven
 */
public class Categoria {
    private String Nombre;
    private ArbolB Libros;
    private int Publicador;
    
    public Categoria(String Nombre, int Publicador){
        this.Nombre = Nombre;
        this.Publicador = Publicador;
        this.Libros =  new ArbolB();
    }
    
    public String getNombre(){
        return this.Nombre;
    }
    
    public int getPublisher(){
        return this.Publicador;
    }
    
    public void changeNombre(String newNombre){
        this.Nombre = newNombre;
    }
    
    public ArbolB getLibrero(){
        return this.Libros;
    }
}
