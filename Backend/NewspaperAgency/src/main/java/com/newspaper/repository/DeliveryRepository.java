package com.newspaper.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.newspaper.dao.DeliveryDAO;
import com.newspaper.entity.Customer;
import com.newspaper.entity.Delivery;
import java.time.LocalDateTime;


public interface DeliveryRepository extends JpaRepository<Delivery, Integer>{

	@Query("SELECT d FROM Delivery d WHERE DATE(d.deliveryTime) = :date")
    List<Delivery> findAllByDeliveryDate(@Param("date") LocalDate date);
	
	@Query("SELECT d FROM Delivery d WHERE d.delivered = false AND d.dailyDeliveryLog.deliveryExecutive.id = :delivery_Executive_Id")
	List<Delivery> findUndeliveredByDeliveryExecutive(@Param("delivery_Executive_Id") Integer userId);
	
	@Query(value = "SELECT DATE(d.delivery_time) AS delivery_date, COUNT(d.id) " +
            "FROM delivery d " +
            "GROUP BY DATE(d.delivery_time)", nativeQuery = true)
	List<Object[]> countDeliveriesPerDay();

	boolean existsByCustomer_IdAndDeliveryTimeBetween(Integer customerId, LocalDateTime start, LocalDateTime end);
	

	@Query("SELECT new com.newspaper.dao.DeliveryDAO(d.id, d.customer.userName, d.customer.fullAddress, d.delivered, d.deliveryTime, d.dailyDeliveryLog.id, d.customer.id) " +
		       "FROM Delivery d " +
		       "WHERE d.customer.id = :customerId AND DATE(d.deliveryTime) = CURRENT_DATE")
	List<DeliveryDAO> findTodaysDeliveriesByCustomerId(@Param("customerId") Integer customerId);


}
