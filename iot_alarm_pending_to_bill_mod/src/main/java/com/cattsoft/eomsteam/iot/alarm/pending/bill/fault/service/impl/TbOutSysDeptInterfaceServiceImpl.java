package com.cattsoft.eomsteam.iot.alarm.pending.bill.fault.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cattsoft.eomsteam.iot.alarm.pending.bill.fault.dao.TbOutSysDeptInterfaceDao;
import com.cattsoft.eomsteam.iot.alarm.pending.bill.fault.entity.TbOutSysDeptInterfaceEntity;
import com.cattsoft.eomsteam.iot.alarm.pending.bill.fault.service.TbOutSysDeptInterfaceService;
import com.cattsoft.eomsteam.uc.pub.db.DefaultStringUtil;

@Service("com.cattsoft.eomsteam.iot.alarm.bill.service.TbOutSysDeptInterfaceService") 
public class TbOutSysDeptInterfaceServiceImpl implements TbOutSysDeptInterfaceService {

	@Autowired
	private TbOutSysDeptInterfaceDao tbOutSysDeptInterfaceDao;
	
	@Override
	public Map<String, TbOutSysDeptInterfaceEntity> getOutSysDeptData(Integer I_SYS, Integer I_OBJ_TYPE) {
		// TODO Auto-generated method stub
		List<Map<String, Object>>  dataList = tbOutSysDeptInterfaceDao.getOutSysDeptByCityName(I_SYS, I_OBJ_TYPE);
		Map<String, TbOutSysDeptInterfaceEntity> data = new HashMap<String, TbOutSysDeptInterfaceEntity>();
		for(Map<String, Object> d : dataList){
			TbOutSysDeptInterfaceEntity tbOutSysDeptInterfaceEntity = new TbOutSysDeptInterfaceEntity();
			tbOutSysDeptInterfaceEntity.setCITY_CODE(DefaultStringUtil.toString(d.get("CITY_CODE")));
			tbOutSysDeptInterfaceEntity.setCITY_NAME(DefaultStringUtil.toString(d.get("CITY_NAME")));
			tbOutSysDeptInterfaceEntity.setI_ID(DefaultStringUtil.toLong(d.get("I_ID")));
			tbOutSysDeptInterfaceEntity.setI_OBJ_TYPE(DefaultStringUtil.toInteger(d.get("I_OBJ_TYPE")));
			tbOutSysDeptInterfaceEntity.setI_SYS(DefaultStringUtil.toInteger(d.get("I_SYS")));
			tbOutSysDeptInterfaceEntity.setS_REF_ID(DefaultStringUtil.toString(d.get("S_REF_ID")));
			tbOutSysDeptInterfaceEntity.setS_REF_NAME(DefaultStringUtil.toString(d.get("S_REF_NAME")));
			d.put(tbOutSysDeptInterfaceEntity.getCITY_NAME(), tbOutSysDeptInterfaceEntity);
		}
		
		return data;
	}

}
