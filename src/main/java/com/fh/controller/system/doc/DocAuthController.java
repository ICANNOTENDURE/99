package com.fh.controller.system.doc;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fh.controller.base.BaseController;
import com.fh.entity.JsonResult;
import com.fh.entity.Page;
import com.fh.entity.app.AppHop;
import com.fh.entity.app.AppLoc;
import com.fh.entity.system.doc.DocInfo;
import com.fh.entity.system.doc.DocInfoReq;
import com.fh.entity.system.doc.DocUser;
import com.fh.service.common.impl.CommonService;
import com.fh.service.system.dictionaries.impl.DictionariesService;
import com.fh.service.system.doc.impl.DocAuthService;
import com.fh.util.PageData;

@Controller
@RequestMapping(value="/docauth")
public class DocAuthController extends BaseController {
	
	@Autowired
	private CommonService commonService;
	@Autowired
	private DocAuthService docAuthService;
	@Autowired
	private DictionariesService dictionariesService;
	
	/**显示列表
	 * @param page
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(Page page) throws Exception{
		ModelAndView mv = getModelAndView();
		mv.setViewName("system/doc/list_doc_auth");
		page.setPd(this.getPageData());
		mv.addObject("list", docAuthService.listPage(page));
		mv.addObject("pd", this.getPageData());
		return mv;
	}
	
	
	@RequestMapping(value="/goAudit")
	public ModelAndView goAudit(String id) throws Exception{
		ModelAndView mv = getModelAndView();
		mv.setViewName("system/doc/auth_detail");
		
		DocInfo docInfo=commonService.selectByPrimaryKey(DocInfo.class, id);
		//医院
		if(StringUtils.isNotBlank(docInfo.getDocHopid())){
			AppHop appHop=commonService.selectByPrimaryKey(AppHop.class, docInfo.getDocHopid());
			mv.addObject("hopName",appHop.getHopName());
		}
		//科室
		if(StringUtils.isNotBlank(docInfo.getDocLocid())){
			AppLoc appLoc=commonService.selectByPrimaryKey(AppLoc.class, docInfo.getDocLocid());
			mv.addObject("locName",appLoc.getLocName());
		}
		//职称
		if(StringUtils.isNotBlank(docInfo.getDocTitle())){
			PageData pd=new PageData();
			pd.put("DICTIONARIES_ID", docInfo.getDocTitle());
			docInfo.setDocTitle(dictionariesService.findById(pd).getString("NAME"));
		}
		DocUser docUser=commonService.selectByPrimaryKey(DocUser.class, docInfo.getDocId());
		Map<String, Object> conMapping=new HashMap<String, Object>();
		conMapping.put("req_Info_Id", id);
		List<DocInfoReq> reqs=commonService.selectByEqCon(DocInfoReq.class, conMapping);
		Collections.sort(reqs,new Comparator<DocInfoReq>(){
            public int compare(DocInfoReq arg0, DocInfoReq arg1) {
                return arg0.getCreateDate().compareTo(arg1.getCreateDate());
            }
        });
		mv.addObject("reqs", reqs);
		mv.addObject("docAccount", docUser.getDocAccount());
		mv.addObject("docInfo", docInfo);
		return mv;
	}
	
	@RequestMapping(value="/audit")
	@ResponseBody
	public JsonResult<Object> aduit() throws Exception{
		
		PageData pg=this.getPageData();
		docAuthService.auidt(pg);
		return new JsonResult<>();
	}
	
}