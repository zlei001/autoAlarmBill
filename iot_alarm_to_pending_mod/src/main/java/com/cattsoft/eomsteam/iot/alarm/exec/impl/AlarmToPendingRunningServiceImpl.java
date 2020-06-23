package com.cattsoft.eomsteam.iot.alarm.exec.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cattsoft.eomsteam.iot.alarm.conf.entity.TbAlarmConfigEntity;
import com.cattsoft.eomsteam.iot.alarm.conf.service.TbAlarmConfigService;
import com.cattsoft.eomsteam.iot.alarm.exec.AlarmToPendingRunningService;
import com.cattsoft.eomsteam.iot.alarm.log.entity.TbAlarmConfigLogEntity;
import com.cattsoft.eomsteam.iot.alarm.log.service.TbAlarmConfigLogService;
import com.cattsoft.eomsteam.iot.alarm.pending.busi.impl.AlarmToPendingBusiService;
import com.cattsoft.eomsteam.iot.alarm.pending.service.TbAlarmPendingService;
import com.cattsoft.eomsteam.iot.alarm.record.entity.TbAlarmRecordEntity;
import com.cattsoft.eomsteam.iot.alarm.record.service.TbAlarmRecordService;

@Service("com.cattsoft.eomsteam.iot.alarm.exec.AlarmToPendingRunningService") 
public class AlarmToPendingRunningServiceImpl implements AlarmToPendingRunningService {
	
    /**
     * 日志对象
     */
    private static Logger logger = Logger.getLogger(AlarmToPendingRunningServiceImpl.class);

	@Autowired
	private TbAlarmConfigLogService tbAlarmConfigLogService;
	
	@Autowired
	private TbAlarmConfigService tbAlarmConfigService;
	
	@Autowired
	private TbAlarmRecordService tbAlarmRecordService;
	
	@Autowired
	private AlarmToPendingBusiService alarmToPendingBusiService;
	
	@Autowired
	private TbAlarmPendingService tbAlarmPendingService;
	
	@Override
	public void doPending() {
		List<TbAlarmConfigLogEntity> configLogEntityList  = tbAlarmConfigLogService.getAlarmConfigLogList();
		logger.info("运行策略日志数量:"+configLogEntityList.size());
		for(TbAlarmConfigLogEntity tbDelimitedConfigLogEntity : configLogEntityList){
			TbAlarmConfigEntity tbAlarmConfigEntity = tbAlarmConfigService.getAlarmConfigById(tbDelimitedConfigLogEntity.getI_REF_ID());
			if(null != tbAlarmConfigEntity){
				List<TbAlarmRecordEntity> recordList = tbAlarmRecordService.getAlarmRecordListByRefId(tbDelimitedConfigLogEntity.getI_REF_ID(), tbDelimitedConfigLogEntity.getS_INST_CYCLE());
				logger.info(tbAlarmConfigEntity.getS_TITLE()+"["+tbAlarmConfigEntity.getI_ID()+"],需操作代办数量:"+recordList.size());
				if(null !=recordList && recordList.size() >0){
					Map<String,Object> pendingData  =  tbAlarmPendingService.getAlarmPendingDataByRefId(tbDelimitedConfigLogEntity.getI_REF_ID(), tbDelimitedConfigLogEntity.getS_INST_CYCLE());
					alarmToPendingBusiService.doAlarmRecordToPending(recordList,pendingData);
				}
			
			}else{
				logger.info("运行策略日志ID:"+tbDelimitedConfigLogEntity.getI_REF_ID()+",已不存在");
			}
		}
	}
	
	/***
	 * 返回处理业务ID
	 * @param tbDelimitedConfigEntity
	 * @return
	 */
	private String getBusiId(TbAlarmConfigEntity tbDelimitedConfigEntity){
		String busiId = "com.cattsoft.eomsteam.iot.alarm.record.busi."+tbDelimitedConfigEntity.getI_OBJ_TYPE();
		return busiId;
	}
	
	

}
