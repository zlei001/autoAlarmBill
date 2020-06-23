package com.cattsoft.eomsteam.iot.stat.record.busi.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cattsoft.eomsteam.iot.stat.log.entity.TbStatSqlLogEntity;
import com.cattsoft.eomsteam.iot.stat.record.busi.abs.AbsStatRecordBusiService;
import com.cattsoft.eomsteam.iot.stat.record.entity.TbStatRecordApnDyEntity;
import com.cattsoft.eomsteam.iot.stat.record.service.TbStatRecordApnDyService;
import com.cattsoft.eomsteam.uc.pub.db.DefaultStringUtil;

@Service("com.cattsoft.eomsteam.iot.stat.record.busi.5") 
public class APNStatRecordBusiServiceImpl extends AbsStatRecordBusiService {
	
	@Autowired
	private TbStatRecordApnDyService tbStatRecordApnDyService;

	@Override
	public void doBusi(TbStatSqlLogEntity tbStatRecordLogEntity ,List<Map<String,Object>> statResultList){
		List<TbStatRecordApnDyEntity> saveTbStatRecordApnDyEntityList = new ArrayList<TbStatRecordApnDyEntity>();
		List<TbStatRecordApnDyEntity> updateTbStatRecordApnDyEntityList = new ArrayList<TbStatRecordApnDyEntity>();
		List<String> keyList = this.getKeyList();
		Map<String,Long>  statRecordData = tbStatRecordApnDyService.getTbStatRecordDataByKey(tbStatRecordLogEntity.getS_INST_CYCLE(),keyList);
		
		for(Map<String,Object> statResultData : statResultList){
			String key = tbStatRecordApnDyService.getKey(keyList, statResultData);
			TbStatRecordApnDyEntity tbStatRecordCityDyEntity = new TbStatRecordApnDyEntity();
			tbStatRecordCityDyEntity.setApn(DefaultStringUtil.toString(statResultData.get("APN")));
			tbStatRecordCityDyEntity.setCustomer_name(DefaultStringUtil.toString(statResultData.get("CUSTOMER_NAME")));
			tbStatRecordCityDyEntity.setI_ID(DefaultStringUtil.toLong(statResultData.get("I_ID")));
			tbStatRecordCityDyEntity.setI_LARGE_CLASS(DefaultStringUtil.toInteger(statResultData.get("I_LARGE_CLASS")));
			tbStatRecordCityDyEntity.setI_NE_MODEL(DefaultStringUtil.toInteger(statResultData.get("I_NE_MODEL")));
			tbStatRecordCityDyEntity.setI_SMALL_CLASS(DefaultStringUtil.toInteger(statResultData.get("I_SMALL_CLASS")));
			tbStatRecordCityDyEntity.setSTATIS_TIME(DefaultStringUtil.toString(statResultData.get("STATIS_TIME")));
			
			if(statRecordData.containsKey(key)){
				//已存在,更新
				tbStatRecordCityDyEntity.setI_ID(DefaultStringUtil.toLong(statRecordData.get(key)));
				this.setTbStatRecordApnDyEntity(tbStatRecordCityDyEntity, tbStatRecordLogEntity, statResultData);
				updateTbStatRecordApnDyEntityList.add(tbStatRecordCityDyEntity);
			}else{
				//不存在,新增
				this.setTbStatRecordApnDyEntity(tbStatRecordCityDyEntity, tbStatRecordLogEntity, statResultData);
				tbStatRecordCityDyEntity.setI_ID(tbStatRecordApnDyService.getID());
				saveTbStatRecordApnDyEntityList.add(tbStatRecordCityDyEntity);
			}
		}
		tbStatRecordApnDyService.save(saveTbStatRecordApnDyEntityList);
		this.setUpdate(tbStatRecordLogEntity, updateTbStatRecordApnDyEntityList);
	}
	
	/***
	 * 设置字符串值
	 * @param tbStatRecordCityDyEntity
	 * @param tbStatRecordLogEntity
	 * @param statResultData
	 */
	private void setUpdate(TbStatSqlLogEntity tbStatRecordLogEntity ,List<TbStatRecordApnDyEntity> setIdList){
		if(1 == tbStatRecordLogEntity.getI_STAT_SOURCE()){
			if(1 == tbStatRecordLogEntity.getI_STAT_TYPE()){
				tbStatRecordApnDyService.updateDelimitedStatRecordByKey(setIdList);
			}else{
				tbStatRecordApnDyService.updateDelimitedEndStatRecordByKey(setIdList);
			}
		}else if(2 == tbStatRecordLogEntity.getI_STAT_SOURCE()){
			if(1 == tbStatRecordLogEntity.getI_STAT_TYPE()){
				tbStatRecordApnDyService.updateAlarmStatRecordByKey(setIdList);
			}else{
				tbStatRecordApnDyService.updateAlarmStatEndRecordByKey(setIdList);
			}
		}else if(3 == tbStatRecordLogEntity.getI_STAT_SOURCE()){
			if(1 == tbStatRecordLogEntity.getI_STAT_TYPE()){
				tbStatRecordApnDyService.updateAlarmPendingStatRecordByKey(setIdList);
			}else{
				tbStatRecordApnDyService.updateAlarmPendingStatEndRecordByKey(setIdList);
			}
		}else if(4 == tbStatRecordLogEntity.getI_STAT_SOURCE()){
			if(1 == tbStatRecordLogEntity.getI_STAT_TYPE()){
				tbStatRecordApnDyService.updateBillStatRecordByKey(setIdList);
			}else{
				tbStatRecordApnDyService.updateBillStatEndRecordByKey(setIdList);
			}
		}
	}
	
	
	/***
	 * 设置字符串值
	 * @param tbStatRecordCityDyEntity
	 * @param tbStatRecordLogEntity
	 * @param statResultData
	 */
	private void setTbStatRecordApnDyEntity(TbStatRecordApnDyEntity tbStatRecordCityDyEntity
											,TbStatSqlLogEntity tbStatRecordLogEntity 
											,Map<String,Object> statResultData){
		if(1 == tbStatRecordLogEntity.getI_STAT_SOURCE()){
			if(1 == tbStatRecordLogEntity.getI_STAT_TYPE()){
				tbStatRecordCityDyEntity.setI_DELIMITED_CNT(DefaultStringUtil.toInteger(statResultData.get("CNT")));
			}else{
				tbStatRecordCityDyEntity.setI_DELIMITED_END_CNT(DefaultStringUtil.toInteger(statResultData.get("CNT")));
			}
			tbStatRecordCityDyEntity.setI_DELIMITED_TIME(DefaultStringUtil.getNowTime("yyyyMMddHHmmss"));
		}else if(2 == tbStatRecordLogEntity.getI_STAT_SOURCE()){
			if(1 == tbStatRecordLogEntity.getI_STAT_TYPE()){
				tbStatRecordCityDyEntity.setI_ALARM_CNT(DefaultStringUtil.toInteger(statResultData.get("CNT")));
			}else{
				tbStatRecordCityDyEntity.setI_ALARM_END_CNT(DefaultStringUtil.toInteger(statResultData.get("CNT")));
			}
			tbStatRecordCityDyEntity.setI_ALARM_TIME(DefaultStringUtil.getNowTime("yyyyMMddHHmmss"));
		}else if(3 == tbStatRecordLogEntity.getI_STAT_SOURCE()){
			if(1 == tbStatRecordLogEntity.getI_STAT_TYPE()){
				tbStatRecordCityDyEntity.setI_ALARM_PENDING_CNT(DefaultStringUtil.toInteger(statResultData.get("CNT")));
			}else{
				tbStatRecordCityDyEntity.setI_ALARM_PENDING_END_CNT(DefaultStringUtil.toInteger(statResultData.get("CNT")));
			}
			tbStatRecordCityDyEntity.setI_ALARM_PENDING_TIME(DefaultStringUtil.getNowTime("yyyyMMddHHmmss"));
		}else if(4 == tbStatRecordLogEntity.getI_STAT_SOURCE()){
			if(1 == tbStatRecordLogEntity.getI_STAT_TYPE()){
				tbStatRecordCityDyEntity.setI_BILL_CNT(DefaultStringUtil.toInteger(statResultData.get("CNT")));
			}else{
				tbStatRecordCityDyEntity.setI_BILL_END_CNT(DefaultStringUtil.toInteger(statResultData.get("CNT")));
			}
			tbStatRecordCityDyEntity.setI_BILL_TIME(DefaultStringUtil.getNowTime("yyyyMMddHHmmss"));
		}
	}
	
	/***
	 * 列表
	 * @return
	 */
	protected List<String> getKeyList(){
		List<String> keyList = new ArrayList<String>();
		keyList.add("STATIS_TIME");
		keyList.add("APN");
		keyList.add("I_NE_MODEL");
		keyList.add("I_LARGE_CLASS");
		keyList.add("I_SMALL_CLASS");
		return keyList;
	}
	
}
