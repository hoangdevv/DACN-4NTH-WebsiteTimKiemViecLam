import React, { useState, useEffect } from "react";
import { Form, Input, Button, Steps, Row, Col, Select } from "antd";

const { Step } = Steps;
const { Option } = Select;

const RegisterPage = () => {
  const [currentStep, setCurrentStep] = useState(0);
  const [form] = Form.useForm();

  // State cho Quốc gia, Tỉnh/Thành phố, Quận/Huyện
  const [countries, setCountries] = useState([]);
  const [provinces, setProvinces] = useState([]);
  const [districts, setDistricts] = useState([]);

  // Giá trị đã chọn
  const [selectedCountry, setSelectedCountry] = useState(null);
  const [selectedProvince, setSelectedProvince] = useState(null);

  // Lấy danh sách Quốc gia và sắp xếp
  useEffect(() => {
    fetch("https://restcountries.com/v3.1/all")
      .then((response) => response.json())
      .then((data) => {
        const countryOptions = data
          .map((country) => ({
            label: country.name.common,
            value: country.name.common,
          }))
          .sort((a, b) => a.label.localeCompare(b.label)); // Sắp xếp theo tên
        setCountries(countryOptions);
      })
      .catch((error) => console.error(error));
  }, []);

  // Lấy danh sách Tỉnh/Thành phố theo Quốc gia đã chọn và sắp xếp
  useEffect(() => {
    if (selectedCountry) {
      fetch("https://provinces.open-api.vn/api/p/")
        .then((response) => response.json())
        .then((data) => {
          const provinceOptions = data
            .map((province) => ({
              label: province.name,
              value: province.code,
            }))
            .sort((a, b) => a.label.localeCompare(b.label)); // Sắp xếp theo tên
          setProvinces(provinceOptions);
        })
        .catch((error) => console.error(error));
    }
  }, [selectedCountry]);

  // Lấy danh sách Quận/Huyện theo Tỉnh/Thành phố đã chọn và sắp xếp
  useEffect(() => {
    if (selectedProvince) {
      fetch(`https://provinces.open-api.vn/api/d/search/?p=${selectedProvince}`)
        .then((response) => response.json())
        .then((data) => {
          const districtOptions = data
            .map((district) => ({
              label: district.name,
              value: district.code,
            }))
            .sort((a, b) => a.label.localeCompare(b.label)); // Sắp xếp theo tên
          setDistricts(districtOptions);
        })
        .catch((error) => console.error(error));
    }
  }, [selectedProvince]);

  const onNext = async () => {
    try {
      await form.validateFields(); // Kiểm tra dữ liệu hợp lệ
      setCurrentStep(currentStep + 1);
    } catch (error) {
      console.log("Validation failed:", error);
    }
  };

  const onPrev = () => {
    setCurrentStep(currentStep - 1);
  };

  const onFinish = (values) => {
    console.log("Form Data:", values);
  };

  return (
    <div style={{ display: "flex", height: "100vh" }}>
      {/* Bên trái */}
      <div
        style={{
          flex: 4,
          background: "linear-gradient(to right, #020024, #cc0a9d)",
        }}
      ></div>

      {/* Bên phải */}
      <div
        style={{
          flex: 6,
          display: "flex",
          justifyContent: "center",
          alignItems: "center",
          padding: "40px",
          backgroundColor: "#fff",
        }}
      >
        <div style={{ width: "100%", maxWidth: "500px" }}>
          <h2 style={{ textAlign: "center", marginBottom: "20px" }}>Đăng ký</h2>
          <Steps
            current={currentStep}
            size="small"
            style={{ marginBottom: "20px" }}
          >
            <Step title="Liên lạc" />
            <Step title="Công ty" />
          </Steps>
          <Form
            form={form}
            layout="vertical"
            onFinish={onFinish}
            style={{ width: "100%" }}
          >
            {currentStep === 0 && (
              <>
                <Row gutter={16}>
                  <Col span={12}>
                    <Form.Item
                      label="Tên"
                      name="firstName"
                      rules={[
                        { required: true, message: "Vui lòng nhập tên!" },
                      ]}
                    >
                      <Input placeholder="" />
                    </Form.Item>
                  </Col>
                  <Col span={12}>
                    <Form.Item
                      label="Họ"
                      name="lastName"
                      rules={[
                        { required: true, message: "Vui lòng nhập họ!" },
                      ]}
                    >
                      <Input placeholder="" />
                    </Form.Item>
                  </Col>
                </Row>

                <Form.Item
                  label="Điện thoại"
                  name="phone"
                  rules={[
                    {
                      required: true,
                      message: "Vui lòng nhập số điện thoại!",
                    },
                    {
                      pattern: /^\d{10,11}$/,
                      message: "Số điện thoại không hợp lệ!",
                    },
                  ]}
                >
                  <Input placeholder="" />
                </Form.Item>

                <Form.Item
                  label="Địa chỉ email"
                  name="email"
                  rules={[
                    { required: true, message: "Vui lòng nhập email!" },
                    { type: "email", message: "Email không hợp lệ!" },
                  ]}
                >
                  <Input placeholder="" />
                </Form.Item>

                <Form.Item
                  label="Mật khẩu"
                  name="password"
                  rules={[
                    { required: true, message: "Vui lòng nhập mật khẩu!" },
                    { min: 6, message: "Mật khẩu phải có ít nhất 6 ký tự!" },
                  ]}
                >
                  <Input.Password placeholder="" />
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
                        return Promise.reject(
                          new Error("Mật khẩu nhập lại không khớp!")
                        );
                      },
                    }),
                  ]}
                >
                  <Input.Password placeholder="" />
                </Form.Item>
              </>
            )}

            {currentStep === 1 && (
              <>
                <Form.Item
                  label="Tên công ty"
                  name="companyName"
                  rules={[{ required: true, message: "Vui lòng nhập tên công ty!" }]}
                >
                  <Input placeholder="" />
                </Form.Item>

                <Form.Item
                  label="Ngành nghề"
                  name="industry"
                  rules={[{ required: true, message: "Vui lòng chọn ngành nghề!" }]}
                >
                  <Select placeholder="Chọn ngành nghề">
                    {/* Giả sử bạn có danh sách ngành nghề */}
                    <Option value="Công nghệ thông tin">Công nghệ thông tin</Option>
                    <Option value="Giáo dục">Giáo dục</Option>
                  </Select>
                </Form.Item>

                <Form.Item label="Địa chỉ công ty" name="address">
                  <Row gutter={16}>
                    <Col span={8}>
                      <Select
                        placeholder="Quốc gia"
                        options={countries}
                        onChange={(value) => setSelectedCountry(value)}
                      />
                    </Col>
                    <Col span={8}>
                      <Select
                        placeholder="Tỉnh/Thành phố"
                        options={provinces}
                        disabled={!selectedCountry}
                        onChange={(value) => setSelectedProvince(value)}
                      />
                    </Col>
                    <Col span={8}>
                      <Select
                        placeholder="Quận/Huyện"
                        options={districts}
                        disabled={!selectedProvince}
                      />
                    </Col>
                  </Row>
                  <Input
                    style={{ marginTop: "10px" }}
                    placeholder="Số nhà, phường/xã"
                  />
                </Form.Item>
              </>
            )}

            <div
              style={{
                display: "flex",
                justifyContent: currentStep > 0 ? "space-between" : "flex-end",
                marginTop: "20px",
              }}
            >
              {currentStep > 0 && (
                <Button onClick={onPrev} style={{ marginRight: "10px" }}>
                  Quay lại
                </Button>
              )}
              {currentStep < 1 && (
                <Button type="primary" onClick={onNext}>
                  Tiếp tục
                </Button>
              )}
              {currentStep === 1 && (
                <Button type="primary" htmlType="submit">
                  Hoàn tất
                </Button>
              )}
            </div>
          </Form>
          <p style={{ textAlign: "center", marginTop: "20px" }}>
            Bạn đã có tài khoản? <a href="/loginEmployer">Đăng nhập</a>
          </p>
        </div>
      </div>
    </div>
  );
};

export default RegisterPage;
