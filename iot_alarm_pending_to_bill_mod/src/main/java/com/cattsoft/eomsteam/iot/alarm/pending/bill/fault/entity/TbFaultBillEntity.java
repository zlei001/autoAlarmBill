package com.cattsoft.eomsteam.iot.alarm.pending.bill.fault.entity;

import java.io.Serializable;

public class TbFaultBillEntity  implements Serializable{

	/**
	 * ÏµÁÐ»¯
	 */
	private static final long serialVersionUID = -1059105693364046578L;
	
	private Long I_ID;
	private String S_NO;
	private Integer I_OBJ_LEVEL;
	private Integer I_OBJ_TYPE;
	private Integer I_NE_MODEL;
	private Integer I_LARGE_CLASS;
	private Integer I_SMALL_CLASS;
	private Integer I_CYCLE;
	private String S_TITLE;
	private String S_MSG;
	private String S_DELIMITED_CYCLE;
	private String CITY_CODE;
	private String CITY_NAME;
	private String S_INST_ID;
	private String S_INST_NAME;
	private Integer I_FLAG;
	private Long I_CREATE_TIME;
	private Long I_UPDATE_TIME;
	private Integer I_DISPATCH;
	private String S_OUTSYS_NO;
	private Integer I_SOURCE;
	private Integer I_DB_TYPE;
	private Integer I_NET_ONE_LEVEL;
	private Integer I_NET_TWO_LEVEL;
	private Integer I_NET_THREE_LEVEL;
	private Integer I_DB_LEVEL;
	private Integer I_DISPATCH_LEVEL;
	private Integer I_ZIP_FLAG;
	private Integer I_ZIP_CNT;
	
	public Long getI_ID() {
		return I_ID;
	}
	public String getS_NO() {
		return S_NO;
	}
	public Integer getI_OBJ_LEVEL() {
		return I_OBJ_LEVEL;
	}
	public Integer getI_OBJ_TYPE() {
		return I_OBJ_TYPE;
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
	public Integer getI_CYCLE() {
		return I_CYCLE;
	}
	public String getS_TITLE() {
		return S_TITLE;
	}
	public String getS_MSG() {
		return S_MSG;
	}
	public String getS_DELIMITED_CYCLE() {
		return S_DELIMITED_CYCLE;
	}
	public String getCITY_CODE() {
		return CITY_CODE;
	}
	public String getCITY_NAME() {
		return CITY_NAME;
	}
	public String getS_INST_ID() {
		return S_INST_ID;
	}
	public String getS_INST_NAME() {
		return S_INST_NAME;
	}
	public Integer getI_FLAG() {
		return I_FLAG;
	}
	public Long getI_CREATE_TIME() {
		return I_CREATE_TIME;
	}
	public Long getI_UPDATE_TIME() {
		return I_UPDATE_TIME;
	}
	public Integer getI_DISPATCH() {
		return I_DISPATCH;
	}
	public String getS_OUTSYS_NO() {
		return S_OUTSYS_NO;
	}
	public Integer getI_SOURCE() {
		return I_SOURCE;
	}
	public Integer getI_DB_TYPE() {
		return I_DB_TYPE;
	}
	public Integer getI_NET_ONE_LEVEL() {
		return I_NET_ONE_LEVEL;
	}
	public Integer getI_NET_TWO_LEVEL() {
		return I_NET_TWO_LEVEL;
	}
	public Integer getI_NET_THREE_LEVEL() {
		return I_NET_THREE_LEVEL;
	}
	public Integer getI_DB_LEVEL() {
		return I_DB_LEVEL;
	}
	public Integer getI_DISPATCH_LEVEL() {
		return I_DISPATCH_LEVEL;
	}
	public Integer getI_ZIP_FLAG() {
		return I_ZIP_FLAG;
	}
	public void setI_ID(Long i_ID) {
		I_ID = i_ID;
	}
	public void setS_NO(String s_NO) {
		S_NO = s_NO;
	}
	public void setI_OBJ_LEVEL(Integer i_OBJ_LEVEL) {
		I_OBJ_LEVEL = i_OBJ_LEVEL;
	}
	public void setI_OBJ_TYPE(Integer i_OBJ_TYPE) {
		I_OBJ_TYPE = i_OBJ_TYPE;
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
	public void setI_CYCLE(Integer i_CYCLE) {
		I_CYCLE = i_CYCLE;
	}
	public void setS_TITLE(String s_TITLE) {
		S_TITLE = s_TITLE;
	}
	public void setS_MSG(String s_MSG) {
		S_MSG = s_MSG;
	}
	public void setS_DELIMITED_CYCLE(String s_DELIMITED_CYCLE) {
		S_DELIMITED_CYCLE = s_DELIMITED_CYCLE;
	}
	public void setCITY_CODE(String cITY_CODE) {
		CITY_CODE = cITY_CODE;
	}
	public void setCITY_NAME(String cITY_NAME) {
		CITY_NAME = cITY_NAME;
	}
	public void setS_INST_ID(String s_INST_ID) {
		S_INST_ID = s_INST_ID;
	}
	public void setS_INST_NAME(String s_INST_NAME) {
		S_INST_NAME = s_INST_NAME;
	}
	public void setI_FLAG(Integer i_FLAG) {
		I_FLAG = i_FLAG;
	}
	public void setI_CREATE_TIME(Long i_CREATE_TIME) {
		I_CREATE_TIME = i_CREATE_TIME;
	}
	public void setI_UPDATE_TIME(Long i_UPDATE_TIME) {
		I_UPDATE_TIME = i_UPDATE_TIME;
	}
	public void setI_DISPATCH(Integer i_DISPATCH) {
		I_DISPATCH = i_DISPATCH;
	}
	public void setS_OUTSYS_NO(String s_OUTSYS_NO) {
		S_OUTSYS_NO = s_OUTSYS_NO;
	}
	public void setI_SOURCE(Integer i_SOURCE) {
		I_SOURCE = i_SOURCE;
	}
	public void setI_DB_TYPE(Integer i_DB_TYPE) {
		I_DB_TYPE = i_DB_TYPE;
	}
	public void setI_NET_ONE_LEVEL(Integer i_NET_ONE_LEVEL) {
		I_NET_ONE_LEVEL = i_NET_ONE_LEVEL;
	}
	public void setI_NET_TWO_LEVEL(Integer i_NET_TWO_LEVEL) {
		I_NET_TWO_LEVEL = i_NET_TWO_LEVEL;
	}
	public void setI_NET_THREE_LEVEL(Integer i_NET_THREE_LEVEL) {
		I_NET_THREE_LEVEL = i_NET_THREE_LEVEL;
	}
	public void setI_DB_LEVEL(Integer i_DB_LEVEL) {
		I_DB_LEVEL = i_DB_LEVEL;
	}
	public void setI_DISPATCH_LEVEL(Integer i_DISPATCH_LEVEL) {
		I_DISPATCH_LEVEL = i_DISPATCH_LEVEL;
	}
	public void setI_ZIP_FLAG(Integer i_ZIP_FLAG) {
		I_ZIP_FLAG = i_ZIP_FLAG;
	}
	public Integer getI_ZIP_CNT() {
		return I_ZIP_CNT;
	}
	public void setI_ZIP_CNT(Integer i_ZIP_CNT) {
		I_ZIP_CNT = i_ZIP_CNT;
	}
	
}
