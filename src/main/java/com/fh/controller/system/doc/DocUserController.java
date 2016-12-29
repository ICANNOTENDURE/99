package com.fh.controller.system.doc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.fh.controller.base.BaseController;
import com.fh.dto.system.DocUserDto;
import com.fh.entity.JsonResult;
import com.fh.entity.Page;
import com.fh.entity.app.AppDisease;
import com.fh.entity.app.AppHop;
import com.fh.entity.app.AppLoc;
import com.fh.entity.system.doc.DocInfo;
import com.fh.entity.system.doc.DocUser;
import com.fh.entity.systemdoc.DocSpecialDisease;
import com.fh.entity.vo.Select;
import com.fh.service.common.impl.CommonService;
import com.fh.service.system.doc.impl.DocUserService;
import com.fh.util.DateUtil;
import com.fh.util.DelAllFile;
import com.fh.util.FileUpload;
import com.fh.util.PageData;
import com.fh.util.PathUtil;

@Controller
@RequestMapping(value="/docuser")
public class DocUserController extends BaseController {
	
	@Autowired
	private CommonService commonService;
	@Autowired
	private DocUserService docUserService;
	
	/**显示列表
	 * @param page
	 * @return
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(Page page){
		ModelAndView mv = getModelAndView();
		try{
			docUserService.listPage(page);
		} catch(Exception e){
		}
		return mv;
	}
	
	/**去新增或者修改页面
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/goSaveOrUpdate")
	public ModelAndView goSaveOrUpdate(String id) throws Exception{
		ModelAndView mv =getModelAndView();
		PageData pd=this.getPageData();
		DocInfo docInfo=new DocInfo();
		DocUser docUser=new DocUser();
		DocUserDto dto=new DocUserDto();
		mv.setViewName("system/doc/edit");
		if(StringUtils.isNotBlank(id)){
			docInfo=commonService.selectByPrimaryKey(DocInfo.class, id);
			docUser=commonService.selectByPrimaryKey(DocUser.class, docInfo.getDocId());
			dto.setDocInfo(docInfo);
			dto.setDocUser(docUser);
			if(StringUtils.isNotBlank(docInfo.getDocLocid())){
				AppLoc appLoc=commonService.selectByPrimaryKey(AppLoc.class, docInfo.getDocLocid());
				dto.getDocInfo().setLocName(appLoc.getLocName());
			}
			if(StringUtils.isNotBlank(docInfo.getDocHopid())){
				AppHop appHop=commonService.selectByPrimaryKey(AppHop.class, docInfo.getDocHopid());
				dto.getDocInfo().setHopName(appHop.getHopName());
			}
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("docinfo_Id", dto.getDocInfo().getInfoId());
			List<DocSpecialDisease> docSpecialDiseases=commonService.selectByEqCon(DocSpecialDisease.class, map);
			if(docSpecialDiseases.size()>0){
				List<Select> selects=new ArrayList<Select>();
				List<String> strings=new ArrayList<String>();
				for(DocSpecialDisease docSpecialDisease:docSpecialDiseases){
					AppDisease appDisease=commonService.selectByPrimaryKey(AppDisease.class, docSpecialDisease.getDiseaseId());
					selects.add(new Select(docSpecialDisease.getDiseaseId(), appDisease.getDiseaseName()));
					strings.add(docSpecialDisease.getDiseaseId());
				}
				pd.put("diseaseSelect", JSON.toJSON(selects));
				pd.put("diseaseIds", JSON.toJSON(strings));
			}

		}
		mv.addObject("pd",dto);
		return mv;
	}
	
	/**保存或者新增
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/saveOrUpdate")
	public ModelAndView saveOrUpdate(@RequestParam(value="tp",required=false) MultipartFile file,DocUserDto dto) throws Exception{
		
		ModelAndView mv = this.getModelAndView();
		if(file!=null){
			String fileName = FileUpload.fileUp(file, PathUtil.PicPath(), DateUtil.getDays()+get32UUID());//执行上传
			dto.getDocInfo().setDocPic(fileName);
		}
		docUserService.saveDoc(dto);
		mv.setViewName("save_result");
		return mv;
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
	@RequestMapping(value="/deltp")
	@ResponseBody
	public JsonResult<Object> deltp(String id) throws Exception{
		
		DocInfo docInfo=commonService.selectByPrimaryKey(DocInfo.class, id);
		DelAllFile.delFolder(PathUtil.PicPath()+docInfo.getDocPic());
		docInfo.setDocPic("");
		commonService.saveOrUpdate(docInfo);
		return new JsonResult<Object>();
	}
}