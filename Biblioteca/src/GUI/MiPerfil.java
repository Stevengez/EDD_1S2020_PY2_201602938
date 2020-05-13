/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import JSONCreator.Constantes;
import JSONCreator.JSONCreator;
import biblioteca.Estructuras.SubNodoHash;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Steven
 */
public class MiPerfil extends javax.swing.JInternalFrame {

    /**
     * Creates new form MiPerfil
     */
    private CentralGUI Centralgui;
    private JInternalFrame MiBiblioteca;

    public MiPerfil(CentralGUI Centralgui, JInternalFrame Biblioteca) {
        this.Centralgui = Centralgui;
        this.MiBiblioteca = Biblioteca;
        setName(Constantes.GUI_VENTANA_MI_CUENTA);
        initComponents();
        this.jLabel1.setText("Hola, "+Centralgui.getLibraryManager().getNetworkManager().getLoggedUser().getNombre());
        placeValues();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        Nombre2 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        Foto = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        Nombre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        Apellido = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        Carnet = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        Password = new javax.swing.JPasswordField();
        Guardar = new javax.swing.JButton();
        Eliminar = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        Carrera = new javax.swing.JTextField();

        jLabel4.setText("Apellido :");

        setClosable(true);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Hola, [Nombre de Usuario]");

        Foto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Foto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/Default2.jpg"))); // NOI18N

        jLabel2.setText("Nombre :");

        Nombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NombreActionPerformed(evt);
            }
        });

        jLabel3.setText("Apellido :");

        jLabel5.setText("Carnet :");

        Carnet.setEditable(false);

        jLabel6.setText("Password :");

        Password.setText("jPasswordField1");

        Guardar.setText("Guardar");
        Guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GuardarActionPerformed(evt);
            }
        });

        Eliminar.setForeground(new java.awt.Color(255, 51, 51));
        Eliminar.setText("Eliminar");
        Eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EliminarActionPerformed(evt);
            }
        });

        jLabel7.setText("Carrera: ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Foto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                        .addComponent(Nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Apellido, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Carrera)
                            .addComponent(Carnet)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(22, 22, 22)
                        .addComponent(Password))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(Guardar, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Foto, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(Nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(Apellido, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(Carnet, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(Carrera, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Password, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Guardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Eliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void placeValues() {
        this.Nombre.setText(this.Centralgui.getLibraryManager().getNetworkManager().getLoggedUser().getNombre());
        this.Apellido.setText(this.Centralgui.getLibraryManager().getNetworkManager().getLoggedUser().getApellido());
        this.Carnet.setText(this.Centralgui.getLibraryManager().getNetworkManager().getLoggedUser().getCarnet() + "");
        this.Carrera.setText(this.Centralgui.getLibraryManager().getNetworkManager().getLoggedUser().getCarrera());
        this.Password.setText(this.Centralgui.getLibraryManager().getNetworkManager().getLoggedUser().getPassword());
    }

    private void NombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NombreActionPerformed

    private void GuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GuardarActionPerformed
        if (Centralgui.getLibraryManager().getNetworkManager().getLoggedUser() != null) {
            if (Integer.parseInt(this.Carnet.getText()) == Centralgui.getLibraryManager().getNetworkManager().getLoggedUser().getCarnet()) {
                SubNodoHash Yo = Centralgui.getLibraryManager().getUsuarios().getUser(Centralgui.getLibraryManager().getNetworkManager().getLoggedUser().getCarnet());
                boolean sinCambios = true;

                if (!this.Nombre.getText().equals(Yo.getNombre())) {
                    sinCambios = false;
                }

                if (!this.Apellido.getText().equals(Yo.getApellido())) {
                    sinCambios = false;
                }

                if (!this.Carrera.getText().equals(Yo.getCarrera())) {
                    sinCambios = false;
                }

                StringBuilder pass = new StringBuilder();
                pass.append(this.Password.getPassword());

                if (!pass.toString().equals(Yo.getPassword())) {
                    sinCambios = false;
                }

                if (!sinCambios) {
                    Yo.setNombre(this.Nombre.getText());
                    Yo.setApellido(this.Apellido.getText());
                    Yo.setCarrera(this.Carrera.getText());
                    Yo.setPassword(JSONCreator.getMD5From(pass.toString()));
                    JOptionPane.showMessageDialog(this, "Usuario Actualizado");
                    this.dispose();

                    /* Registrar Operacion */
                    JSONCreator.editUserOperation(JSONCreator.getCurrentBlock(), Yo);
                }
            } else {
                JOptionPane.showConfirmDialog(this, "Vuelve a iniciar sesion con este usuario.");
                this.dispose();
            }
        } else {
            JOptionPane.showConfirmDialog(this, "Vuelve a iniciar sesion.");
            this.dispose();
        }

    }//GEN-LAST:event_GuardarActionPerformed

    private void EliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarActionPerformed
        if (Centralgui.getLibraryManager().getNetworkManager().getLoggedUser() != null) {
            if (Integer.parseInt(this.Carnet.getText()) == Centralgui.getLibraryManager().getNetworkManager().getLoggedUser().getCarnet()) {
                int a = JOptionPane.showConfirmDialog(this, "Se eliminara tu cuenta sin eliminar ninguno de tus Libros o Categorias\nPor lo que nadie podra eliminarlas o editarlas nunca mas.\n Continuar?","Eliminar Usuario",JOptionPane.YES_NO_OPTION);
                if(a==0){
                    Centralgui.getLibraryManager().getUsuarios().delUser(Centralgui.getLibraryManager().getNetworkManager().getLoggedUser().getCarnet(), false);
                    JOptionPane.showMessageDialog(this, "Usuario eliminado con exito.");
                    this.dispose();
                    Centralgui.getLibraryManager().getNetworkManager().setLogOutFlag();
                    Centralgui.updateLogStatus();
                    MiBiblioteca.dispose();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Vuelve a iniciar sesion con este usuario.");
                this.dispose();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vuelve a iniciar sesion.");
            this.dispose();
        }
    }//GEN-LAST:event_EliminarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Apellido;
    private javax.swing.JTextField Carnet;
    private javax.swing.JTextField Carrera;
    private javax.swing.JButton Eliminar;
    private javax.swing.JLabel Foto;
    private javax.swing.JButton Guardar;
    private javax.swing.JTextField Nombre;
    private javax.swing.JTextField Nombre2;
    private javax.swing.JPasswordField Password;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    // End of variables declaration//GEN-END:variables
}