package server.serverService;

import model.Movie;
import model.MovieList;

import javax.ejb.Remote;
import java.util.LinkedList;

@Remote
public interface MovieSearchBeanRemote {

    public MovieList searchMovies(String filter);
}
