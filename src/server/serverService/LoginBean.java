package server.serverService;

import model.User;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.sql.*;
import java.util.logging.Level;


@Stateless
@Remote(LoginBeanRemote.class)
public class LoginBean implements LoginBeanRemote {

    public LoginBean() {
    }


    public User getUser(){

        User user=null;
        Connection con = null;
        String driver = "org.postgresql.Driver";
        try {

            Class.forName(driver).newInstance();
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/vava",
                    "postgres" , "heslo");

            Statement st = con.createStatement();

            String str= "SELECT * FROM public.users";
            System.out.println("QUERy "  + str);
            ResultSet rs = st.executeQuery(str);


            rs.next();
            ServerLogger.log(Level.INFO,"--------------------- " + rs.getInt(1));
            //System.out.println("asdasdad " + rs.getInt(1) + " " + rs);
            user= new User(rs.getInt(1),rs.getString(2),
                    rs.getString(3),rs.getString(4));
            System.out.println("User name " + user.getLogin() + " LOGIN " + user.getPassword() + " MAIL " + user.getEmail());
            return user;
        } catch (SQLException e){
            ServerLogger.log(Level.INFO,"Zle zadane meno alebo heslo ");
            e.printStackTrace();
        }
          catch (Exception ex){
            ServerLogger.log(Level.INFO,"Chyba ",ex);
             ex.printStackTrace();
        }
        return null;

    }

    public User authentification(User user){

        Connection con = null;
        String driver = "org.postgresql.Driver";
        try {

            Class.forName(driver).newInstance();
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/vava",
                    "postgres" , "heslo");

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
        } catch (SQLException e){
            ServerLogger.log(Level.INFO,"Zle zadane meno alebo heslo ");
            e.printStackTrace();
        }
        catch (Exception ex){
            ServerLogger.log(Level.INFO,"Chyba ",ex);
            ex.printStackTrace();
        }
        return null;
    }

    public int Registracia(User user) {
        Connection con = null;
        String driver = "org.postgresql.Driver";
        PreparedStatement stmt = null;
        try {
            Class.forName(driver).newInstance();
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/vava",
                    "postgres" , "heslo");

            Statement st = con.createStatement();

            String insertPreparedStatement = "insert into users(id, login,password,email) values(default,?, ?, ?)";
                con.setAutoCommit(false);
                stmt = con.prepareStatement(insertPreparedStatement);
                stmt.setString(1, user.getLogin());
                stmt.setString(2, user.getPassword());
                stmt.setString(3, user.getEmail());
                stmt.execute();
                con.commit();

            System.out.println("User name " + user.getLogin() + " LOGIN " + user.getPassword() + " MAIL " + user.getEmail());

            return 1;
        } catch (SQLException e){
            ServerLogger.log(Level.INFO,"Nepodarilo sa registrovat uzivatela ");
            e.printStackTrace();
            return 0;
        } catch (Exception e){
            ServerLogger.log(Level.INFO,"Bean exception ",e);
            e.printStackTrace();
        return 0;
        }
    }

    public String vrat(String string) {
        return null;
    }
}
