package com.cattsoft.eomsteam.iot.alarm.pending.bill.fault.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.cattsoft.eomsteam.iot.alarm.pending.bill.fault.dao.TbFaultBillRefAlarmDao;
import com.cattsoft.eomsteam.iot.alarm.pending.bill.fault.entity.TbFaultBillRefAlarmEntity;
import com.cattsoft.eomsteam.mtoppub.ssh3InitUtil.ConfigService;
import com.cattsoft.eomsteam.uc.pub.db.DefaultStringUtil;
import com.cattsoft.eomsteam.uc.pub.db.uc_main2.dao.UCMainDao;

@Repository("com.cattsoft.eomsteam.iot.alarm.pending.bill.fault.dao.TbFaultBillRefAlarmDao")
public class TbFaultBillRefAlarmDaoImpl  extends UCMainDao  implements TbFaultBillRefAlarmDao {

	@Override
	public Long getID() {
		// TODO Auto-generated method stub
		String id = this.callIdGenratorProc2("TB_FAULT_BILL_REF_ALARM",this.getDomain());
		return Long.valueOf(id);
	}

	@Override
	public void save(List<TbFaultBillRefAlarmEntity> tbFaultBillRefAlarmList) {
		// TODO Auto-generated method stub
		int batchNum  = DefaultStringUtil.toInteger((ConfigService.getPropertiyByKey("batchNum","300")));
		int modFileListSize = tbFaultBillRefAlarmList.size();
		int pageNo = modFileListSize/batchNum;
		if(modFileListSize % batchNum !=0){
			pageNo = pageNo + 1;
		}
		for(int i= 0;i<pageNo;i++ ){
			List<TbFaultBillRefAlarmEntity> tempList = new ArrayList<TbFaultBillRefAlarmEntity>();
			int runNum = (i+1)*batchNum > modFileListSize ? modFileListSize : (i+1)*batchNum;
			for(int j=i*batchNum;j<runNum;j++){
				tempList.add(tbFaultBillRefAlarmList.get(j));
			}
			StringBuffer sql = new StringBuffer();
			sql.append(" INSERT INTO ").append("TB_FAULT_BILL_REF_ALARM");
			sql.append(" (I_ID       ,I_BILL_ID        ,I_ALARM_ID,I_ZIP_TIME");
			sql.append(" ,I_LARGE_CLASS  ,I_SMALL_CLASS        ,I_NE_MODEL");
			sql.append(" )");
			sql.append(" VALUES(?,?,?,?");
			sql.append(" ,?,?,?");
			sql.append(" )");
			List<List> datas = new ArrayList<List>();
			for(TbFaultBillRefAlarmEntity tbFaultBillRefAlarmEntity : tempList ){
				List<Object> d = new ArrayList<Object>();
				d.add(tbFaultBillRefAlarmEntity.getI_ID());
				d.add(tbFaultBillRefAlarmEntity.getI_BILL_ID());
				d.add(tbFaultBillRefAlarmEntity.getI_ALARM_ID());
				d.add(tbFaultBillRefAlarmEntity.getI_ZIP_TIME());
				
				d.add(tbFaultBillRefAlarmEntity.getI_LARGE_CLASS());
				d.add(tbFaultBillRefAlarmEntity.getI_SMALL_CLASS());
				d.add(tbFaultBillRefAlarmEntity.getI_NE_MODEL());
				datas.add(d);
			}
			logger.info("总大小:"+modFileListSize+",批量处理第"+(i+1)+"页,页大小:"+batchNum+",实际大小:"+datas.size()+",过滤前大小:"+tempList.size());
			this.updateByPrepareStatementBatch2(sql.toString(), datas, this.getDomain());
		}
	}

}
