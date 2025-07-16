package com.newspaper.MVControllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.newspaper.dao.DeliveryDAO;
import com.newspaper.exception.ResourceNotFoundException;
import com.newspaper.repository.DeliveryExecutiveRepository;
import com.newspaper.service.DeliverExecutiveService;
import com.newspaper.service.Impl.DeliveryExecutiveServiceImpl;
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/deliveries")
public class DeliveryController {
	@Autowired private DeliverExecutiveService deliverExecutiveService;
	
	@Autowired private DeliveryExecutiveRepository deliveryExecutiveRepository;

	 @PostMapping("/create")
	    public ResponseEntity<?> createDelivery(@RequestParam Integer userId, @RequestParam Integer deliveryExecutiveId) {
	        try {
	            DeliveryDAO deliveryDAO =  deliverExecutiveService.markAsDeliveredUsingCustomerID(userId, deliveryExecutiveId);
	            return ResponseEntity.ok(deliveryDAO);
	        } catch (ResourceNotFoundException e) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	        } catch (IllegalStateException e) {
	            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to record delivery");
	        }
	    }
	 
	 @GetMapping("/today/{executiveId}")
	 public List<DeliveryDAO> getTodaysDeliveries(@PathVariable Integer executiveId) {
	     return deliverExecutiveService.getTodaysDeliveries(executiveId);
	 }

 }


