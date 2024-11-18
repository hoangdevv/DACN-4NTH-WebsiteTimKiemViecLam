import React from 'react';
import PropTypes from 'prop-types';
import { Outlet, useLocation } from 'react-router-dom';
import Header from './User/common/Header';

const Layout = ({ user, setUser }) => {
  const location = useLocation();

  // Danh sách các route không cần Header
  const noHeaderRoutes = ['/jobs', '/jobDetail'];

  return (
    <>
      {/* Kiểm tra và chỉ hiển thị Header nếu không nằm trong noHeaderRoutes */}
      {!noHeaderRoutes.some((path) => location.pathname.startsWith(path)) && (
        <Header user={user} setUser={setUser} />
      )}
      <Outlet />
    </>
  );
};

Layout.propTypes = {
  user: PropTypes.shape({
    full_name: PropTypes.string,
    account_type: PropTypes.string,
  }),
  setUser: PropTypes.func.isRequired,
};

export default Layout;
