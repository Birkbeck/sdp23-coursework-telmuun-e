package sml;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sml.instruction.AddInstruction;
import sml.instruction.MovInstruction;

import java.util.ArrayList;
import java.util.Arrays;

class InstructionFactoryTest {
    private InstructionFactory factory;

    @BeforeEach
    void setUp() {
        factory = InstructionFactory.getInstance();
    }

    @AfterEach
    void tearDown() {
        factory = null;
    }

    @Test
    void executeValid() {
        String opcode = "add";
        ArrayList<String> args = new ArrayList<>(Arrays.asList(null, "EAX", "EBX"));
        Instruction addInstruction = factory.getInstruction(opcode, args);
        Assertions.assertEquals(AddInstruction.class, addInstruction.getClass());
    }

    @Test
    void executeValidTwo() {
        String opcode = "mov";
        ArrayList<String> args = new ArrayList<>(Arrays.asList(null, "EAX", "6"));
        Instruction addInstruction = factory.getInstruction(opcode, args);
        Assertions.assertEquals(MovInstruction.class, addInstruction.getClass());
    }
}