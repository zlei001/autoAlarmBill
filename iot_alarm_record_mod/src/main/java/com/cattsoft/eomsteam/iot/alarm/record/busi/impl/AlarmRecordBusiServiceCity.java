package com.cattsoft.eomsteam.iot.alarm.record.busi.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.cattsoft.eomsteam.iot.alarm.conf.entity.TbAlarmConfigEntity;
import com.cattsoft.eomsteam.iot.alarm.log.entity.TbAlarmConfigLogEntity;
import com.cattsoft.eomsteam.iot.alarm.record.busi.abs.AbsAlarmRecordBusiService;

@Service("com.cattsoft.eomsteam.iot.alarm.record.busi.1") 
public class AlarmRecordBusiServiceCity extends AbsAlarmRecordBusiService {
	@Override
	protected String getS_INST_ID(Map<String,Object> pending){
		StringBuffer str = new StringBuffer();
		str.append(mapCode(pending));
		return str.toString();
	}

	@Override
	protected String getS_INST_NAME(Map<String,Object> pending){
		StringBuffer str = new StringBuffer();

		str.append(mapName(pending));
		return str.toString();
	}

	@Override
	protected String getS_TITLE(Map<String,Object> pending,TbAlarmConfigEntity tbDelimitedConfigEntity,TbAlarmConfigLogEntity tbDelimitedConfigLogEntity){
		StringBuffer str = new StringBuffer();
		str.append("°æµÿ –:").append(mapName(pending)).append("°ø").append(tbDelimitedConfigEntity.getS_TITLE()).append(" ");
		return str.toString();
	}

	@Override
	protected String getCITY_CODE(Map<String, Object> pending) {
		StringBuffer str = new StringBuffer();
		str.append(pending.get("city_code"));
		return str.toString();
	}

	@Override
	protected String getCITY_NAME(Map<String, Object> pending) {
		StringBuffer str = new StringBuffer();

		str.append(pending.get("city_name"));
		return str.toString();
	}


	@Override
	protected String mapName(Map<String, Object> pending) {
		Map<String, Object> maplist = new HashMap<String, Object>();
		String result=new String();

		maplist.put("city_name", "");
		/*maplist.put("city_code","");*/
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		list.add(maplist);
		for (String entry : maplist.keySet()) {
			Boolean contains=pending.containsKey(entry);
			if (contains && pending.get(entry)!=null){
				result=(String) pending.get(entry);

				return result;
			}
		}
		if (result.equals("")){
			result=mapCode(pending);
		}
		return result;
	}
	@Override
	protected String mapCode(Map<String, Object> pending) {
		Map<String, Object> maplist = new HashMap<String, Object>();
		String result="";
		maplist.put("city_code","");
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		list.add(maplist);
		for (String entry : maplist.keySet()) {
			Boolean contains=pending.containsKey(entry);
			if (contains){
				result=(String) pending.get(entry);
				return result;
			}
		}
		return result;
	}


}
