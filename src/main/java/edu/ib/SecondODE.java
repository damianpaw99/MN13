package edu.ib;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.ode.FirstOrderDifferentialEquations;

/**
 * Klasa licząca równanie różniczkowe
 */
public class SecondODE implements FirstOrderDifferentialEquations{

    private double u;

    public SecondODE(double u) {
        this.u = u;
    }

    @Override
    public int getDimension() {
        return 2;
    }

    @Override
    public void computeDerivatives(double t, double[] x, double[] xDot) throws MaxCountExceededException, DimensionMismatchException {
        xDot[0]=x[1];
        xDot[1]=u*(1-Math.pow(x[0],2))*x[1]-x[0];
    }

}
