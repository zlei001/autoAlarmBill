package com.cattsoft.eomsteam.iot.alarm.pending.bill.fault.service;

import java.util.List;

import com.cattsoft.eomsteam.iot.alarm.pending.bill.fault.entity.TbFaultBillEntity;

public interface TbFaultBillRefDeptService {
	
	public void save(List<TbFaultBillEntity> tbFaultBillEntityList);
}
