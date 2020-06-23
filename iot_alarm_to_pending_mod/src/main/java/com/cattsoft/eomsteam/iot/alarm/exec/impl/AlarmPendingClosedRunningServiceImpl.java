package com.cattsoft.eomsteam.iot.alarm.exec.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cattsoft.eomsteam.iot.alarm.conf.entity.TbAlarmConfigEntity;
import com.cattsoft.eomsteam.iot.alarm.conf.service.TbAlarmConfigService;
import com.cattsoft.eomsteam.iot.alarm.exec.AlarmPendingClosedRunningService;
import com.cattsoft.eomsteam.iot.alarm.pending.service.TbAlarmPendingService;

@Service("com.cattsoft.eomsteam.iot.alarm.exec.AlarmPendingClosedRunningService") 
public class AlarmPendingClosedRunningServiceImpl implements AlarmPendingClosedRunningService {
	
    /**
     * 日志对象
     */
    private static Logger logger = Logger.getLogger(AlarmPendingClosedRunningServiceImpl.class);

	@Autowired
	private TbAlarmPendingService tbAlarmPendingService;
	
	@Autowired
	private TbAlarmConfigService tbAlarmConfigService;
	
	
	
	@Override
	public void doPending() {
		// TODO Auto-generated method stub
		List<TbAlarmConfigEntity> configList = tbAlarmConfigService.getAlarmConfigList();
		for(TbAlarmConfigEntity tbAlarmConfigEntity : configList){
			List<Map<String, Object>> timeOutPendingList = tbAlarmPendingService.getAlarmTimeOutPendingListByRefId(tbAlarmConfigEntity.getI_ID());
			logger.info(tbAlarmConfigEntity.getS_TITLE()+"["+tbAlarmConfigEntity.getI_ID()+"],需关闭代办数量:"+timeOutPendingList.size());
			tbAlarmPendingService.updateAlarmPendingClosedByPendingId(timeOutPendingList);
		}
	}

}
