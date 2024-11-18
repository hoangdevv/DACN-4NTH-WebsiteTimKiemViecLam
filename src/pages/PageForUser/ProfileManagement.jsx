import React, { useState, useEffect } from 'react';
import { Container, Row, Col, Alert, Modal } from 'react-bootstrap';
import ProfileCard from '../../components/User/ProfileManagement/ProfileCard';
import ProfileForm from '../../components/User/ProfileManagement/ProfileForm';
import Footer from '../../components/User/common/Footer';
import AvatarUpload from '../../components/User/ProfileManagement/AvatarUpload';
import candidateProfiles from '../../components/data/candidateProfiles';
import { users } from '../../components/data/users';

const ProfileManagement = () => {
  const [profile, setProfile] = useState(null);
  const [showModal, setShowModal] = useState(false);  // Trạng thái Modal
  const [modalType, setModalType] = useState('');  // Loại modal (avatar hay thông tin)

  useEffect(() => {
    const storedUser = JSON.parse(localStorage.getItem('user')); // Lấy thông tin người dùng từ localStorage

    if (!storedUser) {
      console.error('Không tìm thấy thông tin người dùng trong localStorage.');
      return;
    }

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
  }, []); // Chạy khi component được mount

  if (!profile) {
    return <Alert variant="warning">Không tìm thấy thông tin hồ sơ của bạn.</Alert>;  // Hiển thị thông báo khi không có profile
  }

  // Hàm lưu avatar mới
  const handleAvatarSave = (newAvatar) => {
    setProfile((prevProfile) => ({
      ...prevProfile,
      avatar: newAvatar,
    }));
  };

  // Hàm lưu thông tin profile mới
  const handleProfileSave = (updatedProfileData) => {
    setProfile((prevProfile) => ({
      ...prevProfile,
      ...updatedProfileData,
    }));
  };

  return (
    <div>
      <Container className="py-5">
        <Row>
          <Col md={8}>
            <h2 className="mb-4">Quản lý hồ sơ</h2>
            <ProfileCard profile={profile} onEditProfile={() => { setModalType('form'); setShowModal(true); }} onEditAvatar={() => { setModalType('avatar'); setShowModal(true); }} />
          </Col>
          <Col md={4}>
            <Alert variant="info">
              Cập nhật thông tin cá nhân của bạn ở đây.
            </Alert>
          </Col>
        </Row>
      </Container>

      {/* Modal chỉnh sửa thông tin */}
      <Modal show={showModal} onHide={() => setShowModal(false)} centered>
        <Modal.Header closeButton>
          <Modal.Title>{modalType === 'form' ? 'Chỉnh sửa thông tin' : 'Cập nhật Avatar'}</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          {modalType === 'form' && <ProfileForm profile={profile} onSave={handleProfileSave} setShowModal={setShowModal} />}
          {modalType === 'avatar' && <AvatarUpload profile={profile} onSave={handleAvatarSave} setShowModal={setShowModal} />}
        </Modal.Body>
      </Modal>

      <Footer />
    </div>
  );
};

export default ProfileManagement;
