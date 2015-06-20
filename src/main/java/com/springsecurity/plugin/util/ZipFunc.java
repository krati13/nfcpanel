package com.springsecurity.plugin.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.CRC32;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

public class ZipFunc {
    public static void zipDirectory(String ZipDir, String ZipFile) throws IOException, IllegalArgumentException {
        File file = new File(ZipDir);

        File file1 = new File(ZipFile);
        try {
        	ZipOutputStream ZoutPutS = new ZipOutputStream(new FileOutputStream(file1));
            doZipDirectory(file, ZoutPutS);
        } catch (Exception e) {
        	e.printStackTrace();
        }
    }

    public static void zipDirectoryDate(String ZipDir, String ZipFile,int sd, int ed) throws IOException, IllegalArgumentException {
        File file = new File(ZipDir);

        File file1 = new File(ZipFile);
        try {
        	ZipOutputStream ZoutPutS = new ZipOutputStream(new FileOutputStream(file1));
            doZipDirectoryWithDate(file, ZoutPutS,sd,ed);
        } catch (Exception e) {
        	e.printStackTrace();
        }
    }
    
    private static void doZipDirectory(File f, ZipOutputStream ZoutPutS1) throws IOException, IllegalArgumentException {
        if (!f.isDirectory()) {
            throw new IllegalArgumentException("Compress: not a directory:  " + f);
        }

        String[] fList = f.list();
        byte[] buffer = new byte[4096];
        int bytes_read;
        String tmp = "";
        for (String fList1 : fList) {
            File f1 = new File(f, fList1);
            if (f1.isDirectory()) {
                zipDirectory(f1.getAbsolutePath(), f1.getAbsolutePath()+".zip");
            } else {
                try {
                	FileInputStream fis = new FileInputStream(f1);
                    tmp = f1.getName();
                    ZipEntry zipEntry = new ZipEntry(tmp);
                    
                    ZoutPutS1.putNextEntry(zipEntry);
                    
                    while ((bytes_read = fis.read(buffer)) != -1) {
                        ZoutPutS1.write(buffer, 0, bytes_read);
                    }
                } catch (Exception e) {
                	e.printStackTrace();
                }
            }
        }
        CRC32 crc = new CRC32();
        crc.update(buffer);
        //System.out.println("**crc=" + crc.getValue());
    }
    
    private static void doZipDirectoryWithDate(File f, ZipOutputStream ZoutPutS1,int sd, int ed) throws IOException, IllegalArgumentException {
        if (!f.isDirectory()) {
            throw new IllegalArgumentException("Compress: not a directory:  " + f);
        }

        String[] fList = f.list();
        byte[] buffer = new byte[4096];
        int bytes_read;
        String tmp = "";
        for (String fList1 : fList) {
            File f1 = new File(f, fList1);
            if (f1.isDirectory()) {
                if(Integer.parseInt(f1.getName())>=sd && Integer.parseInt(f1.getName())<=ed)
                zipDirectoryDate(f1.getAbsolutePath(), f1.getAbsolutePath()+".zip" , sd , ed);
            } else {
                try {
                	FileInputStream fis = new FileInputStream(f1);
                    tmp = f1.getName();
                    ZipEntry zipEntry = new ZipEntry(tmp);
                    
                    ZoutPutS1.putNextEntry(zipEntry);
                    
                    while ((bytes_read = fis.read(buffer)) != -1) {
                        ZoutPutS1.write(buffer, 0, bytes_read);
                    }
                } catch (Exception e) {
                	e.printStackTrace();
                }
            }
        }
        CRC32 crc = new CRC32();
        crc.update(buffer);
        //System.out.println("**crc=" + crc.getValue());
    }
    
    public File unzip(File impFile) {
        String impFile_Path = impFile.getPath();
        
        String imStringPath=impFile_Path.substring(0, impFile_Path.lastIndexOf(".zip"));
        
        File impFileDir = new File(imStringPath);
        
        boolean fi = false, dFlag = false;
        fi = impFileDir.mkdir();
        if (!fi) {
            File[] files = impFileDir.listFiles();
            for (File file : files) {
                dFlag = file.delete();
            }
            if (impFileDir.list().length < 1 && impFileDir.delete()) {
                impFileDir.mkdir();
            }
        }
        //System.out.println("fi=" + fi + " dFlag=" + dFlag);

        int bytes_read;
        byte[] buffer = new byte[4096];
        try {
        	ZipFile zipFile = new ZipFile(impFile);
            Enumeration enum1 = zipFile.entries();
            while (enum1.hasMoreElements()) {
                ZipEntry tmpZipEntry = (ZipEntry) enum1.nextElement();
                InputStream zis = zipFile.getInputStream(tmpZipEntry);
                
                File drFile = new File(impFileDir.getPath() + "/" + tmpZipEntry.getName());
                FileOutputStream fos = new FileOutputStream(drFile);    
                while ((bytes_read = zis.read(buffer)) != -1) {
                        fos.write(buffer, 0, bytes_read);
                    }
                }
            CRC32 crc = new CRC32();
            crc.update(buffer);
        } catch (IOException io) {
        System.out.println("ioException=" + io.getMessage());
        }
        return impFileDir;
    }
}
