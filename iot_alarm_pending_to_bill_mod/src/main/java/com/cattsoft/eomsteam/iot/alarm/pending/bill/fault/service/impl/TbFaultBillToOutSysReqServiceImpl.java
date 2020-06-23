package com.cattsoft.eomsteam.iot.alarm.pending.bill.fault.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cattsoft.eomsteam.iot.alarm.pending.bill.fault.dao.TbFaultBillToOutSysReqDao;
import com.cattsoft.eomsteam.iot.alarm.pending.bill.fault.entity.TbFaultBillToOutSysReqEntity;
import com.cattsoft.eomsteam.iot.alarm.pending.bill.fault.service.TbFaultBillToOutSysReqService;

@Service("com.cattsoft.eomsteam.iot.alarm.bill.service.TbFaultBillToOutSysReqService") 
public class TbFaultBillToOutSysReqServiceImpl implements TbFaultBillToOutSysReqService {
	
	@Autowired
	private TbFaultBillToOutSysReqDao tbFaultBillToOutSysReqDao;

	@Override
	public Long getID() {
		// TODO Auto-generated method stub
		return tbFaultBillToOutSysReqDao.getID();
	}

	@Override
	public void save(List<TbFaultBillToOutSysReqEntity> tbFaultBillToOutSysReqList) {
		// TODO Auto-generated method stub
		tbFaultBillToOutSysReqDao.save(tbFaultBillToOutSysReqList);
	}

}
