package com.cattsoft.eomsteam.iot.alarm.smallClass.dao;

import java.util.List;
import java.util.Map;

public interface FailCodeDao {
	public List<Map<String, Object>> getHttpTop5Fail( Map<String,Object> valueMap);
	public List<Map<String, Object>> getAttachTop5Fail( Map<String,Object> valueMap);
	public List<Map<String, Object>>  getPdnTop5Fail(  Map<String,Object> valueMap);
	public List<Map<String, Object>> getX2Top5Fail(  Map<String,Object> valueMap);
	public List<Map<String, Object>> getS1Top5Fail(  Map<String,Object> valueMap);
	public List<Map<String, Object>> getDnsTop5Fail(  Map<String,Object> valueMap);

}
