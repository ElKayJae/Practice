package practice4.Server;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Cookie {

 


    public static String randomCookie(String cookieFilePath){

        String randomCookie;
        List<String> cookieList = new LinkedList<>();

        
            try {
                Scanner scanner = new Scanner(new File(cookieFilePath));
                while (scanner.hasNextLine()){
                    String cookie = scanner.nextLine();
                    cookieList.add(cookie);
                }
                scanner.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
    
            System.out.println(cookieList);
            Random random = new Random();
            int index = random.nextInt(cookieList.size());
            randomCookie = cookieList.get(index);
            
            return randomCookie;
        
    }
}
