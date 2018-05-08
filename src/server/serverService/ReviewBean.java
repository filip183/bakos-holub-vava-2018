package server.serverService;


import model.Movie;
import model.MovieList;
import model.Review;
import model.ReviewList;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.logging.Level;

@Stateless
@Remote(ReviewBeanRemote.class)
public class ReviewBean implements ReviewBeanRemote {

    @Override
    public ReviewList searchReviews(String movieName) {

        ArrayList<Review> reviews = new ArrayList<>();
        Connection con = null;
        String driver = "org.postgresql.Driver";

        try {
            Class.forName(driver).newInstance();
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/vava",
                    "postgres" , "heslo");

            Statement st = con.createStatement();

            String str= String.format("SELECT m.id FROM movies m " +
                    " WHERE m.title like '%s' ;",movieName);
            System.out.println("QUERy "  + str);
            ResultSet rs1 = st.executeQuery(str);
            rs1.next();

            str= String.format("SELECT u.login, r.rating, r.detailr FROM reviews r " +
                    " JOIN users u ON r.user_id = u.id " +
                    " WHERE r.movie_id = %s ;",rs1.getString(1));
            System.out.println("QUERy "  + str);
            ResultSet rs = st.executeQuery(str);



            while(rs.next()){
                System.out.println(rs.getString(1));
                reviews.add(new Review(rs.getString(1),rs.getString(3),
                        rs.getString(2)));
            }

            ReviewList reviewList = new ReviewList(reviews);
            return reviewList;
        } catch (Exception e){

            ServerLogger.log(Level.INFO,"ERROR ",e);
            e.printStackTrace();
        }

        return null;

    }

}