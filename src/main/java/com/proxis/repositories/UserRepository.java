package com.proxis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proxis.entities.User;


/**
 * @author Jeanyannick
 *
 */
public interface UserRepository extends JpaRepository<User, Long> {


	User findUserByUserName(String userName);
	User findByConfirmationToken(String confirmationToken);
	User findByResetToken(String resetToken);
	User findByUserEmail(String userEmail);
	
}
