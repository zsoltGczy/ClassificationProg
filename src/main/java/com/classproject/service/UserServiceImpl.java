package com.classproject.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.classproject.repository.RoleRepository;
import com.classproject.repository.UserRepository;
import com.classproject.usersandroles.Role;
import com.classproject.usersandroles.User;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

	private final String USER_ROLE = "USER";
	

	private UserRepository userRepo;
	private RoleRepository roleRepo;
	
	private EmailService emailService;
	
	
	@Autowired
	public UserServiceImpl(UserRepository userRepo, RoleRepository roleRepo) {
		this.userRepo = userRepo;
		this.roleRepo = roleRepo;
	}
	
	@Autowired
	public void setEmailService(EmailService emailService) {
		this.emailService = emailService;
	}
	
	
	




	@Override
	public String userActivation(String code) {
		User user = userRepo.findByActivation(code);
		
		if (user == null)
		    return "noresult";
		
		user.setEnabled(true);
		user.setActivation("");
		userRepo.save(user);
		
		return "ok";
	}



	@Override
	public String registerUser(User userToRegister) {
		User userCheck = userRepo.findByEmail(userToRegister.getEmail());
		
		if(userCheck != null)
			return "alreadyExists";
		
		Role userRole = roleRepo.findByRole(USER_ROLE);
		String randomKey = generateKey();
		
		if(userRole != null) {
			userToRegister.getRoles().add(userRole);
		}else {
			userToRegister.addRoles(USER_ROLE);
		}
		
		userToRegister.setEnabled(false);
		userToRegister.setActivation(randomKey);
		
		if(!userToRegister.getPassword().equals(userToRegister.getPasswordCheck())) {
			return "passWordCheckIsFalse";
		}
		
		userRepo.save(userToRegister);
		emailService.sendMessage(userToRegister, randomKey);
		
		return "ok";
	}
	
	private String generateKey() {
		Random random = new Random();
		char[] word = new char[16];
		
		for(int i = 0; i < word.length; i++) {
			word[i] = (char) ('a' + random.nextInt(26));
		}
		
		return new String(word);
	}




	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		User user = findByEmail(username);
		
		if (user == null) {
			throw new UsernameNotFoundException(username);
		}
		
		return new UserDetailsImpl(user);
	}



	@Override
	public User findByEmail(String email) {
		return userRepo.findByEmail(email);
	}
	
	
}
