/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JSONCreator;

import Network.NetworkManager;
import biblioteca.Estructuras.ArbolAVL;
import biblioteca.Estructuras.BlockChain;
import biblioteca.Estructuras.HashTable;
import biblioteca.Estructuras.ListaSimple;
import biblioteca.Estructuras.NodoSimple;
import biblioteca.Estructuras.SubNodoHash;
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
            String nuevoHashByMe = JSONCreator.getSha256of(Index + Timestamp + PrevHash+ Data + Nonce);
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
    public static void parseDataBlock(String JSONString, NetworkManager NetManager, ArbolAVL Librero, HashTable Usuarios, boolean LocalJSON, JInternalFrame Context) {
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
                JOptionPane.showMessageDialog(Context, Mensaje);
            }
        } catch (ParseException ex) {
            Logger.getLogger(JSONCreator.class.getName()).log(Level.SEVERE, null, ex);
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

    public static JSONObject delRedOperation(JSONObject Bloque, ListaSimple NetWorkNodes) {
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
