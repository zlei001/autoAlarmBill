package com.cattsoft.eomsteam.iot.stat.record.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cattsoft.eomsteam.iot.stat.record.dao.TbStatRecordCellDyDao;
import com.cattsoft.eomsteam.iot.stat.record.entity.TbStatRecordCellDyEntity;
import com.cattsoft.eomsteam.iot.stat.record.service.TbStatRecordCellDyService;
import com.cattsoft.eomsteam.uc.pub.db.DefaultStringUtil;

@Service("com.cattsoft.eomsteam.iot.stat.record.service.TbStatRecordCellDyService") 
public class TbStatRecordCellDyServiceImpl implements TbStatRecordCellDyService {
	
	@Autowired
	private TbStatRecordCellDyDao tbStatRecordDao;
	
	@Override
	public Map<String,Long> getTbStatRecordDataByKey(String STATIS_TIME,List<String> keyList){
		Map<String,Long> data = new HashMap<String,Long>();
		List<Map<String, Object>> recordList = tbStatRecordDao.getTbStatRecordByKey(STATIS_TIME);
		
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
	public void updateDelimitedStatRecordByKey(List<TbStatRecordCellDyEntity> setIdList) {
		// TODO Auto-generated method stub
		tbStatRecordDao.updateDelimitedStatRecordByKey(setIdList);
	}

	@Override
	public void updateAlarmStatRecordByKey(List<TbStatRecordCellDyEntity> setIdList) {
		// TODO Auto-generated method stub
		tbStatRecordDao.updateAlarmStatRecordByKey(setIdList);
	}

	@Override
	public void updateBillStatRecordByKey(List<TbStatRecordCellDyEntity> setIdList) {
		// TODO Auto-generated method stub
		tbStatRecordDao.updateBillStatRecordByKey(setIdList);
	}

	@Override
	public void updateAlarmPendingStatRecordByKey(List<TbStatRecordCellDyEntity> setIdList) {
		// TODO Auto-generated method stub
		tbStatRecordDao.updateAlarmPendingStatRecordByKey(setIdList);
	}

	@Override
	public void save(List<TbStatRecordCellDyEntity> tbStatRecordEntityList) {
		// TODO Auto-generated method stub
		if(tbStatRecordEntityList.size() >0){
			tbStatRecordDao.save(tbStatRecordEntityList);
		}
	}

	@Override
	public void updateDelimitedEndStatRecordByKey(List<TbStatRecordCellDyEntity> setIdList) {
		// TODO Auto-generated method stub
		tbStatRecordDao.updateDelimitedEndStatRecordByKey(setIdList);
	}

	@Override
	public void updateAlarmStatEndRecordByKey(List<TbStatRecordCellDyEntity> setIdList) {
		// TODO Auto-generated method stub
		tbStatRecordDao.updateAlarmStatEndRecordByKey(setIdList);
	}

	@Override
	public void updateBillStatEndRecordByKey(List<TbStatRecordCellDyEntity> setIdList) {
		// TODO Auto-generated method stub
		tbStatRecordDao.updateBillStatEndRecordByKey(setIdList);
	}

	@Override
	public void updateAlarmPendingStatEndRecordByKey(List<TbStatRecordCellDyEntity> setIdList) {
		// TODO Auto-generated method stub
		tbStatRecordDao.updateAlarmPendingStatEndRecordByKey(setIdList);
	}

	@Override
	public Long getID() {
		// TODO Auto-generated method stub
		return tbStatRecordDao.getID();
	}
	
}
