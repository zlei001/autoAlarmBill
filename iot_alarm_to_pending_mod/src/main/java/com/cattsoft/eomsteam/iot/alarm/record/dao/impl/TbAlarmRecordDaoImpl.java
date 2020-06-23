package com.cattsoft.eomsteam.iot.alarm.record.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cattsoft.eomsteam.iot.alarm.record.dao.TbAlarmRecordDao;
import com.cattsoft.eomsteam.mtoppub.ssh3InitUtil.ConfigService;
import com.cattsoft.eomsteam.uc.pub.db.DefaultStringUtil;
import com.cattsoft.eomsteam.uc.pub.db.uc_main2.dao.UCMainDao;

@Repository("com.cattsoft.eomsteam.iot.alarm.record.dao.TbAlarmRecordDao")
public class TbAlarmRecordDaoImpl  extends UCMainDao implements TbAlarmRecordDao {
	
	@Override
	public List<Map<String, Object>> getAlarmRecordListByRefId(Long I_REF_ID,String S_DELIMITED_CYCLE) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select  ");
		sql.append("   I_ID               as \"I_ID\"             ,S_NO           as \"S_NO\"");
		sql.append("  ,I_REF_ID           as \"I_REF_ID\"         ,I_OBJ_LEVEL    as \"I_OBJ_LEVEL\"");
		sql.append("  ,I_OBJ_TYPE         as \"I_OBJ_TYPE\"       ,I_NE_MODEL     as \"I_NE_MODEL\"");
		sql.append("  ,I_LARGE_CLASS      as \"I_LARGE_CLASS\"    ,I_SMALL_CLASS  as \"I_SMALL_CLASS\"");
		sql.append("  ,I_CYCLE            as \"I_CYCLE\"          ,S_TITLE        as \"S_TITLE\"");
		sql.append("  ,S_DELIMITED_CYCLE  as \"S_DELIMITED_CYCLE\" ,S_MSG         as \"S_MSG\"");
		sql.append("  ,CITY_CODE          as \"CITY_CODE\"        ,CITY_NAME      as \"CITY_NAME\"");
		sql.append("  ,S_INST_ID          as \"S_INST_ID\"        ,S_INST_NAME    as \"S_INST_NAME\"");
		sql.append("  ,I_FLAG             as \"I_FLAG\"           ,I_CREATE_TIME  as \"I_CREATE_TIME\"");
		sql.append("  ,I_UPDATE_TIME      as \"I_UPDATE_TIME\"    ,I_OBJ_SOURCE   as \"I_OBJ_SOURCE\"");
		sql.append(" from  TB_ALARM_RECORD ");
		sql.append(" where  I_FLAG = 1 and I_ALARM_PENDING_ID is null");
		sql.append(" and I_REF_ID = ? and S_DELIMITED_CYCLE=?");
		List<Object> data = new ArrayList<Object>();
		data.add(I_REF_ID);
		data.add(S_DELIMITED_CYCLE);
		List<Map<String,Object>> tempResultList =  this.queryBySql(sql.toString(), data);
		return tempResultList;
	}


	@Override
	public void  updateAlarmRecordSetPendingIdByRecordId(List<Map<String,Object>> setIdList){
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
			sql.append(" UPDATE TB_ALARM_RECORD ");
			sql.append(" SET I_ALARM_PENDING_ID = ? ,I_UPDATE_TIME = ?");
			sql.append(" WHERE I_ID = ?");
			List<List> datas = new ArrayList<List>();
			for(Map<String,Object> tempData : tempList ){
				List<Object> d = new ArrayList<Object>();
				d.add(tempData.get("I_ALARM_PENDING_ID"));
				d.add(DefaultStringUtil.getNowTime("yyyyMMddHHmmss"));
				d.add(tempData.get("I_ID"));
				datas.add(d);
			}
			logger.info("总大小:"+modFileListSize+",批量处理第"+(i+1)+"页,页大小:"+batchNum+",实际大小:"+datas.size()+",过滤前大小:"+tempList.size());
			this.updateByPrepareStatementBatch2(sql.toString(), datas, this.getDomain());
		}
	}


	@Override
	public void closedAlarmRecordByPendingId(List<Map<String, Object>> setIdList) {
		// TODO Auto-generated method stub
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
			sql.append(" UPDATE TB_ALARM_RECORD ");
			sql.append(" SET  I_FLAG= 2 ,I_UPDATE_TIME = ?");
			sql.append(" WHERE I_ALARM_PENDING_ID = ? AND I_FLAG =1");
			List<List> datas = new ArrayList<List>();
			for(Map<String,Object> tempData : tempList ){
				List<Object> d = new ArrayList<Object>();
				d.add(DefaultStringUtil.getNowTime("yyyyMMddHHmmss"));
				d.add(tempData.get("I_ID"));
				datas.add(d);
			}
			logger.info("总大小:"+modFileListSize+",批量处理第"+(i+1)+"页,页大小:"+batchNum+",实际大小:"+datas.size()+",过滤前大小:"+tempList.size());
			this.updateByPrepareStatementBatch2(sql.toString(), datas, this.getDomain());
		}
	}
}
