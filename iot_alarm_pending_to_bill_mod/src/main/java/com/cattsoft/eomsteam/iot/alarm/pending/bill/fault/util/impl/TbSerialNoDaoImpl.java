package com.cattsoft.eomsteam.iot.alarm.pending.bill.fault.util.impl;


import com.cattsoft.eomsteam.iot.alarm.pending.bill.fault.util.TbOsDomainDao;
import com.cattsoft.eomsteam.iot.alarm.pending.bill.fault.util.TbSerialNo;
import com.cattsoft.eomsteam.iot.alarm.pending.bill.fault.util.TbSerialNoDao;
import com.cattsoft.eomsteam.uc.pub.db.uc_main2.dao.UCMainDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import util.DateUtil;
import util.StringUtil;

import java.util.*;

/**
 * TbSerialNoDaoImpl : 故障模块单据编号生成类
 * @author lanxianghua
 * 
 * 修改  luoshilong
    添加根据域，前缀，获取单据编码 方法：getBillNoForDomain 
    如 域编码-前缀-日期-流水号 (GZ-YH-20160202-002)
 *
 */
@Repository("com.catt.data.dao.app.dzywSite.pub.impl.TbSerialNoDaoImpl")
public class TbSerialNoDaoImpl extends UCMainDao implements TbSerialNoDao {

	/**
	 * 注入域Dao
	 * */
	@Autowired
	private TbOsDomainDao tbOsDomainDao;

	/** 
	 * 添加log日志
	 */
	private static Log log = LogFactory.getLog(TbSerialNoDaoImpl.class);
	
	/**
	 * getBillNo : (公共方法)根据不同的故障种类，生成不同的单据编号
	 * @param type 单据类型枚举
	 * @param iDomainId 域id
	 * @return String
	 */
	@Override
	public String getBillNo(TbSerialNo.Type type, String iDomainId, String sDomainName) {
		String sDomainCode = tbOsDomainDao.getDomainCodeByDomainName(sDomainName);
		String sDate       = getYearMonthDay();
		String sSerialNo   = getSerialNo(String.valueOf(type.getValue()), iDomainId);
		String prefix = "";
		if(StringUtil.checkStr(sDomainCode)){
			prefix = sDomainCode + "-";
		}
		String sDzywCode  = prefix + sDate + "-" + sSerialNo;
		return sDzywCode;
	}
	
	/**
	 * 获取前缀自定义的单据编号（不再以所属域代码为前缀）
	 * @param type 编号类型
	 * @param iDomainId 所属域
	 * @param dateFormat 日期类型
	 * @param  prefix 自定义前缀
	 * @return 单据编号
	 */
	@Override
	public String getBillNo(TbSerialNo.Type type, String iDomainId, String dateFormat, String prefix){
		if(!StringUtil.checkStr(dateFormat)){
			dateFormat = "yyyyMMdd";
		}
		String sDate = DateUtil.parseToString(new Date(), dateFormat);
		String sSerialNo = getSerialNo(String.valueOf(type.getValue()), iDomainId);
		if (prefix == null || "".equals(prefix) || "null".equals(prefix)) {
			prefix = prefix + "-";
		}
		String billNo = prefix  + sDate + "-" + sSerialNo;
		return billNo;
	}
	
	/**
	 * getYearMonthDay : 取得年月日
	 * @return (String)返回年月日
	 */
	public String getYearMonthDay(){
		Calendar cal =  Calendar.getInstance();
		int int_m    =  cal.get(Calendar.MONTH) + 1;
		int int_d    =  cal.get(Calendar.DATE);
		String month =  cal.get(Calendar.MONTH) + 1 + "";
		String day   =  cal.get(Calendar.DATE)  + "";
		String year  =  cal.get(Calendar.YEAR) + "";
		//月份少于10,前面要补0
		if(int_m < 10){
			month = "0" + (cal.get(Calendar.MONTH) + 1);
		}
		//天数少于10,前面要补0
		if(int_d < 10){
			day   = "0" + cal.get(Calendar.DATE) ;
		}
		String currentDate = year + month + day;
		return currentDate;
	}
	
	/**
	 * 获取流水号
	 * @param iType 单据类型
	 * @param iDomainId 域id
	 * @return String(流水号)
	 */
	@Override
	public String getSerialNo(String iType, String iDomainId){
		String sSerialNo = "";
		if(StringUtil.checkStr(iDomainId) && StringUtil.checkStr(iType)){
 			String currDate    = "'" + DateUtil.getNowTime("yyyy-MM-dd") + "'";    				   			//格式化当前日期方言
			String dDate       = datetimeTostring("DDATE","yyyy-MM-dd");                        //TbSerialNo表的DDATE字段
			StringBuffer upSql = new StringBuffer();
			upSql.append(" update TBSERIALNO set ISERIALNO = ISERIALNO + 1 ");
			upSql.append(" where ");
			upSql.append(" IDOMAINID = ").append(iDomainId);
			upSql.append(" and ITYPE = ").append(iType);
			upSql.append(" and DDATE = ").append("TO_DATE("+currDate+",'YYYY-MM-DD')");
			String sUpdateSql = upSql.toString();
			int count = this.updateBySql(sUpdateSql);
			if(count <= 0){
				//插入
				String iId = this.callIdGenratorProc2("TBSERIALNO", this.getDomain());
				StringBuffer insertSql = new StringBuffer();
				sSerialNo = "1";
				String dNowDate = getDate();  							//获取当前方言日期
				insertSql.append(" insert into TBSERIALNO(ISERIALNOID,IDOMAINID,DDATE,ITYPE,ISERIALNO)values(");
				insertSql.append(iId).append(",");
				insertSql.append(iDomainId).append(",");
				insertSql.append(dNowDate).append(",");
				insertSql.append(iType).append(",");
				insertSql.append(sSerialNo).append(")");  //默认流水号为1
				String sInsertSql = insertSql.toString();
				this.updateBySql(sInsertSql);
			}else{
				//查询
				List<Map<String, String>> rsList = new ArrayList<Map<String,String>>();
				StringBuffer selectSql = new StringBuffer();
				selectSql.append(" select tb.ISERIALNOID,tb.ISERIALNO from TBSERIALNO tb ");
				selectSql.append(" where 1=1 ");
				selectSql.append(" and tb.ITYPE = ").append(iType);
				selectSql.append(" and tb.IDOMAINID = ").append(iDomainId);
				selectSql.append(" and DDATE = ").append("TO_DATE("+currDate+",'YYYY-MM-DD')");
				String sSelectSql = selectSql.toString();
				rsList = this.queryBySql(sSelectSql);
				//若集合大于0
				if(rsList.size() > 0){
					Map<String, String> tempMap = rsList.get(0);
					if(StringUtil.checkObj(tempMap)){
						sSerialNo = StringUtil.toString(tempMap.get("ISERIALNO"));
					}
				}
			}
			//流水号格式化
			sSerialNo = formatSerialNo(sSerialNo);
			System.out.println("流水号是"+sSerialNo);
		}
		return sSerialNo;
	}

	public String getDate() {
		return "TO_DATE(TO_CHAR(SYSDATE,'YYYY-MM-DD'),'YYYY-MM-DD')";
	}

	public String datetimeTostring(String col, String format) {
		if (StringUtil.checkStr(format)) {
			if("yyyy-MM-dd".equals(format))
				format = "%Y-%m-%d";
			else if ("yyyy-MM".equals(format)) {
				format = "%Y-%m";
			}else if ("HH:mm:ss".equals(format)){
				format = "%H:%i:%s";
			}else if("MM-dd".equals(format)){
				format = "%m-%d";
			}else if("HH:mm".equals(format)){
				format = "%H:%i";
			}else if("mm:ss".equals(format)){
				format = "%i:%s";
			}else if("yyyy".equals(format)){
				format = "%Y";
			}else if("MM".equals(format)){
				format = "%m";
			}else if("dd".equals(format)){
				format = "%d";
			}else if("HH".equals(format)){
				format = "%H";
			}else if("mm".equals(format)){
				format = "%i";
			}else if("ss".equals(format)){
				format = "%s";
			}else if("yyyy-MM-dd HH:mm:ss.ms".equals(format)){
				format = "%Y-%m-%d %H:%i:%s.%f";
			}else{
				format = "%Y-%m-%d %H:%i:%s";
			}
		}else {
			format = "%Y-%m-%d %H:%i:%s";
		}
		//return "DATE_FORMAT(" + col + ", '" + format + "')";
		return "TO_DATE(" + col + ", '" + format + "')";
	}
	
	/**
	 * formatSerialNo : 格式化流水号
	 * @param sSerialNo 流水号
	 * @return String
	 */
	public String formatSerialNo(String sSerialNo){
		String sDecimalSerialNo = "";
		if(StringUtil.checkStr(sSerialNo)){
			if(sSerialNo.length() == 1){
				sDecimalSerialNo = "000" + sSerialNo;
			}else if(sSerialNo.length() == 2){
				sDecimalSerialNo = "00" + sSerialNo;
			}else if(sSerialNo.length() == 3){
				sDecimalSerialNo = "0" + sSerialNo;
			}else{
				sDecimalSerialNo = sSerialNo;
			}
		}else{
			sDecimalSerialNo = "0001";
		}
		return sDecimalSerialNo;
	}


	/***
	 * 取单个主键
	 * @return
	 */


	/***
	 * 删除表数据
	 * @param pkId 主键
	 * @return
	 */


	/***
	 * 查询详情
	 * @param pkId 主键
	 * @return
	 */

	/***
	 * 分页查询
	 * @param paramData 查询条件
	 * @return
	 */

}