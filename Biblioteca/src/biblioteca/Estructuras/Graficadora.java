/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.Estructuras;

import biblioteca.Biblioteca;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Steven
 */
public class Graficadora {

    private Biblioteca LibraryManager;
    private String codigoGraphviz;
    private int puntero;

    public Graficadora(Biblioteca LibraryManager) {
        this.LibraryManager = LibraryManager;

    }

    /* Graficar Tabla Hash */
    public String ImprimirUsuarios() {
        FileOutputStream grafica = null;
        try {

            String property = "java.io.tmpdir";
            String tempDir = System.getProperty(property);

            String codigoGraphviz = "";
            codigoGraphviz = "digraph Usuarios{\n"
                    + "rankdir=TB;\n"
                    + "layout=dot;\n"
                    + "node[shape=\"rectangle\"];\n\n";
            int prev = -1;
            for (int x = 0; x < LibraryManager.getUsuarios().getCasilleros().length; x++) {
                if (LibraryManager.getUsuarios().getCasilleros()[x] != null) {
                    if (prev != -1) {
                        codigoGraphviz = codigoGraphviz + prev + " -> " + x + ";\n";
                    }
                    codigoGraphviz = codigoGraphviz + x + " [label=\"" + x + "\" group=0];\n";
                    SubNodoHash temp = LibraryManager.getUsuarios().getCasilleros()[x].getHead();
                    codigoGraphviz = codigoGraphviz + x + " -> " + temp.getCarnet() + ";\n";
                    String rank_carnes = "{rank=same; " + x + " ";
                    int group = 1;
                    while (temp != null) {
                        if (temp.getAnterior() != null) {
                            codigoGraphviz = codigoGraphviz + temp.getAnterior().getCarnet() + " -> " + temp.getCarnet() + ";\n";
                        }
                        codigoGraphviz = codigoGraphviz + temp.getCarnet() + " [label=\"" + temp.getCarnet()
                                + "\n Nombre: " + temp.getFullName() + "\n Password: " + temp.getPassword() + "\" group=" + group + "];\n";
                        rank_carnes = rank_carnes + temp.getCarnet() + " ";
                        temp = temp.getSiguiente();
                        group++;
                    }
                    rank_carnes = rank_carnes + "}\n";
                    codigoGraphviz = codigoGraphviz + "\n" + rank_carnes + " \n";
                    prev = x;
                }
            }

            codigoGraphviz = codigoGraphviz + "}";
            PrintStream p;
            grafica = new FileOutputStream(tempDir + "\\Usuarios.dot");
            p = new PrintStream(grafica);
            p.println(codigoGraphviz);
            p.close();
            Process runtime = Runtime.getRuntime().exec("cmd /c dot -Tpng " + tempDir + "\\Usuarios.dot -o " + tempDir + "\\Usuarios.png");
            runtime.waitFor();
            return tempDir + "\\Usuarios.png";
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Graficadora.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Graficadora.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(Graficadora.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                grafica.close();
            } catch (IOException ex) {
                Logger.getLogger(Graficadora.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return "";
    }

    public String ImprimirNodosRed() {
        FileOutputStream grafica = null;
        try {

            String property = "java.io.tmpdir";
            String tempDir = System.getProperty(property);

            String codigoGraphviz = "digraph NodosRED{\n"
                    + "rankdir=LR;\n"
                    + "layout=dot;\n"
                    + "node[shape=\"rectangle\"];\n\n";
            int prev = -1;
            NodoSimple temp = LibraryManager.getNetworkManager().getNetworkList().getHead();
            while (temp != null) {
                if (temp.getAnterior() != null) {
                    codigoGraphviz = codigoGraphviz + temp.getAnterior().getIP().replaceAll("\\.", "") + " -> " + temp.getIP().replaceAll("\\.", "") + ";\n";
                }
                codigoGraphviz = codigoGraphviz + temp.getIP().replaceAll("\\.", "") + " [label=\" IP: " + temp.getIP()
                        + "&#92;n Puerto: " + temp.getServerPort() + "\"];\n";
                
                temp = temp.getSiguiente();
            }

            codigoGraphviz = codigoGraphviz + "}";
            PrintStream p;
            grafica = new FileOutputStream(tempDir + "\\NodosRED.dot");
            p = new PrintStream(grafica);
            p.println(codigoGraphviz);
            p.close();
            Process runtime = Runtime.getRuntime().exec("cmd /c dot -Tpng " + tempDir + "\\NodosRED.dot -o " + tempDir + "\\NodosRED.png");
            runtime.waitFor();
            return tempDir + "\\NodosRED.png";
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Graficadora.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Graficadora.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(Graficadora.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                grafica.close();
            } catch (IOException ex) {
                Logger.getLogger(Graficadora.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return "";
    }
    
    public String ImprimirBlockChain() {
        FileOutputStream grafica = null;
        try {

            String property = "java.io.tmpdir";
            String tempDir = System.getProperty(property);

            String codigoGraphviz = "digraph BlockChain{\n"
                    + "rankdir=TB;\n"
                    + "layout=dot;\n"
                    + "node[shape=\"rectangle\"];\n\n";
            int prev = -1;
            ChainNode temp = LibraryManager.getBlockChain().getHead();
            while (temp != null) {
                if (temp.getPrev() != null) {
                    codigoGraphviz = codigoGraphviz + temp.getPrev().getIndex() + " -> " + temp.getIndex() + ";\n";
                }
                codigoGraphviz = codigoGraphviz + temp.getIndex() + " [label=\" Index: " + temp.getIndex()
                        + "&#92;n TimeStamp: " + temp.getTimeStamp()
                        + "&#92;n Nonce: " + temp.getNONCE()
                        + "&#92;n Previous Hash: " + temp.getPrevHash()
                        + "&#92;n Hash: " + temp.getHash()+ "\"];\n";
                
                temp = temp.getNext();
            }

            codigoGraphviz = codigoGraphviz + "}";
            PrintStream p;
            grafica = new FileOutputStream(tempDir + "\\BlockChain.dot");
            p = new PrintStream(grafica);
            p.println(codigoGraphviz);
            p.close();
            Process runtime = Runtime.getRuntime().exec("cmd /c dot -Tpng " + tempDir + "\\BlockChain.dot -o " + tempDir + "\\BlockChain.png");
            runtime.waitFor();
            return tempDir + "\\BlockChain.png";
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Graficadora.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Graficadora.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(Graficadora.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                grafica.close();
            } catch (IOException ex) {
                Logger.getLogger(Graficadora.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return "";
    }

    /* Graficar Arbol AVL */
    public String ImprimirCategorias(int Tipo) {
        NodoAVL temp = LibraryManager.getLibrero().getRaiz();
        if (temp == null) {
            return "";
        }
        FileOutputStream grafica = null;
        try {

            String property = "java.io.tmpdir";
            String tempDir = System.getProperty(property);

            if (Tipo == 0) {
                codigoGraphviz = "digraph Categorias{\n"
                        + "rankdir=TB;\n"
                        + "layout=dot;\n"
                        + "node[shape=\"rectangle\"];\n\n";
            } else {
                codigoGraphviz = "digraph Categorias{\n"
                        + "rankdir=LR;\n"
                        + "layout=dot;\n"
                        + "node[shape=\"rectangle\"];\n\n";
            }

            puntero = 0;
            switch (Tipo) {
                case 0:
                    codigoGraphviz = codigoGraphviz + "-1 [label=\"Arbol AVL\"];\n";
                    AVLTree(temp);
                    break;
                case 1:
                    codigoGraphviz = codigoGraphviz + "-1 [label=\"Pre-Order\"];\n";
                    codigoGraphviz = codigoGraphviz + "-1 -> 0;";
                    AVLPreOrder(temp);
                    break;
                case 2:
                    codigoGraphviz = codigoGraphviz + "-1 [label=\"En-Order\"];\n";
                    codigoGraphviz = codigoGraphviz + "-1 -> 0;";
                    AVLEnOrder(temp);
                    break;
                case 3:
                    codigoGraphviz = codigoGraphviz + "-1 [label=\"Post-Order\"];\n";
                    codigoGraphviz = codigoGraphviz + "-1 -> 0;";
                    AVLPostOrder(temp);
                    break;
                default:
                    System.out.println("Opcion Invalida");
                    break;
            }

            if (Tipo != 0) {
                codigoGraphviz = codigoGraphviz + puntero + " [label=\"FIN\"];\n";
            }

            codigoGraphviz = codigoGraphviz + "}";
            PrintStream p;
            switch (Tipo) {
                case 0:
                    grafica = new FileOutputStream(tempDir + "\\AVLTree.dot");
                    break;
                case 1:
                    grafica = new FileOutputStream(tempDir + "\\AVLCAT_PreOrder.dot");
                    break;
                case 2:
                    grafica = new FileOutputStream(tempDir + "\\AVLCAT_EnOrder.dot");
                    break;
                case 3:
                    grafica = new FileOutputStream(tempDir + "\\AVLCAT_PostOrder.dot");
                    break;
                default:
                    System.out.println("Opcion Invalida");
                    break;
            }

            p = new PrintStream(grafica);
            p.println(codigoGraphviz);
            p.close();
            Process runtime = null;
            switch (Tipo) {
                case 0:
                    runtime = Runtime.getRuntime().exec("cmd /c dot -Tpng " + tempDir + "\\AVLTree.dot -o " + tempDir + "\\AVLTree.png");
                    runtime.waitFor();
                    return tempDir + "\\AVLTree.png";
                case 1:
                    runtime = Runtime.getRuntime().exec("cmd /c dot -Tpng " + tempDir + "\\AVLCAT_PreOrder.dot -o " + tempDir + "\\AVLCAT_PreOrder.png");
                    runtime.waitFor();
                    return tempDir + "\\AVLCAT_PreOrder.png";
                case 2:
                    runtime = Runtime.getRuntime().exec("cmd /c dot -Tpng " + tempDir + "\\AVLCAT_EnOrder.dot -o " + tempDir + "\\AVLCAT_EnOrder.png");
                    runtime.waitFor();
                    return tempDir + "\\AVLCAT_EnOrder.png";
                case 3:
                    runtime = Runtime.getRuntime().exec("cmd /c dot -Tpng " + tempDir + "\\AVLCAT_PostOrder.dot -o " + tempDir + "\\AVLCAT_PostOrder.png");
                    runtime.waitFor();
                    return tempDir + "\\AVLCAT_PostOrder.png";
                default:
                    runtime = Runtime.getRuntime().exec("cmd /c dot -Tpng " + tempDir + "\\AVLCAT_EnOrder.dot -o " + tempDir + "\\AVLCAT_EnOrder.png");
                    runtime.waitFor();
                    return tempDir + "\\AVLCAT_EnOrder.png";
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Graficadora.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Graficadora.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(Graficadora.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                grafica.close();
            } catch (IOException ex) {
                Logger.getLogger(Graficadora.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return "";
    }

    public void AVLTree(NodoAVL Padre) {

        codigoGraphviz = codigoGraphviz + Padre.getData().getNombre().replaceAll(" ", "_") + "[label=\"Categoria: " + Padre.getData().getNombre() + "\nLibros: " + Padre.getData().getLibrero().getSize() + "\"];\n";

        if (Padre.getIzquierda() != null) {
            codigoGraphviz = codigoGraphviz + Padre.getData().getNombre().replaceAll(" ", "_") + " -> " + Padre.getIzquierda().getData().getNombre().replaceAll(" ", "_") + ";";
            AVLTree(Padre.getIzquierda());
        }

        if (Padre.getDerecha() != null) {
            codigoGraphviz = codigoGraphviz + Padre.getData().getNombre().replaceAll(" ", "_") + " -> " + Padre.getDerecha().getData().getNombre().replaceAll(" ", "_") + ";";
            AVLTree(Padre.getDerecha());
        }
    }

    public void AVLPreOrder(NodoAVL Padre) {

        codigoGraphviz = codigoGraphviz + puntero + "[label=\"Categoria: " + Padre.getData().getNombre() + "\nLibros: " + Padre.getData().getLibrero().getSize() + "\"];\n";
        codigoGraphviz = codigoGraphviz + puntero + " -> " + (puntero + 1) + ";";
        puntero++;

        if (Padre.getIzquierda() != null) {
            AVLPreOrder(Padre.getIzquierda());
        }

        if (Padre.getDerecha() != null) {
            AVLPreOrder(Padre.getDerecha());
        }
    }

    public void AVLEnOrder(NodoAVL Padre) {

        if (Padre.getIzquierda() != null) {
            AVLEnOrder(Padre.getIzquierda());
        }

        codigoGraphviz = codigoGraphviz + puntero + "[label=\"Categoria: " + Padre.getData().getNombre() + "\nLibros: " + Padre.getData().getLibrero().getSize() + "\"];\n";
        codigoGraphviz = codigoGraphviz + puntero + " -> " + (puntero + 1) + ";";
        puntero++;

        if (Padre.getDerecha() != null) {
            AVLEnOrder(Padre.getDerecha());
        }
    }

    public void AVLPostOrder(NodoAVL Padre) {
        if (Padre.getIzquierda() != null) {
            AVLPostOrder(Padre.getIzquierda());
        }

        if (Padre.getDerecha() != null) {
            AVLPostOrder(Padre.getDerecha());
        }

        codigoGraphviz = codigoGraphviz + puntero + "[label=\"Categoria: " + Padre.getData().getNombre() + "\nLibros: " + Padre.getData().getLibrero().getSize() + "\"];\n";
        codigoGraphviz = codigoGraphviz + puntero + " -> " + (puntero + 1) + ";";
        puntero++;
    }

    /* Graficar ArbolB */
    public String ImprimirLibros(String Nombre, ArbolB Librero) {
        FileOutputStream grafica = null;
        try {

            String property = "java.io.tmpdir";
            String tempDir = System.getProperty(property);

            codigoGraphviz = "digraph Libros{\n"
                    + "rankdir=TB;\n"
                    + "layout=dot;\n"
                    + "node[shape=record];\n\n";

            NodoB Raiz = Librero.getRaiz();
            if (Raiz == null) {
                System.out.println("El Arbol esta vacio.");
            } else {
                Niveles(Raiz);
            }

            codigoGraphviz = codigoGraphviz + "}";
            PrintStream p;
            grafica = new FileOutputStream(tempDir + "\\Libros" + Nombre.replaceAll(" ", "_") + ".dot");
            p = new PrintStream(grafica);
            p.println(codigoGraphviz);
            p.close();
            Process runtime = Runtime.getRuntime().exec("cmd /c dot -Tpng " + tempDir + "\\Libros" + Nombre.replaceAll(" ", "_") + ".dot -o " + tempDir + "\\Libros" + Nombre.replaceAll(" ", "_") + ".png");
            runtime.waitFor();
            return tempDir + "\\Libros" + Nombre.replaceAll(" ", "_") + ".png";
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Graficadora.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Graficadora.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(Graficadora.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                grafica.close();
            } catch (IOException ex) {
                Logger.getLogger(Graficadora.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return "";
    }

    public void Niveles(NodoB Izquierda) {
        NodoB temp = Izquierda;
        while (temp != null) {
            switch (temp.getKeySize()) {
                case 1:
                    codigoGraphviz = codigoGraphviz + "nodo" + temp.getKey1().getClave() + " [label=\"{{<f0> ISBN: " + temp.getKey1().getClave() + "&#92;n Titulo: " + divideName(temp.getKey1().getData().getTitle()) + " }           | { <f4> | <f5> }}\"];\n";
                    if (temp.getKey1().getMenores() != null) {
                        codigoGraphviz = codigoGraphviz + "nodo" + temp.getKey1().getClave() + ":f4 -> nodo" + temp.getKey1().getMenores().getKey1().getClave() + "\n";
                    }

                    if (temp.getKey1().getMayores() != null) {
                        codigoGraphviz = codigoGraphviz + "nodo" + temp.getKey1().getClave() + ":f5 -> nodo" + temp.getKey1().getMayores().getKey1().getClave() + "\n";
                    }
                    break;
                case 2:
                    codigoGraphviz = codigoGraphviz + "nodo" + temp.getKey1().getClave() + " [label=\"{{<f0> ISBN: " + temp.getKey1().getClave() + "&#92;n Titulo: " + divideName(temp.getKey1().getData().getTitle()) + " | <f1> ISBN: " + temp.getKey2().getClave() + "&#92;n Titulo: " + divideName(temp.getKey2().getData().getTitle()) + "  }           | { <f4> | <f5> | <f6> }}\"];\n";

                    if (temp.getKey1().getMenores() != null) {
                        codigoGraphviz = codigoGraphviz + "nodo" + temp.getKey1().getClave() + ":f4 -> nodo" + temp.getKey1().getMenores().getKey1().getClave() + "\n";
                    }

                    if (temp.getKey2().getMenores() != null) {
                        codigoGraphviz = codigoGraphviz + "nodo" + temp.getKey1().getClave() + ":f5 -> nodo" + temp.getKey2().getMenores().getKey1().getClave() + "\n";
                    }

                    if (temp.getKey2().getMayores() != null) {
                        codigoGraphviz = codigoGraphviz + "nodo" + temp.getKey1().getClave() + ":f6 -> nodo" + temp.getKey2().getMayores().getKey1().getClave() + "\n";
                    }
                    break;
                case 3:
                    codigoGraphviz = codigoGraphviz + "nodo" + temp.getKey1().getClave() + " [label=\"{{<f0> ISBN: " + temp.getKey1().getClave() + "&#92;n Titulo: " + divideName(temp.getKey1().getData().getTitle()) + " | <f1> ISBN: " + temp.getKey2().getClave() + "&#92;n Titulo: " + divideName(temp.getKey2().getData().getTitle()) + " | <f2> ISBN: " + temp.getKey3().getClave() + "&#92;n Titulo: " + divideName(temp.getKey3().getData().getTitle()) + " }           | { <f4> | <f5> | <f6> | <f7> }}\"];\n";

                    if (temp.getKey1().getMenores() != null) {
                        codigoGraphviz = codigoGraphviz + "nodo" + temp.getKey1().getClave() + ":f4 -> nodo" + temp.getKey1().getMenores().getKey1().getClave() + "\n";
                    }

                    if (temp.getKey2().getMenores() != null) {
                        codigoGraphviz = codigoGraphviz + "nodo" + temp.getKey1().getClave() + ":f5 -> nodo" + temp.getKey2().getMenores().getKey1().getClave() + "\n";
                    }

                    if (temp.getKey3().getMenores() != null) {
                        codigoGraphviz = codigoGraphviz + "nodo" + temp.getKey1().getClave() + ":f6 -> nodo" + temp.getKey3().getMenores().getKey1().getClave() + "\n";
                    }

                    if (temp.getKey3().getMayores() != null) {
                        codigoGraphviz = codigoGraphviz + "nodo" + temp.getKey1().getClave() + ":f7 -> nodo" + temp.getKey3().getMayores().getKey1().getClave() + "\n";
                    }
                    break;
                case 4:
                    codigoGraphviz = codigoGraphviz + "nodo" + temp.getKey1().getClave() + " [label=\"{{<f0> ISBN: " + temp.getKey1().getClave() + "&#92;n Titulo: " + divideName(temp.getKey1().getData().getTitle()) + " | <f1> ISBN: " + temp.getKey2().getClave() + "&#92;n Titulo: " + divideName(temp.getKey2().getData().getTitle()) + " | <f2> ISBN: " + temp.getKey3().getClave() + "&#92;n Titulo: " + divideName(temp.getKey3().getData().getTitle()) + " | <f3> ISBN: " + temp.getKey4().getClave() + "&#92;n Titulo: " + divideName(temp.getKey4().getData().getTitle()) + " }           | { <f4> | <f5> | <f6> | <f7> |<f8>}}\"];\n";
                    if (temp.getKey1().getMenores() != null) {
                        codigoGraphviz = codigoGraphviz + "nodo" + temp.getKey1().getClave() + ":f4 -> nodo" + temp.getKey1().getMenores().getKey1().getClave() + "\n";
                    }

                    if (temp.getKey2().getMenores() != null) {
                        codigoGraphviz = codigoGraphviz + "nodo" + temp.getKey1().getClave() + ":f5 -> nodo" + temp.getKey2().getMenores().getKey1().getClave() + "\n";
                    }

                    if (temp.getKey3().getMenores() != null) {
                        codigoGraphviz = codigoGraphviz + "nodo" + temp.getKey1().getClave() + ":f6 -> nodo" + temp.getKey3().getMenores().getKey1().getClave() + "\n";
                    }

                    if (temp.getKey4().getMenores() != null) {
                        codigoGraphviz = codigoGraphviz + "nodo" + temp.getKey1().getClave() + ":f7 -> nodo" + temp.getKey4().getMenores().getKey1().getClave() + "\n";
                    }

                    if (temp.getKey4().getMayores() != null) {
                        codigoGraphviz = codigoGraphviz + "nodo" + temp.getKey1().getClave() + ":f8 -> nodo" + temp.getKey4().getMayores().getKey1().getClave() + "\n";
                    }
                    break;
                default:
                    //System.out.println("Sin programar aun este caso.");
                    break;
            }
            temp = temp.getBB();
        }

        if (Izquierda.getKey1() != null && Izquierda.getKey1().getMenores() != null) {
            Niveles(Izquierda.getKey1().getMenores());
        }

    }

    public String divideName(String name) {
        String salida = "";
        if (name.length() > 25) {
            int a = 0;
            while (a < name.length()) {
                if ((a + 25) < name.length()) {
                    salida = salida + name.substring(a, a + 25) + "&#92;n";
                    a = a + 25;
                } else {
                    salida = salida + name.substring(a, a + (name.length() - a)) + "&#92;n";
                    a = a + (name.length() - a);
                }
            }
        } else {
            salida = name;
        }
        return salida;
    }

}
