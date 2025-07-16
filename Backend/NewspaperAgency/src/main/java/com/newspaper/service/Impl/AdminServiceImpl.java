package com.newspaper.service.Impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newspaper.dao.DEDAO;
import com.newspaper.dao.DailyDeliveryLogDAO;
import com.newspaper.dao.UndeliveredCustomerDAO;
import com.newspaper.entity.Customer;
import com.newspaper.entity.DailyDeliveryLog;
import com.newspaper.entity.Delivery;
import com.newspaper.entity.DeliveryExecutive;
import com.newspaper.entity.User;
import com.newspaper.entity.Subscription;
import com.newspaper.exception.ResourceNotFoundException;
import com.newspaper.repository.CustomerRepository;
import com.newspaper.repository.DailyDeliveryLogRepository;
import com.newspaper.repository.DeliveryExecutiveRepository;
import com.newspaper.repository.DeliveryRepository;
import com.newspaper.repository.SubscriptionRepository;
import com.newspaper.repository.UserRepository;
import com.newspaper.service.AdminService;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AdminServiceImpl implements AdminService{

    private final DeliveryRepository deliveryRepository;

    private final SubscriptionRepository subscriptionRepository;

    private final DeliveryExecutiveRepository deliveryExecutiveRepository;

    private final CustomerRepository customerRepository;

	@Autowired
	public UserRepository userRepository;

    AdminServiceImpl(CustomerRepository customerRepository, DeliveryExecutiveRepository deliveryExecutiveRepository, SubscriptionRepository subscriptionRepository, DeliveryRepository deliveryRepository) {
        this.customerRepository = customerRepository;
        this.deliveryExecutiveRepository = deliveryExecutiveRepository;
        this.subscriptionRepository = subscriptionRepository;
        this.deliveryRepository = deliveryRepository;
    }
	
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

	@Override
	public List<User> getAllCustomers() {
		return userRepository.findByRole("customer");
	}

	@Override
	public List<User> getAllDeliveryExecutive() {
		return userRepository.findByRole("delivery_executive");
	}

	@Override
	public List<User> searchUsersByName(String name) {
		return userRepository.findByFullNameContainingIgnoreCase(name);
	}

	@Override
	public List<User> getUserByEmail(String email) {
		return userRepository.findByEmailContainingIgnoreCase(email);
	}

	@Override
	public Customer onboardCustomer(Customer customer) {
	    if (customer.getSubscriptions() != null && !customer.getSubscriptions().isEmpty()) {
	        for (Subscription sub : customer.getSubscriptions()) {
	            sub.setCustomer(customer); // set the customer before saving
	        }
	    }

	    // Now save the customer along with the subscriptions (cascade will handle it)
	    return customerRepository.save(customer);
	}




	@Override
	public Optional<Customer> getCustomerById(Integer userId) {
		
		return customerRepository.findById(userId);
	}

	@Override
	public Customer updateCustomer(Integer userId, Customer updatedCustomer) {
		Customer existingCustomer = customerRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("Customer with given "+userId+"doesn't exist"));
		
		existingCustomer.setUserName(updatedCustomer.getUserName());
		existingCustomer.setFullName(updatedCustomer.getFullName());
		existingCustomer.setEmail(updatedCustomer.getEmail());
		existingCustomer.setContactNumber(updatedCustomer.getContactNumber());
		existingCustomer.setFullAddress(updatedCustomer.getFullAddress());
		existingCustomer.setSectorZone(updatedCustomer.getSectorZone());
		
		return customerRepository.save(existingCustomer);
	}

	@Override
	public void deleteCustomer(Integer userId) {
		if(userId==null) {
			throw new ResourceNotFoundException("Customer ID you entered is not in customer table it may be ID assigned to Delivery Executive or Admin");
		}
		Optional<User> optionaluser = userRepository.findById(userId);
		User user = optionaluser.get();
		if (!(user instanceof Customer)) {
		    throw new ResourceNotFoundException("ID belongs to non-customer user.");
		}

		Customer existingCustomer = customerRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("Customer with given customer ID"+userId+"doesn't exist"));
		
		customerRepository.deleteById(userId);
		
	}

	@Override
	public DeliveryExecutive onboarDeliveryExecutive(DeliveryExecutive deliveryExecutive) {
		return deliveryExecutiveRepository.save(deliveryExecutive);
	}

	@Override
	public Optional<DeliveryExecutive> getDeliveryExecutiveById(Integer userId) {
		return deliveryExecutiveRepository.findById(userId);
	}

	@Override
	public DeliveryExecutive updateDeliveryExecutive(Integer userId, DeliveryExecutive updatedDeliveryExecutive) {
	    DeliveryExecutive existingdelivDeliveryExecutive = deliveryExecutiveRepository
	        .findById(userId)
	        .orElseThrow(() -> new ResourceNotFoundException("Delivery Executive with given " + userId + " doesn't exist"));

	    existingdelivDeliveryExecutive.setUserName(updatedDeliveryExecutive.getUserName());
	    existingdelivDeliveryExecutive.setFullName(updatedDeliveryExecutive.getFullName()); // ✅ FIXED
	    existingdelivDeliveryExecutive.setEmail(updatedDeliveryExecutive.getEmail());
	    existingdelivDeliveryExecutive.setContactNumber(updatedDeliveryExecutive.getContactNumber());
	    existingdelivDeliveryExecutive.setZoneAssigned(updatedDeliveryExecutive.getZoneAssigned());
	    if (updatedDeliveryExecutive.getPassword() != null && !updatedDeliveryExecutive.getPassword().isEmpty()) {
	        existingdelivDeliveryExecutive.setPassword(updatedDeliveryExecutive.getPassword());
	    }
	    return deliveryExecutiveRepository.save(existingdelivDeliveryExecutive);
	}

	@Override
	public void deleteDeliveryExecutive(Integer userId) {
		DeliveryExecutive existingDeliveryExecutive = deliveryExecutiveRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("Delivery Executive with given ID"+userId+"doesn't exist"));
		deliveryExecutiveRepository.deleteById(userId);
	}

	@Override
	public Map<String, Integer> getUsersGroupedByRoles() {
		List<UserRepository.RoleCount> rolecountofUsers = userRepository.countUsersByRole();
		return rolecountofUsers.stream().collect(Collectors.toMap(UserRepository.RoleCount::getRole, UserRepository.RoleCount::getCount));
	}

	@Override
	public long getsubscriptionscount() {
		return subscriptionRepository.count();
	}

	@Override
	public List<UndeliveredCustomerDAO> getCustomersWithoutDeliveryOn(LocalDate date) {
        return customerRepository.findCustomersWithoutDeliveryOn(date);
    }

	@Override
	public long countUndeliveredCustomersOn(LocalDate date) {
		// TODO Auto-generated method stub
		return customerRepository.countCustomersWithoutDeliveryOn(date);
	}

	@Override
	public long countDeliveredCustomersOn(LocalDate date) {
		// TODO Auto-generated method stub
		return customerRepository.countCustomersWithDeliveryOn(date);
	}

	@Override
	public List<Delivery> getDeliveriesByDate(LocalDate date) {
		// TODO Auto-generated method stub
		return deliveryRepository.findAllByDeliveryDate(date);
	}

	@Autowired
    private DeliveryExecutiveRepository deliveryExecutiveRepository1;

	public List<DEDAO> getAllExecutives() {
	    List<DeliveryExecutive> executives = deliveryExecutiveRepository.findAll();

	    return executives.stream()
	            .map(exec -> new DEDAO(
	                    exec.getId(),
	                    exec.getUserName(),
	                    exec.getPassword(),
	                    exec.getFullName(),
	                    exec.getEmail(),
	                    exec.getContactNumber(),
	                    exec.getRole(),
	                    exec.getZoneAssigned()
	            ))
	            .collect(Collectors.toList());
	}


	

}
