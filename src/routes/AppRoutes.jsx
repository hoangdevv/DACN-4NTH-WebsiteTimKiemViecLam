import React from 'react';
import PropTypes from 'prop-types';
import { Routes, Route, Navigate } from 'react-router-dom';
import Layout from '../components/Layout';
import HeaderJobsLayout from '../components/User/Jobs/HeaderJobsLayout';
import ProfileManagement from '../pages/PageForUser/ProfileManagement';
import Home from '../pages/PageForUser/Home';
import NotFound from '../pages/NotFound';
import Login from '../pages/PageForUser/LoginForUser/Login';
import Register from '../pages/PageForUser/LoginForUser/Register';
import Jobs from '../pages/PageForUser/Jobs';
import JobDetail from '../pages/PageForUser/JobDetail';
import Companies from '../pages/PageForUser/Companies';
import CompanyDetail from '../pages/PageForUser/CompanyDetail';
import HomeEmployer from '../pages/PageForEmployer/HomeEmployer';
import EmployerDashboard from '../pages/PageForEmployer/Dashboard/EmployerDashboard';
import LoginEmployer from '../pages/PageForEmployer/LoginForEmployer/LoginEmployer';
import RegisterEmployer from '../pages/PageForEmployer/LoginForEmployer/RegisterEmployer';

const AppRoutes = ({ user, setUser }) => {
  return (
    <Routes>
      {/* Public routes with default Layout */}
      <Route element={<Layout user={user} setUser={setUser} />}>
        <Route path="/" element={<Home />} /> 
        <Route path="/companies" element={<Companies />} />
        <Route path="/companyDetail/:id" element={<CompanyDetail />} />
        <Route path="/profileManagement" element={<ProfileManagement user={user} setUser={setUser} />} />
      </Route>

      {/* Routes with HeaderJobsLayout */}
      <Route element={<HeaderJobsLayout user={user} setUser={setUser} />}>
        <Route path="/jobs" element={<Jobs />} />
        <Route path="/jobDetail/:id" element={<JobDetail />} />
      </Route>

      {/* Auth routes */}
      <Route path="/login" element={<Login setUser={setUser} />} />
      <Route path="/register" element={<Register />} />
      <Route path="/LoginEmployer" element={<LoginEmployer setUser={setUser} />} />
      <Route path="/RegisterEmployer" element={<RegisterEmployer />} />

      {/* Protected routes */}
      <Route
        path="/homeEmployer"
        element={
          user?.account_type === 'employer' ? (
            <HomeEmployer />
          ) : (
            <Navigate to="/LoginEmployer" />
          )
        }
      />
      <Route
        path="/employerDashboard"
        element={
          user?.account_type === 'employer' ? (
            <EmployerDashboard />
          ) : (
            <Navigate to="/LoginEmployer" />
          )
        }
      />

      {/* Fallback for undefined routes */}
      <Route path="*" element={<NotFound />} />
    </Routes>
  );
};

AppRoutes.propTypes = {
  user: PropTypes.shape({
    full_name: PropTypes.string,
    account_type: PropTypes.string,
  }),
  setUser: PropTypes.func.isRequired,
};

export default AppRoutes;
