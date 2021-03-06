package com.company.math;

public class Minkowski implements Metric {
    @Override
    public double distance(Dot x, Dot y) {
        double ret = 0;

        for(int i = 0; i < x.dim(); i++) {
            ret += Math.pow(x.get(i) - y.get(i), 2);
        }

        return Math.sqrt(ret);
    }
}
