package com.bigdata.sso;

import com.bigdata.common.DES;
import com.bigdata.common.MD5;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 描述：
 * 作者：廖明乾
 * 时间：2014-10-24 上午10:01:30
 */
public class RspWebVerify
{
    private static String desKey = "essences";//DES方式加密"账号|时间戳"的密钥
    private static String organizationKey = "4HD85A94LT3JCE94";//机构的KEY值



    public static void main(String[] args)
    {
        String url = "www.baidu.com";
        RspWebVerify rspWebVerify = new RspWebVerify();
        Map<String,String> map = rspWebVerify.generateParam();

        if(url.indexOf("?")>=0)
        {
            url +="&data_es="+map.get("data_es")+"&sign_es="+map.get("sign_es");
        }
        else
        {
            url +="?data_es="+map.get("data_es")+"&sign_es="+map.get("sign_es");
        }

        System.out.println("模拟商城产生的链接："+url);

        System.out.println("\n进行解密和验证：");
        boolean virify = rspWebVerify.verifyParam(map.get("data_es"), map.get("sign_es"));
        System.out.println("验证是否通过："+virify);
        System.out.println("chenyj|2015121215400".split("\\|").length);

    }
    /**
     * 描述：模拟商城的生成加密参数的过程
     * 作者：廖明乾
     * 时间：2014-10-24 上午11:25:46
     * @return Map 其中包含两个值data和sign
     */
    public Map<String,String> generateParam()
    {
        Map<String,String> map = new HashMap<String,String>();

        DES des = new DES(desKey);
        String dataPlain = "chenyj|2015121215400";//data参数明文
        System.out.println("账号|时间："+dataPlain);
        String signPlain = dataPlain+"|"+organizationKey;//sign参数明文

        String dataCrypt = des.encrypt(dataPlain, "UTF-8");//data参数加密产生密文

        String signCrypt = new MD5().getMD5ofStr(signPlain);//sign参数明文加密
        try
        {//DES加密会产生一些符号，不能在URL地址中，因此先转为URL的编码
            dataCrypt = URLEncoder.encode(dataCrypt, "utf-8");
        } catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        map.put("data_es", dataCrypt);
        map.put("sign_es", signCrypt);

        return map;
    }

    /**
     * 描述：模拟第三方机构的验证过程
     * 作者：廖明乾
     * 时间：2014-10-24 上午11:26:53
     * @param dataCrypt 从请求中获取的data参数
     * @param signCrypt 从请求中获取的sign参数
     * @return 验证是否通过，这里使用小时进行验证，时间差在一小时内验证通过
     */
    public static boolean verifyParam(String dataCrypt,String signCrypt)
    {
        DES des = new DES(desKey);
        try
        {//转换参数中的符号
            dataCrypt = URLDecoder.decode(dataCrypt, "UTF-8");


            String dataPlain = des.decrypt(dataCrypt, "utf-8");//解密data参数
            System.out.println("账号|时间："+dataPlain);

            String signPlain = dataPlain+"|"+organizationKey;//拼接自己的KEY生成sign明文
            String signMd5 = new MD5().getMD5ofStr(signPlain);//生成MD5摘要

            //对比根据data明文产生的MD5摘要和sign参数
            if(signMd5.equals(signCrypt))
            {//对比相同
                if(dataPlain != null && dataPlain.indexOf("|")>=0)
                {
                    //获取参数中的时间戳,并转换成时间
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
                    String timeStamp = dataPlain.split("\\|")[1];
                    Date time = dateFormat.parse(timeStamp);

                    //获取当前服务器时间
                    Date now = new Date();

                    if(Math.abs(time.getTime()-now.getTime())/1000.0/3600>1)
                    {//对比时间差异，超过一小时，验证不通过
                        return false;
                    }
                    else
                    {//不超过一小时
                        return true;
                    }
                }
                else
                {//解密data参数得到的明文格式不对
                    return false;
                }
            }
            else
            {//对比不同，说明信息被篡改，验证不通过
                return false;
            }

        } catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

    public static String getVerifyName(String data_es) {
        DES des = new DES(desKey);
        String dataPlain = des.decrypt(data_es, "utf-8");//解密data参数
        String[] arrayRs=dataPlain.split("\\|");
        if(arrayRs.length>0){
            return arrayRs[0];  //To change body of created methods use File | Settings | File Templates.
        }else {
            return null;
        }
    }
}
