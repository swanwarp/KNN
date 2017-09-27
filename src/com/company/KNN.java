package com.company;

import com.company.math.Dot;
import com.company.math.Metric;
import com.company.utils.CPair;
import com.company.utils.Util;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class KNN {
    private Metric p;
    private Pair<Dot, Integer>[] data;
    private int numOfClasses;
    private int k;

    public KNN(Metric p, Pair<Dot, Integer>[] data, int numOfClasses) {
        this.p = p;
        this.data = data;
        this.numOfClasses = numOfClasses;

        findK((int) Math.sqrt(data.length));
    }

    private void findK(int n) {
        if(k == 0) {
            k = (int) Math.sqrt(data.length);
        }

        Pair<Dot, Integer>[] toRnd = Util.shuffleArray(data);
        Pair<Dot, Integer>[] test = Arrays.copyOfRange(toRnd, 0, n),
                train = Arrays.copyOfRange(toRnd, n, toRnd.length);

        int m = Integer.MAX_VALUE;

        for(int i = k; i >= 1; i++) {
            int m1 = 0;

            for(Pair<Dot, Integer> p : test) {
                if(solve(p.getKey(), train) != p.getValue()) {
                    m1++;
                }
            }

            if(m1 < m) {
                k = i;
                m = m1;
            }
        }
    }

    public int solve(Dot d) {
        return solve(d, data);
    }

    private int solve(Dot d, Pair<Dot, Integer>[] data) {
        ArrayList<CPair> toSort = new ArrayList<>();

        for(int i = 0; i < data.length; i++) {
            toSort.add(new CPair(p.distance(d, data[i].getKey()), data[i].getValue()));
        }

        Collections.sort(toSort);

        int most[] = new int[numOfClasses];

        for(int i = 0; i < k; i++) {
            most[toSort.get(i).c]++;
        }

        int max = 0;
        int max_c = 0;

        for(int i = 0; i < numOfClasses; i++) {
            if(most[i] > max) {
                max = most[i];
                max_c = i;
            }
        }

        return max_c;
    }
}
