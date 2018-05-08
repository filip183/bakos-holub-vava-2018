package server.serverService;

import model.Movie;
import model.MovieList;
import model.ReviewList;

import javax.ejb.Remote;
import java.util.LinkedList;

@Remote
public interface ReviewBeanRemote {

    public ReviewList searchReviews(String filter);
}
