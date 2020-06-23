package com.cattsoft.eomsteam.iot.alarm.pending.bill.fault.busi.abs;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.cattsoft.eomsteam.iot.alarm.pending.bill.fault.busi.impl.ZipCellPendingFaultBillBusiService;
import com.cattsoft.eomsteam.iot.alarm.pending.bill.fault.entity.TbFaultBillEntity;
import com.cattsoft.eomsteam.iot.alarm.pending.bill.fault.service.TbFaultBillService;
import com.cattsoft.eomsteam.iot.alarm.pending.pending.entity.TbAlarmPendingEntity;

public abstract class AbsNotCellFaultBillBusiService extends AbsFaultBillBusiService {


    /**
     * 日志对象
     */
    private static Logger logger = Logger.getLogger(ZipCellPendingFaultBillBusiService.class);
	
	@Autowired
	private TbFaultBillService tbFaultBillService;
	
	@Override
	protected  List<TbFaultBillEntity> getZipFaultBillList(TbAlarmPendingEntity tbAlarmPendingEntity){
		List<TbFaultBillEntity> zipFaultBillList = tbFaultBillService.getZipFaultBillByInst(tbAlarmPendingEntity.getS_INST_ID(), tbAlarmPendingEntity.getS_INST_NAME());
		return zipFaultBillList;
	}
	
}
