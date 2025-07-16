package com.newspaper.MVControllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.newspaper.dao.DeliveryDAO;
import com.newspaper.entity.Customer;
import com.newspaper.entity.Subscription;
import com.newspaper.entity.User;
import com.newspaper.repository.SubscriptionRepository;
import com.newspaper.service.CustomerService;

import lombok.AllArgsConstructor;
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/customer")
@AllArgsConstructor
public class CustomerController {

    private final SubscriptionRepository subscriptionRepository;
	@Autowired
	private CustomerService customerService;


    CustomerController(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }
    
    @GetMapping("/{id}")
	public ResponseEntity<Optional<User>> getCustomerById(@PathVariable("id") Integer userId){
		Optional<User> user = customerService.getUserById(userId);
		return ResponseEntity.ok(user);
	}
	
	
	//Build Add Customer REST API
	@PostMapping("/onboard")
	public ResponseEntity<Customer> onboardCustomer(@RequestBody Customer customer){
		Customer savedCustomer = customerService.onboardCustomer(customer);
		return ResponseEntity.ok(savedCustomer);
	}
	
	
	//Build Update Customer REST API
	@PutMapping("/updateinfo")
	public ResponseEntity<Customer> updateCustomer(@RequestBody Customer updatedCustomer) {
		Customer customer = customerService.updateCustomer( updatedCustomer);
		return ResponseEntity.ok(customer);
	}
	
	//Build Delete Customer REST API
	@DeleteMapping("/subscription/{id}")
	public ResponseEntity<String> deleteCustomer(@PathVariable("id") Integer customerId) {
		customerService.deletesubscription(customerId);
		return ResponseEntity.ok("Customer Deleted Successfully");
	}
	
	@GetMapping("/subscriptions/my/{userName}")
	public ResponseEntity<List<Subscription>> getMySubscriptions(@PathVariable String userName){
		List<Subscription> subscriptions = subscriptionRepository.findByCustomerUserName(userName);
		return ResponseEntity.ok(subscriptions);
	}
	
	@GetMapping("/deliveries/today/customer/{customerId}")
	public ResponseEntity<List<DeliveryDAO>> getTodaysDeliveriesForCustomer(@PathVariable Integer customerId) {
	    List<DeliveryDAO> deliveries = customerService.getTodaysDeliveriesForCustomer(customerId);
	    return ResponseEntity.ok(deliveries);
	}

}