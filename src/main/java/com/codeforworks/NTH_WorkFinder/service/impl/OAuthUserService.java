package com.codeforworks.NTH_WorkFinder.service.impl;

import com.codeforworks.NTH_WorkFinder.model.Account;
import com.codeforworks.NTH_WorkFinder.model.Role;
import com.codeforworks.NTH_WorkFinder.model.User;
import com.codeforworks.NTH_WorkFinder.repository.AccountRepository;
import com.codeforworks.NTH_WorkFinder.repository.RoleRepository;
import com.codeforworks.NTH_WorkFinder.repository.UserRepository;
import com.codeforworks.NTH_WorkFinder.security.service.EmailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class OAuthUserService extends DefaultOAuth2UserService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private EmailService emailService;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        String email = oAuth2User.getAttribute("email");
        String name = oAuth2User.getAttribute("name");

        if (email == null || name == null) {
            throw new OAuth2AuthenticationException("Email hoặc tên không tồn tại từ OAuth2 provider.");
        }

        Account account = accountRepository.findByEmail(email).orElseGet(() -> {
            Account newAccount = new Account();
            newAccount.setEmail(email);
            newAccount.setAccountType(Account.AccountType.USER);

            Role userRole = roleRepository.findByRoleName("ROLE_USER")
                    .orElseThrow(() -> new RuntimeException("Role ROLE_USER không tồn tại"));
            newAccount.getRoles().add(userRole);

            try {
                if (!newAccount.getLoggedIn()) {
                    // Gửi email thông báo đăng ký thành công
                    emailService.sendRegistrationSuccessEmail(newAccount.getEmail(), newAccount.getEmail());

                    // Cập nhật trạng thái đăng nhập để chỉ gửi email này một lần
                    newAccount.setLoggedIn(true);
                    accountRepository.save(newAccount);
                }

                User user = new User();
                user.setFullName(name);
                user.setAccount(newAccount);

                userRepository.save(user);
            } catch (Exception e) {
                throw new RuntimeException("Lỗi khi lưu thông tin tài khoản hoặc người dùng: " + e.getMessage());
            }

            return newAccount;
        });

        if (account.getUser() != null && !account.getUser().getFullName().equals(name)) {
            account.getUser().setFullName(name);
            userRepository.save(account.getUser());
        }


        return oAuth2User;
    }
}
