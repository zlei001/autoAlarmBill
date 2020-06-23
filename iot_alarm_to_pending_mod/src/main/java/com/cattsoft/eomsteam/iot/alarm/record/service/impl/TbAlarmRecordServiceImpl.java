package com.cattsoft.eomsteam.iot.alarm.record.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cattsoft.eomsteam.iot.alarm.record.dao.TbAlarmRecordDao;
import com.cattsoft.eomsteam.iot.alarm.record.entity.TbAlarmRecordEntity;
import com.cattsoft.eomsteam.iot.alarm.record.service.TbAlarmRecordService;
import com.cattsoft.eomsteam.uc.pub.db.DefaultStringUtil;

@Service("com.cattsoft.eomsteam.iot.alarm.record.service.TbAlarmRecordService") 
public class TbAlarmRecordServiceImpl implements TbAlarmRecordService {
	
	@Autowired
	private  TbAlarmRecordDao tbAlarmPendingDao;

	@Override
	public List<TbAlarmRecordEntity> getAlarmRecordListByRefId(Long I_REF_ID, String S_DELIMITED_CYCLE) {
		// TODO Auto-generated method stub
		List<TbAlarmRecordEntity>  recordList = new ArrayList<TbAlarmRecordEntity>();
		List<Map<String, Object>> alarmRecordList =  tbAlarmPendingDao.getAlarmRecordListByRefId(I_REF_ID, S_DELIMITED_CYCLE);
		for(Map<String, Object> recordData : alarmRecordList){
			TbAlarmRecordEntity tbAlarmRecordEntity = new TbAlarmRecordEntity();
			tbAlarmRecordEntity.setCITY_CODE(DefaultStringUtil.toString(recordData.get("CITY_CODE")));
			tbAlarmRecordEntity.setCITY_NAME(DefaultStringUtil.toString(recordData.get("CITY_NAME")));
			tbAlarmRecordEntity.setI_CREATE_TIME(DefaultStringUtil.toLong(recordData.get("I_CREATE_TIME")));
			tbAlarmRecordEntity.setI_CYCLE(DefaultStringUtil.toInteger(recordData.get("I_CYCLE")));
			tbAlarmRecordEntity.setI_FLAG(DefaultStringUtil.toInteger(recordData.get("I_FLAG")));
			tbAlarmRecordEntity.setI_ID(DefaultStringUtil.toLong(recordData.get("I_ID")));
			tbAlarmRecordEntity.setI_OBJ_TYPE(DefaultStringUtil.toInteger(recordData.get("I_OBJ_TYPE")));
			tbAlarmRecordEntity.setI_REF_ID(DefaultStringUtil.toLong(recordData.get("I_REF_ID")));
			tbAlarmRecordEntity.setI_UPDATE_TIME(DefaultStringUtil.toLong(recordData.get("I_UPDATE_TIME")));
			tbAlarmRecordEntity.setS_DELIMITED_CYCLE(DefaultStringUtil.toString(recordData.get("S_DELIMITED_CYCLE")));
			
			tbAlarmRecordEntity.setI_NE_MODEL(DefaultStringUtil.toInteger(recordData.get("I_NE_MODEL")));
			tbAlarmRecordEntity.setI_LARGE_CLASS(DefaultStringUtil.toInteger(recordData.get("I_LARGE_CLASS")));
			tbAlarmRecordEntity.setI_SMALL_CLASS(DefaultStringUtil.toInteger(recordData.get("I_SMALL_CLASS")));
			tbAlarmRecordEntity.setI_OBJ_SOURCE(DefaultStringUtil.toInteger(recordData.get("I_OBJ_SOURCE")));
			tbAlarmRecordEntity.setI_OBJ_LEVEL(DefaultStringUtil.toInteger(recordData.get("I_OBJ_LEVEL")));
			tbAlarmRecordEntity.setS_NO(DefaultStringUtil.toString(recordData.get("S_NO")));
			
			tbAlarmRecordEntity.setS_INST_ID(DefaultStringUtil.toString(recordData.get("S_INST_ID")));
			tbAlarmRecordEntity.setS_INST_NAME(DefaultStringUtil.toString(recordData.get("S_INST_NAME")));
			
			tbAlarmRecordEntity.setS_TITLE(DefaultStringUtil.toString(recordData.get("S_TITLE")));
			tbAlarmRecordEntity.setS_MSG(DefaultStringUtil.toString(recordData.get("S_MSG")));
			
			recordList.add(tbAlarmRecordEntity);
		}
		return recordList;
	}

	@Override
	public void updateAlarmRecordSetPendingIdByRecordId(List<Map<String, Object>> setIdList) {
		// TODO Auto-generated method stub
		tbAlarmPendingDao.updateAlarmRecordSetPendingIdByRecordId(setIdList);
	}

	@Override
	public void closedAlarmRecordByPendingId(List<Map<String, Object>> timeOutPendingList) {
		// TODO Auto-generated method stub
		tbAlarmPendingDao.updateAlarmRecordSetPendingIdByRecordId(timeOutPendingList);
	}
	
}
