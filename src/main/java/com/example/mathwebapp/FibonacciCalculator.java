package com.example.mathwebapp;

import java.util.*;

public class FibonacciCalculator {
    public static double add(double a, double b) { return a + b; }
    public static double subtract(double a, double b) { return a - b; }
    public static double multiply(double a, double b) { return a * b; }
    public static String divide(double a, double b) {
        return (b == 0) ? "Cannot divide by zero" : String.format("%.2f", a / b);
    }

    public static class CurveSegment {
        public List<Double> X;
        public List<Double> Y;
        public double radius;
        public int index;

        public CurveSegment(List<Double> X, List<Double> Y, double radius, int index) {
            this.X = X;
            this.Y = Y;
            this.radius = radius;
            this.index = index;
        }
    }

    public static List<CurveSegment> calculateIndividualCurves(int N) {
        List<CurveSegment> segments = new ArrayList<>();
        List<Integer> F = new ArrayList<>(Arrays.asList(0, 1));
        for (int i = 0; i < N; i++) F.add(F.get(F.size() - 1) + F.get(F.size() - 2));
        List<double[]> I = new ArrayList<>(Arrays.asList(
            new double[]{0, 0}, new double[]{0, 0}, new double[]{0, 0}
        ));
        for (int j = 3; j < N; j++) {
            int pivot = (j - 2) % 4;
            boolean X_flag = (pivot == 1 || pivot == 3);
            boolean Sign_Flag = (pivot == 1 || pivot == 2);
            double x_temp = I.get(j - 1)[0];
            double y_temp = I.get(j - 1)[1];
            if (Sign_Flag && X_flag) x_temp += F.get(j - 2);
            else if (Sign_Flag) y_temp += F.get(j - 2);
            else if (X_flag) x_temp -= F.get(j - 2);
            else y_temp -= F.get(j - 2);
            I.add(new double[]{x_temp, y_temp});
        }
        F.remove(0);
        I.remove(0);
        int[][] Angle = {{1, 91}, {91, 181}, {181, 271}, {271, 361}};
        for (int i = 0; i < I.size(); i++) {
            List<Double> segmentX = new ArrayList<>();
            List<Double> segmentY = new ArrayList<>();
            int[] AnR = Angle[i % 4];
            double r = F.get(i);
            double x_align = I.get(i)[0];
            double y_align = I.get(i)[1];
            for (int j = AnR[0]; j < AnR[1]; j++) {
                double x_temp = r * Math.cos(Math.toRadians(j));
                double y_temp = r * Math.sin(Math.toRadians(j));
                segmentX.add(x_temp + x_align);
                segmentY.add(y_temp + y_align);
            }
            segments.add(new CurveSegment(segmentX, segmentY, r, i));
        }
        return segments;
    }
}
