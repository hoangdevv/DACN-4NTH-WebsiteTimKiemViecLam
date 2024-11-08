import React, { useState } from 'react';
import '../../styles/Login.css';
import GoogleIcon from '../../assets/logos/Google.png';
import GithubIcon from '../../assets/logos/GitHub.png';

const Login = () => {
  const [formData, setFormData] = useState({
    email: '',
    password: ''
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData(prevState => ({
      ...prevState,
      [name]: valueô
    }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    // Xử lý logic đăng nhập ở đây
    console.log('Form submitted:', formData);
  };

  return (
    <div className="login-container">
      <div className="login-box">
        <h2>Đăng nhập</h2>
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

        <div className="social-login">
          <p>Hoặc đăng nhập với:</p>
          <div className="social-buttons">
            <button className="google-btn">
              <img src={GoogleIcon} alt="Google" />
              Google
            </button>
            <button className="github-btn">
              <img src={GithubIcon} alt="GitHub" />
              GitHub
            </button>
          </div>
        </div>

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

export default Login;
