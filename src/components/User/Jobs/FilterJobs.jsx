import React, { useState } from 'react';
import { Select, Button, Typography, Popconfirm, Space } from 'antd';
import {
  AppstoreOutlined,
  ClockCircleOutlined,
  DollarOutlined,
  BookOutlined,
  FilterOutlined,
  CalendarOutlined,
  ToolOutlined,
  CloseCircleOutlined,
} from '@ant-design/icons';
import industries from '../../data/industries';

const { Option } = Select;

const FilterJobs = () => {
  const [filters, setFilters] = useState({
    industry: undefined,
    level: undefined,
    experience: undefined,
    salary: undefined,
    education: undefined,
    jobType: undefined,
    postedDate: undefined,
  });

  const resetAllFilters = () => {
    setFilters({
      industry: undefined,
      level: undefined,
      experience: undefined,
      salary: undefined,
      education: undefined,
      jobType: undefined,
      postedDate: undefined,
    });
  };

  const dropdownStyle = {
    width: 180,
    height: '40px', // Chiều cao đồng nhất
    color: 'black',
  };

  return (
    <div
      style={{
        background: 'linear-gradient(to right, #020024, #cc0a9d)',
        padding: '10px 20px',
        borderBottom: '1px solid #ddd',
        position: 'sticky',
        top: 64,
        zIndex: 1000,
      }}
    >
      <Space wrap size="middle" align="center" style={{ justifyContent: 'center', gap: '8px' }}>
        {/* Ngành nghề */}
        <Select
          placeholder={<><AppstoreOutlined /> Ngành nghề</>}
          value={filters.industry}
          onChange={(value) => setFilters({ ...filters, industry: value })}
          style={dropdownStyle}
          allowClear
        >
          {industries.map((industry) => (
            <Option key={industry.id_industry} value={industry.industry_name}>
              {industry.industry_name}
            </Option>
          ))}
        </Select>

        {/* Cấp bậc */}
        <Select
          placeholder={<><ToolOutlined /> Cấp bậc</>}
          value={filters.level}
          onChange={(value) => setFilters({ ...filters, level: value })}
          style={dropdownStyle}
          allowClear
        >
          {['Thực tập', 'Nhân viên', 'Trưởng phòng', 'Giám đốc'].map((level) => (
            <Option key={level} value={level}>
              {level}
            </Option>
          ))}
        </Select>

        {/* Kinh nghiệm */}
        <Select
          placeholder={<><ClockCircleOutlined /> Kinh nghiệm</>}
          value={filters.experience}
          onChange={(value) => setFilters({ ...filters, experience: value })}
          style={dropdownStyle}
          allowClear
        >
          {['Dưới 1 năm', '1-2 năm', '2-5 năm', '5-10 năm', 'Trên 10 năm'].map((exp) => (
            <Option key={exp} value={exp}>
              {exp}
            </Option>
          ))}
        </Select>

        {/* Mức lương */}
        <Select
          placeholder={<><DollarOutlined /> Mức lương</>}
          value={filters.salary}
          onChange={(value) => setFilters({ ...filters, salary: value })}
          style={dropdownStyle}
          allowClear
        >
          {['Dưới 5 triệu', '5-10 triệu', '10-20 triệu', '20+ triệu'].map((salary) => (
            <Option key={salary} value={salary}>
              {salary}
            </Option>
          ))}
        </Select>

        {/* Học vấn */}
        <Select
          placeholder={<><BookOutlined /> Học vấn</>}
          value={filters.education}
          onChange={(value) => setFilters({ ...filters, education: value })}
          style={dropdownStyle}
          allowClear
        >
          {['Trung học phổ thông', 'Cao đẳng', 'Đại học', 'Thạc sĩ', 'Tiến sĩ'].map((edu) => (
            <Option key={edu} value={edu}>
              {edu}
            </Option>
          ))}
        </Select>

        {/* Loại công việc */}
        <Select
          placeholder={<><FilterOutlined /> Loại công việc</>}
          value={filters.jobType}
          onChange={(value) => setFilters({ ...filters, jobType: value })}
          style={dropdownStyle}
          allowClear
        >
          {['Toàn thời gian', 'Bán thời gian', 'Thực tập'].map((type) => (
            <Option key={type} value={type}>
              {type}
            </Option>
          ))}
        </Select>

        {/* Đăng trong */}
        <Select
          placeholder={<><CalendarOutlined /> Đăng trong</>}
          value={filters.postedDate}
          onChange={(value) => setFilters({ ...filters, postedDate: value })}
          style={dropdownStyle}
          allowClear
        >
          {['Hôm nay', '3 ngày', '1 tuần', '2 tuần', '1 tháng'].map((date) => (
            <Option key={date} value={date}>
              {date}
            </Option>
          ))}
        </Select>

        {/* Nút Xóa tất cả */}
        <Popconfirm
          title="Bạn có chắc chắn muốn xóa tất cả bộ lọc không?"
          onConfirm={resetAllFilters}
          okText="Có"
          cancelText="Không"
        >
          <Button
            danger
            icon={<CloseCircleOutlined />}
            style={{
              borderRadius: '8px',
              height: '40px',
              width: '150px',
              lineHeight: '1.2',
            }}
          >
            Xóa tất cả
          </Button>
        </Popconfirm>
      </Space>
    </div>
  );
};

export default FilterJobs;
