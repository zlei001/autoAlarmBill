package com.cattsoft.eomsteam.iot.alarm.conf.service;

import java.util.List;

import com.cattsoft.eomsteam.iot.alarm.conf.entity.TbAlarmConfigEntity;

/***
 * 定界服务
 * @author Administrator
 *
 */
public interface TbAlarmConfigService {

	/***
	 * 定界配置列表
	 * @return
	 */
	public List<TbAlarmConfigEntity> getAlarmConfigList();
	

	/***
	 * 定界配置
	 * @param I_ID 主键
	 * @return
	 */
	public TbAlarmConfigEntity getAlarmConfigById(Long I_ID);
	
	/***
	 * 定界配置
	 * @param tbDelimitedConfigEntity 配置数据
	 * @param S_INST_CYCLE 运行周期期
	 * @return
	 */
	public String getAlarmCountParentSql(TbAlarmConfigEntity tbDelimitedConfigEntity,String S_INST_CYCLE);
	
	/***
	 * 定界配置
	 * @param tbDelimitedConfigEntity 配置数
	 * @param S_INST_CYCLE 运行周期
	 * @return
	 */
	public String getAlarmPendingParentSql(TbAlarmConfigEntity tbDelimitedConfigEntity,String S_INST_CYCLE);
	
}
