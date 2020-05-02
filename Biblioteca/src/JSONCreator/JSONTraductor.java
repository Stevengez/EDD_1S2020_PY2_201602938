/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JSONCreator;

import Network.NetworkManager;
import biblioteca.Estructuras.ArbolAVL;
import biblioteca.Estructuras.ListaSimple;
import biblioteca.Estructuras.NodoSimple;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Steven
 */
public class JSONTraductor {
    private JSONTraductor(){
        
    }
    
    public static void DataHandler(JSONArray Data){
        for(Object Accion : Data){
                        
        }
        
    }
    
    public static void parseDataBlock(String JSONString, NetworkManager NetManager, ArbolAVL Categorias, Object Usuarios){
        try {
            JSONParser JParser = new JSONParser();
            
            JSONObject Bloque = (JSONObject)JParser.parse(JSONString);
            JSONArray Data = (JSONArray)Bloque.get(Constantes.JSON_DATA_LABEL);
            
            for(Object Operacion : Data){
                JSONObject operacion = (JSONObject)Operacion;
                
                for(Object ID : operacion.keySet()){
                    switch((String)ID){
                        case Constantes.JSON_RED_ADD:
                            JSONArray AddRed = (JSONArray) operacion.get(Constantes.JSON_RED_ADD);
                            for(Object nodo : AddRed){
                                JSONObject servidorRemoto = (JSONObject)nodo;
                                NetManager.getNetworkList().AddNetNode(servidorRemoto.get(Constantes.JSON_RED_NODO_IP).toString(),Integer.parseInt(servidorRemoto.get(Constantes.JSON_RED_NODO_SERVERPORT).toString()));
                            }
                            if(NetManager.getNetworkList().getNodeSize()>1){
                                NetManager.setUniqueNodeFlag();
                            }
                            break;
                        case Constantes.JSON_RED_DELETE:
                            JSONArray DelRed = (JSONArray) operacion.get(Constantes.JSON_RED_DELETE);
                            for(Object nodo : DelRed){
                                JSONObject servidorRemoto = (JSONObject)nodo;
                                NetManager.getNetworkList().deleteNetNode(servidorRemoto.get(Constantes.JSON_RED_NODO_IP).toString());
                            }
                            if(NetManager.getNetworkList().getNodeSize()==1){
                                NetManager.setUniqueNodeFlag();
                            }
                            break;
                    }
                }
            }
        } catch (ParseException ex) {
            Logger.getLogger(JSONTraductor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static JSONObject createBlock(){
        JSONObject Bloque = new JSONObject();
            JSONArray Data = new JSONArray(); //Array de Operaciones - > KeySet
        Bloque.put(Constantes.JSON_DATA_LABEL, Data);
        return Bloque;
    }
    
    public static JSONObject addRedOperation(JSONObject Bloque, ListaSimple NetWorkNodes){
        JSONObject Operacion = new JSONObject();
            JSONArray AddRed = new JSONArray();
        
            NodoSimple aux = NetWorkNodes.getHead();
        while(aux!=null){
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
    
    public static JSONObject delRedOperation(JSONObject Bloque, ListaSimple NetWorkNodes){
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
    
}
