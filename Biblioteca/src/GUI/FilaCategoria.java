/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import biblioteca.Estructuras.ArbolB;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JInternalFrame;

/**
 *
 * @author Steven
 */
public class FilaCategoria extends javax.swing.JPanel {

    /**
     * Creates new form FilaCategoria
     */
    
    private String Nombre;
    private ArbolB Librero;
    private CentralGUI Centralgui;
    
    public FilaCategoria(String Nombre, ArbolB Reporte, CentralGUI Centralgui) {
        this.Nombre = Nombre;
        this.Librero = Reporte;
        this.Centralgui = Centralgui;
        initComponents();
        
        /* Configuracion */
        CategoryName.setText(Nombre);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        CategoryName = new javax.swing.JLabel();
        Reporte = new javax.swing.JButton();

        setBackground(java.awt.SystemColor.controlLtHighlight);

        CategoryName.setText("Hola como estas jaja");

        Reporte.setText("Ver Reporte");
        Reporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ReporteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(CategoryName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 139, Short.MAX_VALUE)
                .addComponent(Reporte)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CategoryName)
                    .addComponent(Reporte))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void ReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ReporteActionPerformed
        JInternalFrame ventana = Centralgui.existWindow(Centralgui.getDesktop(), Nombre);
        
        if(ventana == null){
            ventana = new ReporteLibros(Centralgui, Nombre, Librero);
            Centralgui.getDesktop().add(ventana);
            Centralgui.getDesktop().getDesktopManager().activateFrame(ventana);
        }else{
            try {
                ventana.setIcon(false);
                ventana.show();
            } catch (PropertyVetoException ex) {
                Logger.getLogger(FilaCategoria.class.getName()).log(Level.SEVERE, null, ex);
            }
            Centralgui.getDesktop().getDesktopManager().activateFrame(ventana);
        }
    }//GEN-LAST:event_ReporteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel CategoryName;
    private javax.swing.JButton Reporte;
    // End of variables declaration//GEN-END:variables
}
