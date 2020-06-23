package com.cattsoft.eomsteam.iot.alarm.smallClass.busi;

import com.cattsoft.eomsteam.iot.alarm.conf.entity.TbAlarmConfigEntity;
import com.cattsoft.eomsteam.iot.alarm.conf.entity.TbAlarmConfigRuleEntity;

import java.util.List;
import java.util.Map;

public interface SmallClassService {
    public List<Map<String,Object>> fault(String val, String code, String time, String cycle, String colname );
    public List<Map<String,Object>> faultApn(String code, String time, String cycle);
    public List<Map<String,Object>> faultCell( String code,String time,String cycle);
    public List<Map<String,Object>> faultCity( String code,String time,String cycle);
    public List<Map<String,Object>> faultGgsn( String code,String time,String cycle);
    public List<Map<String,Object>> faultLac( String code,String time,String cycle);
    public List<Map<String,Object>> faultTac( String code,String time,String cycle);
    public List<Map<String,Object>> faultTmnl( String code,String time,String cycle);
}
