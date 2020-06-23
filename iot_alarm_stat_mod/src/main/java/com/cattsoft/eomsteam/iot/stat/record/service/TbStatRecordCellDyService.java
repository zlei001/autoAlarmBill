package com.cattsoft.eomsteam.iot.stat.record.service;

import java.util.List;
import java.util.Map;

import com.cattsoft.eomsteam.iot.stat.record.entity.TbStatRecordCellDyEntity;

public interface TbStatRecordCellDyService {
	
	public Long getID();

	public Map<String,Long> getTbStatRecordDataByKey(String STATIS_TIME,List<String> keyList);
	
	public void updateDelimitedStatRecordByKey(List<TbStatRecordCellDyEntity> setIdList) ;
	
	public void updateAlarmStatRecordByKey(List<TbStatRecordCellDyEntity> setIdList) ;
	
	public void updateBillStatRecordByKey(List<TbStatRecordCellDyEntity> setIdList) ;
	
	public void updateAlarmPendingStatRecordByKey(List<TbStatRecordCellDyEntity> setIdList) ;
	
	public void updateDelimitedEndStatRecordByKey(List<TbStatRecordCellDyEntity> setIdList) ;
	
	public void updateAlarmStatEndRecordByKey(List<TbStatRecordCellDyEntity> setIdList) ;
	
	public void updateBillStatEndRecordByKey(List<TbStatRecordCellDyEntity> setIdList) ;
	
	public void updateAlarmPendingStatEndRecordByKey(List<TbStatRecordCellDyEntity> setIdList) ;
	
	public String getKey(List<String> keyList , Map<String, Object> recordData);
	
	
	public void save(List<TbStatRecordCellDyEntity> tbStatRecordEntityList);
}
