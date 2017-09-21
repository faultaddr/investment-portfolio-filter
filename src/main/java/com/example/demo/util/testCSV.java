package com.example.demo.util;

import com.csvreader.CsvReader;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by panyunyi on 2017/9/19.
 * CUFE cs14
 */
public class testCSV {
        static ArrayList<ArrayList<Integer>>list;
        public static void main(String args[]){
            list=new ArrayList<ArrayList<Integer>>();

            String filePath = "E:\\code\\invest\\src\\main\\resources\\交易数据.csv";

            try {
                // 创建CSV读对象
                CsvReader csvReader = new CsvReader(filePath);

                // 读表头
                csvReader.readHeaders();
                while (csvReader.readRecord()){
                    // 读一整行
                   list.add(CSVFileUtil.fromCSVLinetoArray(csvReader.getRawRecord()));

                    //System.out.println(csvReader.getRawRecord());
                    // 读这行的某一列
                    //System.out.println(csvReader.get("Link"));
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            for(int i=0;i<list.size();i++){
                for(int j=0;j<list.get(0).size();j++){
                    System.out.println(list.get(i));
                }
            }
    }
}
