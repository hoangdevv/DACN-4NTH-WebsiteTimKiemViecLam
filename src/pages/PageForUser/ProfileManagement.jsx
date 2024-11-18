import React, { useState, useEffect } from 'react';
import { Container, Row, Col, Alert, Button, Modal } from 'react-bootstrap';
import ProfileCard from '../../components/User/ProfileManagement/ProfileCard'; // Sử dụng ProfileCard duy nhất
import ProfileForm from '../../components/User/ProfileManagement/ProfileForm'; // Dùng ProfileForm để chỉnh sửa
import Footer from '../../components/User/common/Footer';
import AvatarUpload from '../../components/User/ProfileManagement/AvatarUpload'; // Chỉnh sửa Avatar
import candidateProfiles from '../../components/data/candidateProfiles'; // Dữ liệu hồ sơ ứng viên
import { users } from '../../components/data/users'; // Dữ liệu users để tìm id_user từ id_account
import PropTypes from 'prop-types'; // Để xác thực kiểu dữ liệu

const ProfileManagement = () => {
  const [isEditing, setIsEditing] = useState(false);  // Trạng thái chỉnh sửa
  const [profile, setProfile] = useState(null);
  const [showModal, setShowModal] = useState(false);  // Trạng thái Modal

  useEffect(() => {
    const storedUser = JSON.parse(localStorage.getItem('user')); // Lấy thông tin người dùng từ localStorage
    if (storedUser) {
      const userFromUsers = users.find((u) => u.id_account === storedUser.id_account);
      const id_user = userFromUsers ? userFromUsers.id_user : null;

      if (id_user) {
        const userProfile = candidateProfiles.find((candidate) => candidate.id_user === id_user);
        if (userProfile) {
          setProfile(userProfile); // Cập nhật thông tin hồ sơ
        } else {
          console.error('Không tìm thấy hồ sơ ứng viên với id_user:', id_user);
        }
      } else {
        console.error('Không tìm thấy id_user với id_account:', storedUser.id_account);
      }
    } else {
      console.error('Không tìm thấy thông tin người dùng trong localStorage.');
    }
  }, []); // Chạy khi component được mount

  // Hàm lưu thông tin khi người dùng hoàn tất chỉnh sửa
  const handleSave = (updatedProfile) => {
    setProfile(updatedProfile);
    const storedUser = JSON.parse(localStorage.getItem('user'));
    if (storedUser) {
      localStorage.setItem('user', JSON.stringify({ ...storedUser, profile: updatedProfile }));
    }
    setShowModal(false); // Đóng modal sau khi lưu thông tin
  };

  // Hàm để chuyển sang chế độ chỉnh sửa
  const handleEdit = () => {
    setShowModal(true); // Hiển thị Modal khi người dùng nhấn chỉnh sửa
  };

  // Hàm lưu ảnh đại diện mới
  const handleAvatarSave = (newAvatar) => {
    setProfile((prevState) => ({ ...prevState, avatar: newAvatar }));
  };

  // Nếu không tìm thấy hồ sơ, hiển thị thông báo lỗi
  if (!profile) {
    return <Alert variant="warning">Không tìm thấy thông tin hồ sơ của bạn.</Alert>;
  }

  return (
    <div>
      <Container className="py-5">
        <Row>
          <Col md={8}>
            <h2 className="mb-4">Quản lý hồ sơ</h2>
            <ProfileCard profile={profile} onEdit={handleEdit} /> {/* Hiển thị ProfileCard */}
            {/* Không hiển thị ProfileForm và AvatarUpload trên màn hình chính, mà hiển thị khi mở Modal */}
          </Col>
          <Col md={4}>
            <Alert variant="info">
              Bạn có thể cập nhật thông tin cá nhân của mình tại đây. Hãy đảm bảo rằng mọi thông tin đều chính xác!
            </Alert>
          </Col>
        </Row>
      </Container>
      
      {/* Modal chỉnh sửa thông tin */}
      <Modal show={showModal} onHide={() => setShowModal(false)} centered>
        <Modal.Header closeButton>
          <Modal.Title>Chỉnh sửa thông tin</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <AvatarUpload profile={profile} onSave={handleAvatarSave} /> {/* Chỉnh sửa Avatar */}
          <ProfileForm profile={profile} onSave={handleSave} /> {/* Chỉnh sửa thông tin */}
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={() => setShowModal(false)}>
            Đóng
          </Button>
          <Button variant="primary" onClick={() => setShowModal(false)}>
            Lưu thay đổi
          </Button>
        </Modal.Footer>
      </Modal>

      <Footer />
    </div>
  );
};

ProfileManagement.propTypes = {
  profile: PropTypes.object.isRequired, // Đảm bảo prop profile là bắt buộc và có kiểu dữ liệu
};

export default ProfileManagement;
