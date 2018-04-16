package com.gewei.wx.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.gewei.wx.util.Comm;
import com.gewei.wx.util.WchatUtil;

/**
 * Servlet implementation class InitToken
 */
public class InitToken extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected static final Logger logger = Logger.getLogger(InitToken.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InitToken() {
		super();
		// TODO Auto-generated constructor stub
		logger.info("初始化开始   获取 token 和 jsapi_ticket");
		WchatUtil.getToken_Tiket(Comm.appId, Comm.AppSecret);
		logger.info("初始化完成   获取 token 和 jsapi_ticket");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		WchatUtil.getToken_Tiket(Comm.appId, Comm.AppSecret);
		logger.info("初始化 获取 token 和 jsapi_ticket");
	}
}
