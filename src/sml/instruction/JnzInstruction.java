package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;
import sml.Labels;

import java.util.List;

// TODO: write a JavaDoc for the class

/**
 * @author
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
}
