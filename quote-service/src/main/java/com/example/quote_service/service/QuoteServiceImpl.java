package com.example.quote_service.service;

import com.example.quote_service.dto.QuoteRequest;
import com.example.quote_service.dto.QuoteResponse;
import com.example.quote_service.entity.QuoteEntity;
import com.example.quote_service.repository.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuoteServiceImpl implements QuoteService {

    @Autowired
    private QuoteRepository quoteRepository;

    @Override
    public QuoteResponse addQuote(QuoteRequest quoteRequest, Integer userId) {
        System.out.println("Adding quote for user: " + userId); // Debug

        QuoteEntity quote = new QuoteEntity(quoteRequest.getQuoteText(), userId);
        QuoteEntity savedQuote = quoteRepository.save(quote);

        System.out.println("Saved quote: " + savedQuote.getQuoteID() + " for user: " + savedQuote.getUserID()); // Debug

        return new QuoteResponse(savedQuote.getQuoteID(), savedQuote.getQuoteText(),
                savedQuote.getLikes(), savedQuote.getDislikes(), savedQuote.getUserID());
    }

    @Override
    public List<QuoteResponse> getAllQuotes() {
        return quoteRepository.findAll().stream()
                .map(quote -> new QuoteResponse(quote.getQuoteID(), quote.getQuoteText(),
                        quote.getLikes(), quote.getDislikes(), quote.getUserID()))
                .collect(Collectors.toList());
    }

    @Override
    public List<QuoteResponse> getTopQuotes() {
        return quoteRepository.findAllByOrderByLikesDesc().stream()
                .map(quote -> new QuoteResponse(quote.getQuoteID(), quote.getQuoteText(),
                        quote.getLikes(), quote.getDislikes(), quote.getUserID()))
                .collect(Collectors.toList());
    }

    @Override
    public String deleteQuote(Integer quoteId, Integer userId) {
        QuoteEntity quote = quoteRepository.findById(quoteId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Quote not found"));

        if (!quote.getUserID().equals(userId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You can only delete your own quotes");
        }

        quoteRepository.delete(quote);
        return "Quote deleted successfully";
    }

    //test
    @Override
    public void adjustQuoteVotes(Integer quoteId, int likesDelta, int dislikesDelta) {
        QuoteEntity quote = quoteRepository.findById(quoteId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Quote not found"));

        int newLikes = Math.max(0, quote.getLikes() + likesDelta);
        int newDislikes = Math.max(0, quote.getDislikes() + dislikesDelta);

        quote.setLikes(newLikes);
        quote.setDislikes(newDislikes);

        quoteRepository.save(quote);
    }


}