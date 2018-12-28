package com.threedsoft.shipping.db;

import java.util.List;

import org.hibernate.annotations.BatchSize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FacilityCarrierRepository extends JpaRepository<FacilityCarrier, Long>{

	public List<Ship> findByBusNameAndLocnNbrAndCarrierCodeOrderByCarrierCodeDesc(@Param("busName") String busName,
			@Param("locnNbr") Integer locnNbr, @Param("carrierCode") String carrierCode);

	public List<Ship> findByBusNameAndLocnNbrOrderByCarrierCodeDesc(@Param("busName") String busName,
			@Param("locnNbr") Integer locnNbr);
}
