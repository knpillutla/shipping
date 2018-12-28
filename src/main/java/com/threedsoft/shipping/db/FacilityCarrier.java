package com.threedsoft.shipping.db;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

@Entity
@Data
@Table(name="FACILITY_CARRIER")
@EntityListeners(AuditingEntityListener.class)
public class FacilityCarrier  implements Serializable{
	@Column(name="ID")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	@Column(name="BUS_NAME")
	String busName;

	@Column(name="LOCN_NBR")
	Integer locnNbr;

	@Column(name="CARRIER_CODE")
	String carrierCode;

	@Column(name="ACCOUNT_NBR")
	String accounNbr;

	@Column(name="ENABLED")
	String isEnabled;

	@Column(name="SOURCE")
	String source;

	@Column(name="TRANSACTION_NAME")
	String transactionName;

	@Column(name="REF_FIELD_1")
	String refField1;

	@Column(name="REF_FIELD_2")
	String refField2;

	@Column(name="HOST_NAME")
	String hostName;

    @CreatedDate
	@Column(name="CREATED_DTTM", nullable = false, updatable = false)
    LocalDateTime createdDttm;
	
    @Column(name = "UPDATED_DTTM", nullable = false)
    @LastModifiedDate
	LocalDateTime updatedDttm;
	
	@Column(name="CREATED_BY")
	String createdBy;

 	@Column(name="UPDATED_BY")
	String updatedBy;

	@Version
 	@Column(name="VERSION")
	Integer version; 	
}
