package com.cattsoft.eomsteam.iot.alarm.pending.bill.exec.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cattsoft.eomsteam.iot.alarm.pending.bill.exec.AlarmPendingToFaultBillRunningService;
import com.cattsoft.eomsteam.iot.alarm.pending.bill.fault.busi.impl.OneLevelCellPendingFaultBillBusiService;
import com.cattsoft.eomsteam.iot.alarm.pending.bill.fault.busi.impl.OneLevelNotCellPendingFaultBillBusiService;
import com.cattsoft.eomsteam.iot.alarm.pending.bill.fault.busi.impl.TwoLevelCellPendingFaultBillBusiService;
import com.cattsoft.eomsteam.iot.alarm.pending.bill.fault.busi.impl.TwoLevelNotCellPendingFaultBillBusiService;
import com.cattsoft.eomsteam.iot.alarm.pending.bill.fault.busi.impl.ZipCellPendingFaultBillBusiService;
import com.cattsoft.eomsteam.iot.alarm.pending.bill.fault.busi.impl.ZipNotCellPendingFaultBillBusiService;

@Service("com.cattsoft.eomsteam.iot.alarm.pending.bill.exec.AlarmPendingToFaultBillRunningService") 
public class AlarmPendingToFaultBillRunningServiceImpl implements AlarmPendingToFaultBillRunningService {

	@Autowired
	private ZipNotCellPendingFaultBillBusiService zipNotCellPendingFaultBillBusiService;

	@Autowired
	private ZipCellPendingFaultBillBusiService zipCellPendingFaultBillBusiService;
	
	@Autowired
	private OneLevelCellPendingFaultBillBusiService oneLevelCellPendingFaultBillBusiService;
	
	@Autowired
	private OneLevelNotCellPendingFaultBillBusiService oneLevelNotCellPendingFaultBillBusiService;
	
	@Autowired
	private TwoLevelCellPendingFaultBillBusiService twoLevelCellPendingFaultBillBusiService;
	
	@Autowired
	private TwoLevelNotCellPendingFaultBillBusiService twoLevelNotCellPendingFaultBillBusiService;
	
	@Override
	public void doPending() {
		// TODO Auto-generated method stub
		zipNotCellPendingFaultBillBusiService.doBusi();
		zipCellPendingFaultBillBusiService.doBusi();
		oneLevelCellPendingFaultBillBusiService.doBusi();
		oneLevelNotCellPendingFaultBillBusiService.doBusi();
		twoLevelCellPendingFaultBillBusiService.doBusi();
		twoLevelNotCellPendingFaultBillBusiService.doBusi();
	}

}
