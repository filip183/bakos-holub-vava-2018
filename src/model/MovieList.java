package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.Serializable;
import java.util.ArrayList;

public class MovieList implements Serializable {

    ArrayList<Movie> list;

    public MovieList(ArrayList<Movie> list) {
        this.list = list;
    }

    public ArrayList<Movie> getList() {
        return list;
    }

    public void setList(ArrayList<Movie> list) {
        this.list = list;
    }
}
