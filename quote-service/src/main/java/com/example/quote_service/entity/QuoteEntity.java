package com.example.quote_service.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "UsersQuote")
public class QuoteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quoteID")
    private Integer quoteID;

    @Column(name = "likes")
    private int likes = 0;

    @Column(name = "dislikes")
    private int dislikes = 0;

    @Column(name = "quoteText")
    private String quoteText;

    @Column(name = "userID")
    private Integer userID;

    // constructors, getters, setters
    public QuoteEntity() {}

    public QuoteEntity(String quoteText, Integer userID) {
        this.quoteText = quoteText;
        this.userID = userID;
    }

    // getters/setters...
    public Integer getQuoteID() { return quoteID; }
    public void setQuoteID(Integer quoteID) { this.quoteID = quoteID; }
    public int getLikes() { return likes; }
    public void setLikes(int likes) { this.likes = likes; }
    public int getDislikes() { return dislikes; }
    public void setDislikes(int dislikes) { this.dislikes = dislikes; }
    public String getQuoteText() { return quoteText; }
    public void setQuoteText(String quoteText) { this.quoteText = quoteText; }
    public Integer getUserID() { return userID; }
    public void setUserID(Integer userID) { this.userID = userID; }
}