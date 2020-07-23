package com.wyb.IO;

import java.io.*;

public class FileConcatenate {
    public static void concatenateFile(String... filename) {
        String str = null;

        try (BufferedWriter writer = new BufferedWriter( new FileWriter("CombinedFile.txt"));) {
            for(String name:filename){
                try(BufferedReader reader = new BufferedReader( new FileReader(name));) {
                    while ((str = reader.readLine()) != null) {
                        writer.write(str);
                        writer.newLine();
                    }

                }catch (IOException e ) {
                    System.out.println("Error reading/writing file");
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        if (args.length < 0) {
            System.out.println("Usage: java Concatenate file1 file2");
            System.exit(0);
        }
        concatenateFile(args);
        System.out.println("successfully created CombinedFile.txt");
    }
}
