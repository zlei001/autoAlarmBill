package com.cattsoft.eomsteam.iot.alarm.pending.bill.fault.util;

/**
 * 地市枚举
 * @author liaoxiaojin
 *
 */
public enum DomainEnum {
	
	/**
	 * 省公司
	 */
	NOC(1030005L, "省公司");
	
	/**
	 * value
	 */
	private Long value;

	/**
	 *name 
	 */
	private String name;
	
	/**
	 * 枚举
	 * @param value 值
	 * @param name  名称
	 */
	DomainEnum(Long value, String name){
		this.value = value;
		this.name = name;
	}
	
	/**
	 * 取值
	 * @return
	 */
	public Long getValue() {
		return this.value;
	}

	/**
	 * 取中文名称
	 * @return
	 */
	public String getName() {
		return this.name;
	}
}
