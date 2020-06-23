package com.cattsoft.eomsteam.iot.stat.record.dao;

import java.util.List;
import java.util.Map;

import com.cattsoft.eomsteam.iot.stat.record.entity.TbStatRecordCityDyEntity;

public interface TbStatRecordCityDyDao {
	
	public Long getID();
	
	public List<Map<String, Object>> getTbStatRecordByKey(String STATIS_TIME) ;

	public void updateDelimitedStatRecordByKey(List<TbStatRecordCityDyEntity> setIdList) ;
	
	public void updateAlarmStatRecordByKey(List<TbStatRecordCityDyEntity> setIdList) ;
	
	public void updateAlarmPendingStatRecordByKey(List<TbStatRecordCityDyEntity> setIdList) ;
	
	public void updateBillStatRecordByKey(List<TbStatRecordCityDyEntity> setIdList) ;
	
	public void save(List<TbStatRecordCityDyEntity> tbStatRecordEntityList);
	
	
	public void updateDelimitedEndStatRecordByKey(List<TbStatRecordCityDyEntity> setIdList) ;
	
	public void updateAlarmStatEndRecordByKey(List<TbStatRecordCityDyEntity> setIdList) ;
	
	public void updateBillStatEndRecordByKey(List<TbStatRecordCityDyEntity> setIdList) ;
	
	public void updateAlarmPendingStatEndRecordByKey(List<TbStatRecordCityDyEntity> setIdList) ;
}
