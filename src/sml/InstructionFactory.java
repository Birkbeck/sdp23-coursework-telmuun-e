package sml;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.reflect.Constructor;
import java.util.ArrayList;

/**
 * This is an instruction factory class that creates and sends back instruction instance
 * from opcode and its args using Spring beans.
 *
 * @author Telmuun Enkhbold
 */
public class InstructionFactory {
    private static InstructionFactory factory;
    private final ApplicationContext context;
    private InstructionFactory() {
        context = new ClassPathXmlApplicationContext("resources/beans.xml");
    }

    public static InstructionFactory getInstance() {
        if (factory == null) {
            factory = new InstructionFactory();
        }
        return factory;
    }

    /**
     * Creates and returns instruction instance.
     *
     * @param opcode opcode of instruction
     * @param args arguments for instruction
     * @return instruction
     */
    public Instruction getInstruction(String opcode, ArrayList<String> args) {
        Class<?> cls = context.getType(opcode);
        if (cls == null) throw new AssertionError();
        Constructor<?> constructor = cls.getDeclaredConstructors()[0];
        int parameterCount = constructor.getParameterCount();
        Object[] instructionArgs = new Object[parameterCount];
        instructionArgs[0] = args.get(0);
        Class<?>[] parameterTypes = constructor.getParameterTypes();
        for (int i=1; i < parameterTypes.length; i++) {
            String arg = args.get(i);
            if (parameterTypes[i] == RegisterName.class) {
                instructionArgs[i] = Registers.Register.valueOf(arg);
            } else if (parameterTypes[i] == String.class) {
                instructionArgs[i] = arg;
            } else if (parameterTypes[i] == int.class) {
                instructionArgs[i] = Integer.parseInt(arg);
            }
        }
        return (Instruction) context.getBean(opcode, instructionArgs);
    }
}
