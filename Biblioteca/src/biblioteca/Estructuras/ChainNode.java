/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.Estructuras;

import JSONCreator.Constantes;
import org.json.simple.JSONObject;

/**
 *
 * @author Steven
 */
public class ChainNode {
    private ChainNode Next, Prev;
    private int NONCE, Index;
    private JSONObject Data;
    
    public ChainNode(JSONObject Bloque, int Index){
        this.Data = Bloque;
        this.Index = Index;
        this.Next = null;
        this.Prev = null;
    }
        
    public int getIndex(){
        return this.Index;
    }
    
    public String getTimeStamp(){
        return Data.get(Constantes.JSON_TIMESTAMP).toString();
    }
    
    public String getNONCE(){
        return Data.get(Constantes.JSON_NONCE).toString();
    }
    
    public String getPrevHash(){
        return Data.get(Constantes.JSON_PREVIOUSHASH).toString();
    }
    
    public String getHash(){
        return Data.get(Constantes.JSON_HASH).toString();
    }
    
    public JSONObject getData(){
        return this.Data;
    }
    
    public void setNext(ChainNode next){
        this.Next = next;
    }
    
    public void setPrev(ChainNode prev){
        this.Prev = prev;
    }
    
    public ChainNode getPrev(){
        return this.Prev;
    }
    
    public ChainNode getNext(){
        return this.Next;
    }
}
