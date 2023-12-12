package com.psz.graphics.fractals.cnumbers;

public class CNumberOperationUnit {

    public static CNumber create(double real, double imaginary){
        return new CNumberImpl(real, imaginary);
    }

    public static CNumber add(CNumber a, CNumber b){
        return new CNumberImpl(a.getreal() + b.getreal(), a.getImaginary() + b.getImaginary());
    }

    public static CNumber substract(CNumber a, CNumber b){
        return new CNumberImpl(a.getreal() - b.getreal(), a.getImaginary() - b.getImaginary());
    }

    public static CNumber multiply(CNumber a, CNumber b){
        double newReal = a.getreal() * b.getreal() - a.getImaginary() * b.getImaginary();
        double newImaginary = a.getreal() * b.getImaginary() + a.getImaginary() * b.getreal();
        return new CNumberImpl(newReal, newImaginary);
    }

    public static double distance(CNumber a, CNumber b){
        double rd = a.getreal() - b.getreal();
        rd = rd * rd;
        double id = a.getImaginary() - b.getImaginary();
        id = id * id;
        return Math.sqrt(rd + id);

    }

    public static CNumber congregate(CNumber a){
        return new CNumberImpl(a.getreal(), -a.getImaginary());
    }

    static final class CNumberImpl implements CNumber{

        private final double real, imaginary;

        CNumberImpl(double real, double imaginary){
            this.real = real;
            this.imaginary = imaginary;
        }

        @Override
        public double getreal() {
            return this.real;
        }

        @Override
        public double getImaginary() {
            return this.imaginary;
        }

        @Override
        public String toString(){
            return String.format("(%f, i%f)", real, imaginary);
        }


    }
}
