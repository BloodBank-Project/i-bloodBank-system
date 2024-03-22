package com.user.service;

import java.security.Timestamp;
import java.util.List;
import java.util.Optional;

import com.user.bean.UserBean;
import com.user.entity.User;

public interface UserService {

	User saveUser(User user);

	UserBean getByUserId(Long id);

	List<User> getAllUsers();

	void deleteUserById(Long id);

	Optional<User> updateUser(User user);

	Optional<User> userLogin(String email, String password);

	Optional<User> updateUserPassword(String email, String password);

	Optional<User> updateUserBloodBankId(String email, Long bloodBankId);

	Optional<User> updateUserBloodBankIdBlood(String email, Long bloodBankId, Long bloodId);

	
	String generateOtp();

	void sendOtpEmail(String toEmail, String otp);

	boolean verifyOtp(String email, String enteredOtp);

	User forgetPassword(String email);

	void saveOtp(String email, String otp, java.sql.Timestamp expirationTime);

	void sendPasswordResetEmail(String email);
}
