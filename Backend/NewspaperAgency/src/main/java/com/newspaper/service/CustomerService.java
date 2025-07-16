package com.newspaper.service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.Flow.Subscription;

import com.newspaper.dao.CustomerDAO;
import com.newspaper.dao.DeliveryDAO;
import com.newspaper.entity.Customer;
import com.newspaper.entity.User;
import com.newspaper.repository.SubscriptionRepository;

public interface CustomerService {
	
	Customer onboardCustomer(Customer customer);
	
	
	Customer updateCustomer(Customer updateCustomer);
	
	void deletesubscription(Integer customer_id);

	List<Subscription> getSubscriptionsByUserName(String userName);
	
	Optional<User> getUserById(Integer userId);//CUSTOMER
	
	List<DeliveryDAO> getTodaysDeliveriesForCustomer(Integer customerId);
	
}