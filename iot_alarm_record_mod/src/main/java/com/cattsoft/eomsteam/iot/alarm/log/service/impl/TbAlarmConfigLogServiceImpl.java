package com.cattsoft.eomsteam.iot.alarm.log.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cattsoft.eomsteam.iot.alarm.log.dao.TbAlarmConfigLogDao;
import com.cattsoft.eomsteam.iot.alarm.log.entity.TbAlarmConfigLogEntity;
import com.cattsoft.eomsteam.iot.alarm.log.service.TbAlarmConfigLogService;
import com.cattsoft.eomsteam.uc.pub.db.DefaultStringUtil;

@Service("com.cattsoft.eomsteam.iot.alarm.log.service.TbAlarmConfigLogService") 
public class TbAlarmConfigLogServiceImpl implements TbAlarmConfigLogService {
	
	@Autowired
	private TbAlarmConfigLogDao tbAlarmConfigLogDao;

	@Override
	public void save(TbAlarmConfigLogEntity tbAlarmConfigLogEntity) {
		// TODO Auto-generated method stub
		boolean exists = this.exists(tbAlarmConfigLogEntity.getI_REF_ID(), tbAlarmConfigLogEntity.getS_INST_CYCLE());
		if(!exists){
			tbAlarmConfigLogDao.save(tbAlarmConfigLogEntity);
		}
	}

	@Override
	public List<TbAlarmConfigLogEntity> getAlarmConfigLogList() {
		// TODO Auto-generated method stub
		List<TbAlarmConfigLogEntity> configLogEntityList  = new ArrayList<TbAlarmConfigLogEntity>();
		List<Map<String,Object>> configLogList = tbAlarmConfigLogDao.getAlarmConfigLogList();
		for(Map<String,Object> configLog : configLogList){
			TbAlarmConfigLogEntity tbDelimitedConfigLogEntity  = new TbAlarmConfigLogEntity();
			tbDelimitedConfigLogEntity.setI_CREATE_TIME(Long.valueOf(String.valueOf(configLog.get("I_CREATE_TIME"))));
			tbDelimitedConfigLogEntity.setI_CYCLE(Integer.valueOf(String.valueOf(configLog.get("I_CYCLE"))));
			tbDelimitedConfigLogEntity.setI_FLAG(Integer.valueOf(String.valueOf(configLog.get("I_FLAG"))));
			tbDelimitedConfigLogEntity.setI_ID(Long.valueOf(String.valueOf(configLog.get("I_ID"))));
			tbDelimitedConfigLogEntity.setI_OBJ_TYPE(Integer.valueOf(String.valueOf(configLog.get("I_OBJ_TYPE"))));
			tbDelimitedConfigLogEntity.setI_REF_ID(Long.valueOf(String.valueOf(configLog.get("I_REF_ID"))));
			tbDelimitedConfigLogEntity.setI_RUN_CNT(Integer.valueOf(String.valueOf(configLog.get("I_RUN_CNT"))));
			tbDelimitedConfigLogEntity.setS_INST_CYCLE(String.valueOf(configLog.get("S_INST_CYCLE")));
			tbDelimitedConfigLogEntity.setI_PENDING_CNT(DefaultStringUtil.toLong(configLog.get("I_PENDING_CNT")));
			configLogEntityList.add(tbDelimitedConfigLogEntity);
		}
		return configLogEntityList;
	}

	private boolean exists(Long I_REF_ID, String S_INST_CYCLE) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = tbAlarmConfigLogDao.getAlarmConfigLogByRefId(I_REF_ID, S_INST_CYCLE);
		if(null == list || list.isEmpty()){
			return false;
		}
		return true;
	}

	@Override
	public void updateAlarmConfigLogById(Long I_ID) {
		// TODO Auto-generated method stub
		tbAlarmConfigLogDao.updateAlarmConfigLogById(I_ID);
	}

	@Override
	public void updateRunningAlarmConfigLogById(Long I_ID, Long I_PENDING_CNT, Long I_START_TIME, Long I_END_TIME) {
		// TODO Auto-generated method stub
		tbAlarmConfigLogDao.updateRunningAlarmConfigLogById(I_ID, I_PENDING_CNT, I_START_TIME, I_END_TIME);
	}

	@Override
	public void updateAlarmConfigLogEndFlagById(Long I_ID) {
		// TODO Auto-generated method stub
		tbAlarmConfigLogDao.updateAlarmConfigLogEndFlagById(I_ID);
	}

}
