/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import JSONCreator.Constantes;
import biblioteca.Estructuras.ArbolB;
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
public class ReporteLibros extends javax.swing.JInternalFrame {

    /**
     * Creates new form ReporteLibros
     */
    
    private CentralGUI Centralgui;
    private ArbolB Librero;
    private String Nombre;
    public ReporteLibros(CentralGUI Centralgui, ArbolB Librero) {
        this.Centralgui = Centralgui;
        this.Librero = Librero;
        this.Nombre = "";
        setName(Constantes.GUI_VENTANA_REPORTE_BINDIVIDUAL);
        initComponents();
        cargarImagen();
    }
    
    public ReporteLibros(CentralGUI Centralgui, String Nombre, ArbolB Librero) {
        this.Centralgui = Centralgui;
        this.Librero = Librero;
        this.Nombre = Nombre;
        setTitle("Libros de la Categoria: "+Nombre);
        setName(Nombre.replaceAll(" ","_"));
        initComponents();
        cargarImagen();
    }
    
    public void cargarImagen(){
        String ruta = Centralgui.getLibraryManager().getImpresora().ImprimirLibros(Nombre, Librero);
        try {   
            ArbolImagen.setIcon(new ImageIcon(ImageIO.read(new File(ruta))));
        } catch (IOException ex) {
            Logger.getLogger(ReporteTH.class.getName()).log(Level.SEVERE, null, ex);
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

        jScrollPane2 = new javax.swing.JScrollPane();
        ArbolImagen = new javax.swing.JLabel();

        setBackground(java.awt.SystemColor.controlLtHighlight);
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setVisible(true);

        ArbolImagen.setBackground(java.awt.SystemColor.controlLtHighlight);
        ArbolImagen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ArbolImagenMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(ArbolImagen);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 614, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 469, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ArbolImagenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ArbolImagenMouseClicked
        System.out.println("Clicaste la imagen.");
        String ruta = Centralgui.getLibraryManager().getImpresora().ImprimirLibros(Nombre,Librero);
        try {   
            ArbolImagen.setIcon(new ImageIcon(ImageIO.read(new File(ruta))));
        } catch (IOException ex) {
            Logger.getLogger(ReporteTH.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_ArbolImagenMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ArbolImagen;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
