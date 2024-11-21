import React from 'react';
import PropTypes from 'prop-types';
import { Typography, Card, Dropdown, Space, Button, Avatar } from 'antd';
import { BellOutlined, HeartOutlined } from '@ant-design/icons';
import { Link, useNavigate } from 'react-router-dom';
import employers from '../../data/employers';
import jobs from '../../data/jobs';

const { Title, Text } = Typography;

const CompanyDescription = ({ companyId }) => {
  const company = employers.find(emp => emp.id_employer === companyId);
  const companyJobs = jobs.filter(job => job.id_employer === companyId);
  const navigate = useNavigate();

  if (!company) {
    return <div>Không tìm thấy thông tin công ty.</div>;
  }

  const sortMenu = (
    <Dropdown.Menu>
      <Dropdown.Item key="1">Sắp xếp theo A-Z</Dropdown.Item>
      <Dropdown.Item key="2">Sắp xếp theo Z-A</Dropdown.Item>
      <Dropdown.Item key="3">Mới cập nhật</Dropdown.Item>
    </Dropdown.Menu>
  );

  return (
    <div className="mt-4 mb-4">
      {/* Về công ty */}
      <Card bordered={false} className="mb-4">
        <Title level={5} style={{ color: 'rgb(204, 10, 157)', textAlign: 'left' }}>Về công ty</Title>
        <Text style={{ textAlign: 'left', display: 'block' }}>{company.company_description}</Text>
      </Card>

      {/* Việc đang tuyển */}
      <Card bordered={false}>
        <div className="d-flex justify-content-between align-items-center">
          <Title
            level={5}
            style={{ color: 'rgb(204, 10, 157)', marginBottom: 0 }}
          >
            Việc đang tuyển
          </Title>
          <Link
            to="#"
            style={{
              color: 'rgb(204, 10, 157)',
              textDecoration: 'none',
              fontWeight: 'bold',
            }}
          >
            <Space align="center">
              <BellOutlined />
              Gửi thông báo cho tìm kiếm này
            </Space>
          </Link>
        </div>

        {/* Số lượng công việc và dropdown sắp xếp */}
        <div className="d-flex justify-content-between align-items-center mt-3">
          <div>
            <Text strong className="text-warning">{companyJobs.length}</Text>{' '}
            <span style={{ textAlign: 'left' }}>việc làm</span>
          </div>
          <Dropdown overlay={sortMenu} placement="bottomRight">
            <Button>Sắp xếp: Mới cập nhật</Button>
          </Dropdown>
        </div>

        {/* Danh sách việc làm */}
        <div className="mt-4">
          {companyJobs.map((job) => (
            <Card
              key={job.id_job}
              className="mb-3"
              hoverable
              onClick={() => navigate(`/jobDetail/${job.id_job}`)}
              style={{ cursor: 'pointer' }}
            >
              <div className="d-flex align-items-center">
                <Avatar
                  size={50}
                  src="https://via.placeholder.com/50"
                  alt="Logo"
                  className="me-3"
                />
                <div className="flex-grow-1">
                  <Title level={5} className="mb-0">{job.title}</Title>
                  <Text type="secondary">{company.company_name}</Text>
                  <p className="mb-0">{job.salary} | {job.job_level}</p>
                </div>
                <div className="text-end">
                  <Text type="secondary" style={{ fontSize: '12px' }}>Cập nhật: {job.updated_at}</Text>
                  <HeartOutlined style={{ marginLeft: '8px' }} />
                </div>
              </div>
            </Card>
          ))}
        </div>
      </Card>
    </div>
  );
};

CompanyDescription.propTypes = {
  companyId: PropTypes.string.isRequired,
};

export default CompanyDescription;
