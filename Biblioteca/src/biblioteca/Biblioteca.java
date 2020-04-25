/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca;

import biblioteca.Estructuras.ArbolAVL;
import biblioteca.Estructuras.ArbolB;
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
        /*ArbolAVL Librero = new ArbolAVL();
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
        ArbolB librero = new ArbolB();
        librero.NewBook(new Libro(0, "The Lord of the Rings","J.K.K. Tolkien","Paramount Books",1980,1,"Edad Media","Ingles", 201602938));
        librero.NewBook(new Libro(1, "The Lord of the Rings","J.K.K. Tolkien","Paramount Books",1980,1,"Edad Media","Ingles", 201602938));
        librero.NewBook(new Libro(2, "The Lord of the Rings","J.K.K. Tolkien","Paramount Books",1980,1,"Edad Media","Ingles", 201602938));
        librero.NewBook(new Libro(3, "The Lord of the Rings","J.K.K. Tolkien","Paramount Books",1980,1,"Edad Media","Ingles", 201602938));
        librero.NewBook(new Libro(4, "The Lord of the Rings","J.K.K. Tolkien","Paramount Books",1980,1,"Edad Media","Ingles", 201602938));
        librero.NewBook(new Libro(5, "The Lord of the Rings","J.K.K. Tolkien","Paramount Books",1980,1,"Edad Media","Ingles", 201602938));
        librero.NewBook(new Libro(6, "The Lord of the Rings","J.K.K. Tolkien","Paramount Books",1980,1,"Edad Media","Ingles", 201602938));
        librero.NewBook(new Libro(7, "The Lord of the Rings","J.K.K. Tolkien","Paramount Books",1980,1,"Edad Media","Ingles", 201602938));
        librero.NewBook(new Libro(8, "The Lord of the Rings","J.K.K. Tolkien","Paramount Books",1980,1,"Edad Media","Ingles", 201602938));
        librero.NewBook(new Libro(9, "The Lord of the Rings","J.K.K. Tolkien","Paramount Books",1980,1,"Edad Media","Ingles", 201602938));
        
        librero.NewBook(new Libro(10, "The Lord of the Rings","J.K.K. Tolkien","Paramount Books",1980,1,"Edad Media","Ingles", 201602938));
        librero.NewBook(new Libro(11, "The Lord of the Rings","J.K.K. Tolkien","Paramount Books",1980,1,"Edad Media","Ingles", 201602938));
        librero.NewBook(new Libro(12, "The Lord of the Rings","J.K.K. Tolkien","Paramount Books",1980,1,"Edad Media","Ingles", 201602938));
        librero.NewBook(new Libro(13, "The Lord of the Rings","J.K.K. Tolkien","Paramount Books",1980,1,"Edad Media","Ingles", 201602938));
        librero.NewBook(new Libro(14, "The Lord of the Rings","J.K.K. Tolkien","Paramount Books",1980,1,"Edad Media","Ingles", 201602938));
        librero.NewBook(new Libro(15, "The Lord of the Rings","J.K.K. Tolkien","Paramount Books",1980,1,"Edad Media","Ingles", 201602938));
        librero.NewBook(new Libro(16, "The Lord of the Rings","J.K.K. Tolkien","Paramount Books",1980,1,"Edad Media","Ingles", 201602938));
        librero.NewBook(new Libro(17, "The Lord of the Rings","J.K.K. Tolkien","Paramount Books",1980,1,"Edad Media","Ingles", 201602938));
        librero.NewBook(new Libro(18, "The Lord of the Rings","J.K.K. Tolkien","Paramount Books",1980,1,"Edad Media","Ingles", 201602938));
        librero.NewBook(new Libro(19, "The Lord of the Rings","J.K.K. Tolkien","Paramount Books",1980,1,"Edad Media","Ingles", 201602938));
        
        librero.NewBook(new Libro(20, "The Lord of the Rings","J.K.K. Tolkien","Paramount Books",1980,1,"Edad Media","Ingles", 201602938));
        librero.NewBook(new Libro(21, "The Lord of the Rings","J.K.K. Tolkien","Paramount Books",1980,1,"Edad Media","Ingles", 201602938));
        librero.NewBook(new Libro(22, "The Lord of the Rings","J.K.K. Tolkien","Paramount Books",1980,1,"Edad Media","Ingles", 201602938));
        librero.NewBook(new Libro(23, "The Lord of the Rings","J.K.K. Tolkien","Paramount Books",1980,1,"Edad Media","Ingles", 201602938));
        librero.NewBook(new Libro(24, "The Lord of the Rings","J.K.K. Tolkien","Paramount Books",1980,1,"Edad Media","Ingles", 201602938));
        librero.NewBook(new Libro(25, "The Lord of the Rings","J.K.K. Tolkien","Paramount Books",1980,1,"Edad Media","Ingles", 201602938));
        librero.NewBook(new Libro(26, "The Lord of the Rings","J.K.K. Tolkien","Paramount Books",1980,1,"Edad Media","Ingles", 201602938));
        librero.NewBook(new Libro(27, "The Lord of the Rings","J.K.K. Tolkien","Paramount Books",1980,1,"Edad Media","Ingles", 201602938));
        librero.NewBook(new Libro(28, "The Lord of the Rings","J.K.K. Tolkien","Paramount Books",1980,1,"Edad Media","Ingles", 201602938));
        librero.NewBook(new Libro(29, "The Lord of the Rings","J.K.K. Tolkien","Paramount Books",1980,1,"Edad Media","Ingles", 201602938));
        
        librero.NewBook(new Libro(30, "The Lord of the Rings","J.K.K. Tolkien","Paramount Books",1980,1,"Edad Media","Ingles", 201602938));
        /*
        librero.NewBook(new Libro(31, "The Lord of the Rings","J.K.K. Tolkien","Paramount Books",1980,1,"Edad Media","Ingles", 201602938));
        librero.NewBook(new Libro(32, "The Lord of the Rings","J.K.K. Tolkien","Paramount Books",1980,1,"Edad Media","Ingles", 201602938));
        librero.NewBook(new Libro(33, "The Lord of the Rings","J.K.K. Tolkien","Paramount Books",1980,1,"Edad Media","Ingles", 201602938));
        librero.NewBook(new Libro(34, "The Lord of the Rings","J.K.K. Tolkien","Paramount Books",1980,1,"Edad Media","Ingles", 201602938));
        librero.NewBook(new Libro(35, "The Lord of the Rings","J.K.K. Tolkien","Paramount Books",1980,1,"Edad Media","Ingles", 201602938));
        librero.NewBook(new Libro(36, "The Lord of the Rings","J.K.K. Tolkien","Paramount Books",1980,1,"Edad Media","Ingles", 201602938));
        librero.NewBook(new Libro(37, "The Lord of the Rings","J.K.K. Tolkien","Paramount Books",1980,1,"Edad Media","Ingles", 201602938));
        librero.NewBook(new Libro(38, "The Lord of the Rings","J.K.K. Tolkien","Paramount Books",1980,1,"Edad Media","Ingles", 201602938));
        librero.NewBook(new Libro(39, "The Lord of the Rings","J.K.K. Tolkien","Paramount Books",1980,1,"Edad Media","Ingles", 201602938));
        
        librero.NewBook(new Libro(40, "The Lord of the Rings","J.K.K. Tolkien","Paramount Books",1980,1,"Edad Media","Ingles", 201602938));
        librero.NewBook(new Libro(41, "The Lord of the Rings","J.K.K. Tolkien","Paramount Books",1980,1,"Edad Media","Ingles", 201602938));
        librero.NewBook(new Libro(42, "The Lord of the Rings","J.K.K. Tolkien","Paramount Books",1980,1,"Edad Media","Ingles", 201602938));
        librero.NewBook(new Libro(43, "The Lord of the Rings","J.K.K. Tolkien","Paramount Books",1980,1,"Edad Media","Ingles", 201602938));
        librero.NewBook(new Libro(44, "The Lord of the Rings","J.K.K. Tolkien","Paramount Books",1980,1,"Edad Media","Ingles", 201602938));
        librero.NewBook(new Libro(45, "The Lord of the Rings","J.K.K. Tolkien","Paramount Books",1980,1,"Edad Media","Ingles", 201602938));
        librero.NewBook(new Libro(46, "The Lord of the Rings","J.K.K. Tolkien","Paramount Books",1980,1,"Edad Media","Ingles", 201602938));
        librero.NewBook(new Libro(47, "The Lord of the Rings","J.K.K. Tolkien","Paramount Books",1980,1,"Edad Media","Ingles", 201602938));
        librero.NewBook(new Libro(48, "The Lord of the Rings","J.K.K. Tolkien","Paramount Books",1980,1,"Edad Media","Ingles", 201602938));
        librero.NewBook(new Libro(49, "The Lord of the Rings","J.K.K. Tolkien","Paramount Books",1980,1,"Edad Media","Ingles", 201602938));
        
        librero.NewBook(new Libro(50, "The Lord of the Rings","J.K.K. Tolkien","Paramount Books",1980,1,"Edad Media","Ingles", 201602938));
        librero.NewBook(new Libro(51, "The Lord of the Rings","J.K.K. Tolkien","Paramount Books",1980,1,"Edad Media","Ingles", 201602938));
        librero.NewBook(new Libro(52, "The Lord of the Rings","J.K.K. Tolkien","Paramount Books",1980,1,"Edad Media","Ingles", 201602938));
        librero.NewBook(new Libro(53, "The Lord of the Rings","J.K.K. Tolkien","Paramount Books",1980,1,"Edad Media","Ingles", 201602938));
        librero.NewBook(new Libro(54, "The Lord of the Rings","J.K.K. Tolkien","Paramount Books",1980,1,"Edad Media","Ingles", 201602938));
        librero.NewBook(new Libro(55, "The Lord of the Rings","J.K.K. Tolkien","Paramount Books",1980,1,"Edad Media","Ingles", 201602938));
        librero.NewBook(new Libro(56, "The Lord of the Rings","J.K.K. Tolkien","Paramount Books",1980,1,"Edad Media","Ingles", 201602938));
        librero.NewBook(new Libro(57, "The Lord of the Rings","J.K.K. Tolkien","Paramount Books",1980,1,"Edad Media","Ingles", 201602938));
        librero.NewBook(new Libro(58, "The Lord of the Rings","J.K.K. Tolkien","Paramount Books",1980,1,"Edad Media","Ingles", 201602938));
        librero.NewBook(new Libro(59, "The Lord of the Rings","J.K.K. Tolkien","Paramount Books",1980,1,"Edad Media","Ingles", 201602938));
        
        librero.NewBook(new Libro(60, "The Lord of the Rings","J.K.K. Tolkien","Paramount Books",1980,1,"Edad Media","Ingles", 201602938));
        librero.NewBook(new Libro(61, "The Lord of the Rings","J.K.K. Tolkien","Paramount Books",1980,1,"Edad Media","Ingles", 201602938));
        librero.NewBook(new Libro(62, "The Lord of the Rings","J.K.K. Tolkien","Paramount Books",1980,1,"Edad Media","Ingles", 201602938));
        librero.NewBook(new Libro(63, "The Lord of the Rings","J.K.K. Tolkien","Paramount Books",1980,1,"Edad Media","Ingles", 201602938));
        librero.NewBook(new Libro(64, "The Lord of the Rings","J.K.K. Tolkien","Paramount Books",1980,1,"Edad Media","Ingles", 201602938));
        librero.NewBook(new Libro(65, "The Lord of the Rings","J.K.K. Tolkien","Paramount Books",1980,1,"Edad Media","Ingles", 201602938));
        librero.NewBook(new Libro(66, "The Lord of the Rings","J.K.K. Tolkien","Paramount Books",1980,1,"Edad Media","Ingles", 201602938));
        librero.NewBook(new Libro(67, "The Lord of the Rings","J.K.K. Tolkien","Paramount Books",1980,1,"Edad Media","Ingles", 201602938));
        librero.NewBook(new Libro(68, "The Lord of the Rings","J.K.K. Tolkien","Paramount Books",1980,1,"Edad Media","Ingles", 201602938));
        librero.NewBook(new Libro(69, "The Lord of the Rings","J.K.K. Tolkien","Paramount Books",1980,1,"Edad Media","Ingles", 201602938));
        
        librero.NewBook(new Libro(70, "The Lord of the Rings","J.K.K. Tolkien","Paramount Books",1980,1,"Edad Media","Ingles", 201602938));
        librero.NewBook(new Libro(71, "The Lord of the Rings","J.K.K. Tolkien","Paramount Books",1980,1,"Edad Media","Ingles", 201602938));
        librero.NewBook(new Libro(72, "The Lord of the Rings","J.K.K. Tolkien","Paramount Books",1980,1,"Edad Media","Ingles", 201602938));
        librero.NewBook(new Libro(73, "The Lord of the Rings","J.K.K. Tolkien","Paramount Books",1980,1,"Edad Media","Ingles", 201602938));
        librero.NewBook(new Libro(74, "The Lord of the Rings","J.K.K. Tolkien","Paramount Books",1980,1,"Edad Media","Ingles", 201602938));
        librero.NewBook(new Libro(75, "The Lord of the Rings","J.K.K. Tolkien","Paramount Books",1980,1,"Edad Media","Ingles", 201602938));
        librero.NewBook(new Libro(76, "The Lord of the Rings","J.K.K. Tolkien","Paramount Books",1980,1,"Edad Media","Ingles", 201602938));
        librero.NewBook(new Libro(77, "The Lord of the Rings","J.K.K. Tolkien","Paramount Books",1980,1,"Edad Media","Ingles", 201602938));
        librero.NewBook(new Libro(78, "The Lord of the Rings","J.K.K. Tolkien","Paramount Books",1980,1,"Edad Media","Ingles", 201602938));
        librero.NewBook(new Libro(79, "The Lord of the Rings","J.K.K. Tolkien","Paramount Books",1980,1,"Edad Media","Ingles", 201602938));
        
        librero.NewBook(new Libro(80, "The Lord of the Rings","J.K.K. Tolkien","Paramount Books",1980,1,"Edad Media","Ingles", 201602938));
        librero.NewBook(new Libro(81, "The Lord of the Rings","J.K.K. Tolkien","Paramount Books",1980,1,"Edad Media","Ingles", 201602938));
        librero.NewBook(new Libro(82, "The Lord of the Rings","J.K.K. Tolkien","Paramount Books",1980,1,"Edad Media","Ingles", 201602938));
        librero.NewBook(new Libro(83, "The Lord of the Rings","J.K.K. Tolkien","Paramount Books",1980,1,"Edad Media","Ingles", 201602938));
        librero.NewBook(new Libro(84, "The Lord of the Rings","J.K.K. Tolkien","Paramount Books",1980,1,"Edad Media","Ingles", 201602938));
        librero.NewBook(new Libro(85, "The Lord of the Rings","J.K.K. Tolkien","Paramount Books",1980,1,"Edad Media","Ingles", 201602938));
        librero.NewBook(new Libro(86, "The Lord of the Rings","J.K.K. Tolkien","Paramount Books",1980,1,"Edad Media","Ingles", 201602938));
        librero.NewBook(new Libro(87, "The Lord of the Rings","J.K.K. Tolkien","Paramount Books",1980,1,"Edad Media","Ingles", 201602938));
        librero.NewBook(new Libro(88, "The Lord of the Rings","J.K.K. Tolkien","Paramount Books",1980,1,"Edad Media","Ingles", 201602938));
        librero.NewBook(new Libro(89, "The Lord of the Rings","J.K.K. Tolkien","Paramount Books",1980,1,"Edad Media","Ingles", 201602938));
        
        librero.NewBook(new Libro(90, "The Lord of the Rings","J.K.K. Tolkien","Paramount Books",1980,1,"Edad Media","Ingles", 201602938));
        librero.NewBook(new Libro(91, "The Lord of the Rings","J.K.K. Tolkien","Paramount Books",1980,1,"Edad Media","Ingles", 201602938));
        librero.NewBook(new Libro(92, "The Lord of the Rings","J.K.K. Tolkien","Paramount Books",1980,1,"Edad Media","Ingles", 201602938));
        librero.NewBook(new Libro(93, "The Lord of the Rings","J.K.K. Tolkien","Paramount Books",1980,1,"Edad Media","Ingles", 201602938));
        librero.NewBook(new Libro(94, "The Lord of the Rings","J.K.K. Tolkien","Paramount Books",1980,1,"Edad Media","Ingles", 201602938));
        librero.NewBook(new Libro(95, "The Lord of the Rings","J.K.K. Tolkien","Paramount Books",1980,1,"Edad Media","Ingles", 201602938));
        librero.NewBook(new Libro(96, "The Lord of the Rings","J.K.K. Tolkien","Paramount Books",1980,1,"Edad Media","Ingles", 201602938));
        librero.NewBook(new Libro(97, "The Lord of the Rings","J.K.K. Tolkien","Paramount Books",1980,1,"Edad Media","Ingles", 201602938));
        librero.NewBook(new Libro(98, "The Lord of the Rings","J.K.K. Tolkien","Paramount Books",1980,1,"Edad Media","Ingles", 201602938));
        librero.NewBook(new Libro(99, "The Lord of the Rings","J.K.K. Tolkien","Paramount Books",1980,1,"Edad Media","Ingles", 201602938));
        
        librero.NewBook(new Libro(100, "The Lord of the Rings","J.K.K. Tolkien","Paramount Books",1980,1,"Edad Media","Ingles", 201602938));
        */
        
        //librero.Imprimir();
        System.out.println("*************PRE Casos de Eliminacion**************");
        librero.ImprimirNiveles();
        System.out.println("*************Casos de Eliminacion**************");
        //librero.RemoveBook(100);
        //librero.RemoveBook(95);
        librero.RemoveBook(8);
        librero.RemoveBook(17);
        librero.RemoveBook(16);
        librero.RemoveBook(9);
        librero.RemoveBook(25);
        librero.RemoveBook(20);
        
        
        librero.ImprimirNiveles();
        
        //librero.ImprimirNiveles();
        //librero.RemoveBook(14);
        //librero.ImprimirNiveles();
        //librero.RemoveBook(17);
        //librero.ImprimirNiveles();

    }

}
