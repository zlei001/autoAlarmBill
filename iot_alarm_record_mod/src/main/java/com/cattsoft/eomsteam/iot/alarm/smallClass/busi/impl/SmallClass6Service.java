package com.cattsoft.eomsteam.iot.alarm.smallClass.busi.impl;

import com.cattsoft.eomsteam.iot.alarm.smallClass.busi.abs.AbsSmallClassService;
import com.cattsoft.eomsteam.iot.alarm.smallClass.dao.FailCodeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("com.cattsoft.eomsteam.iot.alarm.smallClass.6")
public class SmallClass6Service extends AbsSmallClassService {
    @Autowired
    FailCodeDao failCodeDao;
    @Override
    public List<Map<String,Object>> fault(String val, String code, String time, String cycle, String colname) {
        List<Map<String,Object>> res =null;
        String Method =null;
        if (val.equals("1")){
            res=this.faultCity(code,time,cycle);
        }
        else if (val.equals("2")){
            res=this.faultTac(code,time,cycle);
        }
        else if (val.equals("3")){
            res= this.faultCell(code,time,cycle);
        }
        else if (val.equals("4"))
        {
            res= this.faultGgsn(code,time,cycle);
        }
        else if (val.equals("5"))
        {
            res= this.faultApn(code,time,cycle);
        }
        else if (val.equals("6"))
        {
            res= this.faultTmnl(code,time,cycle);
        }
        else if (val.equals("7"))
        {
            res= this.faultLac(code,time,cycle);
        }

        return res;


    }

    @Override
    public List<Map<String,Object>> faultApn(  String code,String time, String cycle) {
        Map<String,Object> valueMap= new HashMap<String,Object>();
        if (cycle.equals("1")) {
            String tblName = "TB_KR_IOT_DNS_APN_FAULT_DY";
            valueMap.put("tblName",tblName);
        }else if (cycle.equals("2")){
            String tblName = "TB_KR_IOT_DNS_APN_FAULT_HR";
            valueMap.put("tblName",tblName);
        }

        valueMap.put("time",time);
        valueMap.put("code",code);
        valueMap.put("queryCol","apn");
        List<Map<String,Object>> res =failCodeDao.getDnsTop5Fail(valueMap);
        return res;
    }

    @Override
    public List<Map<String,Object>> faultCell(  String code,String time, String cycle) {
        Map<String,Object> valueMap= new HashMap<String,Object>();
        if (cycle.equals("1")) {
            String tblName="TB_KR_IOT_DNS_CELL_FAULT_DY";
            valueMap.put("tblName",tblName);
        }else if (cycle.equals("2")){
            String tblName = "TB_KR_IOT_DNS_CELL_FAULT_HR";
            valueMap.put("tblName",tblName);
        }
        valueMap.put("time",time);
        valueMap.put("code",code);
        valueMap.put("queryCol","eci");
        List<Map<String,Object>> res =failCodeDao.getDnsTop5Fail(valueMap);
        return res;
    }

    @Override
    public List<Map<String,Object>> faultCity(  String code,String time, String cycle) {
        Map<String,Object> valueMap= new HashMap<String,Object>();
        if (cycle.equals("1")) {
            String tblName="TB_KR_IOT_DNS_CITY_FAULT_DY";
            valueMap.put("tblName",tblName);
        }else if (cycle.equals("2")){
            String tblName = "TB_KR_IOT_DNS_CITY_FAULT_HR";
            valueMap.put("tblName",tblName);
        }
        valueMap.put("time",time);
        valueMap.put("code",code);
        valueMap.put("queryCol","city_code");
        List<Map<String,Object>> res =failCodeDao.getDnsTop5Fail(valueMap);
        return res;
    }

    @Override
    public List<Map<String,Object>> faultGgsn(  String code,String time, String cycle) {
        Map<String,Object> valueMap= new HashMap<String,Object>();
        if (cycle.equals("1")) {
            String tblName="TB_KR_IOT_DNS_GGSN_FAULT_DY";
            valueMap.put("tblName",tblName);
        }else if (cycle.equals("2")){
            String tblName = "TB_KR_IOT_DNS_GGSN_FAULT_HR";
            valueMap.put("tblName",tblName);
        }
        valueMap.put("time",time);
        valueMap.put("code",code);
        valueMap.put("queryCol","dst_u_ip");
        List<Map<String,Object>> res =failCodeDao.getDnsTop5Fail(valueMap);
        return res;
    }

    @Override
    public List<Map<String,Object>> faultLac(  String code,String time, String cycle) {

        return null;
    }

    @Override
    public List<Map<String,Object>> faultTac( String code,String time, String cycle) {
        Map<String,Object> valueMap= new HashMap<String,Object>();
        if (cycle.equals("1")) {
            String tblName="TB_KR_IOT_DNS_TAC_FAULT_DY";
            valueMap.put("tblName",tblName);
        }else if (cycle.equals("2")){
            String tblName = "TB_KR_IOT_DNS_TAC_FAULT_HR";
            valueMap.put("tblName",tblName);
        }
        valueMap.put("time",time);
        valueMap.put("code",code);
        valueMap.put("queryCol","tac");
        List<Map<String,Object>> res =failCodeDao.getDnsTop5Fail(valueMap);
        return res;
    }

    @Override
    public List<Map<String,Object>> faultTmnl(  String code,String time, String cycle) {
        Map<String,Object> valueMap= new HashMap<String,Object>();
        if (cycle.equals("1")) {
            String tblName="TB_KR_IOT_DNS_TMNL_FAULT_DY";
            valueMap.put("tblName",tblName);
        }else if (cycle.equals("2")){
            String tblName = "TB_KR_IOT_DNS_TMNL_FAULT_HR";
            valueMap.put("tblName",tblName);
        }
        valueMap.put("time",time);
        valueMap.put("code",code);
        valueMap.put("queryCol","imei_tac");
        List<Map<String,Object>> res =failCodeDao.getDnsTop5Fail(valueMap);
        return res;

    }
}
