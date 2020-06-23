package com.cattsoft.eomsteam.iot.alarm.pending.bill.fault.service;

import java.util.List;
import java.util.Map;

import com.cattsoft.eomsteam.iot.alarm.pending.bill.fault.entity.TbOutSysDeptInterfaceEntity;

public interface TbOutSysDeptInterfaceService {

	public Map<String, TbOutSysDeptInterfaceEntity> getOutSysDeptData(Integer I_SYS ,Integer I_OBJ_TYPE); 
}
