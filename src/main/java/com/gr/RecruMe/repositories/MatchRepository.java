package com.gr.RecruMe.repositories;

import com.gr.RecruMe.models.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<Match,Integer> {
    @Query(value = " select TOP 3 id \n" +
            "  FROM MATCH\n" +
            "  ORDER BY date_finalized DESC ", nativeQuery = true)
    List<Integer> getRecentlyFinalized();
}