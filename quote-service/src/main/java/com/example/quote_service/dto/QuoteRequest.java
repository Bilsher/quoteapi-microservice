package com.example.quote_service.dto;

public class QuoteRequest {
    private String quoteText;

    public QuoteRequest() {}

    public QuoteRequest(String quoteText) {
        this.quoteText = quoteText;
    }

    // getters/setters
    public String getQuoteText() { return quoteText; }
    public void setQuoteText(String quoteText) { this.quoteText = quoteText; }
}