package sml;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.io.IOException;

import static sml.Registers.Register.*;

class TranslatorTest {
    private Machine machine;

    @BeforeEach
    void setUp() {
        machine = new Machine(new Registers());
    }

    @AfterEach
    void tearDown() {
        machine = null;
    }

    @Test
    void getInstructionValid() throws IOException {
        Translator t = new Translator("resources/test_files/test_1.txt");
        t.readAndTranslate(machine.getLabels(), machine.getProgram());
        machine.execute();
        Assertions.assertEquals(720, machine.getRegisters().get(EBX));
    }
}