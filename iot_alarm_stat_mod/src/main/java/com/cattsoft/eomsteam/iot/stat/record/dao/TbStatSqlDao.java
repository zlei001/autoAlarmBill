package com.cattsoft.eomsteam.iot.stat.record.dao;

import java.util.List;
import java.util.Map;

public interface TbStatSqlDao {

	public List<Map<String,Object>> getStatSqlList();
	
	public List<Map<String, Object>> getStatResultSqlList(String sql);
}
