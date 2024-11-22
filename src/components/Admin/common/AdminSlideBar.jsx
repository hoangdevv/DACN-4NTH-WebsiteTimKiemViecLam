import React from 'react';
import PropTypes from 'prop-types';
import { Link, useLocation } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap-icons/font/bootstrap-icons.css';

const AdminSlideBar = ({ user }) => {
  const location = useLocation();

  return (
    <div>
      <div className="text-white p-3 small">
        {/* Thông tin admin */}
        <div className="d-flex align-items-center mb-4">
          <div className="p-2">
            <i className="bi bi-person-circle fs-1"></i>
          </div>
          <div className="p-2">
            <h6 className="mb-0">{user?.full_name || 'Admin'}</h6>
            <p className="mb-0 text-white">Administrator</p>
          </div>
        </div>

        <hr />

        {/* Danh sách điều hướng */}
        <ul className="nav flex-column">
          <li className="nav-item">
            <Link
              to="/admin"
              className={`nav-link text-white ${
                location.pathname === '/admin' ? 'active' : ''
              }`}
            >
              <i className="bi bi-house-door-fill me-2"></i> Trang chính
            </Link>
          </li>
          <li className="nav-item">
            <Link
              to="/admin/user-management"
              className={`nav-link text-white ${
                location.pathname === '/admin/user-management' ? 'active' : ''
              }`}
            >
              <i className="bi bi-people-fill me-2"></i> Quản lý người dùng
            </Link>
          </li>
          <li className="nav-item">
            <Link
              to="/admin/report-management"
              className={`nav-link text-white ${
                location.pathname === '/admin/report-management' ? 'active' : ''
              }`}
            >
              <i className="bi bi-file-earmark-bar-graph-fill me-2"></i> Quản lý báo cáo
            </Link>
          </li>
        </ul>
      </div>
    </div>
  );
};

AdminSlideBar.propTypes = {
  user: PropTypes.shape({
    full_name: PropTypes.string,
    account_type: PropTypes.string,
  }),
};

export default AdminSlideBar;
