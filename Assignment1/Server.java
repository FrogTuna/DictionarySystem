import javax.net.ServerSocketFactory;
import javax.swing.*;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.ParseException;
import java.util.concurrent.ExecutorService;

public class Server {

    //fields
    ExecutorService threadPool;
    private static int port = 3500;
    private static int counter = 0;

    Server(){


    }
    
    public static void receivedJSON(Socket client) throws ParseException {
    	
    	
            try {
                Socket clientSocket = client;

                try {
                    JSONParser parser = new JSONParser();
                    DataInputStream input = new DataInputStream(clientSocket.getInputStream());
                    DataOutputStream output = new DataOutputStream(clientSocket.getOutputStream());
                    System.out.println("CLIENT: " + input.readUTF());

                    
                } finally {
                    if (client != null) {
                        client.close();
                    }

                }
        } catch (IOException var18) {
            var18.printStackTrace();
        }
    	
    	
    }



    public static void main(String[] args){



            try {
                ServerSocket server = new ServerSocket(port);

                try {
                    System.out.println("Waiting for client connection..");

                    while(true) {
                        Socket client = server.accept();
                    }
                } finally {
                    if (server != null) {
                        server.close();
                    }}} 
                catch (IOException var15) {
            var15.printStackTrace();
                }
    }
    	
}
