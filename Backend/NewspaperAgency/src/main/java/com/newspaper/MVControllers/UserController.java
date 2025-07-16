package com.newspaper.MVControllers;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.couchbase.CouchbaseProperties.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.newspaper.dao.PasswordChangeRequest;
import com.newspaper.dao.UserDAOforLOGIN;
import com.newspaper.entity.User;
import com.newspaper.exception.ResourceNotFoundException;
import com.newspaper.service.UserService;
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	public UserService userService;

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody UserDAOforLOGIN userDAOforLOGIN) {
	    try {
	        User user = userService.login(userDAOforLOGIN.getUserName(), userDAOforLOGIN.getPassword());

	        // Return a JSON map instead of a plain string
	        Map<String, String> response = new HashMap<>();
	        response.put("userName", user.getUserName());
	        response.put("fullName", user.getFullName());
	        response.put("role", user.getRole());// <-- this is important
	        response.put("id", String.valueOf(user.getId()));
	        return ResponseEntity.ok(response);
	    } catch (ResourceNotFoundException e) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Credentials");
	    }
	}

	
	@PutMapping("/changepassword/{userId}")//WORKING ONLY FOR THE CUTSOMER WANT TO WORK FOR ALL
	public ResponseEntity<String> updatePassword(@PathVariable Integer userId,@RequestBody PasswordChangeRequest request) {
		boolean success = userService.changePassword(userId, request);
		
		if(success) {
			return ResponseEntity.ok("Password Changed Successfully");
		}
		else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Old password is incorrect or user may not exist.Please check and Try again!!!");
		}
	}
}
