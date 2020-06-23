package com.cattsoft.eomsteam.iot.alarm.exec.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cattsoft.eomsteam.iot.alarm.exec.AlarmLogClosedRunningService;
import com.cattsoft.eomsteam.iot.alarm.log.entity.TbAlarmConfigLogEntity;
import com.cattsoft.eomsteam.iot.alarm.log.service.TbAlarmConfigLogService;
import com.cattsoft.eomsteam.iot.alarm.record.service.TbAlarmRecordService;
import com.cattsoft.eomsteam.uc.pub.db.DefaultStringUtil;

@Service("com.cattsoft.eomsteam.iot.alarm.exec.AlarmLogClosedRunningService") 
public class AlarmLogClosedRunningServiceImpl implements AlarmLogClosedRunningService { 

    /**
     * 日志对象
     */
    private static Logger logger = Logger.getLogger(AlarmLogClosedRunningServiceImpl.class);
	
	@Autowired
	private TbAlarmConfigLogService tbAlarmConfigLogService;
	
	@Autowired
	private TbAlarmRecordService tbAlarmPendingService;

	@Override
	public void doPending() {
		List<TbAlarmConfigLogEntity> configLogList = tbAlarmConfigLogService.getAlarmConfigLogList();
		for(TbAlarmConfigLogEntity configLog : configLogList){
			Map<String,Object> pendingData = tbAlarmPendingService.getAlarmPendingDataByRefId(configLog.getI_REF_ID(), configLog.getS_INST_CYCLE());
			String idInStr = this.getIdStr(pendingData);
			String startTime = configLog.getS_INST_CYCLE().substring(0, 8);
			String endTime = DefaultStringUtil.getStrNowTime("yyyyMMdd");
			long ms = DefaultStringUtil.getTwoTimeDayHourMS(startTime, endTime, "yyyyMMdd") / 60 /60/24/2;
			if(ms >=1){
				logger.info("归档运行日志:"+configLog.getI_ID());
				if(idInStr.length() >0){
					tbAlarmPendingService.updateAlarmConfigLogEndFlagByIds(getIdList(pendingData));
				}
				tbAlarmConfigLogService.updateAlarmConfigLogEndFlagById(configLog.getI_ID());
			}
		}
	}
	
	/***
	 * ID字符串
	 * @param pendingData 代办数据
	 * @return
	 */
	private String getIdStr(Map<String,Object> pendingData){
		StringBuffer id = new StringBuffer();

		Iterator<String> iterator = pendingData.keySet().iterator();

		while(iterator.hasNext()){
			String key = iterator.next();
			if(id.length() >0){
				id.append(",");
			}
			id.append(key);

		}

		return id.toString();
	}

	/***
	 * ID字符串
	 * @param pendingData 代办数据
	 * @return
	 */
	private List<String> getIdList(Map<String,Object> pendingData){

		List<Map<String,Object>> mapList =new ArrayList<Map<String, Object>>();
		mapList.add(pendingData);

		List<String> list= new ArrayList<String>();
		Iterator<String> iterator = pendingData.keySet().iterator();
		while(iterator.hasNext()){
			String key = iterator.next();
			list.add(key);
		}

		return list;
	}



}
