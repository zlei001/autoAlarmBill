package com.cattsoft.eomsteam.iot.alarm.conf.dao;

import java.util.List;
import java.util.Map;


/***
 * 定界配置DAO
 * @author Administrator
 *
 */
public interface TbAlarmConfigDao {

	/***
	 * 定界配置列表
	 * @return
	 */
	public List<Map<String,Object>> getAlarmConfigList();
	
	public Map<String,Object> getAlarmConfigById(Long I_ID);
}
