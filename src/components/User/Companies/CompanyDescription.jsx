import React from 'react';
import PropTypes from 'prop-types';
import { Dropdown } from 'react-bootstrap';
import { Link, useNavigate } from 'react-router-dom';
import employers from '../../data/employers';
import jobs from '../../data/jobs';

const CompanyDescription = ({ companyId }) => {
    const company = employers.find(emp => emp.id_employer === companyId);
    const companyJobs = jobs.filter(job => job.id_employer === companyId);
    const navigate = useNavigate();

    if (!company) {
        return <div>Không tìm thấy thông tin công ty.</div>;
    }

    return (
        <div className="text-start border rounded p-4 mt-3 mb-3">
            {/* Phần "Về Công Ty" */}
            <h6 className="fs-5 text-primary mb-3">Về công ty</h6>
            <p>{company.company_description}</p>

            {/* Phần "Việc Đang Tuyển" */}
            <div className="d-flex justify-content-between align-items-center mt-4">
                <h6 className="fs-5 text-primary">Việc đang tuyển</h6>
                <Link to="#" className="text-primary d-flex align-items-center fs-6">
                    <i className="bi bi-bell me-1 "></i> Gửi thông báo cho tìm kiếm này
                </Link>
            </div>
            
            {/* Số lượng công việc và sắp xếp */}
            <div className="d-flex justify-content-between align-items-center mt-2">
                <div className="d-flex align-items-center">
                    <span className="text-warning fw-bold me-2 fs-6">{companyJobs.length}</span>
                    <span>việc làm</span>
                </div>
                <Dropdown>
                    <Dropdown.Toggle variant="light" id="dropdown-sort" className="border-0">
                        Sắp xếp: Mới cập nhật
                    </Dropdown.Toggle>
                    <Dropdown.Menu>
                        <Dropdown.Item href="#">Sắp xếp theo A-Z</Dropdown.Item>
                        <Dropdown.Item href="#">Sắp xếp theo Z-A</Dropdown.Item>
                        <Dropdown.Item href="#">Mới cập nhật</Dropdown.Item>
                    </Dropdown.Menu>
                </Dropdown>
            </div>

            {/* Danh sách việc làm */}
            <div className="mt-3">
                {companyJobs.map((job) => (
                    <div 
                        key={job.id_job} 
                        className="p-3 mb-3 border rounded d-flex align-items-center cursor-pointer"
                        onClick={() => navigate(`/jobDetail/${job.id_job}`)}
                        style={{ cursor: 'pointer' }}
                    >
                        <img src="https://via.placeholder.com/50" alt="Logo" className="me-3" />
                        <div className="flex-grow-1">
                            <h6 className="mb-1">{job.title}</h6>
                            <small className="text-muted">{company.company_name}</small>
                            <p className="mb-0">{job.salary} | {job.job_level}</p>
                        </div>
                        <div className="ms-auto text-muted text-end">
                            <small>Cập nhật: {job.updated_at}</small>
                            <i className="bi bi-heart ms-2"></i>
                        </div>
                    </div>
                ))}
            </div>
        </div>
    );
};

CompanyDescription.propTypes = {
    companyId: PropTypes.string.isRequired,
};

export default CompanyDescription;
