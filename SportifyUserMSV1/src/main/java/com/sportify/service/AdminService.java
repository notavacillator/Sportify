package com.sportify.service;

import java.util.List;

import com.sportify.dto.UserAdminDTO;
import com.sportify.entities.Report;

public interface AdminService {

	public List<UserAdminDTO> getAllUsers();
	public UserAdminDTO blockUser(UserAdminDTO userAdminDTO);
	public List<Report> getAllReports();
}
