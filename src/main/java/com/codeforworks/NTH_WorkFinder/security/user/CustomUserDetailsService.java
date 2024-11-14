package com.codeforworks.NTH_WorkFinder.security.user;

import com.codeforworks.NTH_WorkFinder.model.Account;
import com.codeforworks.NTH_WorkFinder.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Tìm người dùng trong cơ sở dữ liệu bằng email
        Account account = accountRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        // Trả về đối tượng CustomUserDetails với thông tin từ Account
        return new CustomUserDetails(account);
    }
}
