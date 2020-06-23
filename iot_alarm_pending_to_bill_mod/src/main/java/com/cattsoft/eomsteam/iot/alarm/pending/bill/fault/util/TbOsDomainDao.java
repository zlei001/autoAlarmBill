package com.cattsoft.eomsteam.iot.alarm.pending.bill.fault.util;

import java.util.List;
import java.util.Map;

/***
 * 区域
 * @author pengshiyu
 *
 */
public interface TbOsDomainDao {

	/**
	 * getFtDomainCode : 查询域编号
	 * @param sDomainName
	 * @return String(域编号)
	 */
	public String getDomainCodeByDomainName(String sDomainName);

	/***
	 * 区域列表
	 * @return
	 */
	public List<Map<String,Object>> getDomainList();
	
	/**
	 * 根据域Id查找域名称
	 * @param dataDomainId 域ID
	 * @return map
	 */
	public Map<String,String> getSCityName(Integer dataDomainId);
	
	/**
	 * getFtDomainCode : 查询域编号
	 * @param iDomainId 枚举1,2,3,4
	 * @return String(域编号)
	 */
	public String getDomainCode(String iDomainId);
	
	/**
	 * 根据网元承包人ID查找域名称
	 * @param neUserId 网元承包人ID
	 * @return map
	 */
	public Map<String,String> getNeUserName(Integer neUserId);
}
