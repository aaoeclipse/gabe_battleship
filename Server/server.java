import java.net.*; // makes the network programming easier
import java.io.*; 
  
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
    private battleShipCommunications request;
    // For the server we only need the port number in which the
    // application will be running. 
    public server(){
        try {
            // The port opens and tries to make connection
            serverSocket = new ServerSocket(serverPort);
            System.out.println("[*] Server starting...");
            while (playersConnected != 2){
                // Waiting for connection
                System.out.println("[*] Waiting for players...");
                clientSocket = serverSocket.accept();
                playersConnected++;
    
                System.out.println("[+] Player " + playersConnected + " connected!");
                // The thread can only run a class, therefore we create a class for the 
                // comunication between the computers. We pase the clientsocket and the server
                request = new battleShipCommunications(clientSocket, playersConnected, this);
                thread = new Thread(request);
                // Start the thread
                thread.start();
            }
            
        } catch (IOException e) {
            System.err.println("[-] Cannot establish socket!");
            System.err.println("[-] Check if port is in use");
        }
    }
}

final class battleShipCommunications implements Runnable{

    private Socket clientSocket = null;
    private int player;
    private server serverRunn;
    public battleShipCommunications(Socket clientSocket, int player, server serverRunn){
        this.clientSocket = clientSocket;
        this.player = player;
        this.serverRunn = serverRunn;
    }
    @Override
    public void run(){
        try {
            DataInputStream dis = new DataInputStream(clientSocket.getInputStream()); 
            DataOutputStream dos = new DataOutputStream(clientSocket.getOutputStream()); 
            String received; 
            String toreturn; 
            boolean isAvailable = false;
            int x,y;
            dos.writeUTF("HELLO"); 
            while (true)  
            {
                try 
                {
                    received = dis.readUTF(); 

                    if(received.equals("Exit")) 
                    {
                        exiting();
                        break;
                    }

                    switch (received) { 
                        case "attack":
                            System.out.println("hihi");
                            x = Integer.parseInt(dis.readUTF());
                            System.out.println("read 1: " + x);
                            y = Integer.parseInt(dis.readUTF());
                            System.out.println("read 2: " + y);

                            /* TODO: check with other pc if location is taken */
                            System.out.println("finish");
                            if (isAvailable)
                                dos.writeUTF("hit");

                            System.out.println("break?");
                            break;

                        case "error":
                            break;
                        
                        default:
                            System.out.println("[-] No se entendio el mensaje: " + received);
                    }
                } 
                catch (Exception e) 
                {
                    exiting();
                    break;
                }
            }


        } catch (Exception e) {
            exiting();
        }

    }
    public void exiting(){
        try {
            clientSocket.close();
            System.out.println("[*] connection closed");
            System.out.println("[*] Player "+ player + " has disconnected!");
         } catch (Exception e1) {
             System.err.println("[-] ERROR: couldn't close connection");
             System.out.println("[*] Player "+ player + " has disconnected!");
         }
         
    }
}