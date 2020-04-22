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
        System.out.println("********Busqueda y Eliminacion***********");
        Librero.BuscarNodo("Steven");
        Librero.EliminarNodo("Fisioterapia");
        Librero.ImpresoraGrafica();
        
        

    }

}
