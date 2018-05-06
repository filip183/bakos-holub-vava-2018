package main;


import model.User;
import controller.*;
import server.serverService.LoginBean;
import server.serverService.LoginBeanRemote;
import server.serverService.Service;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class Main {
    public static final Logger logger=Logger.getLogger("log");
    public static Controller controller = new Controller();
    static User userGlobal;

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



            //invokeLogin();
//            Context context = new InitialContext();
//            LoginBean loginBean = (LoginBean) context.lookup("ejb:server/serverService/LoginBean");
//
//            System.out.println(loginBean.getUser().getLogin());



        } catch (Exception e1) {
            e1.printStackTrace();
        }



    }

    public User invokeLogin(User user) throws NamingException {
        // Let's lookup the remote stateless calculator
        final LoginBeanRemote statelessLoginBean = lookupRemoteLoginBean();


        User user1 = statelessLoginBean.authentification(user);
        System.out.println("USER " + user1.getLogin());
        if (user!=null && user.getPassword().equals(user1.getPassword())){
            userGlobal=user1;
            return user1;
        } else {
            userGlobal=null;
            return null;
        }


    }

    private static LoginBeanRemote lookupRemoteLoginBean() throws NamingException {
        final Hashtable jndiProperties = new Hashtable();
        Properties prop= new Properties();

        final Context context = new InitialContext();

        return (LoginBeanRemote) context.lookup("ejb:/Vava20182_war_exploded//LoginBean!server.serverService.LoginBeanRemote");
    }
}
