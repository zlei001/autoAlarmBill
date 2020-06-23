package com.cattsoft.eomsteam.iot.alarm.unit.impl;

import com.cattsoft.eomsteam.iot.alarm.conf.entity.TbAlarmConfigEntity;
import com.cattsoft.eomsteam.iot.alarm.unit.abs.AbsColUnitService;

import org.springframework.stereotype.Service;

import java.text.NumberFormat;

@Service("com.cattsoft.eomsteam.iot.delimited.unit.1")
public class RateColUnitService extends AbsColUnitService {


    @Override
    public String tranValUnit(String val, TbAlarmConfigEntity tbAlarmConfigEntity) {

        NumberFormat nf   =   NumberFormat.getPercentInstance();
        nf.setMinimumFractionDigits(2);
        val=nf.format(Double.valueOf(val));
        return val;
    }

}
