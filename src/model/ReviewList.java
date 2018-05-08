package model;

import java.io.Serializable;
import java.util.ArrayList;

public class ReviewList implements Serializable {

    ArrayList<Review> reviews;

    public ReviewList(ArrayList<Review> reviews) {
        this.reviews = reviews;
    }

    public ArrayList<Review> getReviews() {
        return reviews;
    }

    public void setReviews(ArrayList<Review> reviews) {
        this.reviews = reviews;
    }
}
