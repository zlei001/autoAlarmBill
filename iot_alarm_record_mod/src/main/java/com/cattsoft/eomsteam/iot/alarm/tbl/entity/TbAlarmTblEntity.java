package com.cattsoft.eomsteam.iot.alarm.tbl.entity;

import java.io.Serializable;

public class TbAlarmTblEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7824304584592797099L;
	
	private Long I_ID;
	private Integer I_OBJ_TYPE;
	private Integer I_OBJ_ID;
	private Integer I_CYCLE;
	private String S_OBJ_TBL;
	private String S_OBJ_TBL_WHERE;
	
	private Integer I_NE_MODEL;
	private Integer I_LARGE_CLASS;
	private Integer I_SMALL_CLASS;
	
	public Long getI_ID() {
		return I_ID;
	}
	public Integer getI_OBJ_TYPE() {
		return I_OBJ_TYPE;
	}
	public Integer getI_OBJ_ID() {
		return I_OBJ_ID;
	}
	public Integer getI_CYCLE() {
		return I_CYCLE;
	}
	public String getS_OBJ_TBL() {
		return S_OBJ_TBL;
	}
	public String getS_OBJ_TBL_WHERE() {
		return S_OBJ_TBL_WHERE;
	}
	public void setI_ID(Long i_ID) {
		I_ID = i_ID;
	}
	public void setI_OBJ_TYPE(Integer i_OBJ_TYPE) {
		I_OBJ_TYPE = i_OBJ_TYPE;
	}
	public void setI_OBJ_ID(Integer i_OBJ_ID) {
		I_OBJ_ID = i_OBJ_ID;
	}
	public void setI_CYCLE(Integer i_CYCLE) {
		I_CYCLE = i_CYCLE;
	}
	public void setS_OBJ_TBL(String s_OBJ_TBL) {
		S_OBJ_TBL = s_OBJ_TBL;
	}
	public void setS_OBJ_TBL_WHERE(String s_OBJ_TBL_WHERE) {
		S_OBJ_TBL_WHERE = s_OBJ_TBL_WHERE;
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
	public void setI_NE_MODEL(Integer i_NE_MODEL) {
		I_NE_MODEL = i_NE_MODEL;
	}
	public void setI_LARGE_CLASS(Integer i_LARGE_CLASS) {
		I_LARGE_CLASS = i_LARGE_CLASS;
	}
	public void setI_SMALL_CLASS(Integer i_SMALL_CLASS) {
		I_SMALL_CLASS = i_SMALL_CLASS;
	}
}
