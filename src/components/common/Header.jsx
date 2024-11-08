import React from 'react';
import { Link } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min.js';
import logo from '../../assets/images/logo.png';

const Header = () => {
  return (
    <header className="fixed top-0 left-0 right-0 z-50">
      <div className="container">
        <div className="d-flex justify-content-between align-items-center py-3">
          {/* Logo */}
          <div>
            <Link to="/" className="text-decoration-none text-dark">
              <img src={logo} alt="JobFinder" height="64" />Website Tìm Việc
            </Link>
          </div>

          {/* Navigation */}
          <nav className="d-none d-md-flex">
            <Link to="/" className="text-dark mx-3 text-decoration-none">Trang chủ</Link>
            <Link to="/jobs" className="text-dark mx-3 text-decoration-none">Việc làm</Link>
            <Link to="/companies" className="text-dark mx-3 text-decoration-none">Công ty</Link>
            <Link to="/blog" className="text-dark mx-3 text-decoration-none">Blog</Link>
          </nav>

          {/* Auth Buttons */}
          <div className="d-flex align-items-center">
            <Link to="/login" className="btn btn-link text-dark">Đăng nhập</Link>
            <Link to="/register" className="btn btn-primary ms-3">Đăng ký</Link>
          </div>
        </div>
      </div>
    </header>
  );
};

export default Header;
