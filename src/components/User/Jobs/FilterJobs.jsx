import React, { useState } from 'react';
import { Dropdown, DropdownButton, Button } from 'react-bootstrap';
import 'bootstrap-icons/font/bootstrap-icons.css';
import industries from '../../data/industries';

const FilterJobs = () => {
  // State for each filter dropdown
  const [selectedIndustry, setSelectedIndustry] = useState("Ngành nghề");
  const [selectedLevel, setSelectedLevel] = useState("Cấp bậc");
  const [selectedExperience, setSelectedExperience] = useState("Kinh nghiệm");
  const [selectedSalary, setSelectedSalary] = useState("Mức lương");
  const [selectedEducation, setSelectedEducation] = useState("Học vấn");
  const [selectedJobType, setSelectedJobType] = useState("Loại công việc");
  const [selectedPostedDate, setSelectedPostedDate] = useState("Đăng trong");

  // Function to reset a specific filter to its default value
  const resetFilter = (setFilter, defaultText) => {
    setFilter(defaultText);
  };

  // Function to reset all filters to their default values
  const resetAllFilters = () => {
    setSelectedIndustry("Ngành nghề");
    setSelectedLevel("Cấp bậc");
    setSelectedExperience("Kinh nghiệm");
    setSelectedSalary("Mức lương");
    setSelectedEducation("Học vấn");
    setSelectedJobType("Loại công việc");
    setSelectedPostedDate("Đăng trong");
  };

  return (
    <div 
      className="d-flex justify-content-center gap-3 flex-wrap py-2 sticky-top bg-white border-bottom" 
      style={{ top: '66.8px', zIndex: '1010' }} 
    >
      {/* Ngành nghề */}
      <DropdownButton
        id="dropdown-industry"
        title={<><i className="bi bi-grid-3x3-gap-fill me-2"></i>{selectedIndustry}</>}
        variant="outline-secondary"
        className="rounded-pill px-3 py-1 text-dark"
      >
        {industries.map((industry) => (
          <Dropdown.Item 
            key={industry.id_industry} 
            onClick={() => setSelectedIndustry(industry.industry_name)}
          >
            {industry.industry_name}
          </Dropdown.Item>
        ))}
        <Dropdown.Divider />
        <Dropdown.Item 
          onClick={() => resetFilter(setSelectedIndustry, "Ngành nghề")} 
          className="text-end text-danger d-flex justify-content-end align-items-center"
        >
          <i className="bi bi-trash me-1"></i> Xóa lọc
        </Dropdown.Item>
      </DropdownButton>
      
      {/* Các dropdown khác */}
      {/* Cấp bậc */}
      <DropdownButton
        id="dropdown-level"
        title={<><i className="bi bi-layers-fill me-2"></i>{selectedLevel}</>}
        variant="outline-secondary"
        className="rounded-pill px-3 py-1 text-dark"
      >
        <Dropdown.Item onClick={() => setSelectedLevel("Thực tập")}>Thực tập</Dropdown.Item>
        <Dropdown.Item onClick={() => setSelectedLevel("Nhân viên")}>Nhân viên</Dropdown.Item>
        <Dropdown.Item onClick={() => setSelectedLevel("Trưởng phòng")}>Trưởng phòng</Dropdown.Item>
        <Dropdown.Item onClick={() => setSelectedLevel("Giám đốc")}>Giám đốc</Dropdown.Item>
        <Dropdown.Divider />
        <Dropdown.Item 
          onClick={() => resetFilter(setSelectedLevel, "Cấp bậc")} 
          className="text-end text-danger d-flex justify-content-end align-items-center"
        >
          <i className="bi bi-trash me-1"></i> Xóa lọc
        </Dropdown.Item>
      </DropdownButton>

      {/* Kinh nghiệm */}
      <DropdownButton
        id="dropdown-experience"
        title={<><i className="bi bi-clock-fill me-2"></i>{selectedExperience}</>}
        variant="outline-secondary"
        className="rounded-pill px-3 py-1 text-dark"
      >
        <Dropdown.Item onClick={() => setSelectedExperience("Dưới 1 năm")}>Dưới 1 năm</Dropdown.Item>
        <Dropdown.Item onClick={() => setSelectedExperience("1-2 năm")}>1-2 năm</Dropdown.Item>
        <Dropdown.Item onClick={() => setSelectedExperience("2-5 năm")}>2-5 năm</Dropdown.Item>
        <Dropdown.Item onClick={() => setSelectedExperience("5-10 năm")}>5-10 năm</Dropdown.Item>
        <Dropdown.Item onClick={() => setSelectedExperience("Trên 10 năm")}>Trên 10 năm</Dropdown.Item>
        <Dropdown.Divider />
        <Dropdown.Item 
          onClick={() => resetFilter(setSelectedExperience, "Kinh nghiệm")} 
          className="text-end text-danger d-flex justify-content-end align-items-center"
        >
          <i className="bi bi-trash me-1"></i> Xóa lọc
        </Dropdown.Item>
      </DropdownButton>

      {/* Mức lương */}
      <DropdownButton
        id="dropdown-salary"
        title={<><i className="bi bi-geo-alt-fill me-2"></i>{selectedSalary}</>}
        variant="outline-secondary"
        className="rounded-pill px-3 py-1 text-dark"
      >
        <Dropdown.Item onClick={() => setSelectedSalary("Dưới 5 triệu")}>Dưới 5 triệu</Dropdown.Item>
        <Dropdown.Item onClick={() => setSelectedSalary("5-10 triệu")}>5-10 triệu</Dropdown.Item>
        <Dropdown.Item onClick={() => setSelectedSalary("10-20 triệu")}>10-20 triệu</Dropdown.Item>
        <Dropdown.Item onClick={() => setSelectedSalary("20+ triệu")}>20+ triệu</Dropdown.Item>
        <Dropdown.Divider />
        <Dropdown.Item 
          onClick={() => resetFilter(setSelectedSalary, "Mức lương")} 
          className="text-end text-danger d-flex justify-content-end align-items-center"
        >
          <i className="bi bi-trash me-1"></i> Xóa lọc
        </Dropdown.Item>
      </DropdownButton>

      {/* Học vấn */}
      <DropdownButton
        id="dropdown-education"
        title={<><i className="bi bi-mortarboard-fill me-2"></i>{selectedEducation}</>}
        variant="outline-secondary"
        className="rounded-pill px-3 py-1 text-dark"
      >
        <Dropdown.Item onClick={() => setSelectedEducation("Trung học phổ thông")}>Trung học phổ thông</Dropdown.Item>
        <Dropdown.Item onClick={() => setSelectedEducation("Cao đẳng")}>Cao đẳng</Dropdown.Item>
        <Dropdown.Item onClick={() => setSelectedEducation("Đại học")}>Đại học</Dropdown.Item>
        <Dropdown.Item onClick={() => setSelectedEducation("Thạc sĩ")}>Thạc sĩ</Dropdown.Item>
        <Dropdown.Item onClick={() => setSelectedEducation("Tiến sĩ")}>Tiến sĩ</Dropdown.Item>
        <Dropdown.Divider />
        <Dropdown.Item 
          onClick={() => resetFilter(setSelectedEducation, "Học vấn")} 
          className="text-end text-danger d-flex justify-content-end align-items-center"
        >
          <i className="bi bi-trash me-1"></i> Xóa lọc
        </Dropdown.Item>
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
        <Dropdown.Divider />
        <Dropdown.Item 
          onClick={() => resetFilter(setSelectedJobType, "Loại công việc")} 
          className="text-end text-danger d-flex justify-content-end align-items-center"
        >
          <i className="bi bi-trash me-1"></i> Xóa lọc
        </Dropdown.Item>
      </DropdownButton>

      {/* Đăng trong */}
      <DropdownButton
        id="dropdown-posted-date"
        title={<><i className="bi bi-calendar3-fill me-2"></i>{selectedPostedDate}</>}
        variant="outline-secondary"
        className="rounded-pill px-3 py-1 text-dark"
      >
        <Dropdown.Item onClick={() => setSelectedPostedDate("Hôm nay")}>Hôm nay</Dropdown.Item>
        <Dropdown.Item onClick={() => setSelectedPostedDate("3 ngày")}>3 ngày</Dropdown.Item>
        <Dropdown.Item onClick={() => setSelectedPostedDate("1 Tuần")}>1 tuần</Dropdown.Item>
        <Dropdown.Item onClick={() => setSelectedPostedDate("2 Tuần")}>2 Tuần</Dropdown.Item>
        <Dropdown.Item onClick={() => setSelectedPostedDate("1 Tháng")}>1 Tháng</Dropdown.Item>
        <Dropdown.Divider />
        <Dropdown.Item 
          onClick={() => resetFilter(setSelectedPostedDate, "Đăng trong")} 
          className="text-end text-danger d-flex justify-content-end align-items-center"
        >
          <i className="bi bi-trash me-1"></i> Xóa lọc
        </Dropdown.Item>
      </DropdownButton>

      {/* Nút Xóa tất cả */}
      <span
        className="text-danger d-flex align-items-center"
        onClick={resetAllFilters}
        style={{ cursor: 'pointer' }} 
      >
        <i className="bi bi-trash me-2"></i> Xóa tất cả
      </span>
    </div>
  );
};

export default FilterJobs;
