package com.lxapp;

import com.lxapp.appsession.bean.AppClient;
import com.lxapp.appsession.utils.AppSessionUtils;



public abstract class SecurityService {
	
	
	
	public abstract AppClient checkUser(AppClient subject);
	
	public void login(AppClient client) {
		
		AppSessionUtils.getSession().setAppClient(client);
	}
	
	
	public void logout(AppClient client){
		AppSessionUtils.getSession().setAppClient(null);
	}
	
	public boolean isLogin() {
		return AppSessionUtils.getSession().getAppClient()!=null;
	}
	
	public AppClient getAppClient() {
		return AppSessionUtils.getSession().getAppClient();

	}

}
