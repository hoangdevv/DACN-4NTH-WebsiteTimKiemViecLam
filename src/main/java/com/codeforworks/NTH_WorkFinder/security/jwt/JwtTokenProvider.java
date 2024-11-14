package com.codeforworks.NTH_WorkFinder.security.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtTokenProvider {

    private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);

    @Value("${auth.token.jwtSecret}")
    private String jwtSecret;

    @Value("${auth.token.jwtExpirationMs}")
    private int jwtExpirationMs;

    private SecretKey secretKey;

    // Khởi tạo secretKey sau khi jwtSecret được nạp
    @PostConstruct
    public void init() {
        this.secretKey = Keys.hmacShaKeyFor(jwtSecret.getBytes());
    }

    /**
     * Tạo JWT token dựa trên đối tượng Authentication sau khi người dùng đăng nhập thành công.
     *
     * @param authentication Đối tượng Authentication chứa thông tin người dùng.
     * @return JWT token dưới dạng chuỗi.
     */
    public String generateToken(Authentication authentication) {
        // Lấy UserDetails từ Authentication
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationMs);

        return Jwts.builder()
                .setSubject(username)                           // Đặt username vào Subject của token
                .setIssuedAt(now)                               // Ngày tạo token
                .setExpiration(expiryDate)                      // Ngày hết hạn
                .signWith(secretKey, SignatureAlgorithm.HS512)  // Mã hóa token với bí mật và thuật toán HS512
                .compact();
    }

    /**
     * Xác minh tính hợp lệ của JWT token.
     *
     * @param token JWT token cần xác minh.
     * @return true nếu token hợp lệ, false nếu không hợp lệ.
     */
    public boolean validateToken(String token) {
        try {
            // Xác minh token, nếu không hợp lệ sẽ ném ra exception
            Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
            return true;
        }catch(MalformedJwtException e){
            logger.error("Invalid jwt token : {} ", e.getMessage());
        }catch (ExpiredJwtException e){
            logger.error("Expired token : {} ", e.getMessage());
        }catch (UnsupportedJwtException e){
            logger.error("This token is not supported : {} ", e.getMessage());
        }catch (IllegalArgumentException e){
            logger.error("No  claims found : {} ", e.getMessage());
        }
        return false;
    }

    /**
     * Lấy tên người dùng (username) từ JWT token.
     *
     * @param token JWT token.
     * @return Username đã được mã hóa trong token.
     */
    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }
}
