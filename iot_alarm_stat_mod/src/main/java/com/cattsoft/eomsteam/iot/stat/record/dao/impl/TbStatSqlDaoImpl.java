package com.cattsoft.eomsteam.iot.stat.record.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cattsoft.eomsteam.iot.stat.record.dao.TbStatSqlDao;
import com.cattsoft.eomsteam.uc.pub.db.uc_main2.dao.UCMainDao;

@Repository("com.cattsoft.eomsteam.iot.stat.record.dao.TbStatSqlDao")
public class TbStatSqlDaoImpl  extends UCMainDao implements TbStatSqlDao {

	@Override
	public List<Map<String, Object>> getStatSqlList() {
		StringBuffer sql = new StringBuffer();
		sql.append(" select  ");
		sql.append("   I_ID         as \"I_ID\"      ,I_OBJ_TYPE  as \"I_OBJ_TYPE\"");
		sql.append(" ,I_STAT_TYPE   as \"I_STAT_TYPE\" ,S_OBJ_SQL as \"S_OBJ_SQL\"");
		sql.append(" ,I_STAT_SOURCE as \"I_STAT_SOURCE\"");
		sql.append(" from  TB_STAT_SQL ");
		List<Object> data = new ArrayList<Object>();
		List<Map<String,Object>> tempResultList =  this.queryBySql(sql.toString(), data);
		return tempResultList;
	}

	public List<Map<String, Object>> getStatResultSqlList(String sql){
		List<Object> data = new ArrayList<Object>();
		List<Map<String,Object>> tempResultList =  this.queryBySql(sql.toString(), data);
		return tempResultList;
	}
}
