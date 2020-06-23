package com.cattsoft.eomsteam.iot.alarm.pending.bill.fault.service;

import java.util.List;

import com.cattsoft.eomsteam.iot.alarm.pending.bill.fault.entity.TbFaultBillFBInfoEntity;


public interface TbFaultBillFBInfoService {

	public Long getID();
	
	public void save(List<TbFaultBillFBInfoEntity> tbFaultBillFBInfoEntityList);
	
}