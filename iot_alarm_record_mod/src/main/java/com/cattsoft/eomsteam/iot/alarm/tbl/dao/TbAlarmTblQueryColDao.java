package com.cattsoft.eomsteam.iot.alarm.tbl.dao;

import java.util.List;
import java.util.Map;

public interface TbAlarmTblQueryColDao {

	/***
	 * 
	 * @param I_OBJ_TYPE
	 * @param I_NE_MODEL
	 * @param I_LARGE_CLASS
	 * @param I_SMALL_CLASS
	 * @param I_CYCLE
	 * @param S_OBJ_TBL
	 * @return
	 */
	public List<Map<String,Object>> getAlarmTblQueryColList(Integer I_OBJ_TYPE ,Integer I_NE_MODEL,Integer I_LARGE_CLASS,Integer I_SMALL_CLASS ,Integer I_CYCLE,String S_OBJ_TBL);
}
