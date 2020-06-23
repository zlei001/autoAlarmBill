package com.cattsoft.eomsteam.iot.alarm.tbl.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cattsoft.eomsteam.iot.alarm.tbl.dao.TbAlarmTblQueryColDao;
import com.cattsoft.eomsteam.iot.alarm.tbl.entity.TbAlarmTblQueryColEntity;
import com.cattsoft.eomsteam.iot.alarm.tbl.service.TbAlarmTblQueryColService;

@Service("com.cattsoft.eomsteam.iot.alarm.tbl.service.TbAlarmTblQueryColService") 
public class TbAlarmTblQueryColServiceImpl implements TbAlarmTblQueryColService {

	@Autowired
	private TbAlarmTblQueryColDao tbDelimitedTblQueryColDao;
	

	/***
	 * 
	 * @param I_OBJ_TYPE
	 * @param I_NE_MODEL
	 * @param I_LARGE_CLASS
	 * @param I_SMALL_CLASS
	 * @param I_CYCLE
	 * @param S_OBJ_TBL
	 * @return
	 */
	
	@Override
	public List<TbAlarmTblQueryColEntity> geAlarmTblQueryColList(Integer I_OBJ_TYPE ,Integer I_NE_MODEL,Integer I_LARGE_CLASS,Integer I_SMALL_CLASS ,Integer I_CYCLE,String S_OBJ_TBL) {
		List<TbAlarmTblQueryColEntity> queryColEntityList = new ArrayList<TbAlarmTblQueryColEntity>();
		List<Map<String,Object>> queryColList = tbDelimitedTblQueryColDao.getAlarmTblQueryColList( I_OBJ_TYPE , I_NE_MODEL, I_LARGE_CLASS, I_SMALL_CLASS , I_CYCLE, S_OBJ_TBL);
		for(Map<String,Object> queryCol : queryColList){
			TbAlarmTblQueryColEntity tbDelimitedTblQueryColEntity = new TbAlarmTblQueryColEntity();
			tbDelimitedTblQueryColEntity.setS_COL_NAME(String.valueOf(queryCol.get("S_COL_NAME")));
			tbDelimitedTblQueryColEntity.setS_COL_NAME_ALIAS(String.valueOf(queryCol.get("S_COL_NAME_ALIAS")));
			tbDelimitedTblQueryColEntity.setS_COL_NAME_ZH(String.valueOf(queryCol.get("S_COL_NAME_ZH")));
			queryColEntityList.add(tbDelimitedTblQueryColEntity);
			
		}
		return queryColEntityList;
	}
	
	@Override
	public Map<String,String> getAlarmTblQueryColData(Integer I_OBJ_TYPE ,Integer I_NE_MODEL,Integer I_LARGE_CLASS,Integer I_SMALL_CLASS ,Integer I_CYCLE,String S_OBJ_TBL) {
		Map<String,String> queryColData = new HashMap<String,String>();
		List<Map<String,Object>> queryColList = tbDelimitedTblQueryColDao.getAlarmTblQueryColList( I_OBJ_TYPE , I_NE_MODEL, I_LARGE_CLASS, I_SMALL_CLASS , I_CYCLE, S_OBJ_TBL);
		for(Map<String,Object> queryCol : queryColList){
			queryColData.put(String.valueOf(queryCol.get("S_COL_NAME_ALIAS")), String.valueOf(queryCol.get("S_COL_NAME_ZH")));
		}
		return queryColData;
	}


}
