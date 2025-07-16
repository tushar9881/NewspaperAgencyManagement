package com.newspaper.service.Impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newspaper.entity.Subscription;
import com.newspaper.entity.User;
import com.newspaper.dao.CustomerDAO;
import com.newspaper.dao.DeliveryDAO;
import com.newspaper.entity.Customer;
import com.newspaper.entity.Delivery;
import com.newspaper.exception.ResourceNotFoundException;
import com.newspaper.mapper.CustomerMapper;
import com.newspaper.repository.CustomerRepository;
import com.newspaper.repository.DeliveryRepository;
import com.newspaper.repository.SubscriptionRepository;
import com.newspaper.repository.UserRepository;
import com.newspaper.service.CustomerService;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final UserRepository userRepository;

    private final SubscriptionRepository subscriptionRepository;
    @Autowired
    private DeliveryRepository deliveryRepository;
    
	@Autowired
	private CustomerRepository customerRepository;

    CustomerServiceImpl(SubscriptionRepository subscriptionRepository, UserRepository userRepository) {
        this.subscriptionRepository = subscriptionRepository;
        this.userRepository = userRepository;
    }
	
	@Override
	public Customer onboardCustomer(Customer customer) {
		return customerRepository.save(customer);
	}

	

	@Override
	public Customer updateCustomer(Customer updateCustomer) {
		Customer customer = null;
		customer.setUserName(updateCustomer.getUserName());
		customer.setFullName(updateCustomer.getFullName());
		customer.setPassword(updateCustomer.getPassword());
		customer.setEmail(updateCustomer.getEmail());
		customer.setContactNumber(updateCustomer.getContactNumber());
		customer.setFullAddress(updateCustomer.getFullAddress());
		customer.setSectorZone(updateCustomer.getSectorZone());
		customer.setRegistrationDate(updateCustomer.getRegistrationDate());
		
		Customer updatedCustomerObj = customerRepository.save(customer);
		
		return customerRepository.save(updatedCustomerObj);
	}

	@Override
	public void deletesubscription(Integer customerId) {
	    subscriptionRepository.deleteByCustomerId(customerId);
	}

	@Override
	public List getSubscriptionsByUserName(String userName) {
		return subscriptionRepository.findByCustomerUserName(userName);
	}

	@Override
	public Optional<User> getUserById(Integer userId) {
		// TODO Auto-generated method stub
		return userRepository.findById(userId);
	}

	@Override
	public List<DeliveryDAO> getTodaysDeliveriesForCustomer(Integer customerId) {
	    return deliveryRepository.findTodaysDeliveriesByCustomerId(customerId);
	}


	


}
