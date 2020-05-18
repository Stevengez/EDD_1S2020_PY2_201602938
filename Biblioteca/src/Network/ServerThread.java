/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Network;

import JSONCreator.Constantes;
import JSONCreator.JSONCreator;
import biblioteca.Biblioteca;
import biblioteca.Estructuras.ChainNode;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
            System.out.println("Hilo/Servidor:: Conexion establecida desde: " + Client.getInetAddress());

            while (!Client.isClosed()) {
                ObjectInputStream Recibir = new ObjectInputStream(Client.getInputStream());
                String Request = (String) Recibir.readObject();
                ObjectOutputStream Enviar = new ObjectOutputStream(Client.getOutputStream());
                switch (Request) {
                    case Constantes.REQUEST_NETWORKNODES:
                        Enviar.writeObject(JSONCreator.addRedOperation(JSONCreator.createApartBlock(), NetManager.getNetworkList()).toJSONString());
                        break;
                    case Constantes.REQUEST_ADD_NETWORKNODE:
                        System.out.println("Hilo/Servidor:: Solicitud de Sincronizar un Nuevo Nodo de Red");
                        JSONObject nuevoNodo = (JSONObject) Recibir.readObject();
                        JSONCreator.parseDataBlock(nuevoNodo.toJSONString(), NetManager, null,null,null, true, true, null);
                        Enviar.writeObject(Constantes.REQUEST_ADD_NETWORKNODE_C);
                        System.out.println("Hilo/Servidor:: Agregue el Nodo de Red Solicitado");
                        break;
                    case Constantes.REQUEST_DEL_NETWORKNODE:
                        System.out.println("Hilo/Servidor:: Solicitud de Eliminar un Nodo de la Red");
                        JSONObject eliminarNodo = (JSONObject) Recibir.readObject();
                        JSONCreator.parseDataBlock(eliminarNodo.toJSONString(), NetManager, null,null,null, true, true, null);
                        Enviar.writeObject(Constantes.REQUEST_DEL_NETWORKNODE_C);
                        System.out.println("Hilo/Servidor:: Elimine el Nodo de Red Solicitado");
                        break;
                    case Constantes.REQUEST_BLOCKS_SINCE:
                        int Index = (int) Recibir.readObject();
                        ChainNode temp = LibraryManager.getBlockChain().getIndex(Index);
                        int Loop = LibraryManager.getBlockChain().getNextIndex()-Index;
                        Enviar.writeObject(Loop);
                        while(temp!=null){
                            Enviar.writeObject(temp.getData());
                            temp = temp.getNext();
                        }
                        System.out.println("Hilo/Servidor:: Envie todos los bloques desde el index: "+Index+".");
                        break;
                    case Constantes.REQUEST_ADDBLOCK:
                        JSONObject NuevoBloque = (JSONObject) Recibir.readObject();
                        System.out.println("Hilo/Servidor:: Recibiendo un nuevo bloque.");
                        if (JSONCreator.validateBlock(NuevoBloque, LibraryManager.getBlockChain())) {
                            System.out.println("Hilo/Servidor:: El nuevo bloque es valido, enviando confirmacion.");
                            Enviar.writeObject(Constantes.REQUEST_ADDBLOCK_CONFIRMATION);
                            LibraryManager.getBlockChain().AddNode(NuevoBloque, true, false,true);
                        }else{
                            System.out.println("Hilo/Servidor:: El nodo esta corrupto, enviando confirmacion de error.");
                            Enviar.writeObject(Constantes.REQUEST_ADDBLOCK_ERROR);
                        }
                        break;
                    case Constantes.REQUEST_CLOSESOCKET:
                        Client.close();
                        break;
                }
            }
            System.out.println("Hilo/Servidor:: Conexion Perdida.");
            Client.close();
        } catch (SocketException sex) {
            System.out.println("Hilo/Servidor:: Conexion Perdida: "+sex.getMessage());
        } catch (IOException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
