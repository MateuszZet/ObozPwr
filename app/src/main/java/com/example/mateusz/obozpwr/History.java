package com.example.mateusz.obozpwr;

/**
 * Created by Mateusz on 31.08.2018.
 */

public class History {
    public String date;
    public String user;
    public String desc;
    public Long points;


    public History(String date, String user, String desc, Long points) {
        this.date = date;
        this.user = user;
        this.desc = desc;
        this.points = points;
    }

    public String getDate() {
        return date;
    }

    public String getDesc() {
        return desc;
    }

    public Long getPoints() {
        return points;
    }

    public String getUser() {
        return user;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setPoints(Long points) {
        this.points = points;
    }


    public void setUser(String user) {
        this.user = user;
    }
}
