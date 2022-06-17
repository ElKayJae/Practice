package practice3;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class ShoppingCartDb {

    //initialise cart
    public List<String> cartList = new LinkedList<>();
    public File cartFile;
    public static File cartDbFolder;
    public FileInputStream fis;


    //create directory
    public static void createDb(String cartDb){
        try{
            // define the new file
            cartDbFolder = new File(cartDb);
            //get the path of the file and store it into path object
            Path path = cartDbFolder.toPath();
            //create directories only take in path objects (create folder)
            Files.createDirectories(path);
            System.out.println(cartDbFolder.getPath());
        } catch(IOException e){
            e.printStackTrace();
        }

    }
  
    public List<String> getCartList() {
        return cartList;
    }
    
    

    //load & create cart

    public List<String> loadCart(String user){
        // path\\user
        String saveLocation =  cartDbFolder.getPath() + File.separator + user +".txt";
        //define new file at location specified
        cartFile = new File(saveLocation);
        System.out.println(cartFile.toPath());

        //if file exists, read from file and put it into a list and return
        if (cartFile.exists()){
            try {
                //Inputstream can be used insteead of FileInputstream as well
                fis = new FileInputStream(cartFile);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            //BufferedReader can us used instead of Scanner
            //will need to pass InputStreamReader into BufferedReader
            Scanner scanner = new Scanner(fis);
            while (scanner.hasNextLine()) {
                cartList.add(scanner.nextLine());                
                }
                scanner.close();
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return cartList;
            } else {
                //if file does not exist, return an empty list
                System.out.println("user does not exist!");
                return new LinkedList<>();
            }

        }
    
    //write to Cart
    public void saveCart(String user, List<String> cartList){

        //define new file object same as above step in load function
        String saveLocation =  cartDbFolder.getPath() + File.separator + user+".txt";
        cartFile = new File(saveLocation);
    
        try {
            //can use Writer instead of PrintWriter
            PrintWriter printWriter = new PrintWriter(cartFile);
            BufferedWriter bw = new BufferedWriter(printWriter);

            // OutputStream os = new FileOutputStream(cartFile);
            // OutputStreamWriter osw = new OutputStreamWriter(os);
            // BufferedWriter bw = new BufferedWriter(osw);

            for (String item:cartList)
            bw.write(item+"\n");

            bw.flush();
            bw.close();
            printWriter.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e){
                e.printStackTrace();
            }

        }
    
}
