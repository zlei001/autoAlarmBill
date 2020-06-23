package com.cattsoft.eomsteam.iot.alarm.smallClass.dao.impl;

import com.cattsoft.eomsteam.iot.alarm.smallClass.dao.FailCodeDao;
import com.cattsoft.eomsteam.uc.pub.db.uc_main2.dao.UCMainDao;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@Repository("com.cattsoft.eomsteam.iot.alarm.tbl.dao.FailCodeDao")
public class FailCodeDaoImpl extends UCMainDao implements FailCodeDao {

    @Override
    public List<Map<String, Object>> getHttpTop5Fail( Map<String,Object> valueMap) {

        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT FAIL_CAUSE          as \"FAIL_CAUSE\"          ,ATTACHMENT_FAIL_TIMES     as \"ATTACHMENT_FAIL_TIMES\" FROM ( SELECT  RSP_CODE          as \"FAIL_CAUSE\"          ,HTTP_FAIL_NO     as \"ATTACHMENT_FAIL_TIMES\"  FROM  ");
        sql.append(valueMap.get("tblName"));
        sql.append(" where STATIS_TIME =  ? and  ");
        sql.append(valueMap.get("queryCol")+" = ? ");
        sql.append(" ORDER BY HTTP_FAIL_NO DESC ) where ROWNUM <= 5 ");
        List<Object> data = new ArrayList<Object>();
        data.add(valueMap.get("time"));
        data.add(valueMap.get("code"));
        List<Map<String,Object>> tempResultList =  this.queryBySql(sql.toString(), data);
        return tempResultList;
    }

    @Override
    public List<Map<String, Object>> getAttachTop5Fail(Map<String,Object> valueMap) {
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT FAIL_CAUSE          as \"FAIL_CAUSE\"          ,ATTACHMENT_FAIL_TIMES     as \"ATTACHMENT_FAIL_TIMES\" FROM ( SELECT  FAIL_CAUSE          as \"FAIL_CAUSE\"          ,ATTACHMENT_FAIL_TIMES     as \"ATTACHMENT_FAIL_TIMES\"  FROM  ");

        sql.append(valueMap.get("tblName"));
        sql.append(" where STATIS_TIME =  ? and  ");
        sql.append(valueMap.get("queryCol")+" = ? and \"TYPE\"= ?");
        sql.append(" ORDER BY ATTACHMENT_FAIL_TIMES DESC ) where ROWNUM <= 5 ");
        List<Object> data = new ArrayList<Object>();
        data.add(valueMap.get("time"));
        data.add(valueMap.get("code"));
        data.add("attach");
        List<Map<String,Object>> tempResultList =  this.queryBySql(sql.toString(), data);
        return tempResultList;
    }

    @Override
    public List<Map<String, Object>> getPdnTop5Fail(Map<String,Object> valueMap) {
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT FAIL_CAUSE          as \"FAIL_CAUSE\"          ,ATTACHMENT_FAIL_TIMES     as \"ATTACHMENT_FAIL_TIMES\" FROM ( SELECT  FAIL_CAUSE          as \"FAIL_CAUSE\"          ,ATTACHMENT_FAIL_TIMES     as \"ATTACHMENT_FAIL_TIMES\"  FROM  ");

        sql.append(valueMap.get("tblName"));
        sql.append(" where STATIS_TIME =  ? and  ");
        sql.append(valueMap.get("queryCol")+" = ? and \"TYPE\"= ?");
        sql.append(" ORDER BY ATTACHMENT_FAIL_TIMES DESC ) where ROWNUM <= 5 ");
        List<Object> data = new ArrayList<Object>();
        data.add(valueMap.get("time"));
        data.add(valueMap.get("code"));
        data.add("pdn");
        List<Map<String,Object>> tempResultList =  this.queryBySql(sql.toString(), data);
        return tempResultList;
    }

    @Override
    public List<Map<String, Object>> getX2Top5Fail(Map<String,Object> valueMap) {
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT FAIL_CAUSE          as \"FAIL_CAUSE\"          ,ATTACHMENT_FAIL_TIMES     as \"ATTACHMENT_FAIL_TIMES\" FROM ( SELECT  FAIL_CAUSE          as \"FAIL_CAUSE\"          ,ATTACHMENT_FAIL_TIMES     as \"ATTACHMENT_FAIL_TIMES\"  FROM  ");

        sql.append(valueMap.get("tblName"));
        sql.append(" where STATIS_TIME =  ? and  ");
        sql.append(valueMap.get("queryCol")+" = ? and \"TYPE\"= ?");
        sql.append(" ORDER BY ATTACHMENT_FAIL_TIMES DESC ) where ROWNUM <= 5 ");
        List<Object> data = new ArrayList<Object>();
        data.add(valueMap.get("time"));
        data.add(valueMap.get("code"));
        data.add("x2");
        List<Map<String,Object>> tempResultList =  this.queryBySql(sql.toString(), data);
        return tempResultList;
    }

    @Override
    public List<Map<String, Object>> getS1Top5Fail(Map<String,Object> valueMap) {
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT FAIL_CAUSE          as \"FAIL_CAUSE\"          ,ATTACHMENT_FAIL_TIMES     as \"ATTACHMENT_FAIL_TIMES\" FROM ( SELECT  FAIL_CAUSE          as \"FAIL_CAUSE\"          ,ATTACHMENT_FAIL_TIMES     as \"ATTACHMENT_FAIL_TIMES\"  FROM  ");

        sql.append(valueMap.get("tblName"));
        sql.append(" where STATIS_TIME =  ? and  ");
        sql.append(valueMap.get("queryCol")+" = ? and \"TYPE\"= ?");
        sql.append(" ORDER BY ATTACHMENT_FAIL_TIMES DESC ) where ROWNUM <= 5 ");
        List<Object> data = new ArrayList<Object>();
        data.add(valueMap.get("time"));
        data.add(valueMap.get("code"));
        data.add("s1");
        List<Map<String,Object>> tempResultList =  this.queryBySql(sql.toString(), data);
        return tempResultList;
    }

    @Override
    public List<Map<String, Object>> getDnsTop5Fail(Map<String,Object> valueMap) {
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT FAIL_CAUSE          as \"FAIL_CAUSE\"          ,ATTACHMENT_FAIL_TIMES     as \"ATTACHMENT_FAIL_TIMES\" FROM ( SELECT  RSP_CODE          as \"FAIL_CAUSE\"          ,DNS_RESOLUTION_F_TIMES     as \"ATTACHMENT_FAIL_TIMES\"  FROM  ");

        sql.append(valueMap.get("tblName"));
        sql.append(" where STATIS_TIME =  ? and  ");
        sql.append(valueMap.get("queryCol")+" = ? ");
        sql.append(" ORDER BY DNS_RESOLUTION_F_TIMES DESC ) where ROWNUM <= 5 ");
        List<Object> data = new ArrayList<Object>();
        data.add(valueMap.get("time"));
        data.add(valueMap.get("code"));
        List<Map<String,Object>> tempResultList =  this.queryBySql(sql.toString(), data);
        return tempResultList;
    }
}
