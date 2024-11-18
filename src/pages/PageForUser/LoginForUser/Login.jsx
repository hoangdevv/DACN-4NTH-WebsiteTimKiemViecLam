import React, { useState } from 'react';
import PropTypes from 'prop-types';
import { useNavigate } from 'react-router-dom';
import '../../../styles/Login.css';
import { accounts } from '../../../components/data/accounts';

const Login = ({ setUser }) => {
  const [formData, setFormData] = useState({ email: '', password: '' });
  const [error, setError] = useState('');
  const navigate = useNavigate();

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prevState) => ({ ...prevState, [name]: value }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    // Tìm người dùng trong mảng accounts
    const user = accounts.find(
      (u) =>
        u.email === formData.email &&
        u.password === formData.password &&
        u.account_type === 'user'
    );

    if (user) {
      // Lưu id_account vào localStorage
      const userData = {
        id_account: user.id_account, // Chỉ lưu id_account
        full_name: user.full_name,
        email: user.email,
        account_type: user.account_type,
      };

      // Lưu vào localStorage
      localStorage.setItem('user', JSON.stringify(userData));

      setUser(user); // Cập nhật trạng thái người dùng
      navigate('/'); // Chuyển đến trang chủ
    } else {
      setError('Email hoặc mật khẩu không chính xác hoặc không phải người dùng.');
    }
  };

  return (
    <div className="login-container">
      <div className="login-box">
        <h2>Đăng nhập</h2>
        {error && <p className="error text-danger">{error}</p>}
        <form onSubmit={handleSubmit}>
          <div className="form-group">
            <label htmlFor="email">Email:</label>
            <input
              type="email"
              id="email"
              name="email"
              value={formData.email}
              onChange={handleChange}
              required
            />
          </div>
          <div className="form-group">
            <label htmlFor="password">Mật khẩu:</label>
            <input
              type="password"
              id="password"
              name="password"
              value={formData.password}
              onChange={handleChange}
              required
            />
          </div>
          <button type="submit" className="login-button">
            Đăng nhập
          </button>
        </form>
        <div className="login-footer">
          <a href="/forgot-password" className="forgot-password">
            Quên mật khẩu?
          </a>
          <p className="register-link">
            Chưa có tài khoản? <a href="/register">Đăng ký ngay</a>
          </p>
        </div>
      </div>
    </div>
  );
};

Login.propTypes = {
  setUser: PropTypes.func.isRequired,
};

export default Login;
