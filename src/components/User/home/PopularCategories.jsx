import React from 'react';

const categories = [
  {
    title: 'Công nghệ thông tin',
    count: 1200,
    icon: '💻',
  },
  {
    title: 'Marketing',
    count: 800,
    icon: '📢',
  },
  {
    title: 'Tài chính - Kế toán',
    count: 600,
    icon: '💰',
  },
  {
    title: 'Kinh doanh',
    count: 950,
    icon: '📈',
  },
  {
    title: 'Thiết kế',
    count: 400,
    icon: '🎨',
  },
  {
    title: 'Nhân sự',
    count: 350,
    icon: '👥',
  },
];

const PopularCategories = () => {
  return (
    <section className="py-5 bg-light">
      <div className="container">
        <h2 className="text-center mb-4 fw-bold">Ngành nghề phổ biến</h2>
        <div className="row g-4">
          {categories.map((category) => (
            <div key={category.title} className="col-md-6 col-lg-4">
              <div className="card h-100 border-0 shadow-sm transition-all hover-shadow">
                <div className="card-body d-flex align-items-center p-4">
                  <span className="display-5 me-3 text-primary">{category.icon}</span>
                  <div>
                    <h3 className="h5 mb-2 fw-semibold">{category.title}</h3>
                    <p className="text-muted mb-0">
                      <span className="fw-medium text-primary">{category.count}</span> việc làm
                    </p>
                  </div>
                </div>
              </div>
            </div>
          ))}
        </div>
      </div>
    </section>
  );
};

export default PopularCategories;
