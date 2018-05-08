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
                    "postgres" , "heslo");
            Statement st = con.createStatement();

            String str= String.format("SELECT r.rating, r.detaily FROM reviews r JOIN movies m2 ON r.movie_id = m2.id" +
                    " WHERE m2.title like '%s' and r.user_id = %d",movie,id);

            ResultSet resultSet = st.executeQuery(str);
            while (resultSet.next()){
                list.add(resultSet.getString(1));
                list.add(resultSet.getString(2));
            }
            str= String.format("SELECT a.name FROM actors a JOIN actedin a2 ON a.id = a2.actor_id JOIN movies m2 ON a2.movie_id = m2.id " +
                    " WHERE m2.title like '%s'",movie);
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

    public void rating(int i,String rating,int userid , String title){
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

            String insertPreparedStatement = "UPDATE reviews SET rating = ? ,detaily = ? WHERE movie_id=? and user_id=?";
            con.setAutoCommit(false);
            stmt = con.prepareStatement(insertPreparedStatement);
            stmt.setInt(3, resultSet.getInt(1));
            stmt.setInt(1, i);
            stmt.setString(2, rating);
            stmt.setInt(4,userid );
            stmt.execute();
            con.commit();

        } catch (SQLException e){
            ServerLogger.log(Level.INFO,"Nepodarilo sa registrovat uzivatela ");
            e.printStackTrace();
        } catch (Exception e){
            ServerLogger.log(Level.INFO,"Bean exception ",e);
            e.printStackTrace();
        }
    }



}
