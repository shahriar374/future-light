package com.rectifier.future_light.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Service;

import com.rectifier.future_light.dto.SignupDto;
import com.rectifier.future_light.model.Users;
import com.rectifier.future_light.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	JdbcUserDetailsManager jdbcUserDetailsManager;

	@Autowired
	PasswordEncoder passwordEncoder;

	public Users getCurrentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentUsername = authentication.getName();

		return userRepository.findById(currentUsername).orElse(null);
	}

	public void updateUser(SignupDto dto) {
		userRepository.save(dtoToUser(dto));
	}
	
	public List<Users> findAllUsers() {
		return userRepository.findAll();
	}

	public Page<Users> findAllUsersPage(int pageNo, int pageSize, String sortBy) {
		Sort sort = Sort.by(Sort.Direction.ASC, sortBy);
		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
		
		return userRepository.findAll(pageable);
	}

	public void deleteUser(String username) {
		jdbcUserDetailsManager.deleteUser(username);
	}

	public Users dtoToUser(SignupDto dto) {
		Users user = new Users();
		user.setUsername(dto.getUsername());
		user.setFullname(dto.getFullname());
		user.setEmail(dto.getEmail());
		user.setGender(dto.getGender());
		user.setAge(dto.getAge());

		if (dto.getPassword().isEmpty()) {
			Users tmpUser = userRepository.findById(dto.getUsername()).orElse(null);
			user.setPassword(tmpUser.getPassword());
		} else {
			user.setPassword(passwordEncoder.encode(dto.getPassword()));
		}

		return user;
	}

}
