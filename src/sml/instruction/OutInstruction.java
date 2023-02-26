package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

import java.util.Objects;

/**
 * Represents an out instruction that extends an abstract instruction.
 * It has a name of register of source other than a label and an opcode.
 * It prints the contents of register source on the console.
 *
 * @author Telmuun Enkhbold
 */

public class OutInstruction extends Instruction {
    private final RegisterName source;

    public static final String OP_CODE = "out";

    public OutInstruction(String label, RegisterName source) {
        super(label, OP_CODE);
        this.source = source;
    }

    @Override
    public int execute(Machine m) {
        int value = m.getRegisters().get(source);
        System.out.println(value);
        return NORMAL_PROGRAM_COUNTER_UPDATE;
    }

    @Override
    public String toString() {
        return getLabelString() + getOpcode() + " " + source;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof OutInstruction other) {
            return Objects.equals(this.source, other.source)
                    && this.OP_CODE.equals(other.OP_CODE);
        }
        return false;
    }

    @Override
    public int hashCode() { return Objects.hash(source, OP_CODE); }
}
