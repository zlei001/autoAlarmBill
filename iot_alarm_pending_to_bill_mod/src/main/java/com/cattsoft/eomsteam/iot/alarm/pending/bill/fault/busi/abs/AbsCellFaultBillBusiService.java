package com.cattsoft.eomsteam.iot.alarm.pending.bill.fault.busi.abs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.cattsoft.eomsteam.iot.alarm.pending.bill.fault.entity.TbFaultBillEntity;
import com.cattsoft.eomsteam.iot.alarm.pending.bill.fault.service.TbFaultBillService;
import com.cattsoft.eomsteam.iot.alarm.pending.pending.entity.TbAlarmPendingEntity;

public abstract class AbsCellFaultBillBusiService extends AbsFaultBillBusiService {

	@Autowired
	private TbFaultBillService tbFaultBillService;
	
	@Override
	protected  List<TbFaultBillEntity> getZipFaultBillList(TbAlarmPendingEntity tbAlarmPendingEntity){
		List<TbFaultBillEntity> zipFaultBillList =  tbFaultBillService.getZipFaultBillByInst(tbAlarmPendingEntity.getS_INST_ID(), tbAlarmPendingEntity.getS_INST_NAME(), tbAlarmPendingEntity.getCITY_CODE(), tbAlarmPendingEntity.getCITY_NAME());
		return zipFaultBillList;
	}
}
