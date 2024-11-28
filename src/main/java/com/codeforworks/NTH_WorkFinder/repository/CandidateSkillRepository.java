package com.codeforworks.NTH_WorkFinder.repository;

import com.codeforworks.NTH_WorkFinder.model.Candidate;
import com.codeforworks.NTH_WorkFinder.model.CandidateSkill;
import com.codeforworks.NTH_WorkFinder.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CandidateSkillRepository extends JpaRepository<CandidateSkill, Long> {

    List<CandidateSkill> findByCandidateId(Long candidateId);

    boolean existsByCandidateAndSkill(Candidate candidate, Skill skill);
}
