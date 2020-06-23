package com.cattsoft.eomsteam.iot.alarm.conf.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cattsoft.eomsteam.iot.alarm.conf.dao.TbAlarmConfigDao;
import com.cattsoft.eomsteam.iot.alarm.conf.entity.TbAlarmConfigEntity;
import com.cattsoft.eomsteam.iot.alarm.conf.service.TbAlarmConfigService;
import com.cattsoft.eomsteam.iot.alarm.tbl.entity.TbAlarmTblQueryColEntity;
import com.cattsoft.eomsteam.iot.alarm.tbl.service.TbAlarmTblQueryColService;

@Service("com.cattsoft.eomsteam.iot.alarm.conf.service.TbAlarmConfigService") 
public class TbAlarmConfigServiceImpl implements TbAlarmConfigService {

	@Autowired
	private TbAlarmConfigDao tbAlarmConfigDao;
	
	@Autowired
	private TbAlarmTblQueryColService tbAlarmTblQueryColService;
	
	@Override
	public List<TbAlarmConfigEntity> getAlarmConfigList() {
		List<TbAlarmConfigEntity> configEntityList = new ArrayList<TbAlarmConfigEntity>();
		List<Map<String,Object>> configList = tbAlarmConfigDao.getAlarmConfigList();
		for(Map<String,Object> config : configList){
			TbAlarmConfigEntity  tbAlarmConfigEntity = new TbAlarmConfigEntity();
			tbAlarmConfigEntity.setI_ID(Long.valueOf(String.valueOf(config.get("I_ID"))));
			tbAlarmConfigEntity.setI_OBJ_TYPE(Integer.valueOf(String.valueOf(config.get("I_OBJ_TYPE"))));
			tbAlarmConfigEntity.setI_CYCLE(Integer.valueOf(String.valueOf(config.get("I_CYCLE"))));
			tbAlarmConfigEntity.setS_TITLE(String.valueOf(config.get("S_TITLE")));
			tbAlarmConfigEntity.setS_REMKARK(String.valueOf(config.get("S_REMKARK")));
			tbAlarmConfigEntity.setI_NE_MODEL(Integer.valueOf(String.valueOf(config.get("I_NE_MODEL"))));
			tbAlarmConfigEntity.setI_LARGE_CLASS(Integer.valueOf(String.valueOf(config.get("I_LARGE_CLASS"))));
			tbAlarmConfigEntity.setI_SMALL_CLASS(Integer.valueOf(String.valueOf(config.get("I_SMALL_CLASS"))));
			tbAlarmConfigEntity.setI_OBJ_LEVEL(Integer.valueOf(String.valueOf(config.get("I_OBJ_LEVEL"))));
			configEntityList.add(tbAlarmConfigEntity);
		}
		return configEntityList;
	}

	@Override
	public TbAlarmConfigEntity getAlarmConfigById(Long I_ID) {
		Map<String,Object> config = tbAlarmConfigDao.getAlarmConfigById(I_ID);
		if(null != config){
			TbAlarmConfigEntity  tbAlarmConfigEntity = new TbAlarmConfigEntity();
			tbAlarmConfigEntity.setI_ID(Long.valueOf(String.valueOf(config.get("I_ID"))));
			tbAlarmConfigEntity.setI_OBJ_TYPE(Integer.valueOf(String.valueOf(config.get("I_OBJ_TYPE"))));
			tbAlarmConfigEntity.setI_CYCLE(Integer.valueOf(String.valueOf(config.get("I_CYCLE"))));
			tbAlarmConfigEntity.setS_TITLE(String.valueOf(config.get("S_TITLE")));
			tbAlarmConfigEntity.setS_REMKARK(String.valueOf(config.get("S_REMKARK")));
			tbAlarmConfigEntity.setI_NE_MODEL(Integer.valueOf(String.valueOf(config.get("I_NE_MODEL"))));
			tbAlarmConfigEntity.setI_LARGE_CLASS(Integer.valueOf(String.valueOf(config.get("I_LARGE_CLASS"))));
			tbAlarmConfigEntity.setI_SMALL_CLASS(Integer.valueOf(String.valueOf(config.get("I_SMALL_CLASS"))));
			tbAlarmConfigEntity.setI_OBJ_LEVEL(Integer.valueOf(String.valueOf(config.get("I_OBJ_LEVEL"))));
			return tbAlarmConfigEntity;
		}else{
			return null;
		}
	}

	@Override
	public String getAlarmCountParentSql(TbAlarmConfigEntity tbAlarmConfigEntity,String S_INST_CYCLE) {
		// TODO Auto-generated method stub
		StringBuffer countSql = new StringBuffer();
		countSql.append(" select ");
		countSql.append("   count(*) as \"cnt\"");
		countSql.append(" from ").append(tbAlarmConfigEntity.getS_OBJ_TBL());
		countSql.append(" where ").append(tbAlarmConfigEntity.getS_OBJ_TBL_WHERE().replace("?", S_INST_CYCLE));
		return countSql.toString();
	}

	@Override
	public String getAlarmPendingParentSql(TbAlarmConfigEntity tbAlarmConfigEntity, String S_INST_CYCLE) {

		//tbAlarmConfigEntity.g
		//tbAlarmConfigEntity.g
		List<TbAlarmTblQueryColEntity> queryColEntityList = tbAlarmTblQueryColService.geAlarmTblQueryColList(tbAlarmConfigEntity.getI_OBJ_TYPE()
																												,tbAlarmConfigEntity.getI_NE_MODEL()
																												,tbAlarmConfigEntity.getI_LARGE_CLASS()
																												,tbAlarmConfigEntity.getI_SMALL_CLASS()
																												,tbAlarmConfigEntity.getI_CYCLE()
																												,tbAlarmConfigEntity.getS_OBJ_TBL());
		String queryColSql  = this.getQueryColSql(queryColEntityList);
		StringBuffer countSql = new StringBuffer();
		countSql.append(" select ");
		countSql.append("    ").append(queryColSql);
		countSql.append(" from ").append(tbAlarmConfigEntity.getS_OBJ_TBL());
		countSql.append(" where ").append(tbAlarmConfigEntity.getS_OBJ_TBL_WHERE().replace("?", S_INST_CYCLE));
		return countSql.toString();
	}
	
	/***
	 * 查询字段列表
	 * @param queryColEntityList 字段列表
	 * @return
	 */
	private String getQueryColSql(List<TbAlarmTblQueryColEntity> queryColEntityList){
		StringBuffer queryColSql = new StringBuffer();
		if(null == queryColEntityList || queryColEntityList.isEmpty()){
			queryColSql.append(" * ");
		}else{
			for(TbAlarmTblQueryColEntity tbDelimitedTblQueryColEntity : queryColEntityList){
				if(queryColSql.length() >0){
					queryColSql.append(",");
				}
				queryColSql.append(tbDelimitedTblQueryColEntity.toSql());
			}
		}
		return queryColSql.toString();
	}

}
