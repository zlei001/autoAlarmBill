package com.cattsoft.eomsteam.iot.sync.dao;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@MapperScan
@Repository
public interface TbFaultBillToOutReqDao {

  /***
   *
   * 
   * @return
   */

  public void updateDispatchStatus(String S_NO);

}
