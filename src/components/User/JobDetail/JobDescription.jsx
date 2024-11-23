import React, { useEffect, useState } from "react";
import PropTypes from "prop-types";
import jobs from "../../data/jobs";
import employers from "../../data/employers";
import industries from "../../data/industries";
import professions from "../../data/professions";
import {
  EnvironmentOutlined,
  DollarOutlined,
  UserOutlined,
  FileTextOutlined,
} from "@ant-design/icons";
import { Affix, Menu } from "antd";
import '../../../styles/JobDescription.module.css';

const JobDescription = ({ jobId }) => {
  const [activeSection, setActiveSection] = useState("description");
  const job = jobs.find((job) => job.id_job === jobId);
  const employer = employers.find((emp) => emp.id_employer === job?.id_employer);
  const industry = industries.find((ind) => ind.id_industry === job?.id_industry);
  const profession = professions.find((prof) => prof.id_profession === job?.id_profession);

  useEffect(() => {
    if (!job) return;

    const sections = document.querySelectorAll("section");
    const observer = new IntersectionObserver(
      (entries) => {
        entries.forEach((entry) => {
          if (entry.isIntersecting) {
            setActiveSection(entry.target.id);
          }
        });
      },
      { rootMargin: "-123.6px 0px -70% 0px", threshold: 0.5 }
    );

    sections.forEach((section) => {
      observer.observe(section);
    });

    return () => {
      sections.forEach((section) => {
        observer.unobserve(section);
      });
    };
  }, [job]);

  if (!job) {
    return <p>Không tìm thấy công việc.</p>;
  }

  const handleMenuClick = (e) => {
    const section = document.getElementById(e.key);
    if (section) {
      section.scrollIntoView({ behavior: "smooth", block: "start" });
    }
  };

  const menuItems = [
    { label: "Mô tả", key: "description", icon: <FileTextOutlined /> },
    { label: "Kỹ năng yêu cầu", key: "skills", icon: <UserOutlined /> },
    { label: "Chi tiết công việc", key: "details", icon: <EnvironmentOutlined /> },
    { label: "Liên hệ", key: "contact", icon: <DollarOutlined /> },
  ];

  const menuStyles = {
    borderBottom: "1px solid #ddd",
    background: "#fff",
    position: "sticky",
    top: 0,
    zIndex: 1010,
    display: "flex",
    justifyContent: "center",
  };

  const menuItemDefaultStyles = {
    color: "black", 
    borderBottom: "none",
  };

  const menuItemHoverStyles = {
    color: "rgb(204, 10, 157)", 
    borderBottom: "2px solid rgb(204, 10, 157)", 
  };

  const menuItemSelectedStyles = {
    color: "rgb(204, 10, 157)", 
    borderBottom: "2px solid rgb(204, 10, 157)", 
  };

  return (
    <div className="card mb-4 shadow-sm">
      <Affix offsetTop={64}>
        <Menu
          mode="horizontal"
          selectedKeys={[activeSection]}
          onClick={handleMenuClick}
          items={menuItems.map((item) => ({
            ...item,
            style: activeSection === item.key
              ? menuItemSelectedStyles
              : menuItemDefaultStyles,
          }))}
          style={menuStyles}
          className="custom-menu"
        />
      </Affix>
      <div className="card-body">
        <div className="container mt-4 text-start">
          {/* Thông tin chi tiết công việc */}
          <div className="row mb-4">
            <div className="col-md-6">
              <p><strong>Địa điểm:</strong> {job.location}</p>
              <p><strong>Mức lương:</strong> {job.salary}</p>
            </div>
            <div className="col-md-6">
              <p><strong>Ngành:</strong> {industry?.industry_name || "Chưa có thông tin ngành"}</p>
              <p><strong>Nghề nghiệp:</strong> {profession?.profession_name || "Chưa có thông tin nghề nghiệp"}</p>
            </div>
          </div>
          {/* Nội dung các phần */}
          <section id="description" style={{ scrollMarginTop: "110px" }}>
            <h4>Mô tả công việc</h4>
            <p>{job.description}</p>
          </section>
          <section id="skills" style={{ scrollMarginTop: "110px" }}>
            <h4>Kỹ năng yêu cầu</h4>
            <p>{job.skills || "Chưa có thông tin kỹ năng yêu cầu."}</p>
          </section>
          <section id="details" style={{ scrollMarginTop: "110px" }}>
            <h4>Chi tiết công việc</h4>
            <p>{job.details || "Chưa có thông tin chi tiết công việc."}</p>
          </section>
          <section id="contact" style={{ scrollMarginTop: "110px" }}>
            <h4>Liên hệ</h4>
            <p>
              <strong>Địa chỉ:</strong> {employer?.company_address || "Chưa có thông tin địa chỉ."}
              <br />
              <strong>Số điện thoại:</strong> {employer?.company_phone || "Chưa có thông tin số điện thoại."}
            </p>
          </section>
        </div>
      </div>
    </div>
  );
};

JobDescription.propTypes = {
  jobId: PropTypes.string.isRequired,
};

export default JobDescription;
