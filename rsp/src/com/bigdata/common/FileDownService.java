package com.bigdata.common;

import com.bigdata.common.ftp.Ftp;
import com.bigdata.common.ftp.FtpUtil;
import com.bigdata.exception.BDRuntimeException;
import com.jcraft.jsch.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: bob
 * Date: 14-11-8
 * Time: 下午12:23
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class FileDownService {

    // FTP 地址
    private String ftp_host;
    private int ftp_port;
    private String ftp_username;
    private String ftp_password;

    private String ftp_km_docs_drive;

    private String km_docs_drive;



    @Value("#{configProperties['FTP.HOST']}")
    public void setFtp_host(String ftp_host) {
        this.ftp_host = ftp_host;
    }


    @Value("#{configProperties['FTP.PORT']}")
    public void setFtp_port(int ftp_port) {
        this.ftp_port = ftp_port;
    }


    @Value("#{configProperties['FTP.USERNAME']}")
    public void setFtp_username(String ftp_username) {
        this.ftp_username = ftp_username;
    }

    @Value("#{configProperties['FTP.PASSWORD']}")
    public void setFtp_password(String ftp_password) {
        this.ftp_password = ftp_password;
    }

    @Value("#{configProperties['KM.DOCS.DRIVE']}")
    public void setKm_docs_drive(String km_docs_drive) {
        this.km_docs_drive = km_docs_drive;
    }

    @Value("#{configProperties['FTP.KM.DOCS.DRIVE']}")
    public void setFtp_km_docs_drive(String ftp_km_docs_drive) {
        this.ftp_km_docs_drive = ftp_km_docs_drive;
    }

    public String downloadFile(String fileDir) throws BDRuntimeException {
        try{
        boolean isSuccess=false;
        Ftp f=new Ftp();
        f.setIpAddr(this.ftp_host.trim());
        f.setPort(this.ftp_port);
        f.setUserName(this.ftp_username.trim());
        f.setPwd(this.ftp_password.trim());

        /*f.setIpAddr("192.168.15.206");
        f.setUserName("administrator");
        f.setPwd("Gjzq123");*/

        String remoteBaseDir=fileDir.substring(this.ftp_km_docs_drive.length()-1,fileDir.lastIndexOf("\\")+1);
        //String remoteBaseDirFile=fileDir.substring(directory.length(),fileDir.lastIndexOf("\\")+1);
        String downloadFileName=fileDir.substring(fileDir.lastIndexOf("\\")+1);

        makeDir(new File(this.km_docs_drive+remoteBaseDir));
        FtpUtil.startDown(f, this.km_docs_drive+remoteBaseDir, remoteBaseDir,downloadFileName);//下载ftp文件测试
        }catch (Exception e){
            e.printStackTrace();
            throw  new BDRuntimeException("附件下载失败，附件不存在，或者其他原因导致，具体看日志情况. "+e.getMessage());
        }

        return fileDir;
    }

    public  void makeDir(File dir) {
        if(! dir.getParentFile().exists()) {
            makeDir(dir.getParentFile());
        }
        dir.mkdir();
    }
}
