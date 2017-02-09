package com.fh.service.system.app.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fh.dao.CrudSupport;
import com.fh.dao.DaoSupport;
import com.fh.entity.Page;
import com.fh.entity.system.pat.PatAsk;
import com.fh.entity.system.pat.PatAskSub;
import com.fh.entity.vo.ask.PatAskVO;
import com.fh.entity.vo.im.Message;
import com.fh.service.system.app.PatAskManager;
import com.fh.util.enums.AskType;
import com.fh.util.enums.MessageType;


@Service("patAskService")
public class PatAskService implements PatAskManager{
	
	@Autowired
	private CrudSupport crudSupport;
	
	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	@Override
	public void saveAsk(PatAsk patAsk, String picStr) throws Exception {
		
		patAsk.setAskDate(new Date());
		crudSupport.saveOrUpdate(patAsk);
		PatAskSub patAskSub=new PatAskSub();
		patAskSub.setAsksubDate(new Date());
		patAskSub.setParentId(patAsk.getAskId());
		patAskSub.setAsksubMessageType(MessageType.TEXT.getType());
		patAskSub.setAsksubContent(patAsk.getAskContent());
		patAskSub.setAsksubType(AskType.PAT.getType());
		crudSupport.saveOrUpdate(patAskSub);
		if(StringUtils.isNotBlank(picStr)){
			String[] pics=picStr.split("\\^");
			for(String path:pics){
				patAskSub.setAsksubId("");
				patAskSub.setAsksubContent("");
				patAskSub.setAsksubMessageType(MessageType.IMG.getType());
				patAskSub.setAsksubPath(path);
				crudSupport.saveOrUpdate(patAskSub);
			}
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PatAskVO> listAsk(Page pg) throws Exception {
		
		return (List<PatAskVO>) dao.findForList("AskMapper.listPage", pg);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Message> listAskSub(Page pg) throws Exception {
		// TODO Auto-generated method stub
		return (List<Message>) dao.findForList("AskMapper.listAskSubPage", pg);
	}

}
