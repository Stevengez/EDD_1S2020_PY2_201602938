/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import JSONCreator.Constantes;
import biblioteca.Biblioteca;
import javax.swing.ImageIcon;

/**
 *
 * @author Steven
 */
public class ReporteTH extends javax.swing.JInternalFrame {

    /**
     * Creates new form ReporteTH
     */
    private CentralGUI Centralgui;
    private Biblioteca LibraryManager;
    private String imagen_ruta = "";
    
    public ReporteTH(CentralGUI Centralgui) {
        this.Centralgui = Centralgui;
        this.LibraryManager = Centralgui.getLibraryManager();
        setName(Constantes.GUI_VENTANA_REPORTE_USUARIOS);
        initComponents();
        cargarImagen();
        pack();
    }
    
    public void refreshImagen(){
        ImagenUsuarios.setIcon(new ImageIcon(imagen_ruta));   
        ImagenUsuarios.setText("");
    }
    
    public void cargarImagen(){
        imagen_ruta = this.LibraryManager.getImpresora().ImprimirUsuarios();
        System.out.println("La imagen esta en: "+imagen_ruta);
        ImagenUsuarios.setIcon(new ImageIcon(imagen_ruta));   
        ImagenUsuarios.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        ImagenUsuarios = new javax.swing.JLabel();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        setBackground(new java.awt.Color(255, 255, 255));
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Reporte de Usuarios / Tabla Hash");
        setVisible(true);

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));

        ImagenUsuarios.setBackground(java.awt.SystemColor.controlLtHighlight);
        ImagenUsuarios.setText("jLabel1");
        ImagenUsuarios.setOpaque(true);
        ImagenUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ImagenUsuariosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(ImagenUsuarios);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 445, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 354, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ImagenUsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ImagenUsuariosMouseClicked
        imagen_ruta = this.LibraryManager.getImpresora().ImprimirUsuarios();
        System.out.println("La imagen esta en: "+imagen_ruta);
        ImagenUsuarios.setIcon(new ImageIcon(imagen_ruta));   
        ImagenUsuarios.setText("");
    }//GEN-LAST:event_ImagenUsuariosMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ImagenUsuarios;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
