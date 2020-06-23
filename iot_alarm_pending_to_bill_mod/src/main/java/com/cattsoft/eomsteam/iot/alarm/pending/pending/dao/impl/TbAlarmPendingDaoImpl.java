package com.cattsoft.eomsteam.iot.alarm.pending.pending.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cattsoft.eomsteam.iot.alarm.pending.pending.dao.TbAlarmPendingDao;
import com.cattsoft.eomsteam.mtoppub.ssh3InitUtil.ConfigService;
import com.cattsoft.eomsteam.uc.pub.db.DefaultStringUtil;
import com.cattsoft.eomsteam.uc.pub.db.uc_main2.dao.UCMainDao;

@Repository("com.cattsoft.eomsteam.iot.alarm.pending.pending.dao.TbAlarmPendingDao")
public class TbAlarmPendingDaoImpl  extends UCMainDao implements TbAlarmPendingDao {
	
	/***
	 * 对象类型
	 */
	private static final String I_OBJ_TYPE = "1,4,5";
	
	@Override
	public void updateAlarmPendingDispatchFlag(List<Map<String,Object> > setIdList) {
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
			sql.append(" SET I_DISPATCH_FLAG = 2,I_DISPATCH_TIME = ?");
			sql.append(" WHERE I_ID = ?");
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
	
	@Override
	public List<Map<String, Object>> getAlarmPendingZipListByInst(String S_INST_ID,String S_INST_NAME) {
		String queryCol = this.getAlarmPendingQueryCol();
		StringBuffer sql = new StringBuffer();
		sql.append(" select  ");
		sql.append(queryCol);
		sql.append(" from  TB_ALARM_PENGDING ");
		sql.append(" where  I_FLAG = 1 and I_DISPATCH_FLAG =1  and S_INST_ID = ? and S_INST_NAME = ?");
		List<Object> data = new ArrayList<Object>();
		data.add(S_INST_ID);
		data.add(S_INST_NAME);
		List<Map<String,Object>> tempResultList =  this.queryBySql(sql.toString(), data);
		return tempResultList;
	}
	
	/***
	 * 查询告警代办查询字段
	 * @return
	 */
	protected String getAlarmPendingQueryCol() {
		StringBuffer sql = new StringBuffer();
		sql.append("   I_ID               as \"I_ID\"             ,S_NO           as \"S_NO\"");
		sql.append("  ,I_REF_ID           as \"I_REF_ID\"         ,I_OBJ_LEVEL    as \"I_OBJ_LEVEL\"");
		sql.append("  ,I_OBJ_TYPE         as \"I_OBJ_TYPE\"       ,I_NE_MODEL     as \"I_NE_MODEL\"");
		sql.append("  ,I_LARGE_CLASS      as \"I_LARGE_CLASS\"    ,I_SMALL_CLASS  as \"I_SMALL_CLASS\"");
		sql.append("  ,I_CYCLE            as \"I_CYCLE\"          ,S_TITLE        as \"S_TITLE\"");
		sql.append("  ,S_DELIMITED_CYCLE  as \"S_DELIMITED_CYCLE\",S_MSG          as \"S_MSG\"");
		sql.append("  ,CITY_CODE          as \"CITY_CODE\"        ,CITY_NAME      as \"CITY_NAME\"");
		sql.append("  ,S_INST_ID          as \"S_INST_ID\"        ,S_INST_NAME    as \"S_INST_NAME\"");
		sql.append("  ,I_FLAG             as \"I_FLAG\"           ,I_CREATE_TIME  as \"I_CREATE_TIME\"");
		sql.append("  ,I_UPDATE_TIME      as \"I_UPDATE_TIME\"    ,I_OBJ_SOURCE   as \"I_OBJ_SOURCE\"");
		return sql.toString();
	}
	
	@Override
	public List<Map<String, Object>> getAlarmPendingByNotCellOneLevel() {
		String queryCol = this.getAlarmPendingQueryCol();
		StringBuffer sql = new StringBuffer();
		sql.append(" select  ");
		sql.append(queryCol);
		sql.append(" from  TB_ALARM_PENGDING ");
		sql.append(" where  I_FLAG = 1 and I_DISPATCH_FLAG =1 and I_OBJ_LEVEL = 1 and  I_OBJ_TYPE in(").append(I_OBJ_TYPE).append(")");
		List<Object> data = new ArrayList<Object>();
		List<Map<String,Object>> tempResultList =  this.queryBySql(sql.toString(), data);
		return tempResultList;
	}
	
	@Override
	public List<Map<String, Object>> getAlarmPendingByCellOneLevel() {
		String queryCol = this.getAlarmPendingQueryCol();
		StringBuffer sql = new StringBuffer();
		sql.append(" select  ");
		sql.append(queryCol);
		sql.append(" from  TB_ALARM_PENGDING ");
		sql.append(" where  I_FLAG = 1 and I_DISPATCH_FLAG =1 and I_OBJ_LEVEL = 1 and I_OBJ_TYPE in(2,3)");
		List<Object> data = new ArrayList<Object>();
		List<Map<String,Object>> tempResultList =  this.queryBySql(sql.toString(), data);
		return tempResultList;
	}
	
	@Override
	public List<Map<String, Object>> getAlarmPendingByCellTwoLevel() {
		String queryCol = this.getAlarmPendingQueryCol();
		StringBuffer sql = new StringBuffer();
		sql.append(" select  ");
		sql.append(queryCol);
		sql.append(" from  TB_ALARM_PENGDING ");
		sql.append(" where  I_FLAG = 1 and I_DISPATCH_FLAG =1 and I_OBJ_LEVEL = 2 and I_OBJ_TYPE in(2,3)");
		List<Object> data = new ArrayList<Object>();
		List<Map<String,Object>> tempResultList =  this.queryBySql(sql.toString(), data);
		return tempResultList;
	}



	@Override
	public List<Map<String, Object>> getAlarmPendingByNotCellTwoLevel() {
		String queryCol = this.getAlarmPendingQueryCol();
		StringBuffer sql = new StringBuffer();
		sql.append(" select  ");
		sql.append(queryCol);
		sql.append(" from  TB_ALARM_PENGDING ");
		sql.append(" where  I_FLAG = 1 and I_DISPATCH_FLAG =1 and I_OBJ_LEVEL = 2 and I_OBJ_TYPE in(").append(I_OBJ_TYPE).append(")");
		List<Object> data = new ArrayList<Object>();
		List<Map<String,Object>> tempResultList =  this.queryBySql(sql.toString(), data);
		return tempResultList;
	}
	
	@Override
	public List<Map<String, Object>> getAlarmPendingCellZipCntList() {
		int zipCnt  = DefaultStringUtil.toInteger((ConfigService.getPropertiyByKey("zipCnt","0")));
		StringBuffer sql = new StringBuffer();
		sql.append(" select  ");
		sql.append(" CITY_CODE as \"CITY_CODE\",CITY_NAME   as \"CITY_NAME\"");
		sql.append(" ,S_INST_ID as \"S_INST_ID\",S_INST_NAME as \"S_INST_NAME\"");
		sql.append(" ,count(*) as \"cnt\" ");
		sql.append(" from  TB_ALARM_PENGDING ");
		sql.append(" where  I_FLAG = 1 and I_DISPATCH_FLAG =1 and I_OBJ_TYPE in(2,3)");
		sql.append(" group by CITY_CODE,CITY_NAME,S_INST_ID,S_INST_NAME");
		sql.append(" having count(*) > ").append(zipCnt);
		List<Object> data = new ArrayList<Object>();
		List<Map<String,Object>> tempResultList =  this.queryBySql(sql.toString(), data);
		return tempResultList;
	}

	@Override
	public List<Map<String, Object>> getAlarmPendingNotCellZipCntList() {
		int zipCnt  = DefaultStringUtil.toInteger((ConfigService.getPropertiyByKey("zipCnt","0")));
		StringBuffer sql = new StringBuffer();
		sql.append(" select  ");
		sql.append(" S_INST_ID as \"S_INST_ID\",S_INST_NAME as \"S_INST_NAME\"");
		sql.append(" ,count(*) as \"cnt\" ");
		sql.append(" from  TB_ALARM_PENGDING ");
		sql.append(" where  I_FLAG = 1 and I_DISPATCH_FLAG =1 and I_OBJ_TYPE in(").append(I_OBJ_TYPE).append(")");
		sql.append(" group by S_INST_ID,S_INST_NAME");
		sql.append(" having count(*) > ").append(zipCnt);
		List<Object> data = new ArrayList<Object>();
		List<Map<String,Object>> tempResultList =  this.queryBySql(sql.toString(), data);
		return tempResultList;
	}

}
