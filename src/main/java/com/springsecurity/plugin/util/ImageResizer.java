/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springsecurity.plugin.util;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;

import org.springframework.web.multipart.MultipartFile;

import com.springsecurity.nfc.constants.Constants;

public class ImageResizer implements Constants{
    public BufferedImage scale(File icon, int targetWidth, int targetHeight) {
        BufferedImage ret = null;
        if (icon.exists()) {
            try {
                BufferedImage img = ImageIO.read(icon);
                ret = img;
                int type = (img.getTransparency() == Transparency.OPAQUE) ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;
                BufferedImage scratchImage = null;
                Graphics2D g2 = null;
                int w = img.getWidth();
                int h = img.getHeight();
                int prevW = w;
                int prevH = h;
                do {
                    if (w > targetWidth) {
                        w /= 2;
                        w = (w < targetWidth) ? targetWidth : w;
                    }
                    if (h > targetHeight) {
                        h /= 2;
                        h = (h < targetHeight) ? targetHeight : h;
                    }
                    if (scratchImage == null) {
                        scratchImage = new BufferedImage(w, h, type);
                        g2 = scratchImage.createGraphics();
                    }

                    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                    g2.drawImage(ret, 0, 0, w, h, 0, 0, prevW, prevH, null);

                    prevW = w;
                    prevH = h;
                    ret = scratchImage;
                } while (w != targetWidth || h != targetHeight);
                if (g2 != null) {
                    g2.dispose();
                }
                if (targetWidth != ret.getWidth() || targetHeight != ret.getHeight()) {
                    scratchImage = new BufferedImage(targetWidth, targetHeight, type);
                    g2 = scratchImage.createGraphics();
                    g2.drawImage(ret, 0, 0, null);
                    g2.dispose();
                    ret = scratchImage;
                }
            } catch (IOException e) {
            }
        }
        return ret;
    }

    public File reziseTo(File source,File dest,int width,int height,String ext) {
        try {
            ImageResizer img = new ImageResizer();
        	ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(img.scale(source, width, height), ext, baos);
            dest.delete();
            baos.writeTo(new FileOutputStream(dest));
            baos.flush();
        } catch (IOException e) {
            System.err.println("err" + e.getMessage());
        }
        return dest;
    }
    
    public File createFile(MultipartFile file,ServletContext context,String fileName){
    	try {
            byte[] bytes = file.getBytes();
            // Creating the directory to store file
            String rootPath = context.getRealPath("");
            File dir = new File(rootPath + File.separator + MENU_IMG_FOLDER + File.separator+"Temp");
            if (!dir.exists())
                dir.mkdirs();
            String filePath=dir.getAbsolutePath()+File.separator+fileName;
            // Create the file on server
            File serverFile = new File(filePath);
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
            stream.write(bytes);
            stream.close();
            return serverFile;
        } catch (Exception e) {
        	return null;
        }
    }
    
}
