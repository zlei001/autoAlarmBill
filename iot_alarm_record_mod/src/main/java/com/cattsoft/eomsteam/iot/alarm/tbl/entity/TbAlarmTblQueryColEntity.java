package com.cattsoft.eomsteam.iot.alarm.tbl.entity;

import java.io.Serializable;

public class TbAlarmTblQueryColEntity implements Serializable {

	/**
	 * 系列化
	 */
	private static final long serialVersionUID = 7728941370968788152L;
	
	private String S_COL_NAME;
	
	private String S_COL_NAME_ALIAS;
	
	private String S_COL_NAME_ZH;
	
	public String getS_COL_NAME() {
		return S_COL_NAME;
	}
	public String getS_COL_NAME_ALIAS() {
		return S_COL_NAME_ALIAS;
	}
	public void setS_COL_NAME(String s_COL_NAME) {
		S_COL_NAME = s_COL_NAME;
	}
	public void setS_COL_NAME_ALIAS(String s_COL_NAME_ALIAS) {
		S_COL_NAME_ALIAS = s_COL_NAME_ALIAS;
	}
	
	
	public String getS_COL_NAME_ZH() {
		return S_COL_NAME_ZH;
	}
	public void setS_COL_NAME_ZH(String s_COL_NAME_ZH) {
		S_COL_NAME_ZH = s_COL_NAME_ZH;
	}
	/***
	 * 转化为sql
	 * @return
	 */
	public String toSql(){
		StringBuffer sql = new StringBuffer();
		sql.append(S_COL_NAME).append(" as \"").append(S_COL_NAME_ALIAS).append("\"");
		return sql.toString();
	}

}
