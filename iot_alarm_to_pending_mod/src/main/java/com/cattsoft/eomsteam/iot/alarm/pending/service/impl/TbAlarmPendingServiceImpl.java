package com.cattsoft.eomsteam.iot.alarm.pending.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cattsoft.eomsteam.iot.alarm.pending.dao.TbAlarmPendingDao;
import com.cattsoft.eomsteam.iot.alarm.pending.entity.TbAlarmPendingEntity;
import com.cattsoft.eomsteam.iot.alarm.pending.service.TbAlarmPendingService;
import com.cattsoft.eomsteam.uc.pub.db.DefaultStringUtil;

@Service("com.cattsoft.eomsteam.iot.alarm.pending.service.TbAlarmPendingService") 
public class TbAlarmPendingServiceImpl implements TbAlarmPendingService {
	
	@Autowired
	private TbAlarmPendingDao tbAlarmPendingDao;

	@Override
	public Long getID() {
		// TODO Auto-generated method stub
		return tbAlarmPendingDao.getID();
	}

	@Override
	public void save(List<TbAlarmPendingEntity> alarmPendingEntityList) {
		// TODO Auto-generated method stub
		tbAlarmPendingDao.save(alarmPendingEntityList);
	}

	@Override
	public Map<String, Object> getAlarmPendingDataByRefId(Long I_REF_ID, String S_DELIMITED_CYCLE) {
		// TODO Auto-generated method stub
		Map<String,Object> pendingData  = new HashMap<String,Object>();
		List<Map<String,Object>> pendingList =  tbAlarmPendingDao.getAlarmPendingListByRefId(I_REF_ID, S_DELIMITED_CYCLE);
		if(null != pendingList && pendingList.size() > 0){
			for(Map<String,Object> data : pendingList){
				pendingData.put(DefaultStringUtil.toString(data.get("S_INST_ID")),data.get("I_ID"));
			}
		}
		return pendingData;
	}

	@Override
	public void updateAlarmZipCntByPendingId(List<Map<String, Object>> setIdList) {
		// TODO Auto-generated method stub
		tbAlarmPendingDao.updateAlarmZipCntByPendingId(setIdList);
	}

	@Override
	public List<Map<String, Object>> getAlarmTimeOutPendingListByRefId(Long I_REF_ID) {
		// TODO Auto-generated method stub
		return tbAlarmPendingDao.getAlarmTimeOutPendingListByRefId(I_REF_ID);
	}

	@Override
	public void updateAlarmPendingClosedByPendingId(List<Map<String, Object>> setIdList) {
		// TODO Auto-generated method stub
		tbAlarmPendingDao.updateAlarmPendingClosedByPendingId(setIdList);
	}

}
