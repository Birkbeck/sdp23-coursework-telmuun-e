package sml;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

/**
 * This is an instruction factory class that creates and sends back instruction instance from opcode and its args.
 *
 * @author Telmuun Enkhbold
 */
public class InstructionFactory {
    /**
     * Creates and returns instruction instance.
     *
     * @param opcode opcode of instruction
     * @param args arguments for instruction
     * @return instruction
     */
    public Instruction getInstruction(String opcode, ArrayList<String> args) {
        try {
            String className = "sml.instruction." + opcode.substring(0, 1).toUpperCase() + opcode.substring(1) + "Instruction";
            Constructor<?> constructor = Class.forName(className).getDeclaredConstructors()[0];

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
            return (Instruction) constructor.newInstance(instructionArgs);
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | InvocationTargetException ex) {
            System.out.println("Unknown instruction: " + opcode);
        }
        return null;
    }
}
