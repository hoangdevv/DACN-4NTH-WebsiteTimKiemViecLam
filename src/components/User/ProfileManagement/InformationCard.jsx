import React from 'react';
import { Card } from 'react-bootstrap';
import PropTypes from 'prop-types';

const InformationCard = ({ profile }) => {
  return (
    <Card className="shadow-sm mb-4">
      <Card.Body>
        <Card.Title>Thông tin bổ sung</Card.Title>
        <Card.Text><strong>Vị trí:</strong> {profile.location}</Card.Text>
        <Card.Text><strong>Giới tính:</strong> {profile.sex === 1 ? 'Nam' : 'Nữ'}</Card.Text>
      </Card.Body>
    </Card>
  );
};

InformationCard.propTypes = {
  profile: PropTypes.object.isRequired,  // Xác thực kiểu dữ liệu profile
};

export default InformationCard;
