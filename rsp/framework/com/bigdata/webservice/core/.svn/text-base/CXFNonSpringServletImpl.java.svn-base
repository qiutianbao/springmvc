package com.bigdata.webservice.core;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Endpoint;

import com.bigdata.webservice.impl.HelloWordServiceImpl;
import org.apache.cxf.transport.servlet.CXFNonSpringServlet;

import com.bigdata.webservice.impl.HelloWordServiceImpl;

public class CXFNonSpringServletImpl extends CXFNonSpringServlet {


	/**
	 * 
	 */
	private static final long serialVersionUID = -2963437387304529373L;

	@Override
	public void loadBus(ServletConfig sc) {
		super.loadBus(sc);
		
		Object helloWordServiceImpl=new HelloWordServiceImpl();
		String helloWordServiceAddress="/helloWordService";
		Endpoint.publish(helloWordServiceAddress, helloWordServiceImpl);
		
	}

	@Override
	protected void invoke(HttpServletRequest request,
			HttpServletResponse response) throws ServletException {
		// TODO Auto-generated method stub
		//super.invoke(request, response);
		super.invoke(request, response);
		try {
			PrintWriter out = response.getWriter();
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
