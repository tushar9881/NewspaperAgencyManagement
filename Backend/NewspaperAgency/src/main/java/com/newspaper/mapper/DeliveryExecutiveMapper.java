package com.newspaper.mapper;

import java.util.ArrayList;

import com.newspaper.dao.DeliveryExecutiveDAO;
import com.newspaper.entity.DeliveryExecutive;

public class DeliveryExecutiveMapper {

    // Convert Entity → DAO
	public static DeliveryExecutiveDAO mapToDAO(DeliveryExecutive deliveryExecutive) {
	    return new DeliveryExecutiveDAO(
	        deliveryExecutive.getId(),
	        deliveryExecutive.getUserName(),
	        deliveryExecutive.getPassword(),      
	        deliveryExecutive.getFullName(),
	        deliveryExecutive.getEmail(),
	        deliveryExecutive.getContactNumber(),
	        deliveryExecutive.getRole(),
	        deliveryExecutive.getZoneAssigned()
	    );
	}


    // Convert DAO → Entity
    public static DeliveryExecutive mapToEntity(DeliveryExecutiveDAO dao) {
        return new DeliveryExecutive(
            dao.getUserId(),
            dao.getUserName(),
            dao.getPassword(),
            dao.getFullName(),
            dao.getEmail(),
            dao.getContactNumber(),
            dao.getZoneAssigned(),
            null 
        );
    }


}
