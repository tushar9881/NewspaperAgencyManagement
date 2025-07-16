package com.newspaper.service;

import com.newspaper.dao.PasswordChangeRequest;
import com.newspaper.entity.User;

public interface UserService {

	User login(String userName, String password);
	
	boolean changePassword(Integer userId, PasswordChangeRequest request);

}
