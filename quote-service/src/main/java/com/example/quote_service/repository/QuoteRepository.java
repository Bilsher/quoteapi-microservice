package com.example.quote_service.repository;

import com.example.quote_service.entity.QuoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface QuoteRepository extends JpaRepository<QuoteEntity, Integer> {
    List<QuoteEntity> findAllByOrderByLikesDesc();
}