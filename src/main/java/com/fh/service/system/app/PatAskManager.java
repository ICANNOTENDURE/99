package com.fh.service.system.app;

import com.fh.entity.system.pat.PatAsk;



public interface PatAskManager {
	
	void saveAsk(PatAsk patAsk,String picStr)throws Exception;
}
