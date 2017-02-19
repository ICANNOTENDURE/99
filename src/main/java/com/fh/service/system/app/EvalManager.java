package com.fh.service.system.app;

import java.util.List;

import com.fh.entity.Page;
import com.fh.entity.system.pat.AskEvalute;
import com.fh.entity.vo.pat.EvaluteVO;




public interface EvalManager {
	

	
	void saveEval(AskEvalute askEvalute)throws Exception;
	
	List<EvaluteVO> listEval(Page pg)throws Exception;
}
