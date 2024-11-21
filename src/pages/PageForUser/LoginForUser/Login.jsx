import React from 'react';
import PropTypes from 'prop-types';
import { useNavigate } from 'react-router-dom';
import { Form, Input, Button, Divider, Typography, Space, message } from 'antd';
import { GoogleOutlined, GithubOutlined } from '@ant-design/icons';
import { accounts } from '../../../components/data/accounts';
import '../../../styles/Login.css'; 

const { Link, Text } = Typography;

const Login = ({ setUser }) => {
  const [form] = Form.useForm();
  const navigate = useNavigate();

  const handleSubmit = (values) => {
    const user = accounts.find(
      (u) =>
        u.email === values.email &&
        u.password === values.password &&
        u.account_type === 'user'
    );

    if (user) {
      const userData = {
        id_account: user.id_account,
        full_name: user.full_name,
        email: user.email,
        account_type: user.account_type,
      };

      localStorage.setItem('user', JSON.stringify(userData));

      setUser(user);
      navigate('/');
    } else {
      message.error('Email hoặc mật khẩu không chính xác hoặc không phải người dùng.');
    }
  };

  return (
    <div className="login-container">
      <div style={{ maxWidth: '400px', width: '100%' }}>
        <h2 className="login-title">Đăng nhập</h2>
        <Form
          form={form}
          layout="vertical"
          onFinish={handleSubmit}
          className="login-form"
          requiredMark={false}
        >
          <Form.Item
            label="Email"
            name="email"
            rules={[
              { required: true, message: 'Vui lòng nhập email!' },
              { type: 'email', message: 'Email không hợp lệ!' },
            ]}
          >
            <Input placeholder="Nhập email của bạn" />
          </Form.Item>

          <Form.Item
            label="Mật khẩu"
            name="password"
            rules={[{ required: true, message: 'Vui lòng nhập mật khẩu!' }]}
          >
            <Input.Password placeholder="Nhập mật khẩu của bạn" />
          </Form.Item>

          <Button type="primary" htmlType="submit" block style={{ marginBottom: '1rem' }}>
            Đăng nhập
          </Button>

          <Divider>Hoặc</Divider>

          <Button
            icon={<GoogleOutlined />}
            block
            style={{ marginBottom: '0.5rem' }}
            onClick={() => message.info('Đăng nhập với Google hiện chưa khả dụng.')}
          >
            Đăng nhập với Google
          </Button>
          <Button
            icon={<GithubOutlined />}
            block
            onClick={() => message.info('Đăng nhập với GitHub hiện chưa khả dụng.')}
          >
            Đăng nhập với GitHub
          </Button>

          <Divider />

          <div className="login-footer">
            <Space direction="vertical" size="small">
              <Link href="/forgot-password">Quên mật khẩu?</Link>
              <Text>
                Chưa có tài khoản?{' '}
                <Link href="/register">Đăng ký ngay</Link>
              </Text>
            </Space>
          </div>
        </Form>
      </div>
    </div>
  );
};

Login.propTypes = {
  setUser: PropTypes.func.isRequired,
};

export default Login;