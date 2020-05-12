/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca;

import GUI.CentralGUI;
import Network.NetworkManager;
import biblioteca.Estructuras.ArbolAVL;
import biblioteca.Estructuras.ArbolB;
import JSONCreator.JSONCreator;
import biblioteca.Estructuras.BlockChain;
import biblioteca.Estructuras.Graficadora;
import biblioteca.Estructuras.HashTable;
import java.io.IOException;
import javax.swing.JFrame;

/**
 *
 * @author Steven
 */
public class Biblioteca extends JFrame {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        JSONCreator.createBlock();
        new CentralGUI(new Biblioteca());
    }
    
    /* Estructuras Variables */
    private ArbolAVL Librero;
    private ArbolB LibreroGlobal;
    private HashTable Usuarios;
    private Graficadora Imprimir;
    
    /* Controaldores */
    private NetworkManager router;
    
    /* Creador de Nodos */
    private BlockChain Satoshi;
    
        
    public Biblioteca() {
        
        /* Manejador de la Red */
        
        router = new NetworkManager(this);
        
        /* Inicializar Estructuras Principales */
        
        Librero = new ArbolAVL();
        LibreroGlobal = new ArbolB();
        Usuarios = new HashTable();
        Imprimir = new Graficadora(this);
        
        /* Cadena BlockChain */
        Satoshi = new BlockChain(this);
    }
    
    public Graficadora getImpresora(){
        return this.Imprimir;
    }
    
    public ArbolB getLibreroGlobal(){
        return this.LibreroGlobal;
    }
    
    public ArbolAVL getLibrero(){
        return this.Librero;
    }
    
    public HashTable getUsuarios(){
        return this.Usuarios;
    }
    
    public BlockChain getBlockChain(){
        return this.Satoshi;
    }
    
    public NetworkManager getNetworkManager(){
        return this.router;
    }
}
