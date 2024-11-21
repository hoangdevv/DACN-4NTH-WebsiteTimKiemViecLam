import React, { useState } from 'react';
import FilterJobs from '../../components/User/Jobs/FilterJobs';
import ListJobs from '../../components/User/Jobs/ListJobs';
import Footer from '../../components/User/common/Footer';
import 'bootstrap/dist/css/bootstrap.min.css';

const Jobs = () => {
  return (
    <div>
      <FilterJobs />
      <div className="container text-center mt-4">
        <div className="row">
          <div className="col-sm-9">
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
