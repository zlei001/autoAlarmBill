package com.cattsoft.eomsteam.iot.alarm.conf.entity;

import java.io.Serializable;

import util.StringUtil;

/***
 * 定界配置规则实体
 * @author Administrator
 *
 */
public class TbAlarmConfigRuleEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6605989996008721924L;

	private Long I_ID;
	private Long I_TBL_ID;
	private Long I_REF_ID;
	private String S_COL_NAME;
	private String S_COL_EXP_1;
	private String S_COL_VAL_1;
	private String S_COL_VAL_EXP;
	private String S_COL_EXP_2;
	private String S_COL_VAL_2;
	private String S_COL_UNIT;
	
	public Long getI_ID() {
		return I_ID;
	}
	public Long getI_REF_ID() {
		return I_REF_ID;
	}
	public String getS_COL_NAME() {
		return S_COL_NAME;
	}
	public String getS_COL_EXP_1() {
		return S_COL_EXP_1;
	}
	public String getS_COL_VAL_1() {
		return S_COL_VAL_1;
	}
	public String getS_COL_VAL_EXP() {
		return S_COL_VAL_EXP;
	}
	public String getS_COL_EXP_2() {
		return S_COL_EXP_2;
	}
	public String getS_COL_VAL_2() {
		return S_COL_VAL_2;
	}
	public String getS_COL_UNIT() {
		return S_COL_UNIT;
	}
	public void setI_ID(Long i_ID) {
		I_ID = i_ID;
	}
	public void setI_REF_ID(Long i_REF_ID) {
		I_REF_ID = i_REF_ID;
	}
	public void setS_COL_NAME(String s_COL_NAME) {
		S_COL_NAME = s_COL_NAME;
	}
	public void setS_COL_EXP_1(String s_COL_EXP_1) {
		S_COL_EXP_1 = s_COL_EXP_1;
	}
	public void setS_COL_VAL_1(String s_COL_VAL_1) {
		S_COL_VAL_1 = s_COL_VAL_1;
	}
	public void setS_COL_VAL_EXP(String s_COL_VAL_EXP) {
		S_COL_VAL_EXP = s_COL_VAL_EXP;
	}
	public void setS_COL_EXP_2(String s_COL_EXP_2) {
		S_COL_EXP_2 = s_COL_EXP_2;
	}
	public void setS_COL_VAL_2(String s_COL_VAL_2) {
		S_COL_VAL_2 = s_COL_VAL_2;
	}
	public void setS_COL_UNIT(String s_COL_UNIT) {
		S_COL_UNIT = s_COL_UNIT;
	}
	
	
	
	public Long getI_TBL_ID() {
		return I_TBL_ID;
	}
	public void setI_TBL_ID(Long i_TBL_ID) {
		I_TBL_ID = i_TBL_ID;
	}
	/***
	 * 查询条件
	 */
	public String getWhereSql(){
		StringBuffer col1Sql = new StringBuffer();
		StringBuffer col2Sql = new StringBuffer();
		StringBuffer whereSql = new StringBuffer();
		if(StringUtil.checkStr(S_COL_EXP_1) && StringUtil.checkStr(S_COL_VAL_1)){
			col1Sql.append(this.getS_COL_NAME()).append(" ").append(this.getS_COL_EXP_1()).append(" ").append(this.getS_COL_VAL_1());
		}
		if(StringUtil.checkStr(S_COL_EXP_2) && StringUtil.checkStr(S_COL_VAL_2)){
			col2Sql.append(this.getS_COL_NAME()).append(" ").append(this.getS_COL_EXP_2()).append(" ").append(this.getS_COL_VAL_2());
		}
		
		if(col1Sql.length() > 0 && col2Sql.length() >0){
			whereSql.append("(").append(col1Sql).append(" ").append(this.getS_COL_VAL_EXP()).append(" ").append(col2Sql).append(" )");
		}else if(col1Sql.length() > 0){
			whereSql.append("(").append(col1Sql).append(" )");
		}else{
			whereSql.append("(").append(col2Sql).append(" )");
		}
		return whereSql.toString();
	}

}
