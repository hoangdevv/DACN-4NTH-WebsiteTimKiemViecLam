import React from "react";
import { Button, Form, Input, Typography, Card, message } from "antd";
import { MailOutlined, LockOutlined } from "@ant-design/icons";
import { useNavigate } from "react-router-dom";
import PropTypes from 'prop-types';
import { accounts } from "../../../components/data/accounts"; 
import styles from "../../../styles/AdminLogin.module.css";

const { Title } = Typography;

const AdminLogin = ({ setUser }) => {
  const navigate = useNavigate();

  const handleLogin = (values) => {
    const { email, password } = values;

    // Tìm tài khoản theo email và password
    const account = accounts.find(
      (acc) => acc.email === email && acc.password === password
    );

    if (!account) {
      message.error("Email hoặc mật khẩu không đúng!");
      return;
    }

    if (account.account_type === "admin") {
      // Lưu thông tin người dùng và chuyển hướng
      setUser(account);
      message.success("Đăng nhập thành công!");
      navigate("/admin");
    } else {
      message.warning("Bạn không có quyền truy cập trang này!");
    }
  };

  return (
    <div className={styles["login-container"]}>
      <Card className={`${styles["login-card"]}`}>
        <div style={{ marginBottom: 30 }}>
          <Title level={3} style={{ color: "rgb(204, 10, 157)", textAlign: 'center' }}>
            Đăng nhập Admin
          </Title>
          <p style={{ color: "rgba(0, 0, 0, 0.6)" }}>
            Xin Chào Quản Trị Viên! Vui lòng đăng nhập tài khoản
          </p>
        </div>
        <Form name="admin_login" onFinish={handleLogin}>
          <Form.Item
            name="email"
            rules={[
              { required: true, message: "Vui lòng nhập Email!" },
              { type: "email", message: "Định dạng Email không hợp lệ!" },
            ]}
          >
            <Input
              prefix={<MailOutlined />}
              placeholder="Email"
              style={{ borderRadius: 5 }}
            />
          </Form.Item>
          <Form.Item
            name="password"
            rules={[{ required: true, message: "Vui lòng nhập Password!" }]}
          >
            <Input.Password
              prefix={<LockOutlined />}
              placeholder="Password"
              style={{ borderRadius: 5 }}
            />
          </Form.Item>
          <Form.Item>
          <Button
            type="primary"
            htmlType="submit"
            className={styles.btn}
            style={{
              width: "100%",
              borderRadius: 5,
              background: "rgb(204, 10, 157)",
              border: "none",
            }}
          >
            Login
          </Button>
          </Form.Item>
        </Form>
      </Card>
    </div>
  );
};

AdminLogin.propTypes = {
  setUser: PropTypes.func.isRequired, 
};

export default AdminLogin;
