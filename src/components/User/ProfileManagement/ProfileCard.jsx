import React from 'react';
import { Card, Button } from 'react-bootstrap';
import PropTypes from 'prop-types';

const ProfileCard = ({ profile, onEditProfile, onEditAvatar }) => {
  return (
    <Card>
      <Card.Body>
        {/* Hiển thị Avatar, có thể nhấp để thay đổi */}
        <div className="d-flex justify-content-center">
          <img
            src={profile.avatar || '/default-avatar.png'} // Sử dụng avatar mặc định nếu không có avatar
            alt="Avatar"
            style={{
              height: '150px',
              width: '150px',
              objectFit: 'cover',
              borderRadius: '50%',
            }}
            onClick={onEditAvatar} // Khi click vào avatar, mở modal để chỉnh sửa avatar
            className="cursor-pointer"
          />
        </div>

        {/* Tên người dùng */}
        <h5 className="text-center mt-3">{profile.full_name}</h5>

        {/* Thông tin cá nhân */}
        <div className="mt-4">
          <p><strong>Địa chỉ:</strong> {profile.address}</p>
          <p><strong>Ngày sinh:</strong> {profile.birthday}</p>
          <p><strong>Số điện thoại:</strong> {profile.phone}</p>
          <p><strong>Giới tính:</strong> {profile.sex === 1 ? 'Nam' : 'Nữ'}</p>
        </div>

        {/* Nút chỉnh sửa thông tin */}
        <Button variant="primary" onClick={onEditProfile}>Chỉnh sửa thông tin</Button>
      </Card.Body>
    </Card>
  );
};

ProfileCard.propTypes = {
  profile: PropTypes.object.isRequired,
  onEditProfile: PropTypes.func.isRequired,
  onEditAvatar: PropTypes.func.isRequired,
};

export default ProfileCard;
