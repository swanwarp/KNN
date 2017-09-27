package com.company.math;

public class Dot {
    private final double[] x;

    public Dot(int dim) {
        x = new double[dim];
    }

    public void set(int i, double x_i) {
        x[i] = x_i;
    }

    public double get(int i) {
        return x[i];
    }

    public int dim() {
        return x.length;
    }
}
