package com.cattsoft.eomsteam.iot.alarm.pending.bill.fault.dao;

import java.util.List;

import com.cattsoft.eomsteam.iot.alarm.pending.bill.fault.entity.TbFaultBillFBInfoEntity;


public interface TbFaultBillFBInfoDao {
	public Long getID();

	public void save(List<TbFaultBillFBInfoEntity> tbFaultBillFBInfoEntityList);
}
