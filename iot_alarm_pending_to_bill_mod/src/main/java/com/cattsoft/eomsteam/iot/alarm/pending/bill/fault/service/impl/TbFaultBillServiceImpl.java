package com.cattsoft.eomsteam.iot.alarm.pending.bill.fault.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cattsoft.eomsteam.iot.alarm.pending.bill.fault.dao.TbFaultBillDao;
import com.cattsoft.eomsteam.iot.alarm.pending.bill.fault.entity.TbFaultBillEntity;
import com.cattsoft.eomsteam.iot.alarm.pending.bill.fault.service.TbFaultBillService;
import com.cattsoft.eomsteam.uc.pub.db.DefaultStringUtil;

@Service("com.cattsoft.eomsteam.iot.alarm.bill.service.TbAlarmPendingService") 
public class TbFaultBillServiceImpl implements TbFaultBillService {

	@Autowired
	private TbFaultBillDao tbFaultBillDao;
	
	@Override
	public Long getID() {
		// TODO Auto-generated method stub
		return tbFaultBillDao.getID();
	}

	@Override
	public void save(List<TbFaultBillEntity> tbFaultBillEntityList) {
		// TODO Auto-generated method stub
		tbFaultBillDao.save(tbFaultBillEntityList);
	}

	@Override
	public List<TbFaultBillEntity> getCityFaultBillByInst(String S_INST_ID, String S_INST_NAME) {
		// TODO Auto-generated method stub
		List<TbFaultBillEntity>  faultBillEntityList = new ArrayList<TbFaultBillEntity>();
		List<Map<String,Object>> faultBillList =  tbFaultBillDao.getCityFaultBillByInst(S_INST_ID, S_INST_NAME);
		for(Map<String,Object> faultBill : faultBillList){
			TbFaultBillEntity tbFaultBillEntity = new TbFaultBillEntity();
			tbFaultBillEntity.setI_ID(DefaultStringUtil.toLong(faultBill.get("I_ID")));
			tbFaultBillEntity.setS_INST_ID(DefaultStringUtil.toString(faultBill.get("S_INST_ID")));
			tbFaultBillEntity.setS_INST_NAME(DefaultStringUtil.toString(faultBill.get("S_INST_NAME")));
			tbFaultBillEntity.setI_ZIP_CNT(DefaultStringUtil.toInteger(faultBill.get("I_ZIP_CNT")));
			faultBillEntityList.add(tbFaultBillEntity);
		}
		return faultBillEntityList;
	}

	@Override
	public List<TbFaultBillEntity>  getZipFaultBillByInst(String S_INST_ID, String S_INST_NAME) {
		// TODO Auto-generated method stub
		List<TbFaultBillEntity>  faultBillEntityList = new ArrayList<TbFaultBillEntity>();
		List<Map<String,Object>> faultBillList =  tbFaultBillDao.getZipFaultBillByInst(S_INST_ID, S_INST_NAME);
		for(Map<String,Object> faultBill : faultBillList){
			TbFaultBillEntity tbFaultBillEntity = new TbFaultBillEntity();
			tbFaultBillEntity.setI_ID(DefaultStringUtil.toLong(faultBill.get("I_ID")));
			tbFaultBillEntity.setS_INST_ID(DefaultStringUtil.toString(faultBill.get("S_INST_ID")));
			tbFaultBillEntity.setS_INST_NAME(DefaultStringUtil.toString(faultBill.get("S_INST_NAME")));
			tbFaultBillEntity.setI_ZIP_CNT(DefaultStringUtil.toInteger(faultBill.get("I_ZIP_CNT")));
			faultBillEntityList.add(tbFaultBillEntity);
		}
		return faultBillEntityList;
	}

	@Override
	public void updateZipUpdateTimeById(List<Map<String, Object>> setIdList) {
		// TODO Auto-generated method stub
		tbFaultBillDao.updateZipUpdateTimeById(setIdList);
	}

	@Override
	public List<TbFaultBillEntity>  getZipFaultBillByInst(String S_INST_ID, String S_INST_NAME, String CITY_CODE,
			String CITY_NAME) {
		// TODO Auto-generated method stub
		List<TbFaultBillEntity>  faultBillEntityList = new ArrayList<TbFaultBillEntity>();
		List<Map<String,Object>> faultBillList = tbFaultBillDao.getZipFaultBillByInst(S_INST_ID, S_INST_NAME, CITY_CODE, CITY_NAME);
		for(Map<String,Object> faultBill : faultBillList){
			TbFaultBillEntity tbFaultBillEntity = new TbFaultBillEntity();
			tbFaultBillEntity.setI_ID(DefaultStringUtil.toLong(faultBill.get("I_ID")));
			tbFaultBillEntity.setS_INST_ID(DefaultStringUtil.toString(faultBill.get("S_INST_ID")));
			tbFaultBillEntity.setS_INST_NAME(DefaultStringUtil.toString(faultBill.get("S_INST_NAME")));
			tbFaultBillEntity.setI_ZIP_CNT(DefaultStringUtil.toInteger(faultBill.get("I_ZIP_CNT")));
			faultBillEntityList.add(tbFaultBillEntity);
		}
		return faultBillEntityList;
	}

	@Override
	public List<TbFaultBillEntity> getFaultBillByInst(String S_INST_ID, String S_INST_NAME, Integer I_LARGE_CLASS,
			Integer I_SMALL_CLASS) {
		// TODO Auto-generated method stub
		return null;
	}

}
