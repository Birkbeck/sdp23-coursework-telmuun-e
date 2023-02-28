package sml.instruction;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sml.Instruction;
import sml.Labels;
import sml.Machine;
import sml.Registers;

import java.io.IOException;

import static sml.Registers.Register.*;

class JnzInstructionTest {
    private Machine machine;
    private Registers registers;
    private Labels labels;

    @BeforeEach
    void setUp() {
        machine = new Machine(new Registers());
        registers = machine.getRegisters();
        labels = machine.getLabels();
    }

    @AfterEach
    void tearDown() {
        machine = null;
        registers = null;
    }

    @Test
    void executeValid() throws IOException {
        registers.set(EAX, 5);
        labels.addLabel("f3", 0);
        Instruction instruction = new JnzInstruction(null, EAX, "f3");
        int programCounter = instruction.execute(machine);
        Assertions.assertEquals(0, programCounter);
    }

    @Test
    void executeValidTwo() throws IOException {
        labels.addLabel("f3", 0);
        Instruction instruction = new JnzInstruction(null, EAX, "f3");
        int programCounter = instruction.execute(machine);
        Assertions.assertEquals(-1, programCounter);
    }
}