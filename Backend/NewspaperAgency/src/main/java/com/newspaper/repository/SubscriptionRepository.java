package com.newspaper.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.newspaper.entity.Subscription;

import jakarta.transaction.Transactional;

public interface SubscriptionRepository extends JpaRepository<Subscription, Integer>{

	long count();
	
	@Transactional
	@Modifying
	@Query("DELETE FROM Subscription s WHERE s.customer.id = :customerId")
	void deleteByCustomerId(@Param("customerId") Integer customerId);


	List<Subscription> findByCustomerUserName(String userName);
}
