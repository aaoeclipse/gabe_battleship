package objects;

import java.net.*;
import java.io.*; 

public class Player{

    public String name;
    public Socket playerSocket;

    public DataInputStream read; 
    public DataOutputStream write; 
    
    public Player(Socket playerSocket){
        this.playerSocket = playerSocket;

        // get players input and output streams
        try {
            this.read = new DataInputStream(playerSocket.getInputStream()); 
            this.write = new DataOutputStream(playerSocket.getOutputStream()); 
        } catch (Exception e) {
            System.err.println("[-] (Player) Error in creating player");
        }
        
    }

    public void setName(String name){
        this.name = name;
    }

    public void write(String mssg){
        try {
            write.writeUTF(mssg);    
            
        } catch (Exception e) {
            //TODO: handle exception
        }
    }
}