package com.cattsoft.eomsteam.iot.alarm.pending.bill.fault.dao;

import java.util.List;
import java.util.Map;

import com.cattsoft.eomsteam.iot.alarm.pending.bill.fault.entity.TbFaultBillEntity;

public interface TbFaultBillDao {

	/***
	 * 主键
	 * @return
	 */
	public Long getID() ;

	/***
	 * 保存工单列表
	 * @param faultBillEntityList
	 */
	public void save(List<TbFaultBillEntity> faultBillEntityList) ;
	
	public List<Map<String, Object>> getCityFaultBillByInst(String S_INST_ID,String S_INST_NAME);
	
	public List<Map<String, Object>> getZipFaultBillByInst(String S_INST_ID, String S_INST_NAME,String CITY_CODE, String CITY_NAME);
	
	/***
	 * 
	 * @param S_INST_ID   维度ID
	 * @param S_INST_NAME 维度名称
	 * @return
	 */
	public List<Map<String, Object>> getZipFaultBillByInst(String S_INST_ID,String S_INST_NAME);
	
	public void updateZipUpdateTimeById(List<Map<String,Object> > setIdList) ;
	
	public List<Map<String, Object>> getFaultBillByInst(String S_INST_ID, String S_INST_NAME, Integer I_LARGE_CLASS,Integer I_SMALL_CLASS) ;
}
