package com.bigdata.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.log4j.Logger;


/**
 * 后台模拟http的form表单提交
 * @Title: HttpClientUtils.java
 * @Description: 
 * @version v1.0
 */
public class HttpClientUtils {
    
    
    private static final Logger logger = Logger.getLogger(HttpClientUtils.class);
    /**
     * 发送的参数编码
     */
    public static final String PARAM_ENCODING ="utf-8" ;
    
    /**
     * 创建Http请求，并提交表单 
     * @param url 请求的url地址
     * @param param 基本表单参数 类似<input type="text" name="name" value="123"> 其中 param的key为name value为123
     * @param file 表单中的file对象！
     * @return
     */
    
    public String createHttpClient(String url, Map<String,String> param,File[] file,HttpClient client) {
        PostMethod postMethod = new PostMethod(url);
        String response = null ;
        logger.info("createHttpClient请求的地址："+url);
        logger.info("createHttpClient请求的参数："+param);
        try {
            postMethod.getParams().setContentCharset(PARAM_ENCODING); // 设置参数编码
            getPostParam(postMethod,param);
            MultipartRequestEntity mrp = new MultipartRequestEntity(getPart(param, file), postMethod.getParams());
            postMethod.setRequestEntity(mrp);
            client.executeMethod(postMethod);
            response = postMethod.getResponseBodyAsString();
        } catch(ConnectException e){
            logger.info("ConnectException："+e.getMessage());
            e.printStackTrace();
            response = "false";
            return response;
        }catch (FileNotFoundException e) {
            logger.info("FileNotFoundException："+e.getMessage());
            e.printStackTrace();
            response = "false";
        } catch (HttpException e) {
            logger.info("HttpException："+e.getMessage());
            e.printStackTrace();
            response = "false";
        } catch (IOException e) {
            logger.info("IOException："+e.getMessage());
            e.printStackTrace();
            response = "false";
        }
        return response;
    }
    /**
     * 装入post的参数
     * @param method PostMethod 对象
     * @param param 参数 key=name ，value = value
     * @throws IllegalArgumentException
     * @throws java.io.UnsupportedEncodingException
     */
    private void getPostParam(PostMethod method ,Map param) throws IllegalArgumentException, UnsupportedEncodingException{
        method.addParameter("paramEncode",PARAM_ENCODING);
        int temp = 1 ;
        if(param!=null){
            for (Object key : param.keySet()) {
                method.addParameter(key.toString(),param.get(key).toString());
                temp++;
            }
        }
    }
    /**
     * 组装form表单参数
     * @param param 基本表单参数 类似<input type="text" name="name" value="123"> 其中 param的key为name value为123
     * @param file 表单中的file对象！
     * @throws java.io.IOException
     */
    private Part[] getPart(Map<String,String> param,File[] file) throws IOException{
        Part[] part = new Part[getPartSize(param,file)]; //表单参数数组
        part[0] = new StringPart("paramEncode", PARAM_ENCODING);
        int temp = 1 ;
        
        if(param!=null){
            for (Object key : param.keySet()) {
                part[temp] = new StringPart(key.toString(), param.get(key).toString(), PARAM_ENCODING);
                temp++;
            }
        }
        if( file!=null && file.length > 0){
        	for( File httpFile : file){
        		 part[temp] = new FilePart("formFile", httpFile.getName() ,httpFile);
        		 temp++;
        	}
        }
        return part;
    }
    
    /**
     * 初始化表单参数part长度
     * @param param 基本表单参数 类似< input type="text" name="name" value="123"> 其中 param的key为name value为123
     * @param file 表单中的file对象！
     * @return
     */
    private int getPartSize(Map<String,String> param,File[] file){
        int temp = 1 ; //临时变量用于存放参数数组的长度
        if(param!=null)
            temp += param.size();
        if(file!=null && file.length>0)
            temp = temp+file.length ;
        return temp ;
    }
    
   
    
    
    /**
     * 
     * @param files 文件集合
     * @param gid 业务ID
     * @param fileIds 文件ID
     * @param fileName 文件名称
     * @return
     * @throws java.io.UnsupportedEncodingException
     */
    public static String sendFileByHttp(File []files,String gid,List<String> fileIds,List<String> fileName) throws UnsupportedEncodingException{
 
    	EnvironmentConfig ec=EnvironmentConfig.getInstance();
    	
    	String fromAgentResult = "";  
        HttpClient client = new HttpClient();
        
        PostMethod filePost;
		filePost = new PostMethod(ec.getPropertyValue("/spring/property.properties", "fjUploadUrl")+"?gid="+gid+"&fileId="+ URLEncoder.encode(fileIds.toString().replace("[","").replace("]", "").replace(" ", ""),"utf-8")+"&fileNames="+ URLEncoder.encode(fileName.toString().replace("[","").replace("]", "").replace(" ", ""),"utf-8"));

//      MultipartPostMethod filePost = new MultipartPostMethod(msUrl);  
        // 若上传的文件比较大 , 可在此设置最大的连接超时时间   
//      client.getHttpConnectionManager(). getParams().setConnectionTimeout(8000);
        try {  
        	Part []part = new Part[files.length];
        	for ( int i = 0 , size = files.length; i<size ; i++ ){
        		File tempFile = files[i];
                FilePart fpart = new FilePart(URLEncoder.encode(tempFile.getName(), "utf-8"), tempFile);
                part[i] = fpart;
        	}
            
            MultipartRequestEntity mrp= new MultipartRequestEntity(part, filePost.getParams());  
            filePost.setRequestEntity(mrp);  

            //使用系统提供的默认的恢复策略  
            filePost.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,  
                new DefaultHttpMethodRetryHandler());  
  
            int httpStat = client.executeMethod(filePost);  
           
            
            System.out.println("httpStat----"+httpStat); 

            System.out.println(filePost.getResponseBodyAsString());
            if (!(httpStat == HttpStatus.SC_OK)) {  
                fromAgentResult = "0";  
            } else if (httpStat == HttpStatus.SC_OK) {  
            	if("1".indexOf(filePost.getResponseBodyAsString()) > -1 ){
            		fromAgentResult = "1";
            	}else{
            		fromAgentResult = "0"; 
            	}
            }  
            System.out.println(fromAgentResult);
        } catch (HttpException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        filePost.releaseConnection();
        return fromAgentResult;
    }
    
    
    
    public static void main(String[] args) {
    	EnvironmentConfig ec=EnvironmentConfig.getInstance();
    	System.out.println(ec.getPropertyValue("/Spring/property.properties", "scWebServiceUrl"));
    	System.out.println(ec.getPropertyValue("/Spring/property.properties", "fjUploadUrl"));
    	
    	
//    	
//    	
//    	String tempJson="[{\"gid\":\"10502\"," +
//		"\"mainPost\":\"张三|||无意见|||2012-12-12 12:21:22\"," +
//		"\"secPost\":\"张三|||无意见|||2012-12-12 12:21:22\"," +
//		"\"mainPostDeal\":\"张三|||无意见|||2012-12-12 12:21:22\"," +
//		"\"mainPostFileId\":\"10502\"," +
//		"\"mainPostFileName\":\"上证BUG列表.xlsx\"," +
//		"\"secPostDeal\":\"张三|||无意见|||2012-12-12 12:21:22\"," +
//		"\"secPostFileId\":\"10502\"," +
//		"\"secPostFileName\":\"上证BUG列表.xlsx\"," +
//		"\"innerHgUserDeal\":\"张三|||无意见|||2012-12-12 12:21:22\"," +
//		"\"hgResDeal\":\"张三|||无意见|||2012-12-12 12:21:22\"," +
//		"\"hgChiefInspectorDeal\":\"张三|||无意见|||2012-12-12 12:21:22\"}]";
//    	
//    	
//    	
//    	File file = new File("D:\\diskall\\上证BUG列表.xlsx");
//    	File tempFile = new File("D:\\diskall\\a.doc");
//    	
//    	String fromAgentResult = "";  
//        HttpClient client = new HttpClient();  
//         
//        
//        
//        PostMethod filePost = new PostMethod("http://172.17.31.230/dsoa_ty_shsc/Service/HgScWsServiceOpinionAttachment.aspx?gid=10502&fileId=1,2");  
////       MultipartPostMethod filePost = new MultipartPostMethod(msUrl);  
//        // 若上传的文件比较大 , 可在此设置最大的连接超时时间   
////         client.getHttpConnectionManager(). getParams().setConnectionTimeout(8000);    
//  
//        try {  
//  
//            FilePart fp = new FilePart(file.getName(), file);  
//            FilePart fpart = new FilePart(URLEncoder.encode(tempFile.getName(), "utf-8"), tempFile);
//            MultipartRequestEntity mrp= new MultipartRequestEntity(new Part[]{fp , fpart}, filePost.getParams());  
//            filePost.setRequestEntity(mrp);  
//  
//            //使用系统提供的默认的恢复策略  
//            filePost.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,  
//                new DefaultHttpMethodRetryHandler());  
//  
//            int httpStat = client.executeMethod(filePost);  
//           
//            
//            System.out.println("httpStat----"+httpStat); 
//            System.out.println(filePost.getResponseBodyAsString());
//            if (!(httpStat == HttpStatus.SC_OK)) {  
//                fromAgentResult = "connected fail:" + httpStat;  
//            } else if (httpStat == HttpStatus.SC_OK) {  
//            	
//            }  
//            System.out.println(fromAgentResult);
//        } catch (HttpException e) {  
//            e.printStackTrace();  
//        } catch (IOException e) {  
//            e.printStackTrace();  
//        }  
//        filePost.releaseConnection();  
//    	
//    	
    	
    	
//    	File []httpFile = new File[]{file,tempFile};
//    	
//    	
//        HttpClientUtils client = new HttpClientUtils();
//        HttpClient clients = new HttpClient(); // client 实例
//        Map<String,String> hashMap = new HashMap<String, String>();
//        hashMap.put("gid" , "10502");
//        hashMap.put("fileId", "");
//        client.createHttpClient("http://172.17.31.230/dsoa_ty_shsc/Service/HgScWsServiceOpinionAttachment.aspx?", null, httpFile, clients);
//        
////        String s = client.createHttpClient("http://10.100.19.243:8080/WebTest/servlet/TestServlet",map  ,null ) ;
//        String s = client.createHttpClient("https://10.100.0.51/imr/config/api.jsp?cmd=LOGIN&user=portalreport&pwd=SSIMgtja16829",null  ,null,clients ) ;
//        System.out.println(s);
//        String s1 = client.createHttpClient("https://10.100.0.51/imr/config/api.jsp?cmd=IEVENTS&iguid=792762516889140818",null  ,null,clients ) ;
//        System.out.println(s1);
//        String s2 = client.createHttpClient("https://10.100.0.51/imr/config/api.jsp?cmd=EVENTS&guid=6468:20110621102848:bc8e2d",null  ,null,clients ) ;
//        System.out.println(s2);
//        String s3 = client.createHttpClient("https://10.100.0.51/imr/config/api.jsp?cmd=LOGOFF",null  ,null,clients ) ;
//        System.out.println(s3);
        
    }
    
    /**
     * @Description: 根据传递的url和参数获得json内容
     * @param url
     * @return
     */
    public String getJsonByURL(String url){
//        logger.info("OA-----URL --------:"+url);
        HttpClient client = new HttpClient();
        String result = "["+createHttpClient(url, null, null, client)+"]" ;
//        logger.info("OA-----jsonResult --------:"+result);
        return result;
    }
}
