/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.Estructuras;

import GUI.Settings;
import JSONCreator.Constantes;
import JSONCreator.JSONCreator;
import biblioteca.Biblioteca;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
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
public class BlockChain {

    private ChainNode Inicio, Final;
    private int Size;
    private Path currentDir;
    private Biblioteca LibraryManager;

    public BlockChain(Biblioteca LibraryManager) {
        this.LibraryManager = LibraryManager;
        this.Inicio = null;
        this.Final = null;
        this.Size = 0;
        currentDir = Paths.get("").toAbsolutePath();
        validateFolder();
        loadSavedBlocks();
    }

    private void validateFolder() {
        System.out.println("Current Blockchain dir is: " + currentDir);
        File ConfigDir = new File(currentDir + "\\" + Constantes.JSON_BLOCKLIST_FOLDER);
        if (!ConfigDir.exists()) {
            ConfigDir.mkdir(); // Creamos la carpeta de Bloques
        }
    }

    public void loadSavedBlocks() {
        FileReader reader = null;
        File lista = new File(currentDir + "\\" + Constantes.JSON_BLOCKLIST_FOLDER + "\\" + Constantes.JSON_BLOCKLIST_FILE + Constantes.JSON_BLOCKLIST_FILE_EXT);
        System.out.println("BlockCHain:: Verificado Bloques Previos");
        if (lista.exists()) {
            System.out.println("BlockCHain:: Ya Existe el listado de Bloques.");
            try {
                reader = new FileReader(currentDir + "\\" + Constantes.JSON_BLOCKLIST_FOLDER + "\\" + Constantes.JSON_BLOCKLIST_FILE + Constantes.JSON_BLOCKLIST_FILE_EXT,StandardCharsets.UTF_8);
                JSONParser jparser = new JSONParser();
                JSONObject Bloque = (JSONObject) jparser.parse(reader);
                JSONArray Listado = (JSONArray) Bloque.get(Constantes.JSON_BLOCKLIST_LABEL);

                for (Object bloque : Listado) {
                    System.out.println("BlockChain:: Cargando un Bloque");
                    File bloqueactual = new File(currentDir + "\\" + Constantes.JSON_BLOCKLIST_FOLDER + "\\" + ((JSONObject) bloque).get(Constantes.JSON_BLOCKLIST_ARRAY_FILENAME));
                    if (bloqueactual.exists()) {
                        System.out.println("El bloque del listado existia.");
                        FileReader bloquereader = new FileReader(bloqueactual,StandardCharsets.UTF_8);
                        JSONObject cargarbloque = (JSONObject) jparser.parse(bloquereader);
                        if (JSONCreator.validateBlock(cargarbloque, this)) {
                            System.out.println("Valida el bloque y lo agregue a la lissta en memoria");
                            AddNode(cargarbloque, true, false);
                        } else {
                            System.out.println("Bloque corrupto: \n" + cargarbloque.toJSONString());
                        }
                    }

                }
                System.out.println("EL INDEX ACTUAL ES: "+getNextIndex());
            } catch (FileNotFoundException ex) {
                Logger.getLogger(BlockChain.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(BlockChain.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(BlockChain.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    reader.close();
                } catch (IOException ex) {
                    Logger.getLogger(BlockChain.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void generateBlockListFile() {
        JSONObject Listado = new JSONObject();
        JSONArray Bloques = new JSONArray();
        ChainNode aux = this.Inicio;
        while (aux != null) {
            JSONObject nuevo = new JSONObject();
            nuevo.put(Constantes.JSON_BLOCKLIST_ARRAY_FILENAME, Constantes.JSON_BLOCKLIST_FILE_PREFIX + aux.getIndex() + Constantes.JSON_BLOCKLIST_FILE_EXT);
            Bloques.add(nuevo);
            aux = aux.getNext();
        }
        Listado.put(Constantes.JSON_BLOCKLIST_LABEL, Bloques);
        writeToFile(currentDir + "\\" + Constantes.JSON_BLOCKLIST_FOLDER + "\\" + Constantes.JSON_BLOCKLIST_FILE + Constantes.JSON_BLOCKLIST_FILE_EXT, Listado.toJSONString());
    }

    public void writeToFile(String Path, String Contenido) {
        FileWriter newFile = null;
        try {
            
            newFile = new FileWriter(new File(Path),StandardCharsets.UTF_8);
            newFile.write(Contenido);
            newFile.flush();
            /*
            Writer fstream = null;
            BufferedWriter out = null;
            fstream = new OutputStreamWriter(new FileOutputStream(Path), StandardCharsets.UTF_8);
            fstream.write(Contenido);
            fstream.close();
            */
        } catch (IOException ex) {
            Logger.getLogger(Settings.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void AddNode(JSONObject Bloque, boolean LocalJSON, boolean MyBlock) {
        System.out.println("Agregue un Bloque.");
        if (Inicio == null) {
            Inicio = new ChainNode(Bloque, "0000", Size);
            Final = Inicio;
            if (!LocalJSON) {
                writeToFile(currentDir + "\\" + Constantes.JSON_BLOCKLIST_FOLDER + "\\"
                        + Constantes.JSON_BLOCKLIST_FILE_PREFIX
                        + this.Size
                        + Constantes.JSON_BLOCKLIST_FILE_EXT,
                        Bloque.toJSONString());
            }
            if (!MyBlock) {
                JSONCreator.parseDataBlock(Bloque.toJSONString(), LibraryManager.getNetworkManager(), LibraryManager.getLibreroGlobal(), LibraryManager.getLibrero(), LibraryManager.getUsuarios(), LocalJSON, null);
            }
            this.Size++;
        } else {
            System.out.println("Agregando un Nodo con el HASH: " + Bloque.get(Constantes.JSON_HASH));
            if (getNodo(Bloque.get(Constantes.JSON_HASH) + "") == null) {
                ChainNode nuevo = new ChainNode(Bloque, (String) Bloque.get(Constantes.JSON_HASH), Size);
                Final.setNext(nuevo);
                nuevo.setPrev(Final);
                Final = nuevo;
                if (!LocalJSON) {
                    writeToFile(currentDir + "\\" + Constantes.JSON_BLOCKLIST_FOLDER + "\\"
                            + Constantes.JSON_BLOCKLIST_FILE_PREFIX + this.Size
                            + Constantes.JSON_BLOCKLIST_FILE_EXT,
                            Bloque.toJSONString());
                }
                if (!MyBlock) {
                    JSONCreator.parseDataBlock(Bloque.toJSONString(), LibraryManager.getNetworkManager(), LibraryManager.getLibreroGlobal(), LibraryManager.getLibrero(), LibraryManager.getUsuarios(), LocalJSON, null);
                }

                this.Size++;
            } else {
                System.out.println("Ya existe este ChainNode");
            }
        }
    }
    
    public void AddNode(JSONObject Bloque, boolean LocalJSON, boolean MyBlock, boolean FromPeer) {
        System.out.println("Agregue un Bloque.");
        if (Inicio == null) {
            Inicio = new ChainNode(Bloque, "0000", Size);
            Final = Inicio;
            if (FromPeer) {
                writeToFile(currentDir + "\\" + Constantes.JSON_BLOCKLIST_FOLDER + "\\"
                        + Constantes.JSON_BLOCKLIST_FILE_PREFIX
                        + this.Size
                        + Constantes.JSON_BLOCKLIST_FILE_EXT,
                        Bloque.toJSONString());
            }
            if (!MyBlock) {
                JSONCreator.parseDataBlock(Bloque.toJSONString(), LibraryManager.getNetworkManager(), LibraryManager.getLibreroGlobal(), LibraryManager.getLibrero(), LibraryManager.getUsuarios(), LocalJSON, null);
            }
            this.Size++;
        } else {
            System.out.println("Agregando un Nodo con el HASH: " + Bloque.get(Constantes.JSON_HASH));
            if (getNodo(Bloque.get(Constantes.JSON_HASH) + "") == null) {
                ChainNode nuevo = new ChainNode(Bloque, (String) Bloque.get(Constantes.JSON_HASH), Size);
                Final.setNext(nuevo);
                nuevo.setPrev(Final);
                Final = nuevo;
                if (FromPeer) {
                    writeToFile(currentDir + "\\" + Constantes.JSON_BLOCKLIST_FOLDER + "\\"
                            + Constantes.JSON_BLOCKLIST_FILE_PREFIX + this.Size
                            + Constantes.JSON_BLOCKLIST_FILE_EXT,
                            Bloque.toJSONString());
                }
                if (!MyBlock) {
                    JSONCreator.parseDataBlock(Bloque.toJSONString(), LibraryManager.getNetworkManager(), LibraryManager.getLibreroGlobal(), LibraryManager.getLibrero(), LibraryManager.getUsuarios(), LocalJSON, null);
                }

                this.Size++;
            } else {
                System.out.println("Ya existe este ChainNode");
            }
        }
    }
    
    public ChainNode getIndex(int Index){
        ChainNode temp = this.Inicio;
        while(temp!=null){
            if(temp.getIndex()==Index) return temp;
            temp = temp.getNext();
        }
        return null;
    }
    
    public ChainNode getHead(){
        return this.Inicio;
    }

    public ChainNode getNodo(String Sha256) {
        ChainNode aux = this.Inicio;
        while (aux != null) {
            if (aux.getHASH().equals(Sha256)) {
                return aux;
            }
            aux = aux.getNext();
        }
        return null;
    }

    public int getNextIndex() {
        return this.Size;
    }

    public String getLastHash() {
        if (this.Final == null) {
            return "0000";
        }
        return this.Final.getHASH();
    }

}
