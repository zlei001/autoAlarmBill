package com.cattsoft.eomsteam.iot.alarm.conf.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cattsoft.eomsteam.iot.alarm.conf.dao.TbAlarmConfigDao;
import com.cattsoft.eomsteam.uc.pub.db.uc_main2.dao.UCMainDao;

@Repository("com.cattsoft.eomsteam.iot.alarm.conf.dao.TbDelimitedConfigDao")
public class TbAlarmConfigDaoImpl  extends UCMainDao implements TbAlarmConfigDao {

	@Override
	public List<Map<String,Object>> getAlarmConfigList(){
		StringBuffer sql = new StringBuffer();
		sql.append(" select  ");
		sql.append("   I_ID           as \"I_ID\"         ,I_OBJ_TYPE      as \"I_OBJ_TYPE\"");
		sql.append("  ,I_OBJ_LEVEL    as \"I_OBJ_LEVEL\"  ,I_CYCLE         as \"I_CYCLE\"");
		sql.append("  ,I_NE_MODEL     as \"I_NE_MODEL\"   ,I_LARGE_CLASS   as \"I_LARGE_CLASS\"");
		sql.append("  ,I_SMALL_CLASS  as \"I_SMALL_CLASS\"  ");
		sql.append("  ,S_TITLE        as \"S_TITLE\"   ,S_REMKARK       as \"S_REMKARK\"");
		sql.append(" from  TB_ALARM_CONFIG ");
		sql.append(" where I_FLAG  = 1");
		sql.append(" order by I_OBJ_TYPE asc");
		List<Object> data = new ArrayList<Object>();
		List<Map<String,Object>> tempResultList =  this.queryBySql(sql.toString(), data);
		return tempResultList;
	}

	@Override
	public Map<String, Object> getAlarmConfigById(Long I_ID) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select  ");
		sql.append("   I_ID           as \"I_ID\"         ,I_OBJ_TYPE      as \"I_OBJ_TYPE\"");
		sql.append("  ,I_OBJ_LEVEL    as \"I_OBJ_LEVEL\"  ,I_CYCLE         as \"I_CYCLE\"");
		sql.append("  ,I_NE_MODEL     as \"I_NE_MODEL\"   ,I_LARGE_CLASS   as \"I_LARGE_CLASS\"");
		sql.append("  ,I_SMALL_CLASS  as \"I_SMALL_CLASS\"  ");
		sql.append("  ,S_TITLE        as \"S_TITLE\"   ,S_REMKARK       as \"S_REMKARK\"");
		sql.append(" from  TB_ALARM_CONFIG ");
		sql.append(" where I_ID  =  ? ");
		sql.append(" order by I_OBJ_TYPE asc");
		List<Object> data = new ArrayList<Object>();
		data.add(I_ID);
		List<Map<String,Object>> tempResultList =  this.queryBySql(sql.toString(), data);
		if(tempResultList.size() > 0){
			return tempResultList.get(0);
		}
		return null;
	}
}
