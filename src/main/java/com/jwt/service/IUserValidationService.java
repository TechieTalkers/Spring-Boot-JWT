package com.jwt.service;

import com.jwt.entity.UserDetails;

public interface IUserValidationService {

	public UserDetails authenticateUser(UserDetails userDetails) throws Exception;

	public boolean isValidUsername(String username);

}
