/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.Estructuras;

import org.json.simple.JSONObject;

/**
 *
 * @author Steven
 */
public class ChainNode {
    private ChainNode Next, Prev;
    private String prevSha, CurrentSha;
    private int NONCE, Index;
    private JSONObject Data;
    
    public ChainNode(JSONObject Bloque, String Hash, int Index){
        this.Data = Bloque;
        this.Index = Index;
        this.Next = null;
        this.Prev = null;
        this.CurrentSha = Hash;
    }
    
    public String getHASH(){
        return this.CurrentSha;
    }
    
    public int getIndex(){
        return this.Index;
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
