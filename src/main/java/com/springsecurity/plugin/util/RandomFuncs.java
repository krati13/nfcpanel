/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springsecurity.plugin.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Shyam
 */
public class RandomFuncs {

    public String appendDigits(int count, char ch, String prestr) {
        if (prestr == null) {
            prestr = "";
        }
        StringBuilder sb = new StringBuilder(prestr);
        int i = 0;
        while (i < count) {
            sb.append(ch);
            i++;
        }
        return sb.toString();
    }

    public String completeIt(int size, char ch, String prestr) {
        if (prestr == null) {
            prestr = "";
        }
        int len = prestr.length();
        StringBuilder sb = new StringBuilder(prestr);
        if (len < size) {
            size = size - len;
            int i = 0;
            while (i < size) {
                sb.append(ch);
                i++;
            }
        }
        return sb.toString();
    }

    public String find_ID() {
        StringBuilder hexString = new StringBuilder();
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            byte[] messageDigest = instance.digest(String.valueOf(System.nanoTime()).getBytes());
            for (int i = 0; i < messageDigest.length; i++) {
                String hex = Integer.toHexString(0xFF & messageDigest[i]);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

        } catch (NoSuchAlgorithmException e) {
        }
        return hexString.toString();
    }
    
    public String width_height(File file,String type) {
        String str = "";
        BufferedImage bimg;
        try {
            bimg = ImageIO.read(file);
            int width = bimg.getWidth();
            int height = bimg.getHeight();
            if(type.equalsIgnoreCase("w")){
                str+=width;
            }else{
                str+=height;
            }
        } catch (IOException ex) {
            Logger.getLogger(SaveLinkPreview.class.getName()).log(Level.SEVERE, null, ex);
        }
        return str;
    }
    
    /*
    public static void main(String args[]){
        RandomFuncs RF=new RandomFuncs();
        System.out.println(RF.find_ID());
        System.out.println(RF.find_ID());
        System.out.println(RF.find_ID());
        System.out.println(RF.find_ID());
        System.out.println(""+(100000000000l));
    }*/
}
