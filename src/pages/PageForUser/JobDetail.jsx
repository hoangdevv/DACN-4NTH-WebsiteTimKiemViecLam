import React from 'react';
import { useParams } from 'react-router-dom';
import HeaderJobs from '../../components/User/Jobs/HeaderJobs';
import Footer from '../../components/User/common/Footer';
import JobDetailInfo from '../../components/User/JobDetail/JobDetailInfo';
import JobDescription from '../../components/User/JobDetail/JobDescription';
import SimilarJobs from '../../components/User/JobDetail/SimilarJobs';

const DetailJob = () => {
  const { id } = useParams();

  return (
    <div>
      <div className="container-fluid text-center mt-4 d-flex justify-content-center">
        <div className="row w-100" style={{ maxWidth: '1200px' }}>
          <div className="col-md-8">
            <JobDetailInfo jobId={id} />
            <JobDescription jobId={id} />
          </div>
          <div className="col-md-4">
            <SimilarJobs currentJobId={id} />
          </div>
        </div>
      </div>
      <Footer />
    </div>
  );
};

export default DetailJob;