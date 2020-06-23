package com.cattsoft.eomsteam.iot.alarm.pending.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cattsoft.eomsteam.iot.alarm.pending.dao.TbAlarmPendingDao;
import com.cattsoft.eomsteam.iot.alarm.pending.entity.TbAlarmPendingEntity;
import com.cattsoft.eomsteam.mtoppub.ssh3InitUtil.ConfigService;
import com.cattsoft.eomsteam.uc.pub.db.DefaultStringUtil;
import com.cattsoft.eomsteam.uc.pub.db.uc_main2.dao.UCMainDao;

@Repository("com.cattsoft.eomsteam.iot.alarm.pending.dao.TbAlarmPendingDao")
public class TbAlarmPendingDaoImpl  extends UCMainDao implements TbAlarmPendingDao {
	
	@Override
	public Long getID() {
	    String id = this.callIdGenratorProc2("TB_ALARM_PENGDING",this.getDomain());
		return Long.valueOf(id);
	}

	@Override
	public void save(List<TbAlarmPendingEntity> alarmPendingEntityList) {
		// TODO Auto-generated method stub
		int batchNum  = DefaultStringUtil.toInteger((ConfigService.getPropertiyByKey("batchNum","300")));
		int modFileListSize = alarmPendingEntityList.size();
		int pageNo = modFileListSize/batchNum;
		if(modFileListSize % batchNum !=0){
			pageNo = pageNo + 1;
		}
		for(int i= 0;i<pageNo;i++ ){
			List<TbAlarmPendingEntity> tempList = new ArrayList<TbAlarmPendingEntity>();
			int runNum = (i+1)*batchNum > modFileListSize ? modFileListSize : (i+1)*batchNum;
			for(int j=i*batchNum;j<runNum;j++){
				tempList.add(alarmPendingEntityList.get(j));
			}
			
			StringBuffer sql = new StringBuffer();
			sql.append(" INSERT INTO ").append("TB_ALARM_PENGDING");
			sql.append(" (I_ID       ,I_REF_ID         ,I_OBJ_TYPE,S_NO,I_CYCLE");
			sql.append(" ,S_TITLE      ,S_MSG,S_DELIMITED_CYCLE");
			sql.append(" ,CITY_CODE,CITY_NAME     ,S_INST_ID,S_INST_NAME");
			sql.append(" ,I_FLAG,I_CREATE_TIME,I_UPDATE_TIME");
			sql.append(" ,I_OBJ_LEVEL,I_NE_MODEL     ,I_LARGE_CLASS,I_SMALL_CLASS");
			sql.append(" ,I_OBJ_SOURCE,I_OBJ_ID,I_ZIP_CNT");
			sql.append(" , I_DISPATCH_FLAG, I_RESET_FLAG, I_CANCEL_FLAG");
			sql.append(" )");
			sql.append(" VALUES(?,?,?,?,?");
			sql.append(" ,?,?,?");
			sql.append(" ,?,?,?,?");
			sql.append(" ,?,?,?");
			sql.append(" ,?,?,?,?");
			sql.append(" ,?,?,?");
			sql.append(" ,?,?,?");
			sql.append(" )");
			List<List> datas = new ArrayList<List>();
			for(TbAlarmPendingEntity tbDelimitedRecordEntity : tempList ){
				List<Object> d = new ArrayList<Object>();
				d.add(tbDelimitedRecordEntity.getI_ID());
				d.add(tbDelimitedRecordEntity.getI_REF_ID());
				d.add(tbDelimitedRecordEntity.getI_OBJ_TYPE());
				d.add(tbDelimitedRecordEntity.getS_NO());
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
				
				d.add(tbDelimitedRecordEntity.getI_OBJ_LEVEL());
				d.add(tbDelimitedRecordEntity.getI_NE_MODEL());
				
				d.add(tbDelimitedRecordEntity.getI_LARGE_CLASS());
				d.add(tbDelimitedRecordEntity.getI_SMALL_CLASS());
				d.add(tbDelimitedRecordEntity.getI_OBJ_SOURCE());
				d.add(tbDelimitedRecordEntity.getI_OBJ_ID());
				d.add(tbDelimitedRecordEntity.getI_ZIP_CNT());
				
				d.add(tbDelimitedRecordEntity.getI_DISPATCH_FLAG());
				d.add(tbDelimitedRecordEntity.getI_RESET_FLAG());
				d.add(tbDelimitedRecordEntity.getI_CANCEL_FLAG());
				datas.add(d);
			}
			logger.info("总大小:"+modFileListSize+",批量处理第"+(i+1)+"页,页大小:"+batchNum+",实际大小:"+datas.size()+",过滤前大小:"+tempList.size());
			this.updateByPrepareStatementBatch2(sql.toString(), datas, this.getDomain());
		}
	}

	@Override
	public List<Map<String, Object>> getAlarmPendingListByRefId(Long I_REF_ID,String S_DELIMITED_CYCLE) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select  ");
		sql.append("   I_ID        as \"I_ID\"      ,S_INST_ID  as \"S_INST_ID\"");
		sql.append(" from  TB_ALARM_PENGDING ");
		sql.append(" where I_REF_ID  = ? and S_DELIMITED_CYCLE = ? and I_FLAG = 1");
		List<Object> data = new ArrayList<Object>();
		data.add(I_REF_ID);
		data.add(S_DELIMITED_CYCLE);
		List<Map<String,Object>> tempResultList =  this.queryBySql(sql.toString(), data);
		return tempResultList;
	}
	

	@Override
	public List<Map<String, Object>> getAlarmTimeOutPendingListByRefId(Long I_REF_ID) {
		Integer alarm_pending_closed = DefaultStringUtil.toInteger(ConfigService.getPropertiyByKey("alarm_pending_closed", "-2"));
		String ts  = DefaultStringUtil.getStrNowTime("yyyy-MM-dd");
		String nextTime = DefaultStringUtil.getNextDate(ts, alarm_pending_closed).replace("-", "").replace(":", "")+"235959";
		StringBuffer sql = new StringBuffer();
		sql.append(" select  ");
		sql.append("   I_ID               as \"I_ID\"      ,S_INST_ID  as \"S_INST_ID\"");
		sql.append("  ,S_DELIMITED_CYCLE  as \"S_DELIMITED_CYCLE\"");
		sql.append(" from  TB_ALARM_PENGDING ");
		sql.append(" where I_REF_ID  = ?  and I_FLAG = 1 and I_UPDATE_TIME < ?");
		List<Object> data = new ArrayList<Object>();
		data.add(I_REF_ID);
		data.add(DefaultStringUtil.toLong(nextTime));
		
		List<Map<String,Object>> tempResultList =  this.queryBySql(sql.toString(), data);
		return tempResultList;
	}
	
	@Override
	public void  updateAlarmZipCntByPendingId(List<Map<String,Object>> setIdList){
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
			sql.append(" UPDATE TB_ALARM_PENGDING ");
			sql.append(" SET I_ZIP_CNT = I_ZIP_CNT +1 ,I_UPDATE_TIME = ?");
			sql.append(" WHERE I_ID = ?");
			List<List> datas = new ArrayList<List>();
			for(Map<String,Object> tempData : tempList ){
				List<Object> d = new ArrayList<Object>();
				d.add(tempData.get("I_CREATE_TIME"));
				d.add(tempData.get("I_ID"));
				datas.add(d);
			}
			logger.info("总大小:"+modFileListSize+",批量处理第"+(i+1)+"页,页大小:"+batchNum+",实际大小:"+datas.size()+",过滤前大小:"+tempList.size());
			this.updateByPrepareStatementBatch2(sql.toString(), datas, this.getDomain());
		}
	}
	
	
	@Override
	public void  updateAlarmPendingClosedByPendingId(List<Map<String,Object>> setIdList){
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
			sql.append(" UPDATE TB_ALARM_PENGDING ");
			sql.append(" SET I_FLAG = 2 ,I_CLOSED_TIME = ?");
			sql.append(" WHERE I_ID = ?");
			List<List> datas = new ArrayList<List>();
			for(Map<String,Object> tempData : tempList ){
				List<Object> d = new ArrayList<Object>();
				d.add(DefaultStringUtil.getStrNowTime("yyyyMMddHHmmss"));
				d.add(tempData.get("I_ID"));
				datas.add(d);
			}
			logger.info("总大小:"+modFileListSize+",批量处理第"+(i+1)+"页,页大小:"+batchNum+",实际大小:"+datas.size()+",过滤前大小:"+tempList.size());
			this.updateByPrepareStatementBatch2(sql.toString(), datas, this.getDomain());
		}
	}

}
