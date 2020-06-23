package com.cattsoft.eomsteam.iot.alarm.conf.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cattsoft.eomsteam.iot.alarm.conf.dao.TbAlarmConfigRuleDao;
import com.cattsoft.eomsteam.uc.pub.db.uc_main2.dao.UCMainDao;

@Repository("com.cattsoft.eomsteam.iot.alarm.conf.dao.TbAlarmConfigRuleDao")
public class TbAlarmConfigRuleDaoImpl  extends UCMainDao implements TbAlarmConfigRuleDao {

	@Override
	public List<Map<String,Object>> getAlarmConfigRuleList(){
		StringBuffer sql = new StringBuffer();
		sql.append(" select  ");
		sql.append("   I_ID          as \"I_ID\"          ,I_REF_ID     as \"I_REF_ID\"");
		sql.append("  ,S_COL_EXP_1   as \"S_COL_EXP_1\"   ,S_COL_VAL_1  as \"S_COL_VAL_1\"");
		sql.append("  ,S_COL_EXP_2   as \"S_COL_EXP_2\"   ,S_COL_VAL_2  as \"S_COL_VAL_2\"");
		sql.append("  ,S_COL_VAL_EXP as \"S_COL_VAL_EXP\" ,S_COL_UNIT   as \"S_COL_UNIT\" ");
		sql.append("  ,S_COL_NAME    as \"S_COL_NAME\"    ,I_TBL_ID     as \"I_TBL_ID\"");
		sql.append(" from  TB_ALARM_CONFIG_RULE ");
		List<Object> data = new ArrayList<Object>();
		List<Map<String,Object>> tempResultList =  this.queryBySql(sql.toString(), data);
		return tempResultList;
	}
}
