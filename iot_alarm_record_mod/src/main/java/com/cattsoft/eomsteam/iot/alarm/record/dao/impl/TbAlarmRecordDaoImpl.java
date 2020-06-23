package com.cattsoft.eomsteam.iot.alarm.record.dao.impl;

import java.util.*;

import oracle.jdbc.OracleTypeMetaData;
import org.springframework.stereotype.Repository;

import com.cattsoft.eomsteam.iot.alarm.record.dao.TbAlarmRecordDao;
import com.cattsoft.eomsteam.iot.alarm.record.entity.TbAlarmRecordEntity;
import com.cattsoft.eomsteam.mtoppub.ssh3InitUtil.ConfigService;
import com.cattsoft.eomsteam.uc.pub.db.DefaultStringUtil;
import com.cattsoft.eomsteam.uc.pub.db.uc_main2.dao.UCMainDao;

@Repository("com.cattsoft.eomsteam.iot.alarm.record.dao.TbAlarmRecordDao")
public class TbAlarmRecordDaoImpl  extends UCMainDao implements TbAlarmRecordDao {
	

	@Override
	public Long getID() {
	    String id = this.callIdGenratorProc2("TB_ALARM_RECORD",this.getDomain());
		return Long.valueOf(id);
	}

	
	@Override
	public List<Map<String, Object>> getAlarmPendingListByRefId(Long I_REF_ID,String S_DELIMITED_CYCLE) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select  ");
		sql.append("   I_ID        as \"I_ID\"      ,S_INST_ID  as \"S_INST_ID\"");
		sql.append(" from  TB_ALARM_RECORD ");
		sql.append(" where I_REF_ID  = ? and S_DELIMITED_CYCLE = ? and I_FLAG = 1");
		List<Object> data = new ArrayList<Object>();
		data.add(I_REF_ID);
		data.add(S_DELIMITED_CYCLE);
		List<Map<String,Object>> tempResultList =  this.queryBySql(sql.toString(), data);
		return tempResultList;
	}

	

	@Override
	public Map<String, Object> getAlarmPendingCount(String sql) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> dataList = this.queryBySql(sql);
		if(dataList.size() >0){
			return dataList.get(0);
		}
		return null;
	}

	@Override
	public List<Map<String, Object>> getAlarmPendingList(String sql) {
		// TODO Auto-generated method stub
		return this.queryBySql(sql);
	}


	@Override
	public void save(List<TbAlarmRecordEntity> alarmPendingEntityList) {
		int batchNum  = DefaultStringUtil.toInteger((ConfigService.getPropertiyByKey("batchNum","200")));
		int modFileListSize = alarmPendingEntityList.size();
		int pageNo = modFileListSize/batchNum;
		if(modFileListSize % batchNum !=0){
			pageNo = pageNo + 1;
		}
		for(int i= 0;i<pageNo;i++ ){
			List<TbAlarmRecordEntity> tempList = new ArrayList<TbAlarmRecordEntity>();
			int runNum = (i+1)*batchNum > modFileListSize ? modFileListSize : (i+1)*batchNum;
			for(int j=i*batchNum;j<runNum;j++){
				tempList.add(alarmPendingEntityList.get(j));
			}
			
			StringBuffer sql = new StringBuffer();
			sql.append(" INSERT INTO ").append("TB_ALARM_RECORD");
			sql.append(" (I_ID       ,I_REF_ID         ,I_OBJ_TYPE,S_NO,I_CYCLE");
			sql.append(" ,S_TITLE      ,S_MSG,S_DELIMITED_CYCLE");
			sql.append(" ,CITY_CODE,CITY_NAME     ,S_INST_ID,S_INST_NAME");
			sql.append(" ,I_FLAG,I_CREATE_TIME,I_UPDATE_TIME");
			sql.append(" ,I_OBJ_LEVEL,I_NE_MODEL     ,I_LARGE_CLASS,I_SMALL_CLASS");
			sql.append(" ,I_OBJ_SOURCE,STATIS_TIME");
			sql.append(" )");
			sql.append(" VALUES(?,?,?,?,?");
			sql.append(" ,?,?,?");
			sql.append(" ,?,?,?,?");
			sql.append(" ,?,?,?");
			sql.append(" ,?,?,?,?");
			sql.append(" ,?,?");
			sql.append(" )");
			List<List> datas = new ArrayList<List>();
			for(TbAlarmRecordEntity tbDelimitedRecordEntity : tempList ){
				List<Object> d = new ArrayList<Object>();
				d.add(tbDelimitedRecordEntity.getI_ID());
				d.add(tbDelimitedRecordEntity.getI_REF_ID());
				d.add(tbDelimitedRecordEntity.getI_OBJ_TYPE());
				d.add(tbDelimitedRecordEntity.getS_NO());
				d.add(tbDelimitedRecordEntity.getI_CYCLE());
				d.add(tbDelimitedRecordEntity.getS_TITLE().replace("or", " 或 ").replace("and", " 与  "));
				d.add(tbDelimitedRecordEntity.getS_MSG().replace("or", " 或 ").replace("and", " 与  "));
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
				d.add(tbDelimitedRecordEntity.getSTATIS_TIME());
				datas.add(d);
			}
			logger.info("总大小:"+modFileListSize+",批量处理第"+(i+1)+"页,页大小:"+batchNum+",实际大小:"+datas.size()+",过滤前大小:"+tempList.size());
			this.updateByPrepareStatementBatch2(sql.toString(), datas, this.getDomain());
		}
	}

	@Override
	public void  updateRunningAlarmPendingEndById(String idInStr){
		StringBuffer sql = new StringBuffer();
		String[] splite = idInStr.split(",");
		sql.append(" update  TB_ALARM_RECORD ");
		sql.append(" set I_FLAG = 2,I_UPDATE_TIME=? ,FINISHED_STATIS_TIME= ?");
		sql.append(" where I_ID  in(").append(idInStr).append(")");
		List<Object> data = new ArrayList<Object>();
		data.add(DefaultStringUtil.getNowTime("yyyyMMddHHmmss"));
		data.add(""+DefaultStringUtil.getNowTime("yyyyMMdd"));
		this.updateByPrepareStatement(sql.toString(), data, this.getDomain());
	}

	@Override
	public void  updateRunningAlarmPendingEndByIds(List<String> strsToList ){

		int batchNum  = DefaultStringUtil.toInteger((ConfigService.getPropertiyByKey("batchNum","200")));
		int modFileListSize = strsToList.size();
		int pageNo = modFileListSize/batchNum;
		if(modFileListSize % batchNum !=0){
			pageNo = pageNo + 1;
		}
		for(int i= 0;i<pageNo;i++ ){
			List<String> tempList = new ArrayList<String>();
			int runNum = (i+1)*batchNum > modFileListSize ? modFileListSize : (i+1)*batchNum;
			for(int j=i*batchNum;j<runNum;j++){
				tempList.add(strsToList.get(j));
			}

		StringBuffer sql = new StringBuffer();
		sql.append(" update  TB_ALARM_RECORD ");
		sql.append(" set I_FLAG = 2,I_UPDATE_TIME=? ,FINISHED_STATIS_TIME= ?");
		sql.append(" where I_ID  = ? ");
		List<List> datas = new ArrayList<List>();
			for(String tbDelimitedRecordEntity : tempList ) {
				List<Object> d = new ArrayList<Object>();
				d.add(DefaultStringUtil.getNowTime("yyyyMMddHHmmss"));
				d.add(DefaultStringUtil.getNowTime("yyyyMMdd"));
				d.add(Integer.parseInt(tbDelimitedRecordEntity));
				datas.add(d);
			}

		logger.info("总大小:"+modFileListSize+",批量处理第"+(i+1)+"页,页大小:"+batchNum+",实际大小:"+datas.size()+",过滤前大小:"+tempList.size());
		this.updateByPrepareStatementBatch2(sql.toString(), datas, this.getDomain());
		}
	}







}
