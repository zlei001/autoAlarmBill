package com.cattsoft.eomsteam.iot.stat.exec.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cattsoft.eomsteam.iot.stat.exec.StatAlarmRunningService;
import com.cattsoft.eomsteam.iot.stat.log.entity.TbStatSqlLogEntity;
import com.cattsoft.eomsteam.iot.stat.log.service.TbStatSqlLogService;
import com.cattsoft.eomsteam.iot.stat.record.busi.StatRecordBusiService;
import com.cattsoft.eomsteam.iot.stat.record.service.TbStatSqlService;
import com.cattsoft.eomsteam.mtoppub.ssh3InitUtil.CattApplicationContextUtil;

@Service("com.cattsoft.eomsteam.iot.stat.exec.StatAlarmRunningService") 
public class StatAlarmRunningServiceImpl implements StatAlarmRunningService {
	
	
	@Autowired
	private TbStatSqlLogService tbStatRecordLogService;
	
	
	@Autowired
	private TbStatSqlService tbStatSqlService;
	

	@Override
	public void doPending() {
		// TODO Auto-generated method stub
		List<TbStatSqlLogEntity> recordLogEntityList = tbStatRecordLogService.getAlarmConfigLogList();
		for(TbStatSqlLogEntity tbStatRecordLogEntity : recordLogEntityList){
			String beanId = this.getBeanId(tbStatRecordLogEntity);
			StatRecordBusiService statRecordBusiService = (StatRecordBusiService)CattApplicationContextUtil.getBean(beanId);
			List<Map<String,Object>> statResultList = tbStatSqlService.getStatResultSqlList(tbStatRecordLogEntity.getS_OBJ_SQL());
		
			statRecordBusiService.doBusi(tbStatRecordLogEntity, statResultList);
		}
	}
	
	private String getBeanId(TbStatSqlLogEntity tbStatRecordLogEntity){
		
		String beanId = "com.cattsoft.eomsteam.iot.stat.record.busi."+tbStatRecordLogEntity.getI_OBJ_TYPE();
		return beanId;
	}

}
