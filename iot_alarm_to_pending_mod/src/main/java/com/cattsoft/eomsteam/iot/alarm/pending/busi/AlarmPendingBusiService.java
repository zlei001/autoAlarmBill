package com.cattsoft.eomsteam.iot.alarm.pending.busi;

import java.util.List;
import java.util.Map;

import com.cattsoft.eomsteam.iot.alarm.record.entity.TbAlarmRecordEntity;

public interface AlarmPendingBusiService {

	public void doAlarmRecordToPending(List<TbAlarmRecordEntity> recordList,Map<String,Object> pendingData);
}
