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
    private Biblioteca Context;

    public CentralGUI(Biblioteca Context) {
        this.Context = Context;
        this.LoggedIn = false;
        this.BootUp = false;

        setTitle("Biblioteca Virtual");
        setSize(1300, 700);
        setJMenuBar(crearMenu());

        MultiWindowDesk = new JDesktopPane();
        MultiWindowDeskManager = new DesktopManager_Modificado();
        MultiWindowDesk.setDesktopManager(MultiWindowDeskManager);
        getContentPane().add(MultiWindowDesk);

        MultiWindowDesk.add(new Settings(this, Context.getNetworkManager()));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private JMenuBar crearMenu() {
        JMenuBar menu_superior = new JMenuBar();

        JMenu menu_opcion_Bilioteca = new JMenu("Biblioteca");
        JMenu menu_opcion_CargaMasica = new JMenu("Carga Masiva");
        JMenu menu_opcion_Reportes = new JMenu("Reportes");

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

        menu_superior.add(menu_opcion_Bilioteca);
        menu_superior.add(menu_opcion_CargaMasica);
        menu_superior.add(menu_opcion_Reportes);

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

        menu_superior.setEnabled(false);
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
        } else if (Context.getNetworkManager().getUniqueNodeFlag() || Context.getNetworkManager().getSyncFlag()) {
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

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case Constantes.MENU_OPCION_Bilioteca_Opciones:
                JInternalFrame[] Ventanas = MultiWindowDesk.getAllFrames();
                for (JInternalFrame ventana : Ventanas) {
                    if (ventana.getName().equals(Constantes.GUI_VENTANA_OPCIONES)) {
                        ventana.show();
                    }
                }
                break;
            case Constantes.MENU_OPCION_CargaMasica_Usuarios:
                System.out.println("Menu:: Carga Masiva de usuarios");

                break;
        }
    }
}
