package com.cattsoft.eomsteam.iot.alarm.record.service;

import java.util.List;
import java.util.Map;

import com.cattsoft.eomsteam.iot.alarm.record.entity.TbAlarmRecordEntity;


public interface TbAlarmRecordService {
	

	/***
	 * 主键
	 * @return
	 */
	public String getNo();
	
	/***
	 * 主键
	 * @return
	 */
	public Long getID();

	/***
	 * 
	 * @param I_REF_ID
	 * @param S_DELIMITED_CYCLE
	 * @return
	 */
	public Map<String,Object> getAlarmPendingDataByRefId(Long I_REF_ID,String S_DELIMITED_CYCLE);

	/***
	 * 统计sql
	 * @param sql 执行SQ
	 * @return
	 */
	public Long getAlarmPendingCount(String sql);
	
	/***
	 * 代办sql
	 * @param sql 执行SQ
	 * @return
	 */
	public List<Map<String,Object>> getAlarmPendingList(String sql);
	
	/***
	 * 批量保存代办
	 * @param delimitedPendingEntityList
	 */
	public  void save(List<TbAlarmRecordEntity> delimitedPendingEntityList);
	
	public void  updateAlarmConfigLogEndFlagById(String  idInStr);
	public void  updateAlarmConfigLogEndFlagByIds(List<String>  idInStr);
}
