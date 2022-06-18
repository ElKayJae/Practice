package practice4.Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Hello world!
 *
 */
public class ServerApp 
{
    //mvn compile exec:java -Dexec.mainClass="practice4.Server.ServerApp" -Dexec.args="3000 C:\Users\lowke\Desktop\NUSISS\Practice\sdf-practice4\src\main\java\practice4\Server\Cookietext.txt"
    public static void main( String[] args )
    {
        Integer port = Integer.parseInt(args[0]);
        String cookieFilePath = args[1];

        System.out.println( "Hello World!" );
        try{
            //start server
            ServerSocket server = new ServerSocket(port);
            Socket serverSocket = server.accept(); 
            System.out.println("connected");

            //setup input and output stream
            InputStream isr = serverSocket.getInputStream();
            DataInputStream dis = new DataInputStream(isr);

            OutputStream ois = serverSocket.getOutputStream();
            DataOutputStream dos = new DataOutputStream(ois);

            //read input from inputstream
            String input = dis.readUTF();
            if (input.equals("get-cookie")){
                String randomCookie = Cookie.randomCookie(cookieFilePath);
                System.out.println(randomCookie);

                //write output to outputstream
                dos.writeUTF("cookie-text "+ randomCookie);
                dos.flush();
            } else {
                System.out.println("Invalid command");
            }
        

            server.close();
            serverSocket.close();
               
            }catch (IOException e){
                e.printStackTrace();
            };           
        }       
    }

