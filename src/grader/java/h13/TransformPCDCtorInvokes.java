package h13;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.sourcegrade.jagr.api.testing.ClassTransformer;

public class TransformPCDCtorInvokes implements ClassTransformer {
    @Override
    public String getName() {
        return "pcd-ctor-invokes";
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
        public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
            if ("init".equals(name)) {
                return super.visitMethod(access, name, descriptor, signature, exceptions);
            } else {
                return new MV(super.visitMethod(access, name, descriptor, signature, exceptions));
            }
        }

        private class MV extends MethodVisitor {

            public MV(MethodVisitor methodVisitor) {
                super(Opcodes.ASM9, methodVisitor);
            }

            private boolean lastInsnWasPropertyChangeDialogue = false;

            @Override
            public void visitTypeInsn(int opcode, String type) {
                if (opcode == Opcodes.NEW && "h13/PropertyChangeDialogue".equals(type)) {
                    lastInsnWasPropertyChangeDialogue = true;
                } else {
                    super.visitTypeInsn(opcode, type);
                }
            }

            @Override
            public void visitInsn(int opcode) {
                // DUP follows NEW so this is a relatively safe assumption to make
                if (!lastInsnWasPropertyChangeDialogue || opcode != Opcodes.DUP) {
                    super.visitInsn(opcode);
                }
                lastInsnWasPropertyChangeDialogue = false;
            }

            @Override
            public void visitMethodInsn(int opcode, String owner, String name, String descriptor, boolean isInterface) {
                if (opcode == Opcodes.INVOKESPECIAL
                    && "h13/PropertyChangeDialogue".equals(owner)
                    && "<init>".equals(name)
                    && "()V".equals(descriptor)) {
                    super.visitVarInsn(Opcodes.ALOAD, 0);
                    super.visitFieldInsn(Opcodes.GETFIELD, "h13/ControlFrame", "pcd", "h13/PropertyChangeDialogue");
                } else {
                    super.visitMethodInsn(opcode, owner, name, descriptor, isInterface);
                }
            }
        }
    }
}
