package com.javainuse.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

import com.javainuse.model.DAOUser;

@Repository
public interface UserDao extends CrudRepository<DAOUser, Integer> {
	DAOUser findByRole(String role);
	DAOUser findByEmail(String email);
	//DAOUser findByName(String username);
	
	
}