/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import JSONCreator.Constantes;
import JSONCreator.JSONCreator;
import java.beans.PropertyVetoException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Steven
 */
public class BlockUpload extends javax.swing.JInternalFrame {

    /**
     * Creates new form BlockUpload
     */
    
    private CentralGUI Centralgui;
    public BlockUpload(CentralGUI Centralgui) {
        this.Centralgui = Centralgui;
        setName(Constantes.GUI_VENTANA_SYNCBLOCK);
        moveToFront();
        initComponents();
        SyncProgress.setMinimum(1);
        SyncProgress.setMaximum(5);
        
        getCurrentBlockValues();
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
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        CurrentBlockIndex = new javax.swing.JLabel();
        CurrentBlockOPN = new javax.swing.JLabel();
        CurrentBlockTS = new javax.swing.JLabel();
        CurrentBlockHS = new javax.swing.JLabel();
        SyncProgress = new javax.swing.JProgressBar();
        SyncButton = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        CurrentBlockNN = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        setTitle("Sincronizar Bloque con la red");
        setVisible(true);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Sincronizar Bloque con la RED");

        jLabel2.setText("Numero de Operaciones: ");

        jLabel3.setText("TimeStamp:");

        jLabel4.setText("Index:");

        jLabel5.setText("Hash:");

        CurrentBlockIndex.setBackground(new java.awt.Color(204, 204, 204));
        CurrentBlockIndex.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        CurrentBlockIndex.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        CurrentBlockIndex.setText("0");
        CurrentBlockIndex.setOpaque(true);

        CurrentBlockOPN.setBackground(new java.awt.Color(204, 204, 204));
        CurrentBlockOPN.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        CurrentBlockOPN.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        CurrentBlockOPN.setText("-");
        CurrentBlockOPN.setOpaque(true);

        CurrentBlockTS.setBackground(new java.awt.Color(204, 204, 204));
        CurrentBlockTS.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        CurrentBlockTS.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        CurrentBlockTS.setText("- - -");
        CurrentBlockTS.setOpaque(true);

        CurrentBlockHS.setBackground(new java.awt.Color(204, 204, 204));
        CurrentBlockHS.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        CurrentBlockHS.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        CurrentBlockHS.setText("- - - -");
        CurrentBlockHS.setOpaque(true);

        SyncButton.setText("Preparar");
        SyncButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SyncButtonActionPerformed(evt);
            }
        });

        jLabel6.setText("Nonce:");

        CurrentBlockNN.setBackground(new java.awt.Color(204, 204, 204));
        CurrentBlockNN.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        CurrentBlockNN.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        CurrentBlockNN.setText("-");
        CurrentBlockNN.setOpaque(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(SyncButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(SyncProgress, javax.swing.GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE)
                            .addComponent(CurrentBlockOPN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(CurrentBlockIndex, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(CurrentBlockTS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(CurrentBlockHS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(CurrentBlockNN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(CurrentBlockIndex))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(CurrentBlockTS))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(CurrentBlockNN))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(CurrentBlockOPN))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(CurrentBlockHS))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(SyncProgress, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(SyncButton, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void SyncButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SyncButtonActionPerformed
        JSONObject Bloque = JSONCreator.getCurrentBlock();
        if(SyncButton.getText().equals("Preparar")){
            SyncProgress.setValue(2);
            JSONCreator.prepareBlock(Centralgui.getVirtualLibrary().getBlockChain().getNextIndex(),Centralgui.getVirtualLibrary().getBlockChain().getLastHash());
            SyncProgress.setValue(4);
            CurrentBlockIndex.setText(Bloque.get(Constantes.JSON_INDEX).toString());
            CurrentBlockTS.setText(Bloque.get(Constantes.JSON_TIMESTAMP).toString());
            CurrentBlockNN.setText(Bloque.get(Constantes.JSON_NONCE).toString());
            int operaciones = ((JSONArray)Bloque.get(Constantes.JSON_DATA_LABEL)).size();
            CurrentBlockOPN.setText(operaciones+"");
            CurrentBlockHS.setText(Bloque.get(Constantes.JSON_HASH).toString());
            SyncProgress.setValue(5);
            if(operaciones >0){
                SyncButton.setText("Sincronizar");
            }else{
                JOptionPane.showMessageDialog(this, "No hay operaciones en el nuevo bloque, agregar, elimina o edita algo.");
            }
            SyncProgress.setValue(0);
        }else{
            Centralgui.getVirtualLibrary().getNetworkManager().getClient().requestAddNode(Bloque, this);
            SyncButton.setText("Preparar");
        }
    }//GEN-LAST:event_SyncButtonActionPerformed

    public void getCurrentBlockValues(){
        JSONObject Bloque = JSONCreator.getCurrentBlock();
        if(Bloque.get(Constantes.JSON_NONCE)!=null){
            SyncButton.setText("Sincronizar");
            CurrentBlockIndex.setText(Bloque.get(Constantes.JSON_INDEX).toString());
            CurrentBlockTS.setText(Bloque.get(Constantes.JSON_TIMESTAMP).toString());
            CurrentBlockNN.setText(Bloque.get(Constantes.JSON_NONCE).toString());
            CurrentBlockOPN.setText(((JSONArray)Bloque.get(Constantes.JSON_DATA_LABEL)).size()+"");
            CurrentBlockHS.setText(Bloque.get(Constantes.JSON_HASH).toString());
        }else{
            SyncButton.setText("Preparar");
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel CurrentBlockHS;
    private javax.swing.JLabel CurrentBlockIndex;
    private javax.swing.JLabel CurrentBlockNN;
    private javax.swing.JLabel CurrentBlockOPN;
    private javax.swing.JLabel CurrentBlockTS;
    private javax.swing.JButton SyncButton;
    private javax.swing.JProgressBar SyncProgress;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    // End of variables declaration//GEN-END:variables
}