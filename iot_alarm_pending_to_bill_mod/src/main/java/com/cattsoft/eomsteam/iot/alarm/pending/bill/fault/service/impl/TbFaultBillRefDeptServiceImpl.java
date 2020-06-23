package com.cattsoft.eomsteam.iot.alarm.pending.bill.fault.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cattsoft.eomsteam.iot.alarm.pending.bill.fault.dao.TbFaultBillRefDeptDao;
import com.cattsoft.eomsteam.iot.alarm.pending.bill.fault.entity.TbFaultBillEntity;
import com.cattsoft.eomsteam.iot.alarm.pending.bill.fault.entity.TbFaultBillRefDeptEntity;
import com.cattsoft.eomsteam.iot.alarm.pending.bill.fault.entity.TbOutSysDeptInterfaceEntity;
import com.cattsoft.eomsteam.iot.alarm.pending.bill.fault.service.TbFaultBillRefDeptService;
import com.cattsoft.eomsteam.iot.alarm.pending.bill.fault.service.TbOutSysDeptInterfaceService;

@Service("com.cattsoft.eomsteam.iot.alarm.bill.service.TbFaultBillRefDeptService") 
public class TbFaultBillRefDeptServiceImpl implements TbFaultBillRefDeptService {
	
	@Autowired
	private TbFaultBillRefDeptDao tbFaultBillRefDeptDao;
	
	@Autowired
	private TbOutSysDeptInterfaceService tbOutSysDeptInterfaceService;
	
	@Override
	public void save(List<TbFaultBillEntity> tbFaultBillEntityList) {
		// TODO Auto-generated method stub
		Integer I_SYS  = 1;
		Integer I_OBJ_TYPE = 1;
		Map<String, TbOutSysDeptInterfaceEntity> data= tbOutSysDeptInterfaceService.getOutSysDeptData(I_SYS, I_OBJ_TYPE);
		
		List<TbFaultBillRefDeptEntity> tbFaultBillToOutSysReqList = new ArrayList<TbFaultBillRefDeptEntity>();
		for(TbFaultBillEntity tbFaultBillEntity : tbFaultBillEntityList){
			TbOutSysDeptInterfaceEntity tbOutSysDeptInterfaceEntity = data.get(tbFaultBillEntity.getCITY_NAME());
			TbFaultBillRefDeptEntity tbFaultBillRefDeptEntity = new TbFaultBillRefDeptEntity();
			tbFaultBillRefDeptEntity.setCITY_CODE(tbFaultBillEntity.getCITY_CODE());
			tbFaultBillRefDeptEntity.setCITY_NAME(tbFaultBillEntity.getCITY_NAME());
			tbFaultBillRefDeptEntity.setI_BILL_ID(tbFaultBillEntity.getI_ID());
			tbFaultBillRefDeptEntity.setI_ID(tbFaultBillRefDeptDao.getID());
			if(null != tbOutSysDeptInterfaceEntity){
				tbFaultBillRefDeptEntity.setI_DEPT_ID(tbOutSysDeptInterfaceEntity.getS_REF_ID());
				tbFaultBillRefDeptEntity.setS_DEPT_NAME(tbOutSysDeptInterfaceEntity.getS_REF_NAME());
				tbFaultBillRefDeptEntity.setS_DISP_NAME(tbOutSysDeptInterfaceEntity.getS_REF_NAME());
			}
		}
		tbFaultBillRefDeptDao.save(tbFaultBillToOutSysReqList);
	}

}
