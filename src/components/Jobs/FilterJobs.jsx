import React, { useState } from 'react';
import { Dropdown, DropdownButton } from 'react-bootstrap';
import 'bootstrap-icons/font/bootstrap-icons.css';

const FilterJobs = () => {
  // State for each filter dropdown
  const [selectedIndustry, setSelectedIndustry] = useState("Ngành nghề");
  const [selectedLevel, setSelectedLevel] = useState("Cấp bậc");
  const [selectedExperience, setSelectedExperience] = useState("Kinh nghiệm");
  const [selectedSalary, setSelectedSalary] = useState("Mức lương");
  const [selectedEducation, setSelectedEducation] = useState("Học vấn");
  const [selectedJobType, setSelectedJobType] = useState("Loại công việc");
  const [selectedPostedDate, setSelectedPostedDate] = useState("Đăng trong");

  return (
    <div 
      className="d-flex justify-content-center gap-3 flex-wrap py-2 sticky-top bg-light" 
      style={{ top: '60px', zIndex: '1020' }}
    >
      {/* Ngành nghề */}
      <DropdownButton
        id="dropdown-industry"
        title={<><i className="bi bi-grid-3x3-gap-fill me-2"></i>{selectedIndustry}</>}
        variant="outline-secondary"
        className="rounded-pill px-3 py-1 text-dark"
      >
        <Dropdown.Item onClick={() => setSelectedIndustry("Option 1")}>Option 1</Dropdown.Item>
        <Dropdown.Item onClick={() => setSelectedIndustry("Option 2")}>Option 2</Dropdown.Item>
        <Dropdown.Item onClick={() => setSelectedIndustry("Option 3")}>Option 3</Dropdown.Item>
      </DropdownButton>

      {/* Cấp bậc */}
      <DropdownButton
        id="dropdown-level"
        title={<><i className="bi bi-layers-fill me-2"></i>{selectedLevel}</>}
        variant="outline-secondary"
        className="rounded-pill px-3 py-1 text-dark"
      >
        <Dropdown.Item onClick={() => setSelectedLevel("Nhân viên")}>Nhân viên</Dropdown.Item>
        <Dropdown.Item onClick={() => setSelectedLevel("Trưởng phòng")}>Trưởng phòng</Dropdown.Item>
        <Dropdown.Item onClick={() => setSelectedLevel("Giám đốc")}>Giám đốc</Dropdown.Item>
      </DropdownButton>

      {/* Kinh nghiệm */}
      <DropdownButton
        id="dropdown-experience"
        title={<><i className="bi bi-clock-fill me-2"></i>{selectedExperience}</>}
        variant="outline-secondary"
        className="rounded-pill px-3 py-1 text-dark"
      >
        <Dropdown.Item onClick={() => setSelectedExperience("0-1 năm")}>0-1 năm</Dropdown.Item>
        <Dropdown.Item onClick={() => setSelectedExperience("2-3 năm")}>2-3 năm</Dropdown.Item>
        <Dropdown.Item onClick={() => setSelectedExperience("3+ năm")}>3+ năm</Dropdown.Item>
      </DropdownButton>

      {/* Mức lương */}
      <DropdownButton
        id="dropdown-salary"
        title={<><i className="bi bi-geo-alt-fill me-2"></i>{selectedSalary}</>}
        variant="outline-secondary"
        className="rounded-pill px-3 py-1 text-dark"
      >
        <Dropdown.Item onClick={() => setSelectedSalary("5-10 triệu")}>5-10 triệu</Dropdown.Item>
        <Dropdown.Item onClick={() => setSelectedSalary("10-20 triệu")}>10-20 triệu</Dropdown.Item>
        <Dropdown.Item onClick={() => setSelectedSalary("20+ triệu")}>20+ triệu</Dropdown.Item>
      </DropdownButton>

      {/* Học vấn */}
      <DropdownButton
        id="dropdown-education"
        title={<><i className="bi bi-mortarboard-fill me-2"></i>{selectedEducation}</>}
        variant="outline-secondary"
        className="rounded-pill px-3 py-1 text-dark"
      >
        <Dropdown.Item onClick={() => setSelectedEducation("Trung cấp")}>Trung cấp</Dropdown.Item>
        <Dropdown.Item onClick={() => setSelectedEducation("Cao đẳng")}>Cao đẳng</Dropdown.Item>
        <Dropdown.Item onClick={() => setSelectedEducation("Đại học")}>Đại học</Dropdown.Item>
      </DropdownButton>

      {/* Loại công việc */}
      <DropdownButton
        id="dropdown-job-type"
        title={<><i className="bi bi-briefcase-fill me-2"></i>{selectedJobType}</>}
        variant="outline-secondary"
        className="rounded-pill px-3 py-1 text-dark"
      >
        <Dropdown.Item onClick={() => setSelectedJobType("Toàn thời gian")}>Toàn thời gian</Dropdown.Item>
        <Dropdown.Item onClick={() => setSelectedJobType("Bán thời gian")}>Bán thời gian</Dropdown.Item>
        <Dropdown.Item onClick={() => setSelectedJobType("Thực tập")}>Thực tập</Dropdown.Item>
      </DropdownButton>

      {/* Đăng trong */}
      <DropdownButton
        id="dropdown-posted-date"
        title={<><i className="bi bi-calendar3-fill me-2"></i>{selectedPostedDate}</>}
        variant="outline-secondary"
        className="rounded-pill px-3 py-1 text-dark"
      >
        <Dropdown.Item onClick={() => setSelectedPostedDate("24 giờ qua")}>24 giờ qua</Dropdown.Item>
        <Dropdown.Item onClick={() => setSelectedPostedDate("7 ngày qua")}>7 ngày qua</Dropdown.Item>
        <Dropdown.Item onClick={() => setSelectedPostedDate("30 ngày qua")}>30 ngày qua</Dropdown.Item>
      </DropdownButton>
    </div>
  );
};

export default FilterJobs;
