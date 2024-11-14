import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min.js';
import { Navbar, Nav, NavDropdown, Container } from 'react-bootstrap';
import 'bootstrap-icons/font/bootstrap-icons.css';
import logo from '../../../assets/logos/logo.png';

const Header = () => {
  return (
      <Navbar bg="white" expand="lg" sticky="top" className="border-bottom">
          <Container fluid>
              {/* Logo */}
              <Navbar.Brand href="/">
                  <img
                      src={logo} 
                      alt="CareerLink Logo"
                      style={{ height: '40px'}}
                  />
              </Navbar.Brand>
              
              {/* Middle Navigation Links */}
              <Nav className="mx-auto">
                  <Nav.Link href="/jobs"><i className="bi bi-search"></i> Ngành nghề/ Địa điểm</Nav.Link>
                  <Nav.Link href="/companies"><i className="bi bi-buildings"></i> Công ty</Nav.Link>
                  <Nav.Link href="#guide"><i className="bi bi-book"></i> Cẩm nang việc làm</Nav.Link>
                  <Nav.Link href="#cv-template"><i className="bi bi-file-earmark-text"></i> Mẫu CV Xin Việc</Nav.Link>
              </Nav>
              
              {/* Right Navigation Links */}
              <Nav className="d-flex align-items-center">
                  <Nav.Link href="#chat" className="me-3">
                      <i className="bi bi-chat-dots"></i> Trò chuyện
                  </Nav.Link>
                  <NavDropdown 
                      title={<><i className="bi bi-person-circle"></i> Đăng ký</>} 
                      id="account-dropdown"
                      style={{ border: '1px solid #ccc', borderRadius: '10px' }}
                      className="me-3"
                  >
                      <NavDropdown.Item href="/register">Đăng ký</NavDropdown.Item>
                      <NavDropdown.Item href="/login">Đăng nhập</NavDropdown.Item>
                  </NavDropdown>
                  <Nav.Link href="#employer" className="text-dark">Nhà tuyển dụng</Nav.Link>
              </Nav>
          </Container>
      </Navbar>
  );
};

export default Header;
