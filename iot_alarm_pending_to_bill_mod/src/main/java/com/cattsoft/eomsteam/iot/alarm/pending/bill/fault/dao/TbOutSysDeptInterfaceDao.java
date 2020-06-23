package com.cattsoft.eomsteam.iot.alarm.pending.bill.fault.dao;

import java.util.List;
import java.util.Map;

public interface TbOutSysDeptInterfaceDao {

	public List<Map<String, Object>> getOutSysDeptByCityName(Integer I_SYS ,Integer I_OBJ_TYPE) ;
}
