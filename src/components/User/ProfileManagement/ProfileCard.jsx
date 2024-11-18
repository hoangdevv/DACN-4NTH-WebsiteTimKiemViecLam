import React from 'react';
import { Card, Button } from 'react-bootstrap';
import PropTypes from 'prop-types';

const ProfileCard = ({ profile, onEdit }) => {
  return (
    <Card className="shadow-lg rounded mb-4">
      <Card.Body>
        <div className="d-flex justify-content-between align-items-center mb-3">
          <div>
            <Card.Title className="mb-2"><strong>{profile.full_name}</strong></Card.Title>
            <Card.Subtitle className="text-muted">{profile.email}</Card.Subtitle>
          </div>
          <img
            src={profile.avatar}
            alt="Avatar"
            className="rounded-circle"
            style={{ height: '100px', width: '100px', objectFit: 'cover' }}
          />
        </div>
        <Card.Text><strong>Địa chỉ:</strong> {profile.address}</Card.Text>
        <Card.Text><strong>Ngày sinh:</strong> {profile.birthday}</Card.Text>
        <Card.Text><strong>Số điện thoại:</strong> {profile.phone}</Card.Text>
        <Button variant="primary" onClick={onEdit} className="mt-2">Chỉnh sửa thông tin</Button>
      </Card.Body>
    </Card>
  );
};

ProfileCard.propTypes = {
  profile: PropTypes.object.isRequired,  // Xác thực kiểu dữ liệu profile
  onEdit: PropTypes.func.isRequired,  // Hàm chỉnh sửa thông tin
};

export default ProfileCard;
