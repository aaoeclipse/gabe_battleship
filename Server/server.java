import java.net.*; // makes the network programming easier
import java.io.*; 

import connections.*;
import objects.*;

public class server 
{ 
    public static void main(String... args){
        server battleship = new server();
    }

    // Socket configurations
    private Socket          clientSocket    = null; 
    private ServerSocket    serverSocket    = null; 
    // We use a thread to maintain the connection between the computer and the server
    private Thread          thread          = null;
    // We need to know when both players are connected
    // We make this static in order to be used in other classes
    public static int      playersConnected = 0;
    // Port in which the application will be listening
    private final int serverPort = 2407; 
    // Battle Ship communication Class
    private Login request;
    // Game ID
    public int idGame = 0;
    // Players
    public Player[] players;
    private int MAX_PLAYERS = 100;  // damn

    // For the server we only need the port number in which the
    // application will be running. 
    public server(){
        startServer();
    }

    public void startServer(){
        // Create max space of players
        players = new Player[MAX_PLAYERS]; 

        welcomeMessage();
        try {
            // The port opens and tries to make connection
            serverSocket = new ServerSocket(serverPort);
            System.out.println("[*] Server starting...");
            while (true){

                // Waiting for connection
                System.out.println("[*] Waiting for players...");
                clientSocket = serverSocket.accept();
                players[playersConnected] = new Player(clientSocket);
                System.out.println("[+] Player " + playersConnected + " connected!");
                
                // The thread can only run a class, therefore we create a class for the 
                // comunication between the computers. We pase the clientsocket and the server
                request = new Login(players[playersConnected]);
                thread = new Thread(request);
                playersConnected++;

                // Start the thread
                thread.start();
            }
            
        } catch (IOException e) {
            System.err.println("[-] Cannot establish socket!");
            System.err.println("[-] Check if port is in use");
        }
    }

    public void restartServer(){
        playersConnected = 0;
        try{
            serverSocket.close();
        } catch(IOException e){
            System.err.println("[-] cannot close socekt");
        }
        thread = null;
        startServer();
    }

    public void welcomeMessage(){
        System.out.println("----------------------------");
        System.out.println("BattleShip! V 0.4");
        System.out.println("Created by: Gabriel Morales");
        System.out.println("----------------------------");

    }
}
