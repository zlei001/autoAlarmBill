package linux_lsinst_mod;

import com.cattsoft.eomsteam.uc.pub.db.DefaultStringUtil;

public class N {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(DefaultStringUtil.getStrNowTime("yyyyMM"));
		System.out.println(DefaultStringUtil.getNextMonth(DefaultStringUtil.getNowTime(), -1).replace("-", "").substring(0, 6));
		System.out.println(DefaultStringUtil.getNextMonth(DefaultStringUtil.getNowTime(), -2).replace("-", "").substring(0, 6));
	}

}
