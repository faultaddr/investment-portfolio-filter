package com.example.demo.util;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Created by panyunyi on 2017/9/14.
 * CUFE cs14
 */
public class PrintMessage {
    public static void PrintMessage(HttpServletResponse response, String successStr, String failedStr){
        PrintWriter printWriter=null;
        try{
            printWriter=response.getWriter();
            printWriter.write(successStr);
        }catch (Exception e){
            e.printStackTrace();
            printWriter.write(failedStr);
        }
        finally {
            printWriter.flush();
            printWriter.close();
        }

    }
}
