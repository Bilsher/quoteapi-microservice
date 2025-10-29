package com.example.quote_service.controller;

import com.example.quote_service.service.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/votes")
public class VoteInternalController {

    @Autowired
    private QuoteService quoteService;

    // Этот эндпоинт вызывается vote-service при лайке
    @PostMapping("/{quoteId}/like")
    public String likeQuote(@PathVariable Integer quoteId) {
        quoteService.adjustQuoteVotes(quoteId, 1, 0);
        return "Quote " + quoteId + " liked";
    }

    // Этот эндпоинт вызывается vote-service при дизлайке
    @PostMapping("/{quoteId}/dislike")
    public String dislikeQuote(@PathVariable Integer quoteId) {
        quoteService.adjustQuoteVotes(quoteId, 0, 1);
        return "Quote " + quoteId + " disliked";
    }

    // Эти — для отмены голоса
    @PostMapping("/{quoteId}/unlike")
    public String unlikeQuote(@PathVariable Integer quoteId) {
        quoteService.adjustQuoteVotes(quoteId, -1, 0);
        return "Quote " + quoteId + " unliked";
    }

    @PostMapping("/{quoteId}/undislike")
    public String undislikeQuote(@PathVariable Integer quoteId) {
        quoteService.adjustQuoteVotes(quoteId, 0, -1);
        return "Quote " + quoteId + " undisliked";
    }
}
