package com.cattsoft.eomsteam.iot.stat.record.entity;

import java.io.Serializable;

public class TbStatSqlEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3381908802917531850L;
	
	private Long I_ID;
	private Integer I_OBJ_TYPE;
	private Integer I_STAT_SOURCE;
	private Integer I_STAT_TYPE;
	private String S_OBJ_SQL;
	public Long getI_ID() {
		return I_ID;
	}
	public Integer getI_OBJ_TYPE() {
		return I_OBJ_TYPE;
	}
	public Integer getI_STAT_TYPE() {
		return I_STAT_TYPE;
	}
	public String getS_OBJ_SQL() {
		return S_OBJ_SQL;
	}
	public void setI_ID(Long i_ID) {
		I_ID = i_ID;
	}
	public void setI_OBJ_TYPE(Integer i_OBJ_TYPE) {
		I_OBJ_TYPE = i_OBJ_TYPE;
	}
	public void setI_STAT_TYPE(Integer i_STAT_TYPE) {
		I_STAT_TYPE = i_STAT_TYPE;
	}
	public void setS_OBJ_SQL(String s_OBJ_SQL) {
		S_OBJ_SQL = s_OBJ_SQL;
	}
	public Integer getI_STAT_SOURCE() {
		return I_STAT_SOURCE;
	}
	public void setI_STAT_SOURCE(Integer i_STAT_SOURCE) {
		I_STAT_SOURCE = i_STAT_SOURCE;
	}
}
