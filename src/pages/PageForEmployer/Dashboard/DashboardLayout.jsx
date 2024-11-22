import React from 'react';
import PropTypes from 'prop-types';
import { Outlet } from 'react-router-dom';
import SlideBar from '../../../components/Employer/common/SlideBar';

const DashboardLayout = ({ user }) => {
  return (
    <div className="d-flex">
      {/* Sidebar cố định */}
      <div
        className="bg-dark text-white p-3 position-fixed"
        style={{ top: 0, left: 0, height: '100vh', width: '250px' }}
      >
        <SlideBar user={user} /> 
      </div>

      {/* Nội dung động */}
      <div
        className="container-fluid"
        style={{ marginLeft: '250px', padding: '20px' }}
      >
        <Outlet />
      </div>
    </div>
  );
};

DashboardLayout.propTypes = {
  user: PropTypes.shape({
    full_name: PropTypes.string,
    account_type: PropTypes.string,
  }).isRequired,
};

export default DashboardLayout;
