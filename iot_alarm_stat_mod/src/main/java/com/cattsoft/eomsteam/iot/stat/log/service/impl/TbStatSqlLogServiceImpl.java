package com.cattsoft.eomsteam.iot.stat.log.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cattsoft.eomsteam.iot.stat.log.dao.TbStatSqlLogDao;
import com.cattsoft.eomsteam.iot.stat.log.entity.TbStatSqlLogEntity;
import com.cattsoft.eomsteam.iot.stat.log.service.TbStatSqlLogService;
import com.cattsoft.eomsteam.uc.pub.db.DefaultStringUtil;

@Service("com.cattsoft.eomsteam.iot.stat.log.service.TbStatSqlLogService") 
public class TbStatSqlLogServiceImpl implements TbStatSqlLogService {
	
	@Autowired
	private TbStatSqlLogDao tbStatSqlLogDao;

	@Override
	public void save(TbStatSqlLogEntity tbStatSqlLogEntity) {
		// TODO Auto-generated method stub
		boolean exists = this.exists(tbStatSqlLogEntity.getI_REF_ID(), tbStatSqlLogEntity.getS_INST_CYCLE());
		if(!exists){
			tbStatSqlLogDao.save(tbStatSqlLogEntity);
		}
	}

	@Override
	public List<TbStatSqlLogEntity> getAlarmConfigLogList() {
		// TODO Auto-generated method stub
		List<TbStatSqlLogEntity> configLogEntityList  = new ArrayList<TbStatSqlLogEntity>();
		List<Map<String,Object>> configLogList = tbStatSqlLogDao.getAlarmConfigLogList();
		for(Map<String,Object> configLog : configLogList){
			TbStatSqlLogEntity tbStatSqlLogEntity  = new TbStatSqlLogEntity();
			tbStatSqlLogEntity.setI_CREATE_TIME(DefaultStringUtil.toLong(configLog.get("I_CREATE_TIME")));
			tbStatSqlLogEntity.setI_FLAG(DefaultStringUtil.toInteger(configLog.get("I_FLAG")));
			tbStatSqlLogEntity.setI_ID(DefaultStringUtil.toLong(configLog.get("I_ID")));
			tbStatSqlLogEntity.setI_OBJ_TYPE(DefaultStringUtil.toInteger(configLog.get("I_OBJ_TYPE")));
			tbStatSqlLogEntity.setI_REF_ID(DefaultStringUtil.toLong(configLog.get("I_REF_ID")));
			tbStatSqlLogEntity.setI_RUN_CNT(DefaultStringUtil.toInteger(configLog.get("I_RUN_CNT")));
			tbStatSqlLogEntity.setS_INST_CYCLE(DefaultStringUtil.toString(configLog.get("S_INST_CYCLE")));
			tbStatSqlLogEntity.setI_PENDING_CNT(DefaultStringUtil.toLong(configLog.get("I_PENDING_CNT")));
			
			tbStatSqlLogEntity.setI_STAT_SOURCE(DefaultStringUtil.toInteger(configLog.get("I_STAT_SOURCE")));
			tbStatSqlLogEntity.setI_STAT_TYPE(DefaultStringUtil.toInteger(configLog.get("I_STAT_TYPE")));
			tbStatSqlLogEntity.setS_OBJ_SQL(DefaultStringUtil.toString(configLog.get("S_OBJ_SQL")));
			configLogEntityList.add(tbStatSqlLogEntity);
		}
		return configLogEntityList;
	}

	private boolean exists(Long I_REF_ID, String S_INST_CYCLE) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = tbStatSqlLogDao.getAlarmConfigLogByRefId(I_REF_ID, S_INST_CYCLE);
		if(null == list || list.isEmpty()){
			return false;
		}
		return true;
	}

	@Override
	public void updateAlarmConfigLogById(Long I_ID) {
		// TODO Auto-generated method stub
		tbStatSqlLogDao.updateAlarmConfigLogById(I_ID);
	}

	@Override
	public void updateRunningAlarmConfigLogById(Long I_ID, Long I_PENDING_CNT, Long I_START_TIME, Long I_END_TIME) {
		// TODO Auto-generated method stub
		tbStatSqlLogDao.updateRunningAlarmConfigLogById(I_ID, I_PENDING_CNT, I_START_TIME, I_END_TIME);
	}

	@Override
	public void updateAlarmConfigLogEndFlagById(Long I_ID) {
		// TODO Auto-generated method stub
		tbStatSqlLogDao.updateAlarmConfigLogEndFlagById(I_ID);
	}

}
