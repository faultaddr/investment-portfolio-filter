package com.example.demo.util;

import java.io.*;

/**
 * Created by panyunyi on 2017/9/20.
 * CUFE cs14
 */
public class FileUtil {
    public static void writeByFileOutputStream(String filename,String content) {

        FileOutputStream fop = null;
        File file;

        try {
            file = new File(filename);
            try {
                fop = new FileOutputStream(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            // if file doesnt exists, then create it
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            // get the content in bytes
            byte[] contentInBytes = content.getBytes();

            fop.write(contentInBytes);
            fop.flush();
            fop.close();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fop != null) {
                    fop.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static String readByBufferedReader(String fileName) {
        String read=null;
        try {
            File file = new File(fileName);
            // 读取文件，并且以utf-8的形式写出去
            BufferedReader bufread;

            bufread = new BufferedReader(new FileReader(file));
            while ((read = bufread.readLine()) != null) {
                //System.out.println(read);
            }
            bufread.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return read;
    }
}
