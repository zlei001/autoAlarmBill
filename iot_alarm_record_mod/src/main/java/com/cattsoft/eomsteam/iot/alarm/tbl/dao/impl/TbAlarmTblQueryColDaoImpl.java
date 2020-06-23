package com.cattsoft.eomsteam.iot.alarm.tbl.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cattsoft.eomsteam.iot.alarm.tbl.dao.TbAlarmTblQueryColDao;
import com.cattsoft.eomsteam.uc.pub.db.uc_main2.dao.UCMainDao;

@Repository("com.cattsoft.eomsteam.iot.alarm.tbl.dao.TbAlarmTblQueryColDao")
public class TbAlarmTblQueryColDaoImpl  extends UCMainDao implements TbAlarmTblQueryColDao {

	@Override

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
	public List<Map<String,Object>> getAlarmTblQueryColList(Integer I_OBJ_TYPE ,Integer I_NE_MODEL,Integer I_LARGE_CLASS,Integer I_SMALL_CLASS ,Integer I_CYCLE,String S_OBJ_TBL){
		StringBuffer sql = new StringBuffer();
		sql.append(" select  ");
		sql.append("   c.S_COL_NAME          as \"S_COL_NAME\"          ,S_COL_NAME_ALIAS     as \"S_COL_NAME_ALIAS\"");
		sql.append("  ,c.S_COL_NAME_ZH       as \"S_COL_NAME_ZH\"    ");
		sql.append(" from  TB_ALARM_TBL t ,TB_ALARM_TBL_QUERY_COL c  ");
		sql.append(" where t.I_ID =  c.I_REF_ID");
		sql.append(" and t.I_OBJ_TYPE = ? and t.I_NE_MODEL = ? and t.I_CYCLE = ? and t.S_OBJ_TBL = ?");
		sql.append(" and t.I_LARGE_CLASS = ? and I_SMALL_CLASS = ?");
		List<Object> data = new ArrayList<Object>();
		data.add(I_OBJ_TYPE);
		data.add(I_NE_MODEL);
		data.add(I_CYCLE);
		data.add(S_OBJ_TBL);
		data.add(I_LARGE_CLASS);
		data.add(I_SMALL_CLASS);
		
		List<Map<String,Object>> tempResultList =  this.queryBySql(sql.toString(), data);
		return tempResultList;
	}
}
