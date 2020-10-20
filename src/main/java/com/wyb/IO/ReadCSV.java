package com.wyb.IO;

import java.io.*;
import java.nio.charset.Charset;

public class ReadCSV {
    public static void main(String[] args) throws FileNotFoundException {


        String filePath = "C:\\Users\\32006\\Desktop\\ZKSBM_20200829(1).csv";
        File in =  new File(filePath);
        encode();
        InputStreamReader inputStreamReader = null;
        try {
            inputStreamReader = new InputStreamReader(new FileInputStream(in),"GB18030");
            System.out.println(inputStreamReader.getEncoding());

            char[] chars = new char[1024];
            String content = "";
            while (inputStreamReader.read(chars) > 0 ) {
                content += new String( chars );
                break;
            }
            System.out.println(content);



        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        // new InputStreamReader(new FileInputStream(in), "ISO8859_1");

        // String str = "021000140510000;;0001;鑰佸簷瓒抽噾999鑿犺悵鑺辫矾璺€氳浆杩愮彔鎸傚潬100014;0001;榛勯噾楗板搧;0000000005;鎸傚潬;0000;;鏄?0000000002;瓒抽噾999;;;00000000;;";
        //
        // System.out.println(getUtf8String(str));


    }


    private static String getUtf8String(String str) {
        if (str != null && str.length() > 0) {
            // String needEncodeCode = "ISO-8859-1";
            String needEncodeCode = "UTF-8";
            try {
                if (Charset.forName(needEncodeCode).newEncoder().canEncode(str)) {
                    System.out.println("in");
                    //这个方法是关键，可以判断乱码字符串是否为指定的编码
                    str = new String(str.getBytes(needEncodeCode), "UTF-8");
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return str;
    }

    public static void encode() {
        String name = "I am 君山";
        try {
            byte[] iso8859 = name.getBytes("ISO-8859-1");
            byte[] gb2312 = name.getBytes("GB2312");
            byte[] gbk = name.getBytes("GBK");
            byte[] utf16 = name.getBytes("UTF-16");
            byte[] utf8 = name.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
