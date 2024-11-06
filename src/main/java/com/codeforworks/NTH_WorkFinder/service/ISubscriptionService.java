package com.codeforworks.NTH_WorkFinder.service;

import com.codeforworks.NTH_WorkFinder.model.Employer;
import com.codeforworks.NTH_WorkFinder.model.Package;
import com.codeforworks.NTH_WorkFinder.model.Subscription;

public interface ISubscriptionService {
    Subscription createSubscription(Employer employer, Package aPackage);
}
