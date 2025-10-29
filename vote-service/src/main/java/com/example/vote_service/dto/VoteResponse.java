package com.example.vote_service.dto;

public class VoteResponse {
    private String message;
    private Integer likes;
    private Integer dislikes;

    public VoteResponse() {}
    public VoteResponse(String message, Integer likes, Integer dislikes) {
        this.message = message;
        this.likes = likes;
        this.dislikes = dislikes;
    }

    // Getters/Setters
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public Integer getLikes() { return likes; }
    public void setLikes(Integer likes) { this.likes = likes; }
    public Integer getDislikes() { return dislikes; }
    public void setDislikes(Integer dislikes) { this.dislikes = dislikes; }
}