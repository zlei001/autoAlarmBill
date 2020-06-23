package com.cattsoft.eomsteam.iot.alarm.record.busi.abs;

import java.text.NumberFormat;
import java.util.*;

import com.cattsoft.eomsteam.iot.alarm.smallClass.busi.SmallClassService;

import com.cattsoft.eomsteam.mtoppub.ssh3InitUtil.CattApplicationContextUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.cattsoft.eomsteam.iot.alarm.conf.entity.TbAlarmConfigEntity;
import com.cattsoft.eomsteam.iot.alarm.conf.entity.TbAlarmConfigRuleEntity;
import com.cattsoft.eomsteam.iot.alarm.log.entity.TbAlarmConfigLogEntity;
import com.cattsoft.eomsteam.iot.alarm.record.busi.AlarmRecordBusiService;
import com.cattsoft.eomsteam.iot.alarm.record.entity.TbAlarmRecordEntity;
import com.cattsoft.eomsteam.iot.alarm.record.service.TbAlarmRecordService;
import com.cattsoft.eomsteam.iot.alarm.tbl.service.TbAlarmTblQueryColService;
import com.cattsoft.eomsteam.uc.pub.db.DefaultStringUtil;

public abstract class AbsAlarmRecordBusiService implements AlarmRecordBusiService {
	
    /**
     * 日志对象
     */
    private static Logger logger = Logger.getLogger(AbsAlarmRecordBusiService.class);
	
	@Autowired
	private TbAlarmRecordService tbAlarmPendingService;
	
	@Autowired
	private TbAlarmTblQueryColService tbAlarmTblQueryColService;


	@Override
	public List<TbAlarmRecordEntity> doBusiPending(String delimitedPendingSql,
			TbAlarmConfigLogEntity tbAlarmConfigLogEntity,TbAlarmConfigEntity tbAlarmConfigEntity,List<TbAlarmConfigRuleEntity> alarmConfigRuleEntityList ) {
		// TODO Auto-generated method stub
		List<TbAlarmRecordEntity> delimitedPendingEntityList = new ArrayList<TbAlarmRecordEntity>();
 		List<Map<String,Object>> alarmPendingList = tbAlarmPendingService.getAlarmPendingList(delimitedPendingSql);
		if(!alarmPendingList.isEmpty()){
			Map<String,String> queryColData = tbAlarmTblQueryColService.getAlarmTblQueryColData(tbAlarmConfigEntity.getI_OBJ_TYPE()
																										,tbAlarmConfigEntity.getI_NE_MODEL()
																										,tbAlarmConfigEntity.getI_LARGE_CLASS()
																										,tbAlarmConfigEntity.getI_SMALL_CLASS()
																										,tbAlarmConfigEntity.getI_CYCLE()
																										,tbAlarmConfigEntity.getS_OBJ_TBL());
			Map<String,Object> pengdingData = tbAlarmPendingService.getAlarmPendingDataByRefId(tbAlarmConfigLogEntity.getI_REF_ID(), tbAlarmConfigLogEntity.getS_INST_CYCLE());
			for(Map<String,Object> pending : alarmPendingList){
				String S_INST_ID  = this.getS_INST_ID(pending);
				if(pengdingData.containsKey(S_INST_ID)){
					logger.info("数据已存在:告警策略ID:"+tbAlarmConfigLogEntity.getI_REF_ID()+",告警周期:"+ tbAlarmConfigLogEntity.getS_INST_CYCLE()+",告警对象ID:"+S_INST_ID+",告警主键:"+pengdingData.get(S_INST_ID));
				}else{
					TbAlarmRecordEntity tbAlarmRecordEntity = new TbAlarmRecordEntity();
					tbAlarmRecordEntity.setCITY_CODE(DefaultStringUtil.toString(this.getCITY_CODE(pending)));
					tbAlarmRecordEntity.setCITY_NAME(DefaultStringUtil.toString(this.getCITY_NAME(pending)));
					tbAlarmRecordEntity.setI_CREATE_TIME(DefaultStringUtil.getNowTime("yyyyMMddHHmmss"));
					tbAlarmRecordEntity.setI_CYCLE(tbAlarmConfigLogEntity.getI_CYCLE());
					tbAlarmRecordEntity.setI_FLAG(1);
					tbAlarmRecordEntity.setI_ID(tbAlarmPendingService.getID());
					tbAlarmRecordEntity.setI_OBJ_TYPE(tbAlarmConfigEntity.getI_OBJ_TYPE());
					tbAlarmRecordEntity.setI_REF_ID(tbAlarmConfigEntity.getI_ID());
					tbAlarmRecordEntity.setI_UPDATE_TIME(DefaultStringUtil.getNowTime("yyyyMMddHHmmss"));
					tbAlarmRecordEntity.setS_DELIMITED_CYCLE( tbAlarmConfigLogEntity.getS_INST_CYCLE());
					
					tbAlarmRecordEntity.setI_NE_MODEL(tbAlarmConfigEntity.getI_NE_MODEL());
					tbAlarmRecordEntity.setI_LARGE_CLASS(tbAlarmConfigEntity.getI_LARGE_CLASS());
					tbAlarmRecordEntity.setI_SMALL_CLASS(tbAlarmConfigEntity.getI_SMALL_CLASS());
					tbAlarmRecordEntity.setI_OBJ_SOURCE(1);
					tbAlarmRecordEntity.setI_OBJ_LEVEL(tbAlarmConfigEntity.getI_OBJ_LEVEL());
					
					tbAlarmRecordEntity.setS_NO(getS_AlarmNo(pending));
					
					tbAlarmRecordEntity.setS_INST_ID(this.getS_INST_ID(pending));
					tbAlarmRecordEntity.setS_INST_NAME(this.getS_INST_NAME(pending));
					tbAlarmRecordEntity.setSTATIS_TIME(this.getstatis_time( tbAlarmConfigLogEntity.getS_INST_CYCLE()));
					//定界指标title展示
					String  name = " 实际值为 ";
					StringBuffer failcode= new StringBuffer();
					for (TbAlarmConfigRuleEntity tbAlarmConfigRuleEntity:alarmConfigRuleEntityList) {

						name=name+queryColData.get(tbAlarmConfigRuleEntity.getS_COL_NAME());
						String str111=" ";
						if (queryColData.get(tbAlarmConfigRuleEntity.getS_COL_NAME()).contains("率")&&!"业务传输速率".equals(queryColData.get(tbAlarmConfigRuleEntity.getS_COL_NAME())) ){
							NumberFormat nf   =   NumberFormat.getPercentInstance();
							nf.setMinimumFractionDigits(2);
							str111=nf.format(Double.valueOf(pending.get(tbAlarmConfigRuleEntity.getS_COL_NAME()).toString()));
						}else {
							str111=pending.get(tbAlarmConfigRuleEntity.getS_COL_NAME()).toString();
						}



							 name= name +":"+str111+" ";
							 if (tbAlarmConfigRuleEntity.getS_COL_VAL_1()==null||"".equals(tbAlarmConfigRuleEntity.getS_COL_VAL_1())) {

							 }else {
								 String strper1=" ";
								 if (queryColData.get(tbAlarmConfigRuleEntity.getS_COL_NAME()).contains("率")&&!"业务传输速率".equals(queryColData.get(tbAlarmConfigRuleEntity.getS_COL_NAME())) ){
									 NumberFormat nf   =   NumberFormat.getPercentInstance();
									 nf.setMinimumFractionDigits(2);
									 strper1=nf.format(Double.valueOf(tbAlarmConfigRuleEntity.getS_COL_VAL_1()));
								 }else {
									 strper1=tbAlarmConfigRuleEntity.getS_COL_VAL_1();
								 }
								 name=name+" 策略值为 "+queryColData.get(tbAlarmConfigRuleEntity.getS_COL_NAME())+tbAlarmConfigRuleEntity.getS_COL_EXP_1()+strper1+" ";
							 }


							 if (tbAlarmConfigRuleEntity.getS_COL_VAL_EXP()==null||tbAlarmConfigRuleEntity.getS_COL_VAL_EXP().equals("")) {

							 }else {

								 String strper2=" ";
								 if (queryColData.get(tbAlarmConfigRuleEntity.getS_COL_NAME()).contains("率")&&!"业务传输速率".equals(queryColData.get(tbAlarmConfigRuleEntity.getS_COL_NAME())) ){
									 NumberFormat nf   =   NumberFormat.getPercentInstance();
									 nf.setMinimumFractionDigits(2);
									 strper2=nf.format(Double.valueOf(tbAlarmConfigRuleEntity.getS_COL_VAL_2()));
								 }else {
									 strper2=tbAlarmConfigRuleEntity.getS_COL_VAL_2();
								 }
								 name = name + queryColData.get(tbAlarmConfigRuleEntity.getS_COL_NAME()) + tbAlarmConfigRuleEntity.getS_COL_EXP_2() + strper2;

							 }

							 //加失败码统计
							 if (mapReflect(queryColData.get(tbAlarmConfigRuleEntity.getS_COL_NAME()))==null||mapReflect(queryColData.get(tbAlarmConfigRuleEntity.getS_COL_NAME())).equals("")) {

							 }else {
								 String beanId = "com.cattsoft.eomsteam.iot.alarm.smallClass." + mapReflect(queryColData.get(tbAlarmConfigRuleEntity.getS_COL_NAME()));
								 CattApplicationContextUtil.getBean(beanId);
								 SmallClassService smallClassService = (SmallClassService) CattApplicationContextUtil.getBean(beanId);
								 List<Map<String, Object>> resfailList = smallClassService.fault(tbAlarmConfigEntity.getI_OBJ_TYPE().toString(), this.mapCode(pending), tbAlarmConfigLogEntity.getS_INST_CYCLE(), tbAlarmConfigEntity.getI_CYCLE().toString(), queryColData.get(tbAlarmConfigRuleEntity.getS_COL_NAME()));
								 //List<Map<String,Object>> resfailList = failCodeService.faultApn()
							 failcode.append(" 失败码top5为");
							for (Map<String, Object> resfailtemp : resfailList) {
								if (resfailtemp.get("FAIL_CAUSE") == null) {
									failcode.append("  空 "  + ":" + resfailtemp.get("ATTACHMENT_FAIL_TIMES").toString().replace("null","空") + "  ");

								}else {
									failcode.append("  " + resfailtemp.get("FAIL_CAUSE") + ":" + resfailtemp.get("ATTACHMENT_FAIL_TIMES") + "  ");

								}
							}
						}
                        delimitedPendingEntityList.add(tbAlarmRecordEntity);
					}
					String S_TITLE = this.getS_TITLE(pending, tbAlarmConfigEntity,tbAlarmConfigLogEntity);
					tbAlarmRecordEntity.setS_TITLE(S_TITLE+name);
					
					String S_MSG = this.getS_MSG(pending, tbAlarmConfigEntity,alarmConfigRuleEntityList,tbAlarmConfigLogEntity,queryColData);
					tbAlarmRecordEntity.setS_MSG(S_MSG+name+failcode.toString());



				}
				
			}
		}
		
		return delimitedPendingEntityList;
	}
	
	 private String getstatis_time(String S_INST_CYCLE){
    	if(S_INST_CYCLE.length() > 8){
    		return S_INST_CYCLE.substring(0, 8);
    	}
        return S_INST_CYCLE;
    }
		
	

	/***
	 * 告警对象ID
	 * @param pending 代办数据
	 * @return
	 */
	protected  String getS_AlarmNo(Map<String,Object> pending){
		return tbAlarmPendingService.getNo();
	}
	
	/***
	 * 告警对象ID
	 * @param pending 代办数据
	 * @return
	 */
	protected abstract String getS_INST_ID(Map<String,Object> pending);
	
	/***
	 * 告警对象名称
	 * @param pending 代办数据
	 * @return
	 */
	protected abstract String getS_INST_NAME(Map<String,Object> pending);


	/***
	 * 告警地市code
	 * @param pending 代办数据
	 * @return
	 */
	protected abstract String getCITY_CODE(Map<String,Object> pending);

	/***
	 * 告警地市名称
	 * @param pending 代办数据
	 * @return
	 */
	protected abstract String getCITY_NAME(Map<String,Object> pending);

	/***
	 * 告警对象标题
	 * @param pending 代办数据
	 * @param tbDelimitedConfigEntity 配置数据
	 * @return
	 */
	protected abstract String getS_TITLE(Map<String,Object> pending,TbAlarmConfigEntity tbDelimitedConfigEntity,TbAlarmConfigLogEntity tbDelimitedConfigLogEntity);
	
	/***
	 * 告警对象内容
	 * @param pending 代办数据
	 * @param tbDelimitedConfigEntity 配置数据
	 * @param delimitedConfigRuleEntityList 规则列表
	 * @return
	 */
	protected String getS_MSG(Map<String,Object> pending,TbAlarmConfigEntity tbDelimitedConfigEntity,List<TbAlarmConfigRuleEntity> delimitedConfigRuleEntityList,TbAlarmConfigLogEntity tbDelimitedConfigLogEntity,Map<String,String> queryColData){
		StringBuffer str = new StringBuffer();
		str.append("告警标题:").append(this.getS_TITLE(pending, tbDelimitedConfigEntity, tbDelimitedConfigLogEntity));
		str.append("\n告警周期:").append(tbDelimitedConfigLogEntity.getS_INST_CYCLE());
		
		StringBuffer colDataMsg = new StringBuffer();
		Iterator<String> iterator = queryColData.keySet().iterator();
		while(iterator.hasNext()){
			String key = iterator.next();
			String val = queryColData.get(key);

			String str111="";
			if (val.contains("率")&&!"业务传输速率".equals(val)){
				NumberFormat nf   =   NumberFormat.getPercentInstance();
				nf.setMinimumFractionDigits(2);
				str111=nf.format(Double.valueOf(pending.get(key).toString()));
			}else {
				str111= (String) pending.get(key);
				if(str111==null||str111.equals("")){
					str111=" 空 ";
				}
			}


			if(colDataMsg.length() >0){
			   colDataMsg.append(",");
			}
			colDataMsg.append(val).append("：").append(str111);
		}
		str.append("\n指标描述:").append(colDataMsg);
		return str.toString();
	}



	protected abstract String mapName(Map<String,Object> pending); /*{
		//判断取那个字段
		Map<String, Object> maplist = new HashMap<String, Object>();
		String result=new String();
		maplist.put("roam_city_name","");
		maplist.put("city_name", "");
		maplist.put("customer_name", "");
		maplist.put("ci_name", "");
		maplist.put("eci_name", "");
		maplist.put("dst_ci_name", "");
		maplist.put("dst_eci_name", "");
		maplist.put("tac", "");
		maplist.put("dst_tac", "");
		maplist.put("sgsn_name", "");
		maplist.put("dst_u_name", "");
		maplist.put("ne_name", "");
		maplist.put("mme_name", "");
		maplist.put("imei_tac", "");
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		list.add(maplist);
		for (String entry : maplist.keySet()) {
			Boolean contains=pending.containsKey(entry);
			if (contains){
				result=(String) pending.get(entry);
			}
		}
		return result;
	}*/



	protected abstract String mapCode(Map<String,Object> pending);/* {
		//判断取那个字段
		Map<String, Object> maplist = new HashMap<String, Object>();
		String result=new String();
		maplist.put("roam_city_code","");
		maplist.put("ci","");
		maplist.put("apn","");
		maplist.put("dst_tac","");
		maplist.put("dst_u_ip","");
		maplist.put("imei_tac","");
		maplist.put("eci","");
		maplist.put("city_code","");
		maplist.put("tac","");
		maplist.put("dst_ci","");
		maplist.put("sgsn_c_ip","");
		maplist.put("mme_ip","");
		maplist.put("mme_name","");
		maplist.put("dst_eci","");
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		list.add(maplist);
		for (String entry : maplist.keySet()) {
			Boolean contains=pending.containsKey(entry);
			if (contains){
				result=(String) pending.get(entry);
				return result;
			}
		}
		return result;
	}*/

	protected  String mapReflect(String pending){
		String val =null;
		if (pending.equals("附着成功率")){
			val = "1";

		}
		else if (pending.equals("PDN连接成功率")){
			val = "2";

		}
		else if (pending.equals("S1网络切换成功率")){
			val = "3";

		}
		else if (pending.equals("X2切换成功率")){
			val = "4";

		}


		else if (pending.equals("HTTP请求成功率")){
			val = "5";

		}
		else if (pending.equals("DNS解析成功率")){
			val = "6";

		}
		return  val;
	}
}
