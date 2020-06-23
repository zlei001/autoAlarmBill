package com.cattsoft.eomsteam.iot.sync.service.impl;

import javax.jws.WebService;


import com.cattsoft.eomsteam.iot.sync.service.SyncSheetService;


import net.sf.json.xml.XMLSerializer;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

//name暴露的服务名称, targetNamespace:命名空间,设置为接口的包名倒写(默认是本类包名倒写). endpointInterface接口地址
@WebService(name = "syncSheetState" ,targetNamespace ="http://service.sync.iot.eomsteam.cattsoft.com/" ,endpointInterface = "com.cattsoft.eomsteam.iot.sync.service.SyncSheetService")
public class SyncSheetServiceImpl implements SyncSheetService {
    @Override
    public String syncSheetState(String syncXml) {
//        JSONObject jsonObj = XML.toJSONObject(syncXml);
//        //JSONObject sss = (JSONObject)jsonObj.get("note");
//        JSONArray ss = jsonObj.getJSONArray("");
//        System.out.println(syncXml);


//        XMLSerializer xmlSerializer = new XMLSerializer();
//        String results = xmlSerializer.read(syncXml).toString();
//        System.out.println(results);
//        JSONObject jsonObject = JSONObject.fromObject(results);
//        System.out.println(jsonObject);
//        return "webservice demo get syncXml:"+syncXml;
        //private static void cacheTimer(){
            try{
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY, 18); // 控制时
                calendar.set(Calendar.MINUTE, 0);       // 控制分
                calendar.set(Calendar.SECOND, 0);       // 控制秒

                long timeInterval = 24 * 60 * 60 * 1000;//一天的间隔

                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        //TODO 需要做的事情
                        //umacMap.clear();
                    }
                },calendar.getTime(),timeInterval);
            }catch(Exception e){
                e.printStackTrace();
            }
        return  null;
    }
}