import {React, useState} from 'react';
import PropTypes from 'prop-types';
import { useNavigate } from 'react-router-dom';
import { accounts } from '../../../components/data/accounts';
import { Layout, Card, Row, Col, Typography, Form, Input, Button, Divider } from 'antd';
import Header from '../../../components/Employer/common/Header';
import Footer from '../../../components/User/common/Footer';

const { Title, Text } = Typography;

const LoginEmployer = ({ setUser }) => {
  const [form] = Form.useForm(); // Sử dụng Form API của antd
  const [error, setError] = useState('');
  const navigate = useNavigate();

  const handleSubmit = (values) => {
    const { email, password } = values;

    const employer = accounts.find(
      (u) =>
        u.email === email &&
        u.password === password &&
        u.account_type === "employer"
    );

    if (employer) {
      localStorage.setItem('user', JSON.stringify(employer));
      setUser(employer);
      navigate('/dashboard');
    } else {
      setError('Email hoặc mật khẩu không chính xác hoặc không phải nhà tuyển dụng.');
    }
  };

  return (
    <Layout>
      <Header />
      <Layout.Content style={{ padding: '50px 20px' }}>
        <Card
          style={{
            maxWidth: '900px',
            margin: 'auto',
            boxShadow: '0 4px 12px rgba(0, 0, 0, 0.1)',
          }}
        >
          <Row gutter={32}>
            {/* Form Đăng nhập */}
            <Col md={12} xs={24} style={{ borderRight: '1px solid #f0f0f0' }}>
              <Title
                level={4}
                style={{
                  color: 'rgb(204, 10, 157)',
                  marginBottom: '20px',
                  textAlign: 'center',
                }}
              >
                Nhà tuyển dụng đăng nhập
              </Title>
              {error && <Text type="danger">{error}</Text>}
              <Form
                form={form}
                layout="vertical"
                onFinish={handleSubmit}
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
                  <Input type="email" placeholder="Email" />
                </Form.Item>
                <Form.Item
                  label="Mật khẩu"
                  name="password"
                  rules={[
                    { required: true, message: 'Vui lòng nhập mật khẩu!' },
                  ]}
                >
                  <Input.Password placeholder="Mật khẩu" />
                </Form.Item>
                <Form.Item>
                  <Button
                    type="primary"
                    htmlType="submit"
                    block
                    style={{
                      background: 'linear-gradient(to right, rgb(2, 0, 36), rgb(204, 10, 157))',
                      border: 'none',
                    }}
                  >
                    Đăng nhập
                  </Button>
                </Form.Item>
              </Form>
            </Col>

            {/* Phần bên phải: Đăng ký */}
            <Col md={12} xs={24} style={{ textAlign: 'center' }}>
              <Title level={5}>Bạn chưa có tài khoản?</Title>
              <Button
                type="default"
                href="/RegisterEmployer"
                size="large"
                style={{
                  margin: '20px 0',
                  backgroundColor: '#FFC107',
                  color: '#000',
                  border: 'none',
                  textDecoration: 'none',
                }}
              >
                Đăng ký
              </Button>
              <Text style={{ display: 'block', marginTop: '10px' }}>
                Tham gia ngay hôm nay để truy cập hàng ngàn ứng viên sáng giá!
              </Text>
              <Divider />
              <Title level={5}>Tại sao đăng ký?</Title>
              <div style={{ textAlign: 'center' }}>
                <p>Đăng công việc để nhận được những hồ sơ phù hợp</p>
                <p>Nhận thông báo hồ sơ qua email</p>
              </div>
            </Col>
          </Row>
        </Card>
      </Layout.Content>
      <Footer />
    </Layout>
  );
};

LoginEmployer.propTypes = {
  setUser: PropTypes.func.isRequired,
};

export default LoginEmployer;
