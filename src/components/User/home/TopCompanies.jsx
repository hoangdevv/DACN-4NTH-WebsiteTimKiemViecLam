import React from 'react';
import { Card, Typography, Button, Tooltip } from 'antd';
import { EnvironmentOutlined } from '@ant-design/icons';
import employers from '../../data/employers'; 

const { Title, Text } = Typography;

const TopCompanies = () => {
  const displayedCompanies = employers.slice(0, 8); 

  return (
    <section style={{ padding: '50px 20px', background: '#f9f9f9' }}>
      <div style={{ maxWidth: '1200px', margin: '0 auto', textAlign: 'center' }}>
        <Title level={2} style={{ marginBottom: '30px', fontWeight: '700', color: '#333' }}>
          Công ty hàng đầu
        </Title>
        <div
          style={{
            display: 'grid',
            gridTemplateColumns: 'repeat(auto-fit, minmax(280px, 1fr))',
            gap: '20px',
          }}
        >
          {displayedCompanies.map((employer) => (
            <Card
              key={employer.id_employer}
              hoverable
              style={{
                borderRadius: '10px',
                overflow: 'hidden',
                boxShadow: '0 4px 8px rgba(0, 0, 0, 0.1)',
                transition: 'all 0.3s',
                textAlign: 'center',
                background: '#fff',
              }}
              bodyStyle={{
                padding: '20px',
              }}
            >
              {/* Logo */}
              <div style={{ marginBottom: '15px' }}>
                <img
                  src={employer.logo || 'https://via.placeholder.com/80'} 
                  alt={employer.company_name}
                  style={{
                    width: '80px',
                    height: '80px',
                    borderRadius: '50%',
                    objectFit: 'cover',
                    border: '2px solid #ddd',
                    padding: '5px',
                  }}
                />
              </div>
              {/* Tên Công Ty */}
              <Tooltip title={employer.company_name}>
                <Title
                  level={4}
                  style={{
                    marginBottom: '10px',
                    whiteSpace: 'nowrap',
                    overflow: 'hidden',
                    textOverflow: 'ellipsis',
                    color: '#333',
                  }}
                >
                  {employer.company_name}
                </Title>
              </Tooltip>
              {/* Địa Chỉ */}
              <Tooltip title={employer.company_address}>
                <Text
                  style={{
                    display: 'block',
                    color: '#555',
                    marginBottom: '5px',
                    whiteSpace: 'nowrap',
                    overflow: 'hidden',
                    textOverflow: 'ellipsis',
                  }}
                >
                  <EnvironmentOutlined style={{ marginRight: '5px', color: '#888' }} />
                  {employer.company_address}
                </Text>
              </Tooltip>
              {/* Website */}
              <Tooltip title={employer.company_website}>
                <Text
                  style={{
                    display: 'block',
                    color: '#777',
                    whiteSpace: 'nowrap',
                    overflow: 'hidden',
                    textOverflow: 'ellipsis',
                  }}
                >
                  🌐 {employer.company_website}
                </Text>
              </Tooltip>
            </Card>
          ))}
        </div>
        <Button
          type="primary"
          href="/companies"
          style={{
            marginTop: '20px',
            background: 'linear-gradient(to right, #020024, #cc0a9d)',
            border: 'none',
            borderRadius: '5px',
            padding: '10px 20px',
            color: '#fff',
            fontWeight: 'bold',
            fontSize: '16px',
            textDecoration: 'none',
          }}
        >
          Xem thêm
        </Button>
      </div>
    </section>
  );
};

export default TopCompanies;
