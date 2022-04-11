package com.example.api_rest.LoopUnitTesting;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestPass {

    @Test
    public void testLoop() throws Exception {
        SimpleLoopTest simple = new SimpleLoopTest();

        assertEquals(0, simple.findSum(0)); //Test 1
        assertEquals(0, simple.findSum(-1)); //Test 2
        assertEquals(5, simple.findSum(1)); //Test 3
        assertEquals(5, simple.findSum(2)); //Test 4
        assertEquals(17, simple.findSum(5)); //Test 5
        assertEquals(26, simple.findSum(9)); //Test 6
        assertEquals(36, simple.findSum(10)); //Test 7
        assertEquals(0, simple.findSum(11)); //Test 8
    }
}
