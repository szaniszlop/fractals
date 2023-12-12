package com.psz.graphics.fractals.compute;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class DataPlane<T>{
    private static final int MAX_OUTPUT = 100;

    private final int width, height;
    private final Seeder<T> seeder;

    T[] dataPlane;

    public DataPlane(int width, int height, Seeder<T> seeder){
        this.width = width;
        this.height = height;
        this.seeder = seeder;
        initDataPlane();
    }

    protected DataPlane(int width, int height, List<T> data){
        assert data != null;
        assert data.size() >= width * height;
        this.width = width;
        this.height = height;
        seeder = null;

        dataPlane = (T[])data.toArray();
    }

    public Stream<T> toStream(){
        return Arrays.stream(dataPlane);
    }
    
    private void initDataPlane(){
        dataPlane = (T[]) new Object[width * height];
        for( int i = 0; i < (width*height) ; i++){
            dataPlane[i] = seeder.getValueFor(i % width, i / height);
        }
    }

    public String toString(){
        StringBuffer buffer = new StringBuffer();
        for(int row = 0 ; row < height ; row++){
            if( row < MAX_OUTPUT || row >= height - MAX_OUTPUT ){
                // print first two and last two rows
                buffer.append("\n").append(row).append("\t[ ");
                for(int column = 0 ; column < width ;  column++){
                    if( column < MAX_OUTPUT || column >= width - MAX_OUTPUT ){
                        buffer.append(column > 0 ? ", " : "");
                        buffer.append(dataPlane[row * width + column]);
                    } else {
                        if(column == MAX_OUTPUT){
                            buffer.append("\t ......... \t");
                        }
                    }
                }
                buffer.append(" ]");                
            } else {
                if(row == MAX_OUTPUT){
                    // pring row representing skipped rows
                    buffer.append("\n  .........");
                }
            }
        }
        return buffer.toString();
    }
}
