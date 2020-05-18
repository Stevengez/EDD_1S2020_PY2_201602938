/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import JSONCreator.Constantes;
import java.beans.PropertyVetoException;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;

/**
 *
 * @author Steven
 */
public class ReporteAVL extends javax.swing.JInternalFrame {

    /**
     * Creates new form ReporteAVL
     */
    private CentralGUI Centralgui;

    public ReporteAVL(CentralGUI Centralgui) {
        this.Centralgui = Centralgui;
        setName(Constantes.GUI_VENTANA_REPORTE_AVL);
        initComponents();
        String ruta = Centralgui.getLibraryManager().getImpresora().ImprimirCategorias(0);
        if (!ruta.equals("")) {
            try {
                ReporteContainer.setIcon(new ImageIcon(ImageIO.read(new File(ruta))));
            } catch (IOException ex) {
                Logger.getLogger(ReporteTH.class.getName()).log(Level.SEVERE, null, ex);
            }
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
        jPanel1 = new javax.swing.JPanel();
        ContenedorGraficas = new javax.swing.JPanel();
        ReporteContainer = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        Preorder = new javax.swing.JButton();
        Enorder = new javax.swing.JButton();
        Postorder = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Reporte del Arbol AVL");
        setVisible(true);

        jScrollPane1.setBackground(java.awt.SystemColor.controlLtHighlight);

        jPanel1.setBackground(java.awt.SystemColor.controlLtHighlight);

        ReporteContainer.setBackground(java.awt.SystemColor.controlLtHighlight);

        javax.swing.GroupLayout ContenedorGraficasLayout = new javax.swing.GroupLayout(ContenedorGraficas);
        ContenedorGraficas.setLayout(ContenedorGraficasLayout);
        ContenedorGraficasLayout.setHorizontalGroup(
            ContenedorGraficasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ContenedorGraficasLayout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(ReporteContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        ContenedorGraficasLayout.setVerticalGroup(
            ContenedorGraficasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ReporteContainer, javax.swing.GroupLayout.DEFAULT_SIZE, 346, Short.MAX_VALUE)
        );

        jLabel2.setText("Reporte de Categorias (Arbol AVL)");

        jButton1.setText("Arbol");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        Preorder.setText("PreOrder");
        Preorder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PreorderActionPerformed(evt);
            }
        });

        Enorder.setText("EnOrder");
        Enorder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EnorderActionPerformed(evt);
            }
        });

        Postorder.setText("PostOrder");
        Postorder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PostorderActionPerformed(evt);
            }
        });

        jButton2.setText("Categorias");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ContenedorGraficas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Preorder, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Enorder, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Postorder, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 15, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jButton1)
                    .addComponent(Preorder)
                    .addComponent(Enorder)
                    .addComponent(Postorder)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ContenedorGraficas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jScrollPane1.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 378, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void PreorderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PreorderActionPerformed
        String ruta = Centralgui.getLibraryManager().getImpresora().ImprimirCategorias(1);
        try {
            ReporteContainer.setIcon(new ImageIcon(ImageIO.read(new File(ruta))));
        } catch (IOException ex) {
            Logger.getLogger(ReporteTH.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_PreorderActionPerformed

    private void EnorderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EnorderActionPerformed
        String ruta = Centralgui.getLibraryManager().getImpresora().ImprimirCategorias(2);
        try {
            ReporteContainer.setIcon(new ImageIcon(ImageIO.read(new File(ruta))));
        } catch (IOException ex) {
            Logger.getLogger(ReporteTH.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_EnorderActionPerformed

    private void PostorderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PostorderActionPerformed
        String ruta = Centralgui.getLibraryManager().getImpresora().ImprimirCategorias(3);
        try {
            ReporteContainer.setIcon(new ImageIcon(ImageIO.read(new File(ruta))));
        } catch (IOException ex) {
            Logger.getLogger(ReporteTH.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_PostorderActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String ruta = Centralgui.getLibraryManager().getImpresora().ImprimirCategorias(0);
        try {
            ReporteContainer.setIcon(new ImageIcon(ImageIO.read(new File(ruta))));
        } catch (IOException ex) {
            Logger.getLogger(ReporteTH.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        JInternalFrame listado = Centralgui.existWindow(Centralgui.getDesktop(), Constantes.GUI_VENTANA_REPORTE_BI_LISTA);
        if (listado == null) {
            listado = new ListaCategorias(Centralgui);
            Centralgui.getDesktop().add(listado);
            Centralgui.getDesktop().getDesktopManager().activateFrame(listado);
        } else {
            try {
                listado.setIcon(false);
                listado.show();
                Centralgui.getDesktop().getDesktopManager().activateFrame(listado);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(ReporteAVL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel ContenedorGraficas;
    private javax.swing.JButton Enorder;
    private javax.swing.JButton Postorder;
    private javax.swing.JButton Preorder;
    private javax.swing.JLabel ReporteContainer;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
