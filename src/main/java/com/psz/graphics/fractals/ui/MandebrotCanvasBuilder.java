package com.psz.graphics.fractals.ui;

import com.psz.graphics.fractals.cnumbers.CNumberOperationUnit;

public class MandebrotCanvasBuilder {
    public static final int DEFAULT_WIDTH = 600;
    public static final int DEFAULT_HEIGHT = 600;
    public static final int DEFAULT_MAX_ITERATIONS = 10;
    public static final double DEFAULT_Z_REAL = 0;
    public static final double DEFAULT_Z_IMAGINARY = 0;
    public static final double DEFAULT_CONVERGENCE_THRESHOLD = 0.05;
    public static final double DEFAULT_DIVERGENCE_THRESHOLD = 10.0;
    public static final double DEFAULT_TOP_LEFT_REAL = -2.5;
    public static final double DEFAULT_TOP_LEFT_IMAGINARY = 1.5;
    public static final double DEFAULT_BOTTOM_RIGHT_REAL = 1.5;
    public static final double DEFAULT_BOTTOM_RIGHT_IMAGINARY = -1.5;


    private int imageWidth, imageHeight, maxIterations;
    private double zReal, zImaginary, convergenceThreshold, divergenceThreshold;
    private double topLeftReal, topLeftImaginary, bottomRightReal, bottomRightImaginary;
    private ColorScaleBar.Orientation orientation;

    public MandebrotCanvasBuilder(){
        imageWidth = DEFAULT_WIDTH;
        imageHeight = DEFAULT_HEIGHT;
        maxIterations = DEFAULT_MAX_ITERATIONS;
        zReal = DEFAULT_Z_REAL;
        zImaginary = DEFAULT_Z_IMAGINARY;
        convergenceThreshold = DEFAULT_CONVERGENCE_THRESHOLD;
        divergenceThreshold = DEFAULT_DIVERGENCE_THRESHOLD;
        topLeftReal = DEFAULT_TOP_LEFT_REAL;
        topLeftImaginary = DEFAULT_TOP_LEFT_IMAGINARY;
        bottomRightReal = DEFAULT_BOTTOM_RIGHT_REAL;
        bottomRightImaginary = DEFAULT_BOTTOM_RIGHT_IMAGINARY;
        orientation = ColorScaleBar.Orientation.VERTICAL;
    }

    public MandebrotCanvasBuilder imageWidth(int value){
        this.imageWidth = value;
        return this;
    }

    public MandebrotCanvasBuilder imageHeight(int value){
        this.imageHeight = value;
        return this;
    }

    public MandebrotCanvasBuilder maxIterations(int value){
        this.maxIterations = value;
        return this;
    }

    public MandebrotCanvasBuilder zReal(double value){
        this.zReal = value;
        return this;
    }

    public MandebrotCanvasBuilder zImaginary(double value){
        this.zImaginary = value;
        return this;
    }

    public MandebrotCanvasBuilder convergenceThreshold(double value){
        this.convergenceThreshold = value;
        return this;
    }

    public MandebrotCanvasBuilder divergenceThreshold(double value){
        this.divergenceThreshold = value;
        return this;
    }

    public MandebrotCanvasBuilder topLeftReal(double value){
        this.topLeftReal = value;
        return this;
    }    

    public MandebrotCanvasBuilder topLeftImaginary(double value){
        this.topLeftImaginary = value;
        return this;
    }  

    public MandebrotCanvasBuilder bottomRightReal(double value){
        this.bottomRightReal = value;
        return this;
    }  

    public MandebrotCanvasBuilder bottomRightImaginary(double value){
        this.bottomRightImaginary = value;
        return this;
    }  

    public MandebrotCanvasBuilder scaleBarOrientation(ColorScaleBar.Orientation value){
        this.orientation = value;
        return this;
    }     

    public MandebrotCanvas build(){
        return new MandebrotCanvas( imageWidth,  imageHeight, 
                            CNumberOperationUnit.create(zReal, zImaginary), 
                            convergenceThreshold, divergenceThreshold, maxIterations, 
                            CNumberOperationUnit.create(topLeftReal, topLeftImaginary), 
                            CNumberOperationUnit.create(bottomRightReal, bottomRightImaginary),
                            orientation);
    } 
}
