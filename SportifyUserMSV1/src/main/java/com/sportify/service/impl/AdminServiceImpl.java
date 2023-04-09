package com.sportify.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sportify.dto.UserAdminDTO;
import com.sportify.entities.Report;
import com.sportify.entities.User;
import com.sportify.repository.ReportRepository;
import com.sportify.repository.UserRepository;
import com.sportify.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	public UserRepository userRepository;
	
	@Autowired
	public ReportRepository reportRepository;
	
	@Override
	public List<UserAdminDTO> getAllUsers() {
		List<User> allUsers = userRepository.findAll(); 
		List<UserAdminDTO> allUsersDTO = new ArrayList<UserAdminDTO>();
		allUsers.forEach(user -> {
			allUsersDTO.add(new UserAdminDTO(user.getUserId(), user.getName(), user.getCity(), user.getEmailId(), 
					user.getGender(), user.getBlockedByAdmin(), user.getStrikes()));
		});
		return allUsersDTO;
	}
	
	@Override
	public UserAdminDTO blockUser(UserAdminDTO userAdminDTO) {
		Optional<User> user = userRepository.findById(userAdminDTO.getUserId());
		User user2 = user.get();
		user2.setBlockedByAdmin(true); 
		
		return new UserAdminDTO(user2.getUserId(), user2.getName(), user2.getCity(),user2.getEmailId(), user2.getGender(), 
				user2.getBlockedByAdmin(), user2.getStrikes());
	}

	@Override
	public List<Report> getAllReports() {
		return reportRepository.findAll();
	}

}
