/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Network;

import JSONCreator.Constantes;
import JSONCreator.JSONCreator;
import biblioteca.Biblioteca;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Steven
 */
public class ServerThread extends Thread {

    private Socket Client;
    private Biblioteca LibraryManager;
    private NetworkManager NetManager;

    public ServerThread(Biblioteca LibraryManager, Socket Client) {
        this.Client = Client;
        this.LibraryManager = LibraryManager;
        this.NetManager = LibraryManager.getNetworkManager();
    }

    public void run() {
        try {
            System.out.println("Servidor:: Conexion establecida desde: " + Client.getInetAddress());

            while (!Client.isClosed()) {
                ObjectInputStream Recibir = new ObjectInputStream(Client.getInputStream());
                String Request = (String) Recibir.readObject();
                ObjectOutputStream Enviar = new ObjectOutputStream(Client.getOutputStream());
                switch (Request) {
                    case Constantes.REQUEST_NETWORKNODES:
                        Enviar.writeObject(JSONCreator.addRedOperation(JSONCreator.createApartBlock(), NetManager.getNetworkList()).toJSONString());
                        break;
                    case Constantes.REQUEST_ADD_NETWORKNODE:
                        //Recibir = new ObjectInputStream(Client.getInputStream());
                        JSONObject nuevoNodo = (JSONObject) Recibir.readObject();
                        System.out.println("Solicitud de sincronizar un nuevo nodo");
                        JSONCreator.parseDataBlock(nuevoNodo.toJSONString(), NetManager, null,null,null, true, null);
                        break;
                    case Constantes.REQUEST_ADDNODE:
                        JSONObject NuevoBloque = (JSONObject) Recibir.readObject();
                        System.out.println("Solicitud de sincronizar un nuevo bloque");
                        if (JSONCreator.validateBlock(NuevoBloque, LibraryManager.getBlockChain())) {
                            System.out.println("El Nuevo Bloque es valido y debo agregarlo");
                        }else{
                            System.out.println("El nodo esta corrupto, enviando confirmacion de error");
                            Enviar.writeObject(Constantes.REQUEST_ADDNODE_ERROR);
                        }
                        break;
                    case Constantes.REQUEST_CLOSESOCKET:
                        Client.close();
                        break;
                }
            }
            System.out.println("Conexion Perdida.");
            Client.close();
        } catch (SocketException sex) {
            System.out.println("Conexion Perdida.");
        } catch (IOException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
