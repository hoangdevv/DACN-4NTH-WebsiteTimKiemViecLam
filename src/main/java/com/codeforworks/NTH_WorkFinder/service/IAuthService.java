package com.codeforworks.NTH_WorkFinder.service;

import com.codeforworks.NTH_WorkFinder.dto.auth.login.LoginRequestDTO;
import com.codeforworks.NTH_WorkFinder.dto.auth.login.LoginResponseDTO;
import com.codeforworks.NTH_WorkFinder.dto.auth.register.EmployerRegisterDTO;
import com.codeforworks.NTH_WorkFinder.dto.auth.register.UserRegisterDTO;
import com.codeforworks.NTH_WorkFinder.model.Account;
import com.codeforworks.NTH_WorkFinder.model.Employer;
import com.codeforworks.NTH_WorkFinder.model.User;
import jakarta.servlet.http.HttpServletResponse;

public interface IAuthService {

    User registerUser(UserRegisterDTO userDTO);

    Employer registerEmployer(EmployerRegisterDTO employerDTO);

    LoginResponseDTO login(LoginRequestDTO loginRequest, Account.AccountType expectedAccountType, HttpServletResponse response);
}
