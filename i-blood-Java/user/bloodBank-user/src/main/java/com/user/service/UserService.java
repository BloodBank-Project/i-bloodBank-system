package com.user.service;

import java.util.List;
import java.util.Optional;

import com.user.bean.UserBean;
import com.user.entity.User;

public interface UserService {
	void save(User user);

//	User getById(Long id);
	
	UserBean getById(Long id);

	List<User> getAll();

	void delete(Long id);

	Optional<User> update(User user);

	Optional<User> login(String email, String password);
	
	Optional<User> updatePassword(String email,String password);
	
//	String updatePassword(String email,String password);
	
}
