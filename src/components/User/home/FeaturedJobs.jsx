// eslint-disable-next-line no-unused-vars
import React from 'react';
import TechCorp from '../../../assets/logos/TechCorp.png';
import BrandSolutions from '../../../assets/logos/BrandSolutions.png';

const jobs = [
  {
    title: 'Senior React Developer',
    company: 'Tech Corp',
    location: 'Hà Nội',
    salary: '$1500 - $2500',
    type: 'Full-time',
    logo: TechCorp,
  },
  {
    title: 'Marketing Manager',
    company: 'Brand Solutions',
    location: 'TP. HCM',
    salary: '$1200 - $1800',
    type: 'Full-time',
    logo: BrandSolutions,
  },
  // Thêm các công việc khác...
];

const FeaturedJobs = () => {
  return (
    <section className="py-5">
      <div className="container">
        <h2 className="text-center mb-4">Việc làm nổi bật</h2>
        <div className="row g-4">
          {jobs.map((job) => (
            <div key={job.title} className="col-md-6 col-lg-4">
              <div className="card h-100">
                <div className="card-body">
                  <div className="d-flex align-items-start mb-3">
                    <img
                      src={job.logo}
                      alt={job.company}
                      className="me-3"
                      style={{ width: "48px", height: "48px" }}
                    />
                    <div>
                      <h3 className="h5">{job.title}</h3>
                      <p className="text-muted">{job.company}</p>
                    </div>
                  </div>
                  <div className="space-y-2">
                    <div className="flex items-center text-gray-600">
                      <span className="mr-2">📍</span>
                      {job.location}
                    </div>
                    <div className="flex items-center text-gray-600">
                      <span className="mr-2">💰</span>
                      {job.salary}
                    </div>
                    <div className="flex items-center text-gray-600">
                      <span className="mr-2">⏰</span>
                      {job.type}
                    </div>
                  </div>
                  <button type="button" className ="btn btn-success mt-2">
                    Ứng tuyển ngay
                  </button>
                </div>
              </div>
            </div>
          ))}
        </div>
      </div>
    </section>
  );
};

export default FeaturedJobs;
