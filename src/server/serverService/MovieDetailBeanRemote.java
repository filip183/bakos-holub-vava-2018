package server.serverService;

import model.Movie;

import javax.ejb.Remote;
import java.util.LinkedList;

@Remote
public interface MovieDetailBeanRemote {

    LinkedList<String> movieDetail(String movie, int id);
    void rating(int i,String rating,int user , String title, int checkNumber);
    String actorLink(String string);

}
