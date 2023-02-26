package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

import java.util.Objects;

/**
 * Represents a multiply instruction that extends an abstract instruction.
 * It has names of registers of result and source other than a label and an opcode.
 * It multiplies the contents of registers result and source
 * and store the result in register result.
 *
 * @author Telmuun Enkhbold
 */

public class MultiplyInstruction extends Instruction {
    private final RegisterName result;
    private final RegisterName source;

    public static final String OP_CODE = "mul";

    public MultiplyInstruction(String label, RegisterName result, RegisterName source) {
        super(label, OP_CODE);
        this.result = result;
        this.source = source;
    }

    @Override
    public int execute(Machine m) {
        int value1 = m.getRegisters().get(result);
        int value2 = m.getRegisters().get(source);
        m.getRegisters().set(result, value1 * value2);
        return NORMAL_PROGRAM_COUNTER_UPDATE;
    }

    @Override
    public String toString() {
        return getLabelString() + getOpcode() + " " + result + " " + source;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof MultiplyInstruction other) {
            return Objects.equals(this.result, other.result)
                    && Objects.equals(this.source, other.source)
                    && this.OP_CODE.equals(other.OP_CODE);
        }
        return false;
    }

    @Override
    public int hashCode() { return Objects.hash(result, source, OP_CODE); }
}
