package com.codeforworks.NTH_WorkFinder.config;

import com.codeforworks.NTH_WorkFinder.model.Role;
import com.codeforworks.NTH_WorkFinder.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class RoleInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;

    public RoleInitializer(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    //USER: Có các vai trò bổ sung như PREMIUM_USER, BASIC_USER.
    //EMPLOYER: Có các vai trò bổ sung như HR_MANAGER, SUPERVISOR.
    //ADMIN: Có thể có vai trò như SUPER_ADMIN, MODERATOR.
    @Override
    public void run(String... args) {
        // Kiểm tra và tạo vai trò nếu chưa tồn tại
        if (roleRepository.findByRoleName("BASIC_USER").isEmpty()) {
            Role basicUserRole = new Role();
            basicUserRole.setRoleName("BASIC_USER");
            roleRepository.save(basicUserRole);
        }

        if (roleRepository.findByRoleName("HR_MANAGER").isEmpty()) {
            Role hrManagerRole = new Role();
            hrManagerRole.setRoleName("HR_MANAGER");
            roleRepository.save(hrManagerRole);
        }

        if (roleRepository.findByRoleName("ADMIN").isEmpty()) {
            Role adminRole = new Role();
            adminRole.setRoleName("ADMIN");
            roleRepository.save(adminRole);
        }
    }
}
