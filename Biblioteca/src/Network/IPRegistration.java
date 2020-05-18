/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Network;

import JSONCreator.Constantes;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
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
    }

    public void createClient(JInternalFrame Parent) {
        
        try(DatagramSocket Client = new DatagramSocket(NetManager.getBroadCastClient())) {
            System.out.println("BroadCast:: Cliente creado correctamente en el puerto: "+String.valueOf(NetManager.getBroadCastClient()));
            Client.setBroadcast(true);
            InetAddress host = InetAddress.getByName(NetManager.getBroadCastMask());
            byte[] buffer = new byte[256];
            buffer = "R".getBytes();

            DatagramPacket LookUpPacket = new DatagramPacket(buffer, buffer.length, host, NetManager.getBroadCastServer());
            Client.send(LookUpPacket);

            Client.setSoTimeout(Constantes.RED_BROADCAST_DEFAULT_TIMEOUT);
            buffer = new byte[256];
            DatagramPacket Confirm = new DatagramPacket(buffer, buffer.length);
            Client.receive(Confirm);
            String SyncPort = new String(Confirm.getData(), Confirm.getOffset(), Confirm.getLength());    
            System.out.println("BroadCast-Cliente:: Consegui la IP de un Nodo Registrado: " + Confirm.getAddress().getHostAddress() + ":" + SyncPort);
            if(Confirm.getAddress().getHostAddress().equals(NetManager.getNetworkList().getHead().getIP())){
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
        } catch (IOException ex) {
            Logger.getLogger(IPRegistration.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void createServer() {
        try {
            System.out.println("BroadCast:: Server creado en el puerto"+String.valueOf(NetManager.getBroadCastServer()));
            DatagramSocket Server = new DatagramSocket(NetManager.getBroadCastServer());

            byte[] buffer = new byte[256];
            DatagramPacket request = new DatagramPacket(buffer, buffer.length);
            System.out.println("BroadCast-Server:: Esperando solicitud");
            while (RegistrarNuevasIP) {
                Server.receive(request);
                String respuesta = new String(request.getData(), request.getOffset(), request.getLength());
                InetAddress requestAddress = request.getAddress();
                int requestPort = request.getPort();

                System.out.println("BroadCast-Server:: "+requestAddress.getHostAddress() + ":" + requestPort+" solicito mi IP y mi puerto.");
                buffer = new byte[256];
                buffer = String.valueOf(NetManager.getServerPort()).getBytes();
                DatagramPacket response = new DatagramPacket(buffer, buffer.length, requestAddress, requestPort);
                Server.send(response);
            }

        } catch (SocketException ex) {
            JOptionPane.showMessageDialog(null, "BroadCast-Error:: "+ex.getMessage());
            Logger.getLogger(IPRegistration.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "BroadCast-Error:: "+ex.getMessage());
            Logger.getLogger(IPRegistration.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
