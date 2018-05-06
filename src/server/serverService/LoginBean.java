package server.serverService;

import model.User;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
@Remote(LoginBeanRemote.class)
public class LoginBean implements LoginBeanRemote {
    public static final Logger logger=Logger.getLogger("log");


    public LoginBean() {
    }


    public User getUser(){

        User user=null;
        Connection con = null;
        String driver = "org.postgresql.Driver";
        try {
            FileHandler fh = new FileHandler("C:\\Programovanie\\Java\\Vava20182\\resources\\log2.txt");
            logger.addHandler(fh);

            Class.forName(driver).newInstance();
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/vava",
                    "postgres" , "loginHeslo");

            Statement st = con.createStatement();

            String str= "SELECT * FROM public.users";
            System.out.println("QUERy "  + str);
            ResultSet rs = st.executeQuery(str);


            rs.next();
            logger.info("--------------------- " + rs.getInt(1));
            //System.out.println("asdasdad " + rs.getInt(1) + " " + rs);
            user= new User(rs.getInt(1),rs.getString(2),
                    rs.getString(3),rs.getString(4));
            System.out.println("User name " + user.getLogin() + " LOGIN " + user.getPassword() + " MAIL " + user.getEmail());
            return user;
        } catch (Exception e){

            logger.log(Level.INFO,"ERROR ",e);
            e.printStackTrace();
        }
        return null;
        //return "User name " + user.getLogin();
    }

    public User authentification(User user){


        Connection con = null;
        String driver = "org.postgresql.Driver";
        try {
            FileHandler fh = new FileHandler("C:\\Programovanie\\Java\\Vava20182\\resources\\log2.txt");
            logger.addHandler(fh);

            Class.forName(driver).newInstance();
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/vava",
                    "postgres" , "loginHeslo");

            Statement st = con.createStatement();

            String str= String.format("SELECT * FROM public.users" +
                    " WHERE login = '%s'",user.getLogin());
            System.out.println("QUERy "  + str);
            ResultSet rs = st.executeQuery(str);


            rs.next();
            //System.out.println("asdasdad " + rs.getInt(1) + " " + rs);
            user= new User(rs.getInt(1),rs.getString(2),
                    rs.getString(3),rs.getString(4));
            System.out.println("User name " + user.getLogin() + " LOGIN " + user.getPassword() + " MAIL " + user.getEmail());
            return user;
        } catch (Exception e){

            logger.log(Level.INFO,"ERROR ",e);
            e.printStackTrace();
        }
        return null;
        //return "User name " + user.getLogin();
    }

}
