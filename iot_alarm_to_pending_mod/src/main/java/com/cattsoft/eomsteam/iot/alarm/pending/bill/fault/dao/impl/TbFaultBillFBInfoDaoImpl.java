package com.cattsoft.eomsteam.iot.alarm.pending.bill.fault.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.cattsoft.eomsteam.iot.alarm.pending.bill.fault.dao.TbFaultBillFBInfoDao;
import com.cattsoft.eomsteam.iot.alarm.pending.bill.fault.entity.TbFaultBillFBInfoEntity;
import com.cattsoft.eomsteam.mtoppub.ssh3InitUtil.ConfigService;
import com.cattsoft.eomsteam.uc.pub.db.DefaultStringUtil;
import com.cattsoft.eomsteam.uc.pub.db.uc_main2.dao.UCMainDao;

@Repository("com.cattsoft.eomsteam.iot.alarm.record.dao.TbFaultBillFBInfoDao")
public class TbFaultBillFBInfoDaoImpl   extends UCMainDao implements TbFaultBillFBInfoDao {
	

	@Override
	public Long getID() {
	    String id = this.callIdGenratorProc2("TB_FAULT_BILL_FB_INFO",this.getDomain());
		return Long.valueOf(id);
	}


	@Override
	public void save(List<TbFaultBillFBInfoEntity> tbFaultBillFBInfoEntityList) {
		// TODO Auto-generated method stub
		int batchNum  = DefaultStringUtil.toInteger((ConfigService.getPropertiyByKey("batchNum","300")));
		int modFileListSize = tbFaultBillFBInfoEntityList.size();
		int pageNo = modFileListSize/batchNum;
		if(modFileListSize % batchNum !=0){
			pageNo = pageNo + 1;
		}
		for(int i= 0;i<pageNo;i++ ){
			List<TbFaultBillFBInfoEntity> tempList = new ArrayList<TbFaultBillFBInfoEntity>();
			int runNum = (i+1)*batchNum > modFileListSize ? modFileListSize : (i+1)*batchNum;
			for(int j=i*batchNum;j<runNum;j++){
				tempList.add(tbFaultBillFBInfoEntityList.get(j));
			}
			
			StringBuffer sql = new StringBuffer();
			sql.append(" INSERT INTO ").append("TB_FAULT_BILL_FB_INFO");
			sql.append(" (I_ID       ,I_REF_ID         ,I_REF_TYPE");
			sql.append(" ,I_FLAG,S_MSG,I_CREATE_TIME");
			sql.append(" ,CITY_CODE,CITY_NAME,I_STAFF_ID,S_STAFF_NAME");
			sql.append(" )");
			sql.append(" VALUES(?,?,?");
			sql.append(" ,?,?,?");
			sql.append(" ,?,?,?,?");
			sql.append(" )");
			List<List> datas = new ArrayList<List>();
			for(TbFaultBillFBInfoEntity tbFaultBillFBInfoEntity : tempList ){
				List<Object> d = new ArrayList<Object>();
				d.add(tbFaultBillFBInfoEntity.getI_ID());
				d.add(tbFaultBillFBInfoEntity.getI_REF_ID());
				d.add(tbFaultBillFBInfoEntity.getI_REF_TYPE());
				d.add(tbFaultBillFBInfoEntity.getI_FLAG());
				d.add(tbFaultBillFBInfoEntity.getS_MSG());
				d.add(tbFaultBillFBInfoEntity.getI_CREATE_TIME());
				d.add(tbFaultBillFBInfoEntity.getS_MSG());
				d.add(tbFaultBillFBInfoEntity.getCITY_CODE());
				d.add(tbFaultBillFBInfoEntity.getCITY_NAME());
				d.add(tbFaultBillFBInfoEntity.getI_STAFF_ID());
				d.add(tbFaultBillFBInfoEntity.getS_STAFF_NAME());
				datas.add(d);
			}
			logger.info("总大小:"+modFileListSize+",批量处理第"+(i+1)+"页,页大小:"+batchNum+",实际大小:"+datas.size()+",过滤前大小:"+tempList.size());
			this.updateByPrepareStatementBatch2(sql.toString(), datas, this.getDomain());
		}
	}
}
