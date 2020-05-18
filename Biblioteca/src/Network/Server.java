/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Network;

import biblioteca.Biblioteca;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Steven
 */
public class Server extends Thread{
    private Biblioteca LibraryManager;
    private NetworkManager NetManager;
    public Server(Biblioteca LibraryManager){
        System.out.println("-------Hilo Servidor Comenzado------");
        this.LibraryManager = LibraryManager;
        this.NetManager = LibraryManager.getNetworkManager();
    }
    
    public void run(){
        try {
            System.out.println("Servidor:: Iniciando el servidor en el puerto: "+NetManager.getServerPort());
            ServerSocket Servidor = new ServerSocket(NetManager.getServerPort());
            while(NetManager.getServerStatus()){
                new ServerThread(LibraryManager, Servidor.accept()).start();
            }
            Servidor.close();
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }
    }
}
