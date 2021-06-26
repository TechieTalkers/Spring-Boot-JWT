package com.jwt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jwt.entity.UserDetails;
import com.jwt.repo.UserRepository;

@Service
public class UserValidationServiceImpl implements IUserValidationService {

	@Autowired
	UserRepository jwtRepository;

	@Override
	public UserDetails authenticateUser(UserDetails userDetails) throws Exception {
		UserDetails userData = jwtRepository.findByUsername(userDetails.getUsername());
		if (userData.getPassword().equals(userDetails.getPassword())) {
			return userDetails;
		} else {
			throw new Exception("INVALID_CREDENTIALS, Please provide Valid username and Password");
		}

	}

	@Override
	public boolean isValidUsername(String username) {
		UserDetails userData = jwtRepository.findByUsername(username);
		if (userData != null) {
			return true;
		}
		return false;
	}

}
