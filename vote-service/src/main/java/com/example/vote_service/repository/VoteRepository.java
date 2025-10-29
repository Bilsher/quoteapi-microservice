package com.example.vote_service.repository;

import com.example.vote_service.entity.VoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface VoteRepository extends JpaRepository<VoteEntity, Integer> {
    Optional<VoteEntity> findByUserIdAndQuoteId(Integer userId, Integer quoteId);
    boolean existsByUserIdAndQuoteId(Integer userId, Integer quoteId);
    void deleteByUserIdAndQuoteId(Integer userId, Integer quoteId);
}