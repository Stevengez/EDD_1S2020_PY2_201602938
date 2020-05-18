/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Network;

import JSONCreator.Constantes;
import JSONCreator.JSONCreator;
import biblioteca.Estructuras.NodoSimple;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
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
    }

    public boolean connectTo(String hostname, int RemoteServerPort, JInternalFrame Context) {
        try {
            System.out.println("Cliente:: Comenzando conexion a: " + hostname + ":" + RemoteServerPort);
            Client = new Socket(hostname, RemoteServerPort);
            Client.setSoTimeout(Constantes.RED_DEFAULT_TIMEOUT);
            //Client.bind(new InetSocketAddress(NetManager.getHostName(),NetManager.getClientPort()));
            //Client.connect(new InetSocketAddress(hostname, RemoteServerPort));
            return Client.isConnected();
        } catch (SocketException sex) {
            JOptionPane.showMessageDialog(Context, "Tiempo de espera agotado: " + sex.getMessage());
        } catch (UnknownHostException ux) {
            System.out.println(ux.getMessage());
        } catch (IOException oex) {
            System.out.println(oex.getMessage());
        }
        return false;
    }

    public void requestCloseSocket() {
        try {
            ObjectOutputStream Enviar = new ObjectOutputStream(Client.getOutputStream());
            System.out.println("Cliente:: Envie una solicitud de Cierre");
            Enviar.writeObject(Constantes.REQUEST_CLOSESOCKET);
            Client.close();
            System.out.println("Cliente:: Termine la Conexion.");
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String requestNetworkNodes() {
        try {
            ObjectOutputStream Enviar = new ObjectOutputStream(Client.getOutputStream());
            System.out.println("Cliente:: Envie una solicitud de nodos en la red.");
            Enviar.writeObject(Constantes.REQUEST_NETWORKNODES);

            ObjectInputStream Recibir = new ObjectInputStream(Client.getInputStream());
            System.out.println("Cliente:: Recibi la lista de nodos en la red.");
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
                System.out.println("Cliente:: Envie una solicitud para unirme a la red a: " + peer.getIP());
                Enviar.writeObject(Constantes.REQUEST_ADD_NETWORKNODE);
                Enviar.writeObject(JSONCreator.addMeRedOperation(JSONCreator.createApartBlock(), NetManager.getNetworkList()));

                ObjectInputStream Recibir = new ObjectInputStream(Client.getInputStream());
                String respuesta = (String) Recibir.readObject();
                if (respuesta.equals(Constantes.REQUEST_ADD_NETWORKNODE_C)) {
                    System.out.println("Cliente:: " + peer.getIP() + " me agrego a la red.");
                } else {
                    System.out.println("Cliente:: " + peer.getIP() + " no me agrego a la red.");
                }
                requestCloseSocket();
            } catch (IOException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
            peer = peer.getSiguiente();
            alcance++;
        }
    }

    public void requestDelNetworkNode() {
        if (NetManager.getNetworkList().getHead() != null) {
            NodoSimple peer = NetManager.getNetworkList().getHead().getSiguiente();
            int alcance = 0;
            boolean correcto = true;
            while (peer != null) {
                connectTo(peer.getIP(), peer.getServerPort(), null);
                try {
                    ObjectOutputStream Enviar = new ObjectOutputStream(Client.getOutputStream());
                    System.out.println("Cliente:: Envie una solicitud para removerme de la red a: " + peer.getIP());
                    Enviar.writeObject(Constantes.REQUEST_DEL_NETWORKNODE);
                    Enviar.writeObject(JSONCreator.delMeRedOperation(JSONCreator.createApartBlock(), NetManager.getNetworkList()));

                    ObjectInputStream Recibir = new ObjectInputStream(Client.getInputStream());
                    String respuesta = (String) Recibir.readObject();
                    if (respuesta.equals(Constantes.REQUEST_DEL_NETWORKNODE_C)) {
                        System.out.println("Cliente:: " + peer.getIP() + " me elimino a la red.");
                    } else {
                        System.out.println("Cliente:: " + peer.getIP() + " no me elimino a la red.");
                    }
                } catch (IOException ex) {
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    try {
                        requestCloseSocket();
                    } catch (Exception a) {

                    }
                }
                peer = peer.getSiguiente();
                alcance++;
            }
        }
    }

    public void requestBlockSince(int Index, JInternalFrame Context) {
        try {
            ObjectOutputStream Enviar = new ObjectOutputStream(Client.getOutputStream());
            System.out.println("Cliente:: Envie una solicitud de todos los bloques desde el index " + Index);
            Enviar.writeObject(Constantes.REQUEST_BLOCKS_SINCE);
            Enviar.writeObject(Index);

            ObjectInputStream Recibir = new ObjectInputStream(Client.getInputStream());
            int loop = (int) Recibir.readObject();
            System.out.println("Ciente:: Recibi la respuesta, tengo " + loop + " bloques pendientes.");
            for (int a = 0; a < loop; a++) {
                JSONObject Bloque = (JSONObject) Recibir.readObject();
                if (JSONCreator.validateBlock(Bloque, this.NetManager.getLibraryManager().getBlockChain())) {
                    System.out.println("Cliente:: Sincronice el bloque con index: " + Bloque.get(Constantes.JSON_INDEX).toString());
                    this.NetManager.getLibraryManager().getBlockChain().AddNode(Bloque, true, false, true);
                } else {
                    System.out.println("Cliente:: Bloque corrupto (Index: " + Bloque.get(Constantes.JSON_INDEX).toString() + ").");
                }
            }
            requestCloseSocket();
            JOptionPane.showMessageDialog(Context, "Sincronize todos los bloques");
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void requestAddBlock(JSONObject Bloque, JInternalFrame Context) {
        NodoSimple peer = NetManager.getNetworkList().getHead().getSiguiente();
        int alcance = 0;
        boolean correcto = true;
        System.out.println("Cliente:: Genere un nuevo bloque, enviandolo a todos los nodos de la red.");
        while (peer != null) {
            if (connectTo(peer.getIP(), peer.getServerPort(), Context)) {
                try {
                    ObjectOutputStream Enviar = new ObjectOutputStream(Client.getOutputStream());
                    Enviar.writeObject(Constantes.REQUEST_ADDBLOCK);
                    Enviar.writeObject(Bloque);

                    ObjectInputStream Recibir = new ObjectInputStream(Client.getInputStream());
                    String Respuesta = (String) Recibir.readObject();
                    if (Respuesta.equals(Constantes.REQUEST_ADDBLOCK_CONFIRMATION)) {
                        System.out.println("Cliente:: " + peer.getIP() + " valido y agrego el nodo.");
                    } else {
                        correcto = false;
                    }
                    requestCloseSocket();
                } catch (IOException ex) {
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception os) {
                    System.out.println("Algo paso al enviar el bloque: "+os.getMessage());
                }
            }else{
                System.out.println("No pude conectarme a: "+peer.getIP());
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
            } else {
                JOptionPane.showMessageDialog(Context, "Cliente:: Un nodo reporto el bloque como invalido (Posible sincronizacion requerida)");
            }
        }
    }
}
