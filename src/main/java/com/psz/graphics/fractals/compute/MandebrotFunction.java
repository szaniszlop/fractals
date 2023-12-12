package com.psz.graphics.fractals.compute;

import java.util.function.Function;

import com.psz.graphics.fractals.cnumbers.CNumber;
import com.psz.graphics.fractals.cnumbers.CNumberOperationUnit;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MandebrotFunction implements Function<CNumber, Integer>{

    private final CNumber initialValue;
    private double convergenceThreshold, divergenceThreshold;
    private final int maxIterations;

    @Override
    public Integer apply(CNumber constant) {
        CNumber nextValue, previosValue = initialValue;
        int iterations = 0;
        double distance = 0;
        do{
            // Z(n) = Z(n-1) * Z(n-1) + C
            nextValue = CNumberOperationUnit.add(CNumberOperationUnit.multiply(previosValue, previosValue), constant);
            distance = CNumberOperationUnit.distance(nextValue, previosValue);
            previosValue = nextValue;
            iterations++;
        }while( distance > convergenceThreshold && distance < divergenceThreshold && iterations < maxIterations);
        
        return (distance >= divergenceThreshold || iterations == maxIterations) ? iterations : 0;
    }

}
