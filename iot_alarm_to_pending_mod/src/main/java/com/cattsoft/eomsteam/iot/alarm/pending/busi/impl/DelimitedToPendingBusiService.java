package com.cattsoft.eomsteam.iot.alarm.pending.busi.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.cattsoft.eomsteam.iot.alarm.pending.busi.abs.AbsAlarmPendingBusiService;
import com.cattsoft.eomsteam.iot.alarm.record.entity.TbAlarmRecordEntity;

/***
 * ¶¨½ç¸æ¾¯
 * @author Administrator
 *
 */
@Service("com.cattsoft.eomsteam.iot.alarm.pending.busi.2") 
public class DelimitedToPendingBusiService extends AbsAlarmPendingBusiService {

	@Override
	public void doAlarmRecordToPending(List<TbAlarmRecordEntity> recordList,Map<String,Object> pendingData) {
		// TODO Auto-generated method stub
		
	}

}
