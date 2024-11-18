import React from 'react';
import PropTypes from 'prop-types';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min.js';
import { Navbar, Nav, NavDropdown, Container } from 'react-bootstrap';
import 'bootstrap-icons/font/bootstrap-icons.css';
import logo from '../../../assets/logos/logo.png';

const Header = ({ user, setUser }) => {
  const handleLogout = () => {
    setUser(null); // Xóa thông tin user trong trạng thái ứng dụng
    localStorage.removeItem('user'); // Xóa thông tin user khỏi localStorage
    window.location.href = '/'; // Điều hướng về trang chủ sau khi đăng xuất
  };

  return (
    <Navbar bg="white" expand="lg" sticky="top" className="border-bottom">
      <Container fluid>
        {/* Logo */}
        <Navbar.Brand href="/">
          <img
            src={logo}
            alt="CareerLink Logo"
            style={{ height: '40px' }}
          />
        </Navbar.Brand>

        {/* Middle Navigation Links */}
        <Nav className="mx-auto">
          <Nav.Link href="/jobs">
            <i className="bi bi-search"></i> Ngành nghề/ Địa điểm
          </Nav.Link>
          <Nav.Link href="/companies">
            <i className="bi bi-buildings"></i> Công ty
          </Nav.Link>
          <Nav.Link href="#guide">
            <i className="bi bi-book"></i> Cẩm nang việc làm
          </Nav.Link>
          <Nav.Link href="#cv-template">
            <i className="bi bi-file-earmark-text"></i> Mẫu CV Xin Việc
          </Nav.Link>
        </Nav>

        {/* Right Navigation Links */}
        <Nav className="d-flex align-items-center">
          <Nav.Link href="#chat" className="me-3">
            <i className="bi bi-chat-dots"></i> Trò chuyện
          </Nav.Link>
          {user ? (
            <NavDropdown
              title={
                <>
                  <i className="bi bi-person-circle"></i> {user.full_name}
                </>
              }
              id="user-dropdown"
              style={{ border: '1px solid #ccc', borderRadius: '10px' }}
              className="me-3"
            >
              <NavDropdown.Item href="/profileManagement">
                Quản lý hồ sơ
              </NavDropdown.Item>
              <NavDropdown.Item href="/account-management">
                Quản lý tài khoản
              </NavDropdown.Item>
              <NavDropdown.Divider />
              <NavDropdown.Item onClick={handleLogout}>
                Đăng xuất
              </NavDropdown.Item>
            </NavDropdown>
          ) : (
            <NavDropdown
              title={
                <>
                  <i className="bi bi-person-circle"></i> Đăng ký
                </>
              }
              id="account-dropdown"
              style={{ border: '1px solid #ccc', borderRadius: '10px' }}
              className="me-3"
            >
              <NavDropdown.Item href="/register">Đăng ký</NavDropdown.Item>
              <NavDropdown.Item href="/login">Đăng nhập</NavDropdown.Item>
            </NavDropdown>
          )}
          <Nav.Link href="/homeEmployer" className="text-dark">
            Nhà tuyển dụng
          </Nav.Link>
        </Nav>
      </Container>
    </Navbar>
  );
};

Header.propTypes = {
  user: PropTypes.shape({
    full_name: PropTypes.string,
  }),
  setUser: PropTypes.func.isRequired,
};

export default Header;
