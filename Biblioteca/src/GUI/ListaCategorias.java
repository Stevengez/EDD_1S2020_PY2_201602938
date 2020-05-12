/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import JSONCreator.Constantes;
import biblioteca.Biblioteca;
import biblioteca.Categoria;
import biblioteca.Estructuras.ArbolB;
import java.awt.Dimension;

/**
 *
 * @author Steven
 */
public class ListaCategorias extends javax.swing.JInternalFrame {

    /**
     * Creates new form ListaCategorias
     */
    private Biblioteca LibraryManager;
    private CentralGUI Centralgui;

    public ListaCategorias(CentralGUI Centralgui) {
        this.Centralgui = Centralgui;
        this.LibraryManager = Centralgui.getLibraryManager();
        setName(Constantes.GUI_VENTANA_REPORTE_BI_LISTA);
        initComponents();
        //IntraContenedor.setLayout(null);
        placetheCats();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jProgressBar1 = new javax.swing.JProgressBar();
        jScrollPane1 = new javax.swing.JScrollPane();
        ContenedorFilas = new javax.swing.JPanel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Lista de Categorias");
        setAutoscrolls(true);
        setVisible(true);

        ContenedorFilas.setBackground(new java.awt.Color(51, 51, 51));
        ContenedorFilas.setLayout(new java.awt.GridLayout(0, 1, 0, 1));
        jScrollPane1.setViewportView(ContenedorFilas);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
        );

        setBounds(0, 0, 410, 308);
    }// </editor-fold>//GEN-END:initComponents

    public void placetheCats() {
        Categoria[] arrayCategorias = LibraryManager.getLibrero().getCatsArray();
        if (arrayCategorias != null) {
            for (int x = 0; x < arrayCategorias.length; x++) {
                //System.out.println("Se Supone que agregue una fila.");
                ArbolB librero = arrayCategorias[x].getLibrero();
                String nombre = arrayCategorias[x].getNombre();

                FilaCategoria nueva_fila = new FilaCategoria(nombre, librero, Centralgui);
                /*
            if(x==0){ 
                nueva_fila.setBounds(25, 20, 350, 50);
                nueva_fila.setVisible(true);
            }else{
                nueva_fila.setBounds(25, (x*60)+20, 350, 50);
                nueva_fila.setVisible(true);
            }
                 */
                ContenedorFilas.add(nueva_fila);
                //ContenedorFilas.setBounds(ContenedorFilas.getX(), ContenedorFilas.getY(), ContenedorFilas.getWidth(), ContenedorFilas.getHeight()+80);
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel ContenedorFilas;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}