import React from 'react';
import SearchBar from './SearchBar';
import '../../../styles/Hero.css';
const Hero = () => {
  return (  
    <div className="relative bg-gradient-to-r from-blue-500 to-blue-700 pt-40 mt-16 pb-16 z-0">
      <div className="container mx-auto px-4">
        <div className="text-center mb-12">
          <h1 className="text-4xl md:text-6xl font-bold text-black mb-4">
            Tìm việc làm mơ ước của bạn
          </h1>
          <p className="text-xl text-black opacity-90">
            Hơn 10,000 việc làm đang chờ đợi bạn
          </p>
        </div>
        <SearchBar />
        <div className="container mt-3">
          <div className="d-flex align-items-center mb-3">
            <span className="me-2">Xu hướng hiện nay:</span>
            <div className="d-inline-flex badge-container">
              <span className="badge badge-custom text-black">Java</span>
              <span className="badge badge-custom text-black">ReactJS</span>
              <span className="badge badge-custom text-black">.NET</span>
              <span className="badge badge-custom text-black">Tester</span>
              <span className="badge badge-custom text-black">PHP</span>
              <span className="badge badge-custom text-black">Business Analyst</span>
              <span className="badge badge-custom text-black">NodeJS</span>
              <span className="badge badge-custom text-black">Manager</span>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Hero;
