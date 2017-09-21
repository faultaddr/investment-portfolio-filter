package com.example.demo.util;

import com.sun.javaws.progress.Progress;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by panyunyi on 2017/9/21.
 * CUFE cs14
 */
public class test {

        public static void exeCmd(String commandStr) {
            BufferedReader br = null;
            try {
                Process p = Runtime.getRuntime().exec(commandStr);
                br = new BufferedReader(new InputStreamReader(p.getInputStream()));
                String line = null;
                StringBuilder sb = new StringBuilder();
                while ((line = br.readLine()) != null) {
                    sb.append(line + "\n");
                }
                System.out.println(sb.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
            finally
            {
                if (br != null)
                {
                    try {
                        br.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        String args[];
    public static void main(String args[]) {

        String s="python E:\\code\\invest\\src\\main\\resources\\final.py old 60000 15";
        test.exeCmd(s);
    }
}
