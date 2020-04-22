/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.Estructuras;

import biblioteca.Categoria;
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
public class ArbolAVL {

    private NodoAVL Raiz;
    private int Altura;
    private String PreOrderString;
    private int PreOrderInt;

    public ArbolAVL() {
        this.Raiz = null;
        this.Altura = 0;
    }

    public NodoAVL getRaiz() {
        return this.Raiz;
    }

    public NodoAVL InsertNew(Categoria Data) {
        if (Raiz == null) {
            NodoAVL nuevo = new NodoAVL(Data);
            Raiz = nuevo;
            System.out.println("Cree la Raiz: " + nuevo.getData().getNombre());
            return nuevo;
        } else {
            NodoAVL temp = Insert(Raiz, Data);
            if (temp == null) {
                System.out.println("Ya existe esta categoria.");
                return null;
            } else {
                return temp;
            }
        }
    }

    
    /* ---------------- INSERCION DE NUEVOS NODOS ---------------- */
    
    private NodoAVL Insert(NodoAVL Padre, Categoria Data) {
        int comparacion = Padre.getData().getNombre().compareToIgnoreCase(Data.getNombre());

        if (comparacion < 0) {
            if (Padre.getDerecha() == null) {
                NodoAVL nuevo = new NodoAVL(Data);
                if (Padre.getSubLevels() == 0) {
                    Padre.setbFact(1);
                    Padre.AddSubLevel();
                }
                //System.out.println("Colocando "+nuevo.getData().getNombre()+" a la Derecha de: "+Padre.getData().getNombre());
                Padre.setDerecha(nuevo);
                return nuevo;
            } else {
                NodoAVL temp = Insert(Padre.getDerecha(), Data);
                UpdateSubLevels(Padre);
                setBFact(Padre);
                Balanceo(Padre);
                return temp;
            }
        } else if (comparacion > 0) {
            if (Padre.getIzquierda() == null) {
                NodoAVL nuevo = new NodoAVL(Data);
                if (Padre.getSubLevels() == 0) {
                    Padre.setbFact(-1);
                    Padre.AddSubLevel();
                }
                Padre.setIzquierda(nuevo);
                return nuevo;
            } else {
                NodoAVL temp = Insert(Padre.getIzquierda(), Data);
                UpdateSubLevels(Padre);
                setBFact(Padre);
                Balanceo(Padre);
                return temp;
            }
        } else {
            return null;
        }
    }
    
    public void UpdateSubLevels(NodoAVL Nodo) {
        if (Nodo != null) {
            if (Nodo.getIzquierda() != null && Nodo.getDerecha() != null) {
                if (Nodo.getIzquierda().getSubLevels() + 1 > Nodo.getDerecha().getSubLevels() + 1) {
                    Nodo.setSubLevels(Nodo.getIzquierda().getSubLevels() + 1);
                    //System.out.println("Sume un subnivel a: "+Nodo.getData().getNombre()+" porque La izquierda era mas grande");
                } else if (Nodo.getDerecha().getSubLevels() + 1 > Nodo.getIzquierda().getSubLevels() + 1) {
                    //System.out.println("Sume un subnivel a: "+Nodo.getData().getNombre()+" porque La derecha era mas grande");
                    Nodo.setSubLevels(Nodo.getDerecha().getSubLevels() + 1);
                } else {
                    Nodo.setSubLevels(Nodo.getDerecha().getSubLevels() + 1);
                }
            } else {
                if (Nodo.getIzquierda() != null) {
                    Nodo.setSubLevels(Nodo.getIzquierda().getSubLevels() + 1);
                } else if (Nodo.getDerecha() != null) {
                    Nodo.setSubLevels(Nodo.getDerecha().getSubLevels() + 1);
                } else {
                    Nodo.setSubLevels(0);
                }
            }
        }
    }

    public void setBFact(NodoAVL Nodo) {
        if (Nodo != null) {
            if (Nodo.getIzquierda() != null && Nodo.getDerecha() != null) {
                Nodo.setbFact((Nodo.getDerecha().getSubLevels() + 1) - (Nodo.getIzquierda().getSubLevels() + 1));
                //System.out.println("Calculado en factor de:" + Nodo.getData().getNombre());
            } else if (Nodo.getIzquierda() != null) {
                Nodo.setbFact(-1 * (Nodo.getIzquierda().getSubLevels() + 1));
            } else if (Nodo.getDerecha() != null) {
                Nodo.setbFact(Nodo.getDerecha().getSubLevels() + 1);
            } else {
                Nodo.setbFact(0);
            }

            if (Nodo.getBFact() > 1 || Nodo.getBFact() < -1) {
                System.out.println(Nodo.getData().getNombre() + " requiere Balanceo.");
            }
        }
    }
    
    /* ---------------- BUSQUEDA DE NODOS ---------------- */

    public NodoAVL BuscarNodo(String Categoria) {
        if(this.Raiz==null){
            return null;
        }else{
            NodoAVL temp = Buscar(Raiz, new Categoria(Categoria));
            if(temp==null){
                System.out.println("No se encontro esa categoria.");
            }
            return temp;
        }
        
    }
    
    private NodoAVL Buscar(NodoAVL Padre, Categoria Data){
        int comparacion = Padre.getData().getNombre().compareToIgnoreCase(Data.getNombre());

        if (comparacion < 0) {
            if (Padre.getDerecha() == null) {
                return null;
            } else {
                return Buscar(Padre.getDerecha(), Data);
            }
        } else if (comparacion > 0) {
            if (Padre.getIzquierda() == null) {
                return null;
            } else {
                return Buscar(Padre.getIzquierda(), Data);
            }
        } else {
            return Padre;
        }
        
    }

    
    /* ---------------- ELIMINACION DE NODOS ---------------- */
    
    public void EliminarNodo(String Categoria){
        NodoAVL temp = Eliminar(this.Raiz, new Categoria(Categoria));
        if(temp==null){
            
        }
        
    }
    
    public NodoAVL Eliminar(NodoAVL Padre, Categoria Data){
        int comparacion = Padre.getData().getNombre().compareToIgnoreCase(Data.getNombre());

        if (comparacion < 0) {
            if (Padre.getDerecha() == null) {
                System.out.println("No Encontre el nodo a eliminar");
                return null;
            } else {
                return Eliminar(Padre.getDerecha(), Data);
            }
        } else if (comparacion > 0) {
            if (Padre.getIzquierda() == null) {
                System.out.println("No Encontre el nodo a eliminar");
                return null;
            } else {
                NodoAVL temp = Eliminar(Padre.getIzquierda(), Data);
                if(temp.isDeleted()){
                    
                }
                return temp;
            }
        } else {
            System.out.println("Encontre el nodo a eliminar");
            NodoAVL temp = Padre;
            if(Padre.getIzquierda()==null && Padre.getDerecha()==null){
                Padre.setEliminado(true);
                System.out.println("Eliminacion Simple, era Hoja.");
            }
            return null;
        }
    }

    /* ---------------- BALANCEO y ROTACIONES ---------------- */
    
    public void Balanceo(NodoAVL Nodo) {
        if (Nodo != null) {
            if (Nodo.getBFact() > 1) {
                if (Nodo.getDerecha().getBFact() < 0) {
                    System.out.println("Posible Doble Rotacion (Derecha, Izquierda).");
                    RDI(Nodo);
                } else {
                    System.out.println("Posible Rotacion Simple a la Izquierda.");
                    RSI(Nodo);
                }
            } else if (Nodo.getBFact() < -1) {
                if (Nodo.getIzquierda().getBFact() > 0) {
                    System.out.println("Posible Doble Rotacion (Izquierda, Derecha).");
                    RDD(Nodo);
                } else {
                    System.out.println("Posible Rotacion Simple a la Derecha.");
                    RSD(Nodo);
                }
            }
        }
    }

    public void RSD(NodoAVL Nodo) {
        /* Guardar Temporales */
        NodoAVL Primero = Nodo;
        NodoAVL PrimeroDerecha = Nodo.getDerecha();
        NodoAVL Segundo = Nodo.getIzquierda();
        NodoAVL SegundoDerecha = Segundo.getDerecha();
        NodoAVL Tercero = Segundo.getIzquierda();

        /* Intercambiar Datos del Primero y Segundo */
        Categoria tempPrimero = Primero.getData();
        Categoria tempSegundo = Segundo.getData();

        Primero.setData(tempSegundo);
        Segundo.setData(tempPrimero);

        /* Conecctar Valores */
        Segundo.setDerecha(PrimeroDerecha);
        Segundo.setIzquierda(SegundoDerecha);
        Primero.setDerecha(Segundo);
        Primero.setIzquierda(Tercero);

        /* ReAjustar SubNiveles y Balance Factor */
        UpdateSubLevels(Segundo);
        setBFact(Primero);
        setBFact(Segundo);
        System.out.println("Termine la rotacion (RSD)");
    }

    public void RSI(NodoAVL Nodo) {
        /* Guardar Temporales */
        NodoAVL Primero = Nodo;
        NodoAVL PrimeroIzquierda = Nodo.getIzquierda();
        NodoAVL Segundo = Nodo.getDerecha();
        NodoAVL SegundoIzquierda = Segundo.getIzquierda();
        NodoAVL Tercero = Segundo.getDerecha();

        /* Intercambiar Datos del Primero y Segundo */
        Categoria tempPrimero = Primero.getData();
        Categoria tempSegundo = Segundo.getData();

        Primero.setData(tempSegundo);
        Segundo.setData(tempPrimero);

        /* Conecctar Valores */
        Segundo.setIzquierda(PrimeroIzquierda);
        Segundo.setDerecha(SegundoIzquierda);
        Primero.setIzquierda(Segundo);
        Primero.setDerecha(Tercero);

        /* ReAjustar SubNiveles y Balance Factor */
        UpdateSubLevels(Tercero);
        UpdateSubLevels(Segundo);
        UpdateSubLevels(Primero);
        setBFact(Primero);
        setBFact(Segundo);
        setBFact(Tercero);
        System.out.println("Termine la rotacion (RSI)");
    }

    public void RDI(NodoAVL Nodo) {
        RSD(Nodo.getDerecha());
        RSI(Nodo);
        System.out.println("Termine la rotacion (RDI)");
    }

    public void RDD(NodoAVL Nodo) { // Izquierda, Derecha
        RSI(Nodo.getIzquierda());
        RSD(Nodo);
        System.out.println("Termine la rotacion (RDD)");
    }

    /* ---------------- Impresion Grafica del Arbol ---------------- */
    
    public void ImpresoraGrafica() {
        PreOrderString = "";
        PreOrderString = PreOrderString + "digraph arbolAVL{\n";
        PreOrderString = PreOrderString + "rankdir=TB;\n";
        PreOrderString = PreOrderString + "layout=dot;\n";
        NodoAVL temp = this.Raiz;
        Normalize(temp);
        PreOrderString = PreOrderString + "}";
        try {
            FileOutputStream grafica;
            grafica = new FileOutputStream("Categorias.dot");
            PrintStream P = new PrintStream(grafica);
            P.println(PreOrderString);
            P.close();
            Process runtime = Runtime.getRuntime().exec("cmd /c dot -Tpng " + System.getProperty("user.dir") + "\\Categorias.dot -o " + System.getProperty("user.dir") + "\\Categorias.png");
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ArbolAVL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ArbolAVL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void PreOrder(NodoAVL Nodo) {
        PreOrderString = PreOrderString + PreOrderInt + "[label=\"" + Nodo.getData().getNombre() + "\"];\n";
        PreOrderString = PreOrderString + PreOrderInt + " -> " + (PreOrderInt+1) + ";\n";
        PreOrderInt++;
        if (Nodo.getIzquierda() != null) {
            PreOrder(Nodo.getIzquierda());
        }

        if (Nodo.getDerecha() != null) {
            PreOrder(Nodo.getDerecha());
        }
    }
    
    public void Normalize(NodoAVL Nodo) {
        PreOrderString = PreOrderString + Nodo.getData().getNombre().replaceAll(" ", "_") + "[label=\"" + Nodo.getData().getNombre() + "\"];\n";
        if (Nodo.getIzquierda() != null) {
            PreOrderString = PreOrderString + Nodo.getData().getNombre().replaceAll(" ", "_") + " -> " + Nodo.getIzquierda().getData().getNombre().replaceAll(" ", "_") + ";\n";
        }

        if (Nodo.getDerecha() != null) {
            PreOrderString = PreOrderString + Nodo.getData().getNombre().replaceAll(" ", "_") + " -> " + Nodo.getDerecha().getData().getNombre().replaceAll(" ", "_") + ";\n";
        }
        
        if (Nodo.getIzquierda() != null) {
            Normalize(Nodo.getIzquierda());
        }

        if (Nodo.getDerecha() != null) {
            Normalize(Nodo.getDerecha());
        }
    }
    
    /* ---------------- IMPRESION TEXTO y RECORRIDOS ---------------- */
    
    public void Imprimir() {
        NodoAVL temp = this.Raiz;
        EnOrder(Raiz);

    }

    public void EnOrder(NodoAVL Padre) {
        System.out.println(Padre.getData().getNombre() + ": " + Padre.getSubLevels() + " BFact: " + Padre.getBFact());
        if (Padre.getIzquierda() != null) {
            EnOrder(Padre.getIzquierda());
        }
        if (Padre.getDerecha() != null) {
            EnOrder(Padre.getDerecha());
        }
    }
}
