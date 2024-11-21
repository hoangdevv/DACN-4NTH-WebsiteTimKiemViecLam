import React from 'react';
import { Button, Typography } from 'antd';
import { Link } from 'react-router-dom';

const { Title, Text } = Typography;

const EmailVerification = () => {
  return (
    <div
      style={{
        height: '100vh',
        display: 'flex',
        justifyContent: 'center',
        alignItems: 'center',
        background: 'linear-gradient(to right, #020024, #cc0a9d)', // Nền giống Login
        padding: '1rem',
      }}
    >
      <div
        style={{
          background: '#fff',
          padding: '2rem',
          borderRadius: '8px',
          boxShadow: '0 4px 6px rgba(0, 0, 0, 0.1)',
          maxWidth: '400px',
          width: '100%',
          textAlign: 'center',
        }}
      >
        <Title level={3} style={{ marginBottom: '1rem', color: '#333' }}>
          Xác thực email
        </Title>
        <Text>
          Chúng tôi đã gửi một email xác thực đến địa chỉ của bạn. Vui lòng kiểm tra hộp thư
          và nhấp vào liên kết xác thực để hoàn tất đăng ký.
        </Text>
        <div style={{ marginTop: '1rem' }}>
          <Text>
            Không nhận được email? <Link to="/resend-email">Gửi lại</Link>.
          </Text>
        </div>
        <Button type="primary" style={{ marginTop: '1rem' }}>
          Quay lại trang chủ
        </Button>
      </div>
    </div>
  );
};

export default EmailVerification;
