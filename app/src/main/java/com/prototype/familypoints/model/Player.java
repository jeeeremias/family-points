package com.prototype.familypoints.model;

import java.io.Serializable;

public class Player implements Serializable {

    private String name;
    private int points;
    private int drawable;

    public Player(int id, String name, int points, int drawable) {
        this.name = name;
        this.points = points;
        this.drawable = drawable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getPicturePath() {
        return drawable;
    }

    public void setPicturePath(int picturePath) {
        this.drawable = picturePath;
    }
}
