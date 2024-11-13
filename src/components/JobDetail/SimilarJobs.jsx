import React from 'react';
import PropTypes from 'prop-types';
import jobs from '../data/jobs';
import { FaMapMarkerAlt, FaDollarSign } from 'react-icons/fa';

const SimilarJobs = ({ currentJobId }) => {
  const currentJob = jobs.find(job => job.id_job === currentJobId);

  if (!currentJob) {
    return <p>Không tìm thấy công việc hiện tại.</p>;
  }

  // Lọc các công việc có id_profession trùng với công việc hiện tại
  const similarJobs = jobs
    .filter(job => job.id_profession === currentJob.id_profession && job.id_job !== currentJobId)
    .slice(0, 10); 

  return (
    <div className="card shadow-sm p-3 mb-4">
      <h5 className="mb-3">Việc tương tự</h5>
      {similarJobs.map((job) => (
        <div key={job.id_job} className="mb-3">
          <hr className="my-2" />
          <div className="d-flex align-items-start">
            <img
              src="https://via.placeholder.com/40" 
              alt={job.title}
              className="me-3"
              style={{ width: '40px', height: '40px', borderRadius: '5px' }}
            />
            <div className="text-start">
              <h6 className="mb-1" style={{ fontSize: '0.9rem', fontWeight: 'bold' }}>
                {job.title}
              </h6>
              <p className="mb-1" style={{ fontSize: '0.85rem', color: '#666' }}>
                {job.company_name || 'Tên công ty'}
              </p>
              <div className="d-flex align-items-center mb-1" style={{ fontSize: '0.8rem', color: '#666' }}>
                <FaMapMarkerAlt className="me-1" /> {job.location}
              </div>
              <div className="d-flex align-items-center" style={{ fontSize: '0.8rem', color: '#007bff' }}>
                <FaDollarSign className="me-1" /> {job.salary || 'Thương lượng'}
              </div>
            </div>
          </div>
        </div>
      ))}
    </div>
  );
};

SimilarJobs.propTypes = {
  currentJobId: PropTypes.string.isRequired,
};

export default SimilarJobs;
