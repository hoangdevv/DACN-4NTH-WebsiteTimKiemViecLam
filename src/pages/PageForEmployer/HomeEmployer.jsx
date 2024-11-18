import React from 'react';
import Header from '../../components/Employer/common/Header'
import Footer from '../../components/User/common/Footer';
const HomeEmployer = () => {
    return (
        <div>
            <Header />
            <div className="container py-5">
            {/* Giới thiệu tổng quan */}
            <section className="text-center mb-5">
                <h2 className="display-4 text-dark">Chào mừng đến với Trang Nhà Tuyển Dụng</h2>
                <p className="lead text-muted">
                    Tìm kiếm và tuyển dụng ứng viên phù hợp nhanh chóng với các gói dịch vụ đăng tuyển đa dạng.
                </p>
            </section>

            {/* Các gói dịch vụ */}
            <section className="mb-5">
                <h2 className="text-center text-dark mb-4">Các Gói Dịch Vụ Đăng Tuyển</h2>
                <div className="row row-cols-1 row-cols-md-3 g-4">
                    {['Cơ Bản', 'Nâng Cao', 'Premium'].map((pkg, index) => (
                        <div key={index} className="col">
                            <div className="card shadow-sm">
                                <div className="card-body text-center">
                                    <h5 className="card-title">{pkg} Gói</h5>
                                    <p className="card-text">
                                        Đăng tuyển công việc, quản lý hồ sơ ứng viên và nhiều tính năng khác.
                                    </p>
                                    <button className="btn btn-primary">Đăng ký ngay</button>
                                </div>
                            </div>
                        </div>
                    ))}
                </div>
            </section>

            {/* Lý do chọn dịch vụ */}
            <section className="text-center mb-5">
                <h2 className="text-dark mb-4">Tại sao chọn chúng tôi?</h2>
                <ul className="list-unstyled">
                    <li className="fs-5 mb-3">
                        <i className="bi bi-check-circle text-primary"></i> Đăng tuyển nhanh chóng và dễ dàng.
                    </li>
                    <li className="fs-5 mb-3">
                        <i className="bi bi-check-circle text-primary"></i> Hỗ trợ ứng viên chất lượng từ mọi ngành nghề.
                    </li>
                    <li className="fs-5 mb-3">
                        <i className="bi bi-check-circle text-primary"></i> Giao diện đơn giản và dễ sử dụng.
                    </li>
                </ul>
            </section>
        </div>
            <Footer />
        </div>
    );
};

export default HomeEmployer;