package com.psz.graphics.fractals.compute;

import org.junit.jupiter.api.Test;

import com.psz.graphics.fractals.cnumbers.CNumberOperationUnit;

public class MandebrotSetCalculatorTest {
    @Test
    void testCalculate() {
        MandebrotSetCalculator calculator = new MandebrotSetCalculator(
            CNumberOperationUnit.create(0, 0), 
            0.1, 10.0, 10, 
            CNumberOperationUnit.create(-2, 2), 
            CNumberOperationUnit.create(2, -2), 
            100, 100);
        DataPlane<Integer> result = calculator.calculate();
        System.out.print(result);
    }
}
