package com.cattsoft.eomsteam.iot.alarm.pending.bill.fault.dao;

import java.util.List;

import com.cattsoft.eomsteam.iot.alarm.pending.bill.fault.entity.TbFaultBillToOutSysReqEntity;

public interface TbFaultBillToOutSysReqDao {


	/***
	 * 主键
	 * @return
	 */
	public Long getID() ;

	/***
	 * 保存工单列表
	 * @param tbFaultBillToOutSysReqList
	 */
	public void save(List<TbFaultBillToOutSysReqEntity> tbFaultBillToOutSysReqList) ;
}
