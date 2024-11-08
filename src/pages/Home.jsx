import React from 'react';
import Hero from '../components/home/Hero';
import PopularCategories from '../components/home/PopularCategories';
import FeaturedJobs from '../components/home/FeaturedJobs';
import TopCompanies from '../components/home/TopCompanies';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min.js';
import Header from '../components/common/Header';
import Footer from '../components/common/Footer';
const Home = () => {
  return (
    <div>
      <Header />
      <Hero />
      <PopularCategories />
      <FeaturedJobs />
      <TopCompanies />
      <Footer />
    </div>
  );
};

export default Home;
