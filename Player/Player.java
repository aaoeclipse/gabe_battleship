import java.io.*;
import java.net.*;
import java.util.Scanner;

class Player
{
    Socket serverSocket;
    OutputStream out; 
    InputStream in;
    DataOutputStream dos;
    DataInputStream dis;
    
     public static void main(String argv[]) throws IOException{
        Player client = new Player();
        Scanner scanner;
        
        byte[] toSend;
        byte[] recivedBytes;

        int serverPort = 2407;
        String ipServer = "127.0.0.1";
        // String ipServer = "3.87.32.191";
        
        String userInput;
        client.connect(ipServer, serverPort);

        System.out.print("User: ");
        scanner = new Scanner(System.in);
        userInput = scanner.nextLine();


        System.out.println(client.readMess());

        while (true){
            
            try {
                client.send(userInput);
            } catch (Exception e) {
             System.err.println("ERROR: client sending mssg");
            }

            System.out.print("User: ");
            scanner = new Scanner(System.in);
            userInput = scanner.nextLine();
        }

     }

     public void connect(String ipServer, int port){
         try {
            serverSocket = new Socket(ipServer, port);
            // input stream
            in = serverSocket.getInputStream();
            dis = new DataInputStream(in);
            // output stream
            out = serverSocket.getOutputStream(); 
            dos = new DataOutputStream(out);
         } catch (Exception e) {
             System.err.println("ERROR: En la conexion de sockets");
         }

     }
     public void send(String input) throws IOException{
        dos.writeUTF(input);
    }

    public String readMess()throws IOException{
        return dis.readUTF();
    }
}