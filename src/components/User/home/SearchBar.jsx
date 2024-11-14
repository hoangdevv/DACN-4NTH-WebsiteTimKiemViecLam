import React from 'react';

const SearchBar = () => {
  return (
    <div className="container">
      <div className="row justify-content-center">
        <div className="col-12 col-md-12">
          <div className="card p-4">
            <div className="row g-3">
              <div className="col">
                <input
                  type="text"
                  placeholder="Tìm kiếm cơ hội việc làm"
                  className="form-control"
                />
              </div>
              <div className="col-auto">
                <select className="form-select">
                  <option value="">Lọc theo nghề nghiệp</option>
                  {/* Add more options as needed */}
                </select>
              </div>
              <div className="col-auto">
                <select className="form-select">
                  <option value="">Lọc theo tỉnh thành</option>
                  {/* Add more options as needed */}
                </select>
              </div>
              <div className="col-auto">
                <a href='/jobs' className="btn btn-primary">
                  Lọc nâng cao
                </a>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    
  );
};

export default SearchBar;
