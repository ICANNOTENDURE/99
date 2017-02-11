package com.fh.controller.app.pat;







import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.fh.controller.base.BaseController;
import com.fh.entity.JsonResult;
import com.fh.entity.Page;
import com.fh.entity.system.PatUser;
import com.fh.entity.system.doc.DocInfo;
import com.fh.entity.system.pat.PatAsk;
import com.fh.entity.vo.ask.PatAskVO;
import com.fh.entity.vo.ask.ReadyMsgVO;
import com.fh.entity.vo.im.Message;
import com.fh.entity.vo.token.Token;
import com.fh.plugin.annotation.AppToken;
import com.fh.service.common.impl.CommonService;
import com.fh.service.system.app.impl.PatAskService;
import com.fh.util.Const;
import com.fh.util.enums.AskStatus;
import com.fh.util.enums.UserType;
import com.fh.util.security.AESCoder;

@Controller
@RequestMapping(value="/apppatask")
@Api(value = "病人提问", tags = "病人提问") 
public class AppPatAskController extends BaseController{
	
	@Autowired
	private CommonService commonService;
	@Autowired
	private PatAskService patAskService;
	/**
	 * 插入病人主问题
	 * @return
	 * @throws Exception 
	 */
	@AppToken
	@ApiOperation(notes = "插入病人主问题",  value = "插入病人主问题")
	@RequestMapping(value="/ask",method = RequestMethod.POST)
	@ResponseBody
	public JsonResult<ReadyMsgVO> ask(
			@ApiParam(value = "token",name="APP_TOKEN") @RequestParam String APP_TOKEN,
			@ApiParam(value = "医生id",name="docId") @RequestParam String docId,
			@ApiParam(value = "家属id",name="famId") @RequestParam String famId,
			@ApiParam(value = "提问类容",name="askContent") @RequestParam String askContent,
			@ApiParam(value = "提问标题",name="askTitle") @RequestParam String askTitle,
			@ApiParam(value = "图标路径字符串",name="picStr",example="2.jpg^3.jpg^4.jpg") @RequestParam String picStr
		) throws Exception{
		JsonResult<ReadyMsgVO> result=new JsonResult<ReadyMsgVO>();
		PatAsk patAsk=new PatAsk();
		patAsk.setAskContent(askContent);
		patAsk.setAskDocid(docId);
		patAsk.setAskFamid(famId);
		patAsk.setAskTitle(askTitle);
		patAsk.setAskStatus(AskStatus.TO_PAY.getCode());
		patAsk.setAskPatid(getAppUserId());
		patAskService.saveAsk(patAsk, picStr);
		DocInfo docInfo=commonService.selectByPrimaryKey(DocInfo.class, docId);
		ReadyMsgVO readyMsgVO=new ReadyMsgVO();
		readyMsgVO.setAskId(patAsk.getAskId());
		readyMsgVO.setDocImg(docInfo.getDocPic());
		readyMsgVO.setDocName(docInfo.getDocName());
		PatUser patUser=commonService.selectByPrimaryKey(PatUser.class, getAppUserId());
		readyMsgVO.setPatImg(patUser.getUserImg());
		result.getDatas().add(readyMsgVO);
		return result;
	}
	
	/**
	 * 查询病人提问问题
	 * @return
	 * @throws Exception 
	 */
	@AppToken
	@ApiOperation(notes = "查询病人提问问题",  value = "查询病人提问问题")
	@RequestMapping(value="/listAsk",method = RequestMethod.GET)
	@ResponseBody
	public JsonResult<PatAskVO> listAsk(
			@ApiParam(value = "token",name="APP_TOKEN") @RequestParam String APP_TOKEN,
			@ApiParam(value = "提问状态",name="status") @RequestParam String status,
			@ApiParam(value = "一页的显示条数,传空默认为10",name="SHOW_COUNT") @RequestParam Long SHOW_COUNT,
			@ApiParam(value = "当前页数,不传默认为",name="CURRENT_PAGE") @RequestParam Long CURRENT_PAGE) throws Exception{
		JsonResult<PatAskVO> result=new JsonResult<PatAskVO>();
		Page pg=this.getAppPage();
		pg.getPd().put("patuserid", this.getAppUserId());
		result.setDatas(patAskService.listAsk(pg));
		return result;
	}
	
	/**
	 * 查询病人提问问题
	 * @return
	 * @throws Exception 
	 */
	@ApiOperation(notes = "查询病人提问明细,进入聊天界面",  value = "查询病人提问明细,进入聊天界面")
	@RequestMapping(value="/listAskSub",method = RequestMethod.GET)
	@ResponseBody
	public JsonResult<Message> listAskSub(
			@ApiParam(value = "token",name="APP_TOKEN") @RequestParam String APP_TOKEN,
			@ApiParam(value = "问题id",name="askId") @RequestParam String askId,
			@ApiParam(value = "一页的显示条数,传空默认为10",name="SHOW_COUNT") @RequestParam Long SHOW_COUNT,
			@ApiParam(value = "当前页数,不传默认为",name="CURRENT_PAGE") @RequestParam Long CURRENT_PAGE) throws Exception{
		JsonResult<Message> result=new JsonResult<Message>();

		List<Message> msgs=patAskService.listAskSub(getAppPage());
		if(StringUtils.isNotBlank(APP_TOKEN)){
			String str=AESCoder.aesCbcDecrypt(APP_TOKEN, Const.APP_TOKEN_KEY);
			Token token=JSON.parseObject(str, Token.class);
			for(Message msg:msgs){
				if(UserType.DOC.getType().equals(token.getAccounttType())){
					msg.setToUser(msg.getPatId());
				}else{
					msg.setToUser(msg.getDocId());
				}
			}
		}
		result.setDatas(msgs);
		return result;
	}
}
