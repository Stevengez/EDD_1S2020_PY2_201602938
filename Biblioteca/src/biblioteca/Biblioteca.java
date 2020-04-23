/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca;

import biblioteca.Estructuras.ArbolAVL;
import biblioteca.Estructuras.NodoAVL;

/**
 *
 * @author Steven
 */
public class Biblioteca {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //System.out.println("Hello World.");
        ArbolAVL Librero = new ArbolAVL();
        Librero.InsertNew(new Categoria("Ciencia Ficcion"));
        Librero.InsertNew(new Categoria("Romance"));
        Librero.InsertNew(new Categoria("Aventura"));
        Librero.InsertNew(new Categoria("Thriller"));
        Librero.InsertNew(new Categoria("Suspenso"));
        Librero.InsertNew(new Categoria("Drama"));
        Librero.InsertNew(new Categoria("Terror"));
        Librero.InsertNew(new Categoria("Ciencia"));
        Librero.InsertNew(new Categoria("Biologia"));
        Librero.InsertNew(new Categoria("Matematica"));
        Librero.InsertNew(new Categoria("Calculo Diferencial"));
        Librero.InsertNew(new Categoria("Quimica"));
        Librero.InsertNew(new Categoria("Quimica Avanzada Volumen 3"));
        Librero.InsertNew(new Categoria("Fisica Nuclear"));
        Librero.InsertNew(new Categoria("Fisica TermoNuclear"));
        Librero.InsertNew(new Categoria("Fisioterapia"));
        Librero.Imprimir();
        Librero.ImpresoraGrafica();
        
        /*
        Librero.EliminarNodo("Ciencia Ficcion");
        Librero.EliminarNodo("Romance");
        Librero.EliminarNodo("Aventura");
        Librero.EliminarNodo("Thriller");
        Librero.EliminarNodo("Suspenso");
        Librero.EliminarNodo("Drama");
        Librero.EliminarNodo("Terror");
        Librero.EliminarNodo("Ciencia");
        
        Librero.EliminarNodo("Biologia");
        Librero.EliminarNodo("Matematica");
        Librero.EliminarNodo("Calculo Diferencial");
        Librero.EliminarNodo("Quimica");
        Librero.EliminarNodo("Quimica Avanzada VOlumen 3");
        Librero.EliminarNodo("Fisica Nuclear");
        Librero.EliminarNodo("Fisica TermoNuclear");
        */
        
        
        
        /*Librero.EliminarNodo("Matematica");
        Librero.InsertNew(new Categoria("Matematica"));
        Librero.InsertNew(new Categoria("Agricultura"));
        Librero.InsertNew(new Categoria("Energia Termica"));
        Librero.EliminarNodo("Romance");
        Librero.InsertNew(new Categoria("Corriente Alterna"));
        Librero.InsertNew(new Categoria("Corriente Directa"));
        Librero.InsertNew(new Categoria("Edad de Hierro"));
        */
        
        
        

    }

}
