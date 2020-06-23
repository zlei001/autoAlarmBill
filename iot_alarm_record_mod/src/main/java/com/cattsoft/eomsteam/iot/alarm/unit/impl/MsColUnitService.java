package com.cattsoft.eomsteam.iot.alarm.unit.impl;

import com.cattsoft.eomsteam.iot.alarm.conf.entity.TbAlarmConfigEntity;

import com.cattsoft.eomsteam.iot.alarm.unit.abs.AbsColUnitService;
import org.springframework.stereotype.Service;

@Service("com.cattsoft.eomsteam.iot.delimited.unit.3")
public class MsColUnitService  extends AbsColUnitService {


    @Override
    public String tranValUnit(String val, TbAlarmConfigEntity tbAlarmConfigEntity) {


        return val;
    }
}
