/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springsecurity.plugin.util;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class SaveLinkPreview {

    //public static void main(String[] args) throws Exception {
    //   String imageUrl = "http://localhost:8084/quizuncle/home/welcome";
    //   String destinationFile = "D:/72863967519300.html";
    //    saveImage(imageUrl, destinationFile);
    //}
    public static void saveURL(String imageUrl, String destinationFile) {
        try {
            URL url = new URL(imageUrl);
            OutputStream os;
            InputStream is = url.openStream();
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

    public static String getUrlSource(String url) throws IOException {
        URL u = new URL(url);
        URLConnection yc = u.openConnection();
        StringBuilder a = null;
        try {
        	BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream(), "UTF-8"));
            String inputLine;
            a = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                a.append(inputLine);
            }
        } catch (Exception e) {
        	e.printStackTrace();
        }

        return a.toString();
    }

    public static String width_height(String filename) {
        String str = "";
        BufferedImage bimg;
        try {
            bimg = ImageIO.read(new File(filename));
            int width = bimg.getWidth();
            int height = bimg.getHeight();
            str=width+"*"+height;
        } catch (IOException ex) {
            Logger.getLogger(SaveLinkPreview.class.getName()).log(Level.SEVERE, null, ex);
        }
        return str;
    }
}
