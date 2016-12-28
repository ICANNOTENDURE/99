package com.fh.service.system.doc.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fh.dao.CrudSupport;
import com.fh.dao.DaoSupport;
import com.fh.dto.system.DocUserDto;
import com.fh.entity.Page;
import com.fh.entity.system.doc.DocInfo;
import com.fh.entity.system.doc.DocUser;
import com.fh.entity.systemdoc.DocSpecialDisease;
import com.fh.service.system.doc.DocUserManager;
import com.fh.util.MD5;
import com.fh.util.UuidUtil;


@Service("docUserService")
public class DocUserService implements DocUserManager{
	
	@Resource(name = "daoSupport")
	private DaoSupport dao;
	@Autowired
	private CrudSupport crudSupport;
	
	@Override
	public List<DocInfo> listPage(Page pg) throws Exception {
		@SuppressWarnings("unchecked")
		List<DocInfo> docInfos=(List<DocInfo>) dao.findForList("DocUserMapper.listPage", pg);
		return docInfos;
	}

	@Override
	public void saveDoc(DocUserDto dto) throws Exception {
		
		if(StringUtils.isNotBlank(dto.getDocInfo().getDocId())){
			DocUser docUser=crudSupport.selectByPrimaryKey(DocUser.class, dto.getDocInfo().getDocId());
			docUser.setStatus(dto.getDocUser().getStatus());
			dto.setDocUser(docUser);
		}else{
			dto.getDocUser().setDocPassword(MD5.md5("1"));
			dto.getDocUser().setDocLogindate(new Date());
			Map<String,Object> map=new HashMap<String, Object>();
			map.put("docinfo_Id", dto.getDocInfo().getInfoId());
			crudSupport.deleteByCondition(DocSpecialDisease.class, "docinfo_Id = ${conditionParam.docinfo_Id}", map);
		}

		crudSupport.saveOrUpdate(dto.getDocUser());
		dto.getDocInfo().setDocId(dto.getDocUser().getDocId());
		crudSupport.saveOrUpdate(dto.getDocInfo());
		if(StringUtils.isNotBlank(dto.getDiseasIds())){
			String[] ids=dto.getDiseasIds().split(",");
			List<DocSpecialDisease> diseases=new ArrayList<DocSpecialDisease>();
			for(int i=0;i<ids.length;i++){
				if(StringUtils.isBlank(ids[i].trim())) continue;
				diseases.add(new DocSpecialDisease(UuidUtil.get32UUID(),dto.getDocInfo().getInfoId(), ids[i]));
			}
			crudSupport.insertBatch(diseases);
		}
	}


	
	
	
	
}
