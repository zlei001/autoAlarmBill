package com.cattsoft.eomsteam.iot.alarm.pending.bill.fault.service;

import java.util.List;
import java.util.Map;

import com.cattsoft.eomsteam.iot.alarm.pending.bill.fault.entity.TbFaultBillEntity;

public interface TbFaultBillService {

	/***
	 * 主键
	 * @return
	 */
	public Long getID() ;

	/***
	 * 保存工单列表
	 * @param tbFaultBillEntityList
	 */
	public void save(List<TbFaultBillEntity> tbFaultBillEntityList) ;
	
	public List<TbFaultBillEntity> getCityFaultBillByInst(String S_INST_ID,String S_INST_NAME);
	
	public List<TbFaultBillEntity>  getZipFaultBillByInst(String S_INST_ID, String S_INST_NAME,String CITY_CODE, String CITY_NAME);
	
	/***
	 * 压缩工单列表
	 * @param S_INST_ID
	 * @param S_INST_NAME
	 * @return
	 */
	public List<TbFaultBillEntity>  getZipFaultBillByInst(String S_INST_ID, String S_INST_NAME);
	
	public void updateZipUpdateTimeById(List<Map<String,Object> > setIdList) ;
	
	/***
	 * 查询指标是否已有在途工单
	 * @param S_INST_ID
	 * @param S_INST_NAME
	 * @param I_LARGE_CLASS
	 * @param I_SMALL_CLASS
	 * @return
	 */
	public List<TbFaultBillEntity>  getFaultBillByInst(String S_INST_ID, String S_INST_NAME,Integer I_LARGE_CLASS, Integer I_SMALL_CLASS);
}
