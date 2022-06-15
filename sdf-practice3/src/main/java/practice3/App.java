package practice3;

/**
 * Hello world!
 *
 */
public class App 
{
    //mvn compile exec:java -Dexec.mainClass="practice3.App" -Dexec.args="cartdb"
    public static String cartDb = "db";
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        if (args.length >0 )
        cartDb = args[0];
        ShoppingCartDb.createDb(cartDb);
        Session session = new Session();
        session.start();

    }
}
