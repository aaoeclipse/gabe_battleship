package connections;

import java.net.*;
import java.io.*;

import objects.*;

final public class Login implements Runnable{
    Player player;
    boolean notLoggedIn;
    public Login(Player player){
        this.player = player;
        notLoggedIn = true;
    }

    @Override
    public void run(){
        String username;
        while (notLoggedIn)  
            {
                try {
                    username = player.read.readUTF(); 
                    System.out.println("username");
                    player.write("hello there");
                } catch (Exception e) {
                    System.err.println("[-] (Login) Error with username");
                    try {
                        player.playerSocket.close();
                        
                    } catch (Exception e1) {
                        
                    }
                    notLoggedIn = !notLoggedIn;
                }
            }
    }

    public void searchUsername(){
        BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(
					"users.txt"));
			String line = reader.readLine();
			while (line != null) {
				System.out.println(line);
				// read next line
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

}