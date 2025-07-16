package com.newspaper.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.newspaper.dao.UndeliveredCustomerDAO;
import com.newspaper.entity.Customer;
import com.newspaper.entity.Delivery;

import java.time.LocalDate;
import java.util.List;


public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	Optional<Customer> findById(Integer id);
	
	@Query("SELECT new com.newspaper.dao.UndeliveredCustomerDAO(c.userId, c.userName, c.fullName, c.fullAddress, c.sectorZone) " +
		       "FROM Customer c " +
		       "WHERE c.userId NOT IN (" +
		       "   SELECT d.customer.userId FROM Delivery d WHERE DATE(d.deliveryTime) = :date)")
	List<UndeliveredCustomerDAO> findCustomersWithoutDeliveryOn(@Param("date") LocalDate date);
	
	@Query("SELECT COUNT(c) FROM Customer c " +
		       "WHERE c.userId NOT IN (" +
		       "   SELECT d.customer.userId FROM Delivery d WHERE DATE(d.deliveryTime) = :date)")
	Long countCustomersWithoutDeliveryOn(@Param("date") LocalDate date);


	@Query("SELECT COUNT(c) FROM Customer c " +
		       "WHERE c.userId IN (" +
		       "   SELECT d.customer.userId FROM Delivery d WHERE DATE(d.deliveryTime) = :date)")
	Long countCustomersWithDeliveryOn(@Param("date") LocalDate date);

}
