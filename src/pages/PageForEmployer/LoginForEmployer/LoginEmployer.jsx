import React, { useState } from 'react';
import PropTypes from 'prop-types';
import { useNavigate } from 'react-router-dom';
import { accounts } from '../../../components/data/accounts';
import { Form, Button, Card, Container, Row, Col } from 'react-bootstrap';
import Header from '../../../components/Employer/common/Header';
import Footer from '../../../components/User/common/Footer';

const LoginEmployer = ({ setUser }) => {
  const [formData, setFormData] = useState({ email: '', password: '' });
  const [showPassword, setShowPassword] = useState(false);
  const [error, setError] = useState('');
  const navigate = useNavigate();

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prevState) => ({ ...prevState, [name]: value }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
  
    const employer = accounts.find(
      (u) => u.email === formData.email && 
      u.password === formData.password && 
      u.account_type === 'employer'
    );
  
    if (employer) {
      localStorage.setItem('user', JSON.stringify(employer));
      setUser(employer);
      navigate('/employerDashboard');
    } else {
      setError('Email hoặc mật khẩu không chính xác hoặc không phải nhà tuyển dụng.');
    }
  };

  return (
    <div>
      <Header />
      <Container className="d-flex justify-content-center align-items-center mt-5 mb-5">
        <Card className="shadow p-5" style={{ maxWidth: '900px', width: '100%' }}>
          <Row>
            {/* Form Đăng nhập */}
            <Col md={6} className="border-end">
              <h4 className="mb-4 text-primary">Nhà tuyển dụng đăng nhập</h4>
              {error && <p className="error text-danger">{error}</p>}
              <Form onSubmit={handleSubmit}>
                <Form.Group className="mb-3" controlId="formEmail">
                  <Form.Label>Email</Form.Label>
                  <Form.Control
                    type="email"
                    name="email"
                    placeholder="Email"
                    value={formData.email}
                    onChange={handleChange}
                    required
                  />
                </Form.Group>
                <Form.Group className="mb-3" controlId="formPassword">
                  <Form.Label>Mật khẩu</Form.Label>
                  <Form.Control
                    type={showPassword ? 'text' : 'password'}
                    name="password"
                    placeholder="Mật khẩu"
                    value={formData.password}
                    onChange={handleChange}
                    required
                  />
                </Form.Group>
                <Form.Group className="mb-3" controlId="formShowPassword">
                  <Form.Check
                    type="checkbox"
                    label="Hiển thị mật khẩu"
                    onChange={() => setShowPassword(!showPassword)}
                  />
                </Form.Group>
                <Button variant="primary" type="submit" className="w-100 mb-3">
                  Đăng nhập
                </Button>
              </Form>
            </Col>

            {/* Phần bên phải: Đăng ký */}
            <Col md={6} className="text-center">
              <h5 className="mt-4">Bạn chưa có tài khoản?</h5>
              <Button variant="warning" href="/RegisterEmployer" className="my-4 px-4">
                Đăng ký
              </Button>
              <p>Tham gia ngay hôm nay để truy cập hàng ngàn ứng viên sáng giá!</p>
              <hr />
              <p><strong>Tại sao đăng ký?</strong></p>
              <ul className="list-unstyled">
                <li>Đăng công việc để nhận được những hồ sơ phù hợp</li>
                <li>Nhận thông báo hồ sơ qua email</li>
              </ul>
            </Col>
          </Row>
        </Card>
      </Container>
      <Footer />
    </div>
  );
};

LoginEmployer.propTypes = {
  setUser: PropTypes.func.isRequired,
};

export default LoginEmployer;