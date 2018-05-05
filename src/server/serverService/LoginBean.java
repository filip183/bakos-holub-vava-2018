package server.serverService;

import model.User;

import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
@Path("/getUser")
public class LoginBean implements LoginBeanRemote {
    public static final Logger logger=Logger.getLogger("log");


    public LoginBean() {
    }

    @GET
    public String getUser(){

        User user=null;
        Connection con = null;
        String driver = "org.postgresql.Driver";
        try {
            FileHandler fh = new FileHandler("C:\\Programovanie\\Java\\Vava20182\\resources\\log2.txt");
            logger.addHandler(fh);

            Class.forName(driver).newInstance();
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/vava",
                    "postgres" ,"heslo");

            Statement st = con.createStatement();

            String str= "SELECT * FROM public.users";
            System.out.println("QUERy "  + str);
            ResultSet rs = st.executeQuery(str);


            rs.next();
            //System.out.println("asdasdad " + rs.getInt(1) + " " + rs);
            user= new User(rs.getInt(1),rs.getString(2),
                    rs.getString(3),rs.getString(4));
            System.out.println("User name " + user.getLogin() + " LOGIN " + user.getPassword() + " MAIL " + user.getEmail());
            return "User name " + rs.getInt(1);
        } catch (Exception e){

            logger.log(Level.INFO,"ERROR ",e);
            e.printStackTrace();
        }
        return "agagaga";
        //return "User name " + user.getLogin();
    }

}
