package com.cattsoft.eomsteam.iot.stat.record.busi;

import java.util.List;
import java.util.Map;

import com.cattsoft.eomsteam.iot.stat.log.entity.TbStatSqlLogEntity;

public interface StatRecordBusiService {

	public void doBusi(TbStatSqlLogEntity tbStatRecordLogEntity ,List<Map<String,Object>> statResultList);
}
