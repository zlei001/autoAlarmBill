package com.cattsoft.eomsteam.iot.alarm.record.dao;

import java.util.List;
import java.util.Map;

import com.cattsoft.eomsteam.iot.alarm.record.entity.TbAlarmRecordEntity;

public interface TbAlarmRecordDao {
	
	/***
	 * 主键
	 * @return
	 */
	public Long getID();
	
	public List<Map<String, Object>> getAlarmPendingListByRefId(Long I_REF_ID,String S_DELIMITED_CYCLE);

	/***
	 * 统计sql
	 * @param sql 执行SQL
	 * @return
	 */
	public Map<String,Object> getAlarmPendingCount(String sql);
	
	/***
	 * 代办sql
	 * @param sql 执行SQ
	 * @return
	 */
	public List<Map<String,Object>> getAlarmPendingList(String sql);
	
	/***
	 * 
	 * @param delimitedPendingEntityList
	 */
	public void save(List<TbAlarmRecordEntity> delimitedPendingEntityList);
	
	/***
	 * 更新归档定界代办
	 * @param idInStr 字符串
	 */
	public void  updateRunningAlarmPendingEndById(String idInStr);
	/***
	 * 更新归档定界代办
	 * @param   idInStr 字符串
	 */
	public void  updateRunningAlarmPendingEndByIds(List<String>  idInStr);
}
