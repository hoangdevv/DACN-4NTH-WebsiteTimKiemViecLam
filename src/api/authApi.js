import axios from "axios";
import qs from "qs";

const API_URL = "http://localhost:8080/api/auth";

// Chức năng đăng ký người dùng
export const registerUser = async (userDTO) => {
  try {
    const response = await axios.post(`${API_URL}/register/user`, userDTO, {
      headers: {
        "Content-Type": "application/json",
      },
    });
    return response.data;
  } catch (error) {
    throw new Error(
      error.response ? error.response.data : "Registration failed"
    );
  }
};

// Chức năng đăng ký nhà tuyển dụng
export const registerEmployer = async (employerDTO) => {
  try {
    const response = await axios.post(
      `${API_URL}/register/employer`,
      employerDTO,
      {
        headers: {
          "Content-Type": "application/json",
        },
      }
    );
    return response.data;
  } catch (error) {
    throw new Error(
      error.response ? error.response.data : "Employer registration failed"
    );
  }
};

// Chức năng tạo tài khoản quản trị viên (yêu cầu vai trò quản trị viên)
export const createAdmin = async (email, password) => {
  try {
    const response = await axios.post(`${API_URL}/create/admin`, null, {
      params: { email, password },
      headers: {
        "Content-Type": "application/json",
      },
    });
    return response.data;
  } catch (error) {
    throw new Error(
      error.response ? error.response.data : "Admin creation failed"
    );
  }
};

// Chức năng xác minh tài khoản người dùng bằng OTP
export const verifyAccount = async (email, code) => {
    try {
      const response = await axios.post(
        `${API_URL}/verify`,
        qs.stringify({ email: email, code: code }),  // Ensure the data is correctly formatted
        {
          headers: {
            "Content-Type": "application/x-www-form-urlencoded",
            "Authorization": `Bearer ${localStorage.getItem('jwtToken')}`,
          },
          withCredentials: true,
        }
      );
      console.log("Response:", response.data);
      return response.data;
    } catch (error) {
      console.error("Error during verification:", error);
      if (error.response?.status === 400) {
        throw new Error("Mã xác thực không đúng hoặc session đã hết hạn!");
      }
      throw new Error("Lỗi xác thực!");
    }
  };
  
// Chức năng gửi lại OTP để xác minh tài khoản
export const resendOtp = async (email) => {
  try {
    const response = await axios.post(`${API_URL}/resend-otp`, null, {
      params: { email },
    });
    return response.data;
  } catch (error) {
    throw new Error(
      error.response ? error.response.data : "Failed to resend OTP"
    );
  }
};

// Chức năng đăng nhập (USER, EMPLOYER, ADMIN)
export const login = async (accountType, loginRequest) => {
  try {
    const response = await axios.post(
      `${API_URL}/login/${accountType}`,
      loginRequest,
      {
        headers: {
          "Content-Type": "application/json",
        },
      }
    );
    return response.data;
  } catch (error) {
    throw new Error(error.response ? error.response.data : "Login failed");
  }
};

// Chức năng lấy thông tin người dùng OAuth2 hiện tại
export const getCurrentOAuthUser = async () => {
  try {
    const response = await axios.get(`${API_URL}/oauth2/me`, {
      headers: {
        Authorization: `Bearer ${localStorage.getItem("jwtToken")}`, // Assuming JWT is stored in localStorage
      },
    });
    return response.data;
  } catch (error) {
    throw new Error(
      error.response ? error.response.data : "Failed to fetch user data"
    );
  }
};

// Chức năng gửi mã OTP quên mật khẩu
export const forgotPassword = async (email) => {
  try {
    const response = await axios.post(`${API_URL}/forgot-password`, null, {
      params: { email },
    });
    return response.data;
  } catch (error) {
    throw new Error(
      error.response
        ? error.response.data
        : "Failed to send OTP for password reset"
    );
  }
};

// Chức năng thay đổi mật khẩu sau khi xác minh OTP
export const resetPassword = async (email, code, newPassword) => {
  try {
    const response = await axios.post(`${API_URL}/reset-password`, null, {
      params: { email, code, newPassword },
    });
    return response.data;
  } catch (error) {
    throw new Error(
      error.response ? error.response.data : "Password reset failed"
    );
  }
};

// Chức năng xử lý việc đăng xuất (ví dụ: xóa localStorage hoặc cookie)
export const logout = () => {
  localStorage.removeItem("jwtToken");
  // Optionally clear other session data or tokens
};
