CREATE TABLE SHIP
(
    ID serial primary key,
    BUS_NAME character varying(50),
	LOCN_NBR integer not null default 0,
	COMPANY  character varying(15), -- is gift
	DIVISION  character varying(10), -- is gift
	BUS_UNIT  character varying(3) NOT NULL,
	BATCH_NBR character varying(50),
	PACKAGE_NBR character varying(50),
	ORDER_ID integer not null,
    ORDER_NBR character varying(50),
    STAT_CODE  integer DEFAULT 0,
    ORDER_DTTM timestamp, 
    SHIP_BY_DTTM timestamp,
    EXPECTED_DELIVERY_DTTM timestamp,
    DELIVERY_TYPE character varying(50), -- express, overnight
    SHIP_CARRIER character varying(50), -- ups, fedex
    SHIP_CARRIER_SERVICE character varying(50), -- OVER NIGHT, two day delivery, express, ground etc
    TRACKING_NBR character varying(50), -- ups, fedex
    SHIP_COST numeric(3,2) DEFAULT 0.0,
	DEL_FIRST_NAME character varying(50),
	DEL_LAST_NAME character varying(50),
	DEL_MIDDLE_NAME character varying(50),
	DEL_ADDR_1 character varying(50),
	DEL_ADDR_2 character varying(50),
	DEL_ADDR_3 character varying(50),
	DEL_CITY character varying(50),
	DEL_STATE character varying(50),
	DEL_COUNTRY character varying(50),
	DEL_ZIPCODE character varying(15),
	DEL_PHONE_NBR character varying(50),
	INVOICE_LABEL_FORMAT character varying(5),
	INVOICE_LABEL bytea,
	SHIP_LABEL_FORMAT character varying(5),
	SHIP_LABEL bytea,
	SOURCE character varying(50),
	TRANSACTION_NAME character varying(50),
	REF_FIELD_1  character varying(50),
	REF_FIELD_2  character varying(50),
	HOST_NAME  character varying(50),
    CREATED_DTTM  timestamp not null default NOW(),
    UPDATED_DTTM  timestamp not null default NOW(),
    CREATED_BY character varying(25),
    UPDATED_BY character varying(25),
    VERSION INTEGER,
    unique (BUS_NAME, LOCN_NBR,COMPANY,DIVISION,BUS_UNIT,ORDER_NBR)
);

CREATE TABLE SHIP_LINES
(
    ID serial primary key,
	LOCN_NBR integer,
    SHIP_ID integer not null REFERENCES SHIP (ID),
    LINE_NBR integer not null,
    ITEM_BRCD character varying(25) NULL,
    QTY integer NOT NULL DEFAULT 0,
    ITEM_WIDTH numeric(4,2) DEFAULT 0.0,
    ITEM_HEIGHT numeric(4,2) DEFAULT 0.0,
    ITEM_LENGTH numeric(4,2) DEFAULT 0.0,
    ITEM_UNIT_WT numeric(4,2) DEFAULT 0.0,
    ITEM_UNIT_VOL numeric(4,2) DEFAULT 0.0,
	SOURCE character varying(50),
	TRANSACTION_NAME character varying(50),
	REF_FIELD_1  character varying(50),
	REF_FIELD_2  character varying(50),
	HOST_NAME  character varying(50),
    CREATED_DTTM  timestamp not null default NOW(),
    UPDATED_DTTM  timestamp not null default NOW(),
    CREATED_BY character varying(25),
    UPDATED_BY character varying(25),
    VERSION INTEGER
);

CREATE TABLE CARRIER
(
    ID serial primary key,
    SHIP_CARRIER_ID character varying(50),
    SHIP_CARRIER_CODE character varying(50),
    SHIP_CARRIER_NAME character varying(50),
	SOURCE character varying(50),
	TRANSACTION_NAME character varying(50),
	REF_FIELD_1  character varying(50),
	REF_FIELD_2  character varying(50),
	HOST_NAME  character varying(50),
    CREATED_DTTM  timestamp not null default NOW(),
    UPDATED_DTTM  timestamp not null default NOW(),
    CREATED_BY character varying(25),
    UPDATED_BY character varying(25),
    VERSION INTEGER
);

CREATE TABLE CARRIER_SERVICES
(
    ID serial primary key,
    CARRIER_ID integer not null REFERENCES CARRIER (ID),
    SERVICE_CODE character varying(50),
    DOMESTIC  character varying(50),
    INTERNATIONAL  character varying(50),
 	SOURCE character varying(50),
	TRANSACTION_NAME character varying(50),
	REF_FIELD_1  character varying(50),
	REF_FIELD_2  character varying(50),
	HOST_NAME  character varying(50),
    CREATED_DTTM  timestamp not null default NOW(),
    UPDATED_DTTM  timestamp not null default NOW(),
    CREATED_BY character varying(25),
    UPDATED_BY character varying(25),
    VERSION INTEGER
);

CREATE TABLE FACILITY_CARRIER
(
    ID serial primary key,
    BUS_NAME character varying(50),
	LOCN_NBR integer not null default 0,
    CARRIER_CODE character varying(50),
    ACCOUNT_NBR character varying(50),
    ENABLED character varying(1),
	SOURCE character varying(50),
	TRANSACTION_NAME character varying(50),
	REF_FIELD_1  character varying(50),
	REF_FIELD_2  character varying(50),
	HOST_NAME  character varying(50),
    CREATED_DTTM  timestamp not null default NOW(),
    UPDATED_DTTM  timestamp not null default NOW(),
    CREATED_BY character varying(25),
    UPDATED_BY character varying(25),
    VERSION INTEGER
);

CREATE TABLE FACILITY_CARRIER_SERVICES
(
    ID serial primary key,
    FACILITY_CARRIER_ID INTEGER not null REFERENCES FACILITY_CARRIER (ID),
    SERVICE_CODE character varying(50),
	SOURCE character varying(50),
	TRANSACTION_NAME character varying(50),
	REF_FIELD_1  character varying(50),
	REF_FIELD_2  character varying(50),
	HOST_NAME  character varying(50),
    CREATED_DTTM  timestamp not null default NOW(),
    UPDATED_DTTM  timestamp not null default NOW(),
    CREATED_BY character varying(25),
    UPDATED_BY character varying(25),
    VERSION INTEGER
);
