import React from 'react';

const Footer = () => {
  return (
    <footer className="bg-dark text-white py-5">
      <div className="container">
        <div className="row">
          <div className="col-md-3 mb-4">
            <h3 className="h5 mb-3">Về chúng tôi</h3>
            <ul className="list-unstyled">
              <li><a href="#" className="text-white-50 text-decoration-none">Giới thiệu</a></li>
              <li><a href="#" className="text-white-50 text-decoration-none">Liên hệ</a></li>
              <li><a href="#" className="text-white-50 text-decoration-none">Điều khoản sử dụng</a></li>
              <li><a href="#" className="text-white-50 text-decoration-none">Chính sách bảo mật</a></li>
            </ul>
          </div>
          <div className="col-md-3 mb-4">
            <h3 className="h5 mb-3">Dành cho ứng viên</h3>
            <ul className="list-unstyled">
              <li><a href="#" className="text-white-50 text-decoration-none">Việc làm mới nhất</a></li>
              <li><a href="#" className="text-white-50 text-decoration-none">Tạo CV</a></li>
              <li><a href="#" className="text-white-50 text-decoration-none">Cẩm nang nghề nghiệp</a></li>
              <li><a href="#" className="text-white-50 text-decoration-none">Tra cứu lương</a></li>
            </ul>
          </div>
          <div className="col-md-3 mb-4">
            <h3 className="h5 mb-3">Dành cho nhà tuyển dụng</h3>
            <ul className="list-unstyled">
              <li><a href="#" className="text-white-50 text-decoration-none">Đăng tin tuyển dụng</a></li>
              <li><a href="#" className="text-white-50 text-decoration-none">Tìm hồ sơ</a></li>
              <li><a href="#" className="text-white-50 text-decoration-none">Giải pháp HR</a></li>
              <li><a href="#" className="text-white-50 text-decoration-none">Bảng giá dịch vụ</a></li>
            </ul>
          </div>
          <div className="col-md-3 mb-4">
            <h3 className="h5 mb-3">Kết nối với chúng tôi</h3>
            <div className="d-flex gap-3">
              <a href="#" className="text-white-50 fs-4">📱</a>
              <a href="#" className="text-white-50 fs-4">📘</a>
              <a href="#" className="text-white-50 fs-4">📸</a>
              <a href="#" className="text-white-50 fs-4">💼</a>
            </div>
          </div>
        </div>
        <div className="border-top border-secondary mt-4 pt-4 text-center">
          <p className="mb-0">&copy; 2024 JobFinder. Tất cả quyền được bảo lưu.</p>
        </div>
      </div>
    </footer>
  );
};

export default Footer;
