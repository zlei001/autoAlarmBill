package com.cattsoft.eomsteam.iot.stat.record.dao;

import java.util.List;
import java.util.Map;

import com.cattsoft.eomsteam.iot.stat.record.entity.TbStatRecordApnDyEntity;

public interface TbStatRecordApnDyDao {
	
	public Long getID();
	
	public List<Map<String, Object>> getTbStatRecordByKey(String STATIS_TIME) ;

	public void updateDelimitedStatRecordByKey(List<TbStatRecordApnDyEntity> setIdList) ;
	
	public void updateAlarmStatRecordByKey(List<TbStatRecordApnDyEntity> setIdList) ;
	
	public void updateAlarmPendingStatRecordByKey(List<TbStatRecordApnDyEntity> setIdList) ;
	
	public void updateBillStatRecordByKey(List<TbStatRecordApnDyEntity> setIdList) ;
	
	public void save(List<TbStatRecordApnDyEntity> tbStatRecordEntityList);
	
	
	public void updateDelimitedEndStatRecordByKey(List<TbStatRecordApnDyEntity> setIdList) ;
	
	public void updateAlarmStatEndRecordByKey(List<TbStatRecordApnDyEntity> setIdList) ;
	
	public void updateBillStatEndRecordByKey(List<TbStatRecordApnDyEntity> setIdList) ;
	
	public void updateAlarmPendingStatEndRecordByKey(List<TbStatRecordApnDyEntity> setIdList) ;
}
