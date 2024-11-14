package com.codeforworks.NTH_WorkFinder.repository;

import com.codeforworks.NTH_WorkFinder.model.Industry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IndustryRepository extends JpaRepository<Industry, Long> {
    Optional<Industry> findByName(String name);
}
