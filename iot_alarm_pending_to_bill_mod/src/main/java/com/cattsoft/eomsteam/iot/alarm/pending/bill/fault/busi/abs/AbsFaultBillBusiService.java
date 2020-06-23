package com.cattsoft.eomsteam.iot.alarm.pending.bill.fault.busi.abs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.cattsoft.eomsteam.iot.alarm.pending.bill.fault.busi.FaultBillBusiService;
import com.cattsoft.eomsteam.iot.alarm.pending.bill.fault.busi.impl.ZipCellPendingFaultBillBusiService;
import com.cattsoft.eomsteam.iot.alarm.pending.bill.fault.entity.TbFaultBillEntity;
import com.cattsoft.eomsteam.iot.alarm.pending.bill.fault.entity.TbFaultBillRefAlarmEntity;
import com.cattsoft.eomsteam.iot.alarm.pending.bill.fault.entity.TbFaultBillToOutSysReqEntity;
import com.cattsoft.eomsteam.iot.alarm.pending.bill.fault.service.TbFaultBillRefAlarmService;
import com.cattsoft.eomsteam.iot.alarm.pending.bill.fault.service.TbFaultBillRefDeptService;
import com.cattsoft.eomsteam.iot.alarm.pending.bill.fault.service.TbFaultBillService;
import com.cattsoft.eomsteam.iot.alarm.pending.bill.fault.service.TbFaultBillToOutSysReqService;
import com.cattsoft.eomsteam.iot.alarm.pending.pending.entity.TbAlarmPendingEntity;
import com.cattsoft.eomsteam.iot.alarm.pending.pending.service.TbAlarmPendingService;
import com.cattsoft.eomsteam.uc.pub.db.DefaultStringUtil;

public abstract class AbsFaultBillBusiService implements FaultBillBusiService {

    /**
     * 日志对象
     */
    private static Logger logger = Logger.getLogger(ZipCellPendingFaultBillBusiService.class);

	@Autowired
	private TbAlarmPendingService tbAlarmPendingService;
	
	@Autowired
	private TbFaultBillService tbFaultBillService;
	
	@Autowired
	private TbFaultBillRefAlarmService tbFaultBillRefAlarmService;
	
	@Autowired
	private TbFaultBillToOutSysReqService tbFaultBillToOutSysReqService;
	
	@Autowired
	private TbFaultBillRefDeptService tbFaultBillRefDeptService;
	
	/***
	 * 保存cell数据
	 * @param alarmPendingEntityList
	 */
	protected void doBusiNoZipData(List<TbAlarmPendingEntity> alarmPendingEntityList){
		List<Map<String,Object> > setBillZipIdList = new ArrayList<Map<String,Object> >();
		List<Map<String,Object> > setIdList = new ArrayList<Map<String,Object> >();
		List<TbFaultBillRefAlarmEntity> tbFaultBillRefAlarmEntityList = new ArrayList<TbFaultBillRefAlarmEntity>();
		List<TbFaultBillToOutSysReqEntity> tbFaultBillToOutSysReqEntityList = new ArrayList<TbFaultBillToOutSysReqEntity>();
		List<TbFaultBillEntity> tbFaultBillEntityList = new ArrayList<TbFaultBillEntity>();
		
	    for(TbAlarmPendingEntity tbAlarmPendingEntity : alarmPendingEntityList){
	    	List<TbAlarmPendingEntity> tempList = new ArrayList<TbAlarmPendingEntity>();
			tempList.add(tbAlarmPendingEntity);
			
	    	//地市或小区下是否有归并工单，有则追加描述
			List<TbFaultBillEntity> zipFaultBillList = this.getZipFaultBillList(tbAlarmPendingEntity);
			if(zipFaultBillList.size() >0){
				logger.info("小区告警归并地市:"+tbAlarmPendingEntity.getCITY_NAME()+","+tbAlarmPendingEntity.getS_TITLE()+"["+tbAlarmPendingEntity.getI_ID()+"]");
				
				TbFaultBillEntity tbFaultBillEntity = zipFaultBillList.get(0);
				Map<String,Object> billZipIdData = new HashMap<String,Object>();
				billZipIdData.put("I_ID", tbFaultBillEntity.getI_ID());
				billZipIdData.put("I_ZIP_CNT", tbFaultBillEntity.getI_ZIP_CNT()+tempList.size());
				setBillZipIdList.add(billZipIdData);
				
				this.getTbFaultBillRefAlarmEntityList(tempList, tbFaultBillEntity,tbFaultBillRefAlarmEntityList);
				this.getUpdateAlarmPendingDispatchList(tempList,setIdList);
				this.getTbFaultBillToOutSysReqEntityList(tempList, tbFaultBillEntity,tbFaultBillToOutSysReqEntityList,2);
			}else{
				//小区是否有相同指标的工单，有这追加描述
				List<TbFaultBillEntity> existsFaultBillList = tbFaultBillService.getFaultBillByInst(tbAlarmPendingEntity.getS_INST_ID()    , tbAlarmPendingEntity.getS_INST_NAME()
													, tbAlarmPendingEntity.getI_LARGE_CLASS(), tbAlarmPendingEntity.getI_SMALL_CLASS());
				
				if(existsFaultBillList.size() > 0 ){
					logger.info("小区告警是否已存在工单:"+tbAlarmPendingEntity.getCITY_NAME()+","+tbAlarmPendingEntity.getS_TITLE()+"["+tbAlarmPendingEntity.getI_ID()+"]");
					TbFaultBillEntity tbFaultBillEntity = zipFaultBillList.get(0);
					
					Map<String,Object> billZipIdData = new HashMap<String,Object>();
					billZipIdData.put("I_ID", tbFaultBillEntity.getI_ID());
					billZipIdData.put("I_ZIP_CNT", tbFaultBillEntity.getI_ZIP_CNT()+tempList.size());
					setBillZipIdList.add(billZipIdData);
					
					this.getTbFaultBillRefAlarmEntityList(tempList, tbFaultBillEntity,tbFaultBillRefAlarmEntityList);
					this.getUpdateAlarmPendingDispatchList(tempList,setIdList);
					this.getTbFaultBillToOutSysReqEntityList(tempList, tbFaultBillEntity,tbFaultBillToOutSysReqEntityList,2);
				}else{
					TbFaultBillEntity tbFaultBillEntity = this.getTbFaultBillEntity(tempList);
					tbFaultBillEntityList.add(tbFaultBillEntity);
					this.getTbFaultBillRefAlarmEntityList(tempList, tbFaultBillEntity,tbFaultBillRefAlarmEntityList);
					this.getUpdateAlarmPendingDispatchList(tempList,setIdList);
					this.getTbFaultBillToOutSysReqEntityList(tempList, tbFaultBillEntity,tbFaultBillToOutSysReqEntityList,1);
				}
			}
		}
		
	    tbFaultBillService.updateZipUpdateTimeById(setBillZipIdList);
		tbFaultBillService.save(tbFaultBillEntityList);
		tbFaultBillRefDeptService.save(tbFaultBillEntityList);
		tbFaultBillRefAlarmService.save(tbFaultBillRefAlarmEntityList);
		tbFaultBillToOutSysReqService.save(tbFaultBillToOutSysReqEntityList);
		tbAlarmPendingService.updateAlarmPendingDispatchFlag(setIdList);
	}
	
	/***
	 * 压缩文件
	 * @param S_INST_ID
	 * @param S_INST_NAME
	 */
	protected void doBusiZipData(String S_INST_ID,String S_INST_NAME) {
		// TODO Auto-generated method stub
		List<Map<String,Object> > setBillZipIdList = new ArrayList<Map<String,Object> >();
		List<Map<String,Object> > setIdList = new ArrayList<Map<String,Object> >();
		List<TbFaultBillRefAlarmEntity> tbFaultBillRefAlarmEntityList = new ArrayList<TbFaultBillRefAlarmEntity>();
		List<TbFaultBillToOutSysReqEntity> tbFaultBillToOutSysReqEntityList = new ArrayList<TbFaultBillToOutSysReqEntity>();
		List<TbFaultBillEntity> tbFaultBillEntityList = new ArrayList<TbFaultBillEntity>();
		List<TbAlarmPendingEntity> alarmPendingEntityList = tbAlarmPendingService.getAlarmPendingZipListByInst(S_INST_ID, S_INST_NAME);
		
		TbAlarmPendingEntity tbAlarmPendingEntity = alarmPendingEntityList.get(0);
		List<TbFaultBillEntity> zipFaultBillList = this.getZipFaultBillList(tbAlarmPendingEntity);
		if(zipFaultBillList.size() >0){
			logger.info("小区告警归并地市:"+tbAlarmPendingEntity.getCITY_NAME()+","+tbAlarmPendingEntity.getS_TITLE()+"["+tbAlarmPendingEntity.getI_ID()+"]");
			TbFaultBillEntity tbFaultBillEntity = zipFaultBillList.get(0);;
			this.getTbFaultBillRefAlarmEntityList(alarmPendingEntityList, tbFaultBillEntity,tbFaultBillRefAlarmEntityList);
			this.getUpdateAlarmPendingDispatchList(alarmPendingEntityList,setIdList);
			this.getTbFaultBillToOutSysReqEntityList(alarmPendingEntityList, tbFaultBillEntity,tbFaultBillToOutSysReqEntityList,2);
		}else{
//			List<TbFaultBillEntity> existsFaultBillList = tbFaultBillService.getFaultBillByInst(tbAlarmPendingEntity.getS_INST_ID()    , tbAlarmPendingEntity.getS_INST_NAME()
//					, tbAlarmPendingEntity.getI_LARGE_CLASS(), tbAlarmPendingEntity.getI_SMALL_CLASS());
//
//			if(existsFaultBillList.size() > 0 ){
//				logger.info("小区告警是否已存在工单:"+tbAlarmPendingEntity.getCITY_NAME()+","+tbAlarmPendingEntity.getS_TITLE()+"["+tbAlarmPendingEntity.getI_ID()+"]");
//				TbFaultBillEntity tbFaultBillEntity = zipFaultBillList.get(0);
//				
//				Map<String,Object> billZipIdData = new HashMap<String,Object>();
//				billZipIdData.put("I_ID", tbFaultBillEntity.getI_ID());
//				billZipIdData.put("I_ZIP_CNT", tbFaultBillEntity.getI_ZIP_CNT()+alarmPendingEntityList.size());
//				setBillZipIdList.add(billZipIdData);
//				
//				this.getTbFaultBillRefAlarmEntityList(alarmPendingEntityList, tbFaultBillEntity,tbFaultBillRefAlarmEntityList);
//				this.getUpdateAlarmPendingDispatchList(alarmPendingEntityList,setIdList);
//				this.getTbFaultBillToOutSysReqEntityList(alarmPendingEntityList, tbFaultBillEntity,tbFaultBillToOutSysReqEntityList,2);
//			}else{
				TbFaultBillEntity tbFaultBillEntity = this.getTbFaultBillEntity(alarmPendingEntityList);
				tbFaultBillEntityList.add(tbFaultBillEntity);
				this.getTbFaultBillRefAlarmEntityList(alarmPendingEntityList, tbFaultBillEntity,tbFaultBillRefAlarmEntityList);
				this.getUpdateAlarmPendingDispatchList(alarmPendingEntityList,setIdList);
				this.getTbFaultBillToOutSysReqEntityList(alarmPendingEntityList, tbFaultBillEntity,tbFaultBillToOutSysReqEntityList,1);
//			}
		}
		
		tbFaultBillService.updateZipUpdateTimeById(setBillZipIdList);
		tbFaultBillService.save(tbFaultBillEntityList);
		tbFaultBillRefAlarmService.save(tbFaultBillRefAlarmEntityList);
		tbFaultBillToOutSysReqService.save(tbFaultBillToOutSysReqEntityList);
		tbAlarmPendingService.updateAlarmPendingDispatchFlag(setIdList);
	}
	
	/***
	 * 保存生成工单数据
	 * @param setIdList
	 * @param tbFaultBillRefAlarmEntityList
	 * @param tbFaultBillToOutSysReqEntityList
	 * @param tbFaultBillEntityList
	 */
	protected void save(List<Map<String,Object> > setIdList
			,List<TbFaultBillRefAlarmEntity> tbFaultBillRefAlarmEntityList
			,List<TbFaultBillToOutSysReqEntity> tbFaultBillToOutSysReqEntityList
			,List<TbFaultBillEntity> tbFaultBillEntityList){
		tbFaultBillService.save(tbFaultBillEntityList);
		tbFaultBillRefAlarmService.save(tbFaultBillRefAlarmEntityList);
		tbFaultBillToOutSysReqService.save(tbFaultBillToOutSysReqEntityList);
		tbAlarmPendingService.updateAlarmPendingDispatchFlag(setIdList);
	}
	
	/***
	 * 实体转换设置
	 * @param alarmPendingEntityList
	 * @return
	 */
	protected void getUpdateAlarmPendingDispatchList(List<TbAlarmPendingEntity> alarmPendingEntityList,List<Map<String,Object> > setIdList){
		
		for(TbAlarmPendingEntity tbAlarmPendingEntity : alarmPendingEntityList){
			Map<String,Object> d = new HashMap<String,Object>();
			d.put("I_ID", tbAlarmPendingEntity.getI_ID());
			setIdList.add(d);
		}
	}
	
	/***
	 * 实体转换设置
	 * @param alarmPendingEntityList
	 * @param tbFaultBillEntity
	 * @return
	 */
	protected void getTbFaultBillToOutSysReqEntityList(List<TbAlarmPendingEntity> alarmPendingEntityList,TbFaultBillEntity tbFaultBillEntity,List<TbFaultBillToOutSysReqEntity> tbFaultBillToOutSysReqEntityList,Integer I_REF_TYPE){
		
		for(TbAlarmPendingEntity tbAlarmPendingEntity : alarmPendingEntityList){
			TbFaultBillToOutSysReqEntity tbFaultBillRefAlarmEntity  = new TbFaultBillToOutSysReqEntity();
			tbFaultBillRefAlarmEntity.setI_BILL_ID(tbFaultBillEntity.getI_ID());
			tbFaultBillRefAlarmEntity.setI_CREATE_TIME(DefaultStringUtil.getNowTime("yyyyMMddHHmmss"));
			tbFaultBillRefAlarmEntity.setI_ID(tbFaultBillRefAlarmService.getID());
			tbFaultBillRefAlarmEntity.setI_REF_ID(tbAlarmPendingEntity.getI_ID());
			tbFaultBillRefAlarmEntity.setI_REF_TYPE(I_REF_TYPE);
			tbFaultBillRefAlarmEntity.setI_TRY_CNT(0);
			tbFaultBillRefAlarmEntity.setI_FLAG(1);
			tbFaultBillToOutSysReqEntityList.add(tbFaultBillRefAlarmEntity);
		}
	}
	
	/***
	 * 实体转换设置
	 * @param alarmPendingEntityList
	 * @param tbFaultBillEntity
	 * @return
	 */
	protected void getTbFaultBillRefAlarmEntityList(List<TbAlarmPendingEntity> alarmPendingEntityList,TbFaultBillEntity tbFaultBillEntity,List<TbFaultBillRefAlarmEntity> tbFaultBillRefAlarmEntityList){
		
		for(TbAlarmPendingEntity tbAlarmPendingEntity : alarmPendingEntityList){
			TbFaultBillRefAlarmEntity tbFaultBillRefAlarmEntity  = new TbFaultBillRefAlarmEntity();
			tbFaultBillRefAlarmEntity.setI_ALARM_ID(tbAlarmPendingEntity.getI_ID());
			tbFaultBillRefAlarmEntity.setI_BILL_ID(tbFaultBillEntity.getI_ID());
			tbFaultBillRefAlarmEntity.setI_ID(tbFaultBillRefAlarmService.getID());
			tbFaultBillRefAlarmEntity.setI_ZIP_TIME(DefaultStringUtil.getNowTime("yyyyMMddHHmmss"));
			
			tbFaultBillRefAlarmEntity.setI_NE_MODEL(tbAlarmPendingEntity.getI_NE_MODEL());
			tbFaultBillRefAlarmEntity.setI_LARGE_CLASS(tbAlarmPendingEntity.getI_LARGE_CLASS());
			tbFaultBillRefAlarmEntity.setI_SMALL_CLASS(tbAlarmPendingEntity.getI_SMALL_CLASS());
			tbFaultBillRefAlarmEntityList.add(tbFaultBillRefAlarmEntity);
		}
	}
	
	
	protected TbFaultBillEntity getFaultBillEntity(TbAlarmPendingEntity tbAlarmPendingEntity){
		
		List<TbAlarmPendingEntity> list = new ArrayList<TbAlarmPendingEntity>();
		list.add(tbAlarmPendingEntity);
		
		TbFaultBillEntity tbFaultBillEntity = new TbFaultBillEntity();
		tbFaultBillEntity.setCITY_CODE(tbAlarmPendingEntity.getCITY_CODE());
		tbFaultBillEntity.setCITY_NAME(tbAlarmPendingEntity.getCITY_NAME());
		tbFaultBillEntity.setI_CREATE_TIME(DefaultStringUtil.getNowTime("yyyyMMddHHmmss"));
		tbFaultBillEntity.setI_CYCLE(tbAlarmPendingEntity.getI_CYCLE());
		tbFaultBillEntity.setI_DB_LEVEL(this.getI_DB_LEVEL());
		tbFaultBillEntity.setI_DB_TYPE(this.getI_DB_TYPE());
		tbFaultBillEntity.setI_DISPATCH(this.getI_DISPATCH());
		tbFaultBillEntity.setI_DISPATCH_LEVEL(this.getI_DISPATCH_LEVEL(tbAlarmPendingEntity));
		tbFaultBillEntity.setI_FLAG(1);
		tbFaultBillEntity.setI_ID(tbFaultBillService.getID());
		tbFaultBillEntity.setI_LARGE_CLASS(tbAlarmPendingEntity.getI_LARGE_CLASS());
		tbFaultBillEntity.setI_NE_MODEL(tbAlarmPendingEntity.getI_NE_MODEL());
		tbFaultBillEntity.setI_NET_ONE_LEVEL(this.getI_NET_ONE_LEVEL());
		tbFaultBillEntity.setI_NET_THREE_LEVEL(this.getI_NET_THREE_LEVEL());
		tbFaultBillEntity.setI_NET_TWO_LEVEL(this.getI_NET_TWO_LEVEL());
		tbFaultBillEntity.setI_OBJ_LEVEL(tbAlarmPendingEntity.getI_OBJ_LEVEL());
		tbFaultBillEntity.setI_OBJ_TYPE(tbAlarmPendingEntity.getI_OBJ_TYPE());
		tbFaultBillEntity.setI_SMALL_CLASS(tbAlarmPendingEntity.getI_SMALL_CLASS());
		tbFaultBillEntity.setI_SOURCE(this.getI_SOURCE());
		tbFaultBillEntity.setI_UPDATE_TIME(DefaultStringUtil.getNowTime("yyyyMMddHHmmss"));
		tbFaultBillEntity.setS_DELIMITED_CYCLE(tbAlarmPendingEntity.getS_DELIMITED_CYCLE());
		tbFaultBillEntity.setS_INST_ID(tbAlarmPendingEntity.getS_INST_ID());
		tbFaultBillEntity.setS_INST_NAME(tbAlarmPendingEntity.getS_INST_NAME());
		tbFaultBillEntity.setI_ZIP_FLAG(this.getI_ZIP_FLAG());
		tbFaultBillEntity.setI_ZIP_CNT(0);
		tbFaultBillEntity.setS_OUTSYS_NO("");
		
		tbFaultBillEntity.setS_NO(this.getNo(tbAlarmPendingEntity));
		tbFaultBillEntity.setS_TITLE(tbAlarmPendingEntity.getS_TITLE());
		tbFaultBillEntity.setS_MSG(this.getMsg(list));
		tbFaultBillEntity.setI_ZIP_FLAG(this.getI_ZIP_FLAG());
		tbFaultBillEntity.setI_ZIP_CNT(0);
		return tbFaultBillEntity;
	}
	
	protected abstract List<TbFaultBillEntity> getZipFaultBillList(TbAlarmPendingEntity tbAlarmPendingEntity);
	

	/***
	 * 返回是否压缩
	 * @return
	 */
	protected  Integer  getI_ZIP_FLAG(){
		return 2;
	}
	
	/***
	 * 返回派发状态(未派发)
	 * @return
	 */
	protected  Integer  getI_DISPATCH(){
		return 3;
	}
	
	/***
	 * 返回数据网
	 * @return
	 */
	protected  Integer  getI_NET_ONE_LEVEL(){
		return 1;
	}
	
	/***
	 * 返回GPRS
	 * @return
	 */
	protected  Integer  getI_NET_TWO_LEVEL(){
		return 1;
	}
	
	/***
	 * 返回其他
	 * @return
	 */
	protected  Integer  getI_NET_THREE_LEVEL(){
		return 1;
	}
	
	/***
	 * 返回自动
	 * @return
	 */
	protected  Integer  getI_SOURCE(){
		return 2;
	}
	
	/***
	 * 返回督办级别
	 * @return
	 */
	protected  Integer  getI_DB_LEVEL(){
		return 1;
	}
	
	/***
	 * 返回督办类型(性能督办)
	 * @return
	 */
	protected  Integer  getI_DB_TYPE(){
		return 1;
	}
	
	/***
	 * 返回督办级别
	 * @param tbAlarmPendingEntity
	 * @return
	 */
	protected  Integer  getI_DISPATCH_LEVEL(TbAlarmPendingEntity tbAlarmPendingEntity){
		return 2;
	}
	
	/***
	 * 实体转换设置
	 * @param alarmPendingEntityList
	 * @return
	 */
	protected TbFaultBillEntity getTbFaultBillEntity(List<TbAlarmPendingEntity> alarmPendingEntityList){
		TbAlarmPendingEntity tbAlarmPendingEntity =  alarmPendingEntityList.get(0);
		TbFaultBillEntity tbFaultBillEntity = this.getFaultBillEntity(tbAlarmPendingEntity);
		tbFaultBillEntity.setI_ZIP_CNT(alarmPendingEntityList.size());
		return tbFaultBillEntity;
	}

	
	
	/***
	 * 
	 * @param tbAlarmPendingEntity
	 * @return
	 */
	protected String getNo(TbAlarmPendingEntity tbAlarmPendingEntity){
		return tbAlarmPendingEntity.getS_DELIMITED_CYCLE().substring(0, 8)+"-"+tbFaultBillService.getID();
	}
	
	/***
	 * 
	 * @param tbAlarmPendingEntity
	 * @return
	 */
	protected abstract String getMsg(List<TbAlarmPendingEntity> alarmPendingEntityList);
	

	/***
	 * 通用文字
	 * @return
	 */
	protected abstract String getTitle(TbAlarmPendingEntity tbAlarmPendingEntity);
	


}
