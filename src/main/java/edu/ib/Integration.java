package edu.ib;

import org.apache.commons.math3.ode.FirstOrderIntegrator;
import org.apache.commons.math3.ode.nonstiff.ClassicalRungeKuttaIntegrator;
import org.apache.commons.math3.ode.nonstiff.EulerIntegrator;
import org.apache.commons.math3.ode.nonstiff.MidpointIntegrator;
import org.apache.commons.math3.ode.sampling.StepHandler;

/**
 * Klasa całkująca
 */

public class Integration {
    /**
     * Metoda całkująca metodą Eulera
     * @param x0 Wartości początkowe
     * @param h Próbkowanie
     * @param t Czas końcowy
     * @param t0 Czas początkowy
     * @param u Parametr
     * @param stepHandler Step handler
     * @return Wynik dla czasu końcowego
     */
    public double [] basicEuler(double [] x0, double h, double t, double t0, double u, StepHandler stepHandler){
        SecondODE simple= new SecondODE(u);
        FirstOrderIntegrator integrator=new EulerIntegrator(h);
        double [] x=new double[2];
        integrator.addStepHandler(stepHandler);
        integrator.integrate(simple,t0,x0,t,x);
        return x;
    }
    /**
     * Metoda całkująca metodą Midpoint
     * @param x0 Wartości początkowe
     * @param h Próbkowanie
     * @param t Czas końcowy
     * @param t0 Czas początkowy
     * @param u Parametr
     * @param stepHandler Step handler
     * @return Wynik dla czasu końcowego
     */
    public double [] changedEuler(double [] x0, double h, double t, double t0, double u, StepHandler stepHandler){
        SecondODE simple= new SecondODE(u);
        FirstOrderIntegrator integrator=new MidpointIntegrator(h);
        double [] x=new double[2];
        integrator.addStepHandler(stepHandler);
        integrator.integrate(simple,t0,x0,t,x);
        return x;
    }
    /**
     * Metoda całkująca metodą RK4
     * @param x0 Wartości początkowe
     * @param h Próbkowanie
     * @param t Czas końcowy
     * @param t0 Czas początkowy
     * @param u Parametr
     * @param stepHandler Step handler
     * @return Wynik dla czasu końcowego
     */
    public double [] rk4(double [] x0, double h, double t, double t0, double u, StepHandler stepHandler){
        SecondODE simple= new SecondODE(u);
        FirstOrderIntegrator integrator=new ClassicalRungeKuttaIntegrator(h);
        double [] x=new double[2];
        integrator.addStepHandler(stepHandler);
        integrator.integrate(simple,t0, x0,t,x);
        return x;
    }

}
