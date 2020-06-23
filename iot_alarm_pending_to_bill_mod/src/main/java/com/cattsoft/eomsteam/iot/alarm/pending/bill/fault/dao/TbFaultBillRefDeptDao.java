package com.cattsoft.eomsteam.iot.alarm.pending.bill.fault.dao;

import java.util.List;

import com.cattsoft.eomsteam.iot.alarm.pending.bill.fault.entity.TbFaultBillRefDeptEntity;


public interface TbFaultBillRefDeptDao {

	public Long getID();

	public void save(List<TbFaultBillRefDeptEntity> tbFaultBillToOutSysReqList) ;
}
