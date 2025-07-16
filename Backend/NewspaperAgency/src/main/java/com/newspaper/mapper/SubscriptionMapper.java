package com.newspaper.mapper;

import com.newspaper.dao.SubscriptionDAO;
import com.newspaper.entity.Customer;
import com.newspaper.entity.Subscription;

public class SubscriptionMapper {
	
	public static SubscriptionDAO maoToSubscriptionDAO(Subscription subscription) {
		return new SubscriptionDAO(subscription.getId(),
				subscription.getPaperName(),
				subscription.getStartDate(),
				subscription.getEndDate(),
				subscription.getPrice(),
				subscription.getQuantity(),
				subscription.getTotal());
	}
	
	public static Subscription mapToSubscriptions(SubscriptionDAO subscriptionDAO,Customer customer) {
		return new Subscription(subscriptionDAO.getId(),
				subscriptionDAO.getPaperName(),
				subscriptionDAO.getStartDate(),
				subscriptionDAO.getEndDate(),
				subscriptionDAO.getPrice(),
				subscriptionDAO.getQuantity(),
				subscriptionDAO.getTotal(),customer);
	}
}
