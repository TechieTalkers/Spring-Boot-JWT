package com.jwt.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jwt.entity.UserDetails;

@Repository
public interface UserRepository extends CrudRepository<UserDetails, String> {

	public UserDetails findByUsername(String username);

}
