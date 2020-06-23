package com.cattsoft.eomsteam.iot.alarm.pending.busi.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cattsoft.eomsteam.iot.alarm.pending.bill.fault.entity.TbFaultBillFBInfoEntity;
import com.cattsoft.eomsteam.iot.alarm.pending.bill.fault.entity.TbFaultBillToOutSysReqEntity;
import com.cattsoft.eomsteam.iot.alarm.pending.bill.fault.service.TbFaultBillFBInfoService;
import com.cattsoft.eomsteam.iot.alarm.pending.bill.fault.service.TbFaultBillRefAlarmService;
import com.cattsoft.eomsteam.iot.alarm.pending.bill.fault.service.TbFaultBillToOutSysReqService;
import com.cattsoft.eomsteam.iot.alarm.pending.busi.abs.AbsAlarmPendingBusiService;
import com.cattsoft.eomsteam.iot.alarm.pending.entity.TbAlarmPendingEntity;
import com.cattsoft.eomsteam.iot.alarm.pending.service.TbAlarmPendingService;
import com.cattsoft.eomsteam.iot.alarm.record.entity.TbAlarmFBInfoEntity;
import com.cattsoft.eomsteam.iot.alarm.record.entity.TbAlarmRecordEntity;
import com.cattsoft.eomsteam.iot.alarm.record.service.TbAlarmFBInfoService;
import com.cattsoft.eomsteam.iot.alarm.record.service.TbAlarmRecordService;
import com.cattsoft.eomsteam.uc.pub.db.DefaultStringUtil;

/***
 * 性能告警
 * @author Administrator
 *
 */
@Service("com.cattsoft.eomsteam.iot.alarm.pending.busi.1") 
public class AlarmToPendingBusiService extends AbsAlarmPendingBusiService {

	@Autowired
	private TbAlarmPendingService tbAlarmPendingService;
	
	@Autowired
	private TbAlarmRecordService tbAlarmRecordService;
	
	@Autowired
	private TbAlarmFBInfoService tbAlarmFBInfoService;
	
	@Autowired
	private TbFaultBillRefAlarmService tbFaultBillRefAlarmService;
	
	@Autowired
	private TbFaultBillFBInfoService tbFaultBillFBInfoService;
	
	@Autowired
	private TbFaultBillToOutSysReqService tbFaultBillToOutSysReqService;

	@Override
	public void doAlarmRecordToPending(List<TbAlarmRecordEntity> recordList,Map<String,Object> pendingData) {
		List<TbAlarmPendingEntity> recordPendingList = new ArrayList<TbAlarmPendingEntity>();
		List<Map<String,Object>> zipRecordPendingList = new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> zipUpdatePendingList = new ArrayList<Map<String,Object>>();
		
		List<Map<String,Object>> zipFbRecordPendingList = new ArrayList<Map<String,Object>>();
		// TODO Auto-generated method stub
		for(TbAlarmRecordEntity recordEntity :recordList){
			Map<String,Object> zipData = new HashMap<String,Object>();
			if(!pendingData.containsKey(recordEntity.getS_INST_ID())){
				TbAlarmPendingEntity tbAlarmPendingEntity = new TbAlarmPendingEntity();
				tbAlarmPendingEntity.setCITY_CODE(recordEntity.getCITY_CODE());
				tbAlarmPendingEntity.setCITY_NAME(recordEntity.getCITY_NAME());
				tbAlarmPendingEntity.setI_CANCEL_FLAG(1);
				tbAlarmPendingEntity.setI_CREATE_TIME(recordEntity.getI_CREATE_TIME());
				tbAlarmPendingEntity.setI_CYCLE(recordEntity.getI_CYCLE());
				tbAlarmPendingEntity.setI_DISPATCH_FLAG(1);
				tbAlarmPendingEntity.setI_FLAG(recordEntity.getI_FLAG());
				tbAlarmPendingEntity.setI_ID(tbAlarmPendingService.getID());
				tbAlarmPendingEntity.setI_LARGE_CLASS(recordEntity.getI_LARGE_CLASS());
				tbAlarmPendingEntity.setI_NE_MODEL(recordEntity.getI_NE_MODEL());
				tbAlarmPendingEntity.setI_OBJ_ID(recordEntity.getI_OBJ_ID());
				tbAlarmPendingEntity.setI_OBJ_LEVEL(recordEntity.getI_OBJ_LEVEL());
				tbAlarmPendingEntity.setI_OBJ_TYPE(recordEntity.getI_OBJ_TYPE());
				tbAlarmPendingEntity.setI_REF_ID(recordEntity.getI_REF_ID());
				tbAlarmPendingEntity.setI_RESET_FLAG(1);
				tbAlarmPendingEntity.setI_SMALL_CLASS(recordEntity.getI_SMALL_CLASS());
				tbAlarmPendingEntity.setI_UPDATE_TIME(recordEntity.getI_UPDATE_TIME());
				tbAlarmPendingEntity.setI_ZIP_CNT(0);
				tbAlarmPendingEntity.setS_DELIMITED_CYCLE(recordEntity.getS_DELIMITED_CYCLE());
				tbAlarmPendingEntity.setS_INST_ID(recordEntity.getS_INST_ID());
				tbAlarmPendingEntity.setS_INST_NAME(recordEntity.getS_INST_NAME());
				tbAlarmPendingEntity.setS_MSG(recordEntity.getS_MSG());
				tbAlarmPendingEntity.setS_NO(recordEntity.getS_NO());
				tbAlarmPendingEntity.setS_TITLE(recordEntity.getS_TITLE());
				tbAlarmPendingEntity.setI_OBJ_SOURCE(1);
				tbAlarmPendingEntity.setI_OBJ_ID(0);
				recordPendingList.add(tbAlarmPendingEntity);
				
				zipData.put("I_ALARM_PENDING_ID", tbAlarmPendingEntity.getI_ID());
				zipData.put("I_ID", recordEntity.getI_ID());
			}else{
				zipData.put("I_ALARM_PENDING_ID", pendingData.get(recordEntity.getS_INST_ID()));
				zipData.put("I_ID", recordEntity.getI_ID());
				zipData.put("S_MSG", recordEntity.getS_MSG());
				zipData.put("CITY_CODE", recordEntity.getCITY_CODE());
				zipData.put("CITY_NAME", recordEntity.getCITY_NAME());
				zipFbRecordPendingList.add(zipData);
				
				Map<String,Object> zipPendingData = new HashMap<String,Object>();
				zipPendingData.put("I_CREATE_TIME", pendingData.get(recordEntity.getI_CREATE_TIME()));
				zipPendingData.put("I_ID", recordEntity.getI_ID());
				zipUpdatePendingList.add(zipPendingData);
			}
			zipRecordPendingList.add(zipData);
		}
		tbAlarmRecordService.updateAlarmRecordSetPendingIdByRecordId(zipRecordPendingList);
		tbAlarmPendingService.save(recordPendingList);
		tbAlarmPendingService.updateAlarmZipCntByPendingId(zipUpdatePendingList);
		
		String ids = this.getIds(zipFbRecordPendingList);
		Map<Long,Long> billAlarmData = tbFaultBillRefAlarmService.queryBillRefAlarmId(ids);
		this.save(zipFbRecordPendingList, billAlarmData);
	}
	
	/***
	 * 
	 * @param zipRecordPendingList
	 * @return
	 */
	private String getIds(List<Map<String,Object>> zipRecordPendingList){
		StringBuffer ids  = new StringBuffer();
		for(Map<String,Object> d : zipRecordPendingList){
			if(ids.length() > 0){
			   ids.append(",");
			}
			ids.append(d.get("I_ALARM_PENDING_ID"));
		}
		return ids.toString();
	}
	
	/***
	 * 保存压缩告警相关数据
	 * @param zipRecordPendingList
	 * @param billAlarmData
	 */
	private void save(List<Map<String,Object>> zipRecordPendingList,Map<Long,Long> billAlarmData){
		
		List<TbAlarmFBInfoEntity> tbAlarmFBInfoEntityList = new ArrayList<TbAlarmFBInfoEntity>();
		for(Map<String,Object> zipRecordData : zipRecordPendingList){
			TbAlarmFBInfoEntity tbAlarmFBInfoEntity = new TbAlarmFBInfoEntity();
			tbAlarmFBInfoEntity.setI_ID(tbAlarmFBInfoService.getID());
			tbAlarmFBInfoEntity.setCITY_CODE(DefaultStringUtil.toString(zipRecordData.get("CITY_CODE")));
			tbAlarmFBInfoEntity.setCITY_NAME(DefaultStringUtil.toString(zipRecordData.get("CITY_NAME")));
			tbAlarmFBInfoEntity.setI_CREATE_TIME(DefaultStringUtil.getNowTime("yyyyMMddHHmmss"));
			tbAlarmFBInfoEntity.setI_FLAG(1);
			tbAlarmFBInfoEntity.setI_REF_ID(DefaultStringUtil.toLong(zipRecordData.get("I_ALARM_PENDING_ID")));
			tbAlarmFBInfoEntity.setI_REF_TYPE(4);
			tbAlarmFBInfoEntity.setI_STAFF_ID(0L);
			tbAlarmFBInfoEntity.setS_MSG("追加告警:"+DefaultStringUtil.toString(zipRecordData.get("S_MSG")));
			tbAlarmFBInfoEntity.setS_STAFF_NAME("后台自动");
			tbAlarmFBInfoEntityList.add(tbAlarmFBInfoEntity);
		}
		tbAlarmFBInfoService.save(tbAlarmFBInfoEntityList);
		

		List<TbFaultBillFBInfoEntity> tbFaultBillFBInfoEntityList = new ArrayList<TbFaultBillFBInfoEntity>();
		for(Map<String,Object> zipRecordData : zipRecordPendingList){
			Long I_ALARM_PENDING_ID = DefaultStringUtil.toLong(zipRecordData.get("I_ALARM_PENDING_ID"));
			if(billAlarmData.containsKey(I_ALARM_PENDING_ID)){
				TbFaultBillFBInfoEntity tbFaultBillFBInfoEntity = new TbFaultBillFBInfoEntity();
				tbFaultBillFBInfoEntity.setI_ID(tbFaultBillFBInfoService.getID());
				tbFaultBillFBInfoEntity.setCITY_CODE(DefaultStringUtil.toString(zipRecordData.get("CITY_CODE")));
				tbFaultBillFBInfoEntity.setCITY_NAME(DefaultStringUtil.toString(zipRecordData.get("CITY_NAME")));
				tbFaultBillFBInfoEntity.setI_CREATE_TIME(DefaultStringUtil.getNowTime("yyyyMMddHHmmss"));
				tbFaultBillFBInfoEntity.setI_FLAG(1);
				tbFaultBillFBInfoEntity.setI_REF_ID(billAlarmData.get(I_ALARM_PENDING_ID));
				tbFaultBillFBInfoEntity.setI_REF_TYPE(4);
				tbFaultBillFBInfoEntity.setI_STAFF_ID(0L);
				tbFaultBillFBInfoEntity.setS_MSG("追加告警:"+DefaultStringUtil.toString(zipRecordData.get("S_MSG")));
				tbFaultBillFBInfoEntity.setS_STAFF_NAME("后台");
				tbFaultBillFBInfoEntityList.add(tbFaultBillFBInfoEntity);
			}
		}
		tbFaultBillFBInfoService.save(tbFaultBillFBInfoEntityList);
		
		List<TbFaultBillToOutSysReqEntity> tbFaultBillToOutSysReqList = new ArrayList<TbFaultBillToOutSysReqEntity>();
		for(TbFaultBillFBInfoEntity tbFaultBillFBInfoEntity : tbFaultBillFBInfoEntityList){
			TbFaultBillToOutSysReqEntity tbFaultBillToOutSysReqEntity  = new TbFaultBillToOutSysReqEntity();
			tbFaultBillToOutSysReqEntity.setI_BILL_ID(tbFaultBillFBInfoEntity.getI_REF_ID());
			tbFaultBillToOutSysReqEntity.setI_CREATE_TIME(DefaultStringUtil.getNowTime("yyyyMMddHHmmss"));
			tbFaultBillToOutSysReqEntity.setI_ID(tbFaultBillToOutSysReqService.getID());
			tbFaultBillToOutSysReqEntity.setI_REF_ID(tbFaultBillFBInfoEntity.getI_ID());
			tbFaultBillToOutSysReqEntity.setI_REF_TYPE(2);
			tbFaultBillToOutSysReqEntity.setI_TRY_CNT(0);
			tbFaultBillToOutSysReqEntity.setI_FLAG(1);
			tbFaultBillToOutSysReqList.add(tbFaultBillToOutSysReqEntity);
		}
		
		tbFaultBillToOutSysReqService.save(tbFaultBillToOutSysReqList);
	}
}
