import React from 'react';
import { Layout, Menu, Dropdown, Button, Space } from 'antd';
import { 
  MessageOutlined, 
  HomeOutlined, 
  LoginOutlined, 
  UserAddOutlined 
} from '@ant-design/icons';
import logo from '../../../assets/logos/logo.png';

const { Header } = Layout;

const HeaderComponent = () => {
  // Dropdown menu for registration/login
  const accountMenu = (
    <Menu>
      <Menu.Item key="register" icon={<UserAddOutlined />}>
        <a href="/registerEmployer" style={{ textDecoration: 'none' }}>Đăng ký</a>
      </Menu.Item>
      <Menu.Item key="login" icon={<LoginOutlined />}>
        <a href="/loginEmployer" style={{ textDecoration: 'none' }}>Đăng nhập</a>
      </Menu.Item>
    </Menu>
  );

  return (
    <Header 
      style={{ 
        backgroundColor: 'rgb(2, 0, 36)', 
        display: 'flex', 
        alignItems: 'center', 
        justifyContent: 'space-between',
        padding: '0 24px',
        position: 'sticky',
        top: 0,
        zIndex: 1,
        boxShadow: '0 2px 8px rgba(0, 0, 0, 0.15)'
      }}
    >
      {/* Logo */}
      <div>
        <a href="/" style={{ textDecoration: 'none' }}>
          <img 
            src={logo} 
            alt="CareerLink Logo" 
            style={{ 
              height: '40px', 
              verticalAlign: 'middle' 
            }} 
          />
        </a>
      </div>

      {/* Navigation Menu */}
      <Space size="middle">
        <Button 
          type="text" 
          icon={<MessageOutlined />} 
          href="#chat"
          style={{ 
            color: 'white', 
            textDecoration: 'none' 
          }}
        >
          Trò chuyện
        </Button>

        <Dropdown overlay={accountMenu} placement="bottomRight">
          <Button 
            type="default" 
            icon={<HomeOutlined />}
            style={{ 
              backgroundColor: 'transparent', 
              color: 'white',
              border: '1px solid rgba(255,255,255,0.3)',
              borderRadius: '8px',
              textDecoration: 'none' 
            }}
          >
            Đăng ký / Đăng nhập
          </Button>
        </Dropdown>

        <Button 
          type="text" 
          href="/"
          style={{ 
            color: 'white', 
            textDecoration: 'none' 
          }}
        >
          Người tìm việc
        </Button>
      </Space>
    </Header>
  );
};

export default HeaderComponent;
