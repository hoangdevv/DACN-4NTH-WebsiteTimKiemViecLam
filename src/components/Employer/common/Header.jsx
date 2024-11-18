import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min.js';
import { Navbar, Nav, NavDropdown, Container } from 'react-bootstrap';
import 'bootstrap-icons/font/bootstrap-icons.css';
import logo from '../../../assets/logos/logo.png';

const Header = () => {
  return (
      <Navbar expand="lg" sticky="top" className="border-bottom" style={{ backgroundColor: '#000D2C' }}>
          <Container fluid>
              {/* Logo */}
              <Navbar.Brand href="/">
                  <img
                      src={logo} 
                      alt="CareerLink Logo"
                      style={{ height: '40px'}}
                  />
              </Navbar.Brand>
              
              {/* Right Navigation Links */}
              <Nav className="d-flex align-items-center">
                  <Nav.Link href="#chat" className="me-3 text-white">
                      <i className="bi bi-chat-dots"></i> Trò chuyện
                  </Nav.Link>
                  <NavDropdown 
                      title={<span style={{ color: '#ffffff' }}><i className="bi bi-buildings"></i> Đăng ký</span>} 
                      id="account-dropdown"
                      style={{ border: '1px solid #ccc', borderRadius: '10px' }}
                      className="me-3"
                  >
                      <NavDropdown.Item href="/registerEmployer">Đăng ký</NavDropdown.Item>
                      <NavDropdown.Item href="/loginEmployer">Đăng nhập</NavDropdown.Item>
                  </NavDropdown>
                  <Nav.Link href="/" className="text-white">Người tìm việc</Nav.Link>
              </Nav>
          </Container>
      </Navbar>
  );
};

export default Header;
