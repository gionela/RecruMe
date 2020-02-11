package com.gr.RecruMe.repositories;

import com.gr.RecruMe.models.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchRepository extends JpaRepository<Match,Integer> {
}