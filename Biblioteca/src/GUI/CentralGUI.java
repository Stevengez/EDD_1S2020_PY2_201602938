/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import JSONCreator.Constantes;
import biblioteca.Biblioteca;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
    private Biblioteca LibraryManager;

    public CentralGUI(Biblioteca LibraryManager) {
        this.LoggedIn = false;
        this.BootUp = false;

        setTitle("Biblioteca Virtual");
        setSize(1300, 700);
        setJMenuBar(crearMenu());

        super.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                LibraryManager.getBlockChain().generateBlockListFile();
                System.out.println("Cerrado desde la GUI");
                System.exit(0);
            }
        });

        MultiWindowDesk = new JDesktopPane();
        MultiWindowDeskManager = new DesktopManager_Modificado();
        MultiWindowDesk.setDesktopManager(MultiWindowDeskManager);
        getContentPane().add(MultiWindowDesk);

        MultiWindowDesk.add(new Settings(this, LibraryManager.getNetworkManager()));

        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        this.LibraryManager = LibraryManager;
    }

    public JDesktopPane getDesktop() {
        return this.MultiWindowDesk;
    }

    public Biblioteca getLibraryManager() {
        return this.LibraryManager;
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

        JMenuItem menu_opcion_CargaMasiva_Usuarios = new JMenuItem("Carga Masiva de Usuarios");
        JMenuItem menu_opcion_CargaMasiva_Libros = new JMenuItem("Carga Masiva de Libros");

        menu_opcion_CargaMasiva_Usuarios.setEnabled(false);
        menu_opcion_CargaMasiva_Libros.setEnabled(false);

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

        menu_opcion_CargaMasica.add(menu_opcion_CargaMasiva_Libros);
        menu_opcion_CargaMasica.add(menu_opcion_CargaMasiva_Usuarios);

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
        menu_opcion_CargaMasiva_Usuarios.setActionCommand(Constantes.MENU_OPCION_CargaMasiva_Usuarios);
        menu_opcion_CargaMasiva_Libros.setActionCommand(Constantes.MENU_OPCION_CargaMasiva_Libros);
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
        menu_opcion_CargaMasiva_Usuarios.addActionListener(this);
        menu_opcion_CargaMasiva_Libros.addActionListener(this);
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

        getJMenuBar().getMenu(0).getItem(1).setEnabled(false);
        getJMenuBar().getMenu(1).getItem(0).setEnabled(false);
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
        if (LibraryManager.getNetworkManager().getLoggedUser() != null) {
            enabledLoggedIn();
        } else if (LibraryManager.getNetworkManager().getUniqueNodeFlag() || LibraryManager.getNetworkManager().getSyncFlag()) {
            getJMenuBar().getMenu(0).getItem(0).setText("Iniciar Sesion");
            enableLoggedOut();
        } else {
            notSync();
        }
    }

    public void setLoggedIN() {
        this.LoggedIn = true;
    }

    public void setBootUpFlag() {
        this.BootUp = true;
    }

    public boolean getBootUpFlag() {
        return this.BootUp;
    }

    public JInternalFrame existWindow(JDesktopPane Ventanas, String windowName) {
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
                    ventana.show();
                    MultiWindowDeskManager.activateFrame(ventana);
                } catch (PropertyVetoException ex) {
                    Logger.getLogger(CentralGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void ActivateFrame(JInternalFrame ventana) {
        try {
            ventana.setIcon(false);
            ventana.show();
            MultiWindowDeskManager.activateFrame(ventana);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(CentralGUI.class.getName()).log(Level.SEVERE, null, ex);
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
            case Constantes.MENU_OPCION_Bilioteca_LogInOut:
                if (LibraryManager.getNetworkManager().getLoggedUser() == null) {
                    JInternalFrame log_in = existWindow(MultiWindowDesk, Constantes.GUI_VENTANA_LOGIN);
                    if (log_in != null) {
                        ActivateFrame(log_in);
                    } else {
                        log_in = new IniciarSesion(this);
                        MultiWindowDesk.add(log_in);
                        ActivateFrame(log_in);
                    }
                } else {
                    LibraryManager.getNetworkManager().setLogOutFlag();
                    getJMenuBar().getMenu(0).getItem(0).setText("Iniciar Sesion");
                    updateLogStatus();
                }

                break;
            case Constantes.MENU_OPCION_Bilioteca_MyVirtual:
                JInternalFrame Mi_biblioteca_virtual = existWindow(MultiWindowDesk, Constantes.GUI_VENTANA_BIBLIOTECAVIRTUAL);
                if (Mi_biblioteca_virtual != null) {
                    ActivateFrame(Mi_biblioteca_virtual);
                } else {
                    Mi_biblioteca_virtual = new BibliotecaVirtual(this, false);
                    MultiWindowDesk.add(Mi_biblioteca_virtual);
                    ActivateFrame(Mi_biblioteca_virtual);
                }
                break;
            case Constantes.MENU_OPCION_Bilioteca_Virtual:
                JInternalFrame biblioteca_virtual = existWindow(MultiWindowDesk, Constantes.GUI_VENTANA_BIBLIOTECAVIRTUAL);
                if (biblioteca_virtual != null) {
                    ActivateFrame(biblioteca_virtual);
                } else {
                    biblioteca_virtual =new BibliotecaVirtual(this, true); 
                    MultiWindowDesk.add(biblioteca_virtual);
                    ActivateFrame(biblioteca_virtual);
                }
                break;
            case Constantes.MENU_OPCION_Bilioteca_Opciones:
                JInternalFrame settings = existWindow(MultiWindowDesk, Constantes.GUI_VENTANA_OPCIONES);
                if (settings != null) {
                    ActivateFrame(settings);
                } else {
                    settings =new Settings(this, this.LibraryManager.getNetworkManager()); 
                    MultiWindowDesk.add(settings);
                    ActivateFrame(settings);
                }
                break;
            case Constantes.MENU_OPCION_CargaMasiva_Libros:
                JInternalFrame carga_libros = existWindow(MultiWindowDesk, Constantes.GUI_VENTANA_CARGA_LIBROS);
                if (carga_libros != null) {
                    ActivateFrame(carga_libros);
                } else {
                    carga_libros = new CargaLibros(this);
                    MultiWindowDesk.add(carga_libros);
                    ActivateFrame(carga_libros);
                }
                break;
            case Constantes.MENU_OPCION_CargaMasiva_Usuarios:
                JInternalFrame carga_usuario = existWindow(MultiWindowDesk, Constantes.GUI_VENTANA_CARGA_USUARIOS);
                if (carga_usuario != null) {
                    ActivateFrame(carga_usuario);
                } else {
                    carga_usuario = new CargaUsuarios(this);
                    MultiWindowDesk.add(carga_usuario);
                    ActivateFrame(carga_usuario);
                }
                break;
            case Constantes.MENU_OPCION_Reportes_TablaHash:
                JInternalFrame reporte_usuarios = existWindow(MultiWindowDesk, Constantes.GUI_VENTANA_REPORTE_USUARIOS);
                if (reporte_usuarios != null) {
                    ActivateFrame(reporte_usuarios);
                } else {
                    reporte_usuarios = new ReporteTH(this);
                    MultiWindowDesk.add(reporte_usuarios);
                    ActivateFrame(reporte_usuarios);
                }
                break;
            case Constantes.MENU_OPCION_Reportes_ArbolAVL:
                JInternalFrame reporte_avl = existWindow(MultiWindowDesk, Constantes.GUI_VENTANA_REPORTE_AVL);
                if (reporte_avl != null) {
                    ActivateFrame(reporte_avl);
                } else {
                    reporte_avl = new ReporteAVL(this);
                    MultiWindowDesk.add(reporte_avl);
                    ActivateFrame(reporte_avl);
                }
                break;
            case Constantes.MENU_OPCION_Reportes_ArbolB:
                JInternalFrame reporte_bi = existWindow(MultiWindowDesk, Constantes.GUI_VENTANA_REPORTE_BINDIVIDUAL);
                if (reporte_bi != null) {
                    ActivateFrame(reporte_bi);
                } else {
                    reporte_bi = new ReporteLibros(this, LibraryManager.getLibreroGlobal());
                    MultiWindowDesk.add(reporte_bi);
                    ActivateFrame(reporte_bi);
                }
                break;
            case Constantes.MENU_OPCION_BLOCKCHAIN_SYNC:
                JInternalFrame sync_block = existWindow(MultiWindowDesk, Constantes.GUI_VENTANA_SYNCBLOCK);
                if (sync_block != null) {
                    ActivateFrame(sync_block);
                } else {
                    sync_block = new BlockUpload(this);
                    MultiWindowDesk.add(sync_block);
                    ActivateFrame(sync_block);
                }
                break;
        }
    }
}
