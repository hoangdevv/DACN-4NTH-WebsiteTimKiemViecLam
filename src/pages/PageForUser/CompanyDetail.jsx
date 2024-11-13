import React from 'react';
import { useParams } from 'react-router-dom';
import Header from '../../components/common/Header';
import Footer from '../../components/common/Footer';
import employers from '../../components/data/employers';
import CompanyDetailInfo from '../../components/Companies/CompanyDetailInfo';

const CompanyDetail = () => {
    const { id } = useParams();
    const company = employers.find((emp) => emp.id_employer === id);

    if (!company) {
        return <div>Company not found</div>;
    }

    return (
        <div>
            <Header />
            <div className="container-fluid text-center mt-4 d-flex justify-content-center">
                <div className="row w-100" style={{ maxWidth: '1200px' }}>
                <CompanyDetailInfo />
                    <div className="col-md-8">
                        
                    </div>
                    <div className="col-md-4">
                        
                    </div>
                </div>
            </div>
            <Footer />
        </div>
    );
};

export default CompanyDetail;
