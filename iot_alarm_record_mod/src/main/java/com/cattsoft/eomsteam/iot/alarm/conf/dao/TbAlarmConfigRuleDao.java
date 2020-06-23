package com.cattsoft.eomsteam.iot.alarm.conf.dao;

import java.util.List;
import java.util.Map;

/***
 * 定界规则dao则
 * @author psy
 *
 */
public interface TbAlarmConfigRuleDao {

	/***
	 * 定界规则列表
	 * @return
	 */
	public List<Map<String,Object>> getAlarmConfigRuleList();
}
