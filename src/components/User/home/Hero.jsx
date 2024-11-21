import React from 'react';
import SearchBar from './SearchBar';
import { Typography, Space } from 'antd';

const { Title, Text } = Typography;

const Hero = () => {
  return (
    <div
      style={{
        background: 'linear-gradient(to right, #020024, #cc0a9d)',
        padding: '100px 20px',
        color: '#fff',
        textAlign: 'center',
      }}
    >
      <div style={{ maxWidth: '1200px', margin: '0 auto' }}>
        {/* Phần tiêu đề */}
        <Space direction="vertical" size="large" style={{ width: '100%' }}>
          <Title
            level={1}
            style={{
              color: '#fff',
              fontSize: '3rem',
              lineHeight: '1.2',
              fontWeight: '700',
            }}
          >
            Tìm việc làm mơ ước của bạn
          </Title>
          <Text style={{ fontSize: '1.2rem', opacity: 0.9, color: '#fff' }}>
            Hơn 10,000 việc làm đang chờ đợi bạn
          </Text>
        </Space>

        {/* Thanh tìm kiếm */}
        <SearchBar />

        {/* Xu hướng hiện nay */}
        <div style={{ marginTop: '30px', textAlign: 'center' }}>
          <Text
            style={{
              fontSize: '1rem',
              fontWeight: 'bold',
              textTransform: 'uppercase',
              color: '#fff',
              letterSpacing: '1px',
            }}
          >
            Xu hướng hiện nay:
          </Text>
          <div
            style={{
              marginTop: '15px',
              display: 'flex',
              flexWrap: 'wrap',
              justifyContent: 'center',
              gap: '10px',
            }}
          >
            {[
              'Java',
              'ReactJS',
              '.NET',
              'Tester',
              'PHP',
              'Business Analyst',
              'NodeJS',
              'Manager',
            ].map((trend) => (
              <span
                key={trend}
                style={{
                  backgroundColor: '#fff',
                  color: '#020024',
                  padding: '5px 15px',
                  borderRadius: '15px',
                  fontSize: '0.9rem',
                  fontWeight: '500',
                  transition: 'all 0.3s ease',
                  cursor: 'pointer',
                }}
                onMouseEnter={(e) => {
                  e.target.style.backgroundColor = '#cc0a9d';
                  e.target.style.color = '#fff';
                }}
                onMouseLeave={(e) => {
                  e.target.style.backgroundColor = '#fff';
                  e.target.style.color = '#020024';
                }}
              >
                {trend}
              </span>
            ))}
          </div>
        </div>
      </div>
    </div>
  );
};

export default Hero;
