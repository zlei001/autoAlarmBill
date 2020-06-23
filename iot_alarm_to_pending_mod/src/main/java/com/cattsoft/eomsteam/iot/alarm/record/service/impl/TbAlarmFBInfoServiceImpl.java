package com.cattsoft.eomsteam.iot.alarm.record.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cattsoft.eomsteam.iot.alarm.record.dao.TbAlarmFBInfoDao;
import com.cattsoft.eomsteam.iot.alarm.record.entity.TbAlarmFBInfoEntity;
import com.cattsoft.eomsteam.iot.alarm.record.service.TbAlarmFBInfoService;


@Service("com.cattsoft.eomsteam.iot.alarm.record.service.TbAlarmFBInfoService") 
public class TbAlarmFBInfoServiceImpl implements TbAlarmFBInfoService {

	@Autowired
	private TbAlarmFBInfoDao tbAlarmFBInfoDao;
	
	@Override
	public void save(List<TbAlarmFBInfoEntity> tbAlarmFBInfoEntityList){
		tbAlarmFBInfoDao.save(tbAlarmFBInfoEntityList);
	}

	@Override
	public Long getID() {
		// TODO Auto-generated method stub
		return tbAlarmFBInfoDao.getID();
	}

}
