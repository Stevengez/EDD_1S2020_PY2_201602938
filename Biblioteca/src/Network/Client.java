/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Network;

import JSONCreator.Constantes;
import JSONCreator.JSONCreator;
import biblioteca.Biblioteca;
import biblioteca.Estructuras.ArbolAVL;
import biblioteca.Estructuras.NodoSimple;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import org.json.simple.JSONObject;

/**
 *
 * @author Steven
 */
public class Client {

    private Socket Client;
    private NetworkManager NetManager;

    public Client(NetworkManager Context) {
        System.out.println("------ Cliente Creado --------");
        this.NetManager = Context;
        //connectTo(Context.getHostName(), Context.getPortOut(), Context.getServerPort());
    }

    public boolean connectTo(String hostname, int RemoteServerPort, JInternalFrame Context) {
        try {
            System.out.println("Cliente:: Comenzando conexion a: " + hostname + ":" + RemoteServerPort + " usando el puerto: " + NetManager.getClientPort());
            Client = new Socket();
            Client.bind(new InetSocketAddress(NetManager.getClientPort()));
            Client.setSoTimeout(Constantes.RED_DEFAULT_TIMEOUT);
            Client.connect(new InetSocketAddress(hostname, RemoteServerPort));
            return Client.isConnected();
        } catch (SocketException sex) {
            JOptionPane.showMessageDialog(Context, "Tiempo de espera agotado.");
        } catch (UnknownHostException ux) {
            System.out.println(ux.getMessage());
        } catch (IOException oex) {
            System.out.println(oex.getMessage());
        }
        return false;
    }

    public String requestNetworkNodes() {
        try {
            ObjectOutputStream Enviar = new ObjectOutputStream(Client.getOutputStream());
            System.out.println("Envie una solicitud de Nodos de Red");
            Enviar.writeObject(Constantes.REQUEST_NETWORKNODES);

            ObjectInputStream Recibir = new ObjectInputStream(Client.getInputStream());
            String Respuesta = (String) Recibir.readObject();
            return Respuesta;
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void requestAddNetworkNode(JInternalFrame Context) {
        NodoSimple peer = NetManager.getNetworkList().getHead().getSiguiente();
        int alcance = 0;
        boolean correcto = true;
        while (peer != null) {
            connectTo(peer.getIP(), peer.getServerPort(), Context);
            try {
                ObjectOutputStream Enviar = new ObjectOutputStream(Client.getOutputStream());
                System.out.println("Envie una solicitud de agregar nodo a: "+peer.getIP());
                Enviar.writeObject(Constantes.REQUEST_ADD_NETWORKNODE);
                Enviar.writeObject(JSONCreator.addMeRedOperation(JSONCreator.createApartBlock(), NetManager.getNetworkList()));
                requestCloseSocket();
            } catch (IOException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
            peer = peer.getSiguiente();
            alcance++;
        }
    }

    public void requestCloseSocket() {
        try {
            ObjectOutputStream Enviar = new ObjectOutputStream(Client.getOutputStream());
            System.out.println("Envie una solicitud de Cierre");
            Enviar.writeObject(Constantes.REQUEST_CLOSESOCKET);
            Client.close();
            System.out.println("Termine la Conexion A Peticion");
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void requestAddBlock(JSONObject Bloque, JInternalFrame Context) {
        NodoSimple peer = NetManager.getNetworkList().getHead().getSiguiente();
        int alcance = 0;
        boolean correcto = true;
        while (peer != null) {
            connectTo(peer.getIP(), peer.getServerPort(), Context);
            try {
                ObjectOutputStream Enviar = new ObjectOutputStream(Client.getOutputStream());
                Enviar.writeObject(Constantes.REQUEST_ADDNODE);
                Enviar.writeObject(Bloque);

                ObjectInputStream Recibir = new ObjectInputStream(Client.getInputStream());
                String Respuesta = (String) Recibir.readObject();
                if (Respuesta.equals(Constantes.REQUEST_ADDNODE_CONFIRMATION)) {
                    System.out.println("El Peer agrego el nodo");
                } else {
                    System.out.println("Un error al agregar el nodo");
                    correcto = false;
                }
                requestCloseSocket();
            } catch (IOException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
            alcance++;
            peer = peer.getSiguiente();
        }

        if (alcance == 0) {
            JOptionPane.showMessageDialog(Context, "Eres el unico nodo, no hace falta sincronizar en la red");
            NetManager.getLibraryManager().getBlockChain().AddNode(Bloque, false, true);
            JSONCreator.completeBlock();
        } else {
            if (correcto) {
                NetManager.getLibraryManager().getBlockChain().AddNode(Bloque, false, true);
                JSONCreator.completeBlock();
                if (alcance > 1) {
                    JOptionPane.showMessageDialog(Context, "Sincronize con " + alcance + " nodos de la red.");
                } else {
                    JOptionPane.showMessageDialog(Context, "Sincronize con " + alcance + " nodo de la red.");
                }
            }
        }
    }
}
