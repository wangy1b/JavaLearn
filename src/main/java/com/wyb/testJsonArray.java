package com.wyb;import com.alibaba.fastjson.JSONArray;
//import org.junit.Test;

import java.io.*;
import java.util.List;

public class testJsonArray {

    public static List<String> expStr(String str){
        List<String> exObj= JSONArray.parseArray(str,String.class);
        return exObj;
    }

    public static String readFile(File file) {
    try {
        FileInputStream fileInputStream = new FileInputStream(file);
        // 把每次读取的内容写入到内存中，然后从内存中获取
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        // 只要没读完，不断的读取
        while ((len = fileInputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, len);
        }
        // 得到内存中写入的所有数据
        byte[] data = outputStream.toByteArray();
        fileInputStream.close();
        return new String(data,"utf-8");
//            return new String(data,"utf-8");
        //return new String(data, "GBK");//以GBK（什么编码格式）方式转
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

//    @Test
    public  void test() {

//            String a = "[20180117,4402,27,5428505,8532298,5398490,96,\"137105,111\",20181023,0.000,1.000,0.000,32.900,32.900,1.000,\"2018-10-26 01:24:50.383\",181026004618618,20.4000,201801,28.120]";
//            List<String> test = expStr(a);
//            System.out.println(test);
////            test.forEach(System.out.println(_));
//            test.forEach(t -> System.out.println(t));


        String filename = "C:\\Users\\32006\\IdeaProjects\\test\\src\\main\\resources\\ps_002_test.csv";

        File file = new File(filename);
        BufferedReader reader = null;
        try {
//                System.out.println("以行为单位读取文件内容，一次读一整行：");
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
                System.out.println("line " + line + ": " + tempString);
                List<String> test = expStr(tempString);
//                    test.forEach(t -> System.out.println(t));
                line++;
                if ( line > 2 ) {
                    break;
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }


//            String data = readFile(new File(filename));
//            System.out.println(data);
//
//            List<String> test = expStr(data.trim());
//            test.forEach(t -> System.out.println(t));




    }
}




