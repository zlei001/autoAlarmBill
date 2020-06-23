package com.cattsoft.eomsteam.iot.stat.record.dao;

import java.util.List;
import java.util.Map;

import com.cattsoft.eomsteam.iot.stat.record.entity.TbStatRecordCellDyEntity;

public interface TbStatRecordCellDyDao {
	
	public Long getID();
	
	public List<Map<String, Object>> getTbStatRecordByKey(String STATIS_TIME) ;

	public void updateDelimitedStatRecordByKey(List<TbStatRecordCellDyEntity> setIdList) ;
	
	public void updateAlarmStatRecordByKey(List<TbStatRecordCellDyEntity> setIdList) ;
	
	public void updateAlarmPendingStatRecordByKey(List<TbStatRecordCellDyEntity> setIdList) ;
	
	public void updateBillStatRecordByKey(List<TbStatRecordCellDyEntity> setIdList) ;
	
	public void save(List<TbStatRecordCellDyEntity> tbStatRecordEntityList);
	
	
	public void updateDelimitedEndStatRecordByKey(List<TbStatRecordCellDyEntity> setIdList) ;
	
	public void updateAlarmStatEndRecordByKey(List<TbStatRecordCellDyEntity> setIdList) ;
	
	public void updateBillStatEndRecordByKey(List<TbStatRecordCellDyEntity> setIdList) ;
	
	public void updateAlarmPendingStatEndRecordByKey(List<TbStatRecordCellDyEntity> setIdList) ;
}
