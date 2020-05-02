/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Network;

import JSONCreator.Constantes;
import biblioteca.Biblioteca;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Steven
 */
public class IPRegistration {

    private NetworkManager NetManager;
    private volatile boolean RegistrarNuevasIP = true;

    public IPRegistration(NetworkManager Context) {
        this.NetManager = Context;
        System.out.println("Servicio Creado");
    }

    private void createIPList() {

    }

    public void createClient(JInternalFrame Parent) {
        
        try(DatagramSocket Client = new DatagramSocket(NetManager.getBroadCastClient())) {
            System.out.println("Socket Cliente Broadcast Creado en el puerto"+String.valueOf(NetManager.getBroadCastClient()));
            Client.setBroadcast(true);
            InetAddress host = InetAddress.getByName(NetManager.getBroadCastMask());
            byte[] buffer = new byte[256];
            buffer = "R".getBytes();

            DatagramPacket LookUpPacket = new DatagramPacket(buffer, buffer.length, host, NetManager.getBroadCastServer());
            Client.send(LookUpPacket);

            Client.setSoTimeout(Constantes.RED_DEFAULT_TIMEOUT);
            buffer = new byte[256];
            DatagramPacket Confirm = new DatagramPacket(buffer, buffer.length);
            Client.receive(Confirm);
            String SyncPort = new String(Confirm.getData(), Confirm.getOffset(), Confirm.getLength());    
            System.out.println("Consegui la IP de un Nodo Registrado: " + Confirm.getAddress().getHostAddress() + ":" + SyncPort);
            if(Confirm.getAddress().getHostAddress().equals(NetManager.getNetworkList().getHead().getIP())){
                System.out.println("La respuesta fue de mi mismo");
                buffer = new byte[256];
                Confirm = new DatagramPacket(buffer, buffer.length);
                Client.receive(Confirm);
                SyncPort = new String(Confirm.getData(), Confirm.getOffset(), Confirm.getLength());    
            }
            NetManager.setSyncHost(Confirm.getAddress().getHostAddress());
            NetManager.setSyncPort(Integer.parseInt(SyncPort));
            Client.close();
        } catch (SocketException ex) {
            Logger.getLogger(IPRegistration.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(IPRegistration.class.getName()).log(Level.SEVERE, null, ex);
        } catch(SocketTimeoutException tex){
            NetManager.setUniqueNodeFlag();
            JOptionPane.showMessageDialog(Parent, "No se encontro otra instancia, eres el primer Nodo");
            NetManager.createServerBroadCast();
            NetManager.createServer();
            NetManager.getClient();
            
            /* Manejo de la Interfaz */
            
            
            
        } catch (IOException ex) {
            Logger.getLogger(IPRegistration.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void createServer() {
        try {
            System.out.println("Socket Seerver Broadcast Creado en el puerto"+String.valueOf(NetManager.getBroadCastServer()));
            DatagramSocket Server = new DatagramSocket(NetManager.getBroadCastServer());

            byte[] buffer = new byte[256];
            DatagramPacket request = new DatagramPacket(buffer, buffer.length);
            System.out.println("Servicio Broadcast Esperando Solicitud");
            while (RegistrarNuevasIP) {
                Server.receive(request);
                String respuesta = new String(request.getData(), request.getOffset(), request.getLength());
                System.out.println("La respuesta es: " + respuesta);

                InetAddress requestAddress = request.getAddress();
                int requestPort = request.getPort();

                System.out.println("Recibi una solicitud de registro desde: " + requestAddress.getHostAddress() + ":" + requestPort);
                buffer = new byte[256];
                buffer = String.valueOf(NetManager.getServerPort()).getBytes();
                DatagramPacket response = new DatagramPacket(buffer, buffer.length, requestAddress, requestPort);
                Server.send(response);
            }

        } catch (SocketException ex) {
            Logger.getLogger(IPRegistration.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(IPRegistration.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
