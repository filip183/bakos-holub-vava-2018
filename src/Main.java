


import model.User;
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
         //   fh = new FileHandler("/resources/logFile.txt",true);
         //   logger.addHandler(fh);


            // the following statement is used to log any messages
            logger.info("My first log");


            invokeLogin();
//            Context context = new InitialContext();
//            LoginBean loginBean = (LoginBean) context.lookup("ejb:server/serverService/LoginBean");
//
//            System.out.println(loginBean.getUser().getLogin());



        } catch (Exception e1) {
            e1.printStackTrace();
        }

        logger.info("Hi How r u?");

    }

    private static void invokeLogin() throws NamingException {
        // Let's lookup the remote stateless calculator
        final LoginBeanRemote statelessLoginBean = lookupRemoteLoginBean();


        User user = statelessLoginBean.getUser();
        System.out.println("Name = " + user.getLogin());

    }

    private static LoginBeanRemote lookupRemoteLoginBean() throws NamingException {
        final Hashtable jndiProperties = new Hashtable();
        Properties prop= new Properties();
        prop.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
        prop.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");

        //prop.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        final Context context = new InitialContext(prop);
        // final Context context = new InitialContext();
//        final String appName = "VaVa20182";
//
//        final String moduleName = "Vava20182_ejb_exploded";
//
//        final String distinctName = "";
//        final String beanName = LoginBean.class.getSimpleName();
//        final String viewClassName = LoginBean.class.getName();
        return (LoginBeanRemote) context.lookup("ejb:/Vava20182_war_exploded//LoginBean!server.serverService.LoginBeanRemote");
    }
}
