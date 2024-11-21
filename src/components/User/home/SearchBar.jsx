import React, { useEffect, useState } from 'react';
import { Select, Button, Input, Row, Col } from 'antd';
import professions from '../../../components/data/professions';
import axios from 'axios';

const { Option } = Select;

const SearchBar = () => {
  const [provinces, setProvinces] = useState([]);
  const [loading, setLoading] = useState(false);

  // Fetch provinces from API
  useEffect(() => {
    const fetchProvinces = async () => {
      setLoading(true);
      try {
        const response = await axios.get('https://provinces.open-api.vn/api/p/');
        setProvinces(response.data); // Giả định API trả về danh sách tỉnh thành
      } catch (error) {
        console.error('Lỗi khi tải danh sách tỉnh thành:', error);
      } finally {
        setLoading(false);
      }
    };

    fetchProvinces();
  }, []);

  return (
    <div
      style={{
        background: '#fff',
        padding: '20px',
        borderRadius: '8px',
        boxShadow: '0 4px 6px rgba(0, 0, 0, 0.1)',
        marginTop: '20px',
      }}
    >
      <Row gutter={[16, 16]} justify="center" align="middle">
        {/* Ô tìm kiếm */}
        <Col xs={24} md={10}>
          <Input
            placeholder="Tìm kiếm cơ hội việc làm"
            style={{
              height: '40px',
              borderRadius: '5px',
            }}
          />
        </Col>

        {/* Lọc theo nghề nghiệp */}
        <Col xs={24} md={5}>
          <Select
            placeholder="Lọc theo nghề nghiệp"
            style={{
              width: '100%',
              borderRadius: '5px',
            }}
            allowClear
          >
            {professions.map((profession) => (
              <Option key={profession.id_profession} value={profession.profession_name}>
                {profession.profession_name}
              </Option>
            ))}
          </Select>
        </Col>

        {/* Lọc theo tỉnh thành */}
        <Col xs={24} md={5}>
          <Select
            placeholder="Lọc theo tỉnh thành"
            style={{
              width: '100%',
              borderRadius: '5px',
            }}
            loading={loading}
            allowClear
          >
            {provinces.map((province) => (
              <Option key={province.code} value={province.name}>
                {province.name}
              </Option>
            ))}
          </Select>
        </Col>

        {/* Nút lọc nâng cao */}
        <Col xs={24} md={4}>
          <Button
            type="primary"
            href="/jobs"
            style={{
              background: 'linear-gradient(to right, #020024, #cc0a9d)',
              border: 'none',
              borderRadius: '5px',
              padding: '0 20px',
              height: '40px',
              width: '100%',
              fontWeight: 'bold',
              textDecoration: 'none',
            }}
          >
            Lọc nâng cao
          </Button>
        </Col>
      </Row>
    </div>
  );
};

export default SearchBar;
