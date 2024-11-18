import React, { useState, useEffect } from 'react';
import { Form, Button } from 'react-bootstrap';
import PropTypes from 'prop-types';

const ProfileForm = ({ profile, onSave }) => {
  const [formData, setFormData] = useState({});

  useEffect(() => {
    setFormData(profile); // Thiết lập dữ liệu người dùng ban đầu
  }, [profile]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value,
    });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    onSave(formData); // Lưu dữ liệu chỉnh sửa
  };

  return (
    <Form onSubmit={handleSubmit}>
      <Form.Group className="mb-3">
        <Form.Label>Họ và tên</Form.Label>
        <Form.Control
          type="text"
          name="full_name"
          value={formData.full_name}
          onChange={handleChange}
        />
      </Form.Group>

      <Form.Group className="mb-3">
        <Form.Label>Địa chỉ</Form.Label>
        <Form.Control
          type="text"
          name="address"
          value={formData.address}
          onChange={handleChange}
        />
      </Form.Group>

      <Form.Group className="mb-3">
        <Form.Label>Ngày sinh</Form.Label>
        <Form.Control
          type="date"
          name="birthday"
          value={formData.birthday}
          onChange={handleChange}
        />
      </Form.Group>

      <Form.Group className="mb-3">
        <Form.Label>Số điện thoại</Form.Label>
        <Form.Control
          type="text"
          name="phone"
          value={formData.phone}
          onChange={handleChange}
        />
      </Form.Group>

      <Form.Group className="mb-3">
        <Form.Label>Vị trí</Form.Label>
        <Form.Control
          type="text"
          name="location"
          value={formData.location}
          onChange={handleChange}
        />
      </Form.Group>

      <Form.Group className="mb-3">
        <Form.Label>Giới tính</Form.Label>
        <Form.Control
          as="select"
          name="sex"
          value={formData.sex}
          onChange={handleChange}
        >
          <option value="1">Nam</option>
          <option value="2">Nữ</option>
        </Form.Control>
      </Form.Group>

      <Button variant="primary" type="submit">
        Lưu thông tin
      </Button>
    </Form>
  );
};

ProfileForm.propTypes = {
  profile: PropTypes.object.isRequired,
  onSave: PropTypes.func.isRequired,
};

export default ProfileForm;
