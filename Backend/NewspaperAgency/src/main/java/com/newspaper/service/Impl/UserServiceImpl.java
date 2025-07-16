package com.newspaper.service.Impl;

import java.util.Optional;

import org.hibernate.query.NativeQuery.ReturnableResultNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newspaper.dao.PasswordChangeRequest;
import com.newspaper.entity.Admin;
import com.newspaper.entity.User;
import com.newspaper.exception.ResourceNotFoundException;
import com.newspaper.repository.AdminRepository;
import com.newspaper.repository.UserRepository;
import com.newspaper.service.UserService;

import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {


	@Autowired
	public UserRepository userRepository;

    UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User login(String userName, String password) {
        return userRepository.findByUserName(userName)
            .filter(u -> u.getPassword().trim().equals(password.trim()))
            .orElseThrow(() -> new ResourceNotFoundException("Invalid Login details please check and login again"));
    }

	@Override
	public boolean changePassword(Integer userId, PasswordChangeRequest request) {
		System.out.println("Attempting to change password for: " +userId );
		Optional<User> userfind = userRepository.findById(userId);
		if(userfind.isPresent()) {
			User user = userfind.get();

	        System.out.println("Old password received: " + request.getOldPassword());
	        System.out.println("Password in DB: " + user.getPassword());
			if(user.getPassword().equals(request.getOldPassword())) {
				user.setPassword(request.getNewPassword());
				userRepository.save(user);
				System.out.println("New password set: " + user.getPassword());
	            System.out.println("Password changed successfully.");
				return true;			
				}else {
				System.out.println("Old password does not match.");
				return false;
			}
		}else {
			System.out.println("User not found");
			return false;
		}
	}


	
	
	

}
