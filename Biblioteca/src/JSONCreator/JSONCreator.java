/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JSONCreator;

import Network.NetworkManager;
import biblioteca.Categoria;
import biblioteca.Estructuras.ArbolAVL;
import biblioteca.Estructuras.ArbolB;
import biblioteca.Estructuras.BlockChain;
import biblioteca.Estructuras.Clave;
import biblioteca.Estructuras.HashTable;
import biblioteca.Estructuras.ListaSimple;
import biblioteca.Estructuras.NodoAVL;
import biblioteca.Estructuras.NodoB;
import biblioteca.Estructuras.NodoSimple;
import biblioteca.Estructuras.SubNodoHash;
import biblioteca.Libro;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import jdk.jfr.Timestamp;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Steven
 */
public class JSONCreator {

    private static JSONObject prevBlock;
    private static JSONObject CurrentBlock;

    private JSONCreator() {
    }

    public static JSONObject createBlock() {
        CurrentBlock = new JSONObject();
        JSONArray Data = new JSONArray(); //Array de Operaciones - > KeySet
        CurrentBlock.put(Constantes.JSON_DATA_LABEL, Data);
        return CurrentBlock;
    }

    public static JSONObject createApartBlock() {
        JSONObject nuevoBloque = new JSONObject();
        JSONArray Data = new JSONArray(); //Array de Operaciones - > KeySet
        nuevoBloque.put(Constantes.JSON_DATA_LABEL, Data);
        return nuevoBloque;
    }

    public static JSONObject getCurrentBlock() {
        if (CurrentBlock == null) {
            CurrentBlock = new JSONObject();
        }
        return CurrentBlock;
    }

    public static JSONObject getPrevBlock() {
        return prevBlock;
    }

    public static JSONObject prepareBlock(int Index, String prevHash) {
        JSONObject Bloque = getCurrentBlock();
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(System.currentTimeMillis());
        JSONArray Data = (JSONArray) Bloque.get(Constantes.JSON_DATA_LABEL);
        String PreFinal = Index + timeStamp + prevHash + Data.toJSONString();
        int Nonce = NonceCalc(PreFinal);
        String Hash = getSha256of(PreFinal + Nonce);

        Bloque.put(Constantes.JSON_INDEX, Index);
        Bloque.put(Constantes.JSON_TIMESTAMP, timeStamp);
        Bloque.put(Constantes.JSON_NONCE, Nonce);
        Bloque.put(Constantes.JSON_PREVIOUSHASH, prevHash);
        Bloque.put(Constantes.JSON_HASH, Hash);
        System.out.println("Genere el BLoque y el Hash es: " + Hash);
        System.out.println("Bloque Final: " + Bloque.toJSONString());
        return CurrentBlock;
    }

    public static boolean validateBlock(JSONObject Bloque, BlockChain Satoshi) {
        String Index = Bloque.get(Constantes.JSON_INDEX).toString();
        String Timestamp = Bloque.get(Constantes.JSON_TIMESTAMP).toString();
        String Nonce = Bloque.get(Constantes.JSON_NONCE).toString();
        String Data = ((JSONArray) Bloque.get(Constantes.JSON_DATA_LABEL)).toJSONString();
        String PrevHash = Bloque.get(Constantes.JSON_PREVIOUSHASH).toString();

        if (Satoshi.getLastHash().equals(PrevHash)) {
            String nuevoHashByMe = JSONCreator.getSha256of(Index + Timestamp + PrevHash + Data + Nonce);
            String NuevoHash = Bloque.get(Constantes.JSON_HASH).toString();
            if (nuevoHashByMe.equals(NuevoHash)) {
                return true;
            } else {
                System.out.println("El HASH que genere no coincide con el del JSON");
                return false;
            }
        } else {
            System.out.println("El ultimo HASH no coincide.");
            return false;
        }
    }

    public static void completeBlock() {
        prevBlock = CurrentBlock;
        CurrentBlock = createBlock();
        System.out.println("Se genero un nuevo Blocke en limpio.");
    }

    /* Tratamiento de Blockes */
    public static void parseDataBlock(String JSONString, NetworkManager NetManager, ArbolB LibreroGeneral, ArbolAVL Librero, HashTable Usuarios, boolean LocalJSON, JInternalFrame Context) {
        try {
            JSONParser JParser = new JSONParser();

            JSONObject Bloque = (JSONObject) JParser.parse(JSONString);
            JSONArray Data = (JSONArray) Bloque.get(Constantes.JSON_DATA_LABEL);

            for (Object Operacion : Data) {
                JSONObject operacion = (JSONObject) Operacion;
                int adser = 0, adlib = 0, duser = 0, dulib = 0;
                for (Object ID : operacion.keySet()) {
                    switch ((String) ID) {
                        case Constantes.JSON_RED_ADD:
                            JSONArray AddRed = (JSONArray) operacion.get(ID);
                            for (Object nodo : AddRed) {
                                JSONObject servidorRemoto = (JSONObject) nodo;
                                NetManager.getNetworkList().AddNetNode(servidorRemoto.get(Constantes.JSON_RED_NODO_IP).toString(), Integer.parseInt(servidorRemoto.get(Constantes.JSON_RED_NODO_SERVERPORT).toString()));
                            }
                            if (NetManager.getNetworkList().getNodeSize() > 1) {
                                NetManager.setUniqueNodeFlag();
                            }
                            break;
                        case Constantes.JSON_RED_DELETE:
                            JSONArray DelRed = (JSONArray) operacion.get(ID);
                            for (Object nodo : DelRed) {
                                JSONObject servidorRemoto = (JSONObject) nodo;
                                NetManager.getNetworkList().deleteNetNode(servidorRemoto.get(Constantes.JSON_RED_NODO_IP).toString());
                            }
                            if (NetManager.getNetworkList().getNodeSize() == 1) {
                                NetManager.setUniqueNodeFlag();
                            }
                        case Constantes.JSON_USUARIOS_ADD:
                            JSONArray AddUser = (JSONArray) operacion.get(ID);
                            for (Object nodo : AddUser) {
                                adser++;
                                JSONObject usuario = (JSONObject) nodo;
                                Usuarios.newUser(
                                        Integer.parseInt(usuario.get(Constantes.JSON_USER_CARNET).toString()),
                                        usuario.get(Constantes.JSON_USER_NOMBRE).toString(),
                                        usuario.get(Constantes.JSON_USER_APELLIDO).toString(),
                                        usuario.get(Constantes.JSON_USER_CARRERA).toString(),
                                        usuario.get(Constantes.JSON_USER_PASSWORD).toString(),
                                        LocalJSON);
                            }
                            break;
                        case Constantes.JSON_USUARIOS_DELETE:
                            duser++;
                            JSONArray DelUser = (JSONArray) operacion.get(ID);
                            for (Object nodo : DelUser) {
                                JSONObject usuario = (JSONObject) nodo;
                                Usuarios.delUser(Integer.parseInt(usuario.get(Constantes.JSON_USER_CARNET).toString()), LocalJSON);
                            }
                            break;
                        case Constantes.JSON_CATEGORY_DELETE:
                            JSONArray DelCat = (JSONArray) operacion.get(ID);
                            for (Object nodo : DelCat) {
                                JSONObject categoria = (JSONObject) nodo;
                                Librero.EliminarNodo(categoria.get(Constantes.JSON_CATEGORY_NAME).toString(), LocalJSON);
                            }
                            break;
                        case Constantes.JSON_BOOKS_ADD:
                            JSONArray AddBook = (JSONArray) operacion.get(ID);
                            for (Object nodo : AddBook) {
                                JSONObject libro = (JSONObject) nodo;

                                Libro nuevo_libro = new Libro(Integer.parseInt(libro.get(Constantes.JSON_BOOK_ISBN).toString()),
                                        libro.get(Constantes.JSON_BOOK_TITLE).toString(),
                                        libro.get(Constantes.JSON_BOOK_AUTHOR).toString(),
                                        libro.get(Constantes.JSON_BOOK_PRINTER).toString(),
                                        Integer.parseInt(libro.get(Constantes.JSON_BOOK_YEAR).toString()),
                                        Integer.parseInt(libro.get(Constantes.JSON_BOOK_EDITION).toString()),
                                        libro.get(Constantes.JSON_BOOK_CATEGORY).toString(),
                                        libro.get(Constantes.JSON_BOOK_LANG).toString(),
                                        Integer.parseInt(libro.get(Constantes.JSON_BOOK_ID_PUBLISHER).toString()));

                                Clave libro_general = LibreroGeneral.NewBook(nuevo_libro, LocalJSON);

                                if (libro_general != null) {
                                    NodoAVL categoria = Librero.BuscarYCrear(libro.get(Constantes.JSON_BOOK_CATEGORY).toString(), Integer.parseInt(libro.get(Constantes.JSON_BOOK_ID_PUBLISHER).toString()), LocalJSON);
                                    categoria.getData().getLibrero().NewBook(nuevo_libro, LocalJSON);
                                }
                            }
                            break;
                        case Constantes.JSON_BOOKS_DELETE:
                            JSONArray DelBook = (JSONArray) operacion.get(ID);
                            for (Object nodo : DelBook) {
                                JSONObject libro = (JSONObject) nodo;
                                LibreroGeneral.RemoveBook(Integer.parseInt(libro.get(Constantes.JSON_BOOK_ISBN).toString()), LocalJSON);
                                NodoAVL cat = Librero.BuscarCategoria(libro.get(Constantes.JSON_BOOK_CATEGORY).toString());
                                if (cat != null) {
                                    cat.getData().getLibrero().RemoveBook(Integer.parseInt(libro.get(Constantes.JSON_BOOK_ISBN).toString()), LocalJSON);
                                }
                            }
                            break;
                        default:
                            System.out.println("Llave no identificada: " + (String) ID);
                            break;
                    }
                }
                String Mensaje = "";
                if (adser > 0) {
                    Mensaje = Mensaje + "Se agregaron " + adser + " Usuarios.\n";
                }
                if (duser > 0) {
                    Mensaje = Mensaje + "Se eliminaron " + duser + " Usuarios.\n";
                }
                if (adlib > 0) {
                    Mensaje = Mensaje + "Se agregaron " + adser + " Libros.\n";
                }
                if (dulib > 0) {
                    Mensaje = Mensaje + "Se eliminaron " + duser + " Libros.\n";
                }
                if (!LocalJSON) {
                    JOptionPane.showMessageDialog(Context, Mensaje);
                }
            }
        } catch (ParseException ex) {
            Logger.getLogger(JSONCreator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void parseCargaUsuarios(JSONObject Operacion, HashTable Usuarios, JInternalFrame Context) {
        JSONArray AddUser = (JSONArray) Operacion.get(Constantes.JSON_CARGAMASIVA_USERLABEL);
        int addser = 0;
        int misser = 0;
        for (Object nodo : AddUser) {
            JSONObject usuario = (JSONObject) nodo;
            SubNodoHash nuevo = Usuarios.newUser(
                    Integer.parseInt(usuario.get(Constantes.JSON_USER_CARNET).toString()),
                    usuario.get(Constantes.JSON_USER_NOMBRE).toString(),
                    usuario.get(Constantes.JSON_USER_APELLIDO).toString(),
                    usuario.get(Constantes.JSON_USER_CARRERA).toString(),
                    usuario.get(Constantes.JSON_USER_PASSWORD).toString(),
                    false);
            if (nuevo != null) {
                addser++;
            } else {
                misser++;
            }
        }
        if (addser > 0) {
            String Mensaje = "Se agregaron " + addser + " Usuarios.\n";
            if (misser > 0) {
                Mensaje = Mensaje + "Se omitieron " + misser + " Usuarios que ya existian.\n";
            }
            JOptionPane.showMessageDialog(Context, Mensaje);
        }
    }

    public static void parseCargaLibros(JSONObject Operacion, NetworkManager NetManager, ArbolAVL CatLibrary, ArbolB GeneralLibrary, JInternalFrame Context) {
        JSONArray AddBook = (JSONArray) Operacion.get(Constantes.JSON_CARGAMASIVA_BOOKLABEL);
        int addbk = 0;
        int misbk = 0;
        for (Object nodo : AddBook) {
            JSONObject libro = (JSONObject) nodo;
            Libro nuevo_libro = new Libro(Integer.parseInt(libro.get(Constantes.JSON_BOOK_ISBN).toString()),
                    libro.get(Constantes.JSON_BOOK_TITLE).toString(),
                    libro.get(Constantes.JSON_BOOK_AUTHOR).toString(),
                    libro.get(Constantes.JSON_BOOK_PRINTER).toString(),
                    Integer.parseInt(libro.get(Constantes.JSON_BOOK_YEAR).toString()),
                    Integer.parseInt(libro.get(Constantes.JSON_BOOK_EDITION).toString()),
                    libro.get(Constantes.JSON_BOOK_CATEGORY).toString(),
                    libro.get(Constantes.JSON_BOOK_LANG).toString(),
                    NetManager.getLoggedUser().getCarnet());

            Clave libro_general = GeneralLibrary.NewBook(nuevo_libro, false);

            if (libro_general != null) {
                NodoAVL categoria = CatLibrary.BuscarYCrear(libro.get(Constantes.JSON_BOOK_CATEGORY).toString(), NetManager.getLoggedUser().getCarnet(), false);
                categoria.getData().getLibrero().NewBook(nuevo_libro, false);
                addbk++;
            } else {
                misbk++;
            }
        }
        if (addbk > 0) {
            String Mensaje = "Se agregaron " + addbk + " Libros.\n";
            if (misbk > 0) {
                Mensaje = Mensaje + "Se omitieron " + misbk + " Libros que ya existian.\n";
            }
            JOptionPane.showMessageDialog(Context, Mensaje);
        }
    }

    public static JSONObject addRedOperation(JSONObject Bloque, ListaSimple NetWorkNodes) {
        JSONObject Operacion = new JSONObject();
        JSONArray AddRed = new JSONArray();

        NodoSimple aux = NetWorkNodes.getHead();
        while (aux != null) {
            JSONObject Nodo = new JSONObject();
            Nodo.put(Constantes.JSON_RED_NODO_IP, aux.getIP());
            Nodo.put(Constantes.JSON_RED_NODO_SERVERPORT, aux.getServerPort());
            AddRed.add(Nodo);
            aux = aux.getSiguiente();
        }
        Operacion.put(Constantes.JSON_RED_ADD, AddRed);
        ((JSONArray) Bloque.get(Constantes.JSON_DATA_LABEL)).add(Operacion);
        return Bloque;
    }

    public static JSONObject addMeRedOperation(JSONObject Bloque, ListaSimple NetWorkNodes) {
        JSONObject Operacion = new JSONObject();
        JSONArray AddRed = new JSONArray();

        NodoSimple aux = NetWorkNodes.getHead();
        JSONObject Nodo = new JSONObject();
        Nodo.put(Constantes.JSON_RED_NODO_IP, aux.getIP());
        Nodo.put(Constantes.JSON_RED_NODO_SERVERPORT, aux.getServerPort());
        AddRed.add(Nodo);
        aux = aux.getSiguiente();
        Operacion.put(Constantes.JSON_RED_ADD, AddRed);
        ((JSONArray) Bloque.get(Constantes.JSON_DATA_LABEL)).add(Operacion);
        return Bloque;
    }

    public static JSONObject delMeRedOperation(JSONObject Bloque, ListaSimple NetWorkNodes) {
        JSONObject Operacion = new JSONObject();
        JSONArray delRed = new JSONArray();

        NodoSimple aux = NetWorkNodes.getHead();
        JSONObject Nodo = new JSONObject();
        Nodo.put(Constantes.JSON_RED_NODO_IP, aux.getIP());
        Nodo.put(Constantes.JSON_RED_NODO_SERVERPORT, aux.getServerPort());
        delRed.add(Nodo);

        Operacion.put(Constantes.JSON_RED_DELETE, delRed);
        ((JSONArray) Bloque.get(Constantes.JSON_DATA_LABEL)).add(Operacion);
        return Bloque;
    }

    /* Operaciones de Usuario */
    public static JSONObject addUserOperation(JSONObject Bloque, SubNodoHash Usuario) {
        JSONObject Operacion = new JSONObject();
        JSONArray addUser = new JSONArray();
        System.out.println("Agregue un usuario a la operacion: " + Usuario.getNombre());
        JSONObject User = new JSONObject();
        User.put(Constantes.JSON_USER_CARNET, Usuario.getCarnet());
        User.put(Constantes.JSON_USER_NOMBRE, Usuario.getNombre());
        User.put(Constantes.JSON_USER_APELLIDO, Usuario.getApellido());
        User.put(Constantes.JSON_USER_CARRERA, Usuario.getCarrera());
        User.put(Constantes.JSON_USER_PASSWORD, Usuario.getPassword());
        addUser.add(User);

        Operacion.put(Constantes.JSON_USUARIOS_ADD, addUser);
        ((JSONArray) Bloque.get(Constantes.JSON_DATA_LABEL)).add(Operacion);
        return Bloque;
    }

    public static JSONObject delUserOperation(JSONObject Bloque, SubNodoHash Usuario) {
        JSONObject Operacion = new JSONObject();
        JSONArray addUser = new JSONArray();

        JSONObject User = new JSONObject();
        User.put(Constantes.JSON_USER_CARNET, Usuario.getCarnet());
        addUser.add(Usuario);

        Operacion.put(Constantes.JSON_USUARIOS_DELETE, addUser);
        ((JSONArray) Bloque.get(Constantes.JSON_DATA_LABEL)).add(Operacion);
        return Bloque;
    }

    /* Operaciones de Categoria */
    public static JSONObject addCategoryOperation(JSONObject Bloque, Categoria categoria) {
        JSONObject Operacion = new JSONObject();
        JSONArray addCategory = new JSONArray();

        System.out.println("Agregue una adicion categoria a la operacion: " + categoria.getNombre());
        JSONObject Cat = new JSONObject();
        Cat.put(Constantes.JSON_CATEGORY_NAME, categoria.getNombre());
        Cat.put(Constantes.JSON_CATEGORY_AUTHOR_ID, categoria.getPublisher());
        addCategory.add(Cat);

        Operacion.put(Constantes.JSON_CATEGORY_ADD, addCategory);
        ((JSONArray) Bloque.get(Constantes.JSON_DATA_LABEL)).add(Operacion);
        return Bloque;
    }

    public static JSONObject delCategoryOperation(JSONObject Bloque, Categoria categoria) {
        JSONObject Operacion = new JSONObject();
        JSONArray addCategory = new JSONArray();

        System.out.println("Agregue una eliminacion categoria a la operacion: " + categoria.getNombre());
        JSONObject Cat = new JSONObject();
        Cat.put(Constantes.JSON_CATEGORY_NAME, categoria.getNombre());
        addCategory.add(Cat);

        Operacion.put(Constantes.JSON_CATEGORY_DELETE, addCategory);
        ((JSONArray) Bloque.get(Constantes.JSON_DATA_LABEL)).add(Operacion);
        return Bloque;
    }

    /* Operacionens de Libro */
    public static JSONObject addBookOperation(JSONObject Bloque, Libro libro) {
        JSONObject Operacion = new JSONObject();
        JSONArray addBook = new JSONArray();

        System.out.println("Agregue una adicion de libro a la operacion: " + libro.getTitle());
        JSONObject Book = new JSONObject();
        Book.put(Constantes.JSON_BOOK_ISBN, libro.getISBN());
        Book.put(Constantes.JSON_BOOK_YEAR, libro.getYear());
        Book.put(Constantes.JSON_BOOK_LANG, libro.getLanguage());
        Book.put(Constantes.JSON_BOOK_TITLE, libro.getTitle());
        Book.put(Constantes.JSON_BOOK_PRINTER, libro.getPrinter());
        Book.put(Constantes.JSON_BOOK_AUTHOR, libro.getAuthor());
        Book.put(Constantes.JSON_BOOK_EDITION, libro.getEdition());
        Book.put(Constantes.JSON_BOOK_CATEGORY, libro.getCategory());
        Book.put(Constantes.JSON_BOOK_ID_PUBLISHER, libro.getIDAuthor());
        addBook.add(Book);

        Operacion.put(Constantes.JSON_BOOKS_ADD, addBook);
        ((JSONArray) Bloque.get(Constantes.JSON_DATA_LABEL)).add(Operacion);
        return Bloque;
    }

    public static JSONObject delBookOperation(JSONObject Bloque, Libro libro) {
        JSONObject Operacion = new JSONObject();
        JSONArray delBook = new JSONArray();

        System.out.println("Agregue una eliminacion de libro a la operacion: " + libro.getTitle());
        JSONObject Book = new JSONObject();
        Book.put(Constantes.JSON_BOOK_ISBN, libro.getISBN());
        Book.put(Constantes.JSON_BOOK_TITLE, libro.getTitle());
        Book.put(Constantes.JSON_BOOK_CATEGORY, libro.getCategory());
        Book.put(Constantes.JSON_BOOK_ID_PUBLISHER, libro.getIDAuthor());
        delBook.add(Book);

        Operacion.put(Constantes.JSON_BOOKS_DELETE, delBook);
        ((JSONArray) Bloque.get(Constantes.JSON_DATA_LABEL)).add(Operacion);
        return Bloque;
    }

    /* Herramientass  */
    private static byte[] hasEncoded(String source) {
        try {
            MessageDigest diggest = MessageDigest.getInstance("SHA-256");
            byte[] encoded = diggest.digest(source.getBytes(StandardCharsets.UTF_8));
            return encoded;
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(JSONCreator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private static String toHex(byte[] encoded) {
        StringBuilder hexcoded = new StringBuilder();
        for (int i = 0; i < encoded.length; i++) {
            String hex = Integer.toHexString(0xff & encoded[i]);
            if (hex.length() == 1) {
                hexcoded.append('0');
            }
            hexcoded.append(hex);
        }
        return hexcoded.toString();
    }

    public static String getSha256of(String source) {
        byte[] encoded = hasEncoded(source);
        return toHex(encoded);
    }

    public static String getMD5From(String Password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(Password.getBytes());
            byte[] encoded = md.digest();
            return toHex(encoded);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(JSONCreator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static int NonceCalc(String newNodeString) {
        int NONCE = 0;
        System.out.println("Looking for NONCE");
        while (true) {
            //System.out.println("Testing with NONCE: "+NONCE);
            String candidate = getSha256of(newNodeString + String.valueOf(NONCE));
            if (candidate.substring(0, 4).equals("0000")) {
                break;
            }
            NONCE++;
        }
        return NONCE;
    }

}
