package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;
import sml.Labels;

import java.util.Objects;

/**
 * Represents a jnz instruction that extends an abstract instruction.
 * It has a name of register of source and a label for statement other than a label and an opcode.
 * If the contents of register source is not zero,
 * then make the labeled statement the next statement to execute.
 *
 * @author Telmuun Enkhbold
 */

public class JnzInstruction extends Instruction {
    private final RegisterName source;
    private final String labelForStatement;

    public static final String OP_CODE = "jnz";

    public JnzInstruction(String label, RegisterName source, String labelForStatement) {
        super(label, OP_CODE);
        this.source = source;
        this.labelForStatement = labelForStatement;
    }

    @Override
    public int execute(Machine m) {
        int value = m.getRegisters().get(source);
        if (value != 0) {
            Labels labels = m.getLabels();
            return labels.getAddress(labelForStatement);
        }
        return NORMAL_PROGRAM_COUNTER_UPDATE;
    }

    @Override
    public String toString() {
        return getLabelString() + getOpcode() + " " + source + " " + labelForStatement;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof JnzInstruction other) {
            return Objects.equals(this.source, other.source)
                    && this.labelForStatement.equals(other.labelForStatement)
                    && this.OP_CODE.equals(other.OP_CODE);
        }
        return false;
    }

    @Override
    public int hashCode() { return Objects.hash(labelForStatement, source, OP_CODE); }
}
