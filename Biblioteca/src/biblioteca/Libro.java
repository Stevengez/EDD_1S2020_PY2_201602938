/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca;

/**
 *
 * @author Steven
 */
public class Libro {
    private int ISBN, Year, Edition, ID_Author;
    private String Title, Name_Author, Printer, Category, Language; 
    public Libro(int ISBN, String Title, String Name_Author, String Printer, int Year, int Edition, String Category, String Language, int ID_Author){
        this.ISBN = ISBN;
        this.Title = Title;
        this.Name_Author = Name_Author;
        this.Printer = Printer;
        this.Year = Year;
        this.Edition = Edition;
        this.Category = Category;
        this.Language = Language;
        this.ID_Author = ID_Author;        
    }
    
    public int getISBN(){
        return this.ISBN;
    }
}
