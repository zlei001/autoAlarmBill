package com.cattsoft.eomsteam.iot.alarm.pending.bill.fault.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cattsoft.eomsteam.iot.alarm.pending.bill.fault.dao.TbFaultBillRefAlarmDao;
import com.cattsoft.eomsteam.iot.alarm.pending.bill.fault.entity.TbFaultBillRefAlarmEntity;
import com.cattsoft.eomsteam.iot.alarm.pending.bill.fault.service.TbFaultBillRefAlarmService;
import com.cattsoft.eomsteam.uc.pub.db.DefaultStringUtil;

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

	@Override
	public Map<Long, Long> queryBillRefAlarmId(String ids) {
		// TODO Auto-generated method stub
		Map<Long, Long> billAlarmData = new HashMap<Long, Long>();
		List<Map<String,Object>> list = tbFaultBillRefAlarmDao.queryBillRefAlarmId(ids);
		for(Map<String,Object> d :list){
			Long key = DefaultStringUtil.toLong(d.get("I_ALARM_ID"));
			Long val = DefaultStringUtil.toLong(d.get("I_BILL_ID"));
			billAlarmData.put(key, val);
		}
		return billAlarmData;
	}

}
