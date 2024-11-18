import { accounts } from '../components/data/accounts';

export const authService = {
  login: (email, password) => {
    // Simulate API call
    return new Promise((resolve, reject) => {
      setTimeout(() => {
        const user = accounts.find(
          acc => acc.email === email && acc.password === password
        );
        
        if (user) {
          // Loại bỏ password trước khi trả về
          const { password: _, ...userWithoutPassword } = user;
          resolve(userWithoutPassword);
        } else {
          reject(new Error('Email hoặc mật khẩu không đúng'));
        }
      }, 500); // Giả lập delay 500ms như gọi API thật
    });
  }
};