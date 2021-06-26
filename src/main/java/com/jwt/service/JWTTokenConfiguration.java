package com.jwt.service;

import java.io.Serializable;
import java.time.Instant;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.jwt.entity.UserDetails;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTTokenConfiguration implements Serializable {

	private static final long serialVersionUID = 45820481437901L;

	public static final long tokenValidity = 60000;

	@Value("${jwt.secret.key}")
	private String secretKey;

	@Value("${jwt.secret.sign.key}")
	private String secretSignKey;

	@Autowired
	UserValidationServiceImpl userValidationServiceImpl;

	public String generateJWTToken(UserDetails userdata) {
		final Date currentDate = Date.from(Instant.now());
		final Date expiryDate = Date.from(Instant.now().plusMillis(tokenValidity));
		final String issuer = userdata.getUsername();

		JwtBuilder builder = Jwts.builder().setSubject(secretKey).setIssuer(issuer).setExpiration(expiryDate)
				.setIssuedAt(currentDate).signWith(SignatureAlgorithm.HS512, secretSignKey);
		return builder.compact();
	}

	public boolean validatedJWTToken(String jWT) {
		boolean isValid = true;
		if (StringUtils.isBlank(jWT)) {
			return !isValid;
		}

		Jws<Claims> claims = Jwts.parser().setSigningKey(secretSignKey).parseClaimsJws(jWT);
		if (claims == null) {
			return !isValid;
		}
		final String issuer = StringUtils.trim(claims.getBody().getIssuer());

		if (!userValidationServiceImpl.isValidUsername(issuer)) {
			return !isValid;
		}
		return isValid;
	}

}
