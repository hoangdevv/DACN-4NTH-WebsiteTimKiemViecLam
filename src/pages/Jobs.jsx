import React from 'react';
import Header from '../components/common/Header';
import Footer from '../components/common/Footer';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min.js';
const Jobs = () => {
  return (
    <div>
      <Header />
      <div className="container mx-auto px-4 py-24">
        <h1 className="text-3xl font-bold mb-8">Danh sách việc làm</h1>
        {/* Thêm nội dung trang Jobs ở đây */}
      </div>
      <Footer />
    </div>
  );
};

export default Jobs; 