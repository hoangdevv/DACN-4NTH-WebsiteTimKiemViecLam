import React from 'react';
import PropTypes from 'prop-types';
import jobs from '../data/jobs';
import employers from '../data/employers';
import { FaMapMarkerAlt, FaMoneyBillAlt, FaBriefcase, FaCalendarAlt, FaHeart } from 'react-icons/fa';

const JobDetailInfo = ({ jobId }) => {
  const job = jobs.find((job) => job.id_job === jobId);
  const employer = employers.find((emp) => emp.id_employer === job?.id_employer);

  if (!job) {
    return (
      <div className="text-center mt-4">
        <p>Không tìm thấy công việc.</p>
      </div>
    );
  }

  return (
    <div className="card mb-4 shadow-sm">
      <div className="card-body">
        <div className="d-flex align-items-center mb-4">
          <img
            src="https://via.placeholder.com/60"
            alt="Company Logo"
            className="me-3 rounded"
            style={{ width: '60px', height: '60px' }}
          />
          <div className="text-start">
            <h5 className="card-title mb-1">{job.title}</h5>
            <p className="text-muted mb-1">{employer ? employer.company_name : "Tên công ty không xác định"}</p>
          </div>
        </div>
        <div className="mt-3">
          <div className="d-flex align-items-center mb-2">
            <FaMapMarkerAlt className="me-2" />
            <span>{job.location}</span>
          </div>
          <div className="d-flex align-items-center mb-2">  
            <FaMoneyBillAlt className="me-2" />
            <span className='text-primary'>{job.salary}</span>
          </div>
          <div className="d-flex align-items-center mb-2">
            <FaBriefcase className="me-2" />
            <span>{job.experience_levels}</span>
          </div>
          <div className="d-flex align-items-center">
            <FaCalendarAlt className="me-2" />
            <span>Ngày đăng tuyển {job.created_at} | Hết hạn: {job.expiry_date}</span>
          </div>
        </div>
        <div className="mt-4 d-flex align-items-center">
          <button
            className="btn btn-primary me-3"
            style={{ minWidth: '150px', height: '50px', fontSize: '1rem' }}
          >
            Nộp đơn ngay
          </button>
          <button
            className="btn btn-outline-secondary d-flex align-items-center justify-content-center"
            style={{ minWidth: '150px', height: '50px', fontSize: '1rem' }}
          >
            <FaHeart className="me-2" /> Lưu
          </button>
        </div>
      </div>
    </div>
  );
};

JobDetailInfo.propTypes = {
  jobId: PropTypes.string.isRequired,
};

export default JobDetailInfo;
