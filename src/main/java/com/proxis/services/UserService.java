package com.proxis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proxis.entities.User;
import com.proxis.providers.Constants;
import com.proxis.repositories.UserRepository;


@Service("userService")
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public User findByConfirmationToken(String confirmationToken) {
		return userRepository.findByConfirmationToken(confirmationToken);
	}

	public User findUserByResetToken(String resetToken) {
		return userRepository.findByResetToken(resetToken);
	}

	public User findUserByEmail(String email) {
		return userRepository.findByUserEmail(email);

	};
	

	public void saveUser(User user) {
		userRepository.save(user);
	}

}
