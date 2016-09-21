package com.lms.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.lms.service.RegisterService;
import com.lms.util.ValidatorUtil;

/**
 * Servlet implementation class IdCard
 */
public class IdCard extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IdCard() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		boolean data=false;
		
		String idcard=request.getParameter("idcard");
		if(StringUtils.isNotEmpty(idcard)&&ValidatorUtil.isIDCard(idcard)){
			RegisterService service=new RegisterService();
			if(!service.idcard(idcard)){
				data=true;
			}
		}
		out.write(JSON.toJSONString(data));
	}

}
