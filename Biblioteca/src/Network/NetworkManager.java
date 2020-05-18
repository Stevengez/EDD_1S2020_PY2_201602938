/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Network;

import GUI.Settings;
import JSONCreator.Constantes;
import biblioteca.Biblioteca;
import biblioteca.Estructuras.ListaSimple;
import biblioteca.Estructuras.SubNodoHash;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JInternalFrame;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Steven
 */
public class NetworkManager {

    private volatile boolean unicoNodo, BroadCastServerON, ServerON, Synchronized;
    private volatile SubNodoHash currentUser;
    private int ClientePort; // Puerto del Servidor del Nodo (Los otros nodos solicitaran conexion a este puerto)
    private int ServerPort; // Puerto del Cliente del Nodo (Este nodo usara este puerto para comenzar comunicacion)
    private Server Server;
    private Client Client;
    private String HostName;
    private String HostSync;
    private int PortSync;
    private int BroadCastServer, BroadCastClient;
    private String BroadcastMask;
    private ListaSimple NetworkNodes;
    private Biblioteca LibraryManager;

    public NetworkManager(Biblioteca Context) {
        unicoNodo = false;
        BroadCastServerON = false;
        ServerON = false;
        
        /* Nodos de la Red */
        NetworkNodes = new ListaSimple();
        
        /* Controlador Principal */
        this.LibraryManager = Context;
    }
    
    public void createServer(){
        if(!this.ServerON){
            this.ServerON = true;
            this.Server = new Server(LibraryManager);
            this.Server.start();
        }else{
            System.out.println("NetManager:: El Servidor ya fue creado.");
        }
    }
    
    public void shutdownServer(){
        this.ServerON = false;
    }
    
    public Client getClient(){
        if(this.Client ==null){
            this.Client = new Client(this);
        }
        return this.Client;
    }
    
    public void createServerBroadCast() {
        if (!this.BroadCastServerON) {
            this.BroadCastServerON = true;
            final NetworkManager esta = this;
            new Thread() {
                @Override
                public void run() {
                    IPRegistration recolector = new IPRegistration(esta);
                    recolector.createServer();
                }
            }.start();
        }else{
            System.out.println("NetManager:: Servidor broadcast ya fue creado");
        }
    }
    
    public void registerMySelf(String IP){
        getNetworkList().replaceHead(IP, getServerPort());
    }

    public void createClienteBroadCast(JInternalFrame Context) {
        IPRegistration recolector = new IPRegistration(this);
        recolector.createClient(Context);
    }
    
    public void saveToConfigFile() {
        Path currentDir = Paths.get("").toAbsolutePath();
        File ConfigDir = new File(currentDir + "\\Config");
        if (!ConfigDir.exists()) {
            ConfigDir.mkdir(); // Creamos la carpeta de Config
        }
        File defaultsettings = new File(currentDir + "\\Config\\", "Settings.json");
        JSONObject config = new JSONObject();
        JSONArray red = new JSONArray();
        JSONObject configuraciones = new JSONObject();

        configuraciones.put(Constantes.RED_SERVER_PORT, getServerPort());
        configuraciones.put(Constantes.RED_CLIENT_PORT, getClientPort());
        configuraciones.put(Constantes.RED_HOSTNAME, getHostName());
        configuraciones.put(Constantes.RED_BROADCAST_MASK, getBroadCastMask());
        configuraciones.put(Constantes.RED_BROADCAST_PORTSERVER, getBroadCastServer());
        configuraciones.put(Constantes.RED_BROADCAST_PORTCLIENT, getBroadCastClient());
        configuraciones.put(Constantes.RED_HOST_SYNC, Constantes.RED_DEFAULT_HOST_SYNC);
        configuraciones.put(Constantes.RED_PORT_SYNC, Constantes.RED_DEFAULT_PORT_SYNC);
        red.add(configuraciones);
        config.put(Constantes.JSON_RED_LABEL, red);
        writeToFile(defaultsettings.getAbsolutePath(), config.toJSONString());
    }

    public void writeToFile(String Path, String Contenido) {
        OutputStream newFile = null;
        try {
            newFile = new FileOutputStream(new File(Path));
            newFile.write(Contenido.getBytes(), 0, Contenido.length());
            newFile.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Settings.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Settings.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Biblioteca getLibraryManager() {
        return this.LibraryManager;
    }

    public ListaSimple getNetworkList() {
        return this.NetworkNodes;
    }

    public int getServerPort() {
        return this.ServerPort;
    }

    public int getClientPort() {
        return this.ClientePort;
    }

    public int getBroadCastServer() {
        return this.BroadCastServer;
    }

    public int getBroadCastClient() {
        return this.BroadCastClient;
    }

    public String getBroadCastMask() {
        return this.BroadcastMask;
    }

    public String getHostName() {
        return this.HostName;
    }

    public String getSyncHostName() {
        return this.HostSync;
    }

    public int getSyncPort() {
        return this.PortSync;
    }
    
    public void setUniqueNodeFlag() {
        this.unicoNodo = true;
    }

    public void setUniqueNodeFalse() {
        this.unicoNodo = false;
    }
    
    public boolean getUniqueNodeFlag(){
        return this.unicoNodo;
    }
    
    public void setLoggedFlag(SubNodoHash usuario){
        this.currentUser = usuario;
    }
    
    public void setLogOutFlag(){
        this.currentUser = null;
    }
    
    public SubNodoHash getLoggedUser(){
        return this.currentUser;
    }
    
    public void setSyncFlag() {
        this.Synchronized = true;
    }
    
    public boolean getSyncFlag(){
        return this.Synchronized;
    }

    public void setServerON() {
        this.ServerON = true;
    }

    public void setServerOFF() {
        this.ServerON = false;
    }

    public boolean isUnicoNodo() {
        return this.unicoNodo;
    }
    
    public void setServerPort(int Port) {
        this.ServerPort = Port;
    }

    public void setClientPort(int Port) {
        this.ClientePort = Port;
    }

    public void setHostName(String HostName) {
        this.HostName = HostName;
    }

    public void setSyncHost(String Host) {
        this.HostSync = Host;
    }

    public void setSyncPort(int Port) {
        this.PortSync = Port;
    }

    public void setBroadCastServer(int Port) {
        this.BroadCastServer = Port;
    }

    public void setBroadCastClient(int Port) {
        this.BroadCastClient = Port;
    }

    public void setBroadCastMask(String Mask) {
        this.BroadcastMask = Mask;
    }
    
    public boolean getServerStatus(){
        return this.ServerON;
    }
}
