import React, { createContext, useContext, useState, useEffect } from 'react';

const AuthContext = createContext();

export const useAuth = () => useContext(AuthContext);

export const AuthProvider = ({ children }) => {
  const [user, setUser] = useState(null);

  useEffect(() => {
    const token = localStorage.getItem('jwtToken');
    if (token) {
      // Giải mã JWT để lấy thông tin người dùng, có thể dùng thư viện như jwt-decode
      const decodedToken = jwtDecode(token);
      setUser(decodedToken);
    }
  }, []);

  const login = (userData) => {
    setUser(userData);
    localStorage.setItem('jwtToken', userData.token); // Lưu JWT token vào localStorage
  };

  const logout = () => {
    setUser(null);
    localStorage.removeItem('jwtToken'); // Xóa JWT token
  };

  return (
    <AuthContext.Provider value={{ user, login, logout }}>
      {children}
    </AuthContext.Provider>
  );
};
