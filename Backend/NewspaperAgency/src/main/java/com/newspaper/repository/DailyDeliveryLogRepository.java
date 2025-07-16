package com.newspaper.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.newspaper.entity.DailyDeliveryLog;

public interface DailyDeliveryLogRepository extends JpaRepository<DailyDeliveryLog, Integer> {

	Optional<DailyDeliveryLog> findByDeliveryExecutive_IdAndDeliveryDate(Integer delivery_executiveId, LocalDate deliveryDate);
	
	@Query("SELECT ddl FROM DailyDeliveryLog ddl JOIN FETCH ddl.deliveries WHERE ddl.deliveryExecutive.id = :executiveId AND ddl.deliveryDate = :date")
	Optional<DailyDeliveryLog> findWithDeliveriesByExecutiveIdAndDate(@Param("executiveId") Integer executiveId, @Param("date") LocalDate date);


}
