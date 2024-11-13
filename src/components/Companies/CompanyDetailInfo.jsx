import React from 'react';
import { useParams } from 'react-router-dom';
import employers from '../data/employers';
import 'bootstrap/dist/css/bootstrap.min.css';

const CompanyDetailInfo = () => {
  const { id } = useParams();
  const company = employers.find(emp => emp.id_employer === id);

  if (!company) {
    return <div>Không tìm thấy thông tin công ty.</div>;
  }

  return (
    <div className="container mt-4">
      <div className="card border-0 shadow-sm rounded">
        {/* Background Image */}
        <div className="position-relative">
          <img 
            src="https://via.placeholder.com/1200x400" 
            className="card-img-top" 
            alt="Company Background" 
            style={{ height: '250px', objectFit: 'cover' }} 
          />
        </div>

        {/* Overlay với avatar và thông tin công ty */}
        <div className="card-body">
          <div className="row align-items-start">
            {/* Cột bên trái chứa avatar */}
            <div className="col-auto position-relative" style={{ top: '-52px' }}>
              <img 
                src="https://via.placeholder.com/104" 
                alt="Company Logo" 
                style={{
                  width: '104px',
                  height: '104px',
                  borderRadius: '8px',
                  border: '5px solid white',
                  objectFit: 'cover'
                }} 
              />
            </div>

            {/* Cột bên phải chứa tên công ty và địa chỉ */}
            <div className="col text-start">
              <h5 className="fw-bold mt-3 fs-2">{company.company_name}</h5>
              <p className="text-muted mb-1">
                <i className="bi bi-geo-alt-fill me-2 "></i>
                {company.company_address}
              </p>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default CompanyDetailInfo;
