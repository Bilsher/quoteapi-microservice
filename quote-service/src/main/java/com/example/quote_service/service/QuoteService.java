package com.example.quote_service.service;

import com.example.quote_service.dto.QuoteRequest;
import com.example.quote_service.dto.QuoteResponse;
import java.util.List;

public interface QuoteService {
    QuoteResponse addQuote(QuoteRequest quoteRequest, Integer userId);
    List<QuoteResponse> getAllQuotes();
    List<QuoteResponse> getTopQuotes();
    String deleteQuote(Integer quoteId, Integer userId);

    //test
    void adjustQuoteVotes(Integer quoteId, int likesDelta, int dislikesDelta);

}