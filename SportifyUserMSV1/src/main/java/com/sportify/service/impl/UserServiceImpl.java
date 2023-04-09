package com.sportify.service.impl;

import java.util.Collection;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sportify.dto.UserDTO;
import com.sportify.entities.User;
import com.sportify.exceptions.SportifyAppExceptions;
import com.sportify.repository.ReportRepository;
import com.sportify.repository.UserRepository;
import com.sportify.entities.MyUserDetails;
import com.sportify.entities.Report;
import com.sportify.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService,UserDetailsService {

	
	private static final long serialVersionUID = 1L;

	@Autowired
	public UserRepository userRepository;
	
	@Autowired
	public ReportRepository reportRepository;

	@Override
	public UserDTO signUp(User user) {
		return UserDTO.convertToDTO(userRepository.save(user));
	}

	@Override
	public UserDTO login(User user) throws SportifyAppExceptions {
		Optional<User> loginUser = userRepository.findByEmailId(user.getEmailId());
		User validUser = loginUser.get();
		return UserDTO.convertToDTO(validUser);
	}

	@Override
	public User editProfile(User user) {
		Optional<User> loginUser = userRepository.findByEmailId(user.getEmailId());
		User validUser = loginUser.get();

		validUser.updateDetails(user);

		userRepository.flush();
		return validUser;
	}

	@Override
	public UserDTO getUserById(Integer userId) {
		Optional<User> getUser = userRepository.findById(userId);
		return UserDTO.convertToDTO(getUser.orElseThrow());
	}

	@Override
	public Report submitReport(Report userReport) {
		return reportRepository.save(userReport);
	}

	@Override
	public UserDetails loadUserByUsername(String emailId) throws UsernameNotFoundException {
		Optional<User> user = userRepository.findByEmailId(emailId);
		user.orElseThrow(() -> new UsernameNotFoundException("Not found "+emailId));
		return user.map(MyUserDetails::new).get();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

}