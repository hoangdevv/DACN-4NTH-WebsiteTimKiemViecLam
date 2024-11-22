package com.codeforworks.NTH_WorkFinder.security.config;

import com.codeforworks.NTH_WorkFinder.security.jwt.JwtAuthEntryPoint;
import com.codeforworks.NTH_WorkFinder.security.jwt.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.List;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Autowired
    private JwtAuthEntryPoint jwtAuthEntryPoint;

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;
//    QUYỀN ALL USER
    private static final List<String> PERMIT_ALL_URLS = List.of(
            "/api/auth/**",
            "/oauth2/**",
            "/public/**"
    );

//    QUYỀN EMPLOYER
    private static final List<String> EMPLOYER_ONLY_URLS = List.of(
            "/api/employer/**",
            "/api/job/**",
            "/api/interview/**"
    );

    /**
     * Cấu hình AuthenticationManager để quản lý xác thực người dùng
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)  // Sử dụng cú pháp mới để vô hiệu hóa CSRF

                .exceptionHandling(exceptionHandling -> exceptionHandling
                        .authenticationEntryPoint(jwtAuthEntryPoint)) // Xử lý lỗi xác thực với JwtAuthEntryPoint

                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Sử dụng Stateless session

                // Cấu hình phân quyền
                .authorizeHttpRequests(auth -> {
                    PERMIT_ALL_URLS.forEach(url -> auth.requestMatchers(url).permitAll());
                    EMPLOYER_ONLY_URLS.forEach(url -> auth.requestMatchers(url).hasRole("EMPLOYER"));
                    auth.anyRequest().authenticated(); // Các yêu cầu khác cần xác thực
                })

                // Cấu hình OAuth2
                .oauth2Login(oauth2 -> oauth2
                        .loginPage("/oauth2/authorization")         // Trang login OAuth2
                        .defaultSuccessUrl("http://localhost:3000/oauth/success", true) // URL sau khi thành công
                        .failureUrl("http://localhost:3000/oauth/failure")                  // URL sau khi thất bại
                )


                // Thêm JwtAuthenticationFilter trước UsernamePasswordAuthenticationFilter
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
