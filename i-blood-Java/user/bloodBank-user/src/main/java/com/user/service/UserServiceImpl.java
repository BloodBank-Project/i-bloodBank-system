package com.user.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.user.bean.BloodBankBean;
import com.user.bean.BloodBean;
import com.user.bean.UserBean;
import com.user.entity.User;
import com.user.exception.UserNotFoundException;
import com.user.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public void save(User user) {
		userRepository.save(user);
	}

	@Override
	public UserBean getById(Long id) {
		Optional<User> optional = userRepository.findById(id);

		if (optional.isEmpty()) {
			try {
				optional.orElseThrow(() -> new UserNotFoundException("record not found"));
			} catch (UserNotFoundException e) {
				throw e;
			}
		}

		User user = optional.get();

		String bloodURL = "http://localhost:8010/bloodinfo/" + user.getBlood_group_id();

		String bloodBankURL = "http://localhost:8081/bloodbank/bloodbankController/" + user.getBlood_bank_id();

		HttpHeaders headers = new HttpHeaders();

		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> httpEntity = new HttpEntity<>(headers);

		ResponseEntity<BloodBankBean> responseEntity1 = restTemplate.exchange(bloodBankURL, HttpMethod.GET, httpEntity,
				BloodBankBean.class);

		ResponseEntity<BloodBean> responseEntity2 = restTemplate.exchange(bloodURL, HttpMethod.GET, httpEntity,
				BloodBean.class);

		BloodBankBean bloodBankBean = responseEntity1.getBody();

		BloodBean bloodBean = responseEntity2.getBody();

		UserBean userBean = UserBean.builder().user_id(user.getUser_id()).first_name(user.getFirst_name())
				.last_name(user.getLast_name()).email(user.getEmail()).password(user.getPassword())
				.date_of_birth(user.getDate_of_birth()).gender(user.getGender()).address(user.getAddress())
				.contact_number(user.getContact_number()).alternate_contact_number(user.getAlternate_contact_number())
				.type(user.getType()).bloodBankBean(bloodBankBean).bloodBean(bloodBean).build();

		return userBean;
	}

	@Override
	public List<User> getAll() {
		return userRepository.findAll();
	}

	@Override
	public void delete(Long id) {

		Optional<User> optional = userRepository.findById(id);

		if (optional.isEmpty()) {
			try {
				optional.orElseThrow(() -> new UserNotFoundException("record not present"));
			} catch (UserNotFoundException e) {
				throw e;
			}
		} else {
			userRepository.deleteById(id);
		}
	}

	@Override
	public Optional<User> update(User user) {
		Optional<User> optional = userRepository.findById(user.getUser_id());
		if (optional.isEmpty()) {
			try {
				optional.orElseThrow(() -> new UserNotFoundException("record not found"));
			} catch (UserNotFoundException e) {
				throw e;
			}
		}
		userRepository.save(user);
		return optional;
	}

	@Override
	public Optional<User> updatePassword(String email, String password) {
		Optional<User> optional = userRepository.findByEmail(email);
		if (optional.isPresent()) {
			User user = optional.get();
			if (user.getPassword().equals(password)) {
				throw new UserNotFoundException("previously used password is not applicable");
			} else {
				user.setPassword(password);
				userRepository.save(user);
			}
		} else {
			throw new UserNotFoundException("email not correct");
		}
		return optional;
	}

	@Override
	public Optional<User> login(String email, String password) {
		Optional<User> optional1 = userRepository.findByEmail(email);
		if (optional1.isPresent()) {
			User user = optional1.get();
			if (user.getPassword().equals(password)) {
				System.out.println("login done");
			} else {
				throw new UserNotFoundException("password not correct");
			}

		} else {
			throw new UserNotFoundException("email not correct");
		}
		return optional1;
	}
}
