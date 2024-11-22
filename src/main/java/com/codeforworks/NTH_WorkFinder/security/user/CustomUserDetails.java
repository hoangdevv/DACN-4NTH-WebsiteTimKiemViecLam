package com.codeforworks.NTH_WorkFinder.security.user;

import com.codeforworks.NTH_WorkFinder.model.Account;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class CustomUserDetails implements UserDetails {

    private String username;
    private String password;
    private boolean active;
    private List<GrantedAuthority> authorities;

    public CustomUserDetails(Account account) {
        this.account = account;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Lấy danh sách quyền (Role) của người dùng và ánh xạ thành SimpleGrantedAuthority
        return account.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleName()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return account.getPassword(); // Lấy mật khẩu đã mã hóa từ Account
    }

    @Override
    public String getUsername() {
        return account.getEmail(); // Lấy email làm định danh người dùng
    }

    @Override
    public boolean isAccountNonExpired() { //Tài khoản không hết hạn
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {  //Tài khoản không bị khóa
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {  //Thông tin xác thực không hết hạn
        return true;
    }

    @Override
    public boolean isEnabled() {
        return account.getStatus(); // Trả về trạng thái của tài khoản
    }
}
