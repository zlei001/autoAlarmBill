package com.cattsoft.eomsteam.iot.alarm.record.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cattsoft.eomsteam.iot.alarm.record.dao.TbAlarmRecordDao;
import com.cattsoft.eomsteam.iot.alarm.record.entity.TbAlarmRecordEntity;
import com.cattsoft.eomsteam.iot.alarm.record.service.TbAlarmRecordService;

@Service("com.cattsoft.eomsteam.iot.alarm.record.service.TbAlarmRecordService") 
public class TbAlarmRecordServiceImpl implements TbAlarmRecordService {
	
	@Autowired
	private  TbAlarmRecordDao tbAlarmPendingDao;
	

	@Override
	public Long getAlarmPendingCount(String sql) {
		// TODO Auto-generated method stub
		Map<String,Object> countData = tbAlarmPendingDao.getAlarmPendingCount(sql);
		if(null != countData){
			return Long.valueOf(String.valueOf(countData.get("cnt")));
		}
		 return  0l ;
	}

	@Override
	public List<Map<String, Object>> getAlarmPendingList(String sql) {
		// TODO Auto-generated method stub
		return tbAlarmPendingDao.getAlarmPendingList(sql);
	}

	@Override
	public Map<String,Object> getAlarmPendingDataByRefId(Long I_REF_ID, String S_DELIMITED_CYCLE) {
		// TODO Auto-generated method stub
		Map<String,Object> pengdingData = new HashMap<String,Object>();
		List<Map<String, Object>> pengdingList  = tbAlarmPendingDao.getAlarmPendingListByRefId(I_REF_ID, S_DELIMITED_CYCLE);
		for(Map<String, Object> pengding : pengdingList){
			pengdingData.put(String.valueOf(pengding.get("S_INST_ID")), pengding.get("I_ID"));
		}
		return pengdingData;
	}

	@Override
	public Long getID() {
		// TODO Auto-generated method stub
		return tbAlarmPendingDao.getID();
	}

	@Override
	public void save(List<TbAlarmRecordEntity> delimitedPendingEntityList) {
		// TODO Auto-generated method stub
		tbAlarmPendingDao.save(delimitedPendingEntityList);
	}

	@Override
	public void updateAlarmConfigLogEndFlagById(String  idInStr) {
		// TODO Auto-generated method stub
		tbAlarmPendingDao.updateRunningAlarmPendingEndById(idInStr);
	}

	@Override
	public void updateAlarmConfigLogEndFlagByIds( List<String>  idInStr) {
		// TODO Auto-generated method stub
		tbAlarmPendingDao.updateRunningAlarmPendingEndByIds( idInStr);
	}
	@Override
	public String getNo() {
		// TODO Auto-generated method stub
		return String.valueOf(tbAlarmPendingDao.getID());
	}

}
