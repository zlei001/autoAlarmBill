package com.cattsoft.eomsteam.iot.alarm.record.service;

import java.util.List;
import java.util.Map;

import com.cattsoft.eomsteam.iot.alarm.record.entity.TbAlarmRecordEntity;

public interface TbAlarmRecordService {
	

	/***
	 * 告警记录
	 * @param I_REF_ID  策略ID
	 * @param S_DELIMITED_CYCLE 周期
	 * @return
	 */
	public List<TbAlarmRecordEntity> getAlarmRecordListByRefId(Long I_REF_ID,String S_DELIMITED_CYCLE);

	/***
	 * 设置代办
	 * @param setIdList ID列表
	 */
	public void  updateAlarmRecordSetPendingIdByRecordId(List<Map<String,Object>> setIdList);
	
	public void closedAlarmRecordByPendingId(List<Map<String, Object>> timeOutPendingList) ;
}
