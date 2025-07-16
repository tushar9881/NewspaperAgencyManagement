package com.newspaper.service;

import java.time.LocalDate;
import java.util.List;

import com.newspaper.dao.DailyDeliveryLogDAO;
import com.newspaper.dao.DeliveryDAO;
import com.newspaper.entity.DailyDeliveryLog;
import com.newspaper.entity.Delivery;
import com.newspaper.entity.DeliveryExecutive;

public interface DeliverExecutiveService {

	DeliveryDAO markAsDeliveredUsingCustomerID(Integer userId,Integer deliveryExecutiveId);
	
	
	List<DeliveryDAO> getTodaysDeliveries( Integer deliveryExecutiveId);
}
