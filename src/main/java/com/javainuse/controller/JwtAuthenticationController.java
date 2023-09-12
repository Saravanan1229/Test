package com.javainuse.controller;



import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.javainuse.config.JwtTokenUtil;
import com.javainuse.dao.UserDao;
import com.javainuse.model.DAOUser;
import com.javainuse.model.JwtRequest;
import com.javainuse.model.UserDTO;
import com.javainuse.service.JwtUserDetailsService;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserDao userDao;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken
	(@RequestBody JwtRequest authenticationRequest) throws Exception {
try {
		authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());

		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getEmail());
		DAOUser user = userDao.findByEmail(authenticationRequest.getEmail());

			if(user.getRole().equalsIgnoreCase(authenticationRequest.getRole())) {
				
				final String token = jwtTokenUtil.generateToken(userDetails);

				return ResponseEntity.ok("login successful...! "+"Token:"+(token));
			}else {
				return ResponseEntity.status(400).body("Role Error");
			}
			
		

}catch(Exception e){

	return ResponseEntity.status(400).body(e.getMessage());
}
//		String successMessage = "Request was successful.";
//        return ResponseEntity.ok(new JwtResponse(token)).body(successMessage);
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<?> saveUser(@RequestBody UserDTO user) throws Exception {
		/*userDetailsService.save(user);
		UserDetails userDetails = User(user.getUsername(), user.getPassword(),
			
				new ArrayList<>());
		final String token = jwtTokenUtil.generateToken(userDetails);
		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(user.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetails);*/
		
		
		try {
	        if (user.getUsername() == null || user.getUsername().isEmpty()) {
	            return ResponseEntity.badRequest().body("Username cannot be null or empty");
	        }if(user.getPassword()==null || user.getPassword().isEmpty()) {
	        	return ResponseEntity.badRequest().body("Password cannont be null");
	        }if(user.getRole()==null||user.getRole().isEmpty()) {
	        	return ResponseEntity.badRequest()
	        			.body("Role not null");
	        }if(user.getRole().matches("Emp||Empr")) {
	        	return ResponseEntity.status(200).body("Registration successful" + userDetailsService.save(user));
	    	    
	        }else {
	        	return ResponseEntity.status(400)
	        			.body("Select Emp or Empr");
	        }
	       
	     // return ResponseEntity.status(200).body("Registration successful" + userDetailsService.save(user));
	    
		}catch (Exception e) {
	        return ResponseEntity.status(500).body("An error occurred during registration");
	    }
	}
	
	
	
	
	
	
	
	
	

	private void authenticate(String email, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}