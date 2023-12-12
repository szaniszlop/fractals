package com.psz.graphics.fractals.compute;

import java.util.List;

import com.psz.graphics.fractals.cnumbers.CNumber;

public class MandebrotSetCalculator extends MandebrotFunction{
    private final int width, height;
    private final DataPlane<CNumber> dataPlane;

    public MandebrotSetCalculator(CNumber initialValue, double convergenceThreshold, double divergenceThreshold, int maxIterations,
        CNumber topLeft, CNumber rightBottom, int width, int height){
            super(initialValue, convergenceThreshold, divergenceThreshold, maxIterations);
            this.width = width;
            this.height = height;

            dataPlane = new DataPlane<>(width, height, new CNumberUniformStepDataSeeder(topLeft, rightBottom, width, height));
        }

    public DataPlane<Integer> calculate(){
        List<Integer> result = dataPlane.toStream().parallel().map( (value) -> this.apply(value)).toList();
        return new DataPlane<Integer>(width, height, result);
    }    
}
