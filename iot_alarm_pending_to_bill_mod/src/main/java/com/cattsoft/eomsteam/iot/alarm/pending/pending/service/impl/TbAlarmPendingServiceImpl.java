package com.cattsoft.eomsteam.iot.alarm.pending.pending.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cattsoft.eomsteam.iot.alarm.pending.pending.dao.TbAlarmPendingDao;
import com.cattsoft.eomsteam.iot.alarm.pending.pending.entity.TbAlarmPendingEntity;
import com.cattsoft.eomsteam.iot.alarm.pending.pending.service.TbAlarmPendingService;
import com.cattsoft.eomsteam.uc.pub.db.DefaultStringUtil;

@Service("ccom.cattsoft.eomsteam.iot.alarm.pending.pending.service.TbAlarmPendingService") 
public class TbAlarmPendingServiceImpl implements TbAlarmPendingService {
	
	@Autowired
	private  TbAlarmPendingDao tbAlarmPendingDao;

	@Override
	public List<Map<String, Object>> getAlarmPendingCellZipCntList() {
		// TODO Auto-generated method stub
		return tbAlarmPendingDao.getAlarmPendingCellZipCntList();
	}

	@Override
	public List<TbAlarmPendingEntity> getAlarmPendingZipListByInst(String S_INST_ID, String S_INST_NAME) {
		// TODO Auto-generated method stub
		List<TbAlarmPendingEntity> pendingEntityList = new ArrayList<TbAlarmPendingEntity>();
		List<Map<String,Object>> pendingZipList =  tbAlarmPendingDao.getAlarmPendingZipListByInst(S_INST_ID, S_INST_NAME);
		for(Map<String,Object> pendingZipData : pendingZipList){
			TbAlarmPendingEntity tbAlarmPendingEntity = this.getTbAlarmPendingEntity(pendingZipData);
			pendingEntityList.add(tbAlarmPendingEntity);
		}
		return pendingEntityList;
	}
	
	/***
	 * 设置实体类
	 * @param pendingZipData 压缩数据
	 * @return
	 */
	protected TbAlarmPendingEntity getTbAlarmPendingEntity(Map<String,Object> pendingZipData){
		TbAlarmPendingEntity tbAlarmPendingEntity = new TbAlarmPendingEntity();
		tbAlarmPendingEntity.setCITY_CODE(DefaultStringUtil.toString(pendingZipData.get("CITY_CODE")));
		tbAlarmPendingEntity.setCITY_NAME(DefaultStringUtil.toString(pendingZipData.get("CITY_NAME")));
		tbAlarmPendingEntity.setI_CANCEL_FLAG(DefaultStringUtil.toInteger(pendingZipData.get("I_CANCEL_FLAG")));
		tbAlarmPendingEntity.setI_CREATE_TIME(DefaultStringUtil.toLong(pendingZipData.get("I_CREATE_TIME")));
		tbAlarmPendingEntity.setI_CYCLE(DefaultStringUtil.toInteger(pendingZipData.get("I_CYCLE")));
		tbAlarmPendingEntity.setI_DISPATCH_FLAG(DefaultStringUtil.toInteger(pendingZipData.get("I_DISPATCH_FLAG")));
		tbAlarmPendingEntity.setI_ID(DefaultStringUtil.toLong(pendingZipData.get("I_ID")));
		tbAlarmPendingEntity.setI_LARGE_CLASS(DefaultStringUtil.toInteger(pendingZipData.get("I_LARGE_CLASS")));
		tbAlarmPendingEntity.setI_NE_MODEL(DefaultStringUtil.toInteger(pendingZipData.get("I_NE_MODEL")));
		tbAlarmPendingEntity.setI_OBJ_ID(DefaultStringUtil.toInteger(pendingZipData.get("I_OBJ_ID")));
		tbAlarmPendingEntity.setI_OBJ_LEVEL(DefaultStringUtil.toInteger(pendingZipData.get("I_OBJ_LEVEL")));
		
		tbAlarmPendingEntity.setI_OBJ_SOURCE(DefaultStringUtil.toInteger(pendingZipData.get("I_OBJ_SOURCE")));
		tbAlarmPendingEntity.setI_OBJ_TYPE(DefaultStringUtil.toInteger(pendingZipData.get("I_OBJ_TYPE")));
		tbAlarmPendingEntity.setI_REF_ID(DefaultStringUtil.toLong(pendingZipData.get("I_REF_ID")));
		tbAlarmPendingEntity.setI_RESET_FLAG(DefaultStringUtil.toInteger(pendingZipData.get("I_RESET_FLAG")));
		
		tbAlarmPendingEntity.setI_SMALL_CLASS(DefaultStringUtil.toInteger(pendingZipData.get("I_SMALL_CLASS")));
		tbAlarmPendingEntity.setI_UPDATE_TIME(DefaultStringUtil.toLong(pendingZipData.get("I_UPDATE_TIME")));
		tbAlarmPendingEntity.setI_ZIP_CNT(DefaultStringUtil.toInteger(pendingZipData.get("I_ZIP_CNT")));
		tbAlarmPendingEntity.setS_DELIMITED_CYCLE(DefaultStringUtil.toString(pendingZipData.get("S_DELIMITED_CYCLE")));
	
		tbAlarmPendingEntity.setS_INST_ID(DefaultStringUtil.toString(pendingZipData.get("S_INST_ID")));
		tbAlarmPendingEntity.setS_INST_NAME(DefaultStringUtil.toString(pendingZipData.get("S_INST_NAME")));
		tbAlarmPendingEntity.setS_MSG(DefaultStringUtil.toString(pendingZipData.get("S_MSG")));
		tbAlarmPendingEntity.setS_NO(DefaultStringUtil.toString(pendingZipData.get("S_NO")));
		tbAlarmPendingEntity.setS_TITLE(DefaultStringUtil.toString(pendingZipData.get("S_TITLE")));
		
		return tbAlarmPendingEntity;
	}

	@Override
	public void updateAlarmPendingDispatchFlag(List<Map<String, Object>> setIdList) {
		// TODO Auto-generated method stub
		tbAlarmPendingDao.updateAlarmPendingDispatchFlag(setIdList);
	}

	@Override
	public List<TbAlarmPendingEntity> getAlarmPendingByCellOneLevel() {
		List<TbAlarmPendingEntity> pendingEntityList = new ArrayList<TbAlarmPendingEntity>();
		List<Map<String,Object>> pendingZipList =  tbAlarmPendingDao.getAlarmPendingByCellOneLevel();
		for(Map<String,Object> pendingZipData : pendingZipList){
			TbAlarmPendingEntity tbAlarmPendingEntity = this.getTbAlarmPendingEntity(pendingZipData);
			pendingEntityList.add(tbAlarmPendingEntity);
		}
		return pendingEntityList;
	}

	@Override
	public List<TbAlarmPendingEntity> getAlarmPendingByNotCellOneLevel() {
		List<TbAlarmPendingEntity> pendingEntityList = new ArrayList<TbAlarmPendingEntity>();
		List<Map<String,Object>> pendingZipList =  tbAlarmPendingDao.getAlarmPendingByNotCellOneLevel();
		for(Map<String,Object> pendingZipData : pendingZipList){
			TbAlarmPendingEntity tbAlarmPendingEntity = this.getTbAlarmPendingEntity(pendingZipData);
			pendingEntityList.add(tbAlarmPendingEntity);
		}
		return pendingEntityList;
	}

	@Override
	public List<TbAlarmPendingEntity> getAlarmPendingByCellTwoLevel() {
		List<TbAlarmPendingEntity> pendingEntityList = new ArrayList<TbAlarmPendingEntity>();
		List<Map<String,Object>> pendingZipList =  tbAlarmPendingDao.getAlarmPendingByCellTwoLevel();
		for(Map<String,Object> pendingZipData : pendingZipList){
			TbAlarmPendingEntity tbAlarmPendingEntity = this.getTbAlarmPendingEntity(pendingZipData);
			pendingEntityList.add(tbAlarmPendingEntity);
		}
		return pendingEntityList;
	}

	@Override
	public List<TbAlarmPendingEntity> getAlarmPendingByNotCellTwoLevel() {
		List<TbAlarmPendingEntity> pendingEntityList = new ArrayList<TbAlarmPendingEntity>();
		List<Map<String,Object>> pendingZipList =  tbAlarmPendingDao.getAlarmPendingByNotCellTwoLevel();
		for(Map<String,Object> pendingZipData : pendingZipList){
			TbAlarmPendingEntity tbAlarmPendingEntity = this.getTbAlarmPendingEntity(pendingZipData);
			pendingEntityList.add(tbAlarmPendingEntity);
		}
		return pendingEntityList;
	}

	@Override
	public List<Map<String, Object>> getAlarmPendingNotCellZipCntList() {
		// TODO Auto-generated method stub
		return tbAlarmPendingDao.getAlarmPendingNotCellZipCntList();
	}
}
