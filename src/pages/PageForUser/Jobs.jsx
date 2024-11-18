import React, { useState } from 'react';
import FilterJobs from '../../components/User/Jobs/FilterJobs';
import ListJobs from '../../components/User/Jobs/ListJobs';
import Footer from '../../components/User/common/Footer';
import 'bootstrap/dist/css/bootstrap.min.css';

const Jobs = () => {
  const [sortOption, setSortOption] = useState('Phù hợp nhất');

  return (
    <div>
      <FilterJobs />
      <div className="container text-center mt-4">
        <div className="row">
          <div className="col-sm-9">
            <div className="container py-3 border rounded">
              <nav aria-label="breadcrumb">
                <ol className="breadcrumb">
                  <li className="breadcrumb-item">
                    <a href="/">Trang Chủ</a>
                  </li>
                  <li className="breadcrumb-item active" aria-current="page">
                    <h2 className="fw-bold">
                      Tuyển dụng <span className="text-primary">15,954</span> việc làm mới nhất năm <span className="text-primary">2024</span>
                    </h2>
                  </li>
                </ol>
              </nav>
            </div>
            <ListJobs />
          </div>
          <div className="col-sm-3">
            <div className="bg-white p-4 rounded-xl border shadow-none mb-6">
              {/* Thêm nội dung sidebar nếu cần */}
            </div>
          </div>
        </div>
      </div>
      <Footer />
    </div>
  );
};

export default Jobs;
