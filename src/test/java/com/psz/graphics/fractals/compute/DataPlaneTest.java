package com.psz.graphics.fractals.compute;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.psz.graphics.fractals.cnumbers.CNumber;
import com.psz.graphics.fractals.cnumbers.CNumberOperationUnit;


public class DataPlaneTest {

    @Test
    public void constantDataPlaneSeederTest(){
        IntegerConstantDataSeeder seeder = new IntegerConstantDataSeeder(1);

        DataPlane<Integer> plane = new DataPlane<>(10, 5, seeder);

        int sum = plane.toStream().reduce(0, (a, b) -> a + b);
        System.out.println(plane);
        assertEquals(50, sum);
    }

    @Test
    public void cNumberDataPlaneSeederTest(){
        CNumberUniformStepDataSeeder seeder = new CNumberUniformStepDataSeeder(
                CNumberOperationUnit.create(-2, 2), 
                CNumberOperationUnit.create(2, -2), 
                4,4);

        DataPlane<CNumber> plane = new DataPlane<>(4, 4, seeder);

        System.out.println(plane);
    }


    @Test
    public void largeCNumberDataPlaneSeederTest(){
        CNumberUniformStepDataSeeder seeder = new CNumberUniformStepDataSeeder(
                CNumberOperationUnit.create(-2, 2), 
                CNumberOperationUnit.create(2, -2), 
                600,600);

        DataPlane<CNumber> plane = new DataPlane<>(600, 600, seeder);

        System.out.println(plane);
    }    
}
