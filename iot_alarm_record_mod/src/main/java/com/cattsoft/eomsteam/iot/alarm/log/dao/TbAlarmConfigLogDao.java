package com.cattsoft.eomsteam.iot.alarm.log.dao;

import java.util.List;
import java.util.Map;

import com.cattsoft.eomsteam.iot.alarm.log.entity.TbAlarmConfigLogEntity;

public interface TbAlarmConfigLogDao {

	/***
	 * 保持配置日志
	 * @param tbAlarmConfigLogEntity 日志实例
	 */
	public void save(TbAlarmConfigLogEntity tbAlarmConfigLogEntity);
	
	/***
	 * 代办
	 * @return
	 */
	public List<Map<String,Object>> getAlarmConfigLogList();
	
	/***
	 * 查询数据
	 * @param I_REF_ID   对象ID
	 * @param S_INST_CYCLE 周期
	 * @return
	 */
	public List<Map<String,Object>> getAlarmConfigLogByRefId(Long I_REF_ID,String S_INST_CYCLE);
	
	/***
	 * 更新配置日志
	 * @param I_ID 主键
	 */
	public void  updateAlarmConfigLogById(Long I_ID);
	
	/***
	 * 
	 * @param I_ID   主键
	 * @param I_PENDING_CNT 代办数量
	 * @param I_START_TIME  开始时间
	 * @param I_END_TIME    结束时间
	 */
	public void  updateRunningAlarmConfigLogById(Long I_ID,Long I_PENDING_CNT,Long I_START_TIME,Long I_END_TIME);
	
	
	/***
	 * 归档配置日志
	 * @param I_ID 主键
	 */
	public void  updateAlarmConfigLogEndFlagById(Long I_ID);
}
