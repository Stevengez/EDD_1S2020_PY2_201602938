/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.Estructuras;

import biblioteca.Libro;

/**
 *
 * @author Steven
 */
public class ArbolB {

    private NodoB Raiz;

    public ArbolB() {
        this.Raiz = new NodoB();
    }

    public Clave NewBook(Libro Data) {
        Clave temp = Insert(this.Raiz, Data);
        if (temp == null) {
            return null;
        } else {
            //System.out.println("Listo");
            return temp;
        }
    }

    private Clave Insert(NodoB Padre, Libro Data) {
        if (Padre.SubNiveles() == 0) {
            /* Validamos si existe */
            if (existKey(Padre, Data)) {
                System.out.println("Esta clave ya existe.");
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
                    if (existKey(Padre, Data)) {
                        System.out.println("Esta clave ya existe.");
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
                    if (existKey(Padre, Data)) {
                        System.out.println("Esta clave ya existe.");
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
                    if (existKey(Padre, Data)) {
                        System.out.println("Esta clave ya existe.");
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
                    if (existKey(Padre, Data)) {
                        System.out.println("Esta clave ya existe.");
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

    public void UpdateSubLevels(NodoB Padre) {
        Padre.setSubNiveles(Padre.getKey1().getMenores().SubNiveles() + 1);
    }

    /* Metodos de Balanceo */
    public void Balancear(NodoB Nodo) {
        if (Nodo.getKeySize() == 5) {
            System.out.println("Requiere Balanceo (Caso de Division)");
            DivideNode(Nodo);
        } else if (Nodo.getKeySize() < 2 && Nodo != this.Raiz) {
            System.out.println("Requiere Balanceo (Caso Menos Claves del minimo)");
        }

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
        //  System.out.println("Su Padre es: " + Nodo.getPadre().minKey() + "-" + Nodo.getPadre().maxKey());
        //} else {
        //  System.out.println("No tiene Padre.");
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

            Nodo.postDivisionRaiz();
            Nodo.addSubLevel();
            this.Raiz = Nodo;
        }
        System.out.println("Termine el Balanceo.");
    }

    public void Imprimir() {
        System.out.println("******Imprimiendo Arbol**********");
        EnOrder(this.Raiz);
    }

    public void EnOrder(NodoB Padre) {
        if (Padre.SubNiveles() == 0) {
            //if(Padre.getPadre()==null){
            //    System.out.println("Se supone que estoy imprimiendo la raiz.");
            //}else{
            //    System.out.println("Estoy en el Nodo con clave 1: "+Padre.getKey1().getClave()+" y padre con clave1: "+Padre.getPadre().getKey1().getClave());
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

                    System.out.println(Padre.getKey1().getClave() + "*");

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

                    System.out.println(Padre.getKey1().getClave() + "*");

                    if (Padre.getKey2().getMenores() != null) {
                        EnOrder(Padre.getKey2().getMenores());
                    }

                    System.out.println(Padre.getKey2().getClave() + "*");

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

                    System.out.println(Padre.getKey1().getClave() + "*");

                    if (Padre.getKey2().getMenores() != null) {
                        EnOrder(Padre.getKey2().getMenores());
                    }

                    System.out.println(Padre.getKey2().getClave() + "*");

                    if (Padre.getKey3().getMenores() != null) {
                        EnOrder(Padre.getKey3().getMenores());
                    }

                    System.out.println(Padre.getKey3().getClave() + "*");

                    if (Padre.getKey3().getMayores() != null) {
                        EnOrder(Padre.getKey3().getMayores());
                    }
                    break;
                case 4:
                    if (Padre.getKey1().getMenores() != null) {
                        EnOrder(Padre.getKey1().getMenores());
                    }

                    System.out.println(Padre.getKey1().getClave() + "*");

                    if (Padre.getKey2().getMenores() != null) {
                        EnOrder(Padre.getKey2().getMenores());
                    }

                    System.out.println(Padre.getKey2().getClave() + "*");

                    if (Padre.getKey3().getMenores() != null) {
                        EnOrder(Padre.getKey3().getMenores());
                    }

                    System.out.println(Padre.getKey3().getClave() + "*");

                    if (Padre.getKey4().getMenores() != null) {
                        EnOrder(Padre.getKey4().getMenores());
                    }

                    System.out.println(Padre.getKey4().getClave() + "*");

                    if (Padre.getKey4().getMayores() != null) {
                        EnOrder(Padre.getKey4().getMayores());
                    }
                    break;
                default:
                    System.out.println("Sin programar aun este caso.");
                    break;
            }
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

    private boolean existKey(NodoB Padre, Libro Data) {
        switch (Padre.getKeySize()) {
            case 1:
                if (Data.getISBN() == Padre.getKey1().getClave()) {
                    return true;
                }
                break;
            case 2:
                if (Data.getISBN() == Padre.getKey1().getClave()) {
                    return true;
                }
                if (Data.getISBN() == Padre.getKey2().getClave()) {
                    return true;
                }
                break;
            case 3:
                if (Data.getISBN() == Padre.getKey1().getClave()) {
                    return true;
                }
                if (Data.getISBN() == Padre.getKey2().getClave()) {
                    return true;
                }
                if (Data.getISBN() == Padre.getKey3().getClave()) {
                    return true;
                }
                break;
            case 4:
                if (Data.getISBN() == Padre.getKey1().getClave()) {
                    return true;
                }
                if (Data.getISBN() == Padre.getKey2().getClave()) {
                    return true;
                }
                if (Data.getISBN() == Padre.getKey3().getClave()) {
                    return true;
                }
                if (Data.getISBN() == Padre.getKey4().getClave()) {
                    return true;
                }
                break;
            default:
                return false;
        }
        return false;
    }

}
