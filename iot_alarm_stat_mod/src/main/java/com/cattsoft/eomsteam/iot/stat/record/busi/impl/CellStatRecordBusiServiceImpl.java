package com.cattsoft.eomsteam.iot.stat.record.busi.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cattsoft.eomsteam.iot.stat.log.entity.TbStatSqlLogEntity;
import com.cattsoft.eomsteam.iot.stat.record.busi.abs.AbsStatRecordBusiService;
import com.cattsoft.eomsteam.iot.stat.record.entity.TbStatRecordCellDyEntity;
import com.cattsoft.eomsteam.iot.stat.record.service.TbStatRecordCellDyService;
import com.cattsoft.eomsteam.uc.pub.db.DefaultStringUtil;

@Service("com.cattsoft.eomsteam.iot.stat.record.busi.3") 
public class CellStatRecordBusiServiceImpl extends AbsStatRecordBusiService {
	
	@Autowired
	private TbStatRecordCellDyService tbStatRecordCellDyService;

	@Override
	public void doBusi(TbStatSqlLogEntity tbStatRecordLogEntity ,List<Map<String,Object>> statResultList){
		List<TbStatRecordCellDyEntity> saveTbStatRecordCellDyEntityList = new ArrayList<TbStatRecordCellDyEntity>();
		List<TbStatRecordCellDyEntity> updateTbStatRecordCellDyEntityList = new ArrayList<TbStatRecordCellDyEntity>();
		List<String> keyList = this.getKeyList();
		Map<String,Long>  statRecordData = tbStatRecordCellDyService.getTbStatRecordDataByKey(tbStatRecordLogEntity.getS_INST_CYCLE(),keyList);
		
		for(Map<String,Object> statResultData : statResultList){
			String key = tbStatRecordCellDyService.getKey(keyList, statResultData);
			TbStatRecordCellDyEntity tbStatRecordCityDyEntity = new TbStatRecordCellDyEntity();
			tbStatRecordCityDyEntity.setCITY_CODE(DefaultStringUtil.toString(statResultData.get("CITY_CODE")));
			tbStatRecordCityDyEntity.setCITY_NAME(DefaultStringUtil.toString(statResultData.get("CITY_NAME")));
			tbStatRecordCityDyEntity.setDST_ECI(DefaultStringUtil.toString(statResultData.get("DST_ECI")));
			tbStatRecordCityDyEntity.setDST_ECI_NAME(DefaultStringUtil.toString(statResultData.get("DST_ECI_NAME")));
			
			tbStatRecordCityDyEntity.setI_ID(DefaultStringUtil.toLong(statResultData.get("I_ID")));
			tbStatRecordCityDyEntity.setI_LARGE_CLASS(DefaultStringUtil.toInteger(statResultData.get("I_LARGE_CLASS")));
			tbStatRecordCityDyEntity.setI_NE_MODEL(DefaultStringUtil.toInteger(statResultData.get("I_NE_MODEL")));
			tbStatRecordCityDyEntity.setI_SMALL_CLASS(DefaultStringUtil.toInteger(statResultData.get("I_SMALL_CLASS")));
			tbStatRecordCityDyEntity.setSTATIS_TIME(DefaultStringUtil.toString(statResultData.get("STATIS_TIME")));
			
			if(statRecordData.containsKey(key)){
				//已存在,更新
				tbStatRecordCityDyEntity.setI_ID(DefaultStringUtil.toLong(statRecordData.get(key)));
				this.setTbStatRecordCellDyEntity(tbStatRecordCityDyEntity, tbStatRecordLogEntity, statResultData);
				updateTbStatRecordCellDyEntityList.add(tbStatRecordCityDyEntity);
			}else{
				//不存在,新增
				this.setTbStatRecordCellDyEntity(tbStatRecordCityDyEntity, tbStatRecordLogEntity, statResultData);
				tbStatRecordCityDyEntity.setI_ID(tbStatRecordCellDyService.getID());
				saveTbStatRecordCellDyEntityList.add(tbStatRecordCityDyEntity);
			}
		}
		tbStatRecordCellDyService.save(saveTbStatRecordCellDyEntityList);
		this.setUpdate(tbStatRecordLogEntity, updateTbStatRecordCellDyEntityList);
	}
	
	/***
	 * 设置字符串值
	 * @param tbStatRecordCityDyEntity
	 * @param tbStatRecordLogEntity
	 * @param statResultData
	 */
	private void setUpdate(TbStatSqlLogEntity tbStatRecordLogEntity ,List<TbStatRecordCellDyEntity> setIdList){
		if(1 == tbStatRecordLogEntity.getI_STAT_SOURCE()){
			if(1 == tbStatRecordLogEntity.getI_STAT_TYPE()){
				tbStatRecordCellDyService.updateDelimitedStatRecordByKey(setIdList);
			}else{
				tbStatRecordCellDyService.updateDelimitedEndStatRecordByKey(setIdList);
			}
		}else if(2 == tbStatRecordLogEntity.getI_STAT_SOURCE()){
			if(1 == tbStatRecordLogEntity.getI_STAT_TYPE()){
				tbStatRecordCellDyService.updateAlarmStatRecordByKey(setIdList);
			}else{
				tbStatRecordCellDyService.updateAlarmStatEndRecordByKey(setIdList);
			}
		}else if(3 == tbStatRecordLogEntity.getI_STAT_SOURCE()){
			if(1 == tbStatRecordLogEntity.getI_STAT_TYPE()){
				tbStatRecordCellDyService.updateAlarmPendingStatRecordByKey(setIdList);
			}else{
				tbStatRecordCellDyService.updateAlarmPendingStatEndRecordByKey(setIdList);
			}
		}else if(4 == tbStatRecordLogEntity.getI_STAT_SOURCE()){
			if(1 == tbStatRecordLogEntity.getI_STAT_TYPE()){
				tbStatRecordCellDyService.updateBillStatRecordByKey(setIdList);
			}else{
				tbStatRecordCellDyService.updateBillStatEndRecordByKey(setIdList);
			}
		}
	}
	
	
	/***
	 * 设置字符串值
	 * @param tbStatRecordCityDyEntity
	 * @param tbStatRecordLogEntity
	 * @param statResultData
	 */
	private void setTbStatRecordCellDyEntity(TbStatRecordCellDyEntity tbStatRecordCityDyEntity
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
		keyList.add("CITY_CODE");
		keyList.add("DST_ECI");
		keyList.add("I_NE_MODEL");
		keyList.add("I_LARGE_CLASS");
		keyList.add("I_SMALL_CLASS");
		return keyList;
	}
	
}
