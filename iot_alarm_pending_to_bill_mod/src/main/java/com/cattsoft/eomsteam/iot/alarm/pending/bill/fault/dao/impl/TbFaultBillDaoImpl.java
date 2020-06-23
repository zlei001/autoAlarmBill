package com.cattsoft.eomsteam.iot.alarm.pending.bill.fault.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.cattsoft.eomsteam.iot.alarm.pending.bill.fault.dao.TbFaultBillRefAlarmDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cattsoft.eomsteam.iot.alarm.pending.bill.fault.dao.TbFaultBillDao;
import com.cattsoft.eomsteam.iot.alarm.pending.bill.fault.entity.TbFaultBillEntity;
import com.cattsoft.eomsteam.mtoppub.ssh3InitUtil.ConfigService;
import com.cattsoft.eomsteam.uc.pub.db.DefaultStringUtil;
import com.cattsoft.eomsteam.uc.pub.db.uc_main2.dao.UCMainDao;
import com.cattsoft.eomsteam.iot.alarm.pending.bill.fault.util.TbSerialNoDao;
import com.cattsoft.eomsteam.iot.alarm.pending.bill.fault.util.TbSerialNo;
@Repository("com.cattsoft.eomsteam.iot.alarm.bill.dao.TbFaultBillDao")
public class TbFaultBillDaoImpl  extends UCMainDao implements TbFaultBillDao {
	@Autowired
	private TbSerialNoDao tbSerialNoDao;

	@Override
	public Long getID() {
	    String id = this.callIdGenratorProc2("TB_FAULT_BILL",this.getDomain());
		return Long.valueOf(id);
	}

	@Override
	public void save(List<TbFaultBillEntity> faultBillEntityList) {
		// TODO Auto-generated method stub
		int batchNum  = DefaultStringUtil.toInteger((ConfigService.getPropertiyByKey("batchNum","300")));
		int modFileListSize = faultBillEntityList.size();
		int pageNo = modFileListSize/batchNum;
		if(modFileListSize % batchNum !=0){
			pageNo = pageNo + 1;
		}
		for(int i= 0;i<pageNo;i++ ){
			List<TbFaultBillEntity> tempList = new ArrayList<TbFaultBillEntity>();
			int runNum = (i+1)*batchNum > modFileListSize ? modFileListSize : (i+1)*batchNum;
			for(int j=i*batchNum;j<runNum;j++){
				tempList.add(faultBillEntityList.get(j));
			}
			
			StringBuffer sql = new StringBuffer();
			sql.append(" INSERT INTO ").append("TB_FAULT_BILL");
			sql.append(" (I_ID       ,S_NO        ,I_OBJ_LEVEL,I_OBJ_TYPE,I_NE_MODEL,I_LARGE_CLASS");
			sql.append(" ,I_SMALL_CLASS      ,I_CYCLE,S_TITLE,S_MSG,S_DELIMITED_CYCLE");
			sql.append(" ,CITY_CODE,CITY_NAME     ,S_INST_ID,S_INST_NAME");
			sql.append(" ,I_FLAG,I_CREATE_TIME,I_UPDATE_TIME");
			sql.append(" ,I_DISPATCH,S_OUTSYS_NO     ,I_SOURCE,I_DB_TYPE");
			sql.append(" ,I_NET_ONE_LEVEL,I_NET_TWO_LEVEL,I_NET_THREE_LEVEL");
			sql.append(" , I_DB_LEVEL, I_DISPATCH_LEVEL,I_ZIP_FLAG,I_ZIP_CNT");
			sql.append(" )");
			sql.append(" VALUES(?,?,?,?,?,?");
			sql.append(" ,?,?,?,?,?");
			sql.append(" ,?,?,?,?");
			sql.append(" ,?,?,?");
			sql.append(" ,?,?,?,?");
			sql.append(" ,?,?,?");
			sql.append(" ,?,?,?,?");
			sql.append(" )");
			List<List> datas = new ArrayList<List>();
			for(TbFaultBillEntity tbDelimitedRecordEntity : tempList ){
				List<Object> d = new ArrayList<Object>();
				d.add(tbDelimitedRecordEntity.getI_ID());
				d.add( tbSerialNoDao.getBillNo(TbSerialNo.Type.ARALM, String.valueOf(tbDelimitedRecordEntity.getCITY_CODE()), String.valueOf(tbDelimitedRecordEntity.getCITY_NAME())));
				d.add(tbDelimitedRecordEntity.getI_OBJ_LEVEL());
				d.add(tbDelimitedRecordEntity.getI_OBJ_TYPE());
				d.add(tbDelimitedRecordEntity.getI_NE_MODEL());
				d.add(tbDelimitedRecordEntity.getI_LARGE_CLASS());
				
				d.add(tbDelimitedRecordEntity.getI_SMALL_CLASS());
				d.add(tbDelimitedRecordEntity.getI_CYCLE());
				d.add(tbDelimitedRecordEntity.getS_TITLE());
				d.add(tbDelimitedRecordEntity.getS_MSG());
				d.add(tbDelimitedRecordEntity.getS_DELIMITED_CYCLE());
				
				d.add(tbDelimitedRecordEntity.getCITY_CODE());
				d.add(tbDelimitedRecordEntity.getCITY_NAME());
				d.add(tbDelimitedRecordEntity.getS_INST_ID());
				d.add(tbDelimitedRecordEntity.getS_INST_NAME());
				
				d.add(tbDelimitedRecordEntity.getI_FLAG());
				d.add(tbDelimitedRecordEntity.getI_CREATE_TIME());
				d.add(tbDelimitedRecordEntity.getI_UPDATE_TIME());
				
				d.add(tbDelimitedRecordEntity.getI_DISPATCH());
				d.add(tbDelimitedRecordEntity.getS_OUTSYS_NO());
				d.add(tbDelimitedRecordEntity.getI_SOURCE());
				d.add(tbDelimitedRecordEntity.getI_DB_TYPE());
				
				d.add(tbDelimitedRecordEntity.getI_NET_ONE_LEVEL());
				d.add(tbDelimitedRecordEntity.getI_NET_TWO_LEVEL());
				d.add(tbDelimitedRecordEntity.getI_NET_THREE_LEVEL());
				
				d.add(tbDelimitedRecordEntity.getI_DB_LEVEL());
				d.add(tbDelimitedRecordEntity.getI_DISPATCH_LEVEL());
				d.add(tbDelimitedRecordEntity.getI_ZIP_FLAG());
				d.add(tbDelimitedRecordEntity.getI_ZIP_CNT());
				
				datas.add(d);
			}
			logger.info("总大小:"+modFileListSize+",批量处理第"+(i+1)+"页,页大小:"+batchNum+",实际大小:"+datas.size()+",过滤前大小:"+tempList.size());
			this.updateByPrepareStatementBatch2(sql.toString(), datas, this.getDomain());
		}
	}
	
	@Override
	public List<Map<String, Object>> getCityFaultBillByInst(String S_INST_ID,String S_INST_NAME) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select  ");
		sql.append("   I_ID        as \"I_ID\"      ,S_INST_ID  as \"S_INST_ID\",S_INST_NAME  as \"S_INST_NAME\"");
		sql.append("   ,I_ZIP_CNT  as \"I_ZIP_CNT\"");
		sql.append(" from  TB_FAULT_BILL ");
		sql.append(" where S_INST_ID  = ? and S_INST_NAME = ? and I_FLAG = 1 and I_OBJ_TYPE = 1");
		List<Object> data = new ArrayList<Object>();
		data.add(S_INST_ID);
		data.add(S_INST_NAME);
		List<Map<String,Object>> tempResultList =  this.queryBySql(sql.toString(), data);
		return tempResultList;
	}

	@Override
	public List<Map<String, Object>> getZipFaultBillByInst(String S_INST_ID, String S_INST_NAME) {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer();
		sql.append(" select  ");
		sql.append("   I_ID        as \"I_ID\"      ,S_INST_ID  as \"S_INST_ID\",S_INST_NAME  as \"S_INST_NAME\"");
		sql.append("   ,I_ZIP_CNT  as \"I_ZIP_CNT\"");
		sql.append(" from  TB_FAULT_BILL ");
		sql.append(" where S_INST_ID  = ? and S_INST_NAME = ? and I_FLAG = 1 and I_ZIP_FLAG = 1");
		List<Object> data = new ArrayList<Object>();
		data.add(S_INST_ID);
		data.add(S_INST_NAME);
		List<Map<String,Object>> tempResultList =  this.queryBySql(sql.toString(), data);
		return tempResultList;
	}
	
	@Override
	public List<Map<String, Object>> getZipFaultBillByInst(String S_INST_ID, String S_INST_NAME,String CITY_CODE, String CITY_NAME) {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer();
		sql.append(" select  ");
		sql.append("   I_ID        as \"I_ID\"      ,S_INST_ID  as \"S_INST_ID\",S_INST_NAME  as \"S_INST_NAME\"");
		sql.append("   ,I_ZIP_CNT  as \"I_ZIP_CNT\"");
		sql.append(" from  TB_FAULT_BILL ");
		sql.append(" where ((S_INST_ID  = ? and S_INST_NAME = ?) or (S_INST_ID  = ? and S_INST_NAME = ?)) and I_FLAG = 1 and I_ZIP_FLAG = 1");
		List<Object> data = new ArrayList<Object>();
		data.add(S_INST_ID);
		data.add(S_INST_NAME);
		data.add(CITY_CODE);
		data.add(CITY_NAME);
		List<Map<String,Object>> tempResultList =  this.queryBySql(sql.toString(), data);
		return tempResultList;
	}
	
	@Override
	public void updateZipUpdateTimeById(List<Map<String,Object> > setIdList) {
		int batchNum  = DefaultStringUtil.toInteger((ConfigService.getPropertiyByKey("batchNum","300")));
		int modFileListSize = setIdList.size();
		int pageNo = modFileListSize/batchNum;
		if(modFileListSize % batchNum !=0){
			pageNo = pageNo + 1;
		}
		for(int i= 0;i<pageNo;i++ ){
			List<Map<String,Object>> tempList = new ArrayList<Map<String,Object>>();
			int runNum = (i+1)*batchNum > modFileListSize ? modFileListSize : (i+1)*batchNum;
			for(int j=i*batchNum;j<runNum;j++){
				tempList.add(setIdList.get(j));
			}
			
			StringBuffer sql = new StringBuffer();
			sql.append(" UPDATE TB_FAULT_BILL ");
			sql.append(" SET I_ZIP_CNT = ?,I_UPDATE_TIME = ?");
			sql.append(" WHERE I_ID = ?");
			List<List> datas = new ArrayList<List>();
			for(Map<String,Object> tempData : tempList ){
				List<Object> d = new ArrayList<Object>();
				d.add(tempData.get("I_ZIP_CNT"));
				d.add(DefaultStringUtil.getNowTime("yyyyMMddHHmmss"));
				d.add(tempData.get("I_ID"));
				datas.add(d);
			}
			logger.info("总大小:"+modFileListSize+",批量处理第"+(i+1)+"页,页大小:"+batchNum+",实际大小:"+datas.size()+",过滤前大小:"+tempList.size());
			this.updateByPrepareStatementBatch2(sql.toString(), datas, this.getDomain());
		}
	}

	@Override
	public List<Map<String, Object>> getFaultBillByInst(String S_INST_ID, String S_INST_NAME, Integer I_LARGE_CLASS,
			Integer I_SMALL_CLASS) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select  ");
		sql.append("   I_ID        as \"I_ID\"      ,S_INST_ID  as \"S_INST_ID\",S_INST_NAME  as \"S_INST_NAME\"");
		sql.append("   ,I_ZIP_CNT  as \"I_ZIP_CNT\"");
		sql.append(" from  TB_FAULT_BILL ");
		sql.append(" where S_INST_ID  = ? and S_INST_NAME = ? and I_FLAG = 1 and I_LARGE_CLASS = ? and I_SMALL_CLASS = ?");
		List<Object> data = new ArrayList<Object>();
		data.add(S_INST_ID);
		data.add(S_INST_NAME);
		data.add(I_LARGE_CLASS);
		data.add(I_SMALL_CLASS);
		List<Map<String,Object>> tempResultList =  this.queryBySql(sql.toString(), data);
		return tempResultList;
	}
	
	
}
