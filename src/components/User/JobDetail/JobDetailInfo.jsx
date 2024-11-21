import React from "react";
import PropTypes from "prop-types";
import { useNavigate } from "react-router-dom";
import { EnvironmentOutlined, DollarOutlined, CalendarOutlined, HeartOutlined, UserOutlined } from "@ant-design/icons";
import jobs from "../../data/jobs";
import employers from "../../data/employers";

const JobDetailInfo = ({ jobId }) => {
  const job = jobs.find((job) => job.id_job === jobId);
  const employer = employers.find((emp) => emp.id_employer === job?.id_employer);
  const navigate = useNavigate();

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
            className="rounded me-3"
            width="60"
            height="60"
          />
          <div>
            <h5 className="card-title mb-1">{job.title}</h5>
            <p
              className="mb-1"
              onClick={() => navigate(`/companyDetail/${employer?.id_employer}`)}
              style={{
                cursor: "pointer",
                background: "linear-gradient(to right, rgb(2, 0, 36), rgb(204, 10, 157))",
                WebkitBackgroundClip: "text",
                WebkitTextFillColor: "transparent",
                textAlign: "left",
              }}
            >
              {employer ? employer.company_name : "Tên công ty không xác định"}
            </p>
          </div>
        </div>
        <ul className="list-unstyled text-start">
          <li className="mb-2">
            <EnvironmentOutlined className="me-2" style={{ color: "rgb(204, 10, 157)" }} />
            {job.location}
          </li>
          <li className="mb-2">
            <DollarOutlined className="me-2" style={{ color: "rgb(204, 10, 157)" }} />
            {job.salary}
          </li>
          <li className="mb-2">
            <UserOutlined className="me-2" style={{ color: "rgb(204, 10, 157)" }} />
            {job.experience_levels}
          </li>
          <li>
            <CalendarOutlined className="me-2" style={{ color: "rgb(204, 10, 157)" }} />
            Ngày đăng tuyển {job.created_at} | Hết hạn: {job.expiry_date}
          </li>
        </ul>
        <div className="mt-4 d-flex gap-3">
          <button
            className="btn btn-primary"
            style={{
              background: "linear-gradient(to right, rgb(2, 0, 36), rgb(204, 10, 157))",
              border: "none",
              minWidth: "150px",
              height: "50px",
              fontSize: "1rem",
            }}
          >
            Nộp đơn ngay
          </button>
          <button
            className="btn btn-outline-secondary d-flex align-items-center justify-content-center"
            style={{
              color: "rgb(204, 10, 157)",
              borderColor: "rgb(204, 10, 157)",
              minWidth: "150px",
              height: "50px",
              fontSize: "1rem",
            }}
          >
            <HeartOutlined className="me-2" />
            Lưu
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
