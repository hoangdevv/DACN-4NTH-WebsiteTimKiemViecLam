import React, { useState } from 'react';
import { Form, Button, Alert } from 'react-bootstrap';
import PropTypes from 'prop-types';

const AvatarUpload = ({ profile, onSave, setShowModal }) => {
  const [selectedAvatar, setSelectedAvatar] = useState(null);
  const [error, setError] = useState('');

  const handleFileChange = (e) => {
    const file = e.target.files[0];
    if (file) {
      if (file.type.startsWith('image/')) {
        setError('');
        setSelectedAvatar(URL.createObjectURL(file));
      } else {
        setError('Vui lòng chọn một tệp hình ảnh');
      }
    }
  };

  const handleSave = () => {
    if (selectedAvatar) {
      onSave(selectedAvatar);  // Gọi onSave khi lưu ảnh
      setShowModal(false);  // Đóng modal sau khi lưu ảnh
    } else {
      setError('Chưa chọn ảnh đại diện');
    }
  };

  return (
    <div className="mb-4">
      <Form>
        <Form.Group>
          <Form.Label>Chọn ảnh đại diện mới</Form.Label>
          <Form.Control
            type="file"
            accept="image/*"
            onChange={handleFileChange}
          />
        </Form.Group>
        {error && <Alert variant="danger" className="mt-2">{error}</Alert>}
        <div className="mt-3">
          {selectedAvatar && <img src={selectedAvatar} alt="Avatar Preview" style={{ height: '100px', width: '100px', objectFit: 'cover' }} />}
        </div>
        <Button variant="primary" onClick={handleSave} className="mt-3">Lưu Avatar</Button>
      </Form>
    </div>
  );
};

AvatarUpload.propTypes = {
  profile: PropTypes.object.isRequired,
  onSave: PropTypes.func.isRequired,
  setShowModal: PropTypes.func.isRequired,  // Nhận hàm setShowModal từ profile management
};

export default AvatarUpload;
