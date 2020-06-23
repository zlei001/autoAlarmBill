package com.cattsoft.eomsteam.iot.stat.record.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cattsoft.eomsteam.iot.stat.record.dao.TbStatRecordPoolDyDao;
import com.cattsoft.eomsteam.iot.stat.record.entity.TbStatRecordPoolDyEntity;
import com.cattsoft.eomsteam.iot.stat.record.service.TbStatRecordPoolDyService;
import com.cattsoft.eomsteam.uc.pub.db.DefaultStringUtil;

@Service("com.cattsoft.eomsteam.iot.stat.record.service.TbStatRecordPoolDyService") 
public class TbStatRecordPoolDyServiceImpl implements TbStatRecordPoolDyService {
	
	@Autowired
	private TbStatRecordPoolDyDao tbStatRecordPoolDyDao;
	
	@Override
	public Map<String,Long> getTbStatRecordDataByKey(String STATIS_TIME,List<String> keyList){
		Map<String,Long> data = new HashMap<String,Long>();
		List<Map<String, Object>> recordList = tbStatRecordPoolDyDao.getTbStatRecordByKey(STATIS_TIME);
		
		for(Map<String, Object> recordData : recordList){
			String key = this.getKey(keyList, recordData);
			data.put(key, DefaultStringUtil.toLong(recordData.get("I_ID")));
		}
		return data;
	}
	
	@Override
	public String getKey(List<String> keyList , Map<String, Object> recordData){
		StringBuffer key = new StringBuffer();
		for(String keyStr :keyList){
			if(key.length() > 0){
				key.append("_");
			}
			key.append(recordData.get(keyStr));
		}
		return key.toString();
	}

	@Override
	public void updateDelimitedStatRecordByKey(List<TbStatRecordPoolDyEntity> setIdList) {
		// TODO Auto-generated method stub
		tbStatRecordPoolDyDao.updateDelimitedStatRecordByKey(setIdList);
	}

	@Override
	public void updateAlarmStatRecordByKey(List<TbStatRecordPoolDyEntity> setIdList) {
		// TODO Auto-generated method stub
		tbStatRecordPoolDyDao.updateAlarmStatRecordByKey(setIdList);
	}

	@Override
	public void updateBillStatRecordByKey(List<TbStatRecordPoolDyEntity> setIdList) {
		// TODO Auto-generated method stub
		tbStatRecordPoolDyDao.updateBillStatRecordByKey(setIdList);
	}

	@Override
	public void updateAlarmPendingStatRecordByKey(List<TbStatRecordPoolDyEntity> setIdList) {
		// TODO Auto-generated method stub
		tbStatRecordPoolDyDao.updateAlarmPendingStatRecordByKey(setIdList);
	}

	@Override
	public void save(List<TbStatRecordPoolDyEntity> tbStatRecordEntityList) {
		// TODO Auto-generated method stub
		if(tbStatRecordEntityList.size() >0){
			tbStatRecordPoolDyDao.save(tbStatRecordEntityList);
		}
	}

	@Override
	public void updateDelimitedEndStatRecordByKey(List<TbStatRecordPoolDyEntity> setIdList) {
		// TODO Auto-generated method stub
		tbStatRecordPoolDyDao.updateDelimitedEndStatRecordByKey(setIdList);
	}

	@Override
	public void updateAlarmStatEndRecordByKey(List<TbStatRecordPoolDyEntity> setIdList) {
		// TODO Auto-generated method stub
		tbStatRecordPoolDyDao.updateAlarmStatEndRecordByKey(setIdList);
	}

	@Override
	public void updateBillStatEndRecordByKey(List<TbStatRecordPoolDyEntity> setIdList) {
		// TODO Auto-generated method stub
		tbStatRecordPoolDyDao.updateBillStatEndRecordByKey(setIdList);
	}

	@Override
	public void updateAlarmPendingStatEndRecordByKey(List<TbStatRecordPoolDyEntity> setIdList) {
		// TODO Auto-generated method stub
		tbStatRecordPoolDyDao.updateAlarmPendingStatEndRecordByKey(setIdList);
	}

	@Override
	public Long getID() {
		// TODO Auto-generated method stub
		return tbStatRecordPoolDyDao.getID();
	}
	
}
