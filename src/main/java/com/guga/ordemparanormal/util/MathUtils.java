package com.guga.ordemparanormal.util;

public class MathUtils {
    public static int Oscillate(int input, int min, int max) {
        int range = max - min ;
        return min + Math.abs(((input + range) % (range * 2)) - range);
    }
}
