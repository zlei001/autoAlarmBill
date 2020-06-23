package com.cattsoft.eomsteam.iot.alarm.tbl.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cattsoft.eomsteam.iot.alarm.tbl.dao.TbAlarmTblDao;
import com.cattsoft.eomsteam.iot.alarm.tbl.entity.TbAlarmTblEntity;
import com.cattsoft.eomsteam.iot.alarm.tbl.service.TbAlarmTblService;
import com.cattsoft.eomsteam.uc.pub.db.DefaultStringUtil;

@Service("com.cattsoft.eomsteam.iot.alarm.tbl.service.TbAlarmTblService") 
public class TbAlarmTblServiceImpl implements TbAlarmTblService {
	
	@Autowired
	private TbAlarmTblDao tbAlarmTblDao;

	@Override
	public TbAlarmTblEntity getAlarmTblById(Long I_ID) {
		// TODO Auto-generated method stub
		TbAlarmTblEntity tbAlarmTblEntity = null;
		Map<String,Object> tbAlarmTblData = tbAlarmTblDao.getAlarmTblById(I_ID);
		if(null != tbAlarmTblData){
			tbAlarmTblEntity = new TbAlarmTblEntity();
			tbAlarmTblEntity.setI_CYCLE(DefaultStringUtil.toInteger(tbAlarmTblData.get("I_CYCLE")));
			tbAlarmTblEntity.setI_ID(DefaultStringUtil.toLong(tbAlarmTblData.get("I_ID")));
			tbAlarmTblEntity.setI_OBJ_TYPE(DefaultStringUtil.toInteger(tbAlarmTblData.get("I_OBJ_TYPE")));
			tbAlarmTblEntity.setI_OBJ_ID(DefaultStringUtil.toInteger(tbAlarmTblData.get("I_OBJ_ID")));
			tbAlarmTblEntity.setS_OBJ_TBL(DefaultStringUtil.toString(tbAlarmTblData.get("S_OBJ_TBL")));
			tbAlarmTblEntity.setS_OBJ_TBL_WHERE(DefaultStringUtil.toString(tbAlarmTblData.get("S_OBJ_TBL_WHERE")));
		
			tbAlarmTblEntity.setI_NE_MODEL(DefaultStringUtil.toInteger(tbAlarmTblData.get("I_NE_MODEL")));
			tbAlarmTblEntity.setI_LARGE_CLASS(DefaultStringUtil.toInteger(tbAlarmTblData.get("I_LARGE_CLASS")));
			tbAlarmTblEntity.setI_SMALL_CLASS(DefaultStringUtil.toInteger(tbAlarmTblData.get("I_SMALL_CLASS")));
		}
		return tbAlarmTblEntity;
	}

}
