/*
package com.cattsoft.eomsteam.iot.alarm.smallClass.busi.impl;

import com.cattsoft.eomsteam.iot.alarm.smallClass.busi.abs.AbsSmallClassService;
import org.springframework.stereotype.Service;

@Service("com.cattsoft.eomsteam.iot.alarm.smallClass.17")
public class SmallClass17Service extends AbsSmallClassService {



    @Override
    public String fault(String val, String code) {

        String Method =null;
        if (val=="1"){
            this.faultCity(code);
        }
        else if (val=="2"){
            this.faultTac(code);
        }
        else if (val=="3"){
            this.faultCell(code);
        }
        else if (val=="4")
        {
            this.faultGgsn(code);
        }
        else if (val=="5")
        {
            this.faultApn(code);
        }
        else if (val=="6")
        {
            this.faultTmnl(code);
        }
        else if (val=="7")
        {
            this.faultLac(code);
        }

        return val;
    }

    @Override
    public String faultApn(String code) {
        return null;
    }

    @Override
    public String faultCell(String code) {
        return null;
    }

    @Override
    public String faultCity(String code) {
        String tblName="TB_KR_IOT_HTTP_CITY_FAIL_DY";
        return null;
    }

    @Override
    public String faultGgsn(String code) {
        return null;
    }

    @Override
    public String faultLac(String code) {
        return null;
    }

    @Override
    public String faultTac(String code) {
        return null;
    }

    @Override
    public String faultTmnl(String code) {
        return null;
    }
}
*/
