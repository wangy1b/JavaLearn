package IO;

import java.io.*;
import java.nio.file.*;


public class DirListing {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: DirListing DirectoryName");
            System.exit(0);
        }

        Path dirPath = Paths.get(args[0]);
        DirectoryStream<Path> directory = null;

        DirectoryStream.Filter<Path> filter = new DirectoryStream.Filter<Path>() {
            @Override
            public boolean accept(Path file) throws IOException {
                return (Files.size(file) > 8129*1024L);
            }
        };

        try {
//            directory = Files.newDirectoryStream(dirPath,"*.txt");
            directory = Files.newDirectoryStream(dirPath,filter);
            for (Path p : directory) {
                System.out.println(p.getFileName());
            }
        } catch (Exception e) {
            System.out.println("Invalid path specified:" + args[0]);
        } finally {
            try {
                if (directory != null) {
                    directory.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
