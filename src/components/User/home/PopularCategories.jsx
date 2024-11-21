import React from 'react';
import { Card, Col, Row, Typography } from 'antd';

const { Title, Text } = Typography;

const categories = [
  {
    title: 'Công nghệ thông tin',
    count: 1200,
    icon: '💻',
  },
  {
    title: 'Marketing',
    count: 800,
    icon: '📢',
  },
  {
    title: 'Tài chính - Kế toán',
    count: 600,
    icon: '💰',
  },
  {
    title: 'Kinh doanh',
    count: 950,
    icon: '📈',
  },
  {
    title: 'Thiết kế',
    count: 400,
    icon: '🎨',
  },
  {
    title: 'Nhân sự',
    count: 350,
    icon: '👥',
  },
];

const PopularCategories = () => {
  return (
    <section
      style={{
        padding: '50px 20px',
        background: '#f9f9f9',
      }}
    >
      <div style={{ maxWidth: '1200px', margin: '0 auto' }}>
        <Title
          level={2}
          style={{
            marginBottom: '30px',
            fontWeight: '700',
            textAlign: 'center',
          }}
        >
          Ngành nghề phổ biến
        </Title>
        <Row gutter={[24, 24]}>
          {categories.map((category) => (
            <Col key={category.title} xs={24} sm={12} md={8}>
              <Card
                hoverable
                style={{
                  borderRadius: '10px',
                  overflow: 'hidden',
                  transition: 'all 0.3s',
                  boxShadow: '0 4px 6px rgba(0, 0, 0, 0.1)',
                }}
                bodyStyle={{
                  padding: '20px',
                  display: 'flex',
                  alignItems: 'center',
                }}
              >
                <div
                  style={{
                    fontSize: '3rem',
                    marginRight: '15px',
                    color: '#cc0a9d',
                  }}
                >
                  {category.icon}
                </div>
                <div style={{ textAlign: 'left' }}>
                  <Title
                    level={4}
                    style={{
                      marginBottom: '5px',
                      fontWeight: '600',
                      color: '#333',
                    }}
                  >
                    {category.title}
                  </Title>
                  <Text
                    style={{
                      color: '#555',
                      fontSize: '1rem',
                    }}
                  >
                    <span style={{ color: '#cc0a9d', fontWeight: 'bold' }}>
                      {category.count}
                    </span>{' '}
                    việc làm
                  </Text>
                </div>
              </Card>
            </Col>
          ))}
        </Row>
      </div>
    </section>
  );
};

export default PopularCategories;
