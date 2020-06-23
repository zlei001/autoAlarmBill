package com.cattsoft.eomsteam.iot.stat.record.entity;

import java.io.Serializable;

public class TbStatRecordCellDyEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6835061142914016166L;
	
	private Long I_ID;
	private String STATIS_TIME;
	private Integer I_NE_MODEL;
	private Integer I_LARGE_CLASS;
	private Integer I_SMALL_CLASS;
	private String CITY_CODE;
	private String CITY_NAME;
	private Integer I_DELIMITED_CNT;
	private Integer I_DELIMITED_END_CNT;
	private Integer I_ALARM_CNT;
	private Integer I_ALARM_END_CNT;
	private Integer I_BILL_CNT;
	private Integer I_BILL_END_CNT;
	private Long I_DELIMITED_TIME;
	private Long I_ALARM_TIME;
	private Long I_BILL_TIME;
	
	private Integer I_ALARM_PENDING_CNT;
	private Integer I_ALARM_PENDING_END_CNT;
	private Long I_ALARM_PENDING_TIME;
	
	private String DST_ECI;
	private String DST_ECI_NAME;
	
	public Long getI_ID() {
		return I_ID;
	}
	public String getSTATIS_TIME() {
		return STATIS_TIME;
	}
	public Integer getI_NE_MODEL() {
		return I_NE_MODEL;
	}
	public Integer getI_LARGE_CLASS() {
		return I_LARGE_CLASS;
	}
	public Integer getI_SMALL_CLASS() {
		return I_SMALL_CLASS;
	}
	public String getCITY_CODE() {
		return CITY_CODE;
	}
	public String getCITY_NAME() {
		return CITY_NAME;
	}
	public Integer getI_DELIMITED_CNT() {
		return I_DELIMITED_CNT;
	}
	public Integer getI_DELIMITED_END_CNT() {
		return I_DELIMITED_END_CNT;
	}
	public Integer getI_ALARM_CNT() {
		return I_ALARM_CNT;
	}
	public Integer getI_ALARM_END_CNT() {
		return I_ALARM_END_CNT;
	}
	public Integer getI_BILL_CNT() {
		return I_BILL_CNT;
	}
	public Integer getI_BILL_END_CNT() {
		return I_BILL_END_CNT;
	}
	public Long getI_DELIMITED_TIME() {
		return I_DELIMITED_TIME;
	}
	public Long getI_ALARM_TIME() {
		return I_ALARM_TIME;
	}
	public Long getI_BILL_TIME() {
		return I_BILL_TIME;
	}
	public void setI_ID(Long i_ID) {
		I_ID = i_ID;
	}
	public void setSTATIS_TIME(String sTATIS_TIME) {
		STATIS_TIME = sTATIS_TIME;
	}
	public void setI_NE_MODEL(Integer i_NE_MODEL) {
		I_NE_MODEL = i_NE_MODEL;
	}
	public void setI_LARGE_CLASS(Integer i_LARGE_CLASS) {
		I_LARGE_CLASS = i_LARGE_CLASS;
	}
	public void setI_SMALL_CLASS(Integer i_SMALL_CLASS) {
		I_SMALL_CLASS = i_SMALL_CLASS;
	}
	public void setCITY_CODE(String cITY_CODE) {
		CITY_CODE = cITY_CODE;
	}
	public void setCITY_NAME(String cITY_NAME) {
		CITY_NAME = cITY_NAME;
	}
	public void setI_DELIMITED_CNT(Integer i_DELIMITED_CNT) {
		I_DELIMITED_CNT = i_DELIMITED_CNT;
	}
	public void setI_DELIMITED_END_CNT(Integer i_DELIMITED_END_CNT) {
		I_DELIMITED_END_CNT = i_DELIMITED_END_CNT;
	}
	public void setI_ALARM_CNT(Integer i_ALARM_CNT) {
		I_ALARM_CNT = i_ALARM_CNT;
	}
	public void setI_ALARM_END_CNT(Integer i_ALARM_END_CNT) {
		I_ALARM_END_CNT = i_ALARM_END_CNT;
	}
	public void setI_BILL_CNT(Integer i_BILL_CNT) {
		I_BILL_CNT = i_BILL_CNT;
	}
	public void setI_BILL_END_CNT(Integer i_BILL_END_CNT) {
		I_BILL_END_CNT = i_BILL_END_CNT;
	}
	public void setI_DELIMITED_TIME(Long i_DELIMITED_TIME) {
		I_DELIMITED_TIME = i_DELIMITED_TIME;
	}
	public void setI_ALARM_TIME(Long i_ALARM_TIME) {
		I_ALARM_TIME = i_ALARM_TIME;
	}
	public void setI_BILL_TIME(Long i_BILL_TIME) {
		I_BILL_TIME = i_BILL_TIME;
	}
	public Integer getI_ALARM_PENDING_CNT() {
		return I_ALARM_PENDING_CNT;
	}
	public Integer getI_ALARM_PENDING_END_CNT() {
		return I_ALARM_PENDING_END_CNT;
	}
	public Long getI_ALARM_PENDING_TIME() {
		return I_ALARM_PENDING_TIME;
	}
	public void setI_ALARM_PENDING_CNT(Integer i_ALARM_PENDING_CNT) {
		I_ALARM_PENDING_CNT = i_ALARM_PENDING_CNT;
	}
	public void setI_ALARM_PENDING_END_CNT(Integer i_ALARM_PENDING_END_CNT) {
		I_ALARM_PENDING_END_CNT = i_ALARM_PENDING_END_CNT;
	}
	public void setI_ALARM_PENDING_TIME(Long i_ALARM_PENDING_TIME) {
		I_ALARM_PENDING_TIME = i_ALARM_PENDING_TIME;
	}
	public String getDST_ECI() {
		return DST_ECI;
	}
	public String getDST_ECI_NAME() {
		return DST_ECI_NAME;
	}
	public void setDST_ECI(String dST_ECI) {
		DST_ECI = dST_ECI;
	}
	public void setDST_ECI_NAME(String dST_ECI_NAME) {
		DST_ECI_NAME = dST_ECI_NAME;
	}
	
}
