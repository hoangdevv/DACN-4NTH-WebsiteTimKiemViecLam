import React, { useState } from "react";
import {
  Form,
  Input,
  Button,
  Checkbox,
  Typography,
  message,
  Row,
  Col,
} from "antd";
import "../../../styles/Register.css";
import { registerUser, verifyAccount } from "../../../api/authApi"; // Import API functions

const { Title, Text } = Typography;

const Register = () => {
  const [form] = Form.useForm();
  const [currentStep, setCurrentStep] = useState("register"); // Quản lý bước hiện tại
  const [verificationCode, setVerificationCode] = useState("");
  const [email, setEmail] = useState("");
  const [agreeTerms, setAgreeTerms] = useState(false); // Trạng thái checkbox

  // Xử lý đăng ký
  const handleRegisterSubmit = async (values) => {
    if (!agreeTerms) {
      message.error("Bạn phải đồng ý với điều khoản để tiếp tục!");
      return;
    }

    const { firstName, lastName, email, password, phone } = values;
    const userDTO = {
      fullName: `${firstName} ${lastName}`,
      phone,
      email,
      password,
      confirmPassword: password, // Gửi confirmPassword như trong Postman
    };

    try {
      await registerUser(userDTO); // Gọi API đăng ký người dùng
      message.success("Đăng ký thành công! Hãy nhập mã xác thực.");
      setEmail(email); // Lưu email để sử dụng trong bước xác thực
      setCurrentStep("transition-verify"); // Chuyển sang bước xác thực
      setTimeout(() => setCurrentStep("verify"), 500); // Sau animation, hiển thị form xác thực
    } catch (error) {
      message.error(error?.message || "Đã xảy ra lỗi khi đăng ký.");
    }
  };

  // Xử lý xác thực
  const handleVerificationSubmit = async () => {
    try {
      console.log("Email:", email); // In email để kiểm tra
      console.log("Verification Code:", verificationCode); // In mã xác thực để kiểm tra
      await verifyAccount(email, verificationCode); // Gọi API xác thực tài khoản
      message.success("Xác thực thành công!");
      window.location.href = "./"; // Chuyển hướng về trang chủ sau khi xác thực thành công
    } catch (error) {
      // Log lỗi chi tiết để có thể debug rõ ràng
      console.error("Lỗi xác thực:", error); // In lỗi chi tiết ra console
  
      // Thông báo cho người dùng khi có lỗi
      message.error(error?.message || "Mã xác thực không đúng!");
    }
  };
  // Xử lý quay lại bước đăng ký
  const handleBack = () => {
    setCurrentStep("transition-register");
    setTimeout(() => setCurrentStep("register"), 500);
  };

  return (
    <div className="register-container">
      <div className={`form-wrapper ${currentStep}`}>
        {/* Form Đăng ký */}
        <div
          className={`form-content ${
            currentStep === "register" ? "active" : ""
          }`}
        >
          <Title
            level={1}
            style={{
              textAlign: "center",
              marginBottom: "1.5rem",
              color: "#333",
            }}
          >
            Đăng ký tài khoản
          </Title>
          <Form
            form={form}
            layout="vertical"
            onFinish={handleRegisterSubmit}
            requiredMark={false}
          >
            <Row gutter={16}>
              <Col span={12}>
                <Form.Item
                  label="Họ"
                  name="firstName"
                  rules={[{ required: true, message: "Vui lòng nhập họ!" }]}
                >
                  <Input placeholder="Nhập họ" />
                </Form.Item>
              </Col>
              <Col span={12}>
                <Form.Item
                  label="Tên"
                  name="lastName"
                  rules={[{ required: true, message: "Vui lòng nhập tên!" }]}
                >
                  <Input placeholder="Nhập tên" />
                </Form.Item>
              </Col>
            </Row>

            <Form.Item
              label="Số điện thoại"
              name="phone"
              rules={[
                { required: true, message: "Vui lòng nhập số điện thoại!" },
              ]}
            >
              <Input placeholder="Nhập số điện thoại" />
            </Form.Item>

            <Form.Item
              label="Email"
              name="email"
              rules={[
                { required: true, message: "Vui lòng nhập email!" },
                { type: "email", message: "Email không hợp lệ!" },
              ]}
            >
              <Input placeholder="Nhập email" />
            </Form.Item>

            <Form.Item
              label="Mật khẩu"
              name="password"
              rules={[
                { required: true, message: "Vui lòng nhập mật khẩu!" },
                { min: 6, message: "Mật khẩu phải có ít nhất 6 ký tự!" },
              ]}
            >
              <Input.Password placeholder="Nhập mật khẩu" />
            </Form.Item>

            <Form.Item
              label="Nhập lại mật khẩu"
              name="confirmPassword"
              dependencies={["password"]}
              rules={[
                { required: true, message: "Vui lòng nhập lại mật khẩu!" },
                ({ getFieldValue }) => ({
                  validator(_, value) {
                    if (!value || getFieldValue("password") === value) {
                      return Promise.resolve();
                    }
                    return Promise.reject(new Error("Mật khẩu không khớp!"));
                  },
                }),
              ]}
            >
              <Input.Password placeholder="Nhập lại mật khẩu" />
            </Form.Item>

            <Form.Item>
              <Checkbox
                checked={agreeTerms}
                onChange={(e) => setAgreeTerms(e.target.checked)}
              >
                Tôi đồng ý với <a href="/terms">điều khoản</a>.
              </Checkbox>
            </Form.Item>

            <Button type="primary" htmlType="submit" block>
              Đăng ký
            </Button>
          </Form>
        </div>

        {/* Form Xác thực */}
        <div
          className={`form-content ${currentStep === "verify" ? "active" : ""}`}
        >
          <Title
            level={3}
            style={{ textAlign: "center", marginBottom: "1rem", color: "#333" }}
          >
            Nhập mã xác thực
          </Title>
          <Text>
            Mã xác thực đã được gửi đến email của bạn. Vui lòng nhập mã gồm 6 số
            để hoàn tất đăng ký.
          </Text>
          <Form
            layout="vertical"
            onFinish={handleVerificationSubmit}
            style={{ marginTop: "1rem" }}
          >
            <Form.Item
              label="Mã xác thực"
              rules={[
                { required: true, message: "Vui lòng nhập mã xác thực!" },
                { len: 6, message: "Mã xác thực phải gồm 6 số!" },
              ]}
            >
              <Input
                placeholder="Nhập mã xác thực"
                value={verificationCode}
                onChange={(e) => setVerificationCode(e.target.value)}
                maxLength={6}
              />
            </Form.Item>

            <Button type="primary" htmlType="submit" block>
              Xác thực
            </Button>
            <Button
              type="default"
              block
              style={{ marginTop: "0.5rem" }}
              onClick={handleBack}
            >
              Trở lại
            </Button>
          </Form>
        </div>
      </div>
    </div>
  );
};

export default Register;
