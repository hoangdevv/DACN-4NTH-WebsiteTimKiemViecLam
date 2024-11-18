import React, { useState } from 'react';
import { Form, Button, Card, Container } from 'react-bootstrap';
import Header from '../../../components/Employer/common/Header'
import Footer from '../../../components/User/common/Footer';

const RegisterEmployer = () => {
    const [showPassword, setShowPassword] = useState(false);

    return (
        <div>
            <Header />
            <Container className="d-flex justify-content-center align-items-center mt-5 mb-5">
                <Card className="shadow p-5" style={{ maxWidth: '900px', width: '100%' }}> 
                    <h4 className="text-primary mb-4">Nhà tuyển dụng đăng ký</h4>
                    <p className="text-muted">Tạo tài khoản để tiếp cận kho ứng viên chất lượng và bắt đầu đăng việc ngay</p>
                    <Form>
                        <Form.Group className="mb-3" controlId="formEmail">
                            <Form.Label>Email</Form.Label>
                            <Form.Control type="email" placeholder="Email" />
                        </Form.Group>
                        <Form.Group className="mb-3" controlId="formPassword">
                            <Form.Label>Mật khẩu</Form.Label>
                            <Form.Control type={showPassword ? 'text' : 'password'} placeholder="Mật khẩu" />
                        </Form.Group>
                        <Form.Group className="mb-3" controlId="formConfirmPassword">
                            <Form.Label>Nhập lại mật khẩu</Form.Label>
                            <Form.Control type={showPassword ? 'text' : 'password'} placeholder="Nhập lại mật khẩu" />
                        </Form.Group>
                        <Form.Group className="mb-3" controlId="formShowPassword">
                            <Form.Check
                                type="checkbox"
                                label="Hiển thị mật khẩu"
                                onChange={() => setShowPassword(!showPassword)}
                            />
                        </Form.Group>

                        <h5 className="mt-4 mb-3">Thông tin công ty</h5>
                        <Form.Group className="mb-3" controlId="formCompanyName">
                            <Form.Label>Tên công ty</Form.Label>
                            <Form.Control type="text" placeholder="Tên công ty" />
                        </Form.Group>
                        <Form.Group className="mb-3" controlId="formEmployeeSize">
                            <Form.Label>Số nhân viên</Form.Label>
                            <Form.Select>
                                <option>Chọn số lượng nhân viên</option>
                                <option value="1">1-10</option>
                                <option value="2">11-50</option>
                                <option value="3">51-200</option>
                            </Form.Select>
                        </Form.Group>
                        <Form.Group className="mb-3" controlId="formContact">
                            <Form.Label>Người liên hệ</Form.Label>
                            <Form.Control type="text" placeholder="Người liên hệ" />
                        </Form.Group>
                        <Form.Group className="mb-3" controlId="formPhone">
                            <Form.Label>Điện thoại</Form.Label>
                            <Form.Control type="text" placeholder="Điện thoại" />
                        </Form.Group>

                        <Form.Group className="mb-3" controlId="formLocation">
                            <Form.Label>Địa chỉ</Form.Label>
                            <Form.Control type="text" placeholder="Địa chỉ" />
                        </Form.Group>
                        <Form.Group className="mb-3" controlId="formNewsletter">
                            <Form.Check type="checkbox" label="Nhận bản tin việc làm" />
                        </Form.Group>
                        <Button variant="primary" type="submit" className="w-100 py-2">
                            Đăng ký ngay
                        </Button>
                    </Form>
                    <p className="mt-4 text-center text-muted" style={{ fontSize: '0.875rem' }}>
                        Tôi đồng ý với việc xử lý và cung cấp thông tin dữ liệu cá nhân, đồng thời đã đọc và đồng ý với Thỏa thuận sử dụng và Quy định bảo mật của CareerLink.
                    </p>
                </Card>
            </Container>
            <Footer />
        </div>
    );
};

export default RegisterEmployer;
