package com.cattsoft.eomsteam.iot.stat.record.service;

import java.util.List;
import java.util.Map;

import com.cattsoft.eomsteam.iot.stat.record.entity.TbStatSqlEntity;

public interface TbStatSqlService {

	public List<TbStatSqlEntity> getStatSqlList();
	
	public List<Map<String, Object>> getStatResultSqlList(String sql);
}
