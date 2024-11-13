import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Home from './pages/PageForUser/Home';
import NotFound from './pages/NotFound';
import Login from './pages/PageForUser/LoginForUser/Login';
import Register from './pages/PageForUser/LoginForUser/Register';
import Jobs from './pages/PageForUser/Jobs';
import JobDetail from './pages/PageForUser/JobDetail';
import Companies from './pages/PageForUser/Companies';
import CompanyDetail from './pages/PageForUser/CompanyDetail'
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min.js';

function App() {
  return (
    <Router>
      <div className="min-h-screen bg-gray-50">
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="*" element={<NotFound />} />
          <Route path="/jobs" element={<Jobs />} />
          <Route path="/companies" element={<Companies />} />
          <Route path="/login" element={<Login />} />
          <Route path="/register" element={<Register />} />
          <Route path="/jobDetail/:id" element={<JobDetail />} />
          <Route path="/companyDetail/:id" element={<CompanyDetail />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
