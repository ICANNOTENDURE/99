package com.fh.util;

import org.springframework.context.ApplicationContext;

public class Const {
	public static final String SESSION_SECURITY_CODE = "sessionSecCode";//验证码
	public static final String SESSION_USER = "sessionUser";			//session用的用户
	public static final String SESSION_ROLE_RIGHTS = "sessionRoleRights";
	public static final String sSESSION_ROLE_RIGHTS = "sessionRoleRights";
	public static final String SESSION_menuList = "menuList";			//当前菜单
	public static final String SESSION_allmenuList = "allmenuList";		//全部菜单
	public static final String SESSION_QX = "QX";
	public static final String SESSION_userpds = "userpds";			
	public static final String SESSION_USERROL = "USERROL";				//用户对象
	public static final String SESSION_USERNAME = "USERNAME";			//用户名
	public static final String WEBSOCKET_USERNAME="WEBSOCKETUSERNAME";
	public static final String TRUE = "T";
	public static final String FALSE = "F";
	public static final String LOGIN = "/login_toLogin.do";				//登录地址
	public static final String SYSNAME = "admin/config/SYSNAME.txt";	//系统名称路径
	public static final String PAGE	= "admin/config/PAGE.txt";			//分页条数配置路径
	public static final String EMAIL = "admin/config/EMAIL.txt";		//邮箱服务器配置路径
	public static final String SMS1 = "admin/config/SMS1.txt";			//短信账户配置路径1
	public static final String SMS2 = "admin/config/SMS2.txt";			//短信账户配置路径2
	public static final String FWATERM = "admin/config/FWATERM.txt";	//文字水印配置路径
	public static final String IWATERM = "admin/config/IWATERM.txt";	//图片水印配置路径
	public static final String WEIXIN	= "admin/config/WEIXIN.txt";	//微信配置路径
	public static final String WEBSOCKET = "admin/config/WEBSOCKET.txt";//WEBSOCKET配置路径
	public static final String PICPATH = "admin/config/PICPATH.txt";	//图片配置路径
	public static final String FILEPATHIMG = "uploadFiles/uploadImgs/";	//图片上传路径
	public static final String FILEPATHFILE = "uploadFiles/file/";		//文件上传路径
	public static final String UPLOADEXCEL = "system/hop/uploadexcel";		//上传excel公共页面
	public static final String FILEPATHTWODIMENSIONCODE = "uploadFiles/twoDimensionCode/"; //二维码存放路径
	public static final String NO_INTERCEPTOR_PATH = ".*/((login)|(logout)|(code)|(app)|(weixin)|(static)|(uploadFiles)|(plugins)|(main)|(websocket)).*";	//不对匹配该值的访问路径拦截（正则）
	public static final String APP_PATH=".*/(app).*";
	public static ApplicationContext WEB_APP_CONTEXT = null; //该值会在web容器启动时由WebAppContextListener初始化
	

	
	//app 加密账号的秘要
	public static final String APP_TOKEN_KEY="908067886@qq.com";
	//token失效时间 7天
	public static final int APP_TOKEN_MAX_TIME=7;
	//工程路径
	public static final String APP_URL="http://59.110.66.9/MVNFHM/";
	//图片访问路径
	public static final String APP_IMG_PATH="http://59.110.66.9/MVNFHM/uploadFiles/uploadImgs/";
	//阿里短信
	public static final String ALI_SMS_ACCESS_KEY="LTAICpVU09XNLBEC";
	public static final String ALI_SMS_ACCESS_SECRET="GxxsImlRane5MS8n4XVuM6mUc5oPy9";
	public static final String ALI_SMS_SignName="周鑫";
	public static final String ALI_SMS_TemplateCode="SMS_38530038";
	
	//验证码
	public static final String COOKIE_Verification_Code="COOKIE_Verification_Code";
	public static final int COOKIE_Max_Age=60;
}
