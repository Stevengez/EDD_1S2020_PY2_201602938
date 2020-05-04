/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JSONCreator;

/**
 *
 * @author Steven
 */
public class Constantes {
    public final static String RED_SERVER_PORT = "PORTSERVER";
    public final static String RED_CLIENT_PORT = "PORTCLINET";
    public final static String RED_HOSTNAME = "HOSTNAME";
    public final static String RED_HOST_SYNC = "HOSTNAMEIPCSYNC";
    public final static String RED_PORT_SYNC = "HOSTPORTSYNC";
    public final static String RED_BROADCAST_PORTSERVER = "BROADCASTPORTSERVER";
    public final static String RED_BROADCAST_PORTCLIENT = "BROADCASTPORTCLIENT";
    public final static String RED_BROADCAST_MASK = "BROADCASMASK";
    
    public final static int RED_DEFAULT_SERVER_PORT = 8000;
    public final static int RED_DEFAULT_CLIENT_PORT = 8530;
    public final static String RED_DEFAULT_HOSTNAME = "localhost";
    public final static String RED_DEFAULT_HOST_SYNC = "localhost";
    public final static int RED_DEFAULT_PORT_SYNC = 8000;
    public final static int RED_DEFAULT_BROADCAST_PORTSERVER = 9000;
    public final static int RED_DEFAULT_BROADCAST_PORTCLIENT = 9530;
    public final static String RED_DEFAULT_BROADCAST_MASK = "192.168.0.255";
    
    public final static int RED_DEFAULT_TIMEOUT = 1000;
    
    /* BlockChain */
    public final static String JSON_BLOCKLIST_LABEL = "BLOQUES_LISTADO";
    public final static String JSON_BLOCKLIST_ARRAY_FILENAME = "FILE_NAME";
    public final static String JSON_BLOCKLIST_FILE_PREFIX = "Block_";
    
    public final static String JSON_BLOCKLIST_FILE = "BlockList";
    public final static String JSON_BLOCKLIST_FILE_EXT = ".json";
    public final static String JSON_BLOCKLIST_FOLDER = "Bloques";
    
    /* NETWORK REQUEST */
    
    public final static String REQUEST_NETWORKNODES = "R:NETWORKNODES";
    public final static String REQUEST_CLOSESOCKET = "R:CLOSESOCKET";
    public final static String REQUEST_ADDNODE = "R:ADDNODE";
    public final static String REQUEST_ADDNODE_CONFIRMATION = "C:NODEADDED";
    public final static String REQUEST_ADDNODE_ERROR = "CE:ERRORCORRUPTED";
    
    /* JSON LABEL */
    
    public final static String JSON_DATA_LABEL = "DATA";
    public final static String JSON_INDEX = "INDEX";
    public final static String JSON_TIMESTAMP = "TIMESTAMP";
    public final static String JSON_NONCE = "NONCE";
    public final static String JSON_PREVIOUSHASH = "PREVIOUSHASH";
    public final static String JSON_HASH = "HASH";
    
    
    public final static String JSON_USUARIOS_ADD = "CREAR_USUARIO";
    public final static String JSON_USUARIOS_EDIT = "EDITAR_USUARIO";
    public final static String JSON_USUARIOS_DELETE = "ELIMINAR_USUARIO";
    
    public final static String JSON_BOOKS_ADD = "CREAR_LIBRO";
    public final static String JSON_BOOKS_DELETE = "ELIMINAR_LIBRO";
    
    public final static String JSON_CATEGORY_ADD = "CREAR_CATEGORIA";
    public final static String JSON_CATEGORY_DELETE = "ELIMINAR_CATEGORIA";
    
    /* JSON LABEL RED */
    
    public final static String JSON_RED_NODOS_ARRAY = "NODOS";
    public final static String JSON_RED_ADD = "RED_ADD";
    public final static String JSON_RED_DELETE = "RED_DELETE";
    public final static String JSON_RED_NODO_IP = "NODO_IP";
    public final static String JSON_RED_NODO_SERVERPORT = "NODO_PORT";
        
    /* JSON LABEL USUARIOS */
    
    public final static String JSON_USER_CARNET = "Carnet";
    public final static String JSON_USER_NOMBRE = "Nombre";
    public final static String JSON_USER_APELLIDO = "Apellido";
    public final static String JSON_USER_CARRERA = "Carrera";
    public final static String JSON_USER_PASSWORD = "Password";
    
    /* Constantes estructuras */
    
    public final static int HASH_TABLE_MAXSIZE = 45;
    
    /* GUI Constantes */
    
    public final static String GUI_VENTANA_OPCIONES = "CONFIGURACION";
    public final static String GUI_VENTANA_CARGA_USUARIOS = "CARGAUSUARIOS";
    public final static String GUI_VENTANA_CARGA_LIBROS = "CARGALIBROS";
    public final static String GUI_VENTANA_MI_CUENTA = "MIPERFIL";
    public final static String GUI_VENTANA_SYNCBLOCK = "SINCRONIZADOR";
    
    /* Constantes del Menu */
    
    public final static String MENU_OPCION_Bilioteca_LogInOut = "LOGINOUT";
    public final static String MENU_OPCION_Bilioteca_MyVirtual = "MYVIRTUAL";
    public final static String MENU_OPCION_Bilioteca_Virtual = "VIRTUAL";
    public final static String MENU_OPCION_Bilioteca_Opciones = "CONFIG";
    public final static String MENU_OPCION_Bilioteca_salir = "SALIR";
    public final static String MENU_OPCION_CargaMasica_Usuarios = "CUSUARIOS";
    public final static String MENU_OPCION_CargaMasica_Libros = "CLIBROS";
    public final static String MENU_OPCION_Reportes_ArbolAVL = "RAVL";
    public final static String MENU_OPCION_Reportes_ArbolB = "RB";
    public final static String MENU_OPCION_Reportes_TablaHash = "RT";
    public final static String MENU_OPCION_Reportes_BlockCHain = "RB";
    public final static String MENU_OPCION_Reportes_NetworkList = "RR";
    public final static String MENU_OPCION_BLOCKCHAIN_SYNC = "BCS";
    
    
    private Constantes(){
        
    }
}
