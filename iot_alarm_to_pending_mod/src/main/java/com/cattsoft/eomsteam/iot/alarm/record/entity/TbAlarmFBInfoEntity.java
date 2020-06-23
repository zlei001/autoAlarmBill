package com.cattsoft.eomsteam.iot.alarm.record.entity;

import java.io.Serializable;

public class TbAlarmFBInfoEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2118748151590835749L;
	private Long I_ID;
	private Long I_REF_ID;
	private Integer I_REF_TYPE;
	private Integer I_FLAG;
	private String S_MSG;
	private Long I_CREATE_TIME;
	private String CITY_CODE;
	private String CITY_NAME;
	private Long I_STAFF_ID;
	private String S_STAFF_NAME ;
	public Long getI_ID() {
		return I_ID;
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
	public String getS_MSG() {
		return S_MSG;
	}
	public Long getI_CREATE_TIME() {
		return I_CREATE_TIME;
	}
	public String getCITY_CODE() {
		return CITY_CODE;
	}
	public String getCITY_NAME() {
		return CITY_NAME;
	}
	public Long getI_STAFF_ID() {
		return I_STAFF_ID;
	}
	public String getS_STAFF_NAME() {
		return S_STAFF_NAME;
	}
	public void setI_ID(Long i_ID) {
		I_ID = i_ID;
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
	public void setS_MSG(String s_MSG) {
		S_MSG = s_MSG;
	}
	public void setI_CREATE_TIME(Long i_CREATE_TIME) {
		I_CREATE_TIME = i_CREATE_TIME;
	}
	public void setCITY_CODE(String cITY_CODE) {
		CITY_CODE = cITY_CODE;
	}
	public void setCITY_NAME(String cITY_NAME) {
		CITY_NAME = cITY_NAME;
	}
	public void setI_STAFF_ID(Long i_STAFF_ID) {
		I_STAFF_ID = i_STAFF_ID;
	}
	public void setS_STAFF_NAME(String s_STAFF_NAME) {
		S_STAFF_NAME = s_STAFF_NAME;
	}

}
