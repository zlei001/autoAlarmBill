package com.cattsoft.eomsteam.iot.stat.exec.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cattsoft.eomsteam.iot.stat.exec.StatAlarmLogRunningService;
import com.cattsoft.eomsteam.iot.stat.log.entity.TbStatSqlLogEntity;
import com.cattsoft.eomsteam.iot.stat.log.service.TbStatSqlLogService;
import com.cattsoft.eomsteam.iot.stat.record.entity.TbStatSqlEntity;
import com.cattsoft.eomsteam.iot.stat.record.service.TbStatSqlService;
import com.cattsoft.eomsteam.uc.pub.db.DefaultStringUtil;

@Service("com.cattsoft.eomsteam.iot.stat.exec.StatAlarmLogRunningService") 
public class StatAlarmLogRunningServiceImpl implements StatAlarmLogRunningService {


	@Autowired
	private TbStatSqlService tbStatSqlService;
	
	@Autowired
	private TbStatSqlLogService tbStatRecordLogService;
	
	@Override
	public void doPending() {
		// TODO Auto-generated method stub
		List<TbStatSqlEntity> alarmConfigList = tbStatSqlService.getStatSqlList();
		for(TbStatSqlEntity tbStatSqlEntity : alarmConfigList){
			TbStatSqlLogEntity tbStatRecordLogEntity = new TbStatSqlLogEntity();
			tbStatRecordLogEntity.setI_CREATE_TIME(DefaultStringUtil.getNowTime("yyyyMMddHHmmss"));
			tbStatRecordLogEntity.setI_FLAG(1);
			tbStatRecordLogEntity.setI_OBJ_TYPE(tbStatSqlEntity.getI_OBJ_TYPE());
			tbStatRecordLogEntity.setI_REF_ID(tbStatSqlEntity.getI_ID());
			tbStatRecordLogEntity.setI_RUN_CNT(0);
			tbStatRecordLogEntity.setS_INST_CYCLE(getS_INST_CYCLE());
			tbStatRecordLogEntity.setI_STAT_TYPE(tbStatSqlEntity.getI_STAT_TYPE());
			tbStatRecordLogEntity.setS_OBJ_SQL(tbStatSqlEntity.getS_OBJ_SQL().replace("?", tbStatRecordLogEntity.getS_INST_CYCLE()));
			tbStatRecordLogEntity.setI_STAT_SOURCE(tbStatSqlEntity.getI_STAT_SOURCE());
			tbStatRecordLogService.save(tbStatRecordLogEntity);
		}
	}

	/***
	 * 周期实例
	 * @I_CYCLE 周期
	 */
	private String getS_INST_CYCLE(){
		    return  String.valueOf(DefaultStringUtil.getNowTime("yyyyMMdd"));
	}

}
