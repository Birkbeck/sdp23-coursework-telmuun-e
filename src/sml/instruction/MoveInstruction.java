package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

import java.util.Objects;

/**
 * Represents a move instruction that extends an abstract instruction.
 * It has a name of register of source and its value other than a label and an opcode.
 * It stores integer value in register result.
 *
 * @author Telmuun Enkhbold
 */

public class MoveInstruction extends Instruction {
    private final RegisterName source;
    private final int value;
    public static final String OP_CODE = "mov";

    public MoveInstruction(String label, RegisterName source, int value) {
        super(label, OP_CODE);
        this.source = source;
        this.value = value;
    }

    @Override
    public int execute(Machine m) {
        m.getRegisters().set(source, value);
        return NORMAL_PROGRAM_COUNTER_UPDATE;
    }

    @Override
    public String toString() {
        return getLabelString() + getOpcode() + " " + source + " " + value;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof MoveInstruction other) {
            return Objects.equals(this.source, other.source)
                    && this.OP_CODE.equals(other.OP_CODE);
        }
        return false;
    }

    @Override
    public int hashCode() { return Objects.hash(source, OP_CODE); }
}
