package com.cattsoft.eomsteam.iot.alarm.conf.service;

import java.util.List;
import java.util.Map;

import com.cattsoft.eomsteam.iot.alarm.conf.entity.TbAlarmConfigRuleEntity;

/***
 * 定界规则服务
 * @author Administrator
 *
 */
public interface TbAlarmConfigRuleService {

	/***
	 * 定界规则配置列表
	 * @return
	 */
	public Map<Long,List<TbAlarmConfigRuleEntity>> getAlarmConfigRuleData();
	
	/***
	 * 条件
	 * @param delimitedConfigRuleEntityList 条件列表
	 * @return
	 */
	public String getDelimitedConfigWhereSql(List<TbAlarmConfigRuleEntity> delimitedConfigRuleEntityList);
}
