package com.codeforworks.NTH_WorkFinder.repository;

import com.codeforworks.NTH_WorkFinder.model.Employer;
import com.codeforworks.NTH_WorkFinder.model.Package;
import com.codeforworks.NTH_WorkFinder.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    boolean existsByEmployerAndPackageEntity(Employer employer, Package packageEntity);
}
