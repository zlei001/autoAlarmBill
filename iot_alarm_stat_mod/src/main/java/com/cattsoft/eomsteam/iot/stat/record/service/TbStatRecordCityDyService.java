package com.cattsoft.eomsteam.iot.stat.record.service;

import java.util.List;
import java.util.Map;

import com.cattsoft.eomsteam.iot.stat.record.entity.TbStatRecordCityDyEntity;

public interface TbStatRecordCityDyService {
	
	public Long getID();

	public Map<String,Long> getTbStatRecordDataByKey(String STATIS_TIME,List<String> keyList);
	
	public void updateDelimitedStatRecordByKey(List<TbStatRecordCityDyEntity> setIdList) ;
	
	public void updateAlarmStatRecordByKey(List<TbStatRecordCityDyEntity> setIdList) ;
	
	public void updateBillStatRecordByKey(List<TbStatRecordCityDyEntity> setIdList) ;
	
	public void updateAlarmPendingStatRecordByKey(List<TbStatRecordCityDyEntity> setIdList) ;
	
	public void updateDelimitedEndStatRecordByKey(List<TbStatRecordCityDyEntity> setIdList) ;
	
	public void updateAlarmStatEndRecordByKey(List<TbStatRecordCityDyEntity> setIdList) ;
	
	public void updateBillStatEndRecordByKey(List<TbStatRecordCityDyEntity> setIdList) ;
	
	public void updateAlarmPendingStatEndRecordByKey(List<TbStatRecordCityDyEntity> setIdList) ;
	
	public String getKey(List<String> keyList , Map<String, Object> recordData);
	
	
	public void save(List<TbStatRecordCityDyEntity> tbStatRecordEntityList);
}
