import React from 'react';
import { Link } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap-icons/font/bootstrap-icons.css';

const SlideBar = () => {
    return (
        <div className="position-fixed" style={{ top: 0, left: 0, height: '100vh', width: '250px', backgroundColor: '#343a40' }}>
            <div className="text-white p-3 small">
                
                {/* Thông tin người dùng */}
                <div className="d-flex align-items-center mb-4">
                    <div className="p-2">
                        <i className="bi bi-person-circle fs-1"></i>
                    </div>
                    <div className="p-2">
                        <h6 className="mb-0">Nguyễn Đức Thắng</h6>
                        <p className="mb-0 text-white">Employer</p>
                    </div>
                </div>
                
                <hr />

                {/* Danh sách điều hướng */}
                <ul className="nav flex-column">
                    <li className="nav-item">
                        <Link to="/dashboard" className="nav-link text-white">
                            <i className="bi bi-border-all me-2"></i> Bảng tin
                        </Link>
                    </li>
                    <li className="nav-item">
                        <Link to="/dashboard/users" className="nav-link text-white">
                            <i className="bi bi-file-earmark-person me-2"></i> CV đề xuất
                        </Link>
                    </li>
                </ul>
                
                <hr />

                <ul className="nav flex-column">
                    <li className="nav-item">
                        <Link to="/dashboard/settings" className="nav-link text-white">
                            <i className="bi bi-briefcase-fill me-2"></i> Chiến dịch tuyển dụng
                        </Link>
                    </li>
                    <li className="nav-item">
                        <Link to="/dashboard/reports" className="nav-link text-white">
                            <i className="bi bi-file-earmark-text-fill me-2"></i> Tin tuyển dụng
                        </Link>
                    </li>
                    <li className="nav-item">
                        <Link to="/dashboard/cv-management" className="nav-link text-white">
                            <i className="bi bi-people-fill me-2"></i> Quản lý CV
                        </Link>
                    </li>
                    <li className="nav-item">
                        <Link to="/dashboard/recruitment-reports" className="nav-link text-white">
                            <i className="bi bi-graph-up-arrow me-2"></i> Báo cáo tuyển dụng
                        </Link>
                    </li>
                </ul>
                
                <hr />

                <ul className="nav flex-column">
                    <li className="nav-item">
                        <Link to="/dashboard/purchase-service" className="nav-link text-white">
                            <i className="bi bi-cart-fill me-2"></i> Mua dịch vụ
                        </Link>
                    </li>
                    <li className="nav-item">
                        <Link to="/dashboard/my-services" className="nav-link text-white">
                            <i className="bi bi-bag-fill me-2"></i> Dịch vụ của tôi
                        </Link>
                    </li>
                    <li className="nav-item">
                        <Link to="/dashboard/order-tracking" className="nav-link text-white">
                            <i className="bi bi-receipt me-2"></i> Theo dõi đơn hàng
                        </Link>
                    </li>
                    <li className="nav-item">
                        <Link to="/dashboard/recruitment-reports" className="nav-link text-white">
                            <i className="bi bi-bar-chart-fill me-2"></i> Báo cáo tuyển dụng
                        </Link>
                    </li>
                </ul>
                
                <hr />

                <ul className="nav flex-column">
                    <li className="nav-item">
                        <Link to="/dashboard/notifications" className="nav-link text-white">
                            <i className="bi bi-bell-fill me-2"></i> Thông báo
                        </Link>
                    </li>
                    <li className="nav-item">
                        <Link to="/dashboard/account-settings" className="nav-link text-white">
                            <i className="bi bi-gear-fill me-2"></i> Cài đặt tài khoản
                        </Link>
                    </li>
                </ul>
            </div>
        </div>
    );
};

export default SlideBar;
