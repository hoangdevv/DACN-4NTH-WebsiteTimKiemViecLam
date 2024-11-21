    import React from 'react';
    import { useParams } from 'react-router-dom';
    import Footer from '../../components/User/common/Footer';
    import employers from '../../components/data/employers';
    import CompanyDetailInfo from '../../components/User/CompanyDetail/CompanyDetailInfo';
    import ComnpanyDescription from '../../components/User/CompanyDetail/CompanyDescription';
    import CompanyMedia from '../../components/User/CompanyDetail/ComnpanyMedia';

    const CompanyDetail = () => {
        const { id } = useParams();
        const company = employers.find((emp) => emp.id_employer === id);

        if (!company) {
            return <div>Company not found</div>;
        }

        return (
            <div>
                <div className="container-fluid text-center mt-4 d-flex justify-content-center">
                    <div className="row w-100" style={{ maxWidth: '1200px' }}>
                    <CompanyDetailInfo companyId={id}/>
                        <div className="col-md-8">
                            <ComnpanyDescription companyId={id} />
                        </div>
                        <div className="col-md-4">
                            <CompanyMedia companyId={id} />
                        </div>
                    </div>
                </div>
                <Footer />
            </div>
        );
    };

    export default CompanyDetail;
