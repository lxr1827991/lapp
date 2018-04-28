package com.park.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lxapp.AppContext;
import com.lxapp.appsession.AppSession;
import com.lxapp.appsession.bean.AppClient;
import com.lxapp.appsession.utils.AppSessionUtils;
import com.lxapp.common.JsonResult;
import com.lxapp.common.VcodeFactory;
import com.lxapp.common.bean.Vcode;
import com.lxapp.utils.AppUtils;
import com.lxapp.utils.RequestUtil;
import com.lxr.commons.exception.CallException;
import com.lxr.commons.exception.CheckException;
import com.lxr.commons.utils.ValidateuUitls;
import com.park.api.ServiceManage;
import com.park.api.common.BaseController;
import com.park.api.common.SMSManage;
import com.park.api.common.SMSServiceImpl;



/**
 * 登录授权
 * @author Administrator
 *
 */
@RequestMapping
@Controller
public class AuthenticationController extends BaseController{

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	@RequestMapping("login")
	@ResponseBody
	public Object perRegister(String account,String pwd) {
		
		AppClient client = new AppClient();
		client.setAccount(account);
		client.setPwd(pwd);
		
		ServiceManage.securityService.login(ServiceManage.securityService.checkUser(client));
		
		
		return JsonResult.getSuccessResult();

	}
	
	@RequestMapping("user/test")
	@ResponseBody
	public Object test(String vcode) {
		
		if(vcode!=null)AppSessionUtils.getSession().putAttr("code", vcode);
		
		JsonResult jsonResult = JsonResult.getSuccessResult(AppSessionUtils.getSession().getAttr("code"));
		jsonResult.setSid(AppSessionUtils.getSession().getId());
		
		return jsonResult;

	}
	
	@RequestMapping("test2")
	@ResponseBody
	public Object test2(String vcode) {
		
		System.out.println(AppUtils.getAppInterface().getAppid());
		
		AppSession session = AppSessionUtils.getSession();
		Object c = session.getAttr("code");
		if(c==null) {c="1";
		}else {
			c+="1";
			
		}
		
		if (c.toString().length()>2) {
			AppSessionUtils.getSession().setForever();
		}
		
		AppSessionUtils.getSession().putAttr("code", c);
		
		
		JsonResult jsonResult = JsonResult.getSuccessResult(AppSessionUtils.getSession().getAttr("code"));
		jsonResult.setSid(AppSessionUtils.getSession().getId());
		
		return jsonResult;

	}
	
	@RequestMapping("user/logout")
	@ResponseBody
	public Object logout() {
		
	/*	Subject subject = securityService.getSessionSubject();
		
		securityService.logout(subject);*/
		
		return JsonResult.getSuccessResult("退出成功");

	}
	
	
	public static void main(String[] args) {
		System.out.println();
	}
	
}
