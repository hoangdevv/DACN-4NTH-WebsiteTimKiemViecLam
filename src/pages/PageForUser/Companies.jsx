import React, { useState } from 'react';
import Footer from '../../components/User/common/Footer';
import FilterCompanies from '../../components/User/Companies/FilterCompanies';
import CompanyList from '../../components/User/Companies/CompanyList';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap-icons/font/bootstrap-icons.css';

const Companies = () => {
  const [searchTerm, setSearchTerm] = useState("");

  const handleSearchChange = (e) => {
    setSearchTerm(e.target.value);
  };

  return (
    <div>
      <div className="container mt-4">
        <div className="row mb-4 align-items-center">
          <div className="col-lg-9">
            <h3 className="mb-0">Nhà tuyển dụng hàng đầu</h3>
          </div>
          <div className="col-lg-3">
            <div className="input-group">
              <span className="input-group-text bg-white border-0">
                <i className="bi bi-search"></i>
              </span>
              <input
                type="text"
                className="form-control rounded-pill"
                placeholder="Tìm công ty..."
                aria-label="Tìm công ty"
                value={searchTerm}
                onChange={handleSearchChange}
              />
            </div>
          </div>
        </div>
        <div className="row">
          <div className="col-lg-3">
            <FilterCompanies />
          </div>
          <div className="col-lg-9">
            <CompanyList searchTerm={searchTerm} />
          </div>
        </div>
      </div>
      <Footer />
    </div>
  );
};

export default Companies;
