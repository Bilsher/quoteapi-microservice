package com.example.quote_service.dto;

public class QuoteResponse {
    private Integer quoteID;
    private String quoteText;
    private int likes;
    private int dislikes;
    private Integer userID;

    public QuoteResponse() {}
    public QuoteResponse(Integer quoteID, String quoteText, int likes, int dislikes, Integer userID) {
        this.quoteID = quoteID;
        this.quoteText = quoteText;
        this.likes = likes;
        this.dislikes = dislikes;
        this.userID = userID;
    }

    // Getters/Setters
    public Integer getQuoteID() { return quoteID; }
    public void setQuoteID(Integer quoteID) { this.quoteID = quoteID; }
    public String getQuoteText() { return quoteText; }
    public void setQuoteText(String quoteText) { this.quoteText = quoteText; }
    public int getLikes() { return likes; }
    public void setLikes(int likes) { this.likes = likes; }
    public int getDislikes() { return dislikes; }
    public void setDislikes(int dislikes) { this.dislikes = dislikes; }
    public Integer getUserID() { return userID; }
    public void setUserID(Integer userID) { this.userID = userID; }
}