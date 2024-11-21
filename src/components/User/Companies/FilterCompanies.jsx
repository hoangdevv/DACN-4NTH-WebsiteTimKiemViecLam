import React, { useState } from 'react';
import { Checkbox, Button } from 'antd';  // Import các component từ antd
import locations from '../../data/locations';

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
              <Checkbox 
                id={`location-${index}`} 
                className="me-2"
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
          <Button 
            onClick={() => setShowAll(!showAll)} 
            type="link" 
            className="p-0" 
            style={{ textDecoration: 'none', color: 'rgb(204, 10, 157)'}}
          >
            {showAll ? "Thu gọn" : "Tất cả"}
          </Button>
        </li>
      </ul>
    </div>
  );
};

export default FilterCompanies;
