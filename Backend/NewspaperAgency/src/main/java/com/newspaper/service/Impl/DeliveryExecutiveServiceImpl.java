package com.newspaper.service.Impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newspaper.dao.DeliveryDAO;
import com.newspaper.entity.Customer;
import com.newspaper.entity.DailyDeliveryLog;
import com.newspaper.entity.Delivery;
import com.newspaper.entity.DeliveryExecutive;
import com.newspaper.repository.CustomerRepository;
import com.newspaper.repository.DailyDeliveryLogRepository;
import com.newspaper.repository.DeliveryExecutiveRepository;
import com.newspaper.repository.DeliveryRepository;
import com.newspaper.service.DeliverExecutiveService;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DeliveryExecutiveServiceImpl implements DeliverExecutiveService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private DeliveryExecutiveRepository deliveryExecutiveRepository;

    @Autowired
    private DailyDeliveryLogRepository dailyDeliveryLogRepository;

    @Autowired
    private DeliveryRepository deliveryRepository;

    /**
     * Creates a delivery by fetching customer details using customerId and appends
     * it to the delivery log for the specified delivery executive.
     */

    @Transactional
    public DeliveryDAO markAsDeliveredUsingCustomerID(Integer customerId, Integer deliveryExecutiveId) {
        Customer customer = customerRepository.findById(customerId)
            .orElseThrow(() -> new EntityNotFoundException("Customer not found with ID: " + customerId));

        DeliveryExecutive deliveryExecutive = deliveryExecutiveRepository.findById(deliveryExecutiveId)
            .orElseThrow(() -> new EntityNotFoundException("Delivery Executive not found with ID: " + deliveryExecutiveId));

        LocalDate today = LocalDate.now();

        // Get or create DailyDeliveryLog
        DailyDeliveryLog dailyDeliveryLog = dailyDeliveryLogRepository
            .findByDeliveryExecutive_IdAndDeliveryDate(deliveryExecutiveId, today)
            .orElseGet(() -> {
                DailyDeliveryLog newLog = new DailyDeliveryLog();
                newLog.setDeliveryDate(today);
                newLog.setDeliveryExecutive(deliveryExecutive);
                return dailyDeliveryLogRepository.save(newLog);
            });

        // Ensure the deliveries list is initialized
        Hibernate.initialize(dailyDeliveryLog.getDeliveries());

        // Create a new Delivery
        Delivery delivery = new Delivery();
        delivery.setCustomer(customer);
        delivery.setDelivered(true);
        delivery.setDeliveryTime(LocalDateTime.now());
        delivery.setAddress(customer.getFullAddress());
        delivery.setUserName(customer.getUserName());
        delivery.setDailyDeliveryLog(dailyDeliveryLog);

        // Initialize and add to deliveries list
        if (dailyDeliveryLog.getDeliveries() == null) {
            dailyDeliveryLog.setDeliveries(new ArrayList<>());
        }
        dailyDeliveryLog.getDeliveries().add(delivery);

        // Save delivery
        Delivery savedDelivery = deliveryRepository.save(delivery);

        // Return as DeliveryDAO
        return new DeliveryDAO(
            savedDelivery.getId(),
            savedDelivery.getUserName(),
            savedDelivery.getAddress(),
            savedDelivery.isDelivered(),
            savedDelivery.getDeliveryTime(),
            savedDelivery.getDailyDeliveryLog() != null ? savedDelivery.getDailyDeliveryLog().getId() : null,
            savedDelivery.getCustomer() != null ? savedDelivery.getCustomer().getId() : null
        );
    }

    @Transactional(readOnly = true)
    public List<DeliveryDAO> getTodaysDeliveries(Integer deliveryExecutiveId) {
        LocalDate today = LocalDate.now();

        Optional<DailyDeliveryLog> optionalDailyDeliveryLog = 
            dailyDeliveryLogRepository.findWithDeliveriesByExecutiveIdAndDate(deliveryExecutiveId, today);

        if (optionalDailyDeliveryLog.isEmpty()) {
            return Collections.emptyList();
        }

        DailyDeliveryLog dailyDeliveryLog = optionalDailyDeliveryLog.get();

        List<Delivery> deliveries = dailyDeliveryLog.getDeliveries();

        return deliveries.stream()
                .map(delivery -> {
                    DeliveryDAO dao = new DeliveryDAO();
                    dao.setId(delivery.getId());
                    dao.setUserName(delivery.getUserName());
                    dao.setAddress(delivery.getAddress());
                    dao.setDelivered(delivery.isDelivered());
                    dao.setDeliveryTime(delivery.getDeliveryTime());
                    dao.setCustomerId(delivery.getCustomer().getId());
                    return dao;
                })
                .collect(Collectors.toList());
    }

}
