package com.cattsoft.eomsteam.iot.alarm.pending.bill.fault.entity;

import java.io.Serializable;

public class TbFaultBillRefAlarmEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4658781456589809496L;
	
	private Long I_ID;
	private Long I_BILL_ID;
	private Long I_ALARM_ID;
	private Long I_ZIP_TIME;
	
	private Integer I_LARGE_CLASS;
	private Integer I_SMALL_CLASS;
	private Integer I_NE_MODEL;
	
	public Long getI_ID() {
		return I_ID;
	}
	public Long getI_BILL_ID() {
		return I_BILL_ID;
	}
	public Long getI_ALARM_ID() {
		return I_ALARM_ID;
	}
	public Long getI_ZIP_TIME() {
		return I_ZIP_TIME;
	}
	public void setI_ID(Long i_ID) {
		I_ID = i_ID;
	}
	public void setI_BILL_ID(Long i_BILL_ID) {
		I_BILL_ID = i_BILL_ID;
	}
	public void setI_ALARM_ID(Long i_ALARM_ID) {
		I_ALARM_ID = i_ALARM_ID;
	}
	public void setI_ZIP_TIME(Long i_ZIP_TIME) {
		I_ZIP_TIME = i_ZIP_TIME;
	}
	public Integer getI_LARGE_CLASS() {
		return I_LARGE_CLASS;
	}
	public Integer getI_SMALL_CLASS() {
		return I_SMALL_CLASS;
	}
	public Integer getI_NE_MODEL() {
		return I_NE_MODEL;
	}
	public void setI_LARGE_CLASS(Integer i_LARGE_CLASS) {
		I_LARGE_CLASS = i_LARGE_CLASS;
	}
	public void setI_SMALL_CLASS(Integer i_SMALL_CLASS) {
		I_SMALL_CLASS = i_SMALL_CLASS;
	}
	public void setI_NE_MODEL(Integer i_NE_MODEL) {
		I_NE_MODEL = i_NE_MODEL;
	}

	
}
