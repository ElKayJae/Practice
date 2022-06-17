package practice3;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;


public class Session {
    public List<String> cartList = new LinkedList<>();

    String currentUser;

    public void start(){

        ShoppingCartDb shoppingCartDb = new ShoppingCartDb();

        Scanner scanner = new Scanner(System.in);
        String cmd ="";
        
        
        while (!cmd.equals("stop")){
            System.out.print("> ");
            String input = scanner.nextLine();
            //split input from console into 2 parts with " "
            String[] splitInput = input.split(" ", 2);
            cmd = splitInput[0];
            
            switch (cmd) {
                case "login":
                    currentUser = splitInput[1];
                    cartList=shoppingCartDb.loadCart(currentUser);                
                    break;
    
                case "add":
                    String[] itemList = splitInput[1].split(",");

                    for (String item:itemList){
                        String trimmedItem = item.trim();
                        if (!cartList.contains(trimmedItem))
                        cartList.add(trimmedItem);
                    }
                   break;
    
                case "save":
                    shoppingCartDb.saveCart(currentUser,cartList);               
                    break;

                case "list":
                    for (int i = 0; i < cartList.size(); i++) {
                        System.out.println(i+1+". " + cartList.get(i));
                    }           
                    break;
    
            
                default:
                    System.out.println("invalid command!");
                    break;
            }
        }
    

        scanner.close();

    }
    
    
    
}
