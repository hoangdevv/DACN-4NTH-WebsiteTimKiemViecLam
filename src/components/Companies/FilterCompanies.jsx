import React, { useState } from 'react';
import locations from '../data/locations';

const FilterCompanies = () => {
  const [showAll, setShowAll] = useState(false);

  // Hiển thị 5 địa điểm đầu tiên nếu `showAll` là false
  const displayedLocations = showAll ? locations : locations.slice(0, 5);

  return (
    <div className="filter-sidebar p-3">
      <h5 className="text-start">Nơi làm việc</h5>
      <ul className="list-unstyled">
        {displayedLocations.map((location, index) => (
          <li key={index} className="mb-2">
            <div className="form-check d-flex align-items-center">
              <input
                className="form-check-input me-2"
                type="checkbox"
                id={`location-${index}`}
              />
              <label
                className="form-check-label"
                htmlFor={`location-${index}`}
                style={{ cursor: 'pointer' }}
              >
                {location}
              </label>
            </div>
          </li>
        ))}
        <li className="mt-2">
          <button 
            onClick={() => setShowAll(!showAll)} 
            className="btn btn-link p-0 text-primary" 
            style={{ textDecoration: 'none' }}
          >
            {showAll ? "Thu gọn" : "Tất cả"}
          </button>
        </li>
      </ul>
    </div>
  );
};

export default FilterCompanies;
