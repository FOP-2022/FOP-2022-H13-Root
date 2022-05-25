package h13;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.sourcegrade.jagr.api.testing.ClassTransformer;

public class TransformCFCtorInvokes implements ClassTransformer {
    @Override
    public String getName() {
        return "cf-ctor-invokes";
    }

    @Override
    public void transform(ClassReader reader, ClassWriter writer) {
        if ("h13/ControlFrame".equals(reader.getClassName())) {
            reader.accept(new CV(writer), 0);
        } else {
            reader.accept(writer, 0);
        }
    }

    private class CV extends ClassVisitor {

        public CV(ClassVisitor classVisitor) {
            super(Opcodes.ASM9, classVisitor);
        }

        @Override
        public MethodVisitor visitMethod(int access, String name, String descriptor, String signature,
                String[] exceptions) {
            if ("<init>".equals(name)) {
                return new MV(super.visitMethod(access, name, descriptor, signature, exceptions));
            } else {
                return super.visitMethod(access, name, descriptor, signature, exceptions);
            }
        }

        private class MV extends MethodVisitor {

            public MV(MethodVisitor methodVisitor) {
                super(Opcodes.ASM9, methodVisitor);
            }

            @Override
            public void visitInsn(int opcode) {
                if (opcode == Opcodes.RETURN) {
                    super.visitVarInsn(Opcodes.ALOAD, 0);
                    super.visitMethodInsn(Opcodes.INVOKESTATIC, "h13/PCDReplacementTutor", "addCF",
                            "(Lh13/ControlFrame;)V", false);
                }
                super.visitInsn(opcode);
            }
        }
    }
}
