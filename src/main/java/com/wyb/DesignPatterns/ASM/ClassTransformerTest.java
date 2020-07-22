package DesignPatterns.ASM;

import org.objectweb.asm.*;
import static org.objectweb.asm.Opcodes.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ClassTransformerTest {
    public static void main(String[] args) throws IOException {
        System.out.println("aaa");
        ClassReader cr = new ClassReader(ClassPrinter.class.getClassLoader().getResourceAsStream("C:\\Users\\wangyb\\Desktop\\java\\Bank.class"));
        ClassWriter cw = new ClassWriter(0);
        cr.accept(cw, 0);
        byte[] b = cw.toByteArray();

//        String path = (String) System.getProperties().get("user.dir");
        File f = new File( "C:\\Users\\wangyb\\Desktop\\java\\");
        f.mkdirs();

        FileOutputStream fos = new FileOutputStream(new File( "C:\\Users\\wangyb\\Desktop\\java\\Bank_0.class"));
        fos.write(b);
        fos.flush();
        fos.close();


    }
}
