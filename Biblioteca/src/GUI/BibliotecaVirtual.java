/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import JSONCreator.Constantes;
import biblioteca.Categoria;
import biblioteca.Estructuras.ArbolB;
import biblioteca.Libro;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 *
 * @author Steven
 */
public class BibliotecaVirtual extends javax.swing.JInternalFrame implements ActionListener {

    /**
     * Creates new form BibliotecaVirtual
     */
    private CentralGUI Centralgui;
    private boolean Global, ignoreChange = false, Filtro = false;
    private Categoria[] cats;

    public BibliotecaVirtual(CentralGUI Centralgui, boolean Global) {
        this.Centralgui = Centralgui;
        this.Global = Global;
        if (Global) {
            setTitle("Biblioteca Virtual");
            setName(Constantes.GUI_VENTANA_BIBLIOTECAVIRTUAL);
        } else {
            setTitle("Mi Biblioteca Virtual ("+Centralgui.getLibraryManager().getNetworkManager().getLoggedUser().getNombre()+")");
            setName(Constantes.GUI_VENTANA_MI_BIBLIOTECAVIRTUAL);
        }

        initComponents();

        setJMenuBar(crearMenu());
        updateCatList();
        CatList.setSelectedIndex(0);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        FIltroPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        BusquedaInput = new javax.swing.JTextField();
        Buscar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        CatList = new javax.swing.JComboBox<>();
        EliminarCategoria = new javax.swing.JButton();
        ScrollContainer = new javax.swing.JScrollPane();
        ResultadosPanel = new javax.swing.JPanel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setVisible(true);

        FIltroPanel.setBackground(java.awt.SystemColor.info);

        jLabel1.setText("Buscar por Titulo/ISBN: ");

        BusquedaInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BusquedaInputActionPerformed(evt);
            }
        });
        BusquedaInput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                BusquedaInputKeyReleased(evt);
            }
        });

        Buscar.setText("Buscar");
        Buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BuscarActionPerformed(evt);
            }
        });

        jLabel2.setText("Filtrar por Categoria: ");

        CatList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CatListActionPerformed(evt);
            }
        });

        EliminarCategoria.setText("Eliminar");
        EliminarCategoria.setEnabled(false);
        EliminarCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EliminarCategoriaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout FIltroPanelLayout = new javax.swing.GroupLayout(FIltroPanel);
        FIltroPanel.setLayout(FIltroPanelLayout);
        FIltroPanelLayout.setHorizontalGroup(
            FIltroPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FIltroPanelLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(FIltroPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(FIltroPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(BusquedaInput)
                    .addComponent(CatList, 0, 263, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(FIltroPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Buscar, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                    .addComponent(EliminarCategoria, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(176, Short.MAX_VALUE))
        );
        FIltroPanelLayout.setVerticalGroup(
            FIltroPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FIltroPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(FIltroPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(Buscar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(FIltroPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(BusquedaInput, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(FIltroPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(FIltroPanelLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(FIltroPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(CatList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, FIltroPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(EliminarCategoria)))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        ResultadosPanel.setLayout(new java.awt.GridLayout(0, 1, 0, 1));
        ScrollContainer.setViewportView(ResultadosPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(ScrollContainer)
                    .addComponent(FIltroPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(FIltroPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ScrollContainer, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CatListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CatListActionPerformed
        if (ignoreChange) {
            ignoreChange = false;
        } else {
            System.out.println("Hiciste algo con las categorias");
            if (CatList.getSelectedIndex() == 0) {
                mostrarLibros(Centralgui.getLibraryManager().getLibreroGlobal());
                EliminarCategoria.setEnabled(false);
            } else {
                if (Centralgui.getLibraryManager().getNetworkManager().getLoggedUser() != null) {
                    if (cats[CatList.getSelectedIndex() - 1].getPublisher() == Centralgui.getLibraryManager().getNetworkManager().getLoggedUser().getCarnet()) {
                        EliminarCategoria.setEnabled(true);
                    } else {
                        EliminarCategoria.setEnabled(false);
                    }
                } else {
                    EliminarCategoria.setEnabled(false);
                }

                mostrarLibros(cats[CatList.getSelectedIndex() - 1].getLibrero());

            }
        }
    }//GEN-LAST:event_CatListActionPerformed

    private void EliminarCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarCategoriaActionPerformed
        if (Centralgui.getLibraryManager().getNetworkManager().getLoggedUser() != null) {
            if (cats[CatList.getSelectedIndex() - 1].getPublisher() == Centralgui.getLibraryManager().getNetworkManager().getLoggedUser().getCarnet()) {
                Libro[] libros = Centralgui.getLibraryManager().getLibrero().EliminarNodo(cats[CatList.getSelectedIndex() - 1].getNombre(), false);
                if (libros != null) {
                    for (int x = 0; x < libros.length; x++) {
                        Centralgui.getLibraryManager().getLibreroGlobal().RemoveBook(libros[x].getISBN(), true);
                    }
                    System.out.println("Elimine una Categoria con " + libros.length + " libros.");
                }
                updateCatList();
            } else {
                EliminarCategoria.setEnabled(false);
                JOptionPane.showMessageDialog(this, "Esta categoria no te pertenece.");
            }
        } else {
            EliminarCategoria.setEnabled(false);
            JOptionPane.showMessageDialog(this, "Debes iniciar sesion para hacer esto.");

        }

    }//GEN-LAST:event_EliminarCategoriaActionPerformed

    private void BuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuscarActionPerformed
        if (this.BusquedaInput.getText() == "" || this.BusquedaInput.getText().matches("[\\s]+")) {
            JOptionPane.showMessageDialog(this, "El criterio de busqueda es invalido");
        } else {
            System.out.println("Hiciste algo con las categorias");
            if (CatList.getSelectedIndex() == 0) {
                mostrarLibros(Centralgui.getLibraryManager().getLibreroGlobal());
                EliminarCategoria.setEnabled(false);
            } else {
                if (Centralgui.getLibraryManager().getNetworkManager().getLoggedUser() != null) {
                    if (cats[CatList.getSelectedIndex() - 1].getPublisher() == Centralgui.getLibraryManager().getNetworkManager().getLoggedUser().getCarnet()) {
                        EliminarCategoria.setEnabled(true);
                    } else {
                        EliminarCategoria.setEnabled(false);
                    }
                } else {
                    EliminarCategoria.setEnabled(false);
                }
                mostrarLibros(cats[CatList.getSelectedIndex() - 1].getLibrero());
            }
        }
    }//GEN-LAST:event_BuscarActionPerformed

    private void BusquedaInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BusquedaInputActionPerformed

    }//GEN-LAST:event_BusquedaInputActionPerformed

    private void BusquedaInputKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BusquedaInputKeyReleased
        if (this.BusquedaInput.getText() == "" || this.BusquedaInput.getText().matches("[\\s]+")) {
            this.Filtro = false;
        } else {
            this.Filtro = true;
        }
    }//GEN-LAST:event_BusquedaInputKeyReleased

    private JMenuBar crearMenu() {
        JMenuBar menu_superior = new JMenuBar();
        JMenu menu_opcion_Opciones = new JMenu("Opciones");
        
        JMenuItem menu_opcion_cuenta = new JMenuItem("Mi Cuenta");
        JMenuItem menu_opcion_crear = new JMenuItem("Crear Libro");
        
        menu_opcion_cuenta.setActionCommand(Constantes.MENU_OPCION_Bilioteca_MyProfile);
        menu_opcion_crear.setActionCommand(Constantes.MENU_OPCION_Bilioteca_Create_NewBook);
        
        menu_opcion_cuenta.addActionListener(this);
        menu_opcion_crear.addActionListener(this);

        if (Global) {
            menu_opcion_crear.setEnabled(false);
            menu_opcion_cuenta.setEnabled(false);
        }

        menu_opcion_Opciones.add(menu_opcion_cuenta);
        menu_opcion_Opciones.add(menu_opcion_crear);
        menu_superior.add(menu_opcion_Opciones);
        return menu_superior;
    }

    private void updateCatList() {
        ignoreChange = true;
        CatList.removeAllItems();
        CatList.addItem("Sin Filtro");
        placeCategoriaList();
    }

    private void placeCategoriaList() {
        if (Global) {
            cats = Centralgui.getLibraryManager().getLibrero().getCatsArray();
            if (cats != null) {
                for (int x = 0; x < cats.length; x++) {
                    CatList.addItem(cats[x].getNombre());
                }
            }
        } else {
            cats = Centralgui.getLibraryManager().getLibrero().getCatsArray(Centralgui.getLibraryManager().getNetworkManager().getLoggedUser().getCarnet());
            if (cats != null) {
                for (int x = 0; x < cats.length; x++) {
                    CatList.addItem(cats[x].getNombre());
                }
            }
        }
    }

    private void mostrarLibros(ArbolB librero) {
        ResultadosPanel.removeAll();
        ResultadosPanel.setLayout(new GridLayout(0, 1));
        Libro[] libros = null;
        if (Filtro) {
            libros = librero.getBooksArray(BusquedaInput.getText());
        } else {
            libros = librero.getBooksArray();
        }

        if (libros != null) {
            for (int l = 0; l < libros.length; l++) {
                if (Global) {
                    FilaLibro nuevaFila = new FilaLibro(Centralgui, libros[l], ResultadosPanel);
                    ResultadosPanel.add(nuevaFila);
                } else {
                    if (libros[l].getIDAuthor() == Centralgui.getLibraryManager().getNetworkManager().getLoggedUser().getCarnet()) {
                        FilaLibro nuevaFila = new FilaLibro(Centralgui, libros[l], ResultadosPanel);
                        ResultadosPanel.add(nuevaFila);
                    }
                }
            }
        }

        System.out.println("Termine, vamos a repintar.");
        pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case Constantes.MENU_OPCION_Bilioteca_MyProfile:
                JInternalFrame Mi_Perfil = Centralgui.existWindow(Centralgui.getDesktop(), Constantes.GUI_VENTANA_BIBLIOTECAVIRTUAL);
                if (Mi_Perfil != null) {
                    Centralgui.ActivateFrame(Mi_Perfil);
                } else {
                    Mi_Perfil = new MiPerfil(Centralgui, this);
                    Centralgui.getDesktop().add(Mi_Perfil);
                    Centralgui.ActivateFrame(Mi_Perfil);
                }
                break;
            case Constantes.MENU_OPCION_Bilioteca_Create_NewBook:
                    CrearLibro Nuevo_Libro;
                    if(CatList.getSelectedIndex()==0){
                         Nuevo_Libro = new CrearLibro(Centralgui);
                    }else{
                        Nuevo_Libro = new CrearLibro(Centralgui, cats[CatList.getSelectedIndex()-1].getNombre());
                    }
                    Centralgui.getDesktop().add(Nuevo_Libro);
                    Centralgui.ActivateFrame(Nuevo_Libro);
                break;
                
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Buscar;
    private javax.swing.JTextField BusquedaInput;
    private javax.swing.JComboBox<String> CatList;
    private javax.swing.JButton EliminarCategoria;
    private javax.swing.JPanel FIltroPanel;
    private javax.swing.JPanel ResultadosPanel;
    private javax.swing.JScrollPane ScrollContainer;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables

}
