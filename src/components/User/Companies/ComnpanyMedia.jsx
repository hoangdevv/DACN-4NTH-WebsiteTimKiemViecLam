import React from 'react';
import PropTypes from 'prop-types';
import employers from '../../data/employers';

const CompanyMedia = ({ companyId }) => {
    const company = employers.find(emp => emp.id_employer === companyId);

    if (!company) {
        return <div>Không tìm thấy thông tin công ty.</div>;
    }

    return (
        <div className="p-3 border rounded mt-3 text-start">
            {/* Link Website */}
            <h6 className="text-primary">Website</h6>
            <a href={company.company_website} target="_blank" rel="noopener noreferrer" className="text-decoration-none text-dark">
                <i className="bi bi-globe me-2"></i>{company.company_website}
            </a>

            {/* Social Media Icons */}
            <h6 className="text-primary mt-4">Theo dõi</h6>
            <div className="d-flex gap-2">
                <i className="bi bi-facebook text-primary fs-4"></i>
                <i className="bi bi-linkedin text-info fs-4"></i>
                <i className="bi bi-youtube text-danger fs-4"></i>
                <i className="bi bi-twitter text-primary fs-4"></i>
                <i className="bi bi-google text-danger fs-4"></i>
            </div>

            {/* Mini Map */}
            <h6 className="text-primary mt-4">Bản đồ công ty</h6>
            <div className="mt-2">
                <iframe
                    title="Company Location"
                    src={`https://www.google.com/maps?q=${encodeURIComponent(company.company_address)}&output=embed`}
                    width="100%"
                    height="250"
                    style={{ border: 0 }}
                    allowFullScreen=""
                    loading="lazy"
                ></iframe>
            </div>
        </div>
    );
};

CompanyMedia.propTypes = {
    companyId: PropTypes.string.isRequired,
};

export default CompanyMedia;
