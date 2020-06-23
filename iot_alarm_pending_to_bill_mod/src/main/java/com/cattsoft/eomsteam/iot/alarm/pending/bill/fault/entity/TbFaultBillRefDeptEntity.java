package com.cattsoft.eomsteam.iot.alarm.pending.bill.fault.entity;

import java.io.Serializable;

public class TbFaultBillRefDeptEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1905777604807491323L;
	
	private Long I_ID;
	private Long I_BILL_ID;
	private String CITY_CODE;
	private String CITY_NAME;
	private String I_DEPT_ID;
	private String S_DEPT_NAME;
	private String S_DISP_NAME;
	public Long getI_ID() {
		return I_ID;
	}
	public Long getI_BILL_ID() {
		return I_BILL_ID;
	}
	public String getCITY_CODE() {
		return CITY_CODE;
	}
	public String getCITY_NAME() {
		return CITY_NAME;
	}
	public String getI_DEPT_ID() {
		return I_DEPT_ID;
	}
	public String getS_DEPT_NAME() {
		return S_DEPT_NAME;
	}
	public String getS_DISP_NAME() {
		return S_DISP_NAME;
	}
	public void setI_ID(Long i_ID) {
		I_ID = i_ID;
	}
	public void setI_BILL_ID(Long i_BILL_ID) {
		I_BILL_ID = i_BILL_ID;
	}
	public void setCITY_CODE(String cITY_CODE) {
		CITY_CODE = cITY_CODE;
	}
	public void setCITY_NAME(String cITY_NAME) {
		CITY_NAME = cITY_NAME;
	}
	public void setI_DEPT_ID(String i_DEPT_ID) {
		I_DEPT_ID = i_DEPT_ID;
	}
	public void setS_DEPT_NAME(String s_DEPT_NAME) {
		S_DEPT_NAME = s_DEPT_NAME;
	}
	public void setS_DISP_NAME(String s_DISP_NAME) {
		S_DISP_NAME = s_DISP_NAME;
	}

}
