package com.cattsoft.eomsteam.iot.alarm.conf.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cattsoft.eomsteam.iot.alarm.conf.dao.TbAlarmConfigRuleDao;
import com.cattsoft.eomsteam.iot.alarm.conf.entity.TbAlarmConfigRuleEntity;
import com.cattsoft.eomsteam.iot.alarm.conf.service.TbAlarmConfigRuleService;
import com.cattsoft.eomsteam.uc.pub.db.DefaultStringUtil;

@Service("com.cattsoft.eomsteam.iot.alarm.conf.service.TbAlarmConfigRuleService") 
public class TbAlarmConfigRuleServiceImpl implements TbAlarmConfigRuleService {

	@Autowired
	private TbAlarmConfigRuleDao tbAlarmConfigRuleDao;
	
	@Override
	public Map<Long, List<TbAlarmConfigRuleEntity>> getAlarmConfigRuleData() {
		Map<Long, List<TbAlarmConfigRuleEntity>> configRuleData = new HashMap<Long, List<TbAlarmConfigRuleEntity>>();
		List<Map<String,Object>> configRuleList = tbAlarmConfigRuleDao.getAlarmConfigRuleList();
		for(Map<String,Object> config : configRuleList){
			TbAlarmConfigRuleEntity  tbDelimitedConfigRuleEntity = new TbAlarmConfigRuleEntity();
			tbDelimitedConfigRuleEntity.setI_ID(DefaultStringUtil.toLong(config.get("I_ID")));
			tbDelimitedConfigRuleEntity.setI_REF_ID(DefaultStringUtil.toLong(config.get("I_REF_ID")));
			tbDelimitedConfigRuleEntity.setI_TBL_ID(DefaultStringUtil.toLong(config.get("I_TBL_ID")));
			tbDelimitedConfigRuleEntity.setS_COL_NAME(DefaultStringUtil.toString(config.get("S_COL_NAME")));
			tbDelimitedConfigRuleEntity.setS_COL_EXP_1(DefaultStringUtil.toString(config.get("S_COL_EXP_1")));
			tbDelimitedConfigRuleEntity.setS_COL_VAL_1(DefaultStringUtil.toString(config.get("S_COL_VAL_1")));
			tbDelimitedConfigRuleEntity.setS_COL_VAL_EXP(DefaultStringUtil.toString(config.get("S_COL_VAL_EXP")));
			tbDelimitedConfigRuleEntity.setS_COL_EXP_2(DefaultStringUtil.toString(config.get("S_COL_EXP_2")));
			tbDelimitedConfigRuleEntity.setS_COL_VAL_2(DefaultStringUtil.toString(config.get("S_COL_VAL_2")));
			tbDelimitedConfigRuleEntity.setS_COL_UNIT(DefaultStringUtil.toString(config.get("S_COL_UNIT")));
		
			if(configRuleData.containsKey(tbDelimitedConfigRuleEntity.getI_REF_ID())){
				configRuleData.get(tbDelimitedConfigRuleEntity.getI_REF_ID()).add(tbDelimitedConfigRuleEntity);
			}else{
				List<TbAlarmConfigRuleEntity> list = new ArrayList<TbAlarmConfigRuleEntity>();
				list.add(tbDelimitedConfigRuleEntity);
				configRuleData.put(tbDelimitedConfigRuleEntity.getI_REF_ID(), list);
			}
		}
		return configRuleData;
	}

	@Override
	public String getDelimitedConfigWhereSql(List<TbAlarmConfigRuleEntity> delimitedConfigRuleEntityList) {
		// TODO Auto-generated method stub
		StringBuffer  whereSql = new StringBuffer();
		for(TbAlarmConfigRuleEntity tbDelimitedConfigRuleEntity : delimitedConfigRuleEntityList){
			if(whereSql.length() > 0){
				whereSql.append(" and ");
			}
			whereSql.append(tbDelimitedConfigRuleEntity.getWhereSql());
		}
		return whereSql.toString();
	}

}
