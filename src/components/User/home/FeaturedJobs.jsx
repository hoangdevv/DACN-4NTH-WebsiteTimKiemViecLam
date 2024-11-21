import React from 'react';
import { Card, Carousel, Typography, Button } from 'antd';
import { LeftOutlined, RightOutlined } from '@ant-design/icons';
import jobs from '../../data/jobs';

const { Title, Text } = Typography;

const FeaturedJobs = () => {
  const carouselRef = React.useRef();

  const next = () => {
    carouselRef.current.next();
  };

  const prev = () => {
    carouselRef.current.prev();
  };

  return (
    <section style={{ padding: '50px 20px', background: '#f9f9f9' }}>
      <div style={{ maxWidth: '1200px', margin: '0 auto', textAlign: 'center', position: 'relative' }}>
        <Title level={2} style={{ marginBottom: '30px', fontWeight: '700' }}>
          Việc làm nổi bật
        </Title>

        {/* Mũi tên điều hướng */}
        <div
          style={{
            position: 'absolute',
            top: '50%',
            left: '-20px',
            transform: 'translateY(-50%)',
            zIndex: 10,
          }}
        >
          <Button
            shape="circle"
            icon={<LeftOutlined />}
            onClick={prev}
            style={{
              backgroundColor: '#fff',
              border: '1px solid #ddd',
              boxShadow: '0 2px 6px rgba(0, 0, 0, 0.2)',
            }}
          />
        </div>
        <div
          style={{
            position: 'absolute',
            top: '50%',
            right: '-20px',
            transform: 'translateY(-50%)',
            zIndex: 10,
          }}
        >
          <Button
            shape="circle"
            icon={<RightOutlined />}
            onClick={next}
            style={{
              backgroundColor: '#fff',
              border: '1px solid #ddd',
              boxShadow: '0 2px 6px rgba(0, 0, 0, 0.2)',
            }}
          />
        </div>

        <Carousel
          autoplay
          autoplaySpeed={2000} 
          dots
          effect="scrollx"
          ref={carouselRef}
        >
          {[...Array(Math.ceil(jobs.length / 6))].map((_, index) => (
            <div key={index}>
              <div style={{ display: 'flex', flexWrap: 'wrap', gap: '20px', justifyContent: 'center' }}>
                {jobs.slice(index * 6, (index + 1) * 6).map((job) => (
                  <Card
                    key={job.id_job}
                    hoverable
                    style={{
                      width: 300,
                      borderRadius: '10px',
                      overflow: 'hidden',
                      transition: 'all 0.3s',
                      boxShadow: '0 4px 6px rgba(0, 0, 0, 0.1)',
                      textAlign: 'left',
                    }}
                    bodyStyle={{ padding: '20px' }}
                  >
                    <div style={{ display: 'flex', alignItems: 'center', marginBottom: '15px' }}>
                      <img
                        src={job.logo || 'https://via.placeholder.com/48'}
                        alt={job.title}
                        style={{
                          width: '48px',
                          height: '48px',
                          marginRight: '15px',
                          borderRadius: '50%',
                          objectFit: 'cover',
                        }}
                      />
                      <div>
                        <Title
                          level={4}
                          style={{
                            margin: 0,
                            fontSize: '1.1rem',
                            fontWeight: '600',
                          }}
                        >
                          {job.title}
                        </Title>
                        <Text style={{ color: '#777' }}>{job.company}</Text>
                      </div>
                    </div>
                    <div>
                      <Text style={{ display: 'block', marginBottom: '5px' }}>
                        📍 {job.location}
                      </Text>
                      <Text style={{ display: 'block', marginBottom: '5px' }}>
                        💰 {job.salary}
                      </Text>
                      <Text style={{ display: 'block', marginBottom: '10px' }}>
                        ⏰ {job.job_type}
                      </Text>
                    </div>
                    <Button
                      type="primary"
                      style={{
                        background: 'linear-gradient(to right, #020024, #cc0a9d)',
                        border: 'none',
                        borderRadius: '5px',
                        width: '100%',
                      }}
                    >
                      Ứng tuyển ngay
                    </Button>
                  </Card>
                ))}
              </div>
            </div>
          ))}
        </Carousel>
      </div>
    </section>
  );
};

export default FeaturedJobs;
