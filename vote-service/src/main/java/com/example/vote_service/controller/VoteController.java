package com.example.vote_service.controller;

import com.example.vote_service.dto.VoteResponse;
import com.example.vote_service.service.VoteService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/votes")
public class VoteController {

    @Autowired
    private VoteService voteService;

    @PostMapping("/{quoteId}/like")
    public VoteResponse likeQuote(@PathVariable Integer quoteId, HttpServletRequest request) {
        Integer userId = (Integer) request.getAttribute("userId");
        return voteService.likeQuote(quoteId, userId);
    }

    @PostMapping("/{quoteId}/dislike")
    public VoteResponse dislikeQuote(@PathVariable Integer quoteId, HttpServletRequest request) {
        Integer userId = (Integer) request.getAttribute("userId");
        return voteService.dislikeQuote(quoteId, userId);
    }
}