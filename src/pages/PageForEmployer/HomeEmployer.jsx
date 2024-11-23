import React from "react";
import { Layout, Row, Col, Card, Button, Typography, Space, Divider, List, Timeline, Collapse } from "antd";
import { CheckCircleOutlined, QuestionCircleOutlined, SmileOutlined } from "@ant-design/icons";
import Header from "../../components/Employer/common/Header";
import Footer from "../../components/User/common/Footer";

const { Title, Paragraph } = Typography;
const { Content } = Layout;
const { Panel } = Collapse;

const HomeEmployer = () => {
  const servicePackages = [
    {
      name: "Cơ Bản",
      description: "Đăng tuyển công việc và quản lý hồ sơ ứng viên cơ bản.",
      price: "Miễn phí",
    },
    {
      name: "Nâng Cao",
      description: "Thêm công cụ phân tích và đề xuất ứng viên phù hợp.",
      price: "1.000.000 VND/tháng",
    },
    {
      name: "Premium",
      description: "Tất cả tính năng cao cấp với hỗ trợ chuyên nghiệp 24/7.",
      price: "3.000.000 VND/tháng",
    },
  ];

  const reasons = [
    "Đăng tuyển nhanh chóng và dễ dàng.",
    "Hỗ trợ ứng viên chất lượng từ mọi ngành nghề.",
    "Giao diện đơn giản và dễ sử dụng.",
    "Công cụ phân tích hiệu quả giúp tối ưu tuyển dụng.",
    "Đội ngũ hỗ trợ chuyên nghiệp 24/7.",
  ];

  const processSteps = [
    "Đăng ký tài khoản và lựa chọn gói dịch vụ.",
    "Tạo bài đăng tuyển dụng với các tiêu chí chi tiết.",
    "Sử dụng công cụ phân tích để xem xét ứng viên tiềm năng.",
    "Liên hệ với ứng viên và tổ chức phỏng vấn.",
    "Hoàn tất quy trình tuyển dụng với sự hỗ trợ của chúng tôi.",
  ];

  const faqs = [
    {
      question: "Làm thế nào để đăng ký tài khoản?",
      answer: "Bạn có thể đăng ký tài khoản miễn phí trên trang của chúng tôi bằng cách nhấp vào nút 'Đăng ký'.",
    },
    {
      question: "Tôi có thể hủy gói dịch vụ đã đăng ký không?",
      answer: "Có, bạn có thể hủy gói dịch vụ bất cứ lúc nào trong phần quản lý tài khoản.",
    },
    {
      question: "Hỗ trợ khách hàng hoạt động như thế nào?",
      answer: "Chúng tôi có đội ngũ hỗ trợ trực tuyến 24/7 để giải đáp mọi thắc mắc của bạn.",
    },
  ];

  return (
    <Layout>
      <Header />
      <Content style={{ padding: "50px 100px" }}>
        {/* Section: Giới thiệu */}
        <section className="intro text-center">
          <Title level={2}>Chào mừng đến với Trang Nhà Tuyển Dụng</Title>
          <Paragraph type="secondary">
            Tìm kiếm và tuyển dụng ứng viên phù hợp nhanh chóng với các gói dịch vụ đăng tuyển đa dạng.
          </Paragraph>
        </section>

        <Divider />

        {/* Section: Các gói dịch vụ */}
        <section className="services">
          <Title level={3} className="text-center">Các Gói Dịch Vụ Đăng Tuyển</Title>
          <Row gutter={[16, 16]} justify="center">
            {servicePackages.map((pkg, index) => (
              <Col xs={24} sm={12} lg={8} key={index}>
                <Card
                  title={pkg.name}
                  hoverable
                  bordered={false}
                  style={{ borderRadius: "8px" }}
                >
                  <Paragraph>{pkg.description}</Paragraph>
                  <Title level={4} style={{ color: "#ff4d4f" }}>{pkg.price}</Title>
                  <Button type="primary" block>Đăng ký ngay</Button>
                </Card>
              </Col>
            ))}
          </Row>
        </section>

        <Divider />

        {/* Section: Lợi ích */}
        <section className="benefits text-center">
          <Title level={3}>Lợi ích khi sử dụng dịch vụ của chúng tôi</Title>
          <Row gutter={[16, 16]} justify="center">
            <Col xs={24} sm={12} lg={8}>
              <Card bordered={false}>
                <SmileOutlined style={{ fontSize: "40px", color: "#52c41a" }} />
                <Paragraph>
                  Tiết kiệm thời gian với công cụ tự động hóa trong tuyển dụng.
                </Paragraph>
              </Card>
            </Col>
            <Col xs={24} sm={12} lg={8}>
              <Card bordered={false}>
                <SmileOutlined style={{ fontSize: "40px", color: "#1890ff" }} />
                <Paragraph>
                  Tăng khả năng tiếp cận đến hàng ngàn ứng viên tiềm năng.
                </Paragraph>
              </Card>
            </Col>
            <Col xs={24} sm={12} lg={8}>
              <Card bordered={false}>
                <SmileOutlined style={{ fontSize: "40px", color: "#ff4d4f" }} />
                <Paragraph>
                  Hỗ trợ tối ưu chi phí và cải thiện hiệu quả tuyển dụng.
                </Paragraph>
              </Card>
            </Col>
          </Row>
        </section>

        <Divider />

        {/* Section: Quy trình tuyển dụng */}
        <section className="process text-center">
          <Title level={3}>Quy trình tuyển dụng</Title>
          <Timeline mode="alternate">
            {processSteps.map((step, index) => (
              <Timeline.Item key={index}>{step}</Timeline.Item>
            ))}
          </Timeline>
        </section>

        <Divider />

        {/* Section: FAQ */}
        <section className="faq">
          <Title level={3}>Câu hỏi thường gặp</Title>
          <Collapse>
            {faqs.map((faq, index) => (
              <Panel header={faq.question} key={index} icon={<QuestionCircleOutlined />}>
                <Paragraph>{faq.answer}</Paragraph>
              </Panel>
            ))}
          </Collapse>
        </section>

        <Divider />

        {/* Section: Đối tác chiến lược */}
        <section className="partners text-center">
          <Title level={3}>Đối tác chiến lược</Title>
          <Row gutter={[16, 16]} justify="center">
            {["Google", "Microsoft", "Amazon"].map((partner, index) => (
              <Col xs={24} sm={12} lg={8} key={index}>
                <Card bordered={false}>
                  <Title level={4}>{partner}</Title>
                  <Paragraph>Hợp tác chiến lược để mang lại lợi ích tối ưu cho khách hàng.</Paragraph>
                </Card>
              </Col>
            ))}
          </Row>
        </section>
      </Content>
      <Footer />
    </Layout>
  );
};

export default HomeEmployer;
