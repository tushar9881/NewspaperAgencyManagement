package com.newspaper.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.newspaper.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	Optional<User> findByUserName(String userName);
	
	@Query("SELECT u FROM User u WHERE u.id = :userId")
	Optional<User> findByUserId(@Param("userId") Integer userId);

	
	List<User> findByRole(String role);
	
	List<User> findByFullNameContainingIgnoreCase(String fullName);
	
	List<User> findByEmailContainingIgnoreCase(String email);
	
	@Query("SELECT u.role AS role, COUNT(u) AS count FROM User u GROUP BY u.role")
	List<RoleCount> countUsersByRole();
	interface RoleCount{
		String getRole();
		Integer getCount();
	}
}
