import React, { useState } from 'react';
import { Dropdown } from 'react-bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min.js';
import '../../styles/ListJob.css';
import Jobs from '../data/jobs';
import { Link } from 'react-router-dom';


const ListJobs = () => {
  const [sortOption, setSortOption] = useState('Phù hợp nhất');

  return (
      <>
      {/* Sort and Job Count */}
      <div className="sort-count-container">
          <div>
              <span className="job-count">{Jobs.length}</span> việc làm
          </div>
          <div>
              <Dropdown className="border rounded p-2">
                  <Dropdown.Toggle variant="light" id="dropdownSort" className="text-dark fw-semibold">
                      Sắp xếp: {sortOption}
                  </Dropdown.Toggle>
                  <Dropdown.Menu>
                      <Dropdown.Item onClick={() => setSortOption('Phù hợp nhất')}>Phù hợp nhất</Dropdown.Item>
                      <Dropdown.Item onClick={() => setSortOption('Mới nhất')}>Mới nhất</Dropdown.Item>
                      <Dropdown.Item onClick={() => setSortOption('Lương cao nhất')}>Lương cao nhất</Dropdown.Item>
                  </Dropdown.Menu>
              </Dropdown>
          </div>
      </div>

      {/* Job List */}
      {Jobs.map((job, index) => (
          <Link to={`/jobDetail/${job.id_job}`} key={index} className="container py-3 border rounded d-flex flex-column mb-3 job-container">
              <div className="d-flex align-items-center h-100">
                  <img src="https://via.placeholder.com/150" alt={job.title} className="me-3" style={{ width: '80px', height: '80px', objectFit: 'cover' }} />
                  <div className="flex-grow-1 text-start">
                      <h3 className="fw-bold fs-5">{job.title}</h3>
                      <p className="mb-1 fs-6">{job.company}</p>
                      <div className="d-flex justify-content-between align-items-center">
                          <p className="text-muted fs-6 mb-0">{job.salary} | {job.location}</p>
                          <div className="job-info d-flex align-items-center">
                              <i className="bi bi-heart me-1 job-save"></i> Lưu
                          </div>
                      </div>
                  </div>
              </div>
          </Link>
      ))}
      </>
  );
}

export default ListJobs;
