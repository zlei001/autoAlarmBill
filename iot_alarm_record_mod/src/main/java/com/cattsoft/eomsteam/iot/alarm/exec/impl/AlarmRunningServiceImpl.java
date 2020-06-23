package com.cattsoft.eomsteam.iot.alarm.exec.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pub.dbDialectFactory.*;

import com.cattsoft.eomsteam.iot.alarm.conf.entity.TbAlarmConfigEntity;
import com.cattsoft.eomsteam.iot.alarm.conf.entity.TbAlarmConfigRuleEntity;
import com.cattsoft.eomsteam.iot.alarm.conf.service.TbAlarmConfigRuleService;
import com.cattsoft.eomsteam.iot.alarm.conf.service.TbAlarmConfigService;
import com.cattsoft.eomsteam.iot.alarm.exec.AlarmRunningService;
import com.cattsoft.eomsteam.iot.alarm.log.entity.TbAlarmConfigLogEntity;
import com.cattsoft.eomsteam.iot.alarm.log.service.TbAlarmConfigLogService;
import com.cattsoft.eomsteam.iot.alarm.record.busi.AlarmRecordBusiService;
import com.cattsoft.eomsteam.iot.alarm.record.entity.TbAlarmRecordEntity;
import com.cattsoft.eomsteam.iot.alarm.record.service.TbAlarmRecordService;
import com.cattsoft.eomsteam.iot.alarm.tbl.entity.TbAlarmTblEntity;
import com.cattsoft.eomsteam.iot.alarm.tbl.service.TbAlarmTblService;
import com.cattsoft.eomsteam.mtoppub.ssh3InitUtil.CattApplicationContextUtil;
import com.cattsoft.eomsteam.uc.pub.db.DefaultStringUtil;

@Service("com.cattsoft.eomsteam.iot.alarm.exec.AlarmRunningService") 
public class AlarmRunningServiceImpl implements AlarmRunningService {
	
    /**
     * 日志对象
     */
    private static Logger logger = Logger.getLogger(AlarmRunningServiceImpl.class);

	@Autowired
	private TbAlarmConfigLogService tbAlarmConfigLogService;
	
	@Autowired
	private TbAlarmConfigService tbAlarmConfigService;
	
	@Autowired
	private TbAlarmConfigRuleService tbAlarmConfigRuleService;
	
	@Autowired
	private TbAlarmRecordService tbAlarmPendingService;
	
	@Autowired
	private TbAlarmTblService tbAlarmTblService;

	@Override
	public void doPending() {
		Map<Long,List<TbAlarmConfigRuleEntity>> configRuleData = tbAlarmConfigRuleService.getAlarmConfigRuleData();
		List<TbAlarmConfigLogEntity> configLogEntityList  = tbAlarmConfigLogService.getAlarmConfigLogList();
		logger.info("运行策略日志数量:"+configLogEntityList.size());
		for(TbAlarmConfigLogEntity tbAlarmConfigLogEntity : configLogEntityList){
			TbAlarmConfigEntity tbAlarmConfigEntity = tbAlarmConfigService.getAlarmConfigById(tbAlarmConfigLogEntity.getI_REF_ID());
			if(null != tbAlarmConfigEntity){
			
				List<TbAlarmConfigRuleEntity> configRuleEntityList = configRuleData.get(tbAlarmConfigLogEntity.getI_REF_ID());
				
				Map<Long,List<TbAlarmConfigRuleEntity>> alarmConfigTblRuleData = this.getAlarmConfigRuleTblData(configRuleEntityList);
				
				Iterator<Long> iterator = alarmConfigTblRuleData.keySet().iterator();
				while(iterator.hasNext()){
					Long I_TBL_ID = iterator.next();
					TbAlarmTblEntity tbAlarmTblEntity = tbAlarmTblService.getAlarmTblById(I_TBL_ID);
					if(null != tbAlarmTblEntity){
						tbAlarmConfigEntity.setS_OBJ_TBL(tbAlarmTblEntity.getS_OBJ_TBL());
						tbAlarmConfigEntity.setS_OBJ_TBL_WHERE(tbAlarmTblEntity.getS_OBJ_TBL_WHERE());
						List<TbAlarmConfigRuleEntity> alarmConfigRuleEntityList = alarmConfigTblRuleData.get(I_TBL_ID);
						String alarmCountParentSql   = tbAlarmConfigService.getAlarmCountParentSql(tbAlarmConfigEntity, tbAlarmConfigLogEntity.getS_INST_CYCLE());
						String alarmPendingParentSql = tbAlarmConfigService.getAlarmPendingParentSql(tbAlarmConfigEntity, tbAlarmConfigLogEntity.getS_INST_CYCLE());
						String alarmConfigWhereSql   = tbAlarmConfigRuleService.getDelimitedConfigWhereSql(alarmConfigRuleEntityList);
					
						String alarmCountSql   = alarmCountParentSql   +" and "+ alarmConfigWhereSql;
						String alarmPendingSql = alarmPendingParentSql +" and "+ alarmConfigWhereSql;
						
						Long alarmPendingCount = tbAlarmPendingService.getAlarmPendingCount(alarmCountSql);
						if(alarmPendingCount != tbAlarmConfigLogEntity.getI_PENDING_CNT()){
							try{
								logger.info(tbAlarmConfigEntity.getI_ID()+",告警记录数据发生变更，需重新进行定界逻辑计算：本周期值:"+alarmPendingCount+",上周期值:"+tbAlarmConfigLogEntity.getI_PENDING_CNT());
								String busiId =  this.getBusiId(tbAlarmConfigEntity);
								AlarmRecordBusiService  alarmRecordBusiService = (AlarmRecordBusiService)CattApplicationContextUtil.getBean(busiId);
								Long I_START_TIME = DefaultStringUtil.getNowTime("yyyyMMddHHmmss");
								List<TbAlarmRecordEntity> delimitedPendingEntityList = alarmRecordBusiService.doBusiPending(alarmPendingSql, tbAlarmConfigLogEntity, tbAlarmConfigEntity, alarmConfigRuleEntityList);
								Long I_END_TIME = DefaultStringUtil.getNowTime("yyyyMMddHHmmss");
								tbAlarmPendingService.save(delimitedPendingEntityList);
								tbAlarmConfigLogService.updateRunningAlarmConfigLogById(tbAlarmConfigLogEntity.getI_ID(), alarmPendingCount, I_START_TIME, I_END_TIME);
								logger.info(tbAlarmConfigEntity.getI_ID()+",告警记录数据发生变更，需重新进行定界逻辑计算：本周期值:"+alarmPendingCount+",上周期值:"+tbAlarmConfigLogEntity.getI_PENDING_CNT()+",新增更新 代办数量:"+delimitedPendingEntityList.size());
							}catch(Exception e){
								logger.error("异常信息"+e.getMessage(),e);
							}
						}else{
							logger.info(tbAlarmConfigEntity.getI_ID()+",告警记录数据未发生变更，退出定界逻辑，周值:"+alarmPendingCount+",上周期值:"+tbAlarmConfigLogEntity.getI_PENDING_CNT());
						}
					}
				}
				tbAlarmConfigLogService.updateAlarmConfigLogById(tbAlarmConfigLogEntity.getI_ID());
			}else{
				logger.info("运行策略日志ID:"+tbAlarmConfigLogEntity.getI_REF_ID()+",已不存在");
			}
		}
	}
	
	/***
	 * 
	 * @param alarmConfigRuleEntityList
	 * @return
	 */
	private Map<Long,List<TbAlarmConfigRuleEntity>> getAlarmConfigRuleTblData(List<TbAlarmConfigRuleEntity> alarmConfigRuleEntityList){
		Map<Long,List<TbAlarmConfigRuleEntity>>  configRuleEntitData = new HashMap<Long,List<TbAlarmConfigRuleEntity>> ();
		for(TbAlarmConfigRuleEntity tbAlarmConfigRuleEntity : alarmConfigRuleEntityList){
			List<TbAlarmConfigRuleEntity> list = new ArrayList<TbAlarmConfigRuleEntity>();
			if(configRuleEntitData.containsKey(tbAlarmConfigRuleEntity.getI_TBL_ID())){
				configRuleEntitData.get(tbAlarmConfigRuleEntity.getI_TBL_ID()).add(tbAlarmConfigRuleEntity);
			}else{
				list.add(tbAlarmConfigRuleEntity);
				configRuleEntitData.put(tbAlarmConfigRuleEntity.getI_TBL_ID(),list);
			}
		}
		return configRuleEntitData;
	}
	
	/***
	 * 返回处理业务ID
	 * @param tbDelimitedConfigEntity
	 * @return
	 */
	private String getBusiId(TbAlarmConfigEntity tbDelimitedConfigEntity){
		String busiId = "com.cattsoft.eomsteam.iot.alarm.record.busi."+tbDelimitedConfigEntity.getI_OBJ_TYPE();
		return busiId;
	}
	
	

}
