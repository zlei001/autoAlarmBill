package com.cattsoft.eomsteam.iot.alarm.exec.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cattsoft.eomsteam.iot.alarm.conf.entity.TbAlarmConfigEntity;
import com.cattsoft.eomsteam.iot.alarm.conf.service.TbAlarmConfigService;
import com.cattsoft.eomsteam.iot.alarm.exec.AlarmLogRunningService;
import com.cattsoft.eomsteam.iot.alarm.log.entity.TbAlarmConfigLogEntity;
import com.cattsoft.eomsteam.iot.alarm.log.service.TbAlarmConfigLogService;
import com.cattsoft.eomsteam.uc.pub.db.DefaultStringUtil;

@Service("com.cattsoft.eomsteam.iot.alarm.exec.AlarmLogRunningService") 
public class AlarmLogRunningServiceImpl implements AlarmLogRunningService {

	@Autowired
	private TbAlarmConfigService tbAlarmConfigService;
	
	@Autowired
	private TbAlarmConfigLogService tbAlarmConfigLogService;
	
	@Override
	public void doPending() {
		// TODO Auto-generated method stub
		List<TbAlarmConfigEntity> alarmConfigList = tbAlarmConfigService.getAlarmConfigList();
		for(TbAlarmConfigEntity tbAlarmConfigEntity : alarmConfigList){
			TbAlarmConfigLogEntity tbAlarmConfigLogEntity = new TbAlarmConfigLogEntity();
			tbAlarmConfigLogEntity.setI_CREATE_TIME(DefaultStringUtil.getNowTime("yyyyMMddHHmmss"));
			tbAlarmConfigLogEntity.setI_CYCLE(tbAlarmConfigEntity.getI_CYCLE());
			tbAlarmConfigLogEntity.setI_FLAG(1);
			tbAlarmConfigLogEntity.setI_OBJ_TYPE(tbAlarmConfigEntity.getI_OBJ_TYPE());
			tbAlarmConfigLogEntity.setI_REF_ID(tbAlarmConfigEntity.getI_ID());
			tbAlarmConfigLogEntity.setI_RUN_CNT(0);
			tbAlarmConfigLogEntity.setS_INST_CYCLE(getS_INST_CYCLE(tbAlarmConfigEntity.getI_CYCLE()));
			tbAlarmConfigLogService.save(tbAlarmConfigLogEntity);
		}
	}

	/***
	 * 周期实例
	 * @I_CYCLE 周期
	 */
	private String getS_INST_CYCLE(Integer I_CYCLE){
		if(2 == I_CYCLE){
			return String.valueOf(DefaultStringUtil.getNowTime("yyyyMMddHH"));
		}else{
		    return  String.valueOf(DefaultStringUtil.getNowTime("yyyyMMdd"));
		}
	}
}
