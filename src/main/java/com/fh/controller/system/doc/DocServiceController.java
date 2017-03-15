package com.fh.controller.system.doc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fh.controller.base.BaseController;
import com.fh.entity.JsonResult;
import com.fh.entity.Page;
import com.fh.entity.system.doc.DocInfo;
import com.fh.entity.system.doc.DocUser;
import com.fh.entity.vo.doc.DocServiceVO;
import com.fh.service.common.impl.CommonService;
import com.fh.service.system.dictionaries.impl.DictionariesService;
import com.fh.service.system.doc.impl.DocService;
import com.fh.util.Constants;
import com.fh.util.DelAllFile;
import com.fh.util.MD5;
import com.fh.util.PathUtil;
import com.fh.util.enums.AuditDocStatusEnum;

@Controller
@RequestMapping(value="/docservice")
public class DocServiceController extends BaseController {
	
	@Autowired
	private CommonService commonService;
	@Autowired
	private DocService docService;
	@Autowired
	private DictionariesService dictionariesService;
	
	/**显示列表
	 * @param page
	 * @return
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(Page page){
		ModelAndView mv = getModelAndView();
		mv.setViewName("system/doc/list_doc_service");
		page.setPd(this.getPageData());
		try{
			mv.addObject("docService",dictionariesService.listSubDictByParentId(Constants.DIC_DOC_SERVICE));
			mv.addObject("list", docService.listPage(page));
		} catch(Exception e){
		}
		return mv;
	}
	
	/**显示认证列表
	 * @param page
	 * @return
	 */
	@RequestMapping(value="/listAuth")
	public ModelAndView listAuth(Page page){
		ModelAndView mv = getModelAndView();
		mv.setViewName("system/doc/list_doc_service_auth");
		page.setPd(this.getPageData());
		page.getPd().put("ORDER", "t.audit_Flag");
		try{
			mv.addObject("status", AuditDocStatusEnum.getCombo(this.getPar("status")));
			List<DocServiceVO> docServiceVOs=docService.listPage(page);
			mv.addObject("list", docServiceVOs);
		} catch(Exception e){
			e.printStackTrace();
		}
		return mv;
	}
	
	/**保存或者新增
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/saveOrUpdate")
	@ResponseBody
	public Object saveOrUpdate(com.fh.entity.system.doc.DocService docService) throws Exception{
		
		Map<String, Object> conMapping=new HashMap<String, Object>();
		conMapping.put("docinfo_Id", docService.getDocinfoId());
		conMapping.put("service_Type", docService.getServiceType());
		List<com.fh.entity.system.doc.DocService> list=commonService.selectByEqCon(com.fh.entity.system.doc.DocService.class, conMapping); 
		if(list.size()>0){
			return new JsonResult<Object>(100,"以存在，不能重复添加");
		}
		docService.setAuditFlag(AuditDocStatusEnum.AUDIT_PASS.getCode());
		commonService.saveOrUpdate(docService);
		return new JsonResult<Object>();
	}

	/**检查是否重复
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/checkName/{name}")
	@ResponseBody
	public JsonResult<Object> checkName(@PathVariable String name) throws Exception{
		
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("doc_Account", name);
		List<DocUser> docUsers=commonService.selectByEqCon(DocUser.class, map);
		return new JsonResult<>(docUsers.size(),"");
	}
	/**
	 * 
	* @Title: deltp 
	* @Description: TODO(删除照片) 
	* @param @param id
	* @param @return
	* @param @throws Exception    设定文件 
	* @return JsonResult<Object>    返回类型 
	* @throws 
	* @author zhouxin   
	* @date 2016年12月29日 下午2:36:45
	 */
	@RequestMapping(value="/deltp")
	@ResponseBody
	public JsonResult<Object> deltp(String id) throws Exception{
		
		DocInfo docInfo=commonService.selectByPrimaryKey(DocInfo.class, id);
		DelAllFile.delFolder(PathUtil.PicPath()+docInfo.getDocPic());
		docInfo.setDocPic("");
		commonService.saveOrUpdate(docInfo);
		return new JsonResult<Object>();
	}
	@RequestMapping(value="/pwd")
	@ResponseBody
	public JsonResult<Object> pwd(String id) throws Exception{
		DocUser docUser=commonService.selectByPrimaryKey(DocUser.class, id);
		docUser.setDocPassword(MD5.md5("1"));
		commonService.saveOrUpdate(docUser);
		return new JsonResult<Object>();
	}
	
	
	@RequestMapping(value="/delete/{id}")
	@ResponseBody
	public Object delete(@PathVariable String id){
		commonService.deleteByPrimaryKey(com.fh.entity.system.doc.DocService.class, id);
		return new JsonResult<>(); 
	}
	
	@RequestMapping(value="/audit")
	@ResponseBody
	public Object audit() throws Exception{
		docService.audit(this.getPageData());
		return new JsonResult<>(); 
	}
}