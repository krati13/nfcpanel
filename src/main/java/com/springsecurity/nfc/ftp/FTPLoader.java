package com.springsecurity.nfc.ftp;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import com.springsecurity.nfc.constants.Constants;

public class FTPLoader implements Constants{
	private FTPClient ftp = null;
    
	public FTPLoader() throws Exception{
        ftp = new FTPClient();
        ftp.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));
        int reply;
        ftp.connect(FTP_URL);
        reply = ftp.getReplyCode();
        if (!FTPReply.isPositiveCompletion(reply)) {
            ftp.disconnect();
            throw new Exception("Exception in connecting to FTP Server");
        }
        ftp.login(FTP_USERNAME, FTP_PASSWORD);
        ftp.setDefaultPort(PORT);
        ftp.setFileType(FTP.BINARY_FILE_TYPE);
        ftp.enterLocalPassiveMode();
    }
    
    public void uploadFile(InputStream inputStream, String fileName, String hostDir) throws Exception {
        try {
        	createDirectory(hostDir);
    		this.ftp.storeFile(hostDir + fileName, inputStream);
    		inputStream.close();
    		disconnect();
        } catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public void deleteFile(String filePath) throws Exception {
        try {
    		this.ftp.deleteFile(filePath);
    		disconnect();
        } catch (Exception e) {
			e.printStackTrace();
		}
    }
 
    private void disconnect(){
        if (this.ftp.isConnected()) {
            try {
                this.ftp.logout();
                this.ftp.disconnect();
            } catch (IOException f) {
                f.printStackTrace();
            }
        }
    }
    
    private void createDirectory(String hostDir) throws IOException {
    	String newDir = hostDir.replace(FTP_HOST_DIR_PREFIX, EMPTY);
    	String[] dirArr = newDir.split(BACKSLASH);
    	String newPath = FTP_HOST_DIR_PREFIX;
    	for(String dir : dirArr) { 
    		newPath = newPath+dir;
    		this.ftp.makeDirectory(newPath);
    		newPath = newPath+BACKSLASH;
    	}
    	
    }
}