package com.threedsoft.shipping.db;

import java.util.List;

import org.hibernate.annotations.BatchSize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ShipRepository extends JpaRepository<Ship, Long>{

	@Query("select o from Ship o where o.busName=:busName and o.locnNbr=:locnNbr and o.id=:id")
	public Ship findById(@Param("busName") String busName, @Param("locnNbr") Integer locnNbr, @Param("id") Long id);

	@Query("select o from Ship o where o.busName=:busName and o.locnNbr=:locnNbr and o.company=:company and o.division=:division and o.busUnit=:busUnit")
	public List<Ship> findByUniqueKey(@Param("busName") Integer busName, @Param("locnNbr") Integer locnNbr, @Param("company") String company, @Param("division") String division, @Param("busUnit") String busUnit);
	
	@Query("select o from Ship o where o.busName=:busName and o.locnNbr=:locnNbr and o.orderNbr=:orderNbr")
	public Ship findByBusNameAndLocnNbrAndOrderNbr(@Param("busName") String busName, @Param("locnNbr") Integer locnNbr, @Param("orderNbr") String orderNbr);
	
	@Query("select o from Ship o where o.busName=:busName and o.locnNbr=:locnNbr and o.orderNbr=:orderNbr")
	public List<Ship> findByUniqueKey(@Param("busName") Integer busName, @Param("locnNbr") Integer locnNbr, @Param("orderNbr") String orderNbr);

	@Query("select o from Ship o where o.busName=:busName and o.locnNbr=:locnNbr and o.id=:orderId")
	public Ship findByBusNameAndLocnNbrAndOrderId(@Param("busName") String busName, @Param("locnNbr") Integer locnNbr, @Param("orderId") Long orderId);
	
	@Query("select o from Ship o where o.busName=:busName and o.locnNbr=:locnNbr order by o.id")
	@BatchSize(size = 10)
	public List<Ship> findByBusNameAndLocnNbrOrderByOrderId(@Param("busName") String busName, @Param("locnNbr") Integer locnNbr);
}
