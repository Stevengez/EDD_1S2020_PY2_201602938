/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.Estructuras;

import JSONCreator.JSONCreator;
import biblioteca.Categoria;
import biblioteca.Libro;
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
    private Categoria[] AllCategory;
    private int Size, Puntero;

    public ArbolAVL() {
        this.Raiz = null;
        this.Altura = 0;
    }

    public NodoAVL getRaiz() {
        return this.Raiz;
    }

    public NodoAVL InsertNew(Categoria Data, boolean LocalJSON) {

        if (Raiz == null) {
            NodoAVL nuevo = new NodoAVL(Data);
            Raiz = nuevo;
            //System.out.println("Cree la Raiz: " + nuevo.getData().getNombre());
            this.Size++;

            if (!LocalJSON) {
                /* Agregar Operacion al Bloque */
                JSONCreator.addCategoryOperation(JSONCreator.getCurrentBlock(), Data);
            }

            return nuevo;
        } else {
            //System.out.println("Creando desde arriba: "+Data.getNombre());
            NodoAVL nuevo = Insert(Raiz, Data);
            if (nuevo == null) {
                //System.out.println("Ya existe esta categoria.");
                return null;
            } else {
                //System.out.println("Voy a regresar la nueva Categoria recien creada: "+nuevo.getData().getNombre());
                this.Size++;
                if (!LocalJSON) {
                    /* Agregar Operacion al Bloque */
                    JSONCreator.addCategoryOperation(JSONCreator.getCurrentBlock(), Data);
                }
                return nuevo;
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
                Padre.setDerecha(nuevo);
                nuevo.setPadre(Padre);
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
                nuevo.setPadre(Padre);
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
                    ////System.out.println("Sume un subnivel a: "+Nodo.getData().getNombre()+" porque La izquierda era mas grande");
                } else if (Nodo.getDerecha().getSubLevels() + 1 > Nodo.getIzquierda().getSubLevels() + 1) {
                    ////System.out.println("Sume un subnivel a: "+Nodo.getData().getNombre()+" porque La derecha era mas grande");
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
                ////System.out.println("Calculado en factor de:" + Nodo.getData().getNombre());
            } else if (Nodo.getIzquierda() != null) {
                Nodo.setbFact(-1 * (Nodo.getIzquierda().getSubLevels() + 1));
            } else if (Nodo.getDerecha() != null) {
                Nodo.setbFact(Nodo.getDerecha().getSubLevels() + 1);
            } else {
                Nodo.setbFact(0);
            }

            if (Nodo.getBFact() > 1 || Nodo.getBFact() < -1) {
                //System.out.println(Nodo.getData().getNombre() + " requiere Balanceo.");
            }
        }
    }

    /* ---------------- BUSQUEDA DE NODOS ---------------- */
    public NodoAVL BuscarCategoria(String Categoria) {
        if (this.Raiz == null) {
            return null;
        } else {
            NodoAVL temp = Buscar(Raiz, Categoria);
            if (temp == null) {
                //System.out.println("No se encontro esa categoria.");
            }
            return temp;
        }

    }

    /* ---------------- BUSQUEDA Y CREACION NODOS ---------------- */
    public Categoria BuscarYCrear(String Categoria, int Carnet, boolean LocalJSON) {
        if (this.Raiz == null) {
            Categoria nueva = new Categoria(Categoria, Carnet);
            InsertNew(nueva, LocalJSON);
            return nueva;
        } else {
            NodoAVL temp = Buscar(Raiz, Categoria);
            if (temp == null) {
                Categoria nueva = new Categoria(Categoria, Carnet);
                InsertNew(nueva, LocalJSON);
                return nueva;
            }
            return temp.getData();
        }

    }

    private NodoAVL Buscar(NodoAVL Padre, String Categoria) {
        int comparacion = Padre.getData().getNombre().compareToIgnoreCase(Categoria);

        if (comparacion < 0) {
            if (Padre.getDerecha() == null) {
                return null;
            } else {
                return Buscar(Padre.getDerecha(), Categoria);
            }
        } else if (comparacion > 0) {
            if (Padre.getIzquierda() == null) {
                return null;
            } else {
                return Buscar(Padre.getIzquierda(), Categoria);
            }
        } else {
            //System.out.println("La encontre ("+Categoria+"), es: "+Padre.getData().getNombre());
            return Padre;
        }
    }

    /* ---------------- ELIMINACION DE NODOS ---------------- */
    private NodoAVL MenordeMayores(NodoAVL Padre) {
        if (Padre.getIzquierda() != null) {
            return MenordeMayores(Padre.getIzquierda());
        } else {
            return Padre;
        }
    }

    private NodoAVL MayordeMenores(NodoAVL Padre) {
        if (Padre.getDerecha() != null) {
            return MayordeMenores(Padre.getDerecha());
        } else {
            return Padre;
        }
    }

    public Libro[] EliminarNodo(String Categoria, boolean LocalJSON) {
        NodoAVL temp = Eliminar(this.Raiz, new Categoria(Categoria, 0));
        if (temp == null) {
            //System.out.println("No Existia la categoria");
            return null;
        } else {
            this.Size--;

            /* Eliminacion de libros Prior Delete Category */
            Libro[] eliminar_libros = temp.getData().getLibrero().getBooksArray();

            if (eliminar_libros.length > 0) {
                for (int x = 0; x < eliminar_libros.length; x++) {
                    temp.getData().getLibrero().RemoveBook(eliminar_libros[x].getISBN(), LocalJSON);
                }
                if (!LocalJSON) {
                    /* Agregar Operacion al Bloque */
                    JSONCreator.delCategoryOperation(JSONCreator.getCurrentBlock(), temp.getData());
                }

                return eliminar_libros;
            } else {
                if (!LocalJSON) {
                    /* Agregar Operacion al Bloque */
                    JSONCreator.delCategoryOperation(JSONCreator.getCurrentBlock(), temp.getData());
                }
                return null;
            }
        }
    }

    public NodoAVL Eliminar(NodoAVL Padre, Categoria Data) {
        int comparacion = Padre.getData().getNombre().compareToIgnoreCase(Data.getNombre());

        if (comparacion < 0) {
            if (Padre.getDerecha() == null) {
                //System.out.println("No Encontre el nodo a eliminar");
                return null;
            } else {
                NodoAVL temp = Eliminar(Padre.getDerecha(), Data);
                if (temp != null && temp.isDeleted()) {
                    temp.setEliminado(false);
                    Padre.removeSubLevel();
                    Padre.setDerecha(null);
                }
                UpdateSubLevels(Padre);
                setBFact(Padre);
                Balanceo(Padre);
                return temp;
            }
        } else if (comparacion > 0) {
            if (Padre.getIzquierda() == null) {
                //System.out.println("No Encontre el nodo a eliminar");
                return null;
            } else {
                NodoAVL temp = Eliminar(Padre.getIzquierda(), Data);
                if (temp != null && temp.isDeleted()) {
                    temp.setEliminado(false);
                    Padre.removeSubLevel();
                    Padre.setIzquierda(null);
                }
                UpdateSubLevels(Padre);
                setBFact(Padre);
                Balanceo(Padre);
                return temp;
            }
        } else {
            //System.out.println("Encontre el nodo a eliminar");
            NodoAVL temp = Padre;
            if (Padre.getIzquierda() == null && Padre.getDerecha() == null) {
                Padre.setEliminado(true);
                if (Padre == this.Raiz) {
                    this.Raiz = null;
                    //System.out.println("Caso especial, Eliminacion de la Raiz");
                }
                //System.out.println("Eliminacion Simple.");
                return Padre;
            } else {
                /* Caso en que Tiene 2 Hijos */
                if (Padre.getIzquierda() != null && Padre.getDerecha() != null) {
                    if (Padre.getIzquierda().getSubLevels() > Padre.getDerecha().getSubLevels()) {
                        /* Si El hijo izquierdo tiene mas subniveles */
                        NodoAVL Hijo = MenordeMayores(Padre.getDerecha());
                        Categoria PadreData = Padre.getData();
                        Categoria HijoData = Hijo.getData();

                        Padre.setData(HijoData);
                        Hijo.setData(PadreData);

                        if (Hijo.getSubLevels() > 0) {
                            EHD(Hijo.getPadre().getIzquierda());
                        } else {
                            if (Hijo.getPadre() == Padre) {
                                ESD(Hijo);
                            } else {
                                ESI(Hijo);
                            }
                        }

                        UpdateSubLevels(Hijo.getPadre());
                        setBFact(Hijo.getPadre());
                        Balanceo(Hijo.getPadre());
                        //System.out.println("Eliminacion con Intercambio doble (MenordeMayores).");
                        return Hijo;
                    } else {
                        /* Si El hijo derecho tiene mas subniveles o son iguales */
                        NodoAVL Hijo = MayordeMenores(Padre.getIzquierda());
                        Categoria PadreData = Padre.getData();
                        Categoria HijoData = Hijo.getData();

                        Padre.setData(HijoData);
                        Hijo.setData(PadreData);

                        if (Hijo.getSubLevels() > 0) {
                            EHI(Hijo.getPadre().getDerecha());
                        } else {
                            if (Hijo.getPadre() == Padre) {
                                ESI(Hijo);
                            } else {
                                ESD(Hijo);
                            }
                        }

                        UpdateSubLevels(Hijo.getPadre());
                        setBFact(Hijo.getPadre());
                        Balanceo(Hijo.getPadre());
                        //System.out.println("Eliminacion con Intercambio doble (MayordeMenores).");
                        return Hijo;
                    }
                    /* Solo tiene un hijo */
                } else if (Padre.getIzquierda() != null) {
                    //System.out.println("Eliminacion con Intercambio Simple.");
                    return EHI(Padre);
                } else if (Padre.getDerecha() != null) {
                    //System.out.println("Eliminacion con Intercambio Simple.");
                    return EHD(Padre);
                } else {
                    //System.out.println("Erro en la eliminacion.");
                    return null;
                }
            }
        }
    }

    /* Eliminacion Simple de una Hoja */
    public NodoAVL ESD(NodoAVL Hoja) {
        Hoja.getPadre().removeSubLevel();
        Hoja.getPadre().setDerecha(null);
        return null;
    }

    public NodoAVL ESI(NodoAVL Hoja) {
        Hoja.getPadre().removeSubLevel();
        Hoja.getPadre().setIzquierda(null);
        return null;
    }


    /* Eliminar Intercambiando con el Hijo Derecho */
    public NodoAVL EHD(NodoAVL Padre) {
        NodoAVL tempPadre = Padre;
        NodoAVL tempHijo = Padre.getDerecha();

        /* Intercambiar Data */
        Categoria catPadre = tempPadre.getData();
        Categoria catHijo = tempHijo.getData();

        tempPadre.setData(catHijo);
        tempHijo.setData(catPadre);

        /* Reconectar Nodos */
        tempPadre.setIzquierda(tempHijo.getIzquierda());
        tempPadre.setDerecha(tempHijo.getDerecha());

        /* Re Balancear */
        tempPadre.removeSubLevel();
        setBFact(tempPadre);
        Balanceo(tempPadre);
        return tempHijo;
    }

    /* Eliminar Intercambiando con el Hijo Izquierdo */
    public NodoAVL EHI(NodoAVL Padre) {
        NodoAVL tempPadre = Padre;
        NodoAVL tempHijo = Padre.getIzquierda();

        /* Intercambiar Data */
        Categoria catPadre = tempPadre.getData();
        Categoria catHijo = tempHijo.getData();

        tempPadre.setData(catHijo);
        tempHijo.setData(catPadre);

        /* Reconectar Nodos */
        tempPadre.setIzquierda(tempHijo.getIzquierda());
        tempPadre.setDerecha(tempHijo.getDerecha());

        /* Re Balancear */
        tempPadre.removeSubLevel();
        setBFact(tempPadre);
        Balanceo(tempPadre);
        return tempHijo;
    }

    /* ---------------- BALANCEO y ROTACIONES ---------------- */
    public void Balanceo(NodoAVL Nodo) {
        if (Nodo != null) {
            if (Nodo.getBFact() > 1) {
                if (Nodo.getDerecha().getBFact() < 0) {
                    //System.out.println("Doble Rotacion (Derecha, Izquierda).");
                    RDI(Nodo);
                } else {
                    //System.out.println("Rotacion Simple a la Izquierda.");
                    RSI(Nodo);
                }
            } else if (Nodo.getBFact() < -1) {
                if (Nodo.getIzquierda().getBFact() > 0) {
                    //System.out.println("Doble Rotacion (Izquierda, Derecha).");
                    RDD(Nodo);
                } else {
                    //System.out.println("Rotacion Simple a la Derecha.");
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

        if (PrimeroDerecha != null) {
            PrimeroDerecha.setPadre(Segundo);
        }
        if (SegundoDerecha != null) {
            SegundoDerecha.setPadre(Segundo);
        }
        if (Tercero != null) {
            Tercero.setPadre(Primero);
        }
        Segundo.setPadre(Primero);

        /* ReAjustar SubNiveles y Balance Factor */
        UpdateSubLevels(Segundo);
        setBFact(Primero);
        setBFact(Segundo);
        //System.out.println("Termine la rotacion (RSD)");
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

        if (PrimeroIzquierda != null) {
            PrimeroIzquierda.setPadre(Segundo);
        }
        if (SegundoIzquierda != null) {
            SegundoIzquierda.setPadre(Segundo);
        }
        if (Tercero != null) {
            Tercero.setPadre(Primero);
        }
        Segundo.setPadre(Primero);

        /* ReAjustar SubNiveles y Balance Factor */
        UpdateSubLevels(Tercero);
        UpdateSubLevels(Segundo);
        UpdateSubLevels(Primero);
        setBFact(Primero);
        setBFact(Segundo);
        setBFact(Tercero);
        //System.out.println("Termine la rotacion (RSI)");
    }

    public void RDI(NodoAVL Nodo) {
        RSD(Nodo.getDerecha());
        RSI(Nodo);
        //System.out.println("Termine la rotacion (RDI)");
    }

    public void RDD(NodoAVL Nodo) { // Izquierda, Derecha
        RSI(Nodo.getIzquierda());
        RSD(Nodo);
        //System.out.println("Termine la rotacion (RDD)");
    }

    /* ------------------------ Get Size --------------------------- */
    public int getSize() {
        return this.Size;
    }

    /* ---------------- Impresion Grafica del Arbol ---------------- */
    public void ImpresoraGrafica() {
        if (this.Raiz == null) {
            //System.out.println("No hay nodos.");
        } else {
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
    }

    public void PreOrder(NodoAVL Nodo) {
        PreOrderString = PreOrderString + PreOrderInt + "[label=\"" + Nodo.getData().getNombre() + "\"];\n";
        PreOrderString = PreOrderString + PreOrderInt + " -> " + (PreOrderInt + 1) + ";\n";
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

    public Categoria[] getCatsArray() {
        AllCategory = new Categoria[getSize()];
        Puntero = 0;
        if (Raiz == null) {
            return null;
        }
        EnOrderArray(Raiz);
        //System.out.println("Agrupe " + Puntero + " categorias");
        return AllCategory;
    }

    public Categoria[] getCatsArray(int Carnet) {
        AllCategory = new Categoria[getSize()];
        Puntero = 0;
        if (Raiz == null) {
            return null;
        }

        EnOrderArray(Raiz, Carnet);

        Categoria[] retorno = new Categoria[Puntero];
        for (int a = 0; a < Puntero; a++) {
            retorno[a] = AllCategory[a];
        }
        AllCategory = null;
        //System.out.println("Agrupe " + Puntero + " categorias con filtro");
        return retorno;
    }

    public void EnOrder(NodoAVL Padre) {
        //System.out.println(Padre.getData().getNombre() + ": " + Padre.getSubLevels() + " BFact: " + Padre.getBFact());

        if (Padre.getIzquierda() != null) {
            EnOrder(Padre.getIzquierda());
        }
        if (Padre.getDerecha() != null) {
            EnOrder(Padre.getDerecha());
        }
    }

    public void EnOrderArray(NodoAVL Padre) {

        if (Padre.getIzquierda() != null) {
            EnOrderArray(Padre.getIzquierda());
        }

        AllCategory[Puntero] = Padre.getData();
        Puntero++;

        if (Padre.getDerecha() != null) {
            EnOrderArray(Padre.getDerecha());
        }
    }

    /* Con Filtro */
    public void EnOrderArray(NodoAVL Padre, int Carnet) {

        if (Padre.getIzquierda() != null) {
            EnOrderArray(Padre.getIzquierda(), Carnet);
        }

        Libro[] libros = Padre.getData().getLibrero().getBooksArray();
        boolean put_this_cat = false;
        if (libros != null) {
            for (int l = 0; l < libros.length; l++) {
                if (libros[l].getIDAuthor() == Carnet) {
                    put_this_cat = true;
                }
            }
            if (put_this_cat) {
                AllCategory[Puntero] = Padre.getData();
                Puntero++;
            } else if (Padre.getData().getPublisher() == Carnet) {
                AllCategory[Puntero] = Padre.getData();
                Puntero++;
            }
        }

        if (Padre.getDerecha() != null) {
            EnOrderArray(Padre.getDerecha(), Carnet);
        }
    }
}
