package com.cattsoft.eomsteam.iot.alarm.pending.bill.fault.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cattsoft.eomsteam.iot.alarm.pending.bill.fault.dao.TbFaultBillRefAlarmDao;
import com.cattsoft.eomsteam.iot.alarm.pending.bill.fault.entity.TbFaultBillRefAlarmEntity;
import com.cattsoft.eomsteam.iot.alarm.pending.bill.fault.service.TbFaultBillRefAlarmService;

@Service("com.cattsoft.eomsteam.iot.alarm.pending.bill.fault.service.TbFaultBillRefAlarmService") 
public class TbFaultBillRefAlarmServiceImpl implements TbFaultBillRefAlarmService {
	
	@Autowired
	private TbFaultBillRefAlarmDao tbFaultBillRefAlarmDao;

	@Override
	public Long getID() {
		// TODO Auto-generated method stub
		return tbFaultBillRefAlarmDao.getID();
	}

	@Override
	public void save(List<TbFaultBillRefAlarmEntity> tbFaultBillRefAlarmList) {
		// TODO Auto-generated method stub
		tbFaultBillRefAlarmDao.save(tbFaultBillRefAlarmList);
	}

}
