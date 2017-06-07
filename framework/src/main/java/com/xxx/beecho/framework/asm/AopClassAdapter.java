package com.xxx.beecho.framework.asm;

import jdk.internal.org.objectweb.asm.ClassVisitor;
import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.Opcodes;

/**
 * Created by Administrator on 2017/6/7.
 */
public class AopClassAdapter extends ClassVisitor implements Opcodes {
    public AopClassAdapter(int i, ClassVisitor classVisitor) {
        super(i, classVisitor);
    }

    public void visit(
            int version,
            int access,
            String name,
            String signature,
            String superName,
            String[] interfaces) {
        //更改类名，并使新类继承原有的类。
        super.visit(version, access, name + "_tmp", signature, name, interfaces);
        {//输出一个默认的构造方法
            MethodVisitor mv = super.visitMethod(ACC_PUBLIC, "<init>",
                    "()V", null, null);
            mv.visitCode();
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKESPECIAL, name, "<init>", "()V");
            mv.visitInsn(RETURN);
            mv.visitMaxs(1, 1);
            mv.visitEnd();
        }
    }

    public MethodVisitor visitMethod(
            int access,
            String name,
            String desc,
            String signature,
            String[] exceptions) {
        if ("<init>".equals(name))
            return null;//放弃原有类中所有构造方法
        if (!name.startsWith("asm"))
            return null;// 只对asm开始的方法执行代理

        MethodVisitor mv = super.visitMethod(access, name,
                desc, signature, exceptions);
        return new AopMethodVisitor(this.api, mv);
    }
}
