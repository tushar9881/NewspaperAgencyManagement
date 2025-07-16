package com.newspaper.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.newspaper.entity.DailyDeliveryLog;
import com.newspaper.entity.DeliveryExecutive;


public interface DeliveryExecutiveRepository extends JpaRepository<DeliveryExecutive, Integer> {

	
}
