package com.cattsoft.eomsteam.iot.stat.exec.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cattsoft.eomsteam.iot.stat.exec.StatAlarmClosedRunningService;
import com.cattsoft.eomsteam.iot.stat.log.entity.TbStatSqlLogEntity;
import com.cattsoft.eomsteam.iot.stat.log.service.TbStatSqlLogService;
import com.cattsoft.eomsteam.uc.pub.db.DefaultStringUtil;

@Service("com.cattsoft.eomsteam.iot.stat.exec.StatAlarmClosedRunningService") 
public class StatAlarmClosedRunningServiceImpl implements StatAlarmClosedRunningService {
	 /**
     * 日志对象
     */
    private static Logger logger = Logger.getLogger(StatAlarmClosedRunningServiceImpl.class);
	
	@Autowired
	private TbStatSqlLogService tbStatRecordLogService;

	@Override
	public void doPending() {
		List<TbStatSqlLogEntity> configLogList = tbStatRecordLogService.getAlarmConfigLogList();
		for(TbStatSqlLogEntity configLog : configLogList){
			String startTime = configLog.getS_INST_CYCLE().substring(0, 8);
			String endTime = DefaultStringUtil.getStrNowTime("yyyyMMdd");
			long ms = DefaultStringUtil.getTwoTimeDayHourMS(startTime, endTime, "yyyyMMdd") / 60 /60/24/3;
			if(ms >1){
				logger.info("归档运行日志:"+configLog.getI_ID());
				tbStatRecordLogService.updateAlarmConfigLogEndFlagById(configLog.getI_ID());
			}
		}
	}
	
}
