package server.serverService;


import model.Movie;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.sql.*;
import java.util.LinkedList;
import java.util.logging.Level;

@Stateless
@Remote(MovieDetailBeanRemote.class)
public class MovieDetailBean implements MovieDetailBeanRemote {

    public LinkedList<String> movieDetail(String movie, int id) {
        LinkedList<String> list = new LinkedList<>();

        Connection con = null;
        String driver = "org.postgresql.Driver";
        PreparedStatement stmt = null;
        try {
            Class.forName(driver).newInstance();
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/vava",
                    "postgres", "heslo");
            Statement st = con.createStatement();

            String str = String.format("SELECT r.rating, r.detailr FROM reviews r JOIN movies m2 ON r.movie_id = m2.id" +
                    " WHERE m2.title like '%s' and r.user_id = %d", movie, id);

            ResultSet resultSet = st.executeQuery(str);
            if (!resultSet.next()){
                list.add(""+0);
                list.add("-----");
            } else {
                list.add(resultSet.getString(1));
                list.add(resultSet.getString(2));
            }
            str= String.format("SELECT a.name FROM actors a " +
                    "JOIN actedin a2 ON a.id = a2.actor_id " +
                    "JOIN movies m2 ON a2.movie_id = m2.id " +
                    "WHERE m2.title like '%s'",movie);
            resultSet = st.executeQuery(str);
            while (resultSet.next()){
                list.add(resultSet.getString(1));
            }
            return list;
        } catch (SQLException e){
            ServerLogger.log(Level.INFO,"Nepodarilo sa registrovat uzivatela ");
            e.printStackTrace();
            return null;
        } catch (Exception e){
            ServerLogger.log(Level.INFO,"Bean exception ",e);
            e.printStackTrace();
            return null;
        }

    }

    public void rating(int i,String rating,int userid , String title, int checkNumber){
        Connection con = null;
        String driver = "org.postgresql.Driver";
        PreparedStatement stmt = null;
        try {
            Class.forName(driver).newInstance();
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/vava",
                    "postgres" , "heslo");

            Statement st = con.createStatement();
            String str= String.format("Select id FROM movies WHERE title LIKE '%s'",title);
            System.out.println("QUERy "  + str);
            ResultSet resultSet = st.executeQuery(str);
            resultSet.next();
            String insertPreparedStatement;
            if(checkNumber==0){
                insertPreparedStatement = "UPDATE reviews SET rating = ? ,detailr = ? WHERE movie_id=? and user_id=?";
                con.setAutoCommit(false);
                stmt = con.prepareStatement(insertPreparedStatement);
                stmt.setInt(3, resultSet.getInt(1));
                stmt.setInt(1, i);
                stmt.setString(2, rating);
                stmt.setInt(4,userid );
                stmt.execute();
                con.commit();
            } else {
                insertPreparedStatement = "INSERT INTO reviews VALUES (DEFAULT, ? ,?, ?, ?);";
                con.setAutoCommit(false);
                stmt = con.prepareStatement(insertPreparedStatement);
                stmt.setInt(1, resultSet.getInt(1));
                stmt.setInt(4, i);
                stmt.setString(3, rating);
                stmt.setInt(2,userid );
                stmt.execute();
                con.commit();
            }
        } catch (SQLException e){
            ServerLogger.log(Level.INFO,"Nepodarilo sa registrovat uzivatela ");
            e.printStackTrace();
            try {
                con.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        } catch (Exception e){
            ServerLogger.log(Level.INFO,"Bean exception ",e);
            e.printStackTrace();
        }
    }

    @Override
    public String actorLink(String string) {
        Connection con = null;
        String driver = "org.postgresql.Driver";
        PreparedStatement stmt = null;
        try {
            Class.forName(driver).newInstance();
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/vava",
                    "postgres" , "heslo");

            Statement st = con.createStatement();
            String str= String.format("Select link FROM actors WHERE name LIKE '%s'",string);
            System.out.println("QUERy "  + str);
            ResultSet resultSet = st.executeQuery(str);
            resultSet.next();
            return resultSet.getString(1);


        } catch (SQLException e){
            ServerLogger.log(Level.INFO,"SQL Error ");
            e.printStackTrace();
        } catch (Exception e){
            ServerLogger.log(Level.INFO,"Bean exception ",e);
            e.printStackTrace();
        }
        return null;
    }
}
