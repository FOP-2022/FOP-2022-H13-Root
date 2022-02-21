package h13;

import java.lang.reflect.Modifier;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.slf4j.Logger;
import org.sourcegrade.jagr.api.testing.ClassTransformer;
import org.sourcegrade.jagr.launcher.env.Jagr;

/**
 * The transformer class.
 */
public class TutorTransformer implements ClassTransformer {

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void transform(ClassReader reader, ClassWriter writer) {
        reader.accept(new MethodTransformer(writer), 0);
    }

    @Override
    public int getWriterFlags() {
        return ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES;
    }

    static class MethodTransformer extends ClassVisitor {

        String className;
        int maxVar = 0;
        ClassWriter writer;

        public MethodTransformer(ClassWriter writer) {
            super(Opcodes.ASM9, writer);
            this.writer = writer;
        }

        @Override
        public void visit(int version, int access, String name, String signature, String superName,
                          String[] interfaces) {
            this.className = name;
            access &= ~Modifier.PRIVATE;
            access &= ~Modifier.PROTECTED;
            access |= Modifier.PUBLIC;
            super.visit(version, access, name, signature, superName, interfaces);
        }

        @Override
        public void visitInnerClass(String name, String outerName, String innerName, int access) {
            access &= ~Modifier.PRIVATE;
            access &= ~Modifier.PROTECTED;
            access |= Modifier.PUBLIC;
            super.visitInnerClass(name, outerName, innerName, access);
        }

        @Override
        public MethodVisitor visitMethod(int access, String name, String descriptor, String signature,
                                         String[] exceptions) {

            return new MethodVisitor(Opcodes.ASM9, super.visitMethod(access, name, descriptor, signature, exceptions)) {

                @Override
                public void visitMethodInsn(int opcode, String owner, String name, String descriptor,
                                            boolean isInterface) {
                    var logger = Jagr.Default.getInjector().getInstance(Logger.class);
                    //logger.warn("BeforeThingsHappen");
                    if (opcode == Opcodes.INVOKESTATIC
                        && owner.equals("java/lang/System")
                        && name.equals("exit")
                        && descriptor.equals("(I)V")) {
                        logger.warn("BeforeVisit");
                        super.visitMethodInsn(opcode, "h13/TutorSystem", name, descriptor, isInterface);
                        logger.warn("AfterVisit");
                        return;
                    }
                    super.visitMethodInsn(opcode, owner, name, descriptor, isInterface);
                }
            };
        }


        @Override
        public FieldVisitor visitField(int access, String name, String descriptor, String signature, Object value) {
            access &= ~Modifier.FINAL;
            access &= ~Modifier.PRIVATE;
            access &= ~Modifier.PROTECTED;
            access |= Modifier.PUBLIC;
            return super.visitField(access, name, descriptor, signature, value);
        }
    }
}
