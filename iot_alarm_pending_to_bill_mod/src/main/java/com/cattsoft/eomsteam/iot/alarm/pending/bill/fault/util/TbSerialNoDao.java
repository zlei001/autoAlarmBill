package com.cattsoft.eomsteam.iot.alarm.pending.bill.fault.util;



/**
 * 单据编号Dao
 * 
 * @author lanxianghua
 * 
 * 修改  luoshilong
    添加根据域，前缀，获取单据编码 方法：getBillNoForDomain 
    如 域编码-前缀-日期-流水号 (GZ-YH-20160202-002)
 *  
 * 
 */
public interface TbSerialNoDao {


	/**
	 * 取得单据编号
	 * 
	 * @param type
	 * @param iDomainId
	 * @param sDomainName
	 * @return
	 */
	public String getBillNo(TbSerialNo.Type type, String iDomainId, String sDomainName);

	/**
	 * 获取流水号
	 * @param iType 单据类型
	 * @param iDomainId 域id
	 * @return String(流水号)
	 */
	public String getSerialNo(String iType, String iDomainId);
	
	/**
	 * 获取前缀自定义的单据编号（不再以所属域代码为前缀）
	 * @param type 编号类型
	 * @param iDomainId 所属域
	 * @param dateFormat 日期类型
	 * @param  prefix 自定义前缀
	 * @return 单据编号
	 */
	public String getBillNo(TbSerialNo.Type type, String iDomainId, String dateFormat, String prefix);

}
