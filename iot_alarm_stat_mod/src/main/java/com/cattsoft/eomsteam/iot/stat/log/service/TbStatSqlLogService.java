package com.cattsoft.eomsteam.iot.stat.log.service;

import java.util.List;

import com.cattsoft.eomsteam.iot.stat.log.entity.TbStatSqlLogEntity;

/***
 * 配置日志接口服务
 * @author Administrator
 *
 */
public interface TbStatSqlLogService {

	/***
	 * 保持配置日志
	 * @param tbAlarmConfigLogEntity 日志实例
	 */
	public void save(TbStatSqlLogEntity tbAlarmConfigLogEntity);
	
	/***
	 * 代办
	 * @return
	 */
	public List<TbStatSqlLogEntity> getAlarmConfigLogList();
	
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
