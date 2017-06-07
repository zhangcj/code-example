package com.xxx.beecho.framework.asm;

import jdk.internal.org.objectweb.asm.ClassReader;
import jdk.internal.org.objectweb.asm.ClassWriter;
import jdk.internal.org.objectweb.asm.Opcodes;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Administrator on 2017/6/7.
 */
public class AopClassLoader extends ClassLoader implements Opcodes {

    public AopClassLoader() {
        super();
    }

    public AopClassLoader(ClassLoader parent) {
        super(parent);
    }

    public Class<?> loadClass(String name) throws ClassNotFoundException {
        if (!name.endsWith("_tmp"))
            return super.loadClass(name);
        try {
            ClassWriter cw = new ClassWriter(0);
            InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("com/xxx/beecho/framework/asm/TestBean.class");
            ClassReader reader = new ClassReader(is);
            reader.accept(new AopClassAdapter(ASM4, cw), ClassReader.SKIP_DEBUG);
            byte[] code = cw.toByteArray();

//            // -----
//            FileOutputStream fos = new FileOutputStream("E:\\code\\java\\TestBean_tmp.class");
//            fos.write(code);
//            fos.flush();
//            fos.close();

            return this.defineClass(name, code, 0, code.length);
        } catch (Throwable e) {
            e.printStackTrace();
            throw new ClassNotFoundException();
        }
//        return null;
    }
}
