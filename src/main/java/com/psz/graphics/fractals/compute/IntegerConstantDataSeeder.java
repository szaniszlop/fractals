package com.psz.graphics.fractals.compute;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class IntegerConstantDataSeeder implements Seeder<Integer> {

    private final int value;

    @Override
    public Integer getValueFor(int x, int y) {
        return value;
    }
    
}
