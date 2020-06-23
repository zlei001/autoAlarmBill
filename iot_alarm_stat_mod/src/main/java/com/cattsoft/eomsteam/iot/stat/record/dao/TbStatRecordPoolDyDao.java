package com.cattsoft.eomsteam.iot.stat.record.dao;

import java.util.List;
import java.util.Map;

import com.cattsoft.eomsteam.iot.stat.record.entity.TbStatRecordPoolDyEntity;

public interface TbStatRecordPoolDyDao {
	
	public Long getID();
	
	public List<Map<String, Object>> getTbStatRecordByKey(String STATIS_TIME) ;

	public void updateDelimitedStatRecordByKey(List<TbStatRecordPoolDyEntity> setIdList) ;
	
	public void updateAlarmStatRecordByKey(List<TbStatRecordPoolDyEntity> setIdList) ;
	
	public void updateAlarmPendingStatRecordByKey(List<TbStatRecordPoolDyEntity> setIdList) ;
	
	public void updateBillStatRecordByKey(List<TbStatRecordPoolDyEntity> setIdList) ;
	
	public void save(List<TbStatRecordPoolDyEntity> tbStatRecordEntityList);
	
	
	public void updateDelimitedEndStatRecordByKey(List<TbStatRecordPoolDyEntity> setIdList) ;
	
	public void updateAlarmStatEndRecordByKey(List<TbStatRecordPoolDyEntity> setIdList) ;
	
	public void updateBillStatEndRecordByKey(List<TbStatRecordPoolDyEntity> setIdList) ;
	
	public void updateAlarmPendingStatEndRecordByKey(List<TbStatRecordPoolDyEntity> setIdList) ;
}
