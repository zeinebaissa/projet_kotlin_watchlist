package com.watchlist.backend.models;

public class Watchlist {
    private String idItem; // Unique identifier for the watchlist item
    private String title;  // Title of the movie or series
    private String type;   // Type: "film" or "series"
    private String status; // Status: "to watch" or "watched"
    private String comment;   // Personal note or comment


    public Watchlist() {
        // Default constructor required by Firestore
    }

    public Watchlist(String idItem, String userId, String title, String type, String status, String comment) {
        this.idItem = idItem;
        this.title = title;
        this.type = type;
        this.status = status;
        this.comment = comment;
    }

    // Getters and Setters
    public String getIdItem() {
        return idItem;
    }

    public void setIdItem(String idItem) {
        this.idItem = idItem;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}