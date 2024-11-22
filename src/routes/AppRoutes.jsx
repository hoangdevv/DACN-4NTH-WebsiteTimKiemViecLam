import React from 'react';
import PropTypes from 'prop-types';
import { Routes, Route, Navigate } from 'react-router-dom';

// Layouts
import Layout from '../components/Layout';
import HeaderJobsLayout from '../components/User/Jobs/HeaderJobsLayout';
import DashboardLayout from '../pages/PageForEmployer/Dashboard/DashboardLayout';
import AdminLayout from '../pages/PageForAdmin/Dashboard/AdminLayout';

// Public Pages
import Home from '../pages/PageForUser/Home';
import NotFound from '../pages/NotFound';
import ProfileManagement from '../pages/PageForUser/ProfileManagement';
import Companies from '../pages/PageForUser/Companies';
import CompanyDetail from '../pages/PageForUser/CompanyDetail';
import Jobs from '../pages/PageForUser/Jobs';
import JobDetail from '../pages/PageForUser/JobDetail';

// Auth Pages
import Login from '../pages/PageForUser/LoginForUser/Login';
import Register from '../pages/PageForUser/LoginForUser/Register';
import LoginEmployer from '../pages/PageForEmployer/LoginForEmployer/LoginEmployer';
import RegisterEmployer from '../pages/PageForEmployer/LoginForEmployer/RegisterEmployer';

// Employer Dashboard Pages
import DashboardHome from '../pages/PageForEmployer/Dashboard/DashboardHome';
import CVManagement from '../pages/PageForEmployer/Dashboard/CVManagement';
import RecruitmentReports from '../pages/PageForEmployer/Dashboard/RecruitmentReports';
import CampaignManagement from '../pages/PageForEmployer/Dashboard/CampaignManagement';

// Admin Dashboard Pages
import AdminHome from '../pages/PageForAdmin/Dashboard/AdminHome';
import UserManagement from '../pages/PageForAdmin/Dashboard/UserManagement';
import ReportManagement from '../pages/PageForAdmin/Dashboard/ReportManagement';

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

      {/* Employer Dashboard */}
      <Route
        path="/dashboard"
        element={
          user?.account_type === 'employer' ? (
            <DashboardLayout user={user} />
          ) : (
            <Navigate to="/LoginEmployer" />
          )
        }
      >
        <Route index element={<DashboardHome />} />
        <Route path="cv-management" element={<CVManagement />} />
        <Route path="recruitment-reports" element={<RecruitmentReports />} />
        <Route path="campaign-management" element={<CampaignManagement />} />
      </Route>

      {/* Admin Dashboard */}
      <Route
        path="/admin"
        element={
          user?.account_type === 'admin' ? (
            <AdminLayout user={user} />
          ) : (
            <Navigate to="/login" />
          )
        }
      >
        <Route index element={<AdminHome />} />
        <Route path="user-management" element={<UserManagement />} />
        <Route path="report-management" element={<ReportManagement />} />
      </Route>

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
