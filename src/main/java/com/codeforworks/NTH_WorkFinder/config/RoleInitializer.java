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

    @Override
    public void run(String... args) {
        // Kiểm tra và tạo vai trò nếu chưa tồn tại
        if (roleRepository.findByRoleName("ROLE_USER").isEmpty()) {
            Role basicUserRole = new Role();
            basicUserRole.setRoleName("ROLE_USER");
            roleRepository.save(basicUserRole);
        }

        if (roleRepository.findByRoleName("ROLE_MANAGER").isEmpty()) {
            Role hrManagerRole = new Role();
            hrManagerRole.setRoleName("ROLE_MANAGER");
            roleRepository.save(hrManagerRole);
        }

        if (roleRepository.findByRoleName("ROLE_ADMIN").isEmpty()) {
            Role adminRole = new Role();
            adminRole.setRoleName("ROLE_ADMIN");
            roleRepository.save(adminRole);
        }
    }
}
