package com.codeforworks.NTH_WorkFinder.service.impl;

import com.codeforworks.NTH_WorkFinder.dto.subscription.SubscriptionRequestDTO;
import com.codeforworks.NTH_WorkFinder.dto.subscription.SubscriptionResponseDTO;
import com.codeforworks.NTH_WorkFinder.mapper.SubscriptionMapper;
import com.codeforworks.NTH_WorkFinder.model.Employer;
import com.codeforworks.NTH_WorkFinder.model.Package;
import com.codeforworks.NTH_WorkFinder.model.Subscription;
import com.codeforworks.NTH_WorkFinder.repository.EmployerRepository;
import com.codeforworks.NTH_WorkFinder.repository.PackageRepository;
import com.codeforworks.NTH_WorkFinder.repository.SubscriptionRepository;
import com.codeforworks.NTH_WorkFinder.service.ISubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubscriptionService implements ISubscriptionService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private EmployerRepository employerRepository;

    @Autowired
    private PackageRepository packageRepository;

    @Override
    public SubscriptionResponseDTO createSubscription(SubscriptionRequestDTO subscriptionRequestDTO) {
        Subscription subscription = new Subscription();

        // Lấy Employer và Package từ request
        Employer employer = employerRepository.findById(subscriptionRequestDTO.getEmployerId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy Employer"));
        Package aPackage = packageRepository.findById(subscriptionRequestDTO.getPackageId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy Package"));
        // Kiểm tra nếu Employer đã có đăng ký với gói này
        if (subscriptionRepository.existsByEmployerAndAPackage(employer, aPackage)) {
            throw new RuntimeException("Employer đã có đăng ký cho gói này.");
        }

        // Thiết lập thông tin cho Subscription
        subscription.setEmployer(employer);
        subscription.setAPackage(aPackage);

        Date startDate = new Date();
        subscription.setStartDate(startDate);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        calendar.add(Calendar.DAY_OF_YEAR, aPackage.getDuration());
        subscription.setEndDate(calendar.getTime());

        subscription.setIsActive(true);

        Subscription savedSubscription = subscriptionRepository.save(subscription);
        return SubscriptionMapper.INSTANCE.toSubscriptionResponseDTO(savedSubscription);
    }

    @Override
    public SubscriptionResponseDTO getSubscriptionById(Long id) {
        Subscription subscription = subscriptionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy Subscription"));
        return SubscriptionMapper.INSTANCE.toSubscriptionResponseDTO(subscription);
    }

    @Override
    public List<SubscriptionResponseDTO> getAllSubscriptions() {
        List<Subscription> subscriptions = subscriptionRepository.findAll();
        return subscriptions.stream()
                .map(SubscriptionMapper.INSTANCE::toSubscriptionResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public SubscriptionResponseDTO renewSubscription(Long id, Integer extraDays) {
        Subscription subscription = subscriptionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy Subscription"));
        if (extraDays <= 0) {
            throw new RuntimeException("Số ngày gia hạn không hợp lệ.");
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(subscription.getEndDate());
        calendar.add(Calendar.DAY_OF_YEAR, extraDays);
        subscription.setEndDate(calendar.getTime());

        Subscription updatedSubscription = subscriptionRepository.save(subscription);
        return SubscriptionMapper.INSTANCE.toSubscriptionResponseDTO(updatedSubscription);
    }

    @Override
    public void cancelSubscription(Long id) {
        Subscription subscription = subscriptionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy Subscription"));

        subscription.setIsActive(false);
        subscriptionRepository.save(subscription);
    }
}
