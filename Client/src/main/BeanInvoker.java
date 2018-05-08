package main;


import model.Movie;
import model.MovieList;
import model.User;
import server.serverService.LoginBeanRemote;
import server.serverService.MovieDetailBean;
import server.serverService.MovieDetailBeanRemote;
import server.serverService.UserMoviesBeanRemote;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.*;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BeanInvoker {
    public static final Logger logger=Logger.getLogger("log");
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
            fh = new FileHandler("logFile.txt",true);
            logger.addHandler(fh);

        } catch (Exception e1) {
            e1.printStackTrace();
        }



    }

    public User invokeLogin(User user) throws NamingException {
        // Let's lookup the remote stateless calculator
        final LoginBeanRemote statelessLoginBean = lookupRemoteLoginBean();


        User user1 = statelessLoginBean.authentification(user);

        if (user1!=null && user.getPassword().equals(user1.getPassword())){
            userGlobal=user1;
            return user1;
        } else {
            userGlobal=null;
            return null;
        }
    }

    public int invokeRegistration(User user) throws NamingException {
        // Let's lookup the remote stateless calculator
        final LoginBeanRemote statelessLoginBean = lookupRemoteLoginBean();


        return statelessLoginBean.Registracia(user);
    }

    private static LoginBeanRemote lookupRemoteLoginBean() throws NamingException {
        final Hashtable jndiProperties = new Hashtable();
        Properties prop= new Properties();

        final Context context = new InitialContext();

        return (LoginBeanRemote) context.lookup("ejb:/Vava20182_war_exploded//LoginBean!server.serverService.LoginBeanRemote");
    }

    public MovieList invokeUserMovies(int id) throws NamingException {
        final UserMoviesBeanRemote statelessUserMoviesBean = lookupRemoteUserMovieBean();

        return statelessUserMoviesBean.getMovies(id);
    }

    private static UserMoviesBeanRemote lookupRemoteUserMovieBean() throws NamingException{
        Properties prop= new Properties();

        final Context context = new InitialContext();

        return (UserMoviesBeanRemote) context.lookup("ejb:/Vava20182_war_exploded//UserMoviesBean!server.serverService.UserMoviesBeanRemote");

    }

    private static MovieDetailBeanRemote lookupMovieDetailBean() {
        try {
            final Context context = new InitialContext();
            return (MovieDetailBeanRemote) context.lookup("ejb:/Vava20182_war_exploded//MovieDetailBean!server.serverService.MovieDetailBeanRemote");
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static LinkedList<String> invokeMovieDetails(String movie,int id) {
        final MovieDetailBeanRemote statelessUserMoviesBean = lookupMovieDetailBean();

        return statelessUserMoviesBean.movieDetail(movie,id);
    }

    public static void invokeMovieDetailsRating(int i,String rating,int user , String title) {
        final MovieDetailBeanRemote statelessUserMoviesBean = lookupMovieDetailBean();
        statelessUserMoviesBean.rating(i, rating, user, title);
    }
}
