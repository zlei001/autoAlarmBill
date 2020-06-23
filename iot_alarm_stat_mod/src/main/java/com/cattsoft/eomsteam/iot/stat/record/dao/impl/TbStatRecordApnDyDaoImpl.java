package com.cattsoft.eomsteam.iot.stat.record.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cattsoft.eomsteam.iot.stat.record.dao.TbStatRecordApnDyDao;
import com.cattsoft.eomsteam.iot.stat.record.entity.TbStatRecordApnDyEntity;
import com.cattsoft.eomsteam.mtoppub.ssh3InitUtil.ConfigService;
import com.cattsoft.eomsteam.uc.pub.db.DefaultStringUtil;
import com.cattsoft.eomsteam.uc.pub.db.uc_main2.dao.UCMainDao;

@Repository("com.cattsoft.eomsteam.iot.stat.record.dao.TbStatRecordApnDyDao")
public class TbStatRecordApnDyDaoImpl  extends UCMainDao implements TbStatRecordApnDyDao {
	
	@Override
	public Long getID(){
		String id = this.callIdGenratorProc2("TB_STAT_RECORD_APN_DY");
		return DefaultStringUtil.toLong(id);
	}
	@Override
	public List<Map<String, Object>> getTbStatRecordByKey(String STATIS_TIME) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select  ");
		sql.append("   I_ID        as \"I_ID\"      ,APN  as \"APN\",CUSTOMER_NAME  as \"CUSTOMER_NAME\"");
		sql.append(" , I_NE_MODEL, I_LARGE_CLASS, I_SMALL_CLASS");
		sql.append(" , STATIS_TIME");
		sql.append(" from  TB_STAT_RECORD_APN_DY ");
		sql.append(" where STATIS_TIME  = ? ");
		List<Object> data = new ArrayList<Object>();
		data.add(STATIS_TIME);
		List<Map<String,Object>> tempResultList =  this.queryBySql(sql.toString(), data);
		return tempResultList;
	}
	
	@Override
	public void updateDelimitedStatRecordByKey(List<TbStatRecordApnDyEntity> setIdList) {
		int batchNum  = DefaultStringUtil.toInteger((ConfigService.getPropertiyByKey("batchNum","300")));
		int modFileListSize = setIdList.size();
		int pageNo = modFileListSize/batchNum;
		if(modFileListSize % batchNum !=0){
			pageNo = pageNo + 1;
		}
		for(int i= 0;i<pageNo;i++ ){
			List<TbStatRecordApnDyEntity> tempList = new ArrayList<TbStatRecordApnDyEntity>();
			int runNum = (i+1)*batchNum > modFileListSize ? modFileListSize : (i+1)*batchNum;
			for(int j=i*batchNum;j<runNum;j++){
				tempList.add(setIdList.get(j));
			}
			
			StringBuffer sql = new StringBuffer();
			sql.append(" UPDATE TB_STAT_RECORD_APN_DY ");
			sql.append(" SET  I_DELIMITED_CNT= ? ,I_DELIMITED_TIME = ?");
			sql.append(" WHERE I_ID = ?");
			List<List> datas = new ArrayList<List>();
			for(TbStatRecordApnDyEntity tempData : tempList ){
				List<Object> d = new ArrayList<Object>();
				d.add(tempData.getI_DELIMITED_CNT());
				d.add(DefaultStringUtil.getNowTime("yyyyMMddHHmmss"));
				d.add(tempData.getI_ID());
				datas.add(d);
			}
			logger.info("总大小:"+modFileListSize+",批量处理第"+(i+1)+"页,页大小:"+batchNum+",实际大小:"+datas.size()+",过滤前大小:"+tempList.size());
			this.updateByPrepareStatementBatch2(sql.toString(), datas, this.getDomain());
		}
	}

	@Override
	public void updateAlarmStatRecordByKey(List<TbStatRecordApnDyEntity> setIdList) {
		// TODO Auto-generated method stub
		int batchNum  = DefaultStringUtil.toInteger((ConfigService.getPropertiyByKey("batchNum","300")));
		int modFileListSize = setIdList.size();
		int pageNo = modFileListSize/batchNum;
		if(modFileListSize % batchNum !=0){
			pageNo = pageNo + 1;
		}
		for(int i= 0;i<pageNo;i++ ){
			List<TbStatRecordApnDyEntity> tempList = new ArrayList<TbStatRecordApnDyEntity>();
			int runNum = (i+1)*batchNum > modFileListSize ? modFileListSize : (i+1)*batchNum;
			for(int j=i*batchNum;j<runNum;j++){
				tempList.add(setIdList.get(j));
			}
			
			StringBuffer sql = new StringBuffer();
			sql.append(" UPDATE TB_STAT_RECORD_APN_DY ");
			sql.append(" SET  I_ALARM_CNT= ?  ,I_ALARM_TIME = ?");
			sql.append(" WHERE I_ID = ?");
			List<List> datas = new ArrayList<List>();
			for(TbStatRecordApnDyEntity tempData : tempList ){
				List<Object> d = new ArrayList<Object>();
				d.add(tempData.getI_ALARM_CNT());
				d.add(DefaultStringUtil.getNowTime("yyyyMMddHHmmss"));
				d.add(tempData.getI_ID());
				datas.add(d);
			}
			logger.info("总大小:"+modFileListSize+",批量处理第"+(i+1)+"页,页大小:"+batchNum+",实际大小:"+datas.size()+",过滤前大小:"+tempList.size());
			this.updateByPrepareStatementBatch2(sql.toString(), datas, this.getDomain());
		}
	}
	
	@Override
	public void updateAlarmPendingStatRecordByKey(List<TbStatRecordApnDyEntity> setIdList) {
		// TODO Auto-generated method stub
		int batchNum  = DefaultStringUtil.toInteger((ConfigService.getPropertiyByKey("batchNum","300")));
		int modFileListSize = setIdList.size();
		int pageNo = modFileListSize/batchNum;
		if(modFileListSize % batchNum !=0){
			pageNo = pageNo + 1;
		}
		for(int i= 0;i<pageNo;i++ ){
			List<TbStatRecordApnDyEntity> tempList = new ArrayList<TbStatRecordApnDyEntity>();
			int runNum = (i+1)*batchNum > modFileListSize ? modFileListSize : (i+1)*batchNum;
			for(int j=i*batchNum;j<runNum;j++){
				tempList.add(setIdList.get(j));
			}
			
			StringBuffer sql = new StringBuffer();
			sql.append(" UPDATE TB_STAT_RECORD_APN_DY ");
			sql.append(" SET  I_ALARM_PENDING_CNT= ?  ,I_ALARM_PENDING_TIME = ?");
			sql.append(" WHERE I_ID = ?");
			List<List> datas = new ArrayList<List>();
			for(TbStatRecordApnDyEntity tempData : tempList ){
				List<Object> d = new ArrayList<Object>();
				d.add(tempData.getI_ALARM_PENDING_CNT());
				d.add(DefaultStringUtil.getNowTime("yyyyMMddHHmmss"));
				d.add(tempData.getI_ID());
				datas.add(d);
			}
			logger.info("总大小:"+modFileListSize+",批量处理第"+(i+1)+"页,页大小:"+batchNum+",实际大小:"+datas.size()+",过滤前大小:"+tempList.size());
			this.updateByPrepareStatementBatch2(sql.toString(), datas, this.getDomain());
		}
	}


	
	@Override
	public void updateBillStatRecordByKey(List<TbStatRecordApnDyEntity> setIdList) {
		// TODO Auto-generated method stub
		int batchNum  = DefaultStringUtil.toInteger((ConfigService.getPropertiyByKey("batchNum","300")));
		int modFileListSize = setIdList.size();
		int pageNo = modFileListSize/batchNum;
		if(modFileListSize % batchNum !=0){
			pageNo = pageNo + 1;
		}
		for(int i= 0;i<pageNo;i++ ){
			List<TbStatRecordApnDyEntity> tempList = new ArrayList<TbStatRecordApnDyEntity>();
			int runNum = (i+1)*batchNum > modFileListSize ? modFileListSize : (i+1)*batchNum;
			for(int j=i*batchNum;j<runNum;j++){
				tempList.add(setIdList.get(j));
			}
			
			StringBuffer sql = new StringBuffer();
			sql.append(" UPDATE TB_STAT_RECORD_APN_DY ");
			sql.append(" SET  I_BILL_CNT= ? ,I_BILL_TIME = ?");
			sql.append(" WHERE I_ID = ?");
			List<List> datas = new ArrayList<List>();
			for(TbStatRecordApnDyEntity tempData : tempList ){
				List<Object> d = new ArrayList<Object>();
				d.add(tempData.getI_BILL_CNT());
				d.add(DefaultStringUtil.getNowTime("yyyyMMddHHmmss"));
				d.add(tempData.getI_ID());
				datas.add(d);
			}
			logger.info("总大小:"+modFileListSize+",批量处理第"+(i+1)+"页,页大小:"+batchNum+",实际大小:"+datas.size()+",过滤前大小:"+tempList.size());
			this.updateByPrepareStatementBatch2(sql.toString(), datas, this.getDomain());
		}
	}
	
	@Override
	public void save(List<TbStatRecordApnDyEntity> tbStatRecordEntityList) {
		int batchNum  = DefaultStringUtil.toInteger((ConfigService.getPropertiyByKey("batchNum","300")));
		int modFileListSize = tbStatRecordEntityList.size();
		int pageNo = modFileListSize/batchNum;
		if(modFileListSize % batchNum !=0){
			pageNo = pageNo + 1;
		}
		for(int i= 0;i<pageNo;i++ ){
			List<TbStatRecordApnDyEntity> tempList = new ArrayList<TbStatRecordApnDyEntity>();
			int runNum = (i+1)*batchNum > modFileListSize ? modFileListSize : (i+1)*batchNum;
			for(int j=i*batchNum;j<runNum;j++){
				tempList.add(tbStatRecordEntityList.get(j));
			}
			
			StringBuffer sql = new StringBuffer();
			sql.append(" INSERT INTO ").append("TB_STAT_RECORD_APN_DY");
			sql.append(" (I_ID                 ,APN              ,CUSTOMER_NAME");
			sql.append(" ,I_ALARM_CNT          ,I_ALARM_END_CNT        ,I_ALARM_TIME");
			sql.append(" ,I_ALARM_PENDING_CNT  ,I_ALARM_PENDING_END_CNT,I_ALARM_PENDING_TIME");
			sql.append(" ,I_BILL_CNT           ,I_BILL_END_CNT         ,I_BILL_TIME");
			sql.append(" ,I_DELIMITED_CNT      ,I_DELIMITED_END_CNT    ,I_DELIMITED_TIME");
			sql.append(" ,I_LARGE_CLASS        ,I_NE_MODEL             ,I_SMALL_CLASS,STATIS_TIME");
			sql.append(" )");
			sql.append(" VALUES(?,?,?");
			sql.append(" ,?,?,?");
			sql.append(" ,?,?,?");
			sql.append(" ,?,?,?");
			sql.append(" ,?,?,?");
			sql.append(" ,?,?,?,?");
			sql.append(" )");
			List<List> datas = new ArrayList<List>();
			for(TbStatRecordApnDyEntity tbStatRecordCityDyEntity : tempList ){
				List<Object> d = new ArrayList<Object>();
				d.add(tbStatRecordCityDyEntity.getI_ID());
				d.add(tbStatRecordCityDyEntity.getApn());
				d.add(tbStatRecordCityDyEntity.getCustomer_name());
				
				d.add(tbStatRecordCityDyEntity.getI_ALARM_CNT());
				d.add(tbStatRecordCityDyEntity.getI_ALARM_END_CNT());
				d.add(tbStatRecordCityDyEntity.getI_ALARM_TIME());
				
				d.add(tbStatRecordCityDyEntity.getI_ALARM_PENDING_CNT());
				d.add(tbStatRecordCityDyEntity.getI_ALARM_PENDING_END_CNT());
				d.add(tbStatRecordCityDyEntity.getI_ALARM_PENDING_TIME());
				
				d.add(tbStatRecordCityDyEntity.getI_BILL_CNT());
				d.add(tbStatRecordCityDyEntity.getI_BILL_END_CNT());
				d.add(tbStatRecordCityDyEntity.getI_BILL_TIME());
				
				d.add(tbStatRecordCityDyEntity.getI_DELIMITED_CNT());
				d.add(tbStatRecordCityDyEntity.getI_DELIMITED_END_CNT());
				d.add(tbStatRecordCityDyEntity.getI_DELIMITED_TIME());
				
				d.add(tbStatRecordCityDyEntity.getI_LARGE_CLASS());
				d.add(tbStatRecordCityDyEntity.getI_NE_MODEL());
				d.add(tbStatRecordCityDyEntity.getI_SMALL_CLASS());
				d.add(tbStatRecordCityDyEntity.getSTATIS_TIME());
				datas.add(d);
			}
			logger.info("总大小:"+modFileListSize+",批量处理第"+(i+1)+"页,页大小:"+batchNum+",实际大小:"+datas.size()+",过滤前大小:"+tempList.size());
			this.updateByPrepareStatementBatch2(sql.toString(), datas, this.getDomain());
		}
	}

	@Override
	public void updateDelimitedEndStatRecordByKey(List<TbStatRecordApnDyEntity> setIdList) {
		// TODO Auto-generated method stub
		int batchNum  = DefaultStringUtil.toInteger((ConfigService.getPropertiyByKey("batchNum","300")));
		int modFileListSize = setIdList.size();
		int pageNo = modFileListSize/batchNum;
		if(modFileListSize % batchNum !=0){
			pageNo = pageNo + 1;
		}
		for(int i= 0;i<pageNo;i++ ){
			List<TbStatRecordApnDyEntity> tempList = new ArrayList<TbStatRecordApnDyEntity>();
			int runNum = (i+1)*batchNum > modFileListSize ? modFileListSize : (i+1)*batchNum;
			for(int j=i*batchNum;j<runNum;j++){
				tempList.add(setIdList.get(j));
			}
			
			StringBuffer sql = new StringBuffer();
			sql.append(" UPDATE TB_STAT_RECORD_APN_DY ");
			sql.append(" SET  I_DELIMITED_END_CNT= ? ,I_DELIMITED_TIME = ?");
			sql.append(" WHERE I_ID = ?");
			List<List> datas = new ArrayList<List>();
			for(TbStatRecordApnDyEntity tempData : tempList ){
				List<Object> d = new ArrayList<Object>();
				d.add(tempData.getI_DELIMITED_END_CNT());
				d.add(DefaultStringUtil.getNowTime("yyyyMMddHHmmss"));
				d.add(tempData.getI_ID());
				datas.add(d);
			}
			logger.info("总大小:"+modFileListSize+",批量处理第"+(i+1)+"页,页大小:"+batchNum+",实际大小:"+datas.size()+",过滤前大小:"+tempList.size());
			this.updateByPrepareStatementBatch2(sql.toString(), datas, this.getDomain());
		}
	}

	@Override
	public void updateAlarmStatEndRecordByKey(List<TbStatRecordApnDyEntity> setIdList) {
		// TODO Auto-generated method stub
		int batchNum  = DefaultStringUtil.toInteger((ConfigService.getPropertiyByKey("batchNum","300")));
		int modFileListSize = setIdList.size();
		int pageNo = modFileListSize/batchNum;
		if(modFileListSize % batchNum !=0){
			pageNo = pageNo + 1;
		}
		for(int i= 0;i<pageNo;i++ ){
			List<TbStatRecordApnDyEntity> tempList = new ArrayList<TbStatRecordApnDyEntity>();
			int runNum = (i+1)*batchNum > modFileListSize ? modFileListSize : (i+1)*batchNum;
			for(int j=i*batchNum;j<runNum;j++){
				tempList.add(setIdList.get(j));
			}
			
			StringBuffer sql = new StringBuffer();
			sql.append(" UPDATE TB_STAT_RECORD_APN_DY ");
			sql.append(" SET  I_ALARM_END_CNT= ? ,I_ALARM_TIME = ?");
			sql.append(" WHERE I_ID = ?");
			List<List> datas = new ArrayList<List>();
			for(TbStatRecordApnDyEntity tempData : tempList ){
				List<Object> d = new ArrayList<Object>();
				d.add(tempData.getI_ALARM_END_CNT());
				d.add(DefaultStringUtil.getNowTime("yyyyMMddHHmmss"));
				d.add(tempData.getI_ID());
				datas.add(d);
			}
			logger.info("总大小:"+modFileListSize+",批量处理第"+(i+1)+"页,页大小:"+batchNum+",实际大小:"+datas.size()+",过滤前大小:"+tempList.size());
			this.updateByPrepareStatementBatch2(sql.toString(), datas, this.getDomain());
		}
	}

	@Override
	public void updateBillStatEndRecordByKey(List<TbStatRecordApnDyEntity> setIdList) {
		// TODO Auto-generated method stub
		int batchNum  = DefaultStringUtil.toInteger((ConfigService.getPropertiyByKey("batchNum","300")));
		int modFileListSize = setIdList.size();
		int pageNo = modFileListSize/batchNum;
		if(modFileListSize % batchNum !=0){
			pageNo = pageNo + 1;
		}
		for(int i= 0;i<pageNo;i++ ){
			List<TbStatRecordApnDyEntity> tempList = new ArrayList<TbStatRecordApnDyEntity>();
			int runNum = (i+1)*batchNum > modFileListSize ? modFileListSize : (i+1)*batchNum;
			for(int j=i*batchNum;j<runNum;j++){
				tempList.add(setIdList.get(j));
			}
			
			StringBuffer sql = new StringBuffer();
			sql.append(" UPDATE TB_STAT_RECORD_APN_DY ");
			sql.append(" SET  I_BILL_END_CNT= ? ,I_BILL_TIME = ?");
			sql.append(" WHERE I_ID = ?");
			List<List> datas = new ArrayList<List>();
			for(TbStatRecordApnDyEntity tempData : tempList ){
				List<Object> d = new ArrayList<Object>();
				d.add(tempData.getI_BILL_END_CNT());
				d.add(DefaultStringUtil.getNowTime("yyyyMMddHHmmss"));
				d.add(tempData.getI_ID());
				datas.add(d);
			}
			logger.info("总大小:"+modFileListSize+",批量处理第"+(i+1)+"页,页大小:"+batchNum+",实际大小:"+datas.size()+",过滤前大小:"+tempList.size());
			this.updateByPrepareStatementBatch2(sql.toString(), datas, this.getDomain());
		}
	}

	@Override
	public void updateAlarmPendingStatEndRecordByKey(List<TbStatRecordApnDyEntity> setIdList) {
		// TODO Auto-generated method stub
		int batchNum  = DefaultStringUtil.toInteger((ConfigService.getPropertiyByKey("batchNum","300")));
		int modFileListSize = setIdList.size();
		int pageNo = modFileListSize/batchNum;
		if(modFileListSize % batchNum !=0){
			pageNo = pageNo + 1;
		}
		for(int i= 0;i<pageNo;i++ ){
			List<TbStatRecordApnDyEntity> tempList = new ArrayList<TbStatRecordApnDyEntity>();
			int runNum = (i+1)*batchNum > modFileListSize ? modFileListSize : (i+1)*batchNum;
			for(int j=i*batchNum;j<runNum;j++){
				tempList.add(setIdList.get(j));
			}
			
			StringBuffer sql = new StringBuffer();
			sql.append(" UPDATE TB_STAT_RECORD_APN_DY ");
			sql.append(" SET  I_ALARM_PENDING_END_CNT= ? ,I_ALARM_PENDING_TIME = ?");
			sql.append(" WHERE I_ID = ?");
			List<List> datas = new ArrayList<List>();
			for(TbStatRecordApnDyEntity tempData : tempList ){
				List<Object> d = new ArrayList<Object>();
				d.add(tempData.getI_BILL_END_CNT());
				d.add(DefaultStringUtil.getNowTime("yyyyMMddHHmmss"));
				d.add(tempData.getI_ID());
				datas.add(d);
			}
			logger.info("总大小:"+modFileListSize+",批量处理第"+(i+1)+"页,页大小:"+batchNum+",实际大小:"+datas.size()+",过滤前大小:"+tempList.size());
			this.updateByPrepareStatementBatch2(sql.toString(), datas, this.getDomain());
		}
	}
}
