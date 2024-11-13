import React, { useEffect, useState } from 'react';
import PropTypes from 'prop-types';
import jobs from '../data/jobs';
import employers from '../data/employers';
import industries from '../data/industries';
import professions from '../data/professions';
import '../../styles/JobDescription.css'
import { FaMoneyBillWave, FaMapMarkerAlt, FaUserTie, FaGraduationCap } from 'react-icons/fa';

const JobDescription = ({ jobId }) => {
    const [activeSection, setActiveSection] = useState('description');
    const job = jobs.find((job) => job.id_job === jobId);
    const employer = employers.find((emp) => emp.id_employer === job?.id_employer);
    const industry = industries.find((ind) => ind.id_industry === job?.id_industry);
    const profession = professions.find((prof) => prof.id_profession === job?.id_profession);
  
    useEffect(() => {
      if (!job) return; // Nếu không tìm thấy công việc, ngăn thực hiện bất kỳ logic nào
  
      const sections = document.querySelectorAll('section');
      const observer = new IntersectionObserver(
        (entries) => {
          entries.forEach((entry) => {
            if (entry.isIntersecting) {
              setActiveSection(entry.target.id);
            }
          });
        },
        { rootMargin: '-123.6px 0px -70% 0px', threshold: 0.5 }
      );
  
      sections.forEach((section) => {
        observer.observe(section);
      });
  
      return () => {
        sections.forEach((section) => {
          observer.unobserve(section);
        });
      };
    }, [job]);
  
    if (!job) {
      return <p>Không tìm thấy công việc.</p>;
    }
  
    return (
      <div className="card mb-4 shadow-sm">
        <div className="card-body">
          {/* Thanh điều hướng dạng ul và li */}
          <div className="bg-white py-2 sticky-top" style={{ borderBottom: '1px solid #ddd', top: '66.8px', zIndex: '1010' }}>
            <ul className="nav justify-content-start">
              <li className="nav-item">
                <a
                  className={`nav-link ${activeSection === 'description' ? 'active' : ''}`}
                  href="#description"
                >
                  Mô tả
                </a>
              </li>
              <li className="nav-item">
                <a
                  className={`nav-link ${activeSection === 'skills' ? 'active' : ''}`}
                  href="#skills"
                >
                  Kỹ năng yêu cầu
                </a>
              </li>
              <li className="nav-item">
                <a
                  className={`nav-link ${activeSection === 'details' ? 'active' : ''}`}
                  href="#details"
                >
                  Chi tiết công việc
                </a>
              </li>
              <li className="nav-item">
                <a
                  className={`nav-link ${activeSection === 'contact' ? 'active' : ''}`}
                  href="#contact"
                >
                  Liên hệ
                </a>
              </li>
            </ul>
          </div>
  
          {/* Thông tin chi tiết công việc */}
          <div className="container mt-4 text-start">
            <div className="row mb-4">
              <div className="col-md-6">
                <div className="d-flex align-items-center mb-2">
                  <FaMapMarkerAlt className="me-2 text-muted" />
                  <span><strong>Địa điểm:</strong> {job.location}</span>
                </div>
                <div className="d-flex align-items-center mb-2">
                  <FaMoneyBillWave className="me-2 text-muted" />
                  <span><strong>Mức lương:</strong> {job.salary}</span>
                </div>
                <div className="d-flex align-items-center mb-2">
                  <FaUserTie className="me-2 text-muted" />
                  <span><strong>Chức vụ:</strong> {job.job_level}</span>
                </div>
                <div className="d-flex align-items-center mb-2">
                  <FaGraduationCap className="me-2 text-muted" />
                  <span><strong>Yêu cầu học vấn:</strong> {job.education_levels}</span>
                </div>
              </div>
              <div className="col-md-6">
                <div className="d-flex align-items-center mb-2">
                  <span><strong>Ngành:</strong> {industry?.industry_name || "Chưa có thông tin ngành"}</span>
                </div>
                <div className="d-flex align-items-center mb-2">
                  <span><strong>Nghề nghiệp:</strong> {profession?.profession_name || "Chưa có thông tin nghề nghiệp"}</span>
                </div>
                <div className="d-flex align-items-center mb-2">
                  <span><strong>Thời gian làm việc:</strong> {job.job_type}</span>
                </div>
                <div className="d-flex align-items-center mb-2">
                  <span><strong>Yêu cầu kinh nghiệm:</strong> {job.experience_levels}</span>
                </div>
              </div>
            </div>
  
            {/* Nội dung các phần */}
            <section id="description" className="mb-5" style={{ scrollMarginTop: '125px' }}>
              <h4>Mô tả công việc</h4>
              <p>{job.description}</p>
            </section>
  
            <section id="skills" className="mb-5" style={{ scrollMarginTop: '125px' }}>
              <h4>Kỹ năng yêu cầu</h4>
              <p>{job.skills || 'Chưa có thông tin kỹ năng yêu cầu.'}</p>
            </section>
  
            <section id="details" className="mb-5" style={{ scrollMarginTop: '125px' }}>
              <h4>Chi tiết công việc</h4>
              <p>{job.details || 'Chưa có thông tin chi tiết công việc.'}</p>
            </section>
  
            <section id="contact" className="mb-5" style={{ scrollMarginTop: '125px' }}>
              <h4>Liên hệ</h4>
              <p>
                <strong>Địa chỉ:</strong> {employer?.company_address || 'Chưa có thông tin địa chỉ.'}
                <br />
                <strong>Số điện thoại:</strong> {employer?.company_phone || 'Chưa có thông tin số điện thoại.'}
                <br />
                <strong>Website:</strong> <a href={employer?.company_website} target="_blank" rel="noopener noreferrer">{employer?.company_website || 'Chưa có thông tin website.'}</a>
              </p>
            </section>
          </div>
        </div>
      </div>
    );
  };
  
  JobDescription.propTypes = {
    jobId: PropTypes.string.isRequired,
  };
  
  export default JobDescription;