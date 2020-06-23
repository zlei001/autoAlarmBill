package com.cattsoft.eomsteam.iot.alarm.pending.bill.fault.busi.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cattsoft.eomsteam.iot.alarm.pending.bill.fault.busi.ZipPendingCommonBusiService;
import com.cattsoft.eomsteam.iot.alarm.pending.pending.entity.TbAlarmPendingEntity;

@Service("com.cattsoft.eomsteam.iot.alarm.pending.bill.fault.busi.ZipPendingCommonBusiService") 
public class ZipPendingCommonBusiServiceImpl implements ZipPendingCommonBusiService {

	/***
	 * 
	 * @param tbAlarmPendingEntity
	 * @return
	 */
	@Override
	public String getMsg(List<TbAlarmPendingEntity> alarmPendingEntityList){
		StringBuffer msg = new StringBuffer();
		for(TbAlarmPendingEntity tbAlarmPendingEntity : alarmPendingEntityList){
			msg.append(tbAlarmPendingEntity.getS_MSG());
			msg.append("<br/>");
		}
		
		return msg.toString();
	}

	/***
	 * 通用文字
	 * @return
	 */
	@Override
	public String getTitle(TbAlarmPendingEntity tbAlarmPendingEntity){
		if(1 == tbAlarmPendingEntity.getI_OBJ_TYPE()){
			return this.getCityTitle(tbAlarmPendingEntity);
		}else if(2 == tbAlarmPendingEntity.getI_OBJ_TYPE()){
			return this.getTacTitle(tbAlarmPendingEntity);
		}else if(3 == tbAlarmPendingEntity.getI_OBJ_TYPE()){
			return this.getCellTitle(tbAlarmPendingEntity);
		}else if(4 == tbAlarmPendingEntity.getI_OBJ_TYPE()){
			return this.getNeTitle(tbAlarmPendingEntity);
		}else if(5 == tbAlarmPendingEntity.getI_OBJ_TYPE()){
			return this.getApnTitle(tbAlarmPendingEntity);
		}
		StringBuffer title = new StringBuffer();
		title.append("发生多指标劣化预警");
		return title.toString();
	}
	
	/***
	 * 通用文字
	 * @return
	 */
	protected String getCommonTitle(){
		StringBuffer title = new StringBuffer();
		title.append("发生多指标劣化预警");
		return title.toString();
	}

	/***
	 * 
	 * @param tbAlarmPendingEntity
	 * @return
	 */
	protected String getCityTitle(TbAlarmPendingEntity tbAlarmPendingEntity){
		StringBuffer title = new StringBuffer();
		title.append(tbAlarmPendingEntity.getCITY_NAME()).append("地市物联网业务").append(getCommonTitle());
		return title.toString();
	}
	

	/***
	 * 
	 * @param tbAlarmPendingEntity
	 * @return
	 */
	protected String getTacTitle(TbAlarmPendingEntity tbAlarmPendingEntity){
		StringBuffer title = new StringBuffer();
		title.append("TAC:").append(tbAlarmPendingEntity.getS_INST_NAME()).append("物联网业务").append(getCommonTitle());
		return title.toString();
	}

	/***
	 * 
	 * @param tbAlarmPendingEntity
	 * @return
	 */
	protected String getCellTitle(TbAlarmPendingEntity tbAlarmPendingEntity){
		StringBuffer title = new StringBuffer();
		title.append(tbAlarmPendingEntity.getCITY_NAME()).append("小区物联网业务").append(getCommonTitle());
		return title.toString();
	}
	
	/***
	 * 网元物联网业务发生多指标劣化预警
	 * @param tbAlarmPendingEntity
	 * @return
	 */
	protected String getNeTitle(TbAlarmPendingEntity tbAlarmPendingEntity){
		StringBuffer title = new StringBuffer();
		title.append(tbAlarmPendingEntity.getS_INST_NAME()).append("网元物联网业务").append(getCommonTitle());
		return title.toString();
	}
	
	/***
	 * 
	 * @param tbAlarmPendingEntity
	 * @return
	 */
	protected String getApnTitle(TbAlarmPendingEntity tbAlarmPendingEntity){
		StringBuffer title = new StringBuffer();
		title.append("物联网APN:").append(tbAlarmPendingEntity.getS_INST_NAME()).append(getCommonTitle());
		return title.toString();
	}
	
}
