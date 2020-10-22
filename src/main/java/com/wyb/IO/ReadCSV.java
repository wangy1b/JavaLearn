package com.wyb.IO;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ReadCSV {
    public static void main(String[] args) throws FileNotFoundException {


        String filePath = "C:\\Users\\32006\\Desktop\\YCustomer_20200902.csv";

        try {
            File fileDir = new File(filePath);
            File outFileDir = new File(filePath+"new");
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFileDir), "utf8"));

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(fileDir), "utf-8"));


            String str;

            while ((str = in.readLine()) != null) {
                String tmp = new String(str.getBytes("gbk"));
                System.out.println(tmp);
                out.write(tmp+"\n");
            }

            in.close();
            out.close();
        }
        catch (UnsupportedEncodingException e)
        {
            System.out.println(e.getMessage());
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }


        // String str = "021000140510000;;0001;鑰佸簷瓒抽噾999鑿犺悵鑺辫矾璺€氳浆杩愮彔鎸傚潬100014;0001;榛勯噾楗板搧;0000000005;鎸傚潬;0000;;鏄?0000000002;瓒抽噾999;;;00000000;;";
        //
        // System.out.println(getUtf8String(str));


        // System.out.println(new String("鑰佸簷鍢夊畾娓呮渤搴?;".getBytes("UTF-8"), "GBK"));
        // try {
        //     String str = "鑰佸簷鍢夊畾娓呮渤搴?;";
        //     System.out.println(new String(str.getBytes("BIG5"),"UTF-8"));
        //     System.out.println(new String(str.getBytes("GB18030"),"UTF-8"));
        //
        // } catch (UnsupportedEncodingException e) {
        //     e.printStackTrace();
        // }

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

    private static void test(String fileSt) throws IOException {
        FileInputStream fis = null;
        InputStreamReader isr = null;
        String s;

        try {
            // new input stream reader is created
            fis = new FileInputStream(fileSt);
            isr = new InputStreamReader(fis);

            // the name of the character encoding returned
            s = isr.getEncoding();
            System.out.print("Character Encoding: "+s);

        } catch (Exception e) {
            // print error
            System.out.print("The stream is already closed");
        } finally {
            // closes the stream and releases resources associated
            if(fis!=null)
                fis.close();
            if(isr!=null)
                isr.close();
        }
    }


    public static byte[] readBytes(InputStream inputStream) throws IOException {
        byte[] b = new byte[1024];
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        int c;
        while ((c = inputStream.read(b)) != -1) {
            os.write(b, 0, c);
            break;
        }
        return os.toByteArray();
    }


    public static void transform(File source, String srcEncoding, File target, String tgtEncoding) throws IOException {
        BufferedReader br = null;
        BufferedWriter bw = null;
        try{
            br = new BufferedReader(new InputStreamReader(new FileInputStream(source),srcEncoding));
            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(target), tgtEncoding));
            char[] buffer = new char[16384];
            int read;
            while ((read = br.read(buffer)) != -1)
                bw.write(buffer, 0, read);
        } finally {
            try {
                if (br != null)
                    br.close();
            } finally {
                if (bw != null)
                    bw.close();
            }
        }
    }
}
