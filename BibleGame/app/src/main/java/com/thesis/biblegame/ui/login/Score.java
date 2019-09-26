package com.thesis.biblegame.ui.login;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Score {


    public String name;
    public int score;
    public String picture;

    public Score() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Score(String name, int score, String picture) {
        this.name = name;
        this.score = score;
        this.picture = picture;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }




}
