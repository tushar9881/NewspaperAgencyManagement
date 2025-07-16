package com.newspaper.mapper;

import com.newspaper.dao.AdminDAO;
import com.newspaper.entity.Admin;

public class AdminMaper {
	
	public static AdminDAO mapToAdminDAO(Admin admin) {
		return new AdminDAO(admin.getId(),
				admin.getUserName(),
				admin.getFullName(),
				admin.getPassword(),
				admin.getEmail(),
				admin.getContactNumber());
	}
	
	private static Admin mapToAdmin(AdminDAO adminDAO) {
		return new Admin(adminDAO.getUserId(),
				adminDAO.getUserName(),
				adminDAO.getFullName(),
				adminDAO.getEmail(),
				adminDAO.getContactNumber());
	}
}
