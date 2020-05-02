/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JSONCreator;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Steven
 */
public class NodeCreator {
    public NodeCreator(){
        
    }
    
    private byte[] hasEncoded(String source){
        try {
            MessageDigest diggest = MessageDigest.getInstance("SHA-256");
            byte[] encoded = diggest.digest(source.getBytes(StandardCharsets.UTF_8));
            return encoded;
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(NodeCreator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    private String toHex(byte[] encoded){
        StringBuilder hexcoded = new StringBuilder();
        for(int i=0;i<encoded.length;i++){
            String hex = Integer.toHexString(0xff & encoded[i]);
            if(hex.length() == 1) hexcoded.append('0');
            hexcoded.append(hex);
        }
        return hexcoded.toString();
    }
    
    public String getSha256of(String source){
        byte[] encoded = hasEncoded(source);
        return toHex(encoded);        
    }
    
    public String getMD5From(String Password){
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(Password.getBytes());
            byte[] encoded = md.digest();
            return toHex(encoded);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(NodeCreator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public int NonceCalc(String newNodeString){
        int NONCE = 0;
        System.out.println("Looking for NONCE");
        while(true){
            //System.out.println("Testing with NONCE: "+NONCE);
            String candidate = getSha256of(newNodeString+String.valueOf(NONCE));
            if(candidate.substring(0, 4).equals("0000")){
                break;
            }
            NONCE++;
        }
        return NONCE;
    }
    
}
