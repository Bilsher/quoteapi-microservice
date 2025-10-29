package com.example.vote_service.service;

import com.example.vote_service.dto.VoteResponse;
import com.example.vote_service.entity.VoteEntity;
import com.example.vote_service.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class VoteServiceImpl implements VoteService {

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private RestTemplate restTemplate;

    private void updateQuoteLikes(Integer quoteId, int likesChange, int dislikesChange) {
        try {
            String quoteServiceUrl = "http://quote-service/api/v1/votes/";

            if (likesChange == 1 && dislikesChange == 0)
                quoteServiceUrl += quoteId + "/like";
            else if (likesChange == -1 && dislikesChange == 0)
                quoteServiceUrl += quoteId + "/unlike";
            else if (likesChange == 0 && dislikesChange == 1)
                quoteServiceUrl += quoteId + "/dislike";
            else if (likesChange == 0 && dislikesChange == -1)
                quoteServiceUrl += quoteId + "/undislike";
            else if (likesChange == 1 && dislikesChange == -1)
                quoteServiceUrl += quoteId + "/like"; // смена дизлайка на лайк
            else if (likesChange == -1 && dislikesChange == 1)
                quoteServiceUrl += quoteId + "/dislike"; // смена лайка на дизлайк

            System.out.println("Calling quote-service: " + quoteServiceUrl);

            restTemplate.postForObject(quoteServiceUrl, null, String.class);
        } catch (Exception e) {
            System.out.println("Error updating quote votes: " + e.getMessage());
        }
    }


    @Override
    public VoteResponse likeQuote(Integer quoteId, Integer userId) {
        Optional<VoteEntity> existingVote = voteRepository.findByUserIdAndQuoteId(userId, quoteId);

        if (existingVote.isPresent()) {
            VoteEntity vote = existingVote.get();
            if ("LIKE".equals(vote.getVoteType())) {
                // Удаляем лайк
                voteRepository.delete(vote);
                updateQuoteLikes(quoteId, -1, 0);
                return new VoteResponse("Like removed", -1, 0);
            } else {
                // Меняем дизлайк на лайк
                vote.setVoteType("LIKE");
                voteRepository.save(vote);
                updateQuoteLikes(quoteId, 1, -1);
                return new VoteResponse("Changed dislike to like", 1, -1);
            }
        } else {
            // Новый лайк
            VoteEntity newVote = new VoteEntity(userId, quoteId, "LIKE");
            voteRepository.save(newVote);
            updateQuoteLikes(quoteId, 1, 0);
            return new VoteResponse("Quote liked successfully", 1, 0);
        }
    }

    @Override
    public VoteResponse dislikeQuote(Integer quoteId, Integer userId) {
        Optional<VoteEntity> existingVote = voteRepository.findByUserIdAndQuoteId(userId, quoteId);

        if (existingVote.isPresent()) {
            VoteEntity vote = existingVote.get();
            if ("DISLIKE".equals(vote.getVoteType())) {
                // Удаляем дизлайк
                voteRepository.delete(vote);
                updateQuoteLikes(quoteId, 0, -1);
                return new VoteResponse("Dislike removed", 0, -1);
            } else {
                // Меняем лайк на дизлайк
                vote.setVoteType("DISLIKE");
                voteRepository.save(vote);
                updateQuoteLikes(quoteId, -1, 1);
                return new VoteResponse("Changed like to dislike", -1, 1);
            }
        } else {
            // Новый дизлайк
            VoteEntity newVote = new VoteEntity(userId, quoteId, "DISLIKE");
            voteRepository.save(newVote);
            updateQuoteLikes(quoteId, 0, 1);
            return new VoteResponse("Quote disliked successfully", 0, 1);
        }
    }
}