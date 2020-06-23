package com.cattsoft.eomsteam.iot.alarm.pending.bill.fault.entity;

import java.io.Serializable;

public class TbOutSysDeptInterfaceEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8853980556990574319L;
	
	private Long I_ID;
	private Integer I_SYS;
	private String CITY_CODE;
	private String CITY_NAME;
	private Integer I_OBJ_TYPE;
	private String S_REF_ID;
	private String S_REF_NAME;
	public Long getI_ID() {
		return I_ID;
	}
	public Integer getI_SYS() {
		return I_SYS;
	}
	public String getCITY_CODE() {
		return CITY_CODE;
	}
	public String getCITY_NAME() {
		return CITY_NAME;
	}
	public Integer getI_OBJ_TYPE() {
		return I_OBJ_TYPE;
	}
	public String getS_REF_ID() {
		return S_REF_ID;
	}
	public String getS_REF_NAME() {
		return S_REF_NAME;
	}
	public void setI_ID(Long i_ID) {
		I_ID = i_ID;
	}
	public void setI_SYS(Integer i_SYS) {
		I_SYS = i_SYS;
	}
	public void setCITY_CODE(String cITY_CODE) {
		CITY_CODE = cITY_CODE;
	}
	public void setCITY_NAME(String cITY_NAME) {
		CITY_NAME = cITY_NAME;
	}
	public void setI_OBJ_TYPE(Integer i_OBJ_TYPE) {
		I_OBJ_TYPE = i_OBJ_TYPE;
	}
	public void setS_REF_ID(String s_REF_ID) {
		S_REF_ID = s_REF_ID;
	}
	public void setS_REF_NAME(String s_REF_NAME) {
		S_REF_NAME = s_REF_NAME;
	}

}
