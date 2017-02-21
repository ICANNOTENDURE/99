package com.fh.util;

import com.alibaba.fastjson.JSON;
import com.fh.entity.system.doc.DocUser;
import com.fh.entity.vo.token.Token;
import com.fh.util.enums.UserType;
import com.fh.util.security.AESCoder;



public class TokenUtil  {
	
	protected static Logger logger = Logger.getLogger(TokenUtil.class);
	

	public static String docToken(DocUser docUser,String info){
		
		Token token=new Token();
		token.setAccounttType(UserType.DOC.getType());
		token.setAccount(docUser.getDocId());
		token.setLogDate(docUser.getDocLogindate());
		token.setExpDate(DateUtil.getExpDay(Const.APP_TOKEN_MAX_TIME));
		token.setInfoId(info);
		return AESCoder.aesCbcEncrypt(JSON.toJSONString(token),Const.APP_TOKEN_KEY);
	}
	

}
