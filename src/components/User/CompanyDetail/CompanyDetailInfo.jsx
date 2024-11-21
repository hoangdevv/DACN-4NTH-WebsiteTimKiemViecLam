import React from 'react';
import PropTypes from 'prop-types';
import { Card, Avatar, Typography, Row, Col } from 'antd';
import { EnvironmentOutlined } from '@ant-design/icons';
import employers from '../../data/employers';

const { Title, Text } = Typography;

const CompanyDetailInfo = ({ companyId }) => {
    const company = employers.find(emp => emp.id_employer === companyId);

    if (!company) {
        return <div>Không tìm thấy thông tin công ty.</div>;
    }

    return (
        <div className="container mt-4">
            <Card
                cover={
                    <img 
                        alt="Company Background"
                        src="https://via.placeholder.com/1200x400"
                        style={{ height: '250px', objectFit: 'cover' }}
                    />
                }
                bordered={false}
                style={{ borderRadius: '8px', overflow: 'hidden' }}
            >
                <Row gutter={[16, 16]} align="middle" style={{ marginTop: '-50px' }}>
                    {/* Avatar hình vuông */}
                    <Col>
                        <Avatar
                            src="https://via.placeholder.com/104"
                            size={120}
                            style={{
                                borderRadius: '8px', 
                                border: '5px solid white',
                                boxShadow: '0px 4px 6px rgba(0,0,0,0.1)',
                                width: '120px',
                                height: '120px',
                            }}
                        />
                    </Col>
                    {/* Thông tin công ty */}
                    <Col style={{paddingTop: '30px'}}>
                        <Title level={3} style={{ margin: 0, textAlign: 'left'}}>
                            {company.company_name}
                        </Title>
                        <Text type="secondary" style={{ fontSize: '16px' }}>
                            <EnvironmentOutlined style={{ marginRight: '8px' }} />
                            {company.company_address}
                        </Text>
                    </Col>
                </Row>
            </Card>
        </div>
    );
};

CompanyDetailInfo.propTypes = {
    companyId: PropTypes.string.isRequired,
};

export default CompanyDetailInfo;
