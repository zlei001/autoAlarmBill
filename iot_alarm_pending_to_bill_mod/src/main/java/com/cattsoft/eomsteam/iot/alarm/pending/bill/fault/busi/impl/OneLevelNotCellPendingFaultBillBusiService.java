package com.cattsoft.eomsteam.iot.alarm.pending.bill.fault.busi.impl;


import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cattsoft.eomsteam.iot.alarm.pending.bill.fault.busi.abs.AbsNotCellFaultBillBusiService;
import com.cattsoft.eomsteam.iot.alarm.pending.pending.entity.TbAlarmPendingEntity;
import com.cattsoft.eomsteam.iot.alarm.pending.pending.service.TbAlarmPendingService;

/***
 * 一级警合并规则
 * @author Administrator
 *
 */
@Service("com.cattsoft.eomsteam.iot.alarm.pending.bill.fault.busi.OneLevelNotCellPendingFaultBillBusiService") 
public class OneLevelNotCellPendingFaultBillBusiService extends AbsNotCellFaultBillBusiService {
	
	 /**
     * 日志对象
     */
    private static Logger logger = Logger.getLogger(ZipNotCellPendingFaultBillBusiService.class);

	@Autowired
	private TbAlarmPendingService tbAlarmPendingService;
	
	@Override
	public void doBusi() {
		// TODO Auto-generated method stub
		List<TbAlarmPendingEntity> alarmPendingEntityList = tbAlarmPendingService.getAlarmPendingByNotCellOneLevel();
		logger.info("一级非小区告警:"+alarmPendingEntityList.size());
	    this.doBusiNoZipData(alarmPendingEntityList);
	}
	
	/***
	 * 返回是否压缩
	 * @return
	 */
	@Override
	protected  Integer  getI_ZIP_FLAG(){
		return 2;
	}
	
	
	/***
	 * 
	 * @param tbAlarmPendingEntity
	 * @return
	 */
	protected String getMsg(List<TbAlarmPendingEntity> alarmPendingEntityList){
		StringBuffer msg = new StringBuffer();
		for(TbAlarmPendingEntity tbAlarmPendingEntity : alarmPendingEntityList){
			msg.append(tbAlarmPendingEntity.getS_MSG());
			msg.append("<br/>");
		}
		
		return msg.toString();
	}
	

	/***
	 * 通用文字
	 * @return
	 */
	protected String getTitle(TbAlarmPendingEntity tbAlarmPendingEntity){
		return tbAlarmPendingEntity.getS_TITLE();
	}
}
