package com.cattsoft.eomsteam.iot.alarm.pending.bill.fault.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cattsoft.eomsteam.iot.alarm.pending.bill.fault.dao.TbFaultBillFBInfoDao;
import com.cattsoft.eomsteam.iot.alarm.pending.bill.fault.entity.TbFaultBillFBInfoEntity;
import com.cattsoft.eomsteam.iot.alarm.pending.bill.fault.service.TbFaultBillFBInfoService;



@Service("com.cattsoft.eomsteam.iot.alarm.record.service.TbFaultBillFBInfoService") 
public class TbFaultBillFBInfoServiceImpl implements TbFaultBillFBInfoService {

	@Autowired
	private TbFaultBillFBInfoDao tbFaultBillFBInfoDao;
	
	@Override
	public void save(List<TbFaultBillFBInfoEntity> tbFaultBillFBInfoEntityList){
		tbFaultBillFBInfoDao.save(tbFaultBillFBInfoEntityList);
	}
	
	@Override
	public Long getID() {
		// TODO Auto-generated method stub
		return tbFaultBillFBInfoDao.getID();
	}
	
}
