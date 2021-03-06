package com.wyb.IO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class FileLength {

    public static void main(String[] args) {
        int count = 0;
        InputStream streamReader = null;
        if (args.length < 1) {
            System.out.println("Usage: java FileLength <filename>");
            System.exit(0);
        }
        try {
            streamReader = new FileInputStream(args[0]);
            while (streamReader.read() != -1) {
                count++;
            }
            System.out.println("count: " + count);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                streamReader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
