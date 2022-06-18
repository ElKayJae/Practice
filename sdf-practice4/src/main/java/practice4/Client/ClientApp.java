package practice4.Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientApp {

    //mvn compile exec:java -Dexec.mainClass="practice4.Client.ClientApp" -Dexec.args="0.0.0.0:3000"
public static void main(String[] args) {
    
    //read input from command line
    String arg = args[0];
    String[] arr = arg.split(":", 2);
    String host = arr[0];
    int port = Integer.parseInt(arr[1]);

    try{

        //connect with server        
        Socket clientSocket = new Socket(host, port);

        //setup input and output streams
        InputStream isr = clientSocket.getInputStream();
        DataInputStream dis = new DataInputStream(isr);

        OutputStream ois = clientSocket.getOutputStream();
        DataOutputStream dos = new DataOutputStream(ois);

  
        // reading from terminal
        Scanner scanner = new Scanner(System.in);
        System.out.print(" input command: ");
        String input = scanner.nextLine();
        scanner.close();

        //write output to outputstream
        dos.writeUTF(input);
        dos.flush();

        //input from server inputstream
        String serverResponse=dis.readUTF();
        //removes cookie-text from the input
        String[] split = serverResponse.split(" ", 2);
        System.out.println(split[1]);

        clientSocket.close();

        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
