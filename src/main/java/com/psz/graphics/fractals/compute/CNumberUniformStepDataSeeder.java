package com.psz.graphics.fractals.compute;

import com.psz.graphics.fractals.cnumbers.CNumber;
import com.psz.graphics.fractals.cnumbers.CNumberOperationUnit;

import lombok.ToString;

@ToString
public class CNumberUniformStepDataSeeder implements Seeder<CNumber>{

    private final CNumber topLeft, rightBottom;
    private final int width, height;
    private final double gradientReal, gradientImaginary;

    public CNumberUniformStepDataSeeder(CNumber topLeft, CNumber rightBottom, int width, int height){
        this.topLeft = topLeft;
        this.rightBottom = rightBottom;
        this.width = width;
        this.height = height;
        this.gradientReal = (rightBottom.getreal() - topLeft.getreal()) / width;
        this.gradientImaginary = (topLeft.getImaginary() - rightBottom.getImaginary()) / height;
    }

    @Override
    public CNumber getValueFor(int x, int y) {
        return CNumberOperationUnit.create(topLeft.getreal() + x * gradientReal, topLeft.getImaginary() - y * gradientImaginary);
    }
    
}
