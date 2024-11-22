import React from 'react';
import PropTypes from 'prop-types';
import { Link, useLocation } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap-icons/font/bootstrap-icons.css';

const SlideBar = ({ user }) => {
  const location = useLocation(); 

  return (
    <div
      className="position-fixed"
      style={{
        top: 0,
        left: 0,
        height: '100vh',
        width: '250px',
        backgroundColor: '#343a40',
      }}
    >
      <div className="text-white p-3 small">
        {/* Thông tin người dùng */}
        <div className="d-flex align-items-center mb-4">
          <div className="p-2">
            <i className="bi bi-person-circle fs-1"></i>
          </div>
          <div className="p-2">
            <h6 className="mb-0">{user?.full_name || 'Employer'}</h6> 
            <p className="mb-0 text-white">{user?.account_type || ''}</p>
          </div>
        </div>

        <hr />

        {/* Danh sách điều hướng */}
        <ul className="nav flex-column">
          <li className="nav-item">
            <Link
              to="/dashboard"
              className={`nav-link text-white ${
                location.pathname === '/dashboard' ? 'active' : ''
              }`}
            >
              <i className="bi bi-border-all me-2"></i> Bảng tin
            </Link>
          </li>
          <li className="nav-item">
            <Link
              to="/dashboard/cv-management"
              className={`nav-link text-white ${
                location.pathname === '/dashboard/cv-management' ? 'active' : ''
              }`}
            >
              <i className="bi bi-file-earmark-person me-2"></i> Quản lý CV
            </Link>
          </li>
          <li className="nav-item">
            <Link
              to="/dashboard/recruitment-reports"
              className={`nav-link text-white ${
                location.pathname === '/dashboard/recruitment-reports' ? 'active' : ''
              }`}
            >
              <i className="bi bi-graph-up-arrow me-2"></i> Báo cáo tuyển dụng
            </Link>
          </li>
          <li className="nav-item">
            <Link
              to="/dashboard/campaign-management"
              className={`nav-link text-white ${
                location.pathname === '/dashboard/campaign-management' ? 'active' : ''
              }`}
            >
              <i className="bi bi-briefcase-fill me-2"></i> Chiến dịch tuyển dụng
            </Link>
          </li>
        </ul>

        <hr />

        <ul className="nav flex-column">
          <li className="nav-item">
            <Link
              to="/dashboard/purchase-service"
              className={`nav-link text-white ${
                location.pathname === '/dashboard/purchase-service' ? 'active' : ''
              }`}
            >
              <i className="bi bi-cart-fill me-2"></i> Mua dịch vụ
            </Link>
          </li>
          <li className="nav-item">
            <Link
              to="/dashboard/my-services"
              className={`nav-link text-white ${
                location.pathname === '/dashboard/my-services' ? 'active' : ''
              }`}
            >
              <i className="bi bi-bag-fill me-2"></i> Dịch vụ của tôi
            </Link>
          </li>
          <li className="nav-item">
            <Link
              to="/dashboard/order-tracking"
              className={`nav-link text-white ${
                location.pathname === '/dashboard/order-tracking' ? 'active' : ''
              }`}
            >
              <i className="bi bi-receipt me-2"></i> Theo dõi đơn hàng
            </Link>
          </li>
        </ul>

        <hr />

        <ul className="nav flex-column">
          <li className="nav-item">
            <Link
              to="/dashboard/notifications"
              className={`nav-link text-white ${
                location.pathname === '/dashboard/notifications' ? 'active' : ''
              }`}
            >
              <i className="bi bi-bell-fill me-2"></i> Thông báo
            </Link>
          </li>
          <li className="nav-item">
            <Link
              to="/dashboard/account-settings"
              className={`nav-link text-white ${
                location.pathname === '/dashboard/account-settings' ? 'active' : ''
              }`}
            >
              <i className="bi bi-gear-fill me-2"></i> Cài đặt tài khoản
            </Link>
          </li>
        </ul>
      </div>
    </div>
  );
};

SlideBar.propTypes = {
  user: PropTypes.shape({
    full_name: PropTypes.string,
    account_type: PropTypes.string,
  }),
};

export default SlideBar;
