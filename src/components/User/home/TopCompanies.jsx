/* eslint-disable no-unused-vars */
import React from 'react';
import GoogleLogo from '../../../assets/logos/Google.png';
import FacebookLogo from '../../../assets/logos/Facebook.png';

const companies = [
  {
    name: 'Google',
    industry: 'Công nghệ',
    location: 'Hà Nội', 
    openPositions: 15,
    logo: GoogleLogo,
  },
  {
    name: 'Facebook',
    industry: 'Công nghệ',
    location: 'TP. HCM',
    openPositions: 10,
    logo: FacebookLogo,
  },
  // Thêm các công ty khác...
];

const TopCompanies = () => {
  return (
    <section className="py-5 bg-light">
      <div className="container">
        <h2 className="h2 text-center mb-4">Công ty hàng đầu</h2>
        <div className="row row-cols-1 row-cols-md-2 row-cols-lg-4 g-4">
          {companies.map((company) => (
            <div key={company.name} className="col">
              <div className="card h-100 shadow-sm hover-shadow">
                <img
                  src={company.logo}
                  alt={company.name}
                  className="card-img-top w-50 mx-auto mt-3"
                />
                <div className="card-body text-center">
                  <h3 className="card-title h5">{company.name}</h3>
                  <p className="card-text text-muted">{company.industry}</p>
                  <p className="card-text text-muted">{company.location}</p>
                  <p className="text-primary">
                    {company.openPositions} vị trí đang tuyển
                  </p>
                </div>
              </div>
            </div>
          ))}
        </div>
      </div>
    </section>
  );
};

export default TopCompanies;
