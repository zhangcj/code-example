package com.xxx.beecho.framework;

import com.xxx.beecho.framework.asm.AopClassAdapter;
import com.xxx.beecho.framework.asm.AopClassLoader;
import jdk.internal.org.objectweb.asm.ClassReader;
import jdk.internal.org.objectweb.asm.ClassWriter;
import jdk.internal.org.objectweb.asm.Opcodes;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException {
        try {
            AopClassLoader classLoader = new AopClassLoader();
            Class<?> asmClass = classLoader.loadClass("com.xxx.beecho.framework.asm.TestBean_tmp");

            Object obj = asmClass.newInstance();
            Method method = asmClass.getMethod("asmEcho",null);
            method.invoke(obj,null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
