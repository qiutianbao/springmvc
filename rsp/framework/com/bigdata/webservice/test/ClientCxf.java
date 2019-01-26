package com.bigdata.webservice.test;

import com.bigdata.webservice.impl.HelloWordServiceImpl;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;

import com.bigdata.webservice.HelloWordService;
import com.bigdata.webservice.impl.HelloWordServiceImpl;

public class ClientCxf {
	public static void main(String[] args) {
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();  
	    factory.setServiceClass(HelloWordServiceImpl.class);
        factory.setAddress("http://localhost:8080/oa/services/helloWordService");  
        HelloWordService helloworld = (HelloWordService) factory.create();  
        
        /*Client client = ClientProxy.getClient(helloworld);
        HTTPConduit http =  (HTTPConduit) client.getConduit();
        
        HTTPClientPolicy httpClientPolicy = new HTTPClientPolicy();
        httpClientPolicy.setConnectionTimeout(36000);
        httpClientPolicy.setReceiveTimeout(32000);
        http.setClient(httpClientPolicy);*/
        
        
        System.out.println(helloworld.sayHello("123"));  
        System.exit(0); 
	}
}
 