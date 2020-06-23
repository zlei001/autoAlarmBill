package com.cattsoft.eomsteam.iot.alarm.tbl.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cattsoft.eomsteam.iot.alarm.tbl.dao.TbAlarmTblDao;
import com.cattsoft.eomsteam.uc.pub.db.uc_main2.dao.UCMainDao;

@Repository("com.cattsoft.eomsteam.iot.alarm.tbl.dao.TbAlarmTblDao")
public class TbAlarmTblDaoImpl   extends UCMainDao implements TbAlarmTblDao {
	
	@Override
	public Map<String,Object> getAlarmTblById(Long  I_ID){
		StringBuffer sql = new StringBuffer();
		sql.append(" select  ");
		sql.append("   I_ID          as \"I_ID\"          ,I_OBJ_TYPE     as \"I_OBJ_TYPE\"");
		sql.append("  ,I_CYCLE        as \"I_CYCLE\"");
		sql.append("  ,I_NE_MODEL    as \"I_NE_MODEL\"    ,I_LARGE_CLASS as \"I_LARGE_CLASS\"");
		sql.append("  ,I_SMALL_CLASS as \"I_SMALL_CLASS\" ");
		sql.append("  ,S_OBJ_TBL as \"S_OBJ_TBL\" ");
		sql.append("  ,S_OBJ_TBL_WHERE as \"S_OBJ_TBL_WHERE\" ");
		sql.append(" from  TB_ALARM_TBL ");
		sql.append(" where I_ID =  ?");
		List<Object> data = new ArrayList<Object>();
		data.add(I_ID);
		
		List<Map<String,Object>> tempResultList =  this.queryBySql(sql.toString(), data);
		if(tempResultList.size() > 0){
			return tempResultList.get(0);
		}
		return null;
	}
}
