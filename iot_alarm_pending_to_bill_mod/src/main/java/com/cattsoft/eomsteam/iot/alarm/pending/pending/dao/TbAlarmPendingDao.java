package com.cattsoft.eomsteam.iot.alarm.pending.pending.dao;

import java.util.List;
import java.util.Map;

public interface TbAlarmPendingDao {
	
	/***
	 * 设置派单标志
	 * @param setIdList
	 */
	public void updateAlarmPendingDispatchFlag(List<Map<String,Object> > setIdList);
	
	/***
	 * 压缩告警列表
	 * @param S_INST_ID
	 * @param S_INST_NAME
	 * @return
	 */
	public List<Map<String, Object>> getAlarmPendingZipListByInst(String S_INST_ID,String S_INST_NAME);
	
	/***
	 * 地市多指标压缩数据
	 * @return
	 */
	public List<Map<String, Object>> getAlarmPendingNotCellZipCntList();
	
	/***
	 * 地市多指标压缩数据
	 * @return
	 */
	public List<Map<String, Object>> getAlarmPendingCellZipCntList();
	
	/***
	 * 一级地市派单
	 * @return
	 */
	public List<Map<String, Object>> getAlarmPendingByNotCellOneLevel();
	
	/***
	 * 一级非地市派单
	 * @return
	 */
	public List<Map<String, Object>> getAlarmPendingByCellOneLevel();
	
	/***
	 * 二级地市派单
	 * @return
	 */
	public List<Map<String, Object>> getAlarmPendingByCellTwoLevel();
	
	/***
	 * 二级非地市派单
	 * @return
	 */
	public List<Map<String, Object>> getAlarmPendingByNotCellTwoLevel();
}
