package com.cattsoft.eomsteam.iot.alarm.record.busi;

import java.util.List;

import com.cattsoft.eomsteam.iot.alarm.conf.entity.TbAlarmConfigEntity;
import com.cattsoft.eomsteam.iot.alarm.conf.entity.TbAlarmConfigRuleEntity;
import com.cattsoft.eomsteam.iot.alarm.log.entity.TbAlarmConfigLogEntity;
import com.cattsoft.eomsteam.iot.alarm.record.entity.TbAlarmRecordEntity;

/***
 * ¸æ¾¯ÒµÎñÂß¼­
 * @author Administrator
 *
 */
public interface AlarmRecordBusiService {

	/***
	 * 
	 * @param delimitedPendingSql
	 * @param tbDelimitedConfigLogEntity
	 * @param tbDelimitedConfigEntity
	 * @return
	 */
	public List<TbAlarmRecordEntity> doBusiPending(String delimitedPendingSql,TbAlarmConfigLogEntity tbDelimitedConfigLogEntity,TbAlarmConfigEntity tbDelimitedConfigEntity,List<TbAlarmConfigRuleEntity> delimitedConfigRuleEntityList );
}
