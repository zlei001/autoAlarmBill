package com.cattsoft.eomsteam.iot.alarm.pending.bill.fault.busi;

import java.util.List;

import com.cattsoft.eomsteam.iot.alarm.pending.pending.entity.TbAlarmPendingEntity;

public interface ZipPendingCommonBusiService {

	/***
	 * 
	 * @param tbAlarmPendingEntity
	 * @return
	 */
	public String getMsg(List<TbAlarmPendingEntity> alarmPendingEntityList);
	

	/***
	 * Í¨ÓÃÎÄ×Ö
	 * @return
	 */
	public String getTitle(TbAlarmPendingEntity tbAlarmPendingEntity);

}
