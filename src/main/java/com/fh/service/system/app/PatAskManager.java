package com.fh.service.system.app;

import java.util.List;

import com.fh.entity.Page;
import com.fh.entity.system.pat.PatAsk;
import com.fh.entity.vo.ask.PatAskVO;
import com.fh.entity.vo.im.Message;



public interface PatAskManager {
	
	void saveAsk(PatAsk patAsk,String picStr)throws Exception;
	
	List<PatAskVO> listAsk(Page pg)throws Exception;
	
	List<Message> listAskSub(Page pg)throws Exception;
	
}
