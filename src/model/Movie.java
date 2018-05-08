package model;

import java.io.Serializable;

public class Movie implements Serializable {
    private String title;
    private String genre;
    private String year;
    private String director;
    private int rating;
    private String link;
    private String dirLink;

    public Movie(String title, String genre, String year, String director, int rating, String link, String dirLink) {
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.director = director;
        this.rating = rating;
        this.link=link;
        this.dirLink=dirLink;
    }

    public String getDirLink() {
        return dirLink;
    }

    public void setDirLink(String dirLink) {
        this.dirLink = dirLink;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }
}
