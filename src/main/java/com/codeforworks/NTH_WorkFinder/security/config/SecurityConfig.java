package com.codeforworks.NTH_WorkFinder.security.config;

import com.codeforworks.NTH_WorkFinder.security.jwt.JwtAuthEntryPoint;
import com.codeforworks.NTH_WorkFinder.security.jwt.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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
import java.util.Map;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Autowired
    private JwtAuthEntryPoint jwtAuthEntryPoint;

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    //  QUYỀN ALL
    private static final List<String> PERMIT_ALL_URLS = List.of(
            "/api/auth/register/**",  "/api/auth/create/admin","/api/auth/**","/api/auth/login/**","/api/auth/oauth2/me"
    );

    //  QUYEN USER
    private static final Map<String, List<String>> USER_PERMISSIONS = Map.of(
        "GET", List.of("/api/skills","/api/candidates/{id}","/api/skills/{id}"),
        "POST", List.of("/api/candidates","/api/candidate-skills/{candidateId}"),
        "PUT", List.of("/api/candidates/{id}")
    );

//  QUYỀN EMPLOYER
    private static final Map<String, List<String>> EMPLOYER_PERMISSIONS = Map.of(
            "GET", List.of("/api/skills","/api/candidates","/api/skills/{id}")

    );

    //  QUYỀN ADMIN
    private static final Map<String, List<String>> ADMIN_PERMISSIONS = Map.of(
            "GET", List.of("/api/candidates","/api/skills","/api/candidates/{id}","/api/skills/{id}"),
            "POST", List.of("/api/candidates","/api/skills"),
            "PUT", List.of("/api/candidates/{id}","/api/skills/{id}"),
            "DELETE", List.of("/api/candidates/{id}","/api/skills/{id}")
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
                    // User permissions
                    USER_PERMISSIONS.forEach((method, urls) -> urls.forEach(url ->
                            auth.requestMatchers(HttpMethod.valueOf(method), url).hasRole("USER")));
                    // Employer permissions
                    EMPLOYER_PERMISSIONS.forEach((method, urls) -> urls.forEach(url ->
                            auth.requestMatchers(HttpMethod.valueOf(method), url).hasRole("EMPLOYER")));
                    // Admin permissions
                    ADMIN_PERMISSIONS.forEach((method, urls) -> urls.forEach(url ->
                            auth.requestMatchers(HttpMethod.valueOf(method), url).hasRole("ADMIN")));

                    auth.anyRequest().authenticated(); // Các yêu cầu khác cần xác thực
                })

                // Cấu hình OAuth2
                .oauth2Login(oauth2 -> oauth2
                        .loginPage("/oauth2/authorization")         // Trang login OAuth2
                        .defaultSuccessUrl("http://localhost:5173/oauth/success", true) // URL sau khi thành công
                        .failureUrl("http://localhost:5173/oauth/failure")                  // URL sau khi thất bại
                )


                // Thêm JwtAuthenticationFilter trước UsernamePasswordAuthenticationFilter
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
