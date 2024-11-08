import React from 'react';
const SearchBar = () => {
  return (
    <div className="container">
      <div className="row justify-content-center">
        <div className="col-12 col-md-8">
          <div className="card p-4">
            <div className="row g-3">
              <div className="col-12 col-md">
                <input
                  type="text"
                  placeholder="Chức danh, kỹ năng, công ty..."
                  className="form-control"
                />
              </div>
              <div className="col-12 col-md">
                <select className="form-select">
                  <option value="">Chọn địa điểm</option>
                  <option value="hanoi">Hà Nội</option>
                  <option value="hcm">TP. Hồ Chí Minh</option>
                  <option value="danang">Đà Nẵng</option>
                </select>
              </div>
              <div className="col-12 col-md-auto">
                <button className="btn btn-primary w-100">
                  Tìm kiếm
                </button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default SearchBar;
