import React from 'react';
import PropTypes from 'prop-types';
import { Layout, Menu, Dropdown, Avatar, Button } from 'antd';
import {
  UserOutlined,
  LogoutOutlined,
  SettingOutlined,
  SearchOutlined,
  BankOutlined,
  FileTextOutlined,
  MessageOutlined,
} from '@ant-design/icons';
import logo from '../../../assets/logos/logo.png';

const { Header } = Layout;

const HeaderComponent = ({ user, setUser }) => {
  const handleLogout = () => {
    setUser(null);
    localStorage.removeItem('user');
    window.location.href = '/'; // Điều hướng về trang chủ sau khi đăng xuất
  };

  const userMenu = (
    <Menu>
      <Menu.Item key="profile" icon={<SettingOutlined />}>
        <a href="/profileManagement" style={{ textDecoration: 'none', color: '#000' }}>
          Quản lý hồ sơ
        </a>
      </Menu.Item>
      <Menu.Item key="account" icon={<SettingOutlined />}>
        <a href="/account-management" style={{ textDecoration: 'none', color: '#000' }}>
          Quản lý tài khoản
        </a>
      </Menu.Item>
      <Menu.Divider />
      <Menu.Item key="logout" icon={<LogoutOutlined />} onClick={handleLogout}>
        <span style={{ textDecoration: 'none', color: '#000' }}>Đăng xuất</span>
      </Menu.Item>
    </Menu>
  );

  const guestMenu = (
    <Menu>
      <Menu.Item key="register">
        <a href="/register" style={{ textDecoration: 'none', color: '#000' }}>
          Đăng ký
        </a>
      </Menu.Item>
      <Menu.Item key="login">
        <a href="/login" style={{ textDecoration: 'none', color: '#000' }}>
          Đăng nhập
        </a>
      </Menu.Item>
    </Menu>
  );

  return (
    <Header style={{ background: '#fff', borderBottom: '1px solid #ddd', padding: '0 20px' }}>
      <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center' }}>
        {/* Logo */}
        <div>
          <a href="/" style={{ textDecoration: 'none' }}>
            <img src={logo} alt="Logo" style={{ height: '40px' }} />
          </a>
        </div>

        {/* Middle Navigation Links */}
        <Menu
          mode="horizontal"
          style={{
            borderBottom: 'none',
            flex: 1,
            justifyContent: 'center',
            display: 'flex',
            textAlign: 'center',
          }}
        >
          <Menu.Item key="jobs" icon={<SearchOutlined />}>
            <a href="/jobs" style={{ textDecoration: 'none', color: '#000' }}>
              Ngành nghề/ Địa điểm
            </a>
          </Menu.Item>
          <Menu.Item key="companies" icon={<BankOutlined />}>
            <a href="/companies" style={{ textDecoration: 'none', color: '#000' }}>
              Công ty
            </a>
          </Menu.Item>
          <Menu.Item key="guide" icon={<FileTextOutlined />}>
            <a href="#guide" style={{ textDecoration: 'none', color: '#000' }}>
              Cẩm nang việc làm
            </a>
          </Menu.Item>
          <Menu.Item key="cv-template" icon={<FileTextOutlined />}>
            <a href="#cv-template" style={{ textDecoration: 'none', color: '#000' }}>
              Mẫu CV Xin Việc
            </a>
          </Menu.Item>
        </Menu>

        {/* Right Navigation Links */}
        <div style={{ display: 'flex', alignItems: 'center', gap: '15px' }}>
          <Button
            type="link"
            href="#chat"
            icon={<MessageOutlined />}
            style={{ textDecoration: 'none', color: '#000' }}
          >
            Trò chuyện
          </Button>
          {user ? (
            <Dropdown overlay={userMenu} placement="bottomRight" arrow>
              <div style={{ display: 'flex', alignItems: 'center', cursor: 'pointer' }}>
                <Avatar style={{ backgroundColor: '#87d068' }} icon={<UserOutlined />} />
                {/* Hiển thị tên user bên cạnh avatar */}
                <span style={{ marginLeft: '10px', marginRight: '10px', color: '#000' }}>
                  {user.full_name}
                </span>
              </div>
            </Dropdown>
          ) : (
            <Dropdown overlay={guestMenu} placement="bottomRight" arrow>
              <Button
                type="link"
                icon={<UserOutlined />}
                style={{
                  textDecoration: 'none',
                  color: '#000',
                  border: '1px solid #ddd',
                  borderRadius: '5px',
                }}
              >
                Đăng ký / Đăng nhập
              </Button>
            </Dropdown>
          )}
          <Button
            type="link"
            href="/homeEmployer"
            style={{
              textDecoration: 'none',
              color: '#000',
              border: '1px solid #ddd',
              borderRadius: '5px',
              padding: '0 10px',
            }}
          >
            Nhà tuyển dụng
          </Button>
        </div>
      </div>
    </Header>
  );
};

HeaderComponent.propTypes = {
  user: PropTypes.shape({
    full_name: PropTypes.string,
  }),
  setUser: PropTypes.func.isRequired,
};

export default HeaderComponent;
