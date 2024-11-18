import React from 'react';
import Hero from '../../components/User/home/Hero';
import PopularCategories from '../../components/User/home/PopularCategories';
import FeaturedJobs from '../../components/User/home/FeaturedJobs';
import TopCompanies from '../../components/User/home/TopCompanies';
import Footer from '../../components/User/common/Footer';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min.js';

const Home = () => {
  return (
    <div>
      <Hero />
      <PopularCategories />
      <FeaturedJobs />
      <TopCompanies />
      <Footer />
    </div>
  );
};

export default Home;