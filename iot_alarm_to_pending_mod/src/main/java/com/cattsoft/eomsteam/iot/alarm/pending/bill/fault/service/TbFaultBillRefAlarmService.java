package com.cattsoft.eomsteam.iot.alarm.pending.bill.fault.service;

import java.util.List;
import java.util.Map;

import com.cattsoft.eomsteam.iot.alarm.pending.bill.fault.entity.TbFaultBillRefAlarmEntity;

public interface TbFaultBillRefAlarmService {

	/***
	 * 主键
	 * @return
	 */
	public Long getID() ;

	/***
	 * 保存工单列表
	 * @param tbFaultBillRefAlarmList
	 */
	public void save(List<TbFaultBillRefAlarmEntity> tbFaultBillRefAlarmList) ;
	
	public Map<Long,Long> queryBillRefAlarmId(String ids);
}
