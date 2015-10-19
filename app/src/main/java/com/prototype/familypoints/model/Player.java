package com.prototype.familypoints.model;

import android.support.v4.graphics.drawable.RoundedBitmapDrawable;

public class Player {

    private String name;
    private int points;
    private RoundedBitmapDrawable picture;

    public Player(String name, int points, RoundedBitmapDrawable picture) {
        this.name = name;
        this.points = points;
        this.picture = picture;
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

    public RoundedBitmapDrawable getPicture() {
        return picture;
    }

    public void setPicture(RoundedBitmapDrawable picture) {
        this.picture = picture;
    }
}
