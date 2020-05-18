/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import JSONCreator.Constantes;
import biblioteca.Biblioteca;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author Steven
 */
public class ReportNR extends javax.swing.JInternalFrame {

    /**
     * Creates new form ReportNR
     */
    private CentralGUI Centralgui;
    private Biblioteca LibraryManager;

    public ReportNR(CentralGUI Centralgui) {
        this.Centralgui = Centralgui;
        this.LibraryManager = Centralgui.getLibraryManager();
        setName(Constantes.GUI_VENTANA_REPORTE_NODOSRED);
        initComponents();
        String imagen_ruta = this.LibraryManager.getImpresora().ImprimirNodosRed();
        if (!imagen_ruta.equals("")) {
            System.out.println("Reporte:: Cargando la imagen desde: " + imagen_ruta);
            try {
                ImagenUsuarios.setIcon(new ImageIcon(ImageIO.read(new File(imagen_ruta))));
            } catch (IOException ex) {
                Logger.getLogger(ReportNR.class.getName()).log(Level.SEVERE, null, ex);
            }
            ImagenUsuarios.setText("");
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

        jScrollPane1 = new javax.swing.JScrollPane();
        ImagenUsuarios = new javax.swing.JLabel();

        setClosable(true);
        setResizable(true);
        setTitle("Reporte de Nodos de la Red");

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
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ImagenUsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ImagenUsuariosMouseClicked
        String imagen_ruta = this.LibraryManager.getImpresora().ImprimirNodosRed();
        System.out.println("Reporte:: Cargando la imagen desde: " + imagen_ruta);
        try {
            ImagenUsuarios.setIcon(new ImageIcon(ImageIO.read(new File(imagen_ruta))));
        } catch (IOException ex) {
            Logger.getLogger(ReportNR.class.getName()).log(Level.SEVERE, null, ex);
        }
        ImagenUsuarios.setText("");
    }//GEN-LAST:event_ImagenUsuariosMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ImagenUsuarios;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}