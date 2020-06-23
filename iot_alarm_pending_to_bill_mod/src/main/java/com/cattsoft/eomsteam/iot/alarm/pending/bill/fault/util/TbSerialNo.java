package com.cattsoft.eomsteam.iot.alarm.pending.bill.fault.util;

import java.io.Serializable;



public class TbSerialNo implements Serializable {

	/**
	 * 系列
	 */
	private static final long serialVersionUID = 8765621485162121904L;

	
	public enum Type {
		/**
		 * 模板
		 */
		DEF_TPL(2000, "模版"),
//
//		/**资源纠错流程*/
//		RESOURCECORREC(2001,"资源纠错流程"),
//		/**帐号管理流程*/
//		REGISTERACCOUNT(4001,"帐号管理流程"),
//
//		/**作业计划任务执行*/
//		TASKPLAN(3001,"作业任务执行流程");
		/**三盯关联*/
		ARALM(2001,"告警派单");

		private Integer value;

		private String name;

		Type(Integer value, String name) {
			this.value = value;
			this.name = name;
		}

		/**
		 * 取枚举
		 * @param value
		 * @return
		 */
		public static Type getEnum(Integer value) {
			Type[] types = Type.values();
			for (int i = 0; i < types.length; i++) {
				if (types[i].getValue().equals(value)) {
					return types[i];
				}
			}
			return DEF_TPL;
		}

		/**
		 * 
		 * 取key
		 * @return
		 */
		public Integer getValue() {
			return this.value;
		}

		/**
		 * 
		 * 取value
		 * @return
		 */
		public String getName() {
			return this.name;
		}
	}


}
