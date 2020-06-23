package com.cattsoft.eomsteam.iot.alarm.pending.pending.service;

import java.util.List;
import java.util.Map;

import com.cattsoft.eomsteam.iot.alarm.pending.pending.entity.TbAlarmPendingEntity;


public interface TbAlarmPendingService {
	
	/***
	 * 设置派单标志
	 * @param setIdList
	 */
	public void updateAlarmPendingDispatchFlag(List<Map<String,Object> > setIdList);
	
	/***
	 * 多指标压缩数据
	 * @return
	 */
	public List<Map<String, Object>> getAlarmPendingNotCellZipCntList();
	
	/***
	 * 多指标压缩数据
	 * @return
	 */
	public List<Map<String, Object>> getAlarmPendingCellZipCntList();
	
	/***
	 * 压缩告警列表
	 * @param S_INST_ID
	 * @param S_INST_NAME
	 * @return
	 */
	public List<TbAlarmPendingEntity> getAlarmPendingZipListByInst(String S_INST_ID,String S_INST_NAME);
	/***
	 * 一级地市派单
	 * @return
	 */
	public List<TbAlarmPendingEntity> getAlarmPendingByCellOneLevel();
	
	/***
	 * 一级非地市派单
	 * @return
	 */
	public List<TbAlarmPendingEntity> getAlarmPendingByNotCellOneLevel();
	
	/***
	 * 二级地市派单
	 * @return
	 */
	public List<TbAlarmPendingEntity> getAlarmPendingByCellTwoLevel();
	
	/***
	 * 二级非地市派单
	 * @return
	 */
	public List<TbAlarmPendingEntity> getAlarmPendingByNotCellTwoLevel();
	

}
