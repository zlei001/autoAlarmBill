package com.cattsoft.eomsteam.iot.alarm.pending.bill.fault.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.cattsoft.eomsteam.iot.alarm.pending.bill.fault.dao.TbFaultBillToOutSysReqDao;
import com.cattsoft.eomsteam.iot.alarm.pending.bill.fault.entity.TbFaultBillToOutSysReqEntity;
import com.cattsoft.eomsteam.mtoppub.ssh3InitUtil.ConfigService;
import com.cattsoft.eomsteam.uc.pub.db.DefaultStringUtil;
import com.cattsoft.eomsteam.uc.pub.db.uc_main2.dao.UCMainDao;

@Repository("com.cattsoft.eomsteam.iot.alarm.bill.dao.TbFaultBillToOutSysReqDao")
public class TbFaultBillToOutSysReqDaoImpl  extends UCMainDao  implements TbFaultBillToOutSysReqDao {

	@Override
	public Long getID() {
		 String id = this.callIdGenratorProc2("TB_FAULT_BILL_TO_OUTSYS_REQ",this.getDomain());
		 return Long.valueOf(id);
	}

	@Override
	public void save(List<TbFaultBillToOutSysReqEntity> tbFaultBillToOutSysReqList) {
		int batchNum  = DefaultStringUtil.toInteger((ConfigService.getPropertiyByKey("batchNum","300")));
		int modFileListSize = tbFaultBillToOutSysReqList.size();
		int pageNo = modFileListSize/batchNum;
		if(modFileListSize % batchNum !=0){
			pageNo = pageNo + 1;
		}
		for(int i= 0;i<pageNo;i++ ){
			List<TbFaultBillToOutSysReqEntity> tempList = new ArrayList<TbFaultBillToOutSysReqEntity>();
			int runNum = (i+1)*batchNum > modFileListSize ? modFileListSize : (i+1)*batchNum;
			for(int j=i*batchNum;j<runNum;j++){
				tempList.add(tbFaultBillToOutSysReqList.get(j));
			}
			
			StringBuffer sql = new StringBuffer();
			sql.append(" INSERT INTO ").append("TB_FAULT_BILL_TO_OUTSYS_REQ");
			sql.append(" (I_ID       ,I_BILL_ID        ,I_REF_ID,I_REF_TYPE");
			sql.append(" ,I_FLAG,I_TRY_CNT ,I_CREATE_TIME");
			sql.append(" )");
			sql.append(" VALUES(?,?,?,?");
			sql.append(" ,?,?,?");
			sql.append(" )");
			List<List> datas = new ArrayList<List>();
			for(TbFaultBillToOutSysReqEntity tbFaultBillToOutSysReqEntity : tempList ){
				List<Object> d = new ArrayList<Object>();
				d.add(tbFaultBillToOutSysReqEntity.getI_ID());
				d.add(tbFaultBillToOutSysReqEntity.getI_BILL_ID());
				d.add(tbFaultBillToOutSysReqEntity.getI_REF_ID());
				d.add(tbFaultBillToOutSysReqEntity.getI_REF_TYPE());
				
				d.add(tbFaultBillToOutSysReqEntity.getI_FLAG());
				d.add(tbFaultBillToOutSysReqEntity.getI_TRY_CNT());
				
				d.add(tbFaultBillToOutSysReqEntity.getI_CREATE_TIME());
				datas.add(d);
			}
			logger.info("总大小:"+modFileListSize+",批量处理第"+(i+1)+"页,页大小:"+batchNum+",实际大小:"+datas.size()+",过滤前大小:"+tempList.size());
			this.updateByPrepareStatementBatch2(sql.toString(), datas, this.getDomain());
		}
	}

}
