package com.codeforworks.NTH_WorkFinder.security.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    // Gửi mã xác nhận đăng ký
    public void sendVerificationCode(String toEmail, String code) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");

        String htmlMsg = "<html><body>" +
                "<div style='font-family: Arial, sans-serif; color: #333; max-width: 600px; margin: auto;'>" +
                "<h2 style='color: #4CAF50;'>Xác nhận đăng ký tài khoản</h2>" +
                "<p>Chào bạn,</p>" +
                "<p>Cảm ơn bạn đã đăng ký tài khoản trên nền tảng của chúng tôi. Vui lòng nhập mã xác nhận sau đây để hoàn tất đăng ký:</p>" +
                "<h3 style='background: #f0f0f0; padding: 10px; display: inline-block; border-radius: 5px;'>" + code + "</h3>" +
                "<p>Nếu bạn không thực hiện yêu cầu này, vui lòng bỏ qua email này.</p>" +
                "</div></body></html>";

        helper.setTo(toEmail);
        helper.setSubject("Xác nhận đăng ký tài khoản");
        helper.setText(htmlMsg, true);
        mailSender.send(message);
    }

    // Gửi email thông báo đăng ký thành công
    public void sendRegistrationSuccessEmail(String toEmail, String accountEmail) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");

        String htmlMsg = "<html><body>" +
                "<div style='font-family: Arial, sans-serif; color: #333; max-width: 600px; margin: auto;'>" +
                "<h2 style='color: #4CAF50;'>Đăng ký thành công</h2>" +
                "<p>Chào bạn,</p>" +
                "<p>Bạn đã đăng ký thành công tài khoản với thông tin sau:</p>" +
                "<p><strong>Email đăng nhập:</strong> " + accountEmail + "</p>" +
                "<p>Vui lòng giữ thông tin đăng nhập an toàn.</p>" +
                "<p>Cảm ơn bạn đã chọn sử dụng nền tảng của chúng tôi!</p>" +
                "<div style='padding: 10px 0; font-size: 12px; color: #777;'>Đây là email tự động, vui lòng không trả lời.</div>" +
                "</div></body></html>";

        helper.setTo(toEmail);
        helper.setSubject("Chúc mừng! Đăng ký tài khoản thành công");
        helper.setText(htmlMsg, true);
        mailSender.send(message);
    }

//    Gui ma khi yeu cau quen mat khau
    public void sendPasswordResetEmail(String email, String resetCode) throws MessagingException {
        // Tạo đối tượng MimeMessage
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        // Đặt thông tin cho email
        helper.setTo(email);
        helper.setSubject("Yêu cầu thay đổi mật khẩu");
        helper.setText(buildEmailContent(resetCode), true); // true để cho phép email ở dạng HTML

        // Gửi email
        mailSender.send(message);
    }

    private String buildEmailContent(String resetCode) {
        return "<html>"
                + "<body style=\"font-family: Arial, sans-serif; background-color: #f4f4f9; padding: 20px;\">"
                + "<div style=\"background-color: #ffffff; border-radius: 10px; padding: 30px; box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1); max-width: 600px; margin: 0 auto;\">"
                + "<h2 style=\"color: #333333; text-align: center;\">Yêu cầu thay đổi mật khẩu</h2>"
                + "<p style=\"font-size: 16px; color: #555555;\">Chào bạn,</p>"
                + "<p style=\"font-size: 16px; color: #555555;\">Đây là mã xác minh để giúp bạn thay đổi mật khẩu:</p>"
                + "<h3 style=\"background-color: #f0f0f0; padding: 10px; text-align: center; font-size: 24px; border-radius: 5px; color: #333333;\">"
                + resetCode
                + "</h3>"
                + "<p style=\"font-size: 16px; color: #555555;\">Mã xác minh này sẽ hết hạn sau 5 phút.</p>"
                + "<p style=\"font-size: 16px; color: #555555;\">Nếu bạn không yêu cầu thay đổi mật khẩu, vui lòng bỏ qua email này.</p>"
                + "<p style=\"font-size: 14px; color: #888888; text-align: center;\">Cảm ơn bạn,</p>"
                + "<p style=\"font-size: 14px; color: #888888; text-align: center;\">Đội ngũ hỗ trợ</p>"
                + "</div>"
                + "</body>"
                + "</html>";
    }
}
