package com.cattsoft.eomsteam.iot.alarm.pending.service;

import java.util.List;
import java.util.Map;

import com.cattsoft.eomsteam.iot.alarm.pending.entity.TbAlarmPendingEntity;

public interface TbAlarmPendingService {

	public Long getID();
	
	/***
	 * 批量保存告警
	 * @param alarmPendingEntityList
	 */
	public void save(List<TbAlarmPendingEntity> alarmPendingEntityList);
	
	/***
	 * 查询是否有告警
	 * @param I_REF_ID
	 * @param S_DELIMITED_CYCLE
	 * @return
	 */
	public Map<String, Object> getAlarmPendingDataByRefId(Long I_REF_ID,String S_DELIMITED_CYCLE) ;
	
	/***
	 * 告警叠加数量
	 * @param setIdList
	 */
	public void  updateAlarmZipCntByPendingId(List<Map<String,Object>> setIdList);
	
	/***
	 * 查询超时无告警数据
	 * @param I_REF_ID
	 * @return
	 */
	public List<Map<String, Object>> getAlarmTimeOutPendingListByRefId(Long I_REF_ID) ;
	
	/***
	 * 关闭告警代办
	 * @param setIdList
	 */
	public void  updateAlarmPendingClosedByPendingId(List<Map<String,Object>> setIdList);
}
