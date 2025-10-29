package com.example.vote_service.dto;

public class VoteRequest {
    private String voteType; // "LIKE" or "DISLIKE"

    public VoteRequest() {}
    public VoteRequest(String voteType) { this.voteType = voteType; }

    public String getVoteType() { return voteType; }
    public void setVoteType(String voteType) { this.voteType = voteType; }
}