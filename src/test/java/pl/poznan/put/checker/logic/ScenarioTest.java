package pl.poznan.put.checker.logic;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ScenarioTest {

    @Test
    void testTitleNull(){
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new Scenario(null, List.of(), List.of(), List.of() ));

        assertEquals("Title cannot be null", thrown.getMessage());
    }

    @Test
    void testTitleEmpty(){
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new Scenario("", List.of(), List.of(), List.of() ));

        assertEquals("Title cannot be empty", thrown.getMessage());
    }

    @Test
    void testExtActNull(){
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new Scenario("Title", null, List.of(), List.of() ));

        assertEquals("ExternalActors cannot be null", thrown.getMessage());
    }

    @Test
    void testIntActNull(){
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new Scenario("Title", List.of(), null, List.of() ));

        assertEquals("InternalActors cannot be null", thrown.getMessage());
    }

    @Test
    void testStepsNull(){
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new Scenario("Title", List.of(),  List.of(), null ));

        assertEquals("Steps cannot be null", thrown.getMessage());
    }

}