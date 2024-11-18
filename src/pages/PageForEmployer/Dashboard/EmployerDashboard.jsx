import React from 'react';
import SlideBar from '../../../components/Employer/common/SlideBar';

const EmployerDashboard = () => {
    return (
        <div className="d-flex">
            {/* Sidebar */}
            <div className="bg-dark text-white p-3 position-fixed" style={{ top: 0, left: 0, height: '100vh', width: '250px' }}>
                <SlideBar /> 
            </div> 

            {/* Nội dung chính bên phải */}
            <div className="container-fluid ms-250" style={{ marginLeft: '250px' }}>
                <div className="pt-4 ps-3">
                    <h2>Chào mừng đến với Dashboard!</h2>
                    <p>Đây là nơi bạn có thể quản lý và xem các báo cáo, người dùng, cài đặt, và nhiều hơn nữa.</p>
                </div>
            </div>
        </div>
    );
};

export default EmployerDashboard;