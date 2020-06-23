package com.cattsoft.eomsteam.iot.alarm.pending.bill.fault.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cattsoft.eomsteam.iot.alarm.pending.bill.fault.dao.TbOutSysDeptInterfaceDao;
import com.cattsoft.eomsteam.uc.pub.db.uc_main2.dao.UCMainDao;

@Repository("com.cattsoft.eomsteam.iot.alarm.pending.bill.fault.dao.TbOutSysDeptInterfaceDao")
public class TbOutSysDeptInterfaceDaoImpl  extends UCMainDao implements TbOutSysDeptInterfaceDao {

	@Override
	public List<Map<String, Object>> getOutSysDeptByCityName(Integer I_SYS ,Integer I_OBJ_TYPE) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select  ");
		sql.append(" I_ID, I_SYS, CITY_CODE, CITY_NAME, I_OBJ_TYPE, S_REF_ID, S_REF_NAME");
		sql.append(" from  TB_OUTSYS_DEPT_INTERFACE ");
		sql.append(" where   I_SYS = ? AND I_OBJ_TYPE = ? ");
		List<Object> data = new ArrayList<Object>();
		data.add(I_SYS);
		data.add(I_OBJ_TYPE);
		List<Map<String,Object>> tempResultList =  this.queryBySql(sql.toString(), data);
		return tempResultList;
	}
}
