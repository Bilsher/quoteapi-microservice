package com.example.vote_service.service;

import com.example.vote_service.dto.VoteResponse;

public interface VoteService {
    VoteResponse likeQuote(Integer quoteId, Integer userId);
    VoteResponse dislikeQuote(Integer quoteId, Integer userId);
}