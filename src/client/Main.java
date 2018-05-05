package client;


import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class Main {
    public static final Logger logger=Logger.getLogger("log");

    public static void main(String[] args) {
        FileHandler fh;

        try {
            //Locale.setDefault(new Locale("en"));
            ResourceBundle turboWatch = ResourceBundle.getBundle("turboWatch");
            System.out.println(" POZDRAV " +turboWatch.getString("pozdrav"));
            Locale.setDefault(new Locale("en", "US"));
            turboWatch = ResourceBundle.getBundle("turboWatch");

            System.out.println(" POZDRAV " +turboWatch.getString("pozdrav"));

            System.out.println("");
            // This block configure the logger with handler and formatter
            fh = new FileHandler("resources/logFile.txt",true);
            logger.addHandler(fh);


            // the following statement is used to log any messages
            logger.info("My first log");

        } catch (IOException e1) {
            e1.printStackTrace();
        }

        logger.info("Hi How r u?");

    }
}
