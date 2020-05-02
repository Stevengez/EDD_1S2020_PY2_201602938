/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Network;

import JSONCreator.Constantes;
import JSONCreator.JSONTraductor;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Steven
 */
public class ServerThread extends Thread {
    private Socket Client;
    private NetworkManager NetManager;
    public ServerThread(NetworkManager Context, Socket Client){
        this.Client = Client;
        this.NetManager = Context;
    }
    
    public void run(){
        try {
            System.out.println("Servidor:: Conexion establecida desde: "+Client.getInetAddress());
            
            while(!Client.isClosed()){
                ObjectInputStream Recibir = new ObjectInputStream(Client.getInputStream());
                String Request = (String) Recibir.readObject();
                ObjectOutputStream Enviar = new ObjectOutputStream(Client.getOutputStream());
                switch(Request){
                    case Constantes.REQUEST_NETWORKNODES: 
                        Enviar.writeObject(JSONTraductor.addRedOperation(JSONTraductor.createBlock(),NetManager.getNetworkList()).toJSONString());
                        break;
                    case Constantes.REQUEST_CLOSESOCKET:
                        Client.close();
                        break;
                }
            }
            System.out.println("Conexion Perdida.");
            Client.close();
        } catch(SocketException sex){
            System.out.println("Conexion Perdida.");
        } catch (IOException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
