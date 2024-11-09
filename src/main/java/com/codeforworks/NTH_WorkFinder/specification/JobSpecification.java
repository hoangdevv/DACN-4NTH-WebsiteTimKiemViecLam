package com.codeforworks.NTH_WorkFinder.specification;

import com.codeforworks.NTH_WorkFinder.dto.job.JobSearchCriteria;
import com.codeforworks.NTH_WorkFinder.model.Job;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class JobSpecification implements Specification<Job> {

    private JobSearchCriteria criteria;

    public JobSpecification(JobSearchCriteria criteria) {
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate(Root<Job> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        List<Predicate> predicates = new ArrayList<>();

        // Điều kiện tìm kiếm theo location
        if (criteria.getLocation() != null && !criteria.getLocation().isEmpty()) {
            predicates.add(builder.equal(root.get("location"), criteria.getLocation()));
        }

        // Điều kiện tìm kiếm theo ExperienceLevel
        if (criteria.getExperienceLevel() != null) {
            predicates.add(builder.equal(root.get("experienceLevel"), criteria.getExperienceLevel()));
        }

        // Điều kiện tìm kiếm theo JobLevel
        if (criteria.getJobLevel() != null) {
            predicates.add(builder.equal(root.get("jobLevel"), criteria.getJobLevel()));
        }

        // Điều kiện tìm kiếm theo EducationLevel
        if (criteria.getEducationLevel() != null) {
            predicates.add(builder.equal(root.get("educationLevel"), criteria.getEducationLevel()));
        }

        // Điều kiện tìm kiếm theo JobType
        if (criteria.getJobType() != null) {
            predicates.add(builder.equal(root.get("jobType"), criteria.getJobType()));
        }

        return builder.and(predicates.toArray(new Predicate[0]));
    }
}
