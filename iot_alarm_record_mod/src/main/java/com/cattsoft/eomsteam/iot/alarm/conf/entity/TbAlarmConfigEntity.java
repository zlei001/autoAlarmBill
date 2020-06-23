package com.cattsoft.eomsteam.iot.alarm.conf.entity;

import java.io.Serializable;

/***
 * 定界配置实体
 * @author Administrator
 *
 */
public class TbAlarmConfigEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 545692024805882495L;

	private Long I_ID;
	private Integer I_OBJ_TYPE;
	private Integer I_OBJ_LEVEL;
	private Integer I_NE_MODEL;
	private Integer I_LARGE_CLASS;
	private Integer I_SMALL_CLASS;
	private Integer I_CYCLE;
	private String S_TITLE;
	private String S_REMKARK;
	private String S_OBJ_TBL;
	private String S_OBJ_TBL_WHERE;
	
	public Long getI_ID() {
		return I_ID;
	}
	public Integer getI_OBJ_TYPE() {
		return I_OBJ_TYPE;
	}
	public Integer getI_CYCLE() {
		return I_CYCLE;
	}
	public String getS_TITLE() {
		return S_TITLE;
	}
	public String getS_REMKARK() {
		return S_REMKARK;
	}
	public String getS_OBJ_TBL() {
		return S_OBJ_TBL;
	}
	public void setI_ID(Long i_ID) {
		I_ID = i_ID;
	}
	public void setI_OBJ_TYPE(Integer i_OBJ_TYPE) {
		I_OBJ_TYPE = i_OBJ_TYPE;
	}
	
	public void setI_CYCLE(Integer i_CYCLE) {
		I_CYCLE = i_CYCLE;
	}
	public void setS_TITLE(String s_TITLE) {
		S_TITLE = s_TITLE;
	}
	public void setS_REMKARK(String s_REMKARK) {
		S_REMKARK = s_REMKARK;
	}
	public void setS_OBJ_TBL(String s_OBJ_TBL) {
		S_OBJ_TBL = s_OBJ_TBL;
	}
	public String getS_OBJ_TBL_WHERE() {
		return S_OBJ_TBL_WHERE;
	}
	public void setS_OBJ_TBL_WHERE(String s_OBJ_TBL_WHERE) {
		S_OBJ_TBL_WHERE = s_OBJ_TBL_WHERE;
	}
	public Integer getI_OBJ_LEVEL() {
		return I_OBJ_LEVEL;
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
	public void setI_OBJ_LEVEL(Integer i_OBJ_LEVEL) {
		I_OBJ_LEVEL = i_OBJ_LEVEL;
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
