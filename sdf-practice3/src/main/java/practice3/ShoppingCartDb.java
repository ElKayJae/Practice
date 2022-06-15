package practice3;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.Writer;
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
    public InputStream is;
    public Writer writer;


    //create directory
    public static void createDb(String cartDb){
        try{
            
            cartDbFolder = new File(cartDb);
            Path path = cartDbFolder.toPath();
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
        String saveLocation =  cartDbFolder.getPath() + File.separator + user;
        cartFile = new File(saveLocation);
        System.out.println(cartFile.toPath());
        if (cartFile.exists()){
            try {
                is = new FileInputStream(cartFile);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            Scanner scanner = new Scanner(is);
            while (scanner.hasNextLine()) {
                cartList.add(scanner.nextLine());                
                }
                scanner.close();
                return cartList;
            } else {
                System.out.println("user does not exist!");
                return new LinkedList<>();
            }

        }
    
    //write to Cart
    public void saveCart(String user, List<String> cartList){
        String saveLocation =  cartDbFolder.getPath() + File.separator + user;
        cartFile = new File(saveLocation);
    
        try {
            writer = new PrintWriter(cartFile);
            BufferedWriter bw = new BufferedWriter(writer);

            for (String item:cartList)
            bw.write(item+"\n");

            bw.flush();
            bw.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e){
                e.printStackTrace();
            }

        }
    
}
