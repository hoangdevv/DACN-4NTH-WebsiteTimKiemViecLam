import React from 'react';
import PropTypes from 'prop-types';
import { Link } from 'react-router-dom';
import { Card, Row, Col, Typography } from 'antd';
import employers from '../../data/employers';
import jobs from '../../data/jobs'; // Import dữ liệu jobs

const { Title, Text } = Typography;

const CompanyList = ({ searchTerm }) => {
  const filteredCompanies = employers.filter((company) =>
    company.company_name.toLowerCase().includes(searchTerm.toLowerCase())
  );

  return (
    <Row gutter={[16, 16]}>
      {filteredCompanies.map((company) => {
        // Lọc các công việc của công ty này dựa trên id_employer
        const companyJobs = jobs.filter((job) => job.id_employer === company.id_employer);
        
        return (
          <Col
            key={company.id_employer}
            xs={24}
            sm={12}
            md={8}
            lg={6}
            style={{ marginBottom: '24px' }}
          >
            <Link to={`/companyDetail/${company.id_employer}`} style={{ textDecoration: 'none' }}>
              <Card
                hoverable
                cover={
                  <div className="d-flex justify-content-center" style={{ height: '150px' }}>
                    <img
                      alt="Company Logo"
                      src={`https://via.placeholder.com/150`}
                      className="img-fluid"
                      style={{
                        maxHeight: '100%',
                        maxWidth: '100%',
                        objectFit: 'cover',
                        borderRadius: '8px',
                      }}
                    />
                  </div>
                }
                className="company-card h-100"
                style={{
                  paddingTop: '16px',
                }}
              >
                <Title level={5} className="company-name text-truncate">
                  {company.company_name}
                </Title>
                {/* Đếm số lượng công việc của công ty */}
                <Text className="jobs-available" style={{ color: "rgb(204, 10, 157)" }}>
                  {companyJobs.length} việc đang tuyển
                </Text>
                <p
                  className="location text-muted mb-0 text-truncate"
                  style={{ overflow: 'hidden', whiteSpace: 'nowrap' }}
                  title={company.company_address}
                >
                  {company.company_address}
                </p>
              </Card>
            </Link>
          </Col>
        );
      })}
    </Row>
  );
};

CompanyList.propTypes = {
  searchTerm: PropTypes.string.isRequired,
};

export default CompanyList;
