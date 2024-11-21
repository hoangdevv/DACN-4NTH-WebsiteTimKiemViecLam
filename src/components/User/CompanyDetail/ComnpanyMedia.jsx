import React from 'react';
import PropTypes from 'prop-types';
import { Typography, Card, Space, Divider } from 'antd';
import { GlobalOutlined, FacebookOutlined, LinkedinOutlined, YoutubeOutlined, TwitterOutlined, GoogleOutlined } from '@ant-design/icons';
import employers from '../../data/employers';

const { Title, Link, Text } = Typography;

const CompanyMedia = ({ companyId }) => {
  const company = employers.find(emp => emp.id_employer === companyId);

  if (!company) {
    return <div>Không tìm thấy thông tin công ty.</div>;
  }

  return (
    <Card bordered={false} className="mt-4">
      {/* Link Website */}
      <Title level={5} style={{ color: 'rgb(204, 10, 157)' }}>Website</Title>
      <Link href={company.company_website} target="_blank" rel="noopener noreferrer">
        <Space>
          <GlobalOutlined style={{color: 'rgb(204, 10, 157)'}}/>
          <Text>{company.company_website}</Text>
        </Space>
      </Link>

      <Divider />

      {/* Social Media Icons */}
      <Title level={5} style={{ color: 'rgb(204, 10, 157)' }}>Theo dõi</Title>
      <Space size="large">
        <FacebookOutlined style={{ fontSize: '24px', color: '#3b5998' }} />
        <LinkedinOutlined style={{ fontSize: '24px', color: '#0077b5' }} />
        <YoutubeOutlined style={{ fontSize: '24px', color: '#FF0000' }} />
        <TwitterOutlined style={{ fontSize: '24px', color: '#1DA1F2' }} />
        <GoogleOutlined style={{ fontSize: '24px', color: '#DB4437' }} />
      </Space>

      <Divider />

      {/* Mini Map */}
      <Title level={5} style={{ color: 'rgb(204, 10, 157)' }}>Bản đồ công ty</Title>
      <div className="mt-2">
        <iframe
          title="Company Location"
          src={`https://www.google.com/maps?q=${encodeURIComponent(company.company_address)}&output=embed`}
          width="100%"
          height="250"
          style={{ border: 0 }}
          allowFullScreen=""
          loading="lazy"
        ></iframe>
      </div>
    </Card>
  );
};

CompanyMedia.propTypes = {
  companyId: PropTypes.string.isRequired,
};

export default CompanyMedia;
