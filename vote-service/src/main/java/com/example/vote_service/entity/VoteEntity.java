package com.example.vote_service.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "user_votes")
public class VoteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vote_id")
    private Integer voteId;

    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Column(name = "quote_id", nullable = false)
    private Integer quoteId;

    @Column(name = "vote_type", nullable = false, length = 10)
    private String voteType; // "LIKE" or "DISLIKE"

    public VoteEntity() {}

    public VoteEntity(Integer userId, Integer quoteId, String voteType) {
        this.userId = userId;
        this.quoteId = quoteId;
        this.voteType = voteType;
    }

    // Getters/Setters
    public Integer getVoteId() { return voteId; }
    public void setVoteId(Integer voteId) { this.voteId = voteId; }
    public Integer getUserId() { return userId; }
    public void setUserId(Integer userId) { this.userId = userId; }
    public Integer getQuoteId() { return quoteId; }
    public void setQuoteId(Integer quoteId) { this.quoteId = quoteId; }
    public String getVoteType() { return voteType; }
    public void setVoteType(String voteType) { this.voteType = voteType; }
}