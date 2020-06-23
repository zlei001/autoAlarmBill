package com.cattsoft.eomsteam.iot.alarm.record.dao;

import java.util.List;

import com.cattsoft.eomsteam.iot.alarm.record.entity.TbAlarmFBInfoEntity;


public interface TbAlarmFBInfoDao {
	
	public Long getID();

	public void save(List<TbAlarmFBInfoEntity> tbAlarmFBInfoEntityList);
}
