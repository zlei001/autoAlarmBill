package com.cattsoft.eomsteam.iot.stat.record.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cattsoft.eomsteam.iot.stat.record.dao.TbStatSqlDao;
import com.cattsoft.eomsteam.iot.stat.record.entity.TbStatSqlEntity;
import com.cattsoft.eomsteam.iot.stat.record.service.TbStatSqlService;
import com.cattsoft.eomsteam.uc.pub.db.DefaultStringUtil;

@Service("com.cattsoft.eomsteam.iot.stat.record.service.TbStatSqlService")
public class TbStatSqlServiceImpl implements TbStatSqlService {
	
	@Autowired
	private TbStatSqlDao tbStatSqlDao;

	@Override
	public List<TbStatSqlEntity> getStatSqlList(){
		List<TbStatSqlEntity> tbStatSqlEntityList = new ArrayList<TbStatSqlEntity>();
		List<Map<String,Object>> statSqlList = tbStatSqlDao.getStatSqlList();
		for(Map<String,Object> statSqlData : statSqlList){
			TbStatSqlEntity tbStatSqlEntity = new TbStatSqlEntity();
			tbStatSqlEntity.setI_ID(DefaultStringUtil.toLong(statSqlData.get("I_ID")));
			tbStatSqlEntity.setI_OBJ_TYPE(DefaultStringUtil.toInteger(statSqlData.get("I_OBJ_TYPE")));
			tbStatSqlEntity.setI_STAT_TYPE(DefaultStringUtil.toInteger(statSqlData.get("I_STAT_TYPE")));
			tbStatSqlEntity.setS_OBJ_SQL(DefaultStringUtil.toString(statSqlData.get("S_OBJ_SQL")));
			tbStatSqlEntity.setI_STAT_SOURCE(DefaultStringUtil.toInteger(statSqlData.get("I_STAT_SOURCE")));
			tbStatSqlEntityList.add(tbStatSqlEntity);
		}
		return tbStatSqlEntityList;
	}

	@Override
	public List<Map<String, Object>> getStatResultSqlList(String sql) {
		// TODO Auto-generated method stub
		return tbStatSqlDao.getStatResultSqlList(sql);
	}
}
