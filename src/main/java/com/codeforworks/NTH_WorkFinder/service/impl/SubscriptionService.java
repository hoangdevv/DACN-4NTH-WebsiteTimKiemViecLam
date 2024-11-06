package com.codeforworks.NTH_WorkFinder.service.impl;

import com.codeforworks.NTH_WorkFinder.model.Employer;
import com.codeforworks.NTH_WorkFinder.model.Package;
import com.codeforworks.NTH_WorkFinder.model.Subscription;
import com.codeforworks.NTH_WorkFinder.repository.SubscriptionRepository;
import com.codeforworks.NTH_WorkFinder.service.ISubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class SubscriptionService implements ISubscriptionService {
    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Override
    public Subscription createSubscription(Employer employer, Package aPackage) {
        Subscription subscription = new Subscription();
        subscription.setEmployer(employer);
        subscription.setAPackage(aPackage);

        Date startDate = new Date(); // Ngày bắt đầu là ngày hiện tại
        subscription.setStartDate(startDate);

        // Tính toán ngày kết thúc dựa trên thời hạn của gói
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        calendar.add(Calendar.DAY_OF_YEAR, aPackage.getDuration()); // Cộng số ngày từ duration
        subscription.setEndDate(calendar.getTime());

        subscription.setIsActive(true); // Đặt mặc định là đang hoạt động

        return subscriptionRepository.save(subscription);
    }
}
