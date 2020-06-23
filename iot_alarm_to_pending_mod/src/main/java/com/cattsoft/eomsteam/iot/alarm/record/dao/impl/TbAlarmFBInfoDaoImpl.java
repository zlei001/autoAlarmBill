package com.cattsoft.eomsteam.iot.alarm.record.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.cattsoft.eomsteam.iot.alarm.record.dao.TbAlarmFBInfoDao;
import com.cattsoft.eomsteam.iot.alarm.record.entity.TbAlarmFBInfoEntity;
import com.cattsoft.eomsteam.mtoppub.ssh3InitUtil.ConfigService;
import com.cattsoft.eomsteam.uc.pub.db.DefaultStringUtil;
import com.cattsoft.eomsteam.uc.pub.db.uc_main2.dao.UCMainDao;

@Repository("com.cattsoft.eomsteam.iot.alarm.record.dao.TbAlarmFBInfoDao")
public class TbAlarmFBInfoDaoImpl   extends UCMainDao implements TbAlarmFBInfoDao {

	
	@Override
	public Long getID() {
	    String id = this.callIdGenratorProc2("TB_ALARM_FB_INFO",this.getDomain());
		return Long.valueOf(id);
	}

	
	@Override
	public void save(List<TbAlarmFBInfoEntity> tbAlarmFBInfoEntityList){
		// TODO Auto-generated method stub
		int batchNum  = DefaultStringUtil.toInteger((ConfigService.getPropertiyByKey("batchNum","300")));
		int modFileListSize = tbAlarmFBInfoEntityList.size();
		int pageNo = modFileListSize/batchNum;
		if(modFileListSize % batchNum !=0){
			pageNo = pageNo + 1;
		}
		for(int i= 0;i<pageNo;i++ ){
			List<TbAlarmFBInfoEntity> tempList = new ArrayList<TbAlarmFBInfoEntity>();
			int runNum = (i+1)*batchNum > modFileListSize ? modFileListSize : (i+1)*batchNum;
			for(int j=i*batchNum;j<runNum;j++){
				tempList.add(tbAlarmFBInfoEntityList.get(j));
			}
			
			StringBuffer sql = new StringBuffer();
			sql.append(" INSERT INTO ").append("TB_ALARM_FB_INFO");
			sql.append(" (I_ID       ,I_REF_ID         ,I_REF_TYPE");
			sql.append(" ,I_FLAG,S_MSG,I_CREATE_TIME");
			sql.append(" ,CITY_CODE,CITY_NAME,I_STAFF_ID,S_STAFF_NAME");
			sql.append(" )");
			sql.append(" VALUES(?,?,?");
			sql.append(" ,?,?,?");
			sql.append(" ,?,?,?,?");
			sql.append(" )");
			List<List> datas = new ArrayList<List>();
			for(TbAlarmFBInfoEntity tbAlarmFBInfoEntity : tempList ){
				List<Object> d = new ArrayList<Object>();
				d.add(tbAlarmFBInfoEntity.getI_ID());
				d.add(tbAlarmFBInfoEntity.getI_REF_ID());
				d.add(tbAlarmFBInfoEntity.getI_REF_TYPE());
				
				d.add(tbAlarmFBInfoEntity.getI_FLAG());
				d.add(tbAlarmFBInfoEntity.getS_MSG());
				d.add(tbAlarmFBInfoEntity.getI_CREATE_TIME());
				
				d.add(tbAlarmFBInfoEntity.getCITY_CODE());
				d.add(tbAlarmFBInfoEntity.getCITY_NAME());
				d.add(tbAlarmFBInfoEntity.getI_STAFF_ID());
				d.add(tbAlarmFBInfoEntity.getS_STAFF_NAME());
				datas.add(d);
			}
			logger.info("总大小:"+modFileListSize+",批量处理第"+(i+1)+"页,页大小:"+batchNum+",实际大小:"+datas.size()+",过滤前大小:"+tempList.size());
			this.updateByPrepareStatementBatch2(sql.toString(), datas, this.getDomain());
		}
	}
}
