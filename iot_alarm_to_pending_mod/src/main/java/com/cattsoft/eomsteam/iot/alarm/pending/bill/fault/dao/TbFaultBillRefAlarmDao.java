package com.cattsoft.eomsteam.iot.alarm.pending.bill.fault.dao;

import java.util.List;
import java.util.Map;

import com.cattsoft.eomsteam.iot.alarm.pending.bill.fault.entity.TbFaultBillRefAlarmEntity;

public interface TbFaultBillRefAlarmDao {


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
	
	
	public List<Map<String,Object>> queryBillRefAlarmId(String ids);
}
