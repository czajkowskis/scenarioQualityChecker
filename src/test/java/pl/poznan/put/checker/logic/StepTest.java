package pl.poznan.put.checker.logic;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StepTest {

    @Test
    void testNull(){
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new Step(null, List.of() ));

        assertEquals("Step text cannot be null", thrown.getMessage());
    }

    @Test
    void testEmpty(){
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new Step( "", List.of() ));

        assertEquals("Step text cannot be empty", thrown.getMessage());
    }

}