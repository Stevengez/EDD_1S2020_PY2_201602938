/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import JSONCreator.Constantes;
import JSONCreator.JSONCreator;
import Network.Client;
import Network.NetworkManager;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Steven
 */
public class Settings extends javax.swing.JInternalFrame {

    /**
     * Creates new form Settings
     */
    private CentralGUI Centralgui;
    private NetworkManager NetManager;
    private List<String> IPs;

    public Settings(CentralGUI Centralgui, NetworkManager Context) {
        this.setName(Constantes.GUI_VENTANA_OPCIONES);
        this.NetManager = Context;
        this.Centralgui = Centralgui;
        IPs = new ArrayList();
        initComponents();
        if (!Centralgui.getBootUpFlag()) {
            loadDefault();
            Centralgui.setBootUpFlag();
        }
        InterfazSelector();
        placeValues();

    }

    public void InterfazSelector() {
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface interfaz = interfaces.nextElement();
                Enumeration<InetAddress> direcciones = interfaz.getInetAddresses();
                while (direcciones.hasMoreElements()) {
                    InetAddress ip = direcciones.nextElement();
                    if (ip.getHostAddress().matches("[0-9][0-9]?[0-9]?\\.[0-9][0-9]?[0-9]?\\.[0-9][0-9]?[0-9]?\\.[0-9][0-9]?[0-9]?") && !ip.getHostAddress().matches("127\\.0\\.0\\.1|0\\.0\\.0\\.0")) {
                        Interfaces.addItem(interfaz.getDisplayName() + " : " + ip.getHostAddress());
                        IPs.add(ip.getHostAddress());
                    }
                }
            }
        } catch (SocketException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        ServerPortInput = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        ClientPortInput = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        IPSyncInput = new javax.swing.JTextField();
        PortSyncInput = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        Interfaces = new javax.swing.JComboBox<>();

        setIconifiable(true);
        setResizable(true);
        setTitle("Configuracion de la Red");
        setToolTipText("");
        try {
            setSelected(true);
        } catch (java.beans.PropertyVetoException e1) {
            e1.printStackTrace();
        }
        setVisible(true);

        jLabel1.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel1.setText("Esta es la configuracion que se utilizara para comunicarse con los Nodos de la red");

        jLabel2.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel2.setText("Puerto Servidor Local (Solicitudes Entrantes) :");

        ServerPortInput.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        ServerPortInput.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ServerPortInput.setText("8000");

        jLabel3.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel3.setText("Puerto Cliente Local (Origen de las Solicitudes) :");

        ClientPortInput.setEditable(false);
        ClientPortInput.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        ClientPortInput.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ClientPortInput.setText("System Select");

        jLabel4.setText("IP para Sincronizacion:");

        jLabel5.setText("Puerto:");

        IPSyncInput.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        IPSyncInput.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        IPSyncInput.setText("127.0.0.1");

        PortSyncInput.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        PortSyncInput.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        PortSyncInput.setText("8000");

        jButton1.setText("Buscar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Sincronizar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Guardar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel6.setText("Interfaz/ IP");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(IPSyncInput, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PortSyncInput, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Interfaces, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel1)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel3)
                                        .addComponent(jLabel2))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(ServerPortInput, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(ClientPortInput, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ServerPortInput, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(ClientPortInput)
                        .addGap(2, 2, 2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(Interfaces, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(IPSyncInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(PortSyncInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButton2))
                .addGap(18, 18, 18))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        NetManager.registerMySelf(IPs.get(Interfaces.getSelectedIndex()));
        NetManager.createClienteBroadCast(this);
        IPSyncInput.setText(NetManager.getSyncHostName());
        PortSyncInput.setText(String.valueOf(NetManager.getSyncPort()));
        Centralgui.updateLogStatus();
        if (NetManager.getSyncHostName().matches("(localhost)|(" + IPs.get(Interfaces.getSelectedIndex()) + ")|(0.0.0.0)|(127.0.0.1)")) {
            this.hide();
        }
        EnableInterfazChange();
    }//GEN-LAST:event_jButton1ActionPerformed

    public void EnableInterfazChange() {
        if (NetManager.getServerStatus()) {
            Interfaces.setEnabled(false);
        } else {
            Interfaces.setEnabled(true);
        }
    }

    //Save Button
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        Guardar();
        EnableInterfazChange();
        this.hide();
    }//GEN-LAST:event_jButton3ActionPerformed

    public void Guardar() {
        String errores = "";
        if (!ServerPortInput.getText().matches("[0-9]+")) {
            errores = errores + "El Puerto del Servidor es invalido o esta vacio.\n";
        }

        /*
        if (!ClientPortInput.getText().matches("[0-9]+")) {
            errores = errores + "El Puerto del Cliente es invalido o esta vacio.\n";
        }
         */
        if (!IPSyncInput.getText().matches("([0-9][0-9]?[0-9]?\\.[0-9][0-9]?[0-9]?\\.[0-9][0-9]?[0-9]?\\.[0-9][0-9]?[0-9]?)|localhost|LOCALHOST|Localhost|LocalHost")) {
            errores = errores + "La Direccion IP de sincronizacion esta incorrecta.\n";
        }

        if (!PortSyncInput.getText().matches("[0-9]+")) {
            errores = errores + "El Puerto de sincronizacion es invalido o esta vacio.\n";
        }

        if (errores != "") {
            JOptionPane.showMessageDialog(this, errores);
        } else {
            NetManager.setServerPort(Integer.parseInt(ServerPortInput.getText()));
            //NetManager.setClientPort(Integer.parseInt(ClientPortInput.getText()));
            NetManager.registerMySelf(IPs.get(Interfaces.getSelectedIndex()));
            NetManager.saveToConfigFile();
        }
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Guardar();
        if (IPSyncInput.getText().matches("localhost|LocalHost|Localhost|LOCALHOST|(127\\.0\\.0\\.1|)") || IPSyncInput.getText().matches(IPs.get(Interfaces.getSelectedIndex()))) {
            JOptionPane.showMessageDialog(this, "No te puedes sincronizar contigo mismo");
        } else {
            String errores = "";
            if (!IPSyncInput.getText().matches("([0-9][0-9]?[0-9]?\\.[0-9][0-9]?[0-9]?\\.[0-9][0-9]?[0-9]?\\.[0-9][0-9]?[0-9]?)|localhost|LOCALHOST|Localhost|LocalHost")) {
                errores = errores + "La Direccion IP de sincronizacion esta incorrecta.\n";
            }

            if (!PortSyncInput.getText().matches("[0-9]+")) {
                errores = errores + "El Puerto de sincronizacion es invalido o esta vacio.\n";
            }

            if (!errores.equals("")) {
                JOptionPane.showMessageDialog(this, errores);
            } else {
                if (NetManager.getClient().connectTo(IPSyncInput.getText(), Integer.parseInt(PortSyncInput.getText()), this)) {

                    /* Manejar Nodos de la Red */
                    String Respuesta = NetManager.getClient().requestNetworkNodes();
                    NetManager.getClient().requestCloseSocket();
                    JSONCreator.parseDataBlock(Respuesta, NetManager, null, null, null, false, false, this);

                    /* Registrarse en todos los nodos de la red */
                    NetManager.getClient().requestAddNetworkNode(this);

                    /* Solicitar todos los blockes pendientes */
                    if (NetManager.getClient().connectTo(IPSyncInput.getText(), Integer.parseInt(PortSyncInput.getText()), this)) {
                        NetManager.getClient().requestBlockSince(NetManager.getLibraryManager().getBlockChain().getNextIndex(), this);
                    }

                    /* Estado Sincronizado */
                    NetManager.setSyncFlag();
                    Centralgui.updateLogStatus();

                    JOptionPane.showMessageDialog(this, "Sincronizacion Completa");
                    this.hide();
                    NetManager.createServerBroadCast();
                    NetManager.createServer();
                    NetManager.getClient();
                } else {
                    System.out.println("No se pudo conectar");
                }
            }

        }
        EnableInterfazChange();
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField ClientPortInput;
    private javax.swing.JTextField IPSyncInput;
    private javax.swing.JComboBox<String> Interfaces;
    private javax.swing.JTextField PortSyncInput;
    private javax.swing.JTextField ServerPortInput;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    // End of variables declaration//GEN-END:variables

    private void loadDefault() {
        Path currentDir = Paths.get("").toAbsolutePath();

        File defaultsettings = new File(currentDir + "\\Config\\", "Settings.json");

        if (defaultsettings.exists()) {
            FileReader fileReader;
            JSONParser jsonParser = new JSONParser();
            try {
                FileReader reader = new FileReader(currentDir + "\\Config\\Settings.json");
                JSONParser jparser = new JSONParser();
                JSONObject Red = (JSONObject) jparser.parse(reader);
                JSONArray Datos = (JSONArray) Red.get(Constantes.JSON_RED_LABEL);

                for (Object actual : Datos) {
                    NetManager.setServerPort(Integer.parseInt(((JSONObject) actual).get(Constantes.RED_SERVER_PORT).toString()));
                    NetManager.setClientPort(Integer.parseInt(((JSONObject) actual).get(Constantes.RED_CLIENT_PORT).toString()));
                    NetManager.setHostName(((JSONObject) actual).get(Constantes.RED_HOSTNAME).toString());
                    NetManager.setBroadCastServer(Integer.parseInt(((JSONObject) actual).get(Constantes.RED_BROADCAST_PORTSERVER).toString()));
                    NetManager.setBroadCastClient(Integer.parseInt(((JSONObject) actual).get(Constantes.RED_BROADCAST_PORTCLIENT).toString()));
                    NetManager.setBroadCastMask(((JSONObject) actual).get(Constantes.RED_BROADCAST_MASK).toString());
                    NetManager.setSyncHost(((JSONObject) actual).get(Constantes.RED_HOST_SYNC).toString());
                    NetManager.setSyncPort(Integer.parseInt(((JSONObject) actual).get(Constantes.RED_PORT_SYNC).toString()));
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Settings.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Settings.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(Settings.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            File ConfigDir = new File(currentDir + "\\Config");
            if (!ConfigDir.exists()) {
                ConfigDir.mkdir(); // Creamos la carpeta de Config
            }
            NetManager.setServerPort(Constantes.RED_DEFAULT_SERVER_PORT);
            NetManager.setClientPort(Constantes.RED_DEFAULT_CLIENT_PORT);
            NetManager.setHostName(Constantes.RED_DEFAULT_HOSTNAME);
            NetManager.setBroadCastServer(Constantes.RED_DEFAULT_BROADCAST_PORTSERVER);
            NetManager.setBroadCastClient(Constantes.RED_DEFAULT_BROADCAST_PORTCLIENT);
            NetManager.setBroadCastMask(Constantes.RED_DEFAULT_BROADCAST_MASK);
            NetManager.setSyncHost(Constantes.RED_DEFAULT_HOST_SYNC);
            NetManager.setSyncPort(Constantes.RED_DEFAULT_PORT_SYNC);
            NetManager.saveToConfigFile();
        }
    }

    private void placeValues() {
        ServerPortInput.setText(String.valueOf(NetManager.getServerPort()));
        ClientPortInput.setText("System Select");
        IPSyncInput.setText(NetManager.getSyncHostName());
        PortSyncInput.setText(String.valueOf(NetManager.getSyncPort()));
    }
}
