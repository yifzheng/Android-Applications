package com.example.firebaseapp;

public class Note {

    private String id, title, description, userUID;

    public Note(){}
    public Note(String title, String description)
    {
        this.title = title;
        this.description = description;
    }

    public String getUserUID() {
        return userUID;
    }

    public void setUserUID(String userUID) {
        this.userUID = userUID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
