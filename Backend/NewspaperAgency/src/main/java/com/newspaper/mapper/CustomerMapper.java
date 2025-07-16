package com.newspaper.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.newspaper.dao.CustomerDAO;
import com.newspaper.dao.SubscriptionDAO;
import com.newspaper.entity.Customer;
import com.newspaper.entity.Subscription;

public class CustomerMapper {

    public static CustomerDAO mapToCustomerDao(Customer customer) {
        List<SubscriptionDAO> subscriptionDAOs = customer.getSubscriptions().stream()
                .map(SubscriptionMapper::maoToSubscriptionDAO)
                .collect(Collectors.toList());

        return new CustomerDAO(
                customer.getId(),
                customer.getUserName(),
                customer.getFullName(),
                customer.getEmail(),
                customer.getContactNumber(),
                customer.getFullAddress(),
                customer.getSectorZone(),
                customer.getRegistrationDate(),
                subscriptionDAOs
        );
    }

    public static Customer mapToCustomer(CustomerDAO customerDao) {
        // Step 1: Create Customer object
    	Customer customer = new Customer();
    	customer.setId(customerDao.getUserId());
    	customer.setUserName(customerDao.getUserName());
    	customer.setPassword(null); // or set later
    	customer.setFullName(customerDao.getFullName());
    	customer.setEmail(customerDao.getEmail());
    	customer.setContactNumber(customerDao.getContactNumber());
    	customer.setFullAddress(customerDao.getFullAddress());
    	customer.setSectorZone(customerDao.getSectorZone());
    	customer.setRegistrationDate(customerDao.getRegistrationDate());


        // Step 2: Map and set subscriptions
        if (customerDao.getSubscriptions() != null) { 
            List<Subscription> subscriptions = customerDao.getSubscriptions().stream()
                    .map(subDao -> {
                        Subscription sub = SubscriptionMapper.mapToSubscriptions(subDao, customer);
                        sub.setCustomer(customer);
                        return sub;
                    })
                    .collect(Collectors.toList());
            customer.setSubscriptions(subscriptions);
        }

        return customer;
    }
}
