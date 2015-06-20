/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springsecurity.plugin.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

public class SaveImageFromUrl {

    //public static void main(String[] args) throws Exception {
    //   String imageUrl = "http://localhost:8084/quizuncle/home/welcome";
    //   String destinationFile = "D:/72863967519300.html";
   //    saveImage(imageUrl, destinationFile);
   //}

    public static void saveImage(String imageUrl, String destinationFile) {
        try {
        	
            URL url = new URL(imageUrl);
            InputStream is = url.openStream();
            OutputStream os;
                os = new FileOutputStream(destinationFile);
                byte[] b = new byte[2048];
                int length;
                while ((length = is.read(b)) != -1) {
                    os.write(b, 0, length);
                }
            os.close();
        } catch (IOException e) {
        }
    }
}
