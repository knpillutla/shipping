package com.example.shipping.db;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.Data;

@Entity
@Data
@Table(name="SHIP")
public class Ship  implements Serializable{
	@Column(name="ID")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	@OneToMany(mappedBy = "ship", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	List<ShipLine> shipLines = new ArrayList<>();
	
	@Column(name="BUS_NAME")
	String busName;

	@Column(name="LOCN_NBR")
	Integer locnNbr;

	@Column(name="COMPANY")
	String company;

	@Column(name="DIVISION")
	String division;

	@Column(name="BUS_UNIT")
	String busUnit;

	@Column(name="BATCH_NBR")
	String batchNbr;

	@Column(name="PACKAGE_NBR")
	String packageNbr;

	@Column(name="ORDER_NBR")
	String orderNbr;

	@Column(name="ORDER_ID")
	Long orderId;

	@Column(name="STAT_CODE")
	Integer statCode;

	@Column(name="ORDER_DTTM")
	Date orderDttm;

	@Column(name="SHIP_BY_DTTM")
	Date shipByDttm;

	@Column(name="EXPECTED_DELIVERY_DTTM")
	Date expectedDeliveryDttm;

	@Column(name="DELIVERY_TYPE")
	String deliveryType;

	@Column(name="SHIP_CARRIER")
	String shipCarrier;
    
    @Column(name="SHIP_CARRIER_SERVICE")
    String shipCarrierService;
   
    @Column(name="TRACKING_NBR")
    String trackingNbr;

    @Column(name="SHIP_COST")
    double shipCost;

    @Column(name="DEL_FIRST_NAME")
    String firstName;
    @Column(name="DEL_LAST_NAME")
    String lastName;
    @Column(name="DEL_MIDDLE_NAME")
    String middleName;
    @Column(name="DEL_ADDR_1")
    String addr1;
    @Column(name="DEL_ADDR_2")
    String addr2;
    @Column(name="DEL_ADDR_3")
    String addr3;
    @Column(name="DEL_CITY")
    String city;
    @Column(name="DEL_STATE")
    String state;
    @Column(name="DEL_COUNTRY")
    String country;
    @Column(name="DEL_ZIPCODE")
    String zipcode;
    @Column(name="DEL_PHONE_NBR")
    String phoneNbr;
    @Column(name="INVOICE_ZPL")
    String invoiceZPL;
    @Column(name="LABEL_ZPL")
    String labelZPL;
    
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

    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
	@Column(name="CREATED_DTTM", nullable = false, updatable = false)
	Date createdDttm;
	
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATED_DTTM", nullable = false)
    @LastModifiedDate
	Date updatedDttm;
	
	@Column(name="CREATED_BY")
	String createdBy;

 	@Column(name="UPDATED_BY")
	String updatedBy;

	@Version
 	@Column(name="VERSION")
	Integer version; 	
    
    public void addShipLine(ShipLine shipLine) {
    	shipLines.add(shipLine);
    	//orderLine.setOrder(this);
    }
 
    public void removeShipLine(ShipLine shipLine) {
    	shipLines.remove(shipLine);
    	//orderLine.setOrder(null);
    }
}
