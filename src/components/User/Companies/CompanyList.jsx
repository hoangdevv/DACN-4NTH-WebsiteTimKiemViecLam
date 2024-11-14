import React from 'react';
import PropTypes from 'prop-types';
import { Link } from 'react-router-dom';
import employers from '../../data/employers';

const CompanyList = ({ searchTerm }) => {
  const filteredCompanies = employers.filter((company) =>
    company.company_name.toLowerCase().includes(searchTerm.toLowerCase())
  );

  return (
    <div className="row">
      {filteredCompanies.map((company) => (
        <Link 
          to={`/companyDetail/${company.id_employer}`} 
          key={company.id_employer} 
          className="col-md-4 col-lg-3 mb-4"
          style={{ textDecoration: 'none', color: 'inherit' }}
        >
          <div className="company-card border rounded p-3 text-start h-100 d-flex flex-column">
            <div className="mb-3 d-flex justify-content-center align-items-center" style={{ height: '150px' }}>
              <img 
                src={`https://via.placeholder.com/150`} 
                alt="Company Logo" 
                className="img-fluid" 
                style={{ maxHeight: '100%', maxWidth: '100%', objectFit: 'cover' }} 
              />
            </div>
            <h6 className="company-name text-truncate">{company.company_name}</h6>
            <p className="jobs-available text-primary">0 việc đang tuyển</p>
            <p 
              className="location text-muted mb-0 text-truncate" 
              style={{ overflow: 'hidden', whiteSpace: 'nowrap' }}
              title={company.company_address} 
            >
              {company.company_address}
            </p>
          </div>
        </Link>
      ))}
    </div>
  );
};

CompanyList.propTypes = {
  searchTerm: PropTypes.string.isRequired,
};

export default CompanyList;
