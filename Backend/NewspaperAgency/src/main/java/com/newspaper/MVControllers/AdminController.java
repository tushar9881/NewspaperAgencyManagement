package com.newspaper.MVControllers;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.newspaper.dao.DEDAO;
import com.newspaper.dao.DeliveryDAO;
import com.newspaper.dao.UndeliveredCustomerDAO;
import com.newspaper.entity.Customer;
import com.newspaper.entity.Delivery;
import com.newspaper.entity.DeliveryExecutive;
import com.newspaper.entity.User;
import com.newspaper.repository.DeliveryExecutiveRepository;
import com.newspaper.service.AdminService;
import com.newspaper.service.CustomerService;
import com.newspaper.service.Impl.CustomerServiceImpl;
import lombok.AllArgsConstructor;
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/admin")
@AllArgsConstructor
public class AdminController {



	@Autowired
	public AdminService adminService;


	@GetMapping("/users")
	public List<User> getAllUsers(){
		return adminService.getAllUsers();
	}
	
	@GetMapping("/customers")
	public List<User> getAllCustomers() {
		return adminService.getAllCustomers();
	}

	 @GetMapping("/delivery_executive")
	 public ResponseEntity<List<DEDAO>> getAllExecutives() {
	     return ResponseEntity.ok(adminService.getAllExecutives());
	 }

	
	@GetMapping("/searchbyname")
	public List<User> searchUsersByName(@RequestParam String name){
		return adminService.searchUsersByName(name);
	}
	
	@GetMapping("/searchbyemail")
	public List<User> searchUsersByMail(@RequestParam String email){
		return adminService.getUserByEmail(email);
	}
	
	@PostMapping("/onboardcustomer")
	public ResponseEntity<Customer> onboardCustomer(@RequestBody Customer customer){
		Customer savecustomer = adminService.onboardCustomer(customer);
		return ResponseEntity.ok(savecustomer);
	}
	
	//Build Get Customer REST API
	@GetMapping("/customer/{id}")
	public ResponseEntity<Optional<Customer>> getCustomerById(@PathVariable("id") Integer userId){
		Optional<Customer> customer = adminService.getCustomerById(userId);
		return ResponseEntity.ok(customer);
	}
	
	@PutMapping("/{updateid}")
	public ResponseEntity<Customer> updateCustomerinfo(@PathVariable("updateid") Integer Id,@RequestBody Customer updatedCustomer){
		Customer updated = adminService.updateCustomer(Id, updatedCustomer);
		return ResponseEntity.ok(updated);
	}
		
	@DeleteMapping("/{deleteid}")
	public ResponseEntity<String> deleteCustomer(@PathVariable("deleteid") Integer userId){
		adminService.deleteCustomer(userId);
		return ResponseEntity.ok("Customer deleted successfully");
	}
	
	@PostMapping(value = "/onboardDeliveryExecutive", consumes = "application/json")
	public ResponseEntity<DeliveryExecutive> onboardDeliveryExecutive(@RequestBody DeliveryExecutive deliveryExecutive){
		DeliveryExecutive saveDeliveryExecutive = adminService.onboarDeliveryExecutive(deliveryExecutive);
		return ResponseEntity.ok(saveDeliveryExecutive);
	}
	
	@GetMapping("/deliveryexecutive/{id}")
	public ResponseEntity<Optional<DeliveryExecutive>> getDeliveryExecutiveById(@PathVariable("id") Integer userId){
		Optional<DeliveryExecutive> deliveryexecutive = adminService.getDeliveryExecutiveById(userId);
		return ResponseEntity.ok(deliveryexecutive);
	}
	
	@PutMapping("/deliveryexecutive/{updateDEid}")
	public ResponseEntity<DeliveryExecutive> updateDeliveryExecutiveInfo(@PathVariable("updateDEid") Integer userId,@RequestBody DeliveryExecutive deliveryExecutive){
		DeliveryExecutive updated = adminService.updateDeliveryExecutive(userId, deliveryExecutive);
		
		return ResponseEntity.ok(updated);
	}
	
	@DeleteMapping("/deliveryexecutive/{deleteDEid}")
	public ResponseEntity<String> deleteDeliveryExecutive(@PathVariable("deleteDEid") Integer userId){
		adminService.deleteDeliveryExecutive(userId);
		return ResponseEntity.ok("Delivery executive deleted successfully");
	}
	
	@GetMapping("/rolecount")
	public ResponseEntity<Map<String, Integer>> getUserCountByRole(){
		Map<String, Integer> counts = adminService.getUsersGroupedByRoles();
		return ResponseEntity.ok(counts);
	}
	
	@GetMapping("/subscriptioncount")
	public ResponseEntity<Long> getsubscriptioncount() {
		long count = adminService.getsubscriptionscount();
		return ResponseEntity.ok(count);
	}
	
	@GetMapping("/by-date")
	public ResponseEntity<List<DeliveryDAO>> getDeliveriesByDate(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate localDate) {
		List<Delivery> deliveries = adminService.getDeliveriesByDate(localDate);
		
		List<DeliveryDAO> deliveryDAOs = deliveries.stream().map(delivery->{
					var log = delivery.getDailyDeliveryLog();
					var customer = delivery.getCustomer();
					
					return new DeliveryDAO(delivery.getId(), 
							customer!=null? customer.getUserName():null,
									customer!=null? customer.getFullAddress():null,
											delivery.isDelivered(),
											delivery.getDeliveryTime(),
											log!=null? log.getId():null,
													customer!=null? customer.getId():null);
				}).collect(Collectors.toList());
		return ResponseEntity.ok(deliveryDAOs);
	}

	
	@GetMapping("/no-deliveries")//GET /api/customers/no-deliveries?date=2025-06-15
	public List<UndeliveredCustomerDAO> getCustomerWithoutDeliveryOn(@RequestParam("date") @DateTimeFormat(iso= DateTimeFormat.ISO.DATE) LocalDate localDate) {
		return adminService.getCustomersWithoutDeliveryOn(localDate);
	}
	
	@GetMapping("/undelivered-count")
    public ResponseEntity<Long> getUndeliveredCustomerCount(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        long count = adminService.countUndeliveredCustomersOn(date);
        return ResponseEntity.ok(count);
    }
	
	@GetMapping("/delivered-count")
    public ResponseEntity<Long> getDeliveredCustomerCount(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        long count = adminService.countDeliveredCustomersOn(date);
        return ResponseEntity.ok(count);
    }
	
}
