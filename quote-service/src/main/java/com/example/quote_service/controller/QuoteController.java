package com.example.quote_service.controller;

import com.example.quote_service.dto.QuoteRequest;
import com.example.quote_service.dto.QuoteResponse;
import com.example.quote_service.service.QuoteService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/quotes")
public class QuoteController {

    @Autowired
    private QuoteService quoteService;

    @PostMapping("/add")
    public QuoteResponse addQuote(@RequestBody QuoteRequest quoteRequest, HttpServletRequest request) {
        Integer userId = (Integer) request.getAttribute("userId");
        System.out.println("Controller - UserID from request: " + userId); // Debug
        return quoteService.addQuote(quoteRequest, userId);
    }

    @GetMapping("/all")
    public List<QuoteResponse> getAllQuotes() {
        return quoteService.getAllQuotes();
    }

    @GetMapping("/top")
    public List<QuoteResponse> getTopQuotes() {
        return quoteService.getTopQuotes();
    }

    @DeleteMapping("/{quoteId}/delete")
    public String deleteQuote(@PathVariable Integer quoteId, HttpServletRequest request) {
        Integer userId = (Integer) request.getAttribute("userId");
        return quoteService.deleteQuote(quoteId, userId);
    }

}