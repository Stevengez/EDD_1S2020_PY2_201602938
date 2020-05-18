/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import JSONCreator.Constantes;
import Network.NetworkManager;
import biblioteca.Categoria;
import biblioteca.Estructuras.Clave;
import biblioteca.Libro;
import javax.swing.JOptionPane;

/**
 *
 * @author Steven
 */
public class CrearLibro extends javax.swing.JInternalFrame {

    /**
     * Creates new form CrearLibro
     */

    private CentralGUI Centralgui;
    private NetworkManager NetManager;

    public CrearLibro(CentralGUI Centralgui) {
        this.Centralgui = Centralgui;
        this.NetManager = Centralgui.getLibraryManager().getNetworkManager();
        setName(Constantes.GUI_VENTANA_CREAR_USUARIO);
        initComponents();
        this.Carnet.setText(String.valueOf(NetManager.getLoggedUser().getCarnet()));

    }

    public CrearLibro(CentralGUI Centralgui, String Categoria) {
        this.Centralgui = Centralgui;
        this.NetManager = Centralgui.getLibraryManager().getNetworkManager();
        setName(Constantes.GUI_VENTANA_CREAR_USUARIO);
        initComponents();
        this.Carnet.setText(String.valueOf(NetManager.getLoggedUser().getCarnet()));
        this.Categoria.setText(Categoria);
        this.Categoria.setEditable(false);
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
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        Carnet = new javax.swing.JTextField();
        Idioma = new javax.swing.JTextField();
        Categoria = new javax.swing.JTextField();
        Edicion = new javax.swing.JTextField();
        Ano = new javax.swing.JTextField();
        Editorial = new javax.swing.JTextField();
        Autor = new javax.swing.JTextField();
        Titulo = new javax.swing.JTextField();
        ISBN = new javax.swing.JTextField();
        Agregar = new javax.swing.JButton();

        setBackground(java.awt.SystemColor.controlLtHighlight);
        setClosable(true);
        setIconifiable(true);
        setTitle("Crear Libro");
        setVisible(true);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Agregar un libro a la Biblioteca");

        jLabel2.setText("ISBN: ");

        jLabel3.setText("Titulo:");

        jLabel4.setText("Autor:");

        jLabel5.setText("Editorial:");

        jLabel6.setText("Año:");

        jLabel7.setText("Edicion: ");

        jLabel8.setText("Categoria:");

        jLabel9.setText("Idioma:");

        jLabel10.setText("Carnet (Publicador):");

        Carnet.setEditable(false);

        Agregar.setText("Agregar");
        Agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AgregarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel9)
                            .addComponent(jLabel8)
                            .addComponent(jLabel7)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Carnet)
                            .addComponent(Idioma, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                            .addComponent(Categoria)
                            .addComponent(Edicion)
                            .addComponent(Ano)
                            .addComponent(Editorial)
                            .addComponent(Autor)
                            .addComponent(Titulo)
                            .addComponent(ISBN)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(Agregar, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(ISBN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(Titulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(Autor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(Editorial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(Ano, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(Edicion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(Categoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(Idioma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(Carnet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(Agregar, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void AgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AgregarActionPerformed

        if (this.Centralgui.getLibraryManager().getNetworkManager().getLoggedUser() != null) {
            String Mensaje = "";
            if (this.Titulo.getText() == "" || this.Titulo.getText().matches("[\\s]+")) {
                Mensaje = Mensaje = "Titulo vacio o invalido.\n";
            }

            if (this.Autor.getText() == "" || this.Autor.getText().matches("[\\s]+")) {
                Mensaje = Mensaje = "Autor vacio o invalido.\n";
            }

            if (this.Editorial.getText() == "" || this.Editorial.getText().matches("[\\s]+")) {
                Mensaje = Mensaje = "Editorial vacia o invalida.\n";
            }

            if (!this.Ano.getText().matches("[0-9]+")) {
                Mensaje = Mensaje = "Año vacio o invalido.\n";
            }

            if (!this.Edicion.getText().matches("[0-9]+")) {
                Mensaje = Mensaje = "Edicion vacia o invalida.\n";
            }

            if (this.Idioma.getText() == "" || this.Idioma.getText().matches("[\\s]+")) {
                Mensaje = Mensaje = "Idioma vacio o invalido.\n";
            }

            if (Mensaje != "") {
                JOptionPane.showMessageDialog(this, Mensaje);
            } else {
                Libro nuevo = new Libro(Integer.parseInt(this.ISBN.getText()),this.Titulo.getText(),this.Autor.getText(),this.Editorial.getText(),Integer.parseInt(this.Ano.getText()),Integer.parseInt(this.Edicion.getText()),this.Categoria.getText(),this.Idioma.getText(),Integer.parseInt(this.Carnet.getText()));
                Clave conf = Centralgui.getLibraryManager().getLibreroGlobal().NewBook(nuevo, true);
                
                if(conf !=null){
                    Categoria nueva = Centralgui.getLibraryManager().getLibrero().BuscarYCrear(this.Categoria.getText(), Integer.parseInt(this.Carnet.getText()),false);
                    nueva.getLibrero().NewBook(nuevo,false);
                    JOptionPane.showMessageDialog(this, "Libro agregado a la biblioteca virtual");
                    Centralgui.getLibraryManager().getBlockChain().setPendingChange(true);
                    this.dispose();
                }else{
                    JOptionPane.showMessageDialog(this, "El ISBN debe ser unico, pero este ya existe.");
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Debes iniciar sesion.");
        }
    }//GEN-LAST:event_AgregarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Agregar;
    private javax.swing.JTextField Ano;
    private javax.swing.JTextField Autor;
    private javax.swing.JTextField Carnet;
    private javax.swing.JTextField Categoria;
    private javax.swing.JTextField Edicion;
    private javax.swing.JTextField Editorial;
    private javax.swing.JTextField ISBN;
    private javax.swing.JTextField Idioma;
    private javax.swing.JTextField Titulo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    // End of variables declaration//GEN-END:variables
}
