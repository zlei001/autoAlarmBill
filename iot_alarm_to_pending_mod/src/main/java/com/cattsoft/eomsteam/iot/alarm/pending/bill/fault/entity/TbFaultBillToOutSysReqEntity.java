package com.cattsoft.eomsteam.iot.alarm.pending.bill.fault.entity;

import java.io.Serializable;

public class TbFaultBillToOutSysReqEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5820156089874297637L;
	
	private Long I_ID;
	private Long I_BILL_ID;
	private Long I_REF_ID;
	private Integer I_REF_TYPE;
	private Integer I_FLAG;
	private Integer I_TRY_CNT;
	private String S_MSG;
	private Long I_CREATE_TIME;
	private Long I_START_TIME;
	private Long I_END_TIME;
	public Long getI_ID() {
		return I_ID;
	}
	public Long getI_BILL_ID() {
		return I_BILL_ID;
	}
	public Long getI_REF_ID() {
		return I_REF_ID;
	}
	public Integer getI_REF_TYPE() {
		return I_REF_TYPE;
	}
	public Integer getI_FLAG() {
		return I_FLAG;
	}
	public Integer getI_TRY_CNT() {
		return I_TRY_CNT;
	}
	public String getS_MSG() {
		return S_MSG;
	}
	public Long getI_CREATE_TIME() {
		return I_CREATE_TIME;
	}
	public Long getI_START_TIME() {
		return I_START_TIME;
	}
	public Long getI_END_TIME() {
		return I_END_TIME;
	}
	public void setI_ID(Long i_ID) {
		I_ID = i_ID;
	}
	public void setI_BILL_ID(Long i_BILL_ID) {
		I_BILL_ID = i_BILL_ID;
	}
	public void setI_REF_ID(Long i_REF_ID) {
		I_REF_ID = i_REF_ID;
	}
	public void setI_REF_TYPE(Integer i_REF_TYPE) {
		I_REF_TYPE = i_REF_TYPE;
	}
	public void setI_FLAG(Integer i_FLAG) {
		I_FLAG = i_FLAG;
	}
	public void setI_TRY_CNT(Integer i_TRY_CNT) {
		I_TRY_CNT = i_TRY_CNT;
	}
	public void setS_MSG(String s_MSG) {
		S_MSG = s_MSG;
	}
	public void setI_CREATE_TIME(Long i_CREATE_TIME) {
		I_CREATE_TIME = i_CREATE_TIME;
	}
	public void setI_START_TIME(Long i_START_TIME) {
		I_START_TIME = i_START_TIME;
	}
	public void setI_END_TIME(Long i_END_TIME) {
		I_END_TIME = i_END_TIME;
	}
	
}
