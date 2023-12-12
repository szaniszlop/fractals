package com.psz.graphics.fractals.compute;

public interface Seeder<T> {
    T getValueFor(int x, int y);
}
