/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import JSONCreator.Constantes;
import JSONCreator.JSONCreator;
import biblioteca.Biblioteca;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.beans.PropertyVetoException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author Steven
 */
public class CentralGUI extends JFrame implements ActionListener {

    /* GUI Variables */
    private JDesktopPane MultiWindowDesk;
    private DesktopManager_Modificado MultiWindowDeskManager;
    private volatile boolean LoggedIn, BootUp;


    /* Data Source */
    private Biblioteca VirtualLibrary;

    public CentralGUI(Biblioteca VirtualLibrary) {
        this.VirtualLibrary = VirtualLibrary;
        this.LoggedIn = false;
        this.BootUp = false;

        setTitle("Biblioteca Virtual");
        setSize(1300, 700);
        setJMenuBar(crearMenu());
        super.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e){
                VirtualLibrary.getBlockChain().generateBlockListFile();
                System.out.println("Cerrado desde la GUI");
                System.exit(0);
            }
        });
        
        

        MultiWindowDesk = new JDesktopPane();
        MultiWindowDeskManager = new DesktopManager_Modificado();
        MultiWindowDesk.setDesktopManager(MultiWindowDeskManager);
        getContentPane().add(MultiWindowDesk);

        MultiWindowDesk.add(new Settings(this, VirtualLibrary.getNetworkManager()));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public Biblioteca getVirtualLibrary() {
        return this.VirtualLibrary;
    }

    private JMenuBar crearMenu() {
        JMenuBar menu_superior = new JMenuBar();

        JMenu menu_opcion_Bilioteca = new JMenu("Biblioteca");
        JMenu menu_opcion_CargaMasica = new JMenu("Carga Masiva");
        JMenu menu_opcion_Reportes = new JMenu("Reportes");
        JMenu menu_opcion_Bloques = new JMenu("Bloques");

        JMenuItem menu_opcion_Bilioteca_Sincronizar = new JMenuItem("Sincronizar Cambios");
        menu_opcion_Bilioteca_Sincronizar.setEnabled(false);

        JMenuItem menu_opcion_Bilioteca_LogInOut = new JMenuItem("Iniciar Sesion");
        JMenuItem menu_opcion_Bilioteca_MyVirtual = new JMenuItem("Mi Biblioteca");
        JMenuItem menu_opcion_Bilioteca_Virtual = new JMenuItem("Biblioteca Virtual");
        JMenuItem menu_opcion_Bilioteca_Opciones = new JMenuItem("Configuracion");
        JMenuItem menu_opcion_Bilioteca_salir = new JMenuItem("Salir");

        menu_opcion_Bilioteca_LogInOut.setEnabled(false);
        menu_opcion_Bilioteca_MyVirtual.setEnabled(false);
        menu_opcion_Bilioteca_Virtual.setEnabled(false);

        JMenuItem menu_opcion_CargaMasica_Usuarios = new JMenuItem("Carga Masiva de Libros");
        JMenuItem menu_opcion_CargaMasica_Libros = new JMenuItem("Carga Masiva de Usuarios");

        menu_opcion_CargaMasica_Usuarios.setEnabled(false);
        menu_opcion_CargaMasica_Libros.setEnabled(false);

        JMenuItem menu_opcion_Reportes_ArbolAVL = new JMenuItem("ArbolAVL");
        JMenuItem menu_opcion_Reportes_ArbolB = new JMenuItem("ArbolB");
        JMenuItem menu_opcion_Reportes_TablaHash = new JMenuItem("TablaHash");
        JMenuItem menu_opcion_Reportes_BlockCHain = new JMenuItem("BlockChain");
        JMenuItem menu_opcion_Reportes_NetworkList = new JMenuItem("Nodos de la Red");

        menu_opcion_Reportes_ArbolAVL.setEnabled(false);
        menu_opcion_Reportes_ArbolB.setEnabled(false);
        menu_opcion_Reportes_TablaHash.setEnabled(false);
        menu_opcion_Reportes_BlockCHain.setEnabled(false);
        menu_opcion_Reportes_NetworkList.setEnabled(false);

        menu_opcion_Bilioteca.add(menu_opcion_Bilioteca_LogInOut);
        menu_opcion_Bilioteca.add(menu_opcion_Bilioteca_MyVirtual);
        menu_opcion_Bilioteca.add(menu_opcion_Bilioteca_Virtual);
        menu_opcion_Bilioteca.add(menu_opcion_Bilioteca_Opciones);
        menu_opcion_Bilioteca.add(menu_opcion_Bilioteca_salir);

        menu_opcion_CargaMasica.add(menu_opcion_CargaMasica_Usuarios);
        menu_opcion_CargaMasica.add(menu_opcion_CargaMasica_Libros);

        menu_opcion_Reportes.add(menu_opcion_Reportes_ArbolAVL);
        menu_opcion_Reportes.add(menu_opcion_Reportes_ArbolB);
        menu_opcion_Reportes.add(menu_opcion_Reportes_TablaHash);
        menu_opcion_Reportes.add(menu_opcion_Reportes_BlockCHain);
        menu_opcion_Reportes.add(menu_opcion_Reportes_NetworkList);

        menu_opcion_Bloques.add(menu_opcion_Bilioteca_Sincronizar);

        menu_superior.add(menu_opcion_Bilioteca);
        menu_superior.add(menu_opcion_CargaMasica);
        menu_superior.add(menu_opcion_Reportes);
        menu_superior.add(menu_opcion_Bloques);

        /* Action Command */
        menu_opcion_Bilioteca_LogInOut.setActionCommand(Constantes.MENU_OPCION_Bilioteca_LogInOut);
        menu_opcion_Bilioteca_MyVirtual.setActionCommand(Constantes.MENU_OPCION_Bilioteca_MyVirtual);
        menu_opcion_Bilioteca_Virtual.setActionCommand(Constantes.MENU_OPCION_Bilioteca_Virtual);
        menu_opcion_Bilioteca_Opciones.setActionCommand(Constantes.MENU_OPCION_Bilioteca_Opciones);
        menu_opcion_Bilioteca_salir.setActionCommand(Constantes.MENU_OPCION_Bilioteca_salir);
        menu_opcion_CargaMasica_Usuarios.setActionCommand(Constantes.MENU_OPCION_CargaMasica_Usuarios);
        menu_opcion_CargaMasica_Libros.setActionCommand(Constantes.MENU_OPCION_CargaMasica_Usuarios);
        menu_opcion_Reportes_ArbolAVL.setActionCommand(Constantes.MENU_OPCION_Reportes_ArbolAVL);
        menu_opcion_Reportes_ArbolB.setActionCommand(Constantes.MENU_OPCION_Reportes_ArbolB);
        menu_opcion_Reportes_TablaHash.setActionCommand(Constantes.MENU_OPCION_Reportes_TablaHash);
        menu_opcion_Reportes_BlockCHain.setActionCommand(Constantes.MENU_OPCION_Reportes_BlockCHain);
        menu_opcion_Reportes_NetworkList.setActionCommand(Constantes.MENU_OPCION_Reportes_NetworkList);
        menu_opcion_Bilioteca_Sincronizar.setActionCommand(Constantes.MENU_OPCION_BLOCKCHAIN_SYNC);

        /* Listener */
        menu_opcion_Bilioteca_LogInOut.addActionListener(this);
        menu_opcion_Bilioteca_MyVirtual.addActionListener(this);
        menu_opcion_Bilioteca_Virtual.addActionListener(this);
        menu_opcion_Bilioteca_Opciones.addActionListener(this);
        menu_opcion_Bilioteca_salir.addActionListener(this);
        menu_opcion_CargaMasica_Usuarios.addActionListener(this);
        menu_opcion_CargaMasica_Libros.addActionListener(this);
        menu_opcion_Reportes_ArbolAVL.addActionListener(this);
        menu_opcion_Reportes_ArbolB.addActionListener(this);
        menu_opcion_Reportes_TablaHash.addActionListener(this);
        menu_opcion_Reportes_BlockCHain.addActionListener(this);
        menu_opcion_Reportes_NetworkList.addActionListener(this);
        menu_opcion_Bilioteca_Sincronizar.addActionListener(this);

        return menu_superior;
    }

    public void enableLoggedOut() {
        System.out.println("La cantidad de menus son: " + getJMenuBar().getMenuCount());
        getJMenuBar().getMenu(0).getItem(0).setEnabled(true);
        getJMenuBar().getMenu(0).getItem(2).setEnabled(true);

        getJMenuBar().getMenu(1).getItem(1).setEnabled(true);

        getJMenuBar().getMenu(2).getItem(0).setEnabled(true);
        getJMenuBar().getMenu(2).getItem(1).setEnabled(true);
        getJMenuBar().getMenu(2).getItem(2).setEnabled(true);
        getJMenuBar().getMenu(2).getItem(3).setEnabled(true);
        getJMenuBar().getMenu(2).getItem(4).setEnabled(true);

        getJMenuBar().getMenu(3).getItem(0).setEnabled(true);
    }

    public void enabledLoggedIn() {
        getJMenuBar().getMenu(0).getItem(0).setText("Cerrar Sesion");
        getJMenuBar().getMenu(0).getItem(1).setEnabled(true);

        getJMenuBar().getMenu(1).getItem(0).setEnabled(true);
    }

    public void notSync() {
        getJMenuBar().getMenu(0).getItem(0).setEnabled(false);
        getJMenuBar().getMenu(0).getItem(2).setEnabled(false);
        getJMenuBar().getMenu(1).getItem(1).setEnabled(false);
        getJMenuBar().getMenu(2).getItem(0).setEnabled(false);
        getJMenuBar().getMenu(2).getItem(1).setEnabled(false);
        getJMenuBar().getMenu(2).getItem(2).setEnabled(false);
        getJMenuBar().getMenu(2).getItem(3).setEnabled(false);
        getJMenuBar().getMenu(2).getItem(4).setEnabled(false);
        getJMenuBar().getMenu(0).getItem(1).setEnabled(false);
        getJMenuBar().getMenu(1).getItem(0).setEnabled(false);
    }

    public void updateLogStatus() {
        if (this.LoggedIn) {
            enabledLoggedIn();
        } else if (VirtualLibrary.getNetworkManager().getUniqueNodeFlag() || VirtualLibrary.getNetworkManager().getSyncFlag()) {
            getJMenuBar().getMenu(0).getItem(0).setText("Iniciar Sesion");
            enableLoggedOut();
        } else {
            notSync();
        }
    }

    public void setBootUpFlag() {
        this.BootUp = true;
    }

    public boolean getBootUpFlag() {
        return this.BootUp;
    }

    private JInternalFrame existWindow(JDesktopPane Ventanas, String windowName) {
        JInternalFrame[] ventanas = Ventanas.getAllFrames();
        for (JInternalFrame ventana : ventanas) {
            if (ventana.getName().equals(windowName)) {
                return ventana;
            }
        }
        return null;
    }

    public void showWindow(JDesktopPane Ventanas, String windowName) {
        JInternalFrame[] ventanas = Ventanas.getAllFrames();
        for (JInternalFrame ventana : ventanas) {
            if (ventana.getName().equals(windowName)) {
                try {
                    ventana.setIcon(false);
                    ventana.toFront();
                    ventana.show();                    
                } catch (PropertyVetoException ex) {
                    Logger.getLogger(CentralGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void hideWindow(JDesktopPane Ventanas, String windowName) {
        JInternalFrame[] ventanas = Ventanas.getAllFrames();
        for (JInternalFrame ventana : ventanas) {
            if (ventana.getName().equals(windowName)) {
                ventana.hide();
            }
        }
    }

    public void hideAllWindow(JDesktopPane Ventanas) {
        JInternalFrame[] ventanas = Ventanas.getAllFrames();
        for (JInternalFrame ventana : ventanas) {
            try {
                ventana.setIcon(true);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(CentralGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case Constantes.MENU_OPCION_Bilioteca_Opciones:
                showWindow(MultiWindowDesk, Constantes.GUI_VENTANA_OPCIONES);
                break;
            case Constantes.MENU_OPCION_CargaMasica_Usuarios:
                JInternalFrame carga_usuario = existWindow(MultiWindowDesk, Constantes.GUI_VENTANA_CARGA_USUARIOS);
                if (carga_usuario != null) {
                    carga_usuario.show();
                } else {
                    MultiWindowDesk.add(new Usuarios(this));
                }
                break;
            case Constantes.MENU_OPCION_BLOCKCHAIN_SYNC:
                JInternalFrame sync_block = existWindow(MultiWindowDesk, Constantes.GUI_VENTANA_SYNCBLOCK);
                if (sync_block != null) {
                    sync_block.show();
                } else {
                    MultiWindowDesk.add(new BlockUpload(this));
                }
                break;
        }
    }
}
