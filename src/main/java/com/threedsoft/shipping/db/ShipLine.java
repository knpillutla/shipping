package com.threedsoft.shipping.db;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

@Entity
@Data
@Table(name="SHIP_LINES")
@EntityListeners(AuditingEntityListener.class)
public class ShipLine  implements Serializable{
	@Column(name="ID")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name="SHIP_ID", nullable=false)
    private Ship ship;

	@Column(name="LINE_NBR")
	Integer shipLineNbr;

	@Column(name="LOCN_NBR")
	Integer locnNbr;

	@Column(name="ITEM_BRCD")
	String itemBrcd;

	@Column(name="QTY")
	Integer qty;

	@Column(name="ITEM_WIDTH")
    double itemWidth;
    
    @Column(name="ITEM_HEIGHT")
    double itemHeight;
    
    @Column(name="ITEM_LENGTH")
    double itemLength;
    
    @Column(name="ITEM_UNIT_WT")
    double itemUnitWt;
    
    @Column(name="ITEM_UNIT_VOL")
    double itemUnitVol;

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
