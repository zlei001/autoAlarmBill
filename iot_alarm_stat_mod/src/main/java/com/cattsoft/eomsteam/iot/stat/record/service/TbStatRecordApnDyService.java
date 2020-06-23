package com.cattsoft.eomsteam.iot.stat.record.service;

import java.util.List;
import java.util.Map;

import com.cattsoft.eomsteam.iot.stat.record.entity.TbStatRecordApnDyEntity;

public interface TbStatRecordApnDyService {
	
	public Long getID();

	public Map<String,Long> getTbStatRecordDataByKey(String STATIS_TIME,List<String> keyList);
	
	public void updateDelimitedStatRecordByKey(List<TbStatRecordApnDyEntity> setIdList) ;
	
	public void updateAlarmStatRecordByKey(List<TbStatRecordApnDyEntity> setIdList) ;
	
	public void updateBillStatRecordByKey(List<TbStatRecordApnDyEntity> setIdList) ;
	
	public void updateAlarmPendingStatRecordByKey(List<TbStatRecordApnDyEntity> setIdList) ;
	
	public void updateDelimitedEndStatRecordByKey(List<TbStatRecordApnDyEntity> setIdList) ;
	
	public void updateAlarmStatEndRecordByKey(List<TbStatRecordApnDyEntity> setIdList) ;
	
	public void updateBillStatEndRecordByKey(List<TbStatRecordApnDyEntity> setIdList) ;
	
	public void updateAlarmPendingStatEndRecordByKey(List<TbStatRecordApnDyEntity> setIdList) ;
	
	public String getKey(List<String> keyList , Map<String, Object> recordData);
	
	
	public void save(List<TbStatRecordApnDyEntity> tbStatRecordEntityList);
}
