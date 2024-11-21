import React, { useState } from 'react';
import { List, Typography, Space, Breadcrumb, Select, Pagination } from 'antd';
import jobs from '../../data/jobs';
import { Link } from 'react-router-dom';

const { Text } = Typography;
const { Option } = Select;

const ListJobs = () => {
  const [sortOption, setSortOption] = useState('Phù hợp nhất');
  const [currentPage, setCurrentPage] = useState(1);
  const pageSize = 10;

  const handleSortChange = (value) => {
    setSortOption(value);
  };

  const handlePageChange = (page) => {
    setCurrentPage(page);
  };

  // Lấy danh sách job dựa trên trang hiện tại
  const paginatedJobs = jobs.slice((currentPage - 1) * pageSize, currentPage * pageSize);

  return (
    <div style={{ padding: '20px' }}>
      {/* Breadcrumb */}
      <div
        style={{
          marginBottom: '20px',
          padding: '15px',
          border: '1px solid #f0f0f0',
          borderRadius: '8px',
          backgroundColor: '#ffffff',
        }}
      >
        <Breadcrumb>
          <Breadcrumb.Item>
            <Link to="/" style={{ color: 'rgb(204, 10, 157)', fontWeight: '500' }}>
              Trang Chủ
            </Link>
          </Breadcrumb.Item>
          <Breadcrumb.Item style={{ color: '#333', fontWeight: '500' }}>
            Tuyển dụng
          </Breadcrumb.Item>
        </Breadcrumb>
        <h2 style={{ margin: '10px 0 0', fontWeight: 'bold' }}>
          Tuyển dụng <span style={{ color: 'rgb(204, 10, 157)' }}>15,954</span> việc làm mới nhất
          năm <span style={{ color: 'rgb(204, 10, 157)' }}>2024</span>
        </h2>
      </div>

      {/* Sort and Job Count */}
      <div
        style={{
          display: 'flex',
          justifyContent: 'space-between',
          alignItems: 'center',
          marginBottom: '20px',
        }}
      >
        <div>
          <span style={{ fontWeight: 'bold', fontSize: '16px' }}>{jobs.length}</span> việc làm
        </div>
        <div>
          <Select
            defaultValue="Phù hợp nhất"
            onChange={handleSortChange}
            style={{ width: 200 }}
            size="large"
          >
            <Option value="Phù hợp nhất">Phù hợp nhất</Option>
            <Option value="Mới nhất">Mới nhất</Option>
            <Option value="Lương cao nhất">Lương cao nhất</Option>
          </Select>
        </div>
      </div>

      {/* Job List */}
      <List
        dataSource={paginatedJobs}
        renderItem={(job) => (
          <Link
            to={`/jobDetail/${job.id_job}`}
            style={{
              textDecoration: 'none',
              display: 'block',
              border: '1px solid #f0f0f0',
              borderRadius: '8px',
              padding: '15px',
              marginBottom: '15px',
              backgroundColor: '#ffffff',
              transition: 'transform 0.2s, box-shadow 0.2s',
            }}
            onMouseEnter={(e) => {
              e.currentTarget.style.boxShadow = '0 4px 8px rgba(0, 0, 0, 0.2)';
              e.currentTarget.style.transform = 'scale(1.02)';
            }}
            onMouseLeave={(e) => {
              e.currentTarget.style.boxShadow = 'none';
              e.currentTarget.style.transform = 'scale(1)';
            }}
          >
            <div style={{ display: 'flex', alignItems: 'center', gap: '15px' }}>
              {/* Job Thumbnail */}
              <img
                src="https://via.placeholder.com/150"
                alt={job.title}
                style={{
                  width: '80px',
                  height: '80px',
                  borderRadius: '5px',
                  objectFit: 'cover',
                }}
              />
              {/* Job Info */}
              <div style={{ flexGrow: 1 }}>
                <h3 style={{ margin: 0, fontWeight: 'bold', fontSize: '18px', color: '#333', textAlign: 'left'}}>
                  {job.title}
                </h3>
                <div
                  style={{
                    display: 'flex',
                    justifyContent: 'space-between',
                    alignItems: 'center',
                    marginTop: '10px',
                  }}
                >
                  {/* Salary & Location */}
                  <Space size="small">
                    <Text style={{ color: '#cc0a9d', fontWeight: 'bold' }}>{job.salary}</Text>
                    <Text type="secondary">|</Text>
                    <Text type="secondary">{job.location}</Text>
                  </Space>
                  {/* Save Icon */}
                  <div style={{ display: 'flex', alignItems: 'center', gap: '5px', color: '#cc0a9d' }}>
                    <i className="bi bi-heart"></i>
                    <span>Lưu</span>
                  </div>
                </div>
              </div>
            </div>
          </Link>
        )}
      />

      {/* Pagination */}
      <Pagination
        current={currentPage}
        pageSize={pageSize}
        total={jobs.length}
        onChange={handlePageChange}
        style={{ marginTop: '20px', textAlign: 'center', alignItems: 'center', justifyContent: 'end'}}
      />
    </div>
  );
};

export default ListJobs;
