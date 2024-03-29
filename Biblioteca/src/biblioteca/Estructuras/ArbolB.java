/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.Estructuras;

import JSONCreator.JSONCreator;
import biblioteca.Libro;

/**
 *
 * @author Steven
 */
public class ArbolB {

    private NodoB Raiz;
    private int Size;
    private Libro[] BooksArray;
    private int Puntero;

    public ArbolB() {
        this.Raiz = new NodoB();
        this.Size = 0;
    }

    public Clave NewBook(Libro Data, boolean LocalJSON) {
        Clave temp = Insert(this.Raiz, Data);
        if (temp == null) {
            return null;
        } else {
            if (!LocalJSON) {
                /* Agregar Operacion al Bloque */
                JSONCreator.addBookOperation(JSONCreator.getCurrentBlock(), temp.getData());
            }
            this.Size++;
            //System.out.println("Listo");
            return temp;
        }
    }
    
    /* Buscar Libros */
    
    public Clave getBook(int ISBN) {
        Clave temp = Buscar(this.Raiz, ISBN);
        if (temp == null) {
            return null;
        } else {
            return temp;
        }
    }
    
    private Clave Buscar(NodoB Padre, int ISBN) {
        if (Padre.SubNiveles() == 0) {
            /* Validamos si existe */
            return existKey(Padre, ISBN);
        } else {
            switch (Padre.getKeySize()) {
                case 1:
                    if (existKey(Padre, ISBN) != null) {
                        return existKey(Padre, ISBN);
                    }

                    if (ISBN > Padre.getKey1().getClave()) {
                        return Buscar(Padre.getKey1().getMayores(), ISBN);
                    } else {
                        return Buscar(Padre.getKey1().getMenores(), ISBN);
                    }
                case 2:
                    if (existKey(Padre, ISBN) != null) {
                        return existKey(Padre, ISBN);
                    }

                    if (ISBN > Padre.getKey2().getClave()) {
                        return Buscar(Padre.getKey2().getMayores(), ISBN);
                    } else if (ISBN > Padre.getKey1().getClave()) {
                        return Buscar(Padre.getKey1().getMayores(), ISBN);
                    } else {
                        return Buscar(Padre.getKey1().getMenores(), ISBN);
                    }
                case 3:
                    if (existKey(Padre, ISBN) != null) {
                        return existKey(Padre, ISBN);
                    }

                    if (ISBN > Padre.getKey3().getClave()) {
                        return Buscar(Padre.getKey3().getMayores(), ISBN);
                    } else if (ISBN > Padre.getKey2().getClave()) {
                        return Buscar(Padre.getKey2().getMayores(), ISBN);
                    } else if (ISBN > Padre.getKey1().getClave()) {
                        return Buscar(Padre.getKey1().getMayores(), ISBN);
                    } else {
                        return Buscar(Padre.getKey1().getMenores(), ISBN);
                    }
                case 4:
                    if (existKey(Padre, ISBN) != null) {
                        return existKey(Padre, ISBN);
                    }

                    if (ISBN > Padre.getKey4().getClave()) {
                        return Buscar(Padre.getKey4().getMayores(), ISBN);
                    } else if (ISBN > Padre.getKey3().getClave()) {
                        return Buscar(Padre.getKey3().getMayores(), ISBN);
                    } else if (ISBN > Padre.getKey2().getClave()) {
                        return Buscar(Padre.getKey2().getMayores(), ISBN);
                    } else if (ISBN > Padre.getKey1().getClave()) {
                        return Buscar(Padre.getKey1().getMayores(), ISBN);
                    } else {
                        return Buscar(Padre.getKey1().getMenores(), ISBN);
                    }
            }
            return null;
        }
    }

    public NodoB getRaiz() {
        return this.Raiz;
    }

    public void RemoveBook(int ISBN, boolean LocalJSON) {
        //System.out.println("#######Eliminar: " + ISBN);
        Clave temp = Eliminar(this.Raiz, ISBN);
        if (temp == null) {
            //System.out.println("No existe");
        } else {
            this.Size--;
            temp.getData().setDeletedStatus(true);
            if (!LocalJSON) {
                /* Agregar Operacion al Bloque */
                JSONCreator.delBookOperation(JSONCreator.getCurrentBlock(), temp.getData());
            }
            //System.out.println("Eliminado");
        }
    }

    /* Reemplazar Mayor de Menores */
    private NodoB RBoM(NodoB Padre, Clave Arriba) {
        return Mayor(Arriba.getMenores(), Arriba);
    }

    private NodoB Mayor(NodoB Padre, Clave Arriba) {
        if (Padre.maxClave().getMayores() != null) {
            NodoB temp = Mayor(Padre.maxClave().getMayores(), Arriba);
            Balancear(Padre);
            return temp;
        } else {
            /* Reemplazar y Conectar */

            Padre.maxClave().setMayores(Arriba.getMayores());
            Padre.maxClave().setMenores(Arriba.getMenores());
            Arriba.convertTo(Padre.maxClave());

            /* Eliminar */
            
            Padre.deleteKey(Padre.maxClave());
            Balancear(Padre);
            return Padre;
        }
    }

    private Clave Insert(NodoB Padre, Libro Data) {
        if (Padre.SubNiveles() == 0) {
            /* Validamos si existe */
            if (existKey(Padre, Data.getISBN()) != null) {
                //System.out.println("Esta clave ya existe.");
                return null;
            }

            /* Insert Transaction */
            Clave nuevo = new Clave(Data);
            Padre.InsertNew(nuevo);

            /* Balanceamos */
            Balancear(Padre);
            return nuevo;
        } else {
            switch (Padre.getKeySize()) {
                case 1:
                    if (existKey(Padre, Data.getISBN()) != null) {
                        //System.out.println("Esta clave ya existe.");
                        return null;
                    }

                    if (Data.getISBN() > Padre.getKey1().getClave()) {
                        Clave temp = Insert(Padre.getKey1().getMayores(), Data);
                        Balancear(Padre);
                        UpdateSubLevels(Padre);
                        return temp;
                    } else {
                        Clave temp = Insert(Padre.getKey1().getMenores(), Data);
                        Balancear(Padre);
                        UpdateSubLevels(Padre);
                        return temp;
                    }
                case 2:
                    if (existKey(Padre, Data.getISBN()) != null) {
                        //System.out.println("Esta clave ya existe.");
                        return null;
                    }

                    if (Data.getISBN() > Padre.getKey2().getClave()) {
                        Clave temp = Insert(Padre.getKey2().getMayores(), Data);
                        Balancear(Padre);
                        UpdateSubLevels(Padre);
                        return temp;
                    } else if (Data.getISBN() > Padre.getKey1().getClave()) {
                        Clave temp = Insert(Padre.getKey1().getMayores(), Data);
                        Balancear(Padre);
                        UpdateSubLevels(Padre);
                        return temp;
                    } else {
                        Clave temp = Insert(Padre.getKey1().getMenores(), Data);
                        Balancear(Padre);
                        UpdateSubLevels(Padre);
                        return temp;
                    }
                case 3:
                    if (existKey(Padre, Data.getISBN()) != null) {
                        //System.out.println("Esta clave ya existe.");
                        return null;
                    }

                    if (Data.getISBN() > Padre.getKey3().getClave()) {
                        Clave temp = Insert(Padre.getKey3().getMayores(), Data);
                        Balancear(Padre);
                        UpdateSubLevels(Padre);
                        return temp;
                    } else if (Data.getISBN() > Padre.getKey2().getClave()) {
                        Clave temp = Insert(Padre.getKey2().getMayores(), Data);
                        Balancear(Padre);
                        UpdateSubLevels(Padre);
                        return temp;
                    } else if (Data.getISBN() > Padre.getKey1().getClave()) {
                        Clave temp = Insert(Padre.getKey1().getMayores(), Data);
                        Balancear(Padre);
                        UpdateSubLevels(Padre);
                        return temp;
                    } else {
                        Clave temp = Insert(Padre.getKey1().getMenores(), Data);
                        Balancear(Padre);
                        UpdateSubLevels(Padre);
                        return temp;
                    }
                case 4:
                    if (existKey(Padre, Data.getISBN()) != null) {
                        //System.out.println("Esta clave ya existe.");
                        return null;
                    }
                    if (Data.getISBN() > Padre.getKey4().getClave()) {
                        Clave temp = Insert(Padre.getKey4().getMayores(), Data);
                        Balancear(Padre);
                        UpdateSubLevels(Padre);
                        return temp;
                    } else if (Data.getISBN() > Padre.getKey3().getClave()) {
                        Clave temp = Insert(Padre.getKey3().getMayores(), Data);
                        Balancear(Padre);
                        UpdateSubLevels(Padre);
                        return temp;
                    } else if (Data.getISBN() > Padre.getKey2().getClave()) {
                        Clave temp = Insert(Padre.getKey2().getMayores(), Data);
                        Balancear(Padre);
                        UpdateSubLevels(Padre);
                        return temp;
                    } else if (Data.getISBN() > Padre.getKey1().getClave()) {
                        Clave temp = Insert(Padre.getKey1().getMayores(), Data);
                        Balancear(Padre);
                        UpdateSubLevels(Padre);
                        return temp;
                    } else {
                        Clave temp = Insert(Padre.getKey1().getMenores(), Data);
                        Balancear(Padre);
                        UpdateSubLevels(Padre);
                        return temp;
                    }
            }
            return null;
        }
    }

    private Clave Eliminar(NodoB Padre, int ISBN) {
        if (Padre.SubNiveles() == 0) {
            /* Validamos si existe */
            Clave temp = existKey(Padre, ISBN);
            if (temp != null) {
                /* Eliminar la Hoja */
                Padre.deleteKey(temp);

                /* Balanceamos */
                Balancear(Padre);
                return temp;
            }
            return null;
        } else {
            switch (Padre.getKeySize()) {
                case 1:
                    if (existKey(Padre, ISBN) != null) {
                        Clave temp = existKey(Padre, ISBN);
                        RBoM(Padre, temp);

                        /* Balanceamos */
                        Balancear(Padre);
                        return temp;
                    }

                    if (ISBN > Padre.getKey1().getClave()) {
                        Clave temp = Eliminar(Padre.getKey1().getMayores(), ISBN);
                        Balancear(Padre);
                        UpdateSubLevels(Padre);
                        return temp;
                    } else {
                        Clave temp = Eliminar(Padre.getKey1().getMenores(), ISBN);
                        Balancear(Padre);
                        UpdateSubLevels(Padre);
                        return temp;
                    }
                case 2:
                    if (existKey(Padre, ISBN) != null) {
                        Clave temp = existKey(Padre, ISBN);
                        RBoM(Padre, temp).getPadre();

                        /* Balanceamos */
                        Balancear(Padre);
                        return temp;
                    }

                    if (ISBN > Padre.getKey2().getClave()) {
                        Clave temp = Eliminar(Padre.getKey2().getMayores(), ISBN);
                        Balancear(Padre);
                        UpdateSubLevels(Padre);
                        return temp;
                    } else if (ISBN > Padre.getKey1().getClave()) {
                        Clave temp = Eliminar(Padre.getKey1().getMayores(), ISBN);
                        Balancear(Padre);
                        UpdateSubLevels(Padre);
                        return temp;
                    } else {
                        Clave temp = Eliminar(Padre.getKey1().getMenores(), ISBN);
                        Balancear(Padre);
                        UpdateSubLevels(Padre);
                        return temp;
                    }
                case 3:
                    if (existKey(Padre, ISBN) != null) {
                        Clave temp = existKey(Padre, ISBN);
                        RBoM(Padre, temp);

                        /* Balanceamos */
                        Balancear(Padre);
                        return temp;
                    }

                    if (ISBN > Padre.getKey3().getClave()) {
                        Clave temp = Eliminar(Padre.getKey3().getMayores(), ISBN);
                        Balancear(Padre);
                        UpdateSubLevels(Padre);
                        return temp;
                    } else if (ISBN > Padre.getKey2().getClave()) {
                        Clave temp = Eliminar(Padre.getKey2().getMayores(), ISBN);
                        Balancear(Padre);
                        UpdateSubLevels(Padre);
                        return temp;
                    } else if (ISBN > Padre.getKey1().getClave()) {
                        Clave temp = Eliminar(Padre.getKey1().getMayores(), ISBN);
                        Balancear(Padre);
                        UpdateSubLevels(Padre);
                        return temp;
                    } else {
                        Clave temp = Eliminar(Padre.getKey1().getMenores(), ISBN);
                        Balancear(Padre);
                        UpdateSubLevels(Padre);
                        return temp;
                    }
                case 4:
                    if (existKey(Padre, ISBN) != null) {
                        Clave temp = existKey(Padre, ISBN);
                        RBoM(Padre, temp);

                        /* Balanceamos */
                        Balancear(Padre);
                        return temp;
                    }

                    if (ISBN > Padre.getKey4().getClave()) {
                        Clave temp = Eliminar(Padre.getKey4().getMayores(), ISBN);
                        Balancear(Padre);
                        UpdateSubLevels(Padre);
                        return temp;
                    } else if (ISBN > Padre.getKey3().getClave()) {
                        Clave temp = Eliminar(Padre.getKey3().getMayores(), ISBN);
                        Balancear(Padre);
                        UpdateSubLevels(Padre);
                        return temp;
                    } else if (ISBN > Padre.getKey2().getClave()) {
                        Clave temp = Eliminar(Padre.getKey2().getMayores(), ISBN);
                        Balancear(Padre);
                        UpdateSubLevels(Padre);
                        return temp;
                    } else if (ISBN > Padre.getKey1().getClave()) {
                        Clave temp = Eliminar(Padre.getKey1().getMayores(), ISBN);
                        Balancear(Padre);
                        UpdateSubLevels(Padre);
                        return temp;
                    } else {
                        Clave temp = Eliminar(Padre.getKey1().getMenores(), ISBN);
                        Balancear(Padre);
                        UpdateSubLevels(Padre);
                        return temp;
                    }
            }
            return null;
        }
    }

    public void UpdateSubLevels(NodoB Padre) {
        if (Padre.getKey1().getMenores() != null) {
            Padre.setSubNiveles(Padre.getKey1().getMenores().SubNiveles() + 1);
        } else {
            Padre.setSubNiveles(0);
        }
    }

    /* Metodos de Balanceo */
    public void Balancear(NodoB Nodo) {
        if (Nodo.getKeySize() == 5) {
            //System.out.println("Requiere Balanceo (Caso de Division)");
            DivideNode(Nodo);
        } else if (Nodo.getKeySize() < 2 && Nodo != this.Raiz) {
            if (Nodo.getYB() != null && Nodo.getYB().getKeySize() > 2 && Nodo.getYB().getPadre() == Nodo.getPadre()) {
                //System.out.println("Requiere Balanceo (Prestamo Izquierdo)");
                PrestamoIzquierdo(Nodo.getYB(), Nodo);
            } else if (Nodo.getBB() != null && Nodo.getBB().getKeySize() > 2 && Nodo.getBB().getPadre() == Nodo.getPadre()) {
                //System.out.println("Requiere Balanceo (Prestamo Derecho)");
                PrestamoDerecho(Nodo, Nodo.getBB());
            } else {
                //System.out.println("Requiere Balanceo (Union de Nodos)");
                MergeNodo(Nodo);
            }
        } else {
            //System.out.println("No requiere balanceo");
        }
    }

    public void PrestamoDerecho(NodoB Deficiente, NodoB Derecho) {
        boolean noHoja = false;
        NodoB menoresNuevaCentral = null;

        NodoB Padre = Deficiente.getPadre();
        Clave ClaveCentral = Deficiente.getPadre().getMiddleKey(Deficiente, Derecho);
        Clave NuevaCentral = Derecho.minClave();
        if (ClaveCentral == null) {
            //System.out.println("Tienen el Mismo padre pero las claves no tienen estos mayores/menores");
        }
        //System.out.println("Clave Superior es: " + ClaveCentral.getClave());
        //System.out.println("Clave Reemplazo es: " + NuevaCentral.getClave());

        if (NuevaCentral.getMenores() != null) {
            noHoja = true;
            menoresNuevaCentral = NuevaCentral.getMenores();
        } else {
            //System.out.println("Estoy en una hoja.");
            //System.out.println("La clave minima del deficientes es: " + Deficiente.minClave().getClave());
        }

        /* Reconectar Central */
        NuevaCentral.setMayores(ClaveCentral.getMayores());
        NuevaCentral.setMenores(ClaveCentral.getMenores());

        /* Reconectar Deficiente */
        if (noHoja) {
            ClaveCentral.setMayores(menoresNuevaCentral);
            menoresNuevaCentral.setPadre(Deficiente);
            ClaveCentral.setMenores(Deficiente.minClave().getMayores());
        } else {
            ClaveCentral.setMenores(null);
            ClaveCentral.setMayores(null);
        }

        /* Reemplazo de Claves */
        Derecho.deleteKey(NuevaCentral);
        Padre.deleteKey(ClaveCentral);

        /* Insertar */
        Padre.InsertNew(NuevaCentral);
        Deficiente.InsertNew(ClaveCentral);
    }

    public void PrestamoIzquierdo(NodoB Izquierdo, NodoB Deficiente) {
        boolean noHoja = false;
        NodoB mayoresNuevaCentral = null;

        NodoB Padre = Deficiente.getPadre();
        Clave ClaveCentral = Deficiente.getPadre().getMiddleKey(Izquierdo, Deficiente);
        Clave NuevaCentral = Izquierdo.maxClave();
        if (ClaveCentral == null) {
            //System.out.println("Tienen el Mismo padre pero las claves no tienen estos mayores/menores");
        }
        //System.out.println("Clave Superior es: " + ClaveCentral.getClave());
        //System.out.println("Clave Reemplazo es: " + NuevaCentral.getClave());

        if (NuevaCentral.getMayores() != null) {
            noHoja = true;
            mayoresNuevaCentral = NuevaCentral.getMayores();
        }

        /* Reconectar Central */
        NuevaCentral.setMayores(ClaveCentral.getMayores());
        NuevaCentral.setMenores(ClaveCentral.getMenores());

        /* Reconectar Deficiente */
        if (noHoja) {
            ClaveCentral.setMenores(mayoresNuevaCentral);
            mayoresNuevaCentral.setPadre(Deficiente);
            ClaveCentral.setMayores(Deficiente.minClave().getMenores());
        } else {
            ClaveCentral.setMenores(null);
            ClaveCentral.setMayores(null);
        }

        /* Reemplazo de Claves */
        Izquierdo.deleteKey(NuevaCentral);
        Padre.deleteKey(ClaveCentral);

        /* Insertar */
        Padre.InsertNew(NuevaCentral);
        Deficiente.InsertNew(ClaveCentral);
    }

    public void DivideNode(NodoB Nodo) {
        /*Crear y Conectar lado derecho */
        NodoB mayores = new NodoB();
        mayores.InsertNew(Nodo.getKey4());
        mayores.InsertNew(Nodo.getKey5());
        Nodo.getKey3().setMayores(mayores);
        mayores.setSubNiveles(Nodo.SubNiveles());

        //System.out.println("Estoy Trabajando en el Nodo " + Nodo.minKey() + "-" + Nodo.maxKey());
        //if (Nodo.getPadre() != null) {
        //  //System.out.println("Su Padre es: " + Nodo.getPadre().minKey() + "-" + Nodo.getPadre().maxKey());
        //} else {
        //  //System.out.println("No tiene Padre.");
        //}
        if (Nodo.getPadre() != null) {
            Nodo.getKey3().setMenores(Nodo);
            NodoB Padre = Nodo.getPadre();

            Padre.InsertNew(Nodo.getKey3());
            mayores.setPadre(Padre);

            if (mayores.SubNiveles() > 0) {
                mayores.getKey1().getMenores().setPadre(mayores);
                mayores.getKey1().getMayores().setPadre(mayores);
                mayores.getKey2().getMayores().setPadre(mayores);
            }

            /* Asignar Hermanos */
            if (Nodo.getBB() != null) {
                Nodo.getBB().setYB(mayores);
            }
            mayores.setBB(Nodo.getBB());
            Nodo.setBB(mayores);
            mayores.setYB(Nodo);

            Nodo.postDivision();
        } else { //Raiz
            /*Crear y Conectar lado izquierdo */
            NodoB menores = new NodoB();
            menores.InsertNew(Nodo.getKey1());
            menores.InsertNew(Nodo.getKey2());

            Nodo.getKey3().setMenores(menores);

            /* Asignar Padre a los Nuevos Nodos */
            menores.setPadre(Nodo);
            mayores.setPadre(Nodo);

            /* Asignar SubNiveles a Nodos Nuevos */
            menores.setSubNiveles(Nodo.SubNiveles());
            mayores.setSubNiveles(Nodo.SubNiveles());

            /* Asignar Padre a los SubNodos */
            if (menores.SubNiveles() > 0) {
                menores.getKey1().getMenores().setPadre(menores);
                menores.getKey1().getMayores().setPadre(menores);
                menores.getKey2().getMayores().setPadre(menores);
            }

            if (mayores.SubNiveles() > 0) {
                mayores.getKey1().getMenores().setPadre(mayores);
                mayores.getKey1().getMayores().setPadre(mayores);
                mayores.getKey2().getMayores().setPadre(mayores);
            }

            /* Asignar Hermanos */
            menores.setBB(mayores);
            mayores.setYB(menores);

            /* Cambio de Nivel */
            Nodo.postDivisionRaiz();
            Nodo.addSubLevel();
            this.Raiz = Nodo;
        }
        //System.out.println("Termine el Balanceo.");
    }

    public void MergeNodo(NodoB Nodo) {
        NodoB Padre, Izquierda, Derecha;
        Clave ClaveCentral;

        if (Nodo.getYB() != null) {
            Padre = Nodo.getPadre();
            ClaveCentral = Padre.getMiddleKeyAndChangeRight(Nodo.getYB(), Nodo);
            Izquierda = Nodo.getYB();
            Derecha = Nodo;
            if (ClaveCentral == null) {
                Padre = Nodo.getPadre();
                ClaveCentral = Padre.getMiddleKey(Nodo, Nodo.getBB());
                Izquierda = Nodo;
                Derecha = Nodo.getBB();
            }
        } else {
            Padre = Nodo.getPadre();
            ClaveCentral = Padre.getMiddleKey(Nodo, Nodo.getBB());
            Izquierda = Nodo;
            Derecha = Nodo.getBB();
        }

        /* Bajar la Clave Central */
        if (Derecha.SubNiveles() > 0) {
            if (Derecha.getKey2() != null) {
                if (Padre == this.Raiz && Padre.getKeySize() == 1) {
                    Derecha.getKey1().getMenores().setPadre(Padre);
                    Derecha.getKey1().getMayores().setPadre(Padre);
                    Derecha.getKey2().getMayores().setPadre(Padre);
                } else {
                    Derecha.getKey1().getMenores().setPadre(Izquierda);
                    Derecha.getKey1().getMayores().setPadre(Izquierda);
                    Derecha.getKey2().getMayores().setPadre(Izquierda);
                }
            } else {
                if (Padre == this.Raiz && Padre.getKeySize() == 1) {
                    Derecha.getKey1().getMenores().setPadre(Padre);
                    Derecha.getKey1().getMayores().setPadre(Padre);
                } else {
                    Derecha.getKey1().getMenores().setPadre(Izquierda);
                    Derecha.getKey1().getMayores().setPadre(Izquierda);
                }
                ClaveCentral.setMayores(Derecha.getKey1().getMenores());
            }

            if (Izquierda.getKey2() != null) {
                if (Padre == this.Raiz && Padre.getKeySize() == 1) {
                    Izquierda.getKey1().getMenores().setPadre(Padre);
                    Izquierda.getKey1().getMayores().setPadre(Padre);
                    Izquierda.getKey2().getMayores().setPadre(Padre);
                }
                ClaveCentral.setMenores(Izquierda.getKey2().getMayores());
            } else {
                if (Padre == this.Raiz && Padre.getKeySize() == 1) {
                    Izquierda.getKey1().getMenores().setPadre(Padre);
                    Izquierda.getKey1().getMayores().setPadre(Padre);
                }
                ClaveCentral.setMenores(Izquierda.getKey1().getMayores());
            }
        } else {
            ClaveCentral.setMenores(null); //Borrar Menores porque ahora es hoja
            ClaveCentral.setMayores(null); //Borrar Menores porque ahora es hoja
        }

        if (Padre != this.Raiz || Padre.getKeySize() > 1) {
            if (Padre.getKey1() == ClaveCentral) {
                Padre.getKey2().setMenores(Izquierda);
            }

            Padre.deleteKey(ClaveCentral); //Borrar del nodo padre

            Izquierda.InsertNew(ClaveCentral);

            /* Insertar las 2 o 1 claves de la derecha */
            Izquierda.InsertNew(Derecha.getKey1());
            if (Derecha.SubNiveles() > 0) {
                ClaveCentral.setMayores(Derecha.getKey1().getMenores());
            }
            if (Derecha.getKey2() != null) {
                Izquierda.InsertNew(Derecha.getKey2());
            }

            /* Reconectar Hermanos */
            if (Derecha.getBB() != null) {
                Izquierda.setBB(Derecha.getBB());
                Derecha.getBB().setYB(Izquierda);
            } else {
                Izquierda.setBB(null);
            }
        } else {
            Padre.InsertNew(ClaveCentral);

            /*Insertar las 2 o 1 claves de la Izquierda */
            Padre.InsertNew(Izquierda.getKey1());
            if (Izquierda.getKey2() != null) {
                Padre.InsertNew(Izquierda.getKey2());
            }

            /* Insertar las 2 o 1 claves de la derecha */
            Padre.InsertNew(Derecha.getKey1());
            if (Derecha.SubNiveles() > 0) {
                ClaveCentral.setMayores(Derecha.getKey1().getMenores());
            }
            if (Derecha.getKey2() != null) {
                Padre.InsertNew(Derecha.getKey2());
            }

            /* Reconectar Hermanos */
            if (Derecha.getBB() != null) {
                Padre.setBB(Derecha.getBB());
                Derecha.getBB().setYB(Padre);
            } else {
                Padre.setBB(null);
            }
        }
        //System.out.println("Termine el Balanceo.");
    }

    public void Imprimir() {
        //System.out.println("******Imprimiendo Arbol**********");
        EnOrder(this.Raiz);
    }

    public void ImprimirNiveles() {
        if (this.Raiz == null) {
            //System.out.println("El Arbol esta vacio.");
        } else {
            Niveles(this.Raiz, 0);
        }
    }

    public Libro[] getBooksArray() {
        BooksArray = new Libro[getSize()];
        Puntero = 0;
        ArrayEnOrder(this.Raiz);

        Libro[] retorno = BooksArray;
        BooksArray = null;
        //System.out.println("Voy a devolver un array con: " + Puntero + " libros");
        return retorno;
    }
    
    public Libro[] getBooksArray(String Filtro) {
        BooksArray = new Libro[getSize()];
        Puntero = 0;
        ArrayEnOrder(this.Raiz, Filtro);

        Libro[] retorno = new Libro[Puntero];
        for(int a = 0; a<Puntero; a++){
            retorno[a] = BooksArray[a];
        }
        
        BooksArray = null;
        //System.out.println("Voy a devolver un array con: " + Puntero + " libros");
        return retorno;
    }

    public void ArrayEnOrder(NodoB Padre) {
        if (Padre.SubNiveles() == 0) {
            AddtoArray(Padre);
        } else {
            switch (Padre.getKeySize()) {
                case 1:
                    if (Padre.getKey1().getMenores() != null) {
                        ArrayEnOrder(Padre.getKey1().getMenores());
                    }

                    BooksArray[Puntero] = Padre.getKey1().getData();
                    Puntero++;

                    if (Padre.getKey1().getMayores() != null) {
                        ArrayEnOrder(Padre.getKey1().getMayores());
                    }
                    break;
                case 2:
                    if (Padre.getKey1().getMenores() != null) {
                        ArrayEnOrder(Padre.getKey1().getMenores());
                    }

                    BooksArray[Puntero] = Padre.getKey1().getData();
                    Puntero++;

                    if (Padre.getKey2().getMenores() != null) {
                        ArrayEnOrder(Padre.getKey2().getMenores());
                    }

                    BooksArray[Puntero] = Padre.getKey2().getData();
                    Puntero++;

                    if (Padre.getKey2().getMayores() != null) {
                        ArrayEnOrder(Padre.getKey2().getMayores());
                    }
                    break;
                case 3:
                    if (Padre.getKey1().getMenores() != null) {
                        ArrayEnOrder(Padre.getKey1().getMenores());
                    }

                    BooksArray[Puntero] = Padre.getKey1().getData();
                    Puntero++;

                    if (Padre.getKey2().getMenores() != null) {
                        ArrayEnOrder(Padre.getKey2().getMenores());
                    }

                    BooksArray[Puntero] = Padre.getKey2().getData();
                    Puntero++;

                    if (Padre.getKey3().getMenores() != null) {
                        ArrayEnOrder(Padre.getKey3().getMenores());
                    }

                    BooksArray[Puntero] = Padre.getKey3().getData();
                    Puntero++;

                    if (Padre.getKey3().getMayores() != null) {
                        ArrayEnOrder(Padre.getKey3().getMayores());
                    }
                    break;
                case 4:
                    if (Padre.getKey1().getMenores() != null) {
                        ArrayEnOrder(Padre.getKey1().getMenores());
                    }

                    BooksArray[Puntero] = Padre.getKey1().getData();
                    Puntero++;

                    if (Padre.getKey2().getMenores() != null) {
                        ArrayEnOrder(Padre.getKey2().getMenores());
                    }

                    BooksArray[Puntero] = Padre.getKey2().getData();
                    Puntero++;

                    if (Padre.getKey3().getMenores() != null) {
                        ArrayEnOrder(Padre.getKey3().getMenores());
                    }

                    BooksArray[Puntero] = Padre.getKey3().getData();
                    Puntero++;

                    if (Padre.getKey4().getMenores() != null) {
                        ArrayEnOrder(Padre.getKey4().getMenores());
                    }

                    BooksArray[Puntero] = Padre.getKey4().getData();
                    Puntero++;

                    if (Padre.getKey4().getMayores() != null) {
                        ArrayEnOrder(Padre.getKey4().getMayores());
                    }
                    break;
                default:
                    //System.out.println("Sin programar aun este caso.");
                    break;
            }
        }

    }
    
    public void ArrayEnOrder(NodoB Padre, String Filtro) {
        if (Padre.SubNiveles() == 0) {
            AddtoArray(Padre, Filtro);
        } else {
            switch (Padre.getKeySize()) {
                case 1:
                    if (Padre.getKey1().getMenores() != null) {
                        ArrayEnOrder(Padre.getKey1().getMenores(), Filtro);
                    }

                    if((Filtro.matches("[0-9]+") && Padre.getKey1().getData().getISBN() == Integer.parseInt(Filtro)) || Padre.getKey1().getData().getTitle().toUpperCase().contains(Filtro.toUpperCase())){
                        BooksArray[Puntero] = Padre.getKey1().getData();
                        Puntero++;
                    }

                    if (Padre.getKey1().getMayores() != null) {
                        ArrayEnOrder(Padre.getKey1().getMayores(), Filtro);
                    }
                    break;
                case 2:
                    if (Padre.getKey1().getMenores() != null) {
                        ArrayEnOrder(Padre.getKey1().getMenores(), Filtro);
                    }

                    if((Filtro.matches("[0-9]+") && Padre.getKey1().getData().getISBN() == Integer.parseInt(Filtro)) || Padre.getKey1().getData().getTitle().toUpperCase().contains(Filtro.toUpperCase())){
                        BooksArray[Puntero] = Padre.getKey1().getData();
                        Puntero++;
                    }

                    if (Padre.getKey2().getMenores() != null) {
                        ArrayEnOrder(Padre.getKey2().getMenores(), Filtro);
                    }

                    if((Filtro.matches("[0-9]+") && Padre.getKey2().getData().getISBN() == Integer.parseInt(Filtro)) || Padre.getKey2().getData().getTitle().toUpperCase().contains(Filtro.toUpperCase())){
                        BooksArray[Puntero] = Padre.getKey2().getData();
                        Puntero++;
                    }

                    if (Padre.getKey2().getMayores() != null) {
                        ArrayEnOrder(Padre.getKey2().getMayores(), Filtro);
                    }
                    break;
                case 3:
                    if (Padre.getKey1().getMenores() != null) {
                        ArrayEnOrder(Padre.getKey1().getMenores(), Filtro);
                    }

                    if((Filtro.matches("[0-9]+") && Padre.getKey1().getData().getISBN() == Integer.parseInt(Filtro)) || Padre.getKey1().getData().getTitle().toUpperCase().contains(Filtro.toUpperCase())){
                        BooksArray[Puntero] = Padre.getKey1().getData();
                        Puntero++;
                    }

                    if (Padre.getKey2().getMenores() != null) {
                        ArrayEnOrder(Padre.getKey2().getMenores(), Filtro);
                    }

                    if((Filtro.matches("[0-9]+") && Padre.getKey2().getData().getISBN() == Integer.parseInt(Filtro)) || Padre.getKey2().getData().getTitle().toUpperCase().contains(Filtro.toUpperCase())){
                        BooksArray[Puntero] = Padre.getKey2().getData();
                        Puntero++;
                    }

                    if (Padre.getKey3().getMenores() != null) {
                        ArrayEnOrder(Padre.getKey3().getMenores(), Filtro);
                    }

                    if((Filtro.matches("[0-9]+") && Padre.getKey3().getData().getISBN() == Integer.parseInt(Filtro)) || Padre.getKey3().getData().getTitle().toUpperCase().contains(Filtro.toUpperCase())){
                        BooksArray[Puntero] = Padre.getKey3().getData();
                        Puntero++;
                    }

                    if (Padre.getKey3().getMayores() != null) {
                        ArrayEnOrder(Padre.getKey3().getMayores(), Filtro);
                    }
                    break;
                case 4:
                    if (Padre.getKey1().getMenores() != null) {
                        ArrayEnOrder(Padre.getKey1().getMenores(), Filtro);
                    }

                    if((Filtro.matches("[0-9]+") && Padre.getKey1().getData().getISBN() == Integer.parseInt(Filtro)) || Padre.getKey1().getData().getTitle().toUpperCase().contains(Filtro.toUpperCase())){
                        BooksArray[Puntero] = Padre.getKey1().getData();
                        Puntero++;
                    }

                    if (Padre.getKey2().getMenores() != null) {
                        ArrayEnOrder(Padre.getKey2().getMenores(), Filtro);
                    }

                    if((Filtro.matches("[0-9]+") && Padre.getKey2().getData().getISBN() == Integer.parseInt(Filtro)) || Padre.getKey2().getData().getTitle().toUpperCase().contains(Filtro.toUpperCase())){
                        BooksArray[Puntero] = Padre.getKey2().getData();
                        Puntero++;
                    }

                    if (Padre.getKey3().getMenores() != null) {
                        ArrayEnOrder(Padre.getKey3().getMenores(), Filtro);
                    }

                    if((Filtro.matches("[0-9]+") && Padre.getKey3().getData().getISBN() == Integer.parseInt(Filtro)) || Padre.getKey3().getData().getTitle().toUpperCase().contains(Filtro.toUpperCase())){
                        BooksArray[Puntero] = Padre.getKey3().getData();
                        Puntero++;
                    }

                    if (Padre.getKey4().getMenores() != null) {
                        ArrayEnOrder(Padre.getKey4().getMenores(), Filtro);
                    }

                    if((Filtro.matches("[0-9]+") && Padre.getKey4().getData().getISBN() == Integer.parseInt(Filtro)) || Padre.getKey4().getData().getTitle().toUpperCase().contains(Filtro.toUpperCase())){
                        BooksArray[Puntero] = Padre.getKey4().getData();
                        Puntero++;
                    }

                    if (Padre.getKey4().getMayores() != null) {
                        ArrayEnOrder(Padre.getKey4().getMayores(), Filtro);
                    }
                    break;
                default:
                    //System.out.println("Sin programar aun este caso.");
                    break;
            }
        }

    }

    public void EnOrder(NodoB Padre) {
        if (Padre.SubNiveles() == 0) {
            //if(Padre.getPadre()==null){
            //    //System.out.println("Se supone que estoy imprimiendo la raiz.");
            //}else{
            //    //System.out.println("Estoy en el Nodo con clave 1: "+Padre.getKey1().getClave()+" y padre con clave1: "+Padre.getPadre().getKey1().getClave());
            //}
            ImprimirClaves(Padre);
        } else {
            switch (Padre.getKeySize()) {
                case 1:
                    //System.out.println("-------Caso 1---------");
                    //ImprimirClaves(Padre);
                    //System.out.println("---- FIN Caso 1 ------");
                    if (Padre.getKey1().getMenores() != null) {
                        EnOrder(Padre.getKey1().getMenores());
                    }

                    //System.out.println(Padre.getKey1().getClave() + "*");

                    if (Padre.getKey1().getMayores() != null) {
                        EnOrder(Padre.getKey1().getMayores());
                    }
                    break;
                case 2:
                    //System.out.println("-------Caso 2---------");
                    //ImprimirClaves(Padre);
                    //System.out.println("---- FIN Caso 2 ------");
                    //System.out.println("-----Caso 2--------");
                    if (Padre.getKey1().getMenores() != null) {
                        EnOrder(Padre.getKey1().getMenores());
                    }

                    //System.out.println(Padre.getKey1().getClave() + "*");

                    if (Padre.getKey2().getMenores() != null) {
                        EnOrder(Padre.getKey2().getMenores());
                    }

                    //System.out.println(Padre.getKey2().getClave() + "*");

                    if (Padre.getKey2().getMayores() != null) {
                        EnOrder(Padre.getKey2().getMayores());
                    }
                    break;
                case 3:
                    //System.out.println("-------Caso 3---------");
                    //ImprimirClaves(Padre);
                    //System.out.println("---- FIN Caso 3 ------");
                    if (Padre.getKey1().getMenores() != null) {
                        EnOrder(Padre.getKey1().getMenores());
                    }

                    //System.out.println(Padre.getKey1().getClave() + "*");

                    if (Padre.getKey2().getMenores() != null) {
                        EnOrder(Padre.getKey2().getMenores());
                    }

                    //System.out.println(Padre.getKey2().getClave() + "*");

                    if (Padre.getKey3().getMenores() != null) {
                        EnOrder(Padre.getKey3().getMenores());
                    }

                    //System.out.println(Padre.getKey3().getClave() + "*");

                    if (Padre.getKey3().getMayores() != null) {
                        EnOrder(Padre.getKey3().getMayores());
                    }
                    break;
                case 4:
                    if (Padre.getKey1().getMenores() != null) {
                        EnOrder(Padre.getKey1().getMenores());
                    }

                    //System.out.println(Padre.getKey1().getClave() + "*");

                    if (Padre.getKey2().getMenores() != null) {
                        EnOrder(Padre.getKey2().getMenores());
                    }

                    //System.out.println(Padre.getKey2().getClave() + "*");

                    if (Padre.getKey3().getMenores() != null) {
                        EnOrder(Padre.getKey3().getMenores());
                    }

                    //System.out.println(Padre.getKey3().getClave() + "*");

                    if (Padre.getKey4().getMenores() != null) {
                        EnOrder(Padre.getKey4().getMenores());
                    }

                    //System.out.println(Padre.getKey4().getClave() + "*");

                    if (Padre.getKey4().getMayores() != null) {
                        EnOrder(Padre.getKey4().getMayores());
                    }
                    break;
                default:
                    //System.out.println("Sin programar aun este caso.");
                    break;
            }
        }
    }

    public void Niveles(NodoB Izquierda, int Nivel) {
        /*Hermanos Izquierdos*/
        NodoB temp = Izquierda;
        //System.out.println("------- Nivel : " + Nivel + " ---------");
        String nivel = "";
        while (temp != null) {
            nivel = nivel + StringClaves(temp) + " > ";
            temp = temp.getBB();
        }
        System.out.println(nivel);
        //System.out.println("----------------- -----------");
        if (Izquierda.getKey1() != null && Izquierda.getKey1().getMenores() != null) {
            Niveles(Izquierda.getKey1().getMenores(), Nivel + 1);
        } else {
            //System.out.println("Estoy en el nivel " + Nivel + " y ya no hay mas niveles.");
        }

    }

    private void ImprimirClaves(NodoB Nodo) {
        switch (Nodo.getKeySize()) {
            case 1:
                System.out.println(Nodo.getKey1().getClave());
                break;
            case 2:
                System.out.println(Nodo.getKey1().getClave());
                System.out.println(Nodo.getKey2().getClave());
                break;
            case 3:
                System.out.println(Nodo.getKey1().getClave());
                System.out.println(Nodo.getKey2().getClave());
                System.out.println(Nodo.getKey3().getClave());
                break;
            case 4:
                System.out.println(Nodo.getKey1().getClave());
                System.out.println(Nodo.getKey2().getClave());
                System.out.println(Nodo.getKey3().getClave());
                System.out.println(Nodo.getKey4().getClave());
                break;
        }
    }

    private void AddtoArray(NodoB Nodo) {
        switch (Nodo.getKeySize()) {
            case 1:
                BooksArray[Puntero] = Nodo.getKey1().getData();
                Puntero++;
                break;
            case 2:
                BooksArray[Puntero] = Nodo.getKey1().getData();
                Puntero++;
                BooksArray[Puntero] = Nodo.getKey2().getData();
                Puntero++;
                break;
            case 3:
                BooksArray[Puntero] = Nodo.getKey1().getData();
                Puntero++;
                BooksArray[Puntero] = Nodo.getKey2().getData();
                Puntero++;
                BooksArray[Puntero] = Nodo.getKey3().getData();
                Puntero++;
                break;
            case 4:
                BooksArray[Puntero] = Nodo.getKey1().getData();
                Puntero++;
                BooksArray[Puntero] = Nodo.getKey2().getData();
                Puntero++;
                BooksArray[Puntero] = Nodo.getKey3().getData();
                Puntero++;
                BooksArray[Puntero] = Nodo.getKey4().getData();
                Puntero++;
                break;
        }
    }
    
    private void AddtoArray(NodoB Nodo, String Filtro) {
        switch (Nodo.getKeySize()) {
            case 1:
                if((Filtro.matches("[0-9]+") && Nodo.getKey1().getData().getISBN() == Integer.parseInt(Filtro)) || Nodo.getKey1().getData().getTitle().toUpperCase().contains(Filtro.toUpperCase())){
                    BooksArray[Puntero] = Nodo.getKey1().getData();
                    Puntero++;
                }                
                break;
            case 2:
                if((Filtro.matches("[0-9]+") && Nodo.getKey1().getData().getISBN() == Integer.parseInt(Filtro)) || Nodo.getKey1().getData().getTitle().toUpperCase().contains(Filtro.toUpperCase())){
                    BooksArray[Puntero] = Nodo.getKey1().getData();
                    Puntero++;
                }                
                if((Filtro.matches("[0-9]+") && Nodo.getKey2().getData().getISBN() == Integer.parseInt(Filtro)) || Nodo.getKey2().getData().getTitle().toUpperCase().contains(Filtro.toUpperCase())){
                    BooksArray[Puntero] = Nodo.getKey2().getData();
                    Puntero++;
                }
                break;
            case 3:
                if((Filtro.matches("[0-9]+") && Nodo.getKey1().getData().getISBN() == Integer.parseInt(Filtro)) || Nodo.getKey1().getData().getTitle().toUpperCase().contains(Filtro.toUpperCase())){
                    BooksArray[Puntero] = Nodo.getKey1().getData();
                    Puntero++;
                }                
                if((Filtro.matches("[0-9]+") && Nodo.getKey2().getData().getISBN() == Integer.parseInt(Filtro)) || Nodo.getKey2().getData().getTitle().toUpperCase().contains(Filtro.toUpperCase())){
                    BooksArray[Puntero] = Nodo.getKey2().getData();
                    Puntero++;
                }
                if((Filtro.matches("[0-9]+") && Nodo.getKey3().getData().getISBN() == Integer.parseInt(Filtro)) || Nodo.getKey3().getData().getTitle().toUpperCase().contains(Filtro.toUpperCase())){
                    BooksArray[Puntero] = Nodo.getKey3().getData();
                    Puntero++;
                }
                break;
            case 4:
                if((Filtro.matches("[0-9]+") && Nodo.getKey1().getData().getISBN() == Integer.parseInt(Filtro)) || Nodo.getKey1().getData().getTitle().toUpperCase().contains(Filtro.toUpperCase())){
                    BooksArray[Puntero] = Nodo.getKey1().getData();
                    Puntero++;
                }                
                if((Filtro.matches("[0-9]+") && Nodo.getKey2().getData().getISBN() == Integer.parseInt(Filtro)) || Nodo.getKey2().getData().getTitle().toUpperCase().contains(Filtro.toUpperCase())){
                    BooksArray[Puntero] = Nodo.getKey2().getData();
                    Puntero++;
                }
                if((Filtro.matches("[0-9]+") && Nodo.getKey3().getData().getISBN() == Integer.parseInt(Filtro)) || Nodo.getKey3().getData().getTitle().toUpperCase().contains(Filtro.toUpperCase())){
                    BooksArray[Puntero] = Nodo.getKey3().getData();
                    Puntero++;
                }
                if((Filtro.matches("[0-9]+") && Nodo.getKey4().getData().getISBN() == Integer.parseInt(Filtro)) || Nodo.getKey4().getData().getTitle().toUpperCase().contains(Filtro.toUpperCase())){
                    BooksArray[Puntero] = Nodo.getKey4().getData();
                    Puntero++;
                }
                break;
        }
    }

    private String StringClaves(NodoB Nodo) {
        switch (Nodo.getKeySize()) {
            case 1:
                return Nodo.getKey1().getClave() + "";
            case 2:
                return Nodo.getKey1().getClave() + "," + Nodo.getKey2().getClave();
            case 3:
                return Nodo.getKey1().getClave() + "," + Nodo.getKey2().getClave() + "," + Nodo.getKey3().getClave();
            case 4:
                return Nodo.getKey1().getClave() + "," + Nodo.getKey2().getClave() + "," + Nodo.getKey3().getClave() + "," + Nodo.getKey4().getClave();
            default:
                return "";
        }
    }

    private Clave existKey(NodoB Padre, int ISBN) {
        switch (Padre.getKeySize()) {
            case 1:
                if (ISBN == Padre.getKey1().getClave()) {
                    return Padre.getKey1();
                }
                break;
            case 2:
                if (ISBN == Padre.getKey1().getClave()) {
                    return Padre.getKey1();
                }
                if (ISBN == Padre.getKey2().getClave()) {
                    return Padre.getKey2();
                }
                break;
            case 3:
                if (ISBN == Padre.getKey1().getClave()) {
                    return Padre.getKey1();
                }
                if (ISBN == Padre.getKey2().getClave()) {
                    return Padre.getKey2();
                }
                if (ISBN == Padre.getKey3().getClave()) {
                    return Padre.getKey3();
                }
                break;
            case 4:
                if (ISBN == Padre.getKey1().getClave()) {
                    return Padre.getKey1();
                }
                if (ISBN == Padre.getKey2().getClave()) {
                    return Padre.getKey2();
                }
                if (ISBN == Padre.getKey3().getClave()) {
                    return Padre.getKey3();
                }
                if (ISBN == Padre.getKey4().getClave()) {
                    return Padre.getKey4();
                }
                break;
            default:
                return null;
        }
        return null;
    }

    public int getSize() {
        return this.Size;
    }

}
