package com.newspaper.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.newspaper.dao.CustomerDAO;
import com.newspaper.dao.DEDAO;
import com.newspaper.dao.DailyDeliveryLogDAO;
import com.newspaper.dao.UndeliveredCustomerDAO;
import com.newspaper.entity.Customer;
import com.newspaper.entity.DailyDeliveryLog;
import com.newspaper.entity.Delivery;
import com.newspaper.entity.DeliveryExecutive;
import com.newspaper.entity.User;

public interface AdminService {

	//Search/Read/Filter
	
	List<User> getAllUsers();//USER
	
	List<User> getAllCustomers();//CUSTOMER
	
	List<User> getAllDeliveryExecutive();//DE
	List<DEDAO> getAllExecutives();
	
	List<User> searchUsersByName(String name);//USER

	List<User> getUserByEmail(String email);//USER
	
	//CRUD Customer
	
	Customer onboardCustomer(Customer customer);//CUSTOMER
	
	Optional<Customer> getCustomerById(Integer userId);//CUSTOMER
	
	Customer updateCustomer(Integer userId,Customer updatedCustomer);//CUSTOMER
	
	void deleteCustomer(Integer userId);//CUSTOMER
	
	//CRUD DE
	
	DeliveryExecutive onboarDeliveryExecutive(DeliveryExecutive deliveryExecutive);//DE
	
	Optional<DeliveryExecutive> getDeliveryExecutiveById(Integer userId);//DE
	
	DeliveryExecutive updateDeliveryExecutive(Integer userId,DeliveryExecutive updatedDeliveryExecutive);//DE
	
	void deleteDeliveryExecutive(Integer userId);//DE
	
	//Usercount by role
	Map<String, Integer> getUsersGroupedByRoles();//USER COUNTS BY ROLE *****************************************
	
	long getsubscriptionscount();//TOTAL SUBSCRIPTION COOUNT *************************************
	
	//delivery log implemented so below were the services related to it 
	List<Delivery> getDeliveriesByDate(LocalDate date);//daily delivery record DATE
	
	//List<Delivery> getUndeliveredByDeliveryExecutive(Integer userId);//undelivered deliveries by executive NOT SHOWING THE RESULT
	
	//deliveries count per day as per date
	long countDeliveredCustomersOn(LocalDate date);//DE IN THAT DAY*******************************
	
	List<UndeliveredCustomerDAO> getCustomersWithoutDeliveryOn(LocalDate date);//*******************
	
	long countUndeliveredCustomersOn(LocalDate date);

}
