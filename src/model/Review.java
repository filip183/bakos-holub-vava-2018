package model;

import java.io.Serializable;

public class Review implements Serializable{
    private String userName;
    private String detailr;
    private String value;

    public Review(String userName, String detailr, String value) {
        this.userName = userName;
        this.detailr = detailr;
        this.value = value;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDetailr() {
        return detailr;
    }

    public void setDetailr(String detailr) {
        this.detailr = detailr;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
