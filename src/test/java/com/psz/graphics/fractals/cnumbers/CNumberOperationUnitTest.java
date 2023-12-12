package com.psz.graphics.fractals.cnumbers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CNumberOperationUnitTest {
    @Test
    void testAdd() {
        double ar, ai, br, bi;
        ar = Math.random();
        br = Math.random();
        ai = Math.random();
        bi = Math.random();
        CNumber a = CNumberOperationUnit.create(ar, ai);
        CNumber b = CNumberOperationUnit.create(br, bi);
        CNumber result = CNumberOperationUnit.add(a, b);
        assertEquals(ar + br, result.getreal());
        assertEquals(ai + bi, result.getImaginary());
        System.out.println(result);
    }

    @Test
    void testCongregate() {
        double ar, ai;
        ar = Math.random();
        ai = Math.random();
        CNumber a = CNumberOperationUnit.create(ar, ai);
        CNumber result = CNumberOperationUnit.congregate(a);
        assertEquals(ar, result.getreal());
        assertEquals(-ai, result.getImaginary());
        System.out.println(result);        
    }

    @Test
    void testDistance() {
        double ar, ai, br, bi;
        ar = Math.random();
        br = Math.random();
        ai = Math.random();
        bi = Math.random();
        CNumber a = CNumberOperationUnit.create(ar, ai);
        CNumber b = CNumberOperationUnit.create(br, bi);
        double result = CNumberOperationUnit.distance(a, b);
        assertEquals(Math.sqrt((ar-br)*(ar-br) + (ai-bi)*(ai-bi)), result);
        System.out.println(result);        
    }

    @Test
    void testMultiply() {
        double ar, ai, br, bi;
        ar = Math.random();
        br = Math.random();
        ai = Math.random();
        bi = Math.random();
        CNumber a = CNumberOperationUnit.create(ar, ai);
        CNumber b = CNumberOperationUnit.create(br, bi);
        CNumber result = CNumberOperationUnit.multiply(a, b);
        assertEquals(ar * br - ai * bi, result.getreal());
        assertEquals(ai * br + ar * bi, result.getImaginary());
        System.out.println(result);        
    }

    @Test
    void testSubstract() {
        double ar, ai, br, bi;
        ar = Math.random();
        br = Math.random();
        ai = Math.random();
        bi = Math.random();
        CNumber a = CNumberOperationUnit.create(ar, ai);
        CNumber b = CNumberOperationUnit.create(br, bi);
        CNumber result = CNumberOperationUnit.substract(a, b);
        assertEquals(ar - br, result.getreal());
        assertEquals(ai - bi, result.getImaginary());
        System.out.println(result);        
    }
}
