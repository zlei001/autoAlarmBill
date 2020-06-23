package com.cattsoft.eomsteam.iot.stat.record.service;

import java.util.List;
import java.util.Map;

import com.cattsoft.eomsteam.iot.stat.record.entity.TbStatRecordPoolDyEntity;

public interface TbStatRecordPoolDyService {
	
	public Long getID();

	public Map<String,Long> getTbStatRecordDataByKey(String STATIS_TIME,List<String> keyList);
	
	public void updateDelimitedStatRecordByKey(List<TbStatRecordPoolDyEntity> setIdList) ;
	
	public void updateAlarmStatRecordByKey(List<TbStatRecordPoolDyEntity> setIdList) ;
	
	public void updateBillStatRecordByKey(List<TbStatRecordPoolDyEntity> setIdList) ;
	
	public void updateAlarmPendingStatRecordByKey(List<TbStatRecordPoolDyEntity> setIdList) ;
	
	public void updateDelimitedEndStatRecordByKey(List<TbStatRecordPoolDyEntity> setIdList) ;
	
	public void updateAlarmStatEndRecordByKey(List<TbStatRecordPoolDyEntity> setIdList) ;
	
	public void updateBillStatEndRecordByKey(List<TbStatRecordPoolDyEntity> setIdList) ;
	
	public void updateAlarmPendingStatEndRecordByKey(List<TbStatRecordPoolDyEntity> setIdList) ;
	
	public String getKey(List<String> keyList , Map<String, Object> recordData);
	
	
	public void save(List<TbStatRecordPoolDyEntity> tbStatRecordEntityList);
}
