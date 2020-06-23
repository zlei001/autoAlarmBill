package com.cattsoft.eomsteam.iot.alarm.record.service;

import java.util.List;

import com.cattsoft.eomsteam.iot.alarm.record.entity.TbAlarmFBInfoEntity;


public interface TbAlarmFBInfoService {
	
	public Long getID();

	public void save(List<TbAlarmFBInfoEntity> tbAlarmFBInfoEntityList);
}
