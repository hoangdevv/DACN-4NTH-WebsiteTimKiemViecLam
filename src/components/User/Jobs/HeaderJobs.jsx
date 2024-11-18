import React from 'react';
import PropTypes from 'prop-types';
import '@fortawesome/fontawesome-free/css/all.min.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min.js';
import { Navbar, Nav, NavDropdown, Container, Form } from 'react-bootstrap';
import 'bootstrap-icons/font/bootstrap-icons.css';
import logo from '../../../assets/logos/logo.png';

const HeaderJobs = ({ user, setUser }) => {
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

        {/* Search */}
        <Form className="d-flex w-50 me-3">
          <Form.Control
            type="search"
            placeholder="Nhập tên vị trí, công ty, từ khóa"
            className="me-2"
            aria-label="Search"
            style={{ height: '40px' }}
          />
          <Form.Control
            type="search"
            placeholder="Nhập tỉnh, thành phố"
            className="me-2"
            aria-label="Location"
            style={{ height: '40px' }}
          />
          <button
            className="btn btn-primary"
            type="submit"
            style={{ height: '40px', minWidth: '120px' }}
          >
            Tìm kiếm ngay
          </button>
        </Form>

        {/* Right Navigation Links */}
        <Nav className="d-flex align-items-center">
          <NavDropdown
            title={
              <>
                <i className="bi bi-list"></i>
              </>
            }
            id="menu-dropdown"
            style={{ border: '1px solid #ccc', borderRadius: '10px' }}
            className="me-3"
          >
            <NavDropdown.Item href="/jobs">
              <i className="bi bi-search"></i> Ngành nghề/ Địa điểm
            </NavDropdown.Item>
            <NavDropdown.Item href="/companies">
              <i className="bi bi-buildings"></i> Công ty
            </NavDropdown.Item>
            <NavDropdown.Item href="#guide">
              <i className="bi bi-book"></i> Cẩm nang việc làm
            </NavDropdown.Item>
            <NavDropdown.Item href="#cv-template">
              <i className="bi bi-file-earmark-text"></i> Mẫu CV Xin Việc
            </NavDropdown.Item>
          </NavDropdown>
          <Nav.Link href="#chat" className="me-3">
            <i className="bi bi-chat-dots"></i> Trò chuyện
          </Nav.Link>

          {/* Kiểm tra trạng thái user */}
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
              <NavDropdown.Item href="/profile-management">
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

HeaderJobs.propTypes = {
  user: PropTypes.shape({
    full_name: PropTypes.string,
  }),
  setUser: PropTypes.func.isRequired,
};

export default HeaderJobs;
