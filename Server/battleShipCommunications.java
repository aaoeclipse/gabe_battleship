
final class battleShipCommunications implements Runnable{

    private Socket clientSocket = null;
    private int player;
    private server serverRunn;
    private int localId;
    String received;

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
                        case "hit":
                            System.out.println("Atacking!");
                            x = Integer.parseInt(dis.readUTF());
                            System.out.println("read 1: " + x);
                            y = Integer.parseInt(dis.readUTF());
                            System.out.println("read 2: " + y);

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
            System.out.println("Game ended");   
            clientSocket.close();



        } catch (Exception e) {
            exiting();
        }

    }
    public void exiting(){
        try {
            clientSocket.close();
            System.out.println("[*] connection closed");
            System.out.println("[*] Player "+ player + " has disconnected!");
            System.out.println();
            System.out.println("[*] Restarting Search...");
         } catch (Exception e1) {
             System.err.println("[-] ERROR: couldn't close connection");
             System.out.println("[*] Player "+ player + " has disconnected!");
             System.out.println();
             System.out.println("[*] Restarting Search...");
        }
        System.out.println();
        serverRunn.restartServer(); 
    }
}