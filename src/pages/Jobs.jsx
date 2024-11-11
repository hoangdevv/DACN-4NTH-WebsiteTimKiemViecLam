import React, { useState } from 'react';
import HeaderJobs from '../components/Jobs/headerJobs';
import FilterJobs from '../components/Jobs/FilterJobs'
import Footer from '../components/common/Footer';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min.js';
import { Dropdown } from 'react-bootstrap';

const JobList = [
  {
    title: 'Senior React Developer',
    company: 'Tech Corp',
    salary: '8 - 12 triệu',
    location: 'Hà Nội',
    image: 'https://via.placeholder.com/150',
    date: '2024-01-01',
  },
  {
    title: 'Marketing Manager',
    company: 'Brand Solutions',
    salary: '8 - 15 triệu',
    location: 'Hồ Chí Minh',
    image: 'https://via.placeholder.com/150',
    date: '2024-01-03',
  },
  {
    title: 'Senior React Developer',
    company: 'Tech Corp',
    salary: '8 - 12 triệu',
    location: 'Hà Nội',
    image: 'https://via.placeholder.com/150',
    date: '2024-04-05',
  },
  {
    title: 'Senior React Developer',
    company: 'Tech Corp',
    salary: '8 - 12 triệu',
    location: 'Hà Nội',
    image: 'https://via.placeholder.com/150',
    date: '2024-02-07',
  },
  {
    title: 'Senior React Developer',
    company: 'Tech Corp',
    salary: '8 - 12 triệu',
    location: 'Hà Nội',
    image: 'https://via.placeholder.com/150',
    date: '2024-07-02',
  },
  {
    title: 'Senior React Developer',
    company: 'Tech Corp',
    salary: '8 - 12 triệu',
    location: 'Hà Nội',
    image: 'https://via.placeholder.com/150',
    date: '2024-12-24',
  },
];
const Jobs = () => {
  const [sortOption, setSortOption] = useState('Phù hợp nhất');

  return (
    <div>
      <HeaderJobs />
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
                      <li className="breadcrumb-item active text-start" aria-current="page">
                          Tuyển Dụng 15,954 Việc Làm Mới Nhất Năm 2024
                      </li>
                  </ol>
              </nav>
              <h2 className="fw-bold">Tuyển dụng <span className="text-primary">15,954</span> việc làm mới nhất năm <span className="text-primary">2024</span></h2>
            </div>
            {/* Sort */}
            <div className="d-flex justify-content-end mt-3 mb-3">
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
            {/* Job List */}
            {JobList.map((job, index) => (
              <div className="container py-3 border rounded d-flex flex-column mb-3" style={{ height: '150px' }}>
                <div className="d-flex align-items-center h-100">
                  <img src={job.image} alt={job.title} className="me-3" style={{ width: '80px', height: '80px', objectFit: 'cover' }} />
                  <div className="flex-grow-1 text-start">
                    <h3 className="fw-bold fs-5">{job.title}</h3>
                    <p className="mb-1 fs-6">{job.company}</p>
                    <p className="text-muted fs-6">{job.salary} | {job.location}</p>
                  </div>
                  
                </div>
              </div>
            ))}
          </div>
          <div className="col-sm-3">
            <div className="bg-white p-4 rounded-xl border border-solid border-[#EFEFF0] shadow-none lg:shadow mb-6"></div>

          </div>
        </div>
      </div>
      <Footer />
    </div>
  );
};

export default Jobs; 