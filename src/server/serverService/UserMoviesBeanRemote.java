package server.serverService;


import javafx.collections.ObservableList;
import model.MovieList;

import javax.ejb.Remote;


@Remote
public interface UserMoviesBeanRemote {

    MovieList getMovies(int id);

}