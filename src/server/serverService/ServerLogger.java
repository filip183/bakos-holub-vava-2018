package server.serverService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;

public class ServerLogger {
    public static final java.util.logging.Logger logger= java.util.logging.Logger.getLogger(LoginBean.class.getName());

    public static void log(Level level,String string, Throwable thrown) {
        if (logger.getHandlers().length==0)
            addHandler();
        logger.log(level,string,thrown);
    }

    public static void log(Level level,String string) {
        if (logger.getHandlers().length==0)
            addHandler();
        if (logger.getHandlers().length==1)
            addHandler();
        logger.log(level,string);
    }

    private static void addHandler() {
        String riadok=null;
        try {
            FileReader fileReader;
            BufferedReader bufferedReader;

//            try {
//                fileReader = new FileReader("/resources/serverLogerConfig.txt");
//                bufferedReader = new BufferedReader(fileReader);
//                riadok = bufferedReader.readLine();
//            }
//            catch (Exception e){
//                logger.log(Level.SEVERE,"Chyba pri citani config pre log",e);
//            }
//            if(riadok!=null) {
                FileHandler fh = new FileHandler("C:\\Programovanie\\Java\\Vava20182\\resources\\ServerlogFile.txt", true);
                logger.addHandler(fh);
//            }
        }
        catch (IOException e){
            logger.log(Level.SEVERE,"Cant open handler file");
        }
    }
}
