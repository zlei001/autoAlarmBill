package com.cattsoft.eomsteam.iot.alarm.log.entity;

import java.io.Serializable;

/***
 * 定界配置日志
 * @author Administrator
 *
 */
public class TbAlarmConfigLogEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6474135437938039662L;

	private Long I_ID;
	private Long I_REF_ID;
	private Integer I_CYCLE;
	private Integer I_FLAG;
	private Long I_CREATE_TIME;
	private String S_INST_CYCLE;
	private Integer I_RUN_CNT;
	private Integer I_OBJ_TYPE;
	private Long I_PENDING_CNT;
	public Long getI_ID() {
		return I_ID;
	}
	public Long getI_REF_ID() {
		return I_REF_ID;
	}
	public Integer getI_CYCLE() {
		return I_CYCLE;
	}
	public Integer getI_FLAG() {
		return I_FLAG;
	}
	public Long getI_CREATE_TIME() {
		return I_CREATE_TIME;
	}
	public String getS_INST_CYCLE() {
		return S_INST_CYCLE;
	}
	public Integer getI_RUN_CNT() {
		return I_RUN_CNT;
	}
	public void setI_ID(Long i_ID) {
		I_ID = i_ID;
	}
	public void setI_REF_ID(Long i_REF_ID) {
		I_REF_ID = i_REF_ID;
	}
	public void setI_CYCLE(Integer i_CYCLE) {
		I_CYCLE = i_CYCLE;
	}
	public void setI_FLAG(Integer i_FLAG) {
		I_FLAG = i_FLAG;
	}
	public void setI_CREATE_TIME(Long i_CREATE_TIME) {
		I_CREATE_TIME = i_CREATE_TIME;
	}
	public void setS_INST_CYCLE(String s_INST_CYCLE) {
		S_INST_CYCLE = s_INST_CYCLE;
	}
	public void setI_RUN_CNT(Integer i_RUN_CNT) {
		I_RUN_CNT = i_RUN_CNT;
	}
	public Integer getI_OBJ_TYPE() {
		return I_OBJ_TYPE;
	}
	public void setI_OBJ_TYPE(Integer i_OBJ_TYPE) {
		I_OBJ_TYPE = i_OBJ_TYPE;
	}
	public Long getI_PENDING_CNT() {
		return I_PENDING_CNT;
	}
	public void setI_PENDING_CNT(Long i_PENDING_CNT) {
		I_PENDING_CNT = i_PENDING_CNT;
	}
	
}
