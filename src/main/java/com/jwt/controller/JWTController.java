package com.jwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.bean.JwtResponse;
import com.jwt.entity.UserDetails;
import com.jwt.service.JWTTokenConfiguration;
import com.jwt.service.UserValidationServiceImpl;

@RestController
public class JWTController {

	@Autowired
	UserValidationServiceImpl userValidationServiceImpl;

	@Autowired
	JWTTokenConfiguration jwtTokenConfiguration;

	@PostMapping("/generatejwt")
	public ResponseEntity<?> generateJwtToken(@RequestBody UserDetails userDetails) throws Exception {
		userValidationServiceImpl.authenticateUser(userDetails);
		String token = jwtTokenConfiguration.generateJWTToken(userDetails);
		return ResponseEntity.ok(new JwtResponse(token, "JWT Token generated Successfully"));

	}

}
