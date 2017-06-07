package com.xxx.beecho.framework.asm;

import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.Opcodes;

/**
 * Created by Administrator on 2017/6/7.
 */
public class AopMethodVisitor extends MethodVisitor implements Opcodes {
    public AopMethodVisitor(int i, MethodVisitor methodVisitor) {
        super(i, methodVisitor);
    }

    public void visitCode(){
        super.visitCode();
        this.visitMethodInsn(INVOKESTATIC,"com/xxx/beecho/framework/asm/AopInteceptor","before","()V",false);
    }

    public void visitInsn(int opcode) {
        if (opcode >= IRETURN && opcode <= RETURN)//在方法返回之前
        {
            this.visitMethodInsn(INVOKESTATIC, "com/xxx/beecho/framework/asm/AopInteceptor", "after", "()V", false);
        }
        super.visitInsn(opcode);
    }
}
