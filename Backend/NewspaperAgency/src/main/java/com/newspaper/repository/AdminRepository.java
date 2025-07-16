package com.newspaper.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.newspaper.entity.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer>{

	Optional<Admin> findByUserName(String userName);
}
