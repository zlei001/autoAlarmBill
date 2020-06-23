package com.cattsoft.eomsteam.iot.stat.record.busi.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cattsoft.eomsteam.iot.stat.log.entity.TbStatSqlLogEntity;
import com.cattsoft.eomsteam.iot.stat.record.busi.abs.AbsStatRecordBusiService;
import com.cattsoft.eomsteam.iot.stat.record.entity.TbStatRecordPoolDyEntity;
import com.cattsoft.eomsteam.iot.stat.record.service.TbStatRecordPoolDyService;
import com.cattsoft.eomsteam.uc.pub.db.DefaultStringUtil;

@Service("com.cattsoft.eomsteam.iot.stat.record.busi.4") 
public class PoolStatRecordBusiServiceImpl extends AbsStatRecordBusiService {
	
	@Autowired
	private TbStatRecordPoolDyService tbStatRecordPoolDyService;

	@Override
	public void doBusi(TbStatSqlLogEntity tbStatRecordLogEntity ,List<Map<String,Object>> statResultList){
		List<TbStatRecordPoolDyEntity> saveTbStatRecordPoolDyEntityList = new ArrayList<TbStatRecordPoolDyEntity>();
		List<TbStatRecordPoolDyEntity> updateTbStatRecordPoolDyEntityList = new ArrayList<TbStatRecordPoolDyEntity>();
		List<String> keyList = this.getKeyList();
		Map<String,Long>  statRecordData = tbStatRecordPoolDyService.getTbStatRecordDataByKey(tbStatRecordLogEntity.getS_INST_CYCLE(),keyList);
		
		for(Map<String,Object> statResultData : statResultList){
			String key = tbStatRecordPoolDyService.getKey(keyList, statResultData);
			TbStatRecordPoolDyEntity tbStatRecordCityDyEntity = new TbStatRecordPoolDyEntity();
			tbStatRecordCityDyEntity.setMME_NAME(DefaultStringUtil.toString(statResultData.get("MME_NAME")));
			tbStatRecordCityDyEntity.setI_ID(DefaultStringUtil.toLong(statResultData.get("I_ID")));
			tbStatRecordCityDyEntity.setI_LARGE_CLASS(DefaultStringUtil.toInteger(statResultData.get("I_LARGE_CLASS")));
			tbStatRecordCityDyEntity.setI_NE_MODEL(DefaultStringUtil.toInteger(statResultData.get("I_NE_MODEL")));
			tbStatRecordCityDyEntity.setI_SMALL_CLASS(DefaultStringUtil.toInteger(statResultData.get("I_SMALL_CLASS")));
			tbStatRecordCityDyEntity.setSTATIS_TIME(DefaultStringUtil.toString(statResultData.get("STATIS_TIME")));
			
			if(statRecordData.containsKey(key)){
				//已存在,更新
				tbStatRecordCityDyEntity.setI_ID(DefaultStringUtil.toLong(statRecordData.get(key)));
				this.setTbStatRecordPoolDyEntity(tbStatRecordCityDyEntity, tbStatRecordLogEntity, statResultData);
				updateTbStatRecordPoolDyEntityList.add(tbStatRecordCityDyEntity);
			}else{
				//不存在,新增
				this.setTbStatRecordPoolDyEntity(tbStatRecordCityDyEntity, tbStatRecordLogEntity, statResultData);
				tbStatRecordCityDyEntity.setI_ID(tbStatRecordPoolDyService.getID());
				saveTbStatRecordPoolDyEntityList.add(tbStatRecordCityDyEntity);
			}
		}
		tbStatRecordPoolDyService.save(saveTbStatRecordPoolDyEntityList);
		this.setUpdate(tbStatRecordLogEntity, updateTbStatRecordPoolDyEntityList);
	}
	
	/***
	 * 设置字符串值
	 * @param tbStatRecordCityDyEntity
	 * @param tbStatRecordLogEntity
	 * @param statResultData
	 */
	private void setUpdate(TbStatSqlLogEntity tbStatRecordLogEntity ,List<TbStatRecordPoolDyEntity> setIdList){
		if(1 == tbStatRecordLogEntity.getI_STAT_SOURCE()){
			if(1 == tbStatRecordLogEntity.getI_STAT_TYPE()){
				tbStatRecordPoolDyService.updateDelimitedStatRecordByKey(setIdList);
			}else{
				tbStatRecordPoolDyService.updateDelimitedEndStatRecordByKey(setIdList);
			}
		}else if(2 == tbStatRecordLogEntity.getI_STAT_SOURCE()){
			if(1 == tbStatRecordLogEntity.getI_STAT_TYPE()){
				tbStatRecordPoolDyService.updateAlarmStatRecordByKey(setIdList);
			}else{
				tbStatRecordPoolDyService.updateAlarmStatEndRecordByKey(setIdList);
			}
		}else if(3 == tbStatRecordLogEntity.getI_STAT_SOURCE()){
			if(1 == tbStatRecordLogEntity.getI_STAT_TYPE()){
				tbStatRecordPoolDyService.updateAlarmPendingStatRecordByKey(setIdList);
			}else{
				tbStatRecordPoolDyService.updateAlarmPendingStatEndRecordByKey(setIdList);
			}
		}else if(4 == tbStatRecordLogEntity.getI_STAT_SOURCE()){
			if(1 == tbStatRecordLogEntity.getI_STAT_TYPE()){
				tbStatRecordPoolDyService.updateBillStatRecordByKey(setIdList);
			}else{
				tbStatRecordPoolDyService.updateBillStatEndRecordByKey(setIdList);
			}
		}
	}
	
	
	/***
	 * 设置字符串值
	 * @param tbStatRecordCityDyEntity
	 * @param tbStatRecordLogEntity
	 * @param statResultData
	 */
	private void setTbStatRecordPoolDyEntity(TbStatRecordPoolDyEntity tbStatRecordCityDyEntity
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
		keyList.add("MME_NAME");
		keyList.add("I_NE_MODEL");
		keyList.add("I_LARGE_CLASS");
		keyList.add("I_SMALL_CLASS");
		return keyList;
	}
	
}
